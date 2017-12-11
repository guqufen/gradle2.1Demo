package net.fnsco.web.controller.open.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;

@RestController
@RequestMapping(value = "/api/send", method = RequestMethod.POST)
@Api(value = "/api/send", tags = { "发送验证码" })
public class SendMessageController {
	
	public ResultDTO sendMessage(String mobile){
		String deviceId = "fns";
		MessageUtils rt = new MessageUtils();
		ResultDTO result = rt.sendValidateCode(deviceId, mobile);
		return result;
	}

	
}
