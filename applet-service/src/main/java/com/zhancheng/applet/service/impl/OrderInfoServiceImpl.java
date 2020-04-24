package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhancheng.applet.common.WXConfig;
import com.zhancheng.applet.common.WxUtil;
import com.zhancheng.applet.service.OrderInfoService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.commom.SignUtil;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private WXConfig wxConfig;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderPayHistoryMapper orderPayHistoryMapper;


    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public String saveOrderInfo(Long uid, JSONObject json) {
        // 生成 订单号
        String orderNum = SignUtil.generateNonceStr();
        // 获取 sku 信息
        JSONArray skuArray = json.getJSONArray("skuInfo");
        JSONObject productInfo = json.getJSONObject("productInfo");
        Long addressId = json.getLong("addressId");
        //获得优惠券id
        Long cid = json.getLong("cid");
        Coupon coupon = null;
        List<CouponUser> couponUsers = null;
        if (cid != null) {
            Map<String, Object> hashMap = MapFactory.getHashMap();
            hashMap.put("cid", cid);
            List<Coupon> coupons = couponMapper.selectByMap(hashMap);
            hashMap.put("uid", uid);
            hashMap.put("is_use", "0");
            couponUsers = couponUserMapper.selectByMap(hashMap);
            if (couponUsers.size() <= 0 || coupons.size() <= 0) {
                throw new MyException(CodeMsg.COUPON_NOT_EXISTED);
            }
            coupon = coupons.get(0);
            LocalDateTime useDateEnd = coupon.getUseDateEnd();
            LocalDateTime useDateStart = coupon.getUseDateStart();
            LocalDateTime now = LocalDateTime.now();
            //判断使用的优惠券是否过期
            if (!now.isBefore(useDateEnd) || !now.isAfter(useDateStart)) {
                throw new MyException(CodeMsg.COUPON_EXPIRED);
            }
        }
        BigDecimal sum = new BigDecimal(0);

        if (ObjectUtil.isNotEmpty(skuArray)){

            for (int i = 0; i < skuArray.size(); i++) {
                JSONObject jsonObject = skuArray.getJSONObject(i);
                //获得skuid
                long skuid = jsonObject.getLong("skuid");
                ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(skuid);

                int num = jsonObject.getInt("num");
                System.err.println(num);
                if (num <= 0 || productGuigeSku == null) {
                    throw new MyException(CodeMsg.PARAMETER_ERROR);
                }
                BigDecimal price = productGuigeSku.getPrice();
                //获得sku数量
                //判断数量是否大于库存
                if (productGuigeSku.getNum() < num) {
                    throw new MyException(CodeMsg.GOODS_NUM_ERROR);
                }
                System.err.println(productGuigeSku);
                // 减少库存数量
                productGuigeSku.setNum(productGuigeSku.getNum() - num);
                System.err.println(productGuigeSku);
                productGuigeSku.updateById();
                sum = sum.add(price.multiply(new BigDecimal(num)));
                //存储订单商品
                OrderProduct orderProduct = new OrderProduct();
                Product product = productMapper.selectById(productGuigeSku.getPid());
                orderProduct.setOrderNumber(orderNum);
                orderProduct.setProductName(product.getProductName());
                orderProduct.setProductNum(num);
                orderProduct.setProductPrice(productGuigeSku.getPrice());
                orderProduct.setSkuId(productGuigeSku.getSkuId());
                orderProduct.setSpuId(productGuigeSku.getPid());
                orderProductMapper.insert(orderProduct);
            }
        } else if (ObjectUtil.isNotNull(productInfo)){
            // 产品id
            Long pid = productInfo.getLong("pid");
            // 数量
            Integer num = productInfo.getInt("num");

            Product product = productMapper.selectById(pid);

            if (num < 1 || num > product.getStore()){
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }

            product.setStore(product.getStore() - num);
            product.updateById();
            // 算出总价格
            sum = new BigDecimal(num).multiply(product.getPrice());

            // 添加订单商品
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderNumber(orderNum)
                    .setProductName(product.getProductName())
                    .setProductNum(num)
                    .setProductPrice(product.getPrice())
                    .setSpuId(product.getPid());
            orderProduct.insert();
        }

        BigDecimal payMoney = sum;
        //判断优惠券是否满足金额
        if (coupon != null) {
            BigDecimal minMoney = coupon.getMinMoney();
            if (sum.compareTo(minMoney) <= 0) {
                throw new MyException(CodeMsg.COUPON_FAIL);
            }
            payMoney = payMoney.subtract(minMoney);
        }
        if (couponUsers != null) {
            //标记优惠券已经被使用
            CouponUser couponUser = new CouponUser();
            couponUser.setId(couponUsers.get(0).getId());
            couponUser.setIsUse(true);
            couponUserMapper.updateById(couponUser);
        }

        OrderInfo orderInfo = new OrderInfo();

        UserAddress userAddress = userAddressMapper.selectById(addressId);

        if (ObjectUtil.isNotNull(userAddress)){
            // 收货地址
            String contactAddress = userAddress.getProvince() + userAddress.getCity()
                    + userAddress.getCounty() + userAddress.getContactAddress();

            orderInfo.setContactName(userAddress.getContactName())
                    .setContactPhone(userAddress.getContactPhone())
                    .setContactAddress(contactAddress);
        }

        orderInfo.setUid(uid);
        //总金额
        orderInfo.setTotalPrice(sum);
        //实际应该支付
        orderInfo.setPayMoney(payMoney);
        //订单号
        orderInfo.setOrderNumber(orderNum);
        //保存订单状态待支付
        orderInfo.setOrderState(0);
        orderInfoMapper.insert(orderInfo);
        return R.ok(orderNum);
    }

    @Override
    public String wxPay(String orderNumber, Long uid) throws Exception {
        Map<String, Object> query = MapFactory.getHashMap();
        User user = userMapper.selectById(uid);
        //获取openid
        String openid = user.getOpenid();
        query.put("uid", uid);
        query.put("order_number", orderNumber);
        query.put("order_state", 0);
        List<OrderInfo> orderInfos = orderInfoMapper.selectByMap(query);
        if (orderInfos == null || orderInfos.size() <= 0) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        OrderInfo orderInfo = orderInfos.get(0);
        int price = orderInfo.getPayMoney().multiply(new BigDecimal(100)).intValue();
        Map<String, String> map = wxUtil.unifiedOrder("商品下单支付", "", orderNumber, price + "", openid);
        if ("FAIL".equals(map.get("status"))) {
            return R.fail();
        }
        return R.ok(map);
    }

    @Override
    public String wxPayUnifiedNotify(String notifyXml) {
        LOG.info("notifyXml\t" + notifyXml);
        synchronized (new Object()) {
            try {
                Map<String, String> notify = WXPayUtil.xmlToMap(notifyXml);
                //订单号
                String orderNumber = notify.get("out_trade_no");
                //应用Id
                String appid = notify.get("appid");
                //支付金额
                String totalFee = notify.get("total_fee");
                //支付用户自定义信息
//                String attach = notify.get("attach");
                //判断AppID是否正确
                if (!appid.equals(wxConfig.getAppID())) {
                    return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[app_id错误!]]></return_msg>" + "</xml>";
                }
                Map<String, Object> hashMap = MapFactory.getHashMap();
                //判断订单是否是支付状态
                hashMap.put("order_state", "0");
                hashMap.put("order_number",orderNumber);
                List<OrderInfo> orderInfos = orderInfoMapper.selectByMap(hashMap);
                if (orderInfos == null) {
                    return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[没有这个订单号]]></return_msg>" + "</xml>";
                }
                OrderInfo orderInfo = orderInfos.get(0);
                String payMoney = orderInfo.getPayMoney().multiply(new BigDecimal("100")).intValue() + "";
                if (!payMoney.equals(totalFee)) {
                    LOG.info("--------------totalFee:" + totalFee);
                    LOG.info("--------------payMoney:" + payMoney);
                    return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[订单金额不一致]]></return_msg>" + "</xml>";
                }

                if ("SUCCESS".equals(notify.get("return_code")) && "SUCCESS".equals(notify.get("result_code"))) {
                    //验证签名
                    if (WXPayUtil.isSignatureValid(notify, wxConfig.getKey())) {
                        OrderInfo temp = new OrderInfo();
                        temp.setOid(orderInfo.getOid());
                        temp.setPayWay(2);
                        temp.setPayPlatform(2);
                        temp.setOrderState(1);
                        orderInfoMapper.updateById(temp);
                        OrderPayHistory orderPayHistory = new OrderPayHistory();
                        orderPayHistory.setOrderNumber(orderNumber);
                        orderPayHistory.setPayWay(2);
                        orderPayHistory.setRemark("微信订单支付");
                        orderPayHistory.setOrderMoney(orderInfo.getTotalPrice());
                        orderPayHistory.setPayMoney(orderInfo.getPayMoney());
                        orderPayHistoryMapper.insert(orderPayHistory);
                        LOG.info("订单:" + orderNumber + ",回调成功!!!");
                        return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
                    }
                }


            } catch (Exception ex) {
                return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[产生异常.]]></return_msg>" + "</xml>";
            }
        }

        return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[支付失败.]]></return_msg>" + "</xml>";


    }
}
