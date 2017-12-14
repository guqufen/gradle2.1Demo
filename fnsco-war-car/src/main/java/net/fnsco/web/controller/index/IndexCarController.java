package net.fnsco.web.controller.index;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.fnsco.car.service.config.ConfigService;
import net.fnsco.car.service.config.entity.ConfigDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.vo.IndexVO;

@RestController
@RequestMapping(value = "/h5/index", method = RequestMethod.POST)
@Api(value = "/h5/index", tags = { "首页显示接口" })
public class IndexCarController extends BaseController {
	
	@Autowired
	private ConfigService configService;
	
	private ResultDTO<IndexVO> index() {
		IndexVO index = new IndexVO();
		ConfigDO  config = configService.queryIndex();
		Integer num = config.getOrderNo();
		String keep = config.getKeep1();
		BigDecimal amt =new BigDecimal(keep);
		Date date = config.getModifyTime();
		if (DateUtils.isSameDay(new Date(), date)){
		     //如果时间和第一天处于同一天
			amt = amt.divide(new BigDecimal(10000), 2);
			index.setAmount(amt);
			index.setNumber(num);
			return ResultDTO.success(index);
		}
		Integer number = new Random().nextInt(6)+5;
		num= num + number;
		Integer numberAmt =new Random().nextInt(5000)+5000;
		numberAmt = numberAmt * 100;
		index.setAmount(amt);
		index.setNumber(num);
		return ResultDTO.success(index);
	}
	
}
