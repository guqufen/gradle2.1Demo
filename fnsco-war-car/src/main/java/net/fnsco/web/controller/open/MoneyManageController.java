package net.fnsco.web.controller.open;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.comm.CarServiceConstant;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.finance.OrderFinanceService;
import net.fnsco.car.service.finance.entity.OrderFinanceDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.core.utils.dto.MessageValidateDTO;
import net.fnsco.web.controller.jo.SaveFinanceJO;

/**
 * 
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 上午11:43:36
 */
@RestController
@RequestMapping(value = "/h5/manage", method = RequestMethod.POST)
@Api(value = "/h5/manage", tags = { "业务申请-理财申请接口" })
public class MoneyManageController extends BaseController {
	@Autowired
	private OrderFinanceService orderFinanceService;
	@RequestMapping(value = "/saveFinance")
	@ApiOperation(value = "理财申请-添加申请")
	private ResultDTO<Object> saveFinance(@RequestBody SaveFinanceJO saveFinanceJO) {
		String code = saveFinanceJO.getCode();
		String mobile = saveFinanceJO.getMobile();
		String type = saveFinanceJO.getType();
		if(StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)){
			return ResultDTO.fail(CarServiceConstant.anErrorMap.get("0001"));
		}
		//获取session中验证码信息
		MessageValidateDTO mDTO = (MessageValidateDTO) session.getAttribute(mobile);
		if(mDTO == null){
			return ResultDTO.fail(CarServiceConstant.anErrorMap.get("2021"));
		}
		//校验验证码是否正确
		MessageUtils utils = new MessageUtils();
		ResultDTO<Object> rt = utils.validateCode2(code, mobile,mDTO);
		if(!rt.isSuccess()){
			return ResultDTO.fail(rt.getMessage());
		}
		CustomerDO customerDO =  new CustomerDO();
		customerDO.setName(saveFinanceJO.getName());
		customerDO.setMobile(saveFinanceJO.getMobile());
		OrderFinanceDO orderFinance = new OrderFinanceDO();
		orderFinance.setCityId(saveFinanceJO.getCityId());
		//orderFinance.setBuyType(saveFinanceJO.getBuyType());
		orderFinance.setSuggestCode(saveFinanceJO.getSuggestCode());
		ResultDTO<Object> res = orderFinanceService.saveFinance(customerDO,orderFinance);
        return res;
    }
}
