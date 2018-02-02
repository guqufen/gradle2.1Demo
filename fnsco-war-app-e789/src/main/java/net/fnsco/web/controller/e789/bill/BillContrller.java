package net.fnsco.web.controller.e789.bill;

import java.math.BigDecimal;
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
import net.fnsco.core.utils.NumberUtil;
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
		
		BillTotalVO resultData = new BillTotalVO();
		resultData.setCurrentPageNum(billJO.getPageNum());
		resultData.setPageSize(billJO.getPageSize());
		
		TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
		tradeWithdraw.setAppUserId(billJO.getUserId());
		tradeWithdraw.setAppShowList(true);
		ResultPageDTO<TradeWithdrawDO> datasResult = tradeWithdrawService.page(tradeWithdraw, billJO.getPageNum(), billJO.getPageSize());
		List<TradeWithdrawDO> datas =  datasResult.getList();
		Set<String> sets = Sets.newHashSet(); //存放月数据
		List<BillVO> data = Lists.newArrayList();
		List<BillDayVO> billDetails = null;//存放每日数据
		
		for (TradeWithdrawDO tradeWithdrawDO : datas) {
			String createMonth = NumberUtil.formatYYYYMMDate(tradeWithdrawDO.getCreateTime());//获取月份数据
			
			if(!sets.contains(createMonth)) {//如果已经存在月份，则下继续执行，如果不存在则新增加月份
				sets.add(createMonth);
				BillVO mouthBillVo = new BillVO();
				billDetails =  Lists.newArrayList();
				mouthBillVo.setBillDate(createMonth);
				mouthBillVo.setBillDetails(billDetails);
				data.add(mouthBillVo);
			}
			BillDayVO billDayVO = new BillDayVO();
			billDayVO.setAmount(NumberUtil.formatRMBNumber(tradeWithdrawDO.getAmount()));
			billDayVO.setStatus(tradeWithdrawDO.getStatus());
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
		
		String backMouth = null;
		if(billJO.getPageNum() > 1) {//当页码大于1的时候，需要获取到上一页最后一条数据的月份，用于判断是否需要显示
			ResultPageDTO<TradeWithdrawDO> datasResultBack = tradeWithdrawService.page(tradeWithdraw, billJO.getPageNum()-1, billJO.getPageSize());
			if(CollectionUtils.isNotEmpty(datasResultBack.getList())) {
				TradeWithdrawDO tradeWithdrawDOBack = datasResultBack.getList().get(datasResultBack.getList().size()-1);
				backMouth = NumberUtil.formatYYYYMMDate(tradeWithdrawDOBack.getCreateTime());
			}
		}
		
		List<Integer> statuses = Lists.newArrayList();
		statuses.add(3);statuses.add(2);
		for (BillVO billVO : data) {
			List<MonthWithdrawCountDTO> countWithdraws = tradeWithdrawService.doQueryTotalAmountGroupByMouth(billJO.getUserId(), billVO.getBillDate(), statuses);
			if(CollectionUtils.isNotEmpty(countWithdraws)) {
				for (MonthWithdrawCountDTO monthWithdrawCountDTO : countWithdraws) {
					if(monthWithdrawCountDTO.getTradeType()==1) {
						billVO.setTotalRevenue(NumberUtil.formatRMBNumber(new BigDecimal(monthWithdrawCountDTO.getTotalAmount())));;
					}else {
						billVO.setTotalExpenditure(NumberUtil.addRMBNumber(billVO.getTotalExpenditure(),NumberUtil.formatRMBNumber(monthWithdrawCountDTO.getTotalAmount())));
					}
					//根据当前第一条数据的月份跟上一页最后一页的的月份对比，如果相等不需要显示，不相等需要显示月份统计总和
					if(billJO.getPageNum() == 1) {
						billVO.setDisplay(true);
					}else {
						String firstMouth = billVO.getBillDate();
						if(firstMouth.equals(backMouth)) {
							billVO.setDisplay(false);
						}else {
							billVO.setDisplay(true);
						}
					}
				}
				//如果统计结果为空，则赋值0
				if(Strings.isNullOrEmpty(billVO.getTotalExpenditure())) {
					billVO.setTotalExpenditure("-0.00");
				}else {
					billVO.setTotalExpenditure("-"+billVO.getTotalExpenditure());
				}
				if(Strings.isNullOrEmpty(billVO.getTotalRevenue())) {
					billVO.setTotalRevenue("+0.00");
				}else {
					billVO.setTotalRevenue("+"+billVO.getTotalRevenue());
				}
			}
		}
		
		Integer totalNum = datasResult.getTotal();
		resultData.setTotalPage(totalNum/billJO.getPageSize());
		if(totalNum%billJO.getPageSize() != 0) {
			resultData.setTotalPage(resultData.getTotalPage()+1);
		}
		
		resultData.setBillListData(data);
		
        return success(resultData);
    }
}
