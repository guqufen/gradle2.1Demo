package net.fnsco.web.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.finance.OrderFinanceService;
import net.fnsco.car.service.finance.entity.OrderFinanceDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.jo.SaveFinanceJO;

/**
 * 
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 上午11:43:36
 */
@RestController
@RequestMapping(value = "/web/car", method = RequestMethod.POST)
@Api(value = "/web/car", tags = { "业务申请-理财申请接口" })
public class MoneyManageController extends BaseController {
	@Autowired
	private OrderFinanceService orderFinanceService;
	@RequestMapping(value = "/saveFinance")
	@ApiOperation(value = "理财申请-添加申请")
	private ResultDTO<Object> saveFinance(@RequestBody SaveFinanceJO saveFinanceJO) {
		saveFinanceJO.getCode();
		//appUserService.getValidateCode(appUserDTO);
		CustomerDO customerDO =  new CustomerDO();
		customerDO.setName(saveFinanceJO.getName());
		customerDO.setMobile(saveFinanceJO.getMobile());
		OrderFinanceDO orderFinance = new OrderFinanceDO();
		orderFinance.setCityId(saveFinanceJO.getCityId());
		orderFinance.setBuyType(saveFinanceJO.getBuyType());
		orderFinance.setSuggestCode(saveFinanceJO.getSuggestCode());
		ResultDTO<Object> res = orderFinanceService.saveFinance(customerDO,orderFinance);
        return res;
    }
}
