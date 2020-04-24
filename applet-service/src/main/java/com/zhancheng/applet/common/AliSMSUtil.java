package com.zhancheng.applet.common;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangchao
 */
public class AliSMSUtil {
    private static final Logger logger = LoggerFactory.getLogger(AliSMSUtil.class);
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";


    private static final String ACCESS_KEY_ID = "LTAIt5HT5tuBdPsR";

    private static final String ACCESS_KEY_SECRET = "GrdKkl5crqEmJDlT70FzHQk4GKeAz2";

    /***
     *
     * @param phoneNumbers 必填:待发送手机号
     * @param templateCode 必填:短信模板-可在短信控制台中找到
     * @param templateParam 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"
     * @return
     * @throws ClientException
     */
    public static CommonResponse sendSms(String phoneNumbers, String templateCode, String templateParam) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "湛程网络科技");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(response.getData());
            return response;
        } catch (ServerException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (ClientException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
