package net.fnsco.core.base;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import net.fnsco.core.constants.CoreConstants;

@SuppressWarnings("unchecked")
public class ResultDTO<T extends Object> extends DTO {
	private static final long serialVersionUID = -7387542509934814087L;
	private boolean success;

	private String code;
	private String message;
	private T data;

	public ResultDTO() {
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
		return success(null);
	}

	public static ResultDTO success(String data) {
		Map map = Maps.newHashMap();
		map.put("result", data);
		return success(map);
	}

	public static ResultDTO successForSubmit() {
		return successForSubmit(null);
	}

	public static ResultDTO success(Object data) {
		ResultDTO result = new ResultDTO(true, data, CoreConstants.OK,
				CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.OK));
		return result;
	}

	public static ResultDTO success(Object data,String messageCode) {
    	String message =  CoreConstants.ERROR_MESSGE_MAP.get(messageCode);
    	if(Strings.isNullOrEmpty(message)){
    		message = messageCode;
    		messageCode = CoreConstants.OK;
    	}
        ResultDTO result = new ResultDTO(true, data, messageCode,message);
        return result;
    }

	public static ResultDTO successForSave(Object data) {
		ResultDTO result = new ResultDTO(true, data, CoreConstants.WEB_SAVE_OK,
				CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));
		return result;
	}

	public static ResultDTO successForSubmit(Object data) {
		ResultDTO result = new ResultDTO(true, data, CoreConstants.WEB_SUBMIT_OK,
				CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SUBMIT_OK));
		return result;
	}

	public static ResultDTO successForMessage(Object data) {
		ResultDTO result = new ResultDTO(true, data, CoreConstants.ID_CARD_OK,
				CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.ID_CARD_OK));
		return result;
	}

	public static ResultDTO fail(Object data,String messageCode) {
		String message =  CoreConstants.ERROR_MESSGE_MAP.get(messageCode);
    	if(Strings.isNullOrEmpty(message)){
    		message = messageCode;
    	}
		ResultDTO result = new ResultDTO(false, data,messageCode ,	message);
		return result;
	}
	public static ResultDTO fail(Object data) {
		ResultDTO result = new ResultDTO(false, data, CoreConstants.E_BANK_VALIDATE_FAIL,CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.E_BANK_VALIDATE_FAIL));
		return result;
	}

	public static ResultDTO fail(String code) {
		String msg = CoreConstants.ERROR_MESSGE_MAP.get(code);
		if (Strings.isNullOrEmpty(msg)) {
			msg = code;
			code = CoreConstants.E_COMM_BUSSICSS;
		}
		ResultDTO result = new ResultDTO(false, null, code, msg);
		return result;
	}

	public static ResultDTO fail(String code, String msg) {
		ResultDTO result = new ResultDTO(false, null, code, msg);
		return result;
	}

	public static ResultDTO failForMessage(String message) {
		ResultDTO result = new ResultDTO(false, message, null, message);
		return result;
	}

	public static ResultDTO fail() {
		return fail(CoreConstants.E_COMM_BUSSICSS);
	}

	public static ResultDTO failForSave() {
		return fail(CoreConstants.E_COMM_BUSSICSS);
	}

	public static ResultDTO failForUpdate() {
		return fail(CoreConstants.E_COMM_BUSSICSS);
	}

	/**
	 * success
	 *
	 * @return the success
	 * @since CodingExample Ver 1.0
	 */

	public boolean isSuccess() {
		return success;
	}

	/**
	 * data
	 *
	 * @return the data
	 * @since CodingExample Ver 1.0
	 */

	public T getData() {
		return data;
	}

	/**
	 * code
	 *
	 * @return the code
	 * @since CodingExample Ver 1.0
	 */

	public String getCode() {
		return code;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * message
	 *
	 * @return the message
	 * @since CodingExample Ver 1.0
	 */

	public String getMessage() {
		return message;
	}

}