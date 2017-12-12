package net.fnsco.web.controller.open.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.core.utils.dto.MessageValidateDTO;

@RestController
@RequestMapping(value = "/h5")
@Api(value = "/h5", tags = { "发送验证码" })
public class SendMessageController {
	
	
	@RequestMapping(value = "/sendMessage",method = RequestMethod.GET)
	@ApiOperation(value = "发送验证码")
	public ResultDTO sendMessage(final HttpServletRequest req ){
		String mobile = req.getParameter("mobile");
		String type = req.getParameter("type");
		String deviceId = "fns";
		MessageUtils mUtils = new MessageUtils();
		//获取六位验证码
		final HttpSession httpSession=req.getSession();  
		MessageValidateDTO mvDTO = mUtils.getValidateCode(deviceId,mobile,type);
	    httpSession.setAttribute(deviceId+mobile,mvDTO);  //验证码放入session
	    String code = mvDTO.getCode();
		//发送验证码
		ResultDTO result = mUtils.sendValidateCode(mobile,code);
		return result;
	}

	
}
