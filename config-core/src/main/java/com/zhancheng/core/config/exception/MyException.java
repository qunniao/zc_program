package com.zhancheng.core.config.exception;

import com.zhancheng.core.constant.CodeMsg;
import lombok.Data;

/**
 * 自定义异常
 *
 * @author tangchao
 */
@Data
public class MyException extends RuntimeException {
    /**
     * 错误信息
     */
    private CodeMsg codeMsg;

    public MyException(CodeMsg codeMsg) {
        super();
        this.codeMsg = codeMsg;
    }


}
