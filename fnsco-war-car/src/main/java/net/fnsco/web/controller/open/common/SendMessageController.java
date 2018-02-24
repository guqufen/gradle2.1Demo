package net.fnsco.web.controller.open.common;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.comm.CarServiceConstant;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.web.controller.dto.MessageValidateDTO;
import net.fnsco.web.controller.jo.SendMessageJO;

@RestController
@RequestMapping(value = "/h5")
@Api(value = "/h5", tags = { "发送验证码" })
public class SendMessageController extends BaseController{
	
	
	@RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
	@ApiOperation(value = "发送验证码")
	public ResultDTO sendMessage(@RequestBody SendMessageJO jo){
		String mobile = jo.getMobile();
		String type = jo.getType();
		type = CarServiceConstant.ApplyType.getNameByType(type);
		//获取六位验证码
		String code = MessageUtils.getValidateCode(jo.getMobile());
		MessageValidateDTO mvDTO = new MessageValidateDTO(code,System.currentTimeMillis());
		this.session.setAttribute(type+mobile,mvDTO);  //验证码放入session
		//发送验证码
		MessageUtils.sendValidateCode(mobile,code);
		logger.warn("将验证码key=["+type+mobile+"]"+"验证码value=["+mvDTO.getCode()+"]放入session");
		return ResultDTO.success();
	}

	
}
