package net.fnsco.freamwork.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.freamwork.aop.Result;
import net.fnsco.freamwork.comm.FrameworkConstant;

@ControllerAdvice
//如果返回的为json数据或其它对象，添加该注解  
@ResponseBody
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例  
    @ExceptionHandler(value = Exception.class)
    public Object MethodArgumentNotValidHandler(HttpServletRequest request, Exception exception) throws Exception {
        //按需重新封装需要返回的错误信息  
        logger.error("业务处理异常", exception);
        Result result = new Result();
        result.setCode(FrameworkConstant.E_SYSTEM_EXCEPTION);
        result.setMessage(FrameworkConstant.ERROR_MESSGE_MAP.get(FrameworkConstant.E_SYSTEM_EXCEPTION) + exception.getMessage());
        result.setSuccess(false);
        return result;
    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        //"没有权限，请联系管理员授权");
        Result result = new Result();
        result.setCode(FrameworkConstant.E_NOT_AUTHORIZED);
        result.setMessage(FrameworkConstant.ERROR_MESSGE_MAP.get(FrameworkConstant.E_NOT_AUTHORIZED));
        result.setSuccess(false);
        return result;
    }
}
