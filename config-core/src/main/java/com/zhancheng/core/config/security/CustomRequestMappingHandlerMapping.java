package com.zhancheng.core.config.security;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;


/**
 * @author Demon
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        Verify verify = AnnotationUtils.findAnnotation(handlerType, Verify.class);
        return createCondition(verify);
    }

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        Verify verify = AnnotationUtils.findAnnotation(method, Verify.class);
        return createCondition(verify);
    }

    private RequestCondition<ApiVersionCondition> createCondition(Verify verify) {
        return verify == null ? null : new ApiVersionCondition(verify.version());
    }


}
