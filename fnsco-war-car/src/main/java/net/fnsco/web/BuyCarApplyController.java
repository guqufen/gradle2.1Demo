package net.fnsco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.buy.OrderBuyService;
import net.fnsco.car.buy.entity.OrderBuyDO;
import net.fnsco.car.customer.CustomerService;
import net.fnsco.car.customer.entity.CustomerDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.jo.BuyCarJO;
import net.fnsco.web.vo.BuyCarVO;

@RestController
@RequestMapping(value = "/api/buy", method = RequestMethod.POST)
@Api(value = "/api/buy", tags = { "业务申请-买车申请" })
public class BuyCarApplyController extends BaseController{
	
	@Autowired
	private OrderBuyService orderBuyService;
	
	
	@RequestMapping(value = "/add")
	@ApiOperation(value = "买车申请-添加申请")
	public ResultDTO<BuyCarVO> addJO(@RequestBody BuyCarJO jo){
		CustomerDO customer = new CustomerDO();
		customer.setName(jo.getName());
		customer.setMobile(jo.getMobile());
		OrderBuyDO orderBuy = new OrderBuyDO();
		orderBuy.setCityId(jo.getCityId());
		orderBuy.setCarTypeId(jo.getCarTypeId());//汽车品牌
		orderBuy.setCarModel(jo.getCarModel());
		orderBuy.setBuyType(jo.getBuyType());
		orderBuy.setSuggestCode(jo.getSuggestCode());
		
		ResultDTO<Object> result = orderBuyService.addJo(orderBuy,customer);
		if(result.isSuccess()){
			return ResultDTO.success("提交成功");
		}else{
			return ResultDTO.fail("提交失败");
		}
	}
	
	//查询城市接口
}