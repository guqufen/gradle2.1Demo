package net.fnsco.web.controller.e789.bill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;
import net.fnsco.web.controller.e789.jo.BillJO;
import net.fnsco.web.controller.e789.vo.BillVO;

/**
 * @desc 账单信息业务控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:58:22
 */
@RestController
@RequestMapping(value = "/app2c/bill", method = RequestMethod.POST)
@Api(value = "/app2c/bill", tags = { "我的-钱包-账单信息相关功能接口" })
public class BillContrller extends BaseController {
	
	@Autowired
	private TradeWithdrawService tradeWithdrawService;
	
	/**
	 * queryBillList:(获取账单列表接口)
	 *
	 * @param  @param billJO
	 * @param  @return    设定文件
	 * @return ResultDTO<List<BillVO>>    DOM对象
	 * @author tangliang
	 * @date   2017年12月4日 下午6:04:22
	 */
	@RequestMapping(value = "/queryBillList")
    @ApiOperation(value = "我的-钱包-我的账单-获取账单列表接口")
    public ResultDTO<List<BillVO>> queryBillList(@RequestBody BillJO billJO) {
		
		if(null == billJO.getUserId()) {
			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
		}
		
		if(null == billJO.getPageNum()) {
			billJO.setPageNum(1);
		}
		
		if(null == billJO.getPageSize()) {
			billJO.setPageSize(20);
		}
		
		TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
		tradeWithdraw.setAppUserId(billJO.getUserId());
		tradeWithdraw.setStatus(3);
		ResultPageDTO<TradeWithdrawDO> datasResult = tradeWithdrawService.page(tradeWithdraw, billJO.getPageNum(), billJO.getPageSize());
		List<TradeWithdrawDO> datas =  datasResult.getList();
		Set<String> sets = Sets.newHashSet(); 
		for (TradeWithdrawDO tradeWithdrawDO : datas) {
			String createMonth = formatYYYYMMDate(tradeWithdrawDO.getCreateTime());
			if(Strings.isNullOrEmpty(createMonth)) {
				continue;
			}
			if(!sets.contains(createMonth)) {
				sets.add(createMonth);
				BillVO mouthBillVo = new BillVO();
				mouthBillVo.setBillDate(createMonth);
				
			}
			
		}
		List<BillVO> data = Lists.newArrayList();
        return success(null);
    }
	
	/**
	 * formatYYYYMMDate:(获取这种格式的日期字符串yyyy-MM)
	 *
	 * @param  @param date
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2017年12月7日 下午1:59:41
	 */
	private String formatYYYYMMDate(Date date) {
		if(null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
}
