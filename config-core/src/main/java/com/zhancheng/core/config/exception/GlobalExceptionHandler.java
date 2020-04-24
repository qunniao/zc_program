package com.zhancheng.core.config.exception;

import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 边书恒
 * @Title: GlobalExceptionHandler
 * @ProjectName Exception_demo
 * @Description: TODO
 * @date 2019/7/1515:52
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时异常
     */
//    @ExceptionHandler(RuntimeException.class)
//    public String runtimeExceptionHandler(RuntimeException ex) {
//        return resultFormat(CodeMsg.RUNTIME_EXCEPTION, ex);
//    }

    /**
     * 空指针异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(CodeMsg.NULL_POINTER_EXCEPTION, ex);
    }

    /**
     * 类型强制转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(CodeMsg.CLASS_CAST_EXCEPTION, ex);
    }

    /**
     * IO异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException ex) {
        return resultFormat(CodeMsg.IO_EXCEPTION, ex);
    }

    /**
     * 指定的类不存在异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassNotFoundException.class)
    public String classNotFoundExceptionHandler(ClassNotFoundException ex) {

        return resultFormat(CodeMsg.CLASS_NOT_FOUND_EXCEPTION, ex);
    }

    /**
     * 算术异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    public String arithmeticExceptionHandler(ArithmeticException ex) {

        return resultFormat(CodeMsg.ARITHMETIC_EXCEPTION, ex);
    }

    /**
     * 数组下标越界异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public String arrayIndexOutOfBoundsExceptionHandler(ArrayIndexOutOfBoundsException ex) {
        return resultFormat(CodeMsg.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION, ex);
    }

    /**
     * 索引越界异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(CodeMsg.INDEX_OUT_OF_BOUNDS_EXCEPTION, ex);
    }

    /**
     * 数组负下标异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NegativeArraySizeException.class)
    public String negativeArraySizeExceptionHandler(NegativeArraySizeException ex) {
        return resultFormat(CodeMsg.NEGATIVE_ARRAY_SIZE_EXCEPTION, ex);
    }

    /**
     * 方法的参数错误异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return resultFormat(CodeMsg.ILLEGAL_ARGUMENT_EXCEPTION, ex);
    }

    /**
     * 没有访问权限异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalAccessException.class)
    public String illegalAccessExceptionHandler(IllegalAccessException ex) {
        return resultFormat(CodeMsg.ILLEGAL_ACCESS_EXCEPTION, ex);
    }

    /**
     * 违背安全原则异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(SecurityException.class)
    public String securityExceptionHandler(SecurityException ex) {
        return resultFormat(CodeMsg.SECURITY_EXCEPTION, ex);
    }

    /**
     * 文件已结束异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(EOFException.class)
    public String eOFExceptionHandler(EOFException ex) {

        return resultFormat(CodeMsg.EOF_EXCEPTION, ex);
    }

    /**
     * 文件未找到异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(FileNotFoundException.class)
    public String fileNotFoundExceptionHandler(FileNotFoundException ex) {

        return resultFormat(CodeMsg.FILE_NOT_FOUND_EXCEPTION, ex);
    }

    /**
     * 数字格式化异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatExceptionHandler(NumberFormatException ex) {
        return resultFormat(CodeMsg.NUMBER_FORMAT_EXCEPTION, ex);
    }

    /**
     * 操作数据库异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public String sqlExceptionHandler(SQLException ex) {
        return resultFormat(CodeMsg.SQL_EXCEPTION, ex);
    }

    /**
     * 方法未找到异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(CodeMsg.NO_SUCH_METHOD_EXCEPTION, ex);
    }

    /**
     * 数组存储异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ArrayStoreException.class)
    public String arrayStoreExceptionHandler(ArrayStoreException ex) {
        return resultFormat(CodeMsg.ARRAY_STORE_EXCEPTION, ex);
    }

    /**
     * 不支持克隆异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(CloneNotSupportedException.class)
    public String cloneNotSupportedExceptionHandler(CloneNotSupportedException ex) {
        return resultFormat(CodeMsg.CLONE_NOT_SUPPORTED_EXCEPTION, ex);
    }

    /**
     * 枚举常量不存在异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(EnumConstantNotPresentException.class)
    public String enumConstantNotPresentExceptionHandler(EnumConstantNotPresentException ex) {
        return resultFormat(CodeMsg.ENUM_CONSTANT_NOT_PRESENT_EXCEPTION, ex);
    }

    /**
     * 实例化异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InstantiationException.class)
    public String instantiationExceptionHandler(InstantiationException ex) {
        return resultFormat(CodeMsg.INSTANTIATION_EXCEPTION, ex);
    }

    /**
     * 类型不存在异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(TypeNotPresentException.class)
    public String typeNotPresentExceptionHandler(TypeNotPresentException ex) {
        return resultFormat(CodeMsg.TYPE_NOT_PRESENT_EXCEPTION, ex);
    }

    /**
     * Http消息不可读异常错误 400
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        return resultFormat(CodeMsg.HTTP_MESSAGE_NOT_READABLE_EXCEPTION, ex);
    }

    /**
     * 类型不匹配 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        return resultFormat(CodeMsg.TYPE_MISMATCH_EXCEPTION, ex);
    }

    /**
     * 缺少Servlet请求参数异常 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return resultFormat(CodeMsg.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, ex);
    }

    /**
     * Http请求方法不支持 405错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(CodeMsg.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, ex);
    }

    /**
     * Http媒体类型不可接受异常 406错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        return resultFormat(CodeMsg.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION, ex);
    }

    /**
     * 500错误 不支持转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class})
    public String server500(ConversionNotSupportedException ex) {
        return resultFormat(CodeMsg.CONVERSION_NOT_SUPPORTED_EXCEPTION, ex);
    }

    /**
     * 500错误 Http消息不可写异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotWritableException.class})
    public String server500(HttpMessageNotWritableException ex) {
        return resultFormat(CodeMsg.HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION, ex);
    }

    /**
     * 使用 @Validated 注解参数校验异常
     * @param ex
     * @return String
     */
    @ExceptionHandler({BindException.class})
    public String bindException(BindException ex){
        log.error("参数校验异常", ex);
        FieldError fieldError = ex.getFieldError();
        log.error("变量：{} {},当前值为 {}", fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue());
        return resultFormat(CodeMsg.PARAM_VERIFY_EXCEPTION, ex);
    }

    /**
     * 其他错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {

        log.info(ex.getMessage());
        logOut(ex);
        return R.fail(CodeMsg.SERVER_ERROR);
    }

    /**
     * 自定义业务处理异常
     * @param e
     * @return String
     */
    @ExceptionHandler({MyException.class})
    public String myException(MyException e) {
        CodeMsg codeMsg = e.getCodeMsg();
        log.error(codeMsg.getDescription());
        log.error(codeMsg.getStatus().toString());
        logOut(e);
        return R.fail(codeMsg);
    }

    private String resultFormat(CodeMsg codeMsg, Exception ex) {

        log.error(ex.getMessage());
        log.error(codeMsg.getDescription(), codeMsg.getStatus());
        logOut(ex);
        return R.fail(codeMsg);
    }

    private void logOut(Exception ex){

        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        log.error("抛出异常的文件路径：{}", className);
        log.error("抛出异常的类名：{}", fileName);
        log.error("抛出异常的方法：{}", methodName);
        log.error("抛出异常的行数：{}", lineNumber);
        ex.printStackTrace();
    }
}
