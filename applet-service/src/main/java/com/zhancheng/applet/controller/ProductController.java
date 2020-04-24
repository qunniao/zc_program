package com.zhancheng.applet.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhancheng.applet.service.ProductService;
import com.zhancheng.applet.service.ProductTypeService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品spu表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "商品相关相关")
@RestController
@RequestMapping("{version}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    private static final String SORT = "desc";

    @Verify
    @GetMapping("/productIndex")
    @ApiOperation(value = "返回商城页面的商品", notes = "")
    public String productIndex() {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("pid", 0);
        //获取所有的一级分类
        Collection<ProductType> productTypes = productTypeService.listByMap(hashMap);
        Iterator<ProductType> iterator = productTypes.iterator();
        StringBuffer result = new StringBuffer("{\"list\":[{");
        while (iterator.hasNext()) {
            if (!"{\"list\":[{".equals(result.toString())) {
                result.append(",{");
            }
            ProductType next = iterator.next();
            result.append("\"tid\":" + next.getTid());
            result.append(",\"type_name\": \"" + next.getTypeName() + "\"");
            result.append(",\"goods\":");
            List<Map<String, Object>> product = productService.getProduct(next.getTid(),  "0", "3");
            result.append(JSONObject.toJSONString(product));
            result.append("}");
        }
        result.append("]}");
        System.err.println(result.toString());
        return R.ok(JSONObject.parseObject(result.toString()));
    }


    @Verify
    @GetMapping("/productList")
    @ApiOperation(value = "返回更多页面的商品列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "可以传入一级分类Id也可以传入二级分类Id", required = true),
            @ApiImplicitParam(name = "desc", value = "值为desc则是price降序,其他是升序", required = true),
            @ApiImplicitParam(name = "page", value = "页数,从第一页开始", required = true, defaultValue = "1")
    })
    public String productIndex(Long pid, String desc, Integer page) {
        //查询是否有子Id，有？一级分类：二级分类
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("pid", pid);
        List<Map<String, Object>> result = null;
        Collection<ProductType> productTypes = productTypeService.listByMap(hashMap);
        if (!SORT.equals(desc)) {
            desc = "";
        }
        //一级分类查询
        if (productTypes.size() > 0) {
            result = productService.getProduct(pid, desc, (page - 1) * 10 + "", page * 10 + "");
        }
//        二级分类查询
        else {
            result = productService.getProduct(desc, (page - 1) * 10 + "", page * 10 + "", pid.toString());
        }
        return R.ok(result);
    }

    @Verify
    @GetMapping("/productInfo")
    @ApiOperation(value = "返回商品详细信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "这个是商品Id", required = true),
            @ApiImplicitParam(name = "type", value = "0是手机登录,1电脑网页登录", defaultValue = "1")
    })
    public String productInfo(Long pid, Integer type) {
        if (type == null) {
            type = 1;
        }
        String productInfo = productService.getProductInfo(pid, type);
        return productInfo;
    }

    @Verify
    @GetMapping("/searchProduct")
    @ApiOperation(value = "模糊搜索商品", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "word", value = "关键词", required = true),
            @ApiImplicitParam(name = "desc", value = "值为desc则是price降序,其他是升序", required = true),
            @ApiImplicitParam(name = "page", value = "页数,从第一页开始", required = true, defaultValue = "1")
    })
    public String searchProduct(String word, Integer page, String desc) {
        if (!SORT.equals(desc)) {
            desc = "";
        }
        return productService.searchProduct(word, (page - 1) * 10 + "", page * 10 + "", desc);
    }

    @Verify
    @GetMapping("/getParam/{pid}")
    @ApiOperation(value = "查看商品参数", notes = "传入商品Id查询参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "商品id", required = true)
    })
    public String getParam(@PathVariable Long pid) {
        return R.ok(productService.getParam(pid));
    }

    @Verify
    @GetMapping("/getSku/{pid}")
    @ApiOperation(value = "查看商品规格", notes = "传入商品Id查询参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "商品id", required = true)
    })
    public String getSku(@PathVariable Long pid) {
        return R.ok(productService.getSku(pid));
    }

    @Verify
    @GetMapping("/getList")
    @ApiOperation(value = "查询商品列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "可以传入一级分类Id也可以传入二级分类Id"),
            @ApiImplicitParam(name = "sortType", value = "值为1或空是price降序,其他是升序", required = true)
    })
    public String getList(PageDto pageDto, Long tid, Integer sortType) {

        return R.ok(productService.getList(pageDto, tid, sortType));
    }
}

