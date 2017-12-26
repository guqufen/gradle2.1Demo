package net.fnsco.risk.service.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import net.fnsco.risk.service.trade.dao.helper.TradeDataProvider;
import net.fnsco.risk.service.trade.entity.TradeDataDO;;

public interface TradeDataDAO {

    @Select("SELECT MAX(time_stamp) FROM t_trade_data WHERE inner_code = #{innerCode}")
    public String getByInnerCode(@Param("innerCode") String innerCode);


    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_time_out",property = "payTimeOut"),@Result( column = "customer_info",property = "customerInfo"),@Result( column = "customer_ip",property = "customerIp"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_currency",property = "settleCurrency"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "succ_time",property = "succTime"),@Result( column = "org_mer_order_id",property = "orgMerOrderId"),@Result( column = "can_ref_amt",property = "canRefAmt"),@Result( column = "ref_cnt",property = "refCnt"),@Result( column = "ref_amt",property = "refAmt"),@Result( column = "bank_id",property = "bankId"),@Result( column = "pp_flag",property = "ppFlag"),@Result( column = "dc_type",property = "dcType"),@Result( column = "certify_id",property = "certifyId"),@Result( column = "msg_dest_id",property = "msgDestId"),@Result( column = "customer_nm",property = "customerNm"),@Result( column = "phone_no",property = "phoneNo"),@Result( column = "sub_open_id",property = "subOpenId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "order_no",property = "orderNo"),@Result( column = "order_time",property = "orderTime"),@Result( column = "order_info",property = "orderInfo"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "time_stamp",property = "timeStamp"),@Result( column = "trade_detail",property = "tradeDetail"),@Result( column = "mer_id",property = "merId"),@Result( column = "term_id",property = "termId"),@Result( column = "batch_no",property = "batchNo"),@Result( column = "trace_no",property = "traceNo"),@Result( column = "refer_no",property = "referNo"),@Result( column = "auth_code",property = "authCode"),@Result( column = "order_id_scan",property = "orderIdScan"),@Result( column = "send_time",property = "sendTime"),@Result( column = "create_time",property = "createTime"),@Result( column = "pay_medium",property = "payMedium"),@Result( column = "channel_term_code",property = "channelTermCode") })
    @SelectProvider(type = TradeDataProvider.class, method = "pageList")
    public List<TradeDataDO> pageList(@Param("tradeData") TradeDataDO tradeData, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeDataProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeData") TradeDataDO tradeData);
    
    
    /**
	 * 统计某个实体商户一个月实际交易金额总和
	 * @param startTime
	 * @param endTime
	 * @param entityInnerCode
	 * @param amt
	 * @return
	 */
    @Select("SELECT SUM(amt) FROM t_trade_data WHERE time_stamp >= #{startTime} AND time_stamp < #{endTime} AND inner_code IN (SELECT inner_code FROM m_merchant_core_entity_ref WHERE "
    		+ "entity_inner_code = #{entityInnerCode} ) AND STATUS = 1 AND amt <= #{amt}")
	public String queryMerchantMouthSum(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("entityInnerCode")String entityInnerCode,@Param("amt")String amt);

}