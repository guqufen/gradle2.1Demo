package net.fnsco.web.controller.open;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.web.controller.jo.EstiPremiumsJO;
import net.fnsco.web.controller.jo.SaveSafeJO;
import net.fnsco.web.controller.vo.EstiPremiumsVO;
import net.fnsco.web.controller.vo.InsuVO;
import net.fnsco.web.controller.vo.QueryInsuVO;

/**
 * 
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 上午11:43:36
 */
@RestController
@RequestMapping(value = "/h5/insu", method = RequestMethod.POST)
@Api(value = "/h5/insu", tags = { "业务申请-保险申请接口" })
public class InsuranceController extends BaseController {
	@Autowired
	private OrderSafeService orderSafeService;
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/saveSafe")
	@ApiOperation(value = "保险申请-添加申请")
	private ResultDTO<Object> saveSafe(@RequestBody SaveSafeJO saveSafeJO) {
		//校验验证码是否正确
		MessageUtils utils = new MessageUtils();
		ResultDTO<Object> rt = utils.validateCode("fns", saveSafeJO.getCode(), saveSafeJO.getMobile());
		if(!rt.isSuccess()){
			return ResultDTO.fail(rt.getMessage());
		}
		CustomerDO customerDO =  new CustomerDO();
		customerDO.setName(saveSafeJO.getName());
		customerDO.setMobile(saveSafeJO.getMobile());
		OrderSafeDO orderSafe = new OrderSafeDO();
		orderSafe.setCityId(saveSafeJO.getCityId());
		BigDecimal carPrice = saveSafeJO.getCarOriginalPrice().multiply(new BigDecimal(100));
		orderSafe.setCarOriginalPrice(carPrice);
		/*Integer id = configService.queryIdByName(saveSafeJO.getInsuCompanyName());
		if(id==null) {
			return ResultDTO.fail("没有找到相应的保险公司");
		}*/
		orderSafe.setInsuCompanyId(saveSafeJO.getInsuCompanyId());
		//orderSafe.setEstiPremiums(saveSafeJO.getEstiPremiums());
		orderSafe.setSuggestCode(saveSafeJO.getSuggestCode());
		ResultDTO<Object> res = orderSafeService.saveSafe(customerDO,orderSafe);
        return res;
    }
	
	@RequestMapping(value = "/queryInsu" , method = RequestMethod.GET)
	@ApiOperation(value = "保险申请-保险公司")
	private ResultDTO<QueryInsuVO> queryInsu() {
		QueryInsuVO queryInsuVO = new QueryInsuVO();
		List<ConfigDO> res = configService.queryAll();
		List<InsuVO> insuList = new ArrayList<InsuVO>();
		for(ConfigDO cf : res) {
			InsuVO insu = new InsuVO();
			insu.setText(cf.getName());
			insu.setValue(cf.getId());
			insuList.add(insu);
		}
		queryInsuVO.setInsuList(insuList);
		return ResultDTO.success(queryInsuVO);
	}
	
	@RequestMapping(value = "/estiPremiums")
	@ApiOperation(value = "保险申请-估算保费")
	private ResultDTO<EstiPremiumsVO> estiPremiums(@RequestBody EstiPremiumsJO estiPremiumsJO) {
		EstiPremiumsVO estiPremiums = new EstiPremiumsVO();
		estiPremiums.setEstiPremiums(estiPremiumsJO.getCarOriginalPrice().divide(new BigDecimal(10)));
        return ResultDTO.success(estiPremiums);
    }
	
}
