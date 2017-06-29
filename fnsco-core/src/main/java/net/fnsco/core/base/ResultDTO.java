package net.fnsco.core.base;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import net.fnsco.core.constants.CoreConstants;

public class ResultDTO<T extends Object> extends DTO {
    private static final long serialVersionUID = -7387542509934814087L;
    private boolean           success;
    private String            code;
    private String            message;
    private T                 data;

    public ResultDTO() {
        this.data = (T) new Object();
        this.code = "";
        this.message = "";
    }

    public ResultDTO(boolean success, Object data, String code, String message) {
        this.data = (T) data;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static ResultDTO success() {
        return success(new Object());
    }

    public static ResultDTO success(String data) {
        Map map = Maps.newHashMap();
        map.put("result", data);
        return success(map);
    }

    public static ResultDTO success(Object data) {
        ResultDTO result = new ResultDTO(true, data, CoreConstants.OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.OK));
        return result;
    }

    public static ResultDTO fail(String code) {
        ResultDTO result = new ResultDTO(false, new Object(), code, CoreConstants.ERROR_MESSGE_MAP.get(code));
        return result;
    }

    public static ResultDTO fail() {
        return fail(CoreConstants.E_COMM_BUSSICSS);
    }

    /**
     * success
     *
     * @return  the success
     * @since   CodingExample Ver 1.0
    */
    
    public boolean isSuccess() {
        return success;
    }

  

    /**
     * data
     *
     * @return  the data
     * @since   CodingExample Ver 1.0
    */
    
    public T getData() {
        return data;
    }

     
    
    
}