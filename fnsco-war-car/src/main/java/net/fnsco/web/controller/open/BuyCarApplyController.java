package net.fnsco.web.controller.open;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.comm.CarConstant;
import net.fnsco.car.comm.CarServiceConstant;
import net.fnsco.car.service.agent.AgentService;
import net.fnsco.car.service.agent.entity.AgentDO;
import net.fnsco.car.service.buy.OrderBuyService;
import net.fnsco.car.service.buy.entity.OrderBuyDO;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.core.utils.dto.MessageValidateDTO;
import net.fnsco.web.controller.jo.BuyCarJO;
import net.fnsco.web.controller.vo.BuyCarVO;
/**
 * 
 * @deprecated 
 * @author   binghui.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月12日 下午6:15:10
 *
 */
@RestController
@RequestMapping(value = "/h5/buyCar", method = RequestMethod.POST)
@Api(value = "/h5/buyCar", tags = { "业务申请-买车申请" })
public class BuyCarApplyController extends BaseController {

	@Autowired
	private OrderBuyService orderBuyService;
	@Autowired
	private DicCityService dicCityService;
	@Autowired
	private AgentService agentService;

	@RequestMapping(value = "/add")
	@ApiOperation(value = "买车申请-添加申请")
	public ResultDTO<BuyCarVO> addJO(@RequestBody BuyCarJO jo) {
		String code = jo.getVerCode();
		String mobile = jo.getMobile();
		String suggestCode = jo.getSuggestCode();
		if(Strings.isNullOrEmpty(code)||Strings.isNullOrEmpty(code)||Strings.isNullOrEmpty(code)){
			return ResultDTO.fail(CarConstant.E_PARAMETER_NOT_NULL);
		}
		AgentDO agent = agentService.doQueryByCode(suggestCode);
		if(agent == null){
			return ResultDTO.fail("推荐码不存在");
			
		}
		String type =CarServiceConstant.ApplyType.getNameByType("01");
		//获取session中验证码信息
		MessageValidateDTO mDTO = (MessageValidateDTO) session.getAttribute(type+mobile);
		//校验验证码是否正确
		MessageUtils utils = new MessageUtils();
		ResultDTO<Object> rt = utils.validateCode2(code, mobile,mDTO);
		if(!rt.isSuccess()){
			return ResultDTO.fail(rt.getMessage());
		}
		CustomerDO customer = new CustomerDO();
		customer.setName(jo.getName());
		customer.setMobile(jo.getMobile());
		OrderBuyDO orderBuy = new OrderBuyDO();
		orderBuy.setCityId(jo.getCityId());
		orderBuy.setCarTypeId(jo.getCarTypeId());// 汽车品牌
		orderBuy.setCarSubTypeId(Integer.valueOf(jo.getCarSubTypeId()));
		orderBuy.setBuyType(jo.getBuyType());
		orderBuy.setSuggestCode(jo.getSuggestCode());

		ResultDTO<Object> result = orderBuyService.addJo(orderBuy, customer);
		if (!result.isSuccess()) {
		
			return ResultDTO.fail();
		}
		return ResultDTO.success("提交成功");
	}

}
