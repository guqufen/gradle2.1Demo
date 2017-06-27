package net.fnsco.freamwork.aop;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

public class OutWriterUtil {
    private static Logger logger = LoggerFactory.getLogger(OutWriterUtil.class);

    public static void outJson(HttpServletResponse response, String code, String message) {
        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            Result result = new Result();
            result.setCode(code);
            result.setMessage(message);
            result.setSuccess(false);
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            logger.error("与客户端通讯异常:" + e.getMessage(), e);
        }

    }
}