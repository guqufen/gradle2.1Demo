package net.fnsco.web.controller.e789.bill;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
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
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.dto.MonthWithdrawCountDTO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;
import net.fnsco.web.controller.e789.jo.BillJO;
import net.fnsco.web.controller.e789.vo.BillDayVO;
import net.fnsco.web.controller.e789.vo.BillTotalVO;
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
    public ResultDTO<BillTotalVO> queryBillList(@RequestBody BillJO billJO) {
		
		if(null == billJO.getUserId()) {
			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
		}
		
		if(null == billJO.getPageNum()) {
			billJO.setPageNum(1);
		}
		
		if(null == billJO.getPageSize()) {
			billJO.setPageSize(20);
		}
		
		BillTotalVO resultData = new BillTotalVO();
		resultData.setCurrentPageNum(billJO.getPageNum());
		resultData.setPageSize(billJO.getPageSize());
		
		
		TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
		tradeWithdraw.setAppUserId(billJO.getUserId());
		tradeWithdraw.setStatus(3);
		ResultPageDTO<TradeWithdrawDO> datasResult = tradeWithdrawService.page(tradeWithdraw, billJO.getPageNum(), billJO.getPageSize());
		List<TradeWithdrawDO> datas =  datasResult.getList();
		Set<String> sets = Sets.newHashSet(); 
		List<BillVO> data = Lists.newArrayList();
		for (TradeWithdrawDO tradeWithdrawDO : datas) {
			String createMonth = formatYYYYMMDate(tradeWithdrawDO.getCreateTime());
			if(Strings.isNullOrEmpty(createMonth)) {
				continue;
			}
			List<BillDayVO> billDetails = null;
			if(!sets.contains(createMonth)) {
				sets.add(createMonth);
				BillVO mouthBillVo = new BillVO();
				billDetails =  Lists.newArrayList();
				mouthBillVo.setBillDate(createMonth);
				mouthBillVo.setBillDetails(billDetails);
				data.add(mouthBillVo);
			}
			BillDayVO billDayVO = new BillDayVO();
			billDayVO.setAmount(formatRMBNumber(tradeWithdrawDO.getAmount()));
			if(null != tradeWithdrawDO.getTradeType() && 2==tradeWithdrawDO.getTradeType()) {
				billDayVO.setBillType("1");
				billDayVO.setBillTypeName("交易支出");
			}else {
				billDayVO.setBillType("0");
				billDayVO.setBillTypeName("交易收入");
			}
			billDayVO.setBillDayDate(DateUtils.dateFormatToStrs(tradeWithdrawDO.getCreateTime()));
			billDetails.add(billDayVO);
		}
		
		for (BillVO billVO : data) {
			List<MonthWithdrawCountDTO> countWithdraws = tradeWithdrawService.doQueryTotalAmountGroupByMouth(billJO.getUserId(), billVO.getBillDate(), 3);
			if(CollectionUtils.isNotEmpty(countWithdraws)) {
				for (MonthWithdrawCountDTO monthWithdrawCountDTO : countWithdraws) {
					if(monthWithdrawCountDTO.getTradeType()==2) {
						billVO.setTotalRevenue(formatRMBNumber(new BigDecimal(monthWithdrawCountDTO.getTotalAmount())));;
					}else {
						billVO.setTotalExpenditure(addRMBNumber(billVO.getTotalExpenditure(),formatRMBNumber(monthWithdrawCountDTO.getTotalAmount())));
					}
					if(billJO.getPageNum() == 1) {
						billVO.setDisplay(true);
					}else {
						TradeWithdrawDO tradeWithdrawDO = datas.get(0);
						ResultPageDTO<TradeWithdrawDO> datasResultBack = tradeWithdrawService.page(tradeWithdraw, billJO.getPageNum()-1, billJO.getPageSize());
						if(CollectionUtils.isNotEmpty(datasResultBack.getList())) {
							TradeWithdrawDO tradeWithdrawDOBack = datasResultBack.getList().get(datasResultBack.getList().size());
							String firstMouth = formatYYYYMMDate(tradeWithdrawDO.getCreateTime());
							String backMouth = formatYYYYMMDate(tradeWithdrawDOBack.getCreateTime());
							if(firstMouth.equals(backMouth)) {
								billVO.setDisplay(false);
							}else {
								billVO.setDisplay(true);
							}
						}
					}
				}
				if(Strings.isNullOrEmpty(billVO.getTotalExpenditure())) {
					billVO.setTotalExpenditure("0.00");
				}
				if(Strings.isNullOrEmpty(billVO.getTotalRevenue())) {
					billVO.setTotalRevenue("0.00");
				}
			}
		}
		
		Integer totalNum = data.size();
		resultData.setTotalPage(totalNum/billJO.getPageSize());
		if(totalNum%billJO.getPageSize() != 0) {
			resultData.setTotalPage(resultData.getTotalPage()+1);
		}
		
		resultData.setBillListData(data);
		
        return success(resultData);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		return sdf.format(date);
	}
	
	/**
	 * formatRMBNumber:(改为保留两位小数 RMB显示)
	 *
	 * @param  @param str
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2017年12月7日 下午6:09:41
	 */
	private String formatRMBNumber(BigDecimal str) {
		if(str == null) {
			return String.format("%.2f", 0);
		}else {
			return String.format("%.2f", str.divide(new BigDecimal(100)).doubleValue());
		}
	}
	
	private String formatRMBNumber(String str) {
		if(str == null) {
			return String.format("%.2f", 0);
		}else {
			return String.format("%.2f", new BigDecimal(str).divide(new BigDecimal(100)).doubleValue());
		}
	}
	private String addRMBNumber(String str,String str2) {
		if(Strings.isNullOrEmpty(str)) {
			str = "0";
		}
		if(Strings.isNullOrEmpty(str2)) {
			str2 = "0";
		}
		
		BigDecimal result  = new BigDecimal(str).add(new BigDecimal(str2));
		
		return String.format("%.2f", result.doubleValue());
	}
}
