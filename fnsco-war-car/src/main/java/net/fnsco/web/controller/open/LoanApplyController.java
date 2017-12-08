package net.fnsco.web.controller.open;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.loan.OrderLoanService;
import net.fnsco.car.service.loan.entity.OrderLoanDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.jo.LoanJO;
import net.fnsco.web.controller.vo.LoanVO;
import net.fnsco.web.controller.vo.QueryCityVO;

@RestController
@RequestMapping(value = "/api/loan", method = RequestMethod.POST)
@Api(value = "/api/loan", tags = { "业务申请-贷款申请" })
public class LoanApplyController extends BaseController {

	@Autowired
	private OrderLoanService orderLoanService;

	@RequestMapping(value = "/add")
	@ApiOperation(value = "贷款申请-添加申请")
	public ResultDTO<LoanVO> addJO(@RequestBody LoanJO jo) {
		CustomerDO customer = new CustomerDO();
		customer.setName(jo.getName());
		customer.setMobile(jo.getMobile());

		OrderLoanDO orderLoan = new OrderLoanDO();
		orderLoan.setCityId(jo.getCityId());
		orderLoan.setAmount(jo.getAmount());
		orderLoan.setSuggestCode(jo.getSuggestCode());
		ResultDTO<Object> result = orderLoanService.addJo(orderLoan, customer);
		if (result.isSuccess()) {
			return ResultDTO.success("提交成功");
		} else {
			return ResultDTO.fail("提交失败");
		}
	}

}
