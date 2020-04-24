package com.zhancheng.applet.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author tangchao
 */
public class SMSUtil {

    private static final Logger logger = LoggerFactory.getLogger(SMSUtil.class);

    /***
     *
     * @param phoneNumbers 必填:待发送手机号
     * @param templateCode 必填:短信模板-可在短信控制台中找到
     *                                                  修改手机号码:SMS_161295122
     * @param code 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"
     * @return 发送成功返回 true
     */
    public static boolean sendAliSMS(String phoneNumbers, String templateCode, String code) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("code", code);
            String templateParam = JSONObject.toJSONString(map);
            CommonResponse response = AliSMSUtil.sendSms(phoneNumbers, templateCode, templateParam);
            JSONObject jsonObject = JSON.parseObject(response.getData());
            String rMessage = jsonObject.getString("Message");
            String rCode = jsonObject.getString("Code");
            if (rMessage != null && "OK".equals(rMessage) && rCode != null && "OK".equals(rCode)) {
                return true;
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static String getCode() {
        //生成短信6位随机验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            code.append(code.toString() + random.nextInt(10));
        }
        return code.toString();
    }

}
