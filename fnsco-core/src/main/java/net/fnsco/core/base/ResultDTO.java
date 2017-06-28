package net.fnsco.core.base;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.constants.CoreConstants;

public class ResultDTO<T extends Object> extends DTO {
    private static final long serialVersionUID = -7387542509934814087L;
    private static final int  ERROR_CODE       = 0;
    private boolean           success;
    private String            code;
    private String            message;
    private T                 data;
    public static ResultDTO success(Object data) {
        ResultDTO result = new ResultDTO();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
    public static ResultDTO error(String message) {
        ResultDTO result = new ResultDTO();
        result.setError(message);
        return result;
    }
    
    public ResultDTO<T> setError() {
        return setError("");
    }

    public ResultDTO<T> error() {
        return setError("");
    }

    public ResultDTO<T> setError(int resultCode) {
        return setError(String.valueOf(resultCode), CoreConstants.ERROR_MESSGE_MAP.get(resultCode));
    }

    public ResultDTO<T> setError(String resultMessage) {
        return setError(String.valueOf(ERROR_CODE), resultMessage);
    }

    public ResultDTO<T> setError(int resultCode, String resultMessage) {
        return setError(String.valueOf(resultCode), resultMessage);
    }

    public ResultDTO<T> setError(String resultCode, String resultMessage) {
        setSuccess(false);
        setCode(resultCode);
        setMessage(resultMessage);
        return this;
    }

    public ResultDTO<T> success() {
        return this.setSuccess(true, "");
    }


    public ResultDTO<T> setSuccess(String message) {
        return setSuccess(true, message);
    }

    public ResultDTO<T> setSuccess(boolean result, String message) {
        setSuccess(result);
        setMessage(message);
        return this;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}