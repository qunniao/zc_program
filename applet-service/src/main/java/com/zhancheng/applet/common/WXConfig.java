package com.zhancheng.applet.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
public class WXConfig implements com.github.wxpay.sdk.WXPayConfig {

    /**
     * 服务器地址
     */
    @Value("${weChat.domain}")
    private String domain;


    /**
     * 商户Id
     */
    @Value("${weChat.mchId}")
    private String mchId;
    /**
     * 支付key
     */
    @Value("${weChat.key}")
    private String key;

    /**
     * appid
     */
    @Value("${weChat.appId}")
    private String appId;

    /**
     * appSecret
     */
    @Value("${weChat.secret}")
    private String appSecret;

    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return mchId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    /**
     * 连接超时时间
     *
     * @return
     */
    @Override
    public int getHttpConnectTimeoutMs() {
        return 10 * 1000;
    }

    /**
     * 读取超时时间
     *
     * @return
     */
    @Override
    public int getHttpReadTimeoutMs() {
        return 10 * 1000;
    }

    public String getMchId() {
        return mchId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getGrantType() {
        return "authorization_code";
    }

    /**
     * 回调地址
     */
    public String getNotifyURL() {
        return this.domain + "/orderInfo/wxPayUnifiedNotify";
    }

}
