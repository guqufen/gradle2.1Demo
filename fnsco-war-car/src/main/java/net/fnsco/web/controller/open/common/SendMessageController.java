package net.fnsco.web.controller.open.common;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;

@RestController
@RequestMapping(value = "/h5", method = RequestMethod.POST)
@Api(value = "/h5", tags = { "发送验证码" })
public class SendMessageController {
	
	
	@RequestMapping(value = "/sendMessage")
	@ApiOperation(value = "发送验证码")
	public ResultDTO sendMessage(@RequestBody String mobile){
		String deviceId = "fns";
		MessageUtils mUtils = new MessageUtils();
		ResultDTO result = mUtils.sendValidateCode(deviceId, mobile);
		return result;
	}

	
}
