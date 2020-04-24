package com.zhancheng.core.constant;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * rest返回对象
 *
 * @param <T>
 * @author tangchao
 */
@Data
public class  R<T> {
    /**
     * 服务器响应数据
     */
    private T data;

    /**
     * 状态码
     */
    private int status;

    /**
     * 状态描述
     */
    private String description;


    private R(CodeMsg codeMsg, T data) {
        this.data = data;
        this.status = codeMsg.getStatus();
        this.description = codeMsg.getDescription();
    }

    private R(CodeMsg codeMsg) {
        this.status = codeMsg.getStatus();
        this.description = codeMsg.getDescription();
    }

    private R(String description) {
        this.status = CodeMsg.SERVER_ERROR.getStatus();
        this.description = description;
    }

    /**
     * 返回默认成功方法
     *
     * @return
     */
    public static String ok() {
        return JSONObject.toJSONString(new R(CodeMsg.OK), SerializerFeature.WriteMapNullValue);

    }

    /**
     * 带参数的成功方法
     *
     * @param data 需要返回的数据
     * @return
     */
    public static <T> String ok(T data) {
        return JSONObject.toJSONString(new R<T>(CodeMsg.OK, data), SerializerFeature.WriteMapNullValue);
    }


    /**
     * 返回带参数的错误方法
     *
     * @param codeMsg 错误信息
     * @return
     */
    public static String fail(CodeMsg codeMsg) {
        return JSONObject.toJSONString(new R(codeMsg), SerializerFeature.WriteMapNullValue);
    }

    /**
     * (可以用于处理系统异常)
     *
     * @deprecated 该方法不推荐请使用带枚举参数的方法
     */
    @Deprecated
    public static String fail(String description) {
        return JSONObject.toJSONString(new R(description), SerializerFeature.WriteMapNullValue);
    }

    /**
     * 返回默认的错误方法
     *
     * @return
     */
    public static String fail() {
        return JSONObject.toJSONString(new R(CodeMsg.SERVER_ERROR), SerializerFeature.WriteMapNullValue);
    }

}