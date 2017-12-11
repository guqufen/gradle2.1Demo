package net.fnsco.web.controller.open;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.common.comm.ServiceClient.Request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.config.ConfigService;
import net.fnsco.car.service.config.entity.ConfigDO;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.finance.entity.OrderFinanceDO;
import net.fnsco.car.service.safe.OrderSafeService;
import net.fnsco.car.service.safe.entity.OrderSafeDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.jo.EstiPremiumsJO;
import net.fnsco.web.controller.jo.SaveSafeJO;
import net.fnsco.web.controller.vo.EstiPremiumsVO;
import net.fnsco.web.controller.vo.QueryInsuVO;

/**
 * 
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 上午11:43:36
 */
@RestController
@RequestMapping(value = "/web/car", method = RequestMethod.POST)
@Api(value = "/web/car", tags = { "业务申请-保险申请接口" })
public class InsuranceController extends BaseController {
	@Autowired
	private OrderSafeService orderSafeService;
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/saveSafe")
	@ApiOperation(value = "保险申请-添加申请")
	private ResultDTO<Object> saveSafe(SaveSafeJO saveSafeJO) {
		saveSafeJO.getCode();
		//appUserService.getValidateCode(appUserDTO);
		
		CustomerDO customerDO =  new CustomerDO();
		customerDO.setName(saveSafeJO.getName());
		customerDO.setMobile(saveSafeJO.getMobile());
		OrderSafeDO orderSafe = new OrderSafeDO();
		orderSafe.setCityId(saveSafeJO.getCityId());
		orderSafe.setCarOriginalPrice(saveSafeJO.getCarOriginalPrice());
		Integer id = configService.queryIdByName(saveSafeJO.getName());
		if(id==null) {
			return ResultDTO.fail("没有找到相应的保险公司");
		}
		orderSafe.setInsuCompanyId(id);
		orderSafe.setEstiPremiums(saveSafeJO.getEstiPremiums());
		orderSafe.setSuggestCode(saveSafeJO.getSuggestCode());
		ResultDTO<Object> res = orderSafeService.saveSafe(customerDO,orderSafe);
        return res;
    }
	
	@RequestMapping(value = "/queryInsu")
	@ApiOperation(value = "保险申请-保险公司")
	private ResultDTO<QueryInsuVO> queryInsu() {
		QueryInsuVO queryInsuVO = new QueryInsuVO();
		List<ConfigDO> res = configService.queryAll();
		List<String> insuList = new ArrayList<String>();
		for(ConfigDO cf : res) {
			insuList.add(cf.getName());
		}
		queryInsuVO.setInsuList(insuList);
		return ResultDTO.success(queryInsuVO);
	}
	
	@RequestMapping(value = "/estiPremiums")
	@ApiOperation(value = "保险申请-估算保费")
	private ResultDTO<EstiPremiumsVO> estiPremiums(EstiPremiumsJO estiPremiumsJO) {
		EstiPremiumsVO estiPremiums = new EstiPremiumsVO();
		estiPremiums.setEstiPremiums(estiPremiumsJO.getCarOriginalPrice().divide(new BigDecimal(10)));
        return ResultDTO.success(estiPremiums);
    }
	
}
