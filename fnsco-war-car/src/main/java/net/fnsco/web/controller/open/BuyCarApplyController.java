package net.fnsco.web.controller.open;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.buy.OrderBuyService;
import net.fnsco.car.service.buy.entity.OrderBuyDO;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.web.controller.jo.BuyCarJO;
import net.fnsco.web.controller.vo.BuyCarVO;
/**
 * 
 * @author Administrator
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

	@RequestMapping(value = "/add")
	@ApiOperation(value = "买车申请-添加申请")
	public ResultDTO<BuyCarVO> addJO(@RequestBody BuyCarJO jo) {
		//校验验证码是否正确
		MessageUtils utils = new MessageUtils();
		ResultDTO<Object> rt = utils.validateCode("fns", jo.getVerCode(), jo.getMobile());
		if(!rt.isSuccess()){
			return ResultDTO.fail(rt.getMessage());
		}
		CustomerDO customer = new CustomerDO();
		customer.setName(jo.getName());
		customer.setMobile(jo.getMobile());
		OrderBuyDO orderBuy = new OrderBuyDO();
		orderBuy.setCityId(jo.getCityId());
		orderBuy.setCarTypeId(jo.getCarTypeId());// 汽车品牌
		orderBuy.setCarModel(jo.getCarModel());
		orderBuy.setBuyType(jo.getBuyType());
		orderBuy.setSuggestCode(jo.getSuggestCode());

		ResultDTO<Object> result = orderBuyService.addJo(orderBuy, customer);
		if (!result.isSuccess()) {
		
			return ResultDTO.fail("提交失败");
		}
		return ResultDTO.success("提交成功");
	}

}
