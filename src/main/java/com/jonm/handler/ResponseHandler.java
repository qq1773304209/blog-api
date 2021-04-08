package com.jonm.handler;

import com.jonm.respond.Result;
import com.jonm.util.JacksonUtils;
import com.jonm.respond.ErrorResult;
import com.jonm.respond.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/*
*   ResponseBodyAdviced:
*          作用： 拦截controller返回值，一般做response同一格式，加密，签名
*
* */
//@ControllerAdvice(basePackages = "com.jonm")
@Slf4j
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
    * @Description: 拦截controller返回值
    * @Param: [o, methodParameter, mediaType, aClass, serverHttpRequest, serverHttpResponse]
    * @return: java.lang.Object
    * @Author: Jonm
    * @Date: 2021/3/27
    */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("调用ResponseBodyAdvice。。。。。。");
        if(o instanceof ErrorResult){
            ErrorResult errorResult = (ErrorResult) o;
            return ResultUtil.except(errorResult.getCode(),errorResult.getMsg());
        }else if (o instanceof String){
            return JacksonUtils.object2Json(ResultUtil.success(o));
        }
        else if (o instanceof Result){
            return o;
        }
        return ResultUtil.success(o);
    }

}
