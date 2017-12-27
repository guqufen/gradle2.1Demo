package net.fnsco.web.controller.index;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.config.ConfigService;
import net.fnsco.car.service.config.entity.ConfigDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.vo.IndexVO;

@RestController
@RequestMapping(value = "/h5/index")
@Api(value = "/h5/index", tags = { "首页显示接口" })
public class IndexCarController extends BaseController {
	
	@Autowired
	private ConfigService configService;
	@RequestMapping(value = "/doRequest" , method = RequestMethod.GET)
	@ApiOperation(value = "首页请求金额销售额")
	private ResultDTO<IndexVO> doRequest() {
		IndexVO index = new IndexVO();
		List<ConfigDO>  configList = configService.queryIndex();
		Integer num = null ;
		String amtStr = null;
		Date date = null;
		for(ConfigDO config : configList) {
			if(config.getOrderNo()==1) {
				num = Integer.valueOf(config.getValue()).intValue(); 
				date = config.getModifyTime();
			}else {
				amtStr = config.getValue();
				date = config.getModifyTime();
			}
		}
		BigDecimal amt =new BigDecimal(amtStr);
		if (DateUtils.isSameDay(new Date(), date)){
		     //如果时间和第一天处于同一天
			amt = amt.divide(new BigDecimal(10000000), 2, BigDecimal.ROUND_HALF_UP);
			index.setAmount(amt);
			index.setNumber(num);
			return ResultDTO.success(index);
		}
		Integer number = new Random().nextInt(6)+5;
		num= num + number;
		Integer numberAmt =new Random().nextInt(10000)+10000;
		numberAmt = numberAmt * 100;
		Integer numKeep = Integer.valueOf(amtStr).intValue();
		numberAmt = numberAmt + numKeep;
		amt = new BigDecimal(numberAmt);
		amt = amt.divide(new BigDecimal(10000000), 2, BigDecimal.ROUND_HALF_UP);
		String numAmt = numberAmt.toString();
		index.setAmount(amt);
		index.setNumber(num);
		ConfigDO config1 = new ConfigDO();
		config1.setValue(String.valueOf(num));;
		config1.setOrderNo(1);
		config1.setModifyTime(new Date());
		configService.doUpdate(config1, getUserId());
		ConfigDO config2 = new ConfigDO();
		config2.setValue(String.valueOf(amt));;
		config2.setOrderNo(1);
		config2.setModifyTime(new Date());
		configService.doUpdate(config2, getUserId());
		return ResultDTO.success(index);
	}
	
}
