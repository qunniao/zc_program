package com.zhancheng.core.constant;

/**
 * 返回状态的枚举
 *
 * @author Demon
 */
public enum CodeMsg {
    /**
     * 成功(所有正常状态的返回)
     */
    OK("SUCCESS", 0),

    /**
     * 服务端异常(不想描述错误信息的返回)
     */
    SERVER_ERROR("Server error", 444),

    /**
     * token为空 Token is empty
     */
    TOKEN_IS_NULL("token为空", 4000),

    /**
     * token 异常 Token abnormal
     */
    TOKENG_ABNORMAL("token 异常", 4001),

    /**
     * 权限不足可能没有登录 Permission error
     */
    PERMISSION_ERROR("权限不足可能没有登录", 4002),


    /**
     * 密码或者账号错误 Incorrect password or account number
     */
    PASSWORD_ERROR("密码或者账号错误", 4003),


    /**
     * 用户被禁用 User disabled!!!
     */
    USER_DISABLED("用户被禁用!!!", 4004),

    /**
     * 阿里云服务器请求失败 Aliyun request failed
     */
    ALIYUN_ERROR("阿里云服务器请求失败", 4030),


    /**
     * 微信服务器请求失败    WeChat request failed
     */
    WECHAT_ERROR("微信服务器请求失败", 4050),

    /**
     * 微信code无效 WeChat Code failure
     */
    WECHAT_CODE_ERROR("微信code无效", 4051),


    /**
     * 微信获得用户信息失败   Failed to retrieve user information
     */
    WECHAT_GET_USER_ERROR("微信获得用户信息失败", 4052),


    /**
     * 手机号为空    The phone number is empty
     */
    PHONE_NUMBER_EMPTY("手机号为空", 4060),
    /**
     * 验证码错误    Verification code error
     */
    VERIFICATION_CODE_ERROR("验证码错误", 4061),
    /**
     * 参数为空 Parameter is null
     */
    PARAMETER_NULL("参数为空", 4062),

    /**
     * 添加的信息已经存在    Already existed
     */
    ALREADY_EXISTED("添加的信息已经存在", 4063),
    /**
     * 参数错误 Parameter is error
     */
    PARAMETER_ERROR("参数错误", 4064),

    /**
     * 查询为空 The query is empty
     */
    QUERY_EMPTY("查询为空", 4101),

    /**
     * 商品低于1或者超过库存  The quantity of goods exceeds the limit
     */
    GOODS_NUM_ERROR("商品低于1或者超过库存", 4102),

    /**
     * 优惠券过期    Coupon expired
     */
    COUPON_EXPIRED("优惠券过期", 4103),

    /**
     * 优惠券不满足条件 Fail to meet the conditionx
     */
    COUPON_FAIL("优惠券不满足条件", 4104),

    /**
     * 图片格式不匹配
     */
    IMAGE_MISMATCHING("图片格式不匹配", 4105),
    RUNTIME_EXCEPTION("运行时异常", 4106),
    NULL_POINTER_EXCEPTION("空指针异常", 4107),
    CLASS_CAST_EXCEPTION("类型强制转换异常", 4108),
    IO_EXCEPTION("IO异常", 4109),
    CLASS_NOT_FOUND_EXCEPTION("指定的类不存在", 4110),
    ARITHMETIC_EXCEPTION("算术异常", 4111),
    ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION("数组下标越界", 4112),
    INDEX_OUT_OF_BOUNDS_EXCEPTION("索引越界", 4113),
    NEGATIVE_ARRAY_SIZE_EXCEPTION("数组负下标", 4114),
    ILLEGAL_ARGUMENT_EXCEPTION("方法的参数错误", 4115),
    ILLEGAL_ACCESS_EXCEPTION("没有访问权限", 4116),
    SECURITY_EXCEPTION("违背安全原则", 4117),
    EOF_EXCEPTION("文件已结束异常", 4118),
    FILE_NOT_FOUND_EXCEPTION("文件未找到", 4119),
    NUMBER_FORMAT_EXCEPTION("数字格式化异常", 4120),
    SQL_EXCEPTION("操作数据库异常", 4121),
    NO_SUCH_METHOD_EXCEPTION("方法未找到", 4122),
    ARRAY_STORE_EXCEPTION("数组存储异常", 4123),
    CLONE_NOT_SUPPORTED_EXCEPTION("不支持克隆", 4124),
    ENUM_CONSTANT_NOT_PRESENT_EXCEPTION("枚举常量不存在", 4125),
    INSTANTIATION_EXCEPTION("实例化异常", 4126),
    TYPE_NOT_PRESENT_EXCEPTION("类型不存在", 4127),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("缺少Servlet请求参数", 4128),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("Http消息不可读", 4129),
    TYPE_MISMATCH_EXCEPTION("类型不匹配", 4130),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("Http请求方法错误", 4131),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION("Http媒体类型不可接受", 4132),
    CONVERSION_NOT_SUPPORTED_EXCEPTION("不支持转换", 4133),
    HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION("Http消息不可写", 4134),
    PARAM_VERIFY_EXCEPTION("参数校验异常", 4134),
    USER_CARD_EXISTS("用户名片已存在", 4135),
    NOT_EXISTED("要修改的数据不存在", 4136),
    SKU_NOT_EXISTED("sku不存在", 4137),
    COUPON_NOT_EXISTED("优惠券不存在", 4138),
    PRODUCT_NOT_EXISTED("产品不存在", 4139),
    NEWS_NOT_EXISTED("新闻资讯不存在", 4140),
    USER_CARD_NOT_EXISTS("用户名片已存在", 4141)

    ;

    /**
     * 构造方法
     *
     * @param description 描述状态的详细信息
     * @param status      状态代码
     */
    CodeMsg(String description, int status) {
        this.description = description;
        this.status = status;
    }

    private Integer status;

    private String description;

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
