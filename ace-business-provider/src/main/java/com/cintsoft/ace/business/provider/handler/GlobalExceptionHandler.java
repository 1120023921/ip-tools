package com.cintsoft.ace.business.provider.handler;

import com.cintsoft.common.exception.BusinessException;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.common.web.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 胡昊
 * Description: 统一异常拦截器
 * Date: 2019/3/10
 * Time: 20:33
 * Create: DoubleH
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultBean<Object> businessExceptionHandler(BusinessException e) {
        log.error(e.getMsg(), e);
        return ResultBean.restResult(e.getData(), e.getCode(), e.getMsg());
    }

    @ExceptionHandler(ParameterValidateException.class)
    @ResponseBody
    public ResultBean<Object> parameterValidateExceptionHandler(ParameterValidateException e) {
        return ResultBean.restResult(null, e.getCode(), e.getMsg());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResultBean<Object> authenticationExceptionExceptionHandler(AuthenticationException e) {
        log.error(e.getMessage(), e);
        return ResultBean.restResult("UNAUTHORIZED", 401, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResultBean<Object> accessDeniedExceptionHandler(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return ResultBean.restResult("FORBIDDEN", 403, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean<Object> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResultBean.restResult("error", 500, e.getMessage());
    }
}
