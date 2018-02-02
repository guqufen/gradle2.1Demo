package net.fnsco.web.controller.e789.tradedata;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.StringUtil;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.web.controller.e789.jo.TradeDataDetailJO;
import net.fnsco.web.controller.e789.jo.TradeDataJO;
import net.fnsco.web.controller.e789.vo.TradeDataDetailVO;
import net.fnsco.web.controller.e789.vo.TradeDataListVO;
import net.fnsco.web.controller.e789.vo.TradeDataVO;

/**
 * @desc 流水查询控制器
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年12月5日 下午3:12:47
 */
@RestController
@RequestMapping(value = "/app2c/tradeData", method = RequestMethod.POST)
@Api(value = "/app2c/tradeData", tags = { "首页-流水相关功能接口" })
public class TradedataE789Controller extends BaseController {

	@Autowired
	private TradeOrderService tradeOrderService;
	@Autowired
	private PaymentService paymentService;

	/**
	 * queryTradeDataList:(查询流水列表)
	 *
	 * @param @param
	 *            tradeDataJO
	 * @param @return
	 *            设定文件
	 * @return ResultDTO<Object> DOM对象
	 * @author tangliang
	 * @date 2017年12月5日 下午4:03:41
	 */
	@RequestMapping(value = "/query")
	@ApiOperation(value = "首页-查询交易流水列表")
	public ResultDTO<TradeDataVO> queryTradeDataList(@RequestBody TradeDataJO tradeDataJO) {
		if (null == tradeDataJO.getUserId()) {
			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
		}
		
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setUserId(tradeDataJO.getUserId());
		ResultPageDTO<TradeOrderDO> resultData = tradeOrderService.page(tradeOrderDO, tradeDataJO.getPageNum(),
				tradeDataJO.getPageSize());

		TradeDataVO resultVO = new TradeDataVO();
		resultVO.setCurrentPageNum(tradeDataJO.getPageNum());
		resultVO.setPageSize(tradeDataJO.getPageSize());
		Integer yushu = resultData.getTotal() % resultVO.getPageSize();
		Integer totalPage = resultData.getTotal() / resultVO.getPageSize();
		if (yushu != 0) {
			totalPage = totalPage + 1;
		}
		resultVO.setTotalPage(totalPage);
		List<TradeOrderDO> data = resultData.getList();
		List<TradeDataListVO> datas = Lists.newArrayList();
		for (TradeOrderDO tradeOrderDO2 : data) {
			TradeDataListVO vo = new TradeDataListVO();
			vo.setOrderNo(tradeOrderDO2.getOrderNo());
			vo.setPaySubType(tradeOrderDO2.getPaySubType());
			vo.setTradeAmt(StringUtil.formatRMBNumber(tradeOrderDO2.getTxnAmount().toString()));
			vo.setTradeStatus(tradeOrderDO2.getRespCode());
			if ("1000".equals(tradeOrderDO2.getRespCode())) {
				vo.setTradeStatusName("处理中");
			} else if ("1001".equals(tradeOrderDO2.getRespCode())) {
				vo.setTradeStatusName("成功");
			} else if ("1002".equals(tradeOrderDO2.getRespCode())) {
				vo.setTradeStatusName("失败");
			} else if ("1003".equals(tradeOrderDO2.getRespCode())) {
				vo.setTradeStatusName("已退货");
			}
			vo.setTradeDate(DateUtils.strToDate(tradeOrderDO2.getCreateTime()));
			vo.setTraId(tradeOrderDO2.getId().toString());

			datas.add(vo);
		}

		resultVO.setDatas(datas);

		return success(resultVO);
	}

	/**
	 * queryTradeDataDetail:(查询交易流水详情)
	 *
	 * @param  @param tradeDataDetailJO
	 * @param  @return    设定文件
	 * @return ResultDTO<TradeDataDetailVO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 下午4:07:33
	 */
	@RequestMapping(value = "/queryDetail")
	@ApiOperation(value = "首页-流水详情-查询交易流水详情")
	public ResultDTO<TradeDataDetailVO> queryTradeDataDetail(@RequestBody TradeDataDetailJO tradeDataDetailJO){
		
		if(Strings.isNullOrEmpty(tradeDataDetailJO.getOrderNo())) { 
			return ResultDTO.fail(ApiConstant.E_ORDER_NO_NULL);
		}
		TradeOrderDO tradeOrderDO =  tradeOrderService.queryByOrderId(tradeDataDetailJO.getOrderNo());
		TradeDataDetailVO vo = new TradeDataDetailVO();
		
		vo.setCreateDate(DateUtils.strToDate(tradeOrderDO.getCreateTime()));
		vo.setOrderAmt(StringUtil.formatRMBNumber(tradeOrderDO.getTxnAmount().toString()));
		vo.setOrderNo(tradeOrderDO.getOrderNo());
		vo.setPayDate(DateUtils.strToDate(tradeOrderDO.getCompleteTime()));
		vo.setPaySubType(tradeOrderDO.getPaySubType());
		vo.setTraId(tradeOrderDO.getId().toString());
		vo.setTradeStatus(tradeOrderDO.getRespCode());
		
		logger.info("订单号="+tradeDataDetailJO.getOrderNo()+"交易状态="+tradeOrderDO.getRespCode());
		//查询中信交易状态
//		if(StringUtils.equals("05", tradeOrderDO.getChannelType())||"1000".equals(tradeOrderDO.getRespCode())){
//			TradeOrderDO tradeOrderDO2 = new TradeOrderDO();
//			tradeOrderDO2 = paymentService.getDealStatus(tradeOrderDO.getId(),tradeOrderDO.getPaySubType(),tradeOrderDO.getChannelMerId(),tradeOrderDO.getOrderNo(),tradeOrderDO.getOrderCeateTime());
//			if(tradeOrderDO2 != null){
//				vo.setTradeStatus(tradeOrderDO2.getRespCode());//新的交易状态
//			}
//		}
		if("1000".equals(tradeOrderDO.getRespCode())) {
			vo.setTradeStatusName("处理中");
		}else if("1001".equals(tradeOrderDO.getRespCode())) {
			vo.setTradeStatusName("成功");
		}else if("1002".equals(tradeOrderDO.getRespCode())) {
			vo.setTradeStatusName("失败");
		}else if("1003".equals(tradeOrderDO.getRespCode())) {
			vo.setTradeStatusName("已退货");
		}
		
		//返回支付子类型名称
		if("00".equals(tradeOrderDO.getPaySubType())) {
			vo.setPaySubTypeName("刷卡支付");
		}else if("01".equals(tradeOrderDO.getPaySubType())) {
			vo.setPaySubTypeName("微信支付");
		}else if("02".equals(tradeOrderDO.getPaySubType())) {
			vo.setPaySubTypeName("支付宝支付");
		}else if("03".equals(tradeOrderDO.getPaySubType())) {
			vo.setPaySubTypeName("分闪付支付");
		}else if("04".equals(tradeOrderDO.getPaySubType())) {
			vo.setPaySubTypeName("余额支付");
		}
		
		
		vo.setRespMsg(tradeOrderDO.getRespMsg());
		
		return success(vo);
	}

}
