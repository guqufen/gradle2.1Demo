package net.fnsco.web.controller.e789.phoneCharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.phoneCharge.PrepaidRefillService;
import net.fnsco.trading.service.phoneCharge.dto.FlowPackageCheckDTO;
import net.fnsco.trading.service.phoneCharge.dto.JuheDTO;
import net.fnsco.web.controller.e789.jo.FlowChargeJO;
import net.fnsco.web.controller.e789.jo.FlowPackageCheckJO;

@RestController
@RequestMapping(value="/app2c/e789/phoneCharge", method=RequestMethod.POST)
@Api(value="/app2c/e789/phoneCharge", tags={"账户页-手机充值"})
public class PrepaidRefillController extends BaseController {

	@Autowired
	private PrepaidRefillService prepaidRefillService;

	@RequestMapping("/prepaidRefill")
	@ApiOperation(value = "手机号码充值url")
	public ResultDTO prepaidRefill() {
		return null;
	}

	@RequestMapping("/flowPackageCheck")
	@ApiOperation(value = "检测手机号支持的流量资费套餐url")
	public ResultDTO<FlowPackageCheckDTO> flowPackageCheck(@RequestBody FlowPackageCheckJO flowPackageCheckJO) {
		return prepaidRefillService.flowPackageCheck(flowPackageCheckJO.getPhone());
	}

	@RequestMapping("/flowCharge")
	@ApiOperation(value = "手机流量充值url")
	public ResultDTO flowCharge(@RequestBody FlowChargeJO fl) {
		return null;
	}

	@RequestMapping("/demo")
	public ResultDTO demo(@RequestBody String str){
		JuheDTO juhe = JSONObject.parseObject(str,JuheDTO.class);
		JSONArray jsonArray = (JSONArray) juhe.getResult();
		String ss = jsonArray.toString();
		List<FlowPackageCheckDTO> fl = JSONObject.parseObject(ss,List.class);

		System.out.println(str);
		return null;
	}
}
