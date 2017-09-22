package net.fnsco.risk.service.trade.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.trade.entity.TradeDataDO;
import net.fnsco.risk.service.trade.dao.helper.TradeDataProvider;

import java.util.List;;

public interface TradeDataDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_time_out",property = "payTimeOut"),@Result( column = "customer_info",property = "customerInfo"),@Result( column = "customer_ip",property = "customerIp"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_currency",property = "settleCurrency"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "succ_time",property = "succTime"),@Result( column = "org_mer_order_id",property = "orgMerOrderId"),@Result( column = "can_ref_amt",property = "canRefAmt"),@Result( column = "ref_cnt",property = "refCnt"),@Result( column = "ref_amt",property = "refAmt"),@Result( column = "bank_id",property = "bankId"),@Result( column = "pp_flag",property = "ppFlag"),@Result( column = "dc_type",property = "dcType"),@Result( column = "certify_id",property = "certifyId"),@Result( column = "msg_dest_id",property = "msgDestId"),@Result( column = "customer_nm",property = "customerNm"),@Result( column = "phone_no",property = "phoneNo"),@Result( column = "sub_open_id",property = "subOpenId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "order_no",property = "orderNo"),@Result( column = "order_time",property = "orderTime"),@Result( column = "order_info",property = "orderInfo"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "time_stamp",property = "timeStamp"),@Result( column = "trade_detail",property = "tradeDetail"),@Result( column = "mer_id",property = "merId"),@Result( column = "term_id",property = "termId"),@Result( column = "batch_no",property = "batchNo"),@Result( column = "trace_no",property = "traceNo"),@Result( column = "refer_no",property = "referNo"),@Result( column = "auth_code",property = "authCode"),@Result( column = "order_id_scan",property = "orderIdScan"),@Result( column = "send_time",property = "sendTime"),@Result( column = "create_time",property = "createTime"),@Result( column = "pay_medium",property = "payMedium"),@Result( column = "channel_term_code",property = "channelTermCode") })
    @Select("SELECT * FROM t_trade_data WHERE id = #{id}")
    public TradeDataDO getById(@Param("id") int id);

    @Insert("INSERT into t_trade_data(id,inner_code,txn_type,txn_sub_type,currency,pay_time_out,subject,body,customer_info,remark,customer_ip,tn,resp_code,resp_msg,settle_amount,settle_currency,settle_date,succ_time,org_mer_order_id,can_ref_amt,ref_cnt,ref_amt,bank_id,pp_flag,purpose,dc_type,certify_id,msg_dest_id,customer_nm,phone_no,sub_open_id,channel_type,amt,order_no,order_time,order_info,pay_type,pay_sub_type,time_stamp,trade_detail,mer_id,term_id,batch_no,trace_no,refer_no,auth_code,order_id_scan,source,md5,send_time,create_time,status,pay_medium,channel_term_code) VALUES (#{id},#{innerCode},#{txnType},#{txnSubType},#{currency},#{payTimeOut},#{subject},#{body},#{customerInfo},#{remark},#{customerIp},#{tn},#{respCode},#{respMsg},#{settleAmount},#{settleCurrency},#{settleDate},#{succTime},#{orgMerOrderId},#{canRefAmt},#{refCnt},#{refAmt},#{bankId},#{ppFlag},#{purpose},#{dcType},#{certifyId},#{msgDestId},#{customerNm},#{phoneNo},#{subOpenId},#{channelType},#{amt},#{orderNo},#{orderTime},#{orderInfo},#{payType},#{paySubType},#{timeStamp},#{tradeDetail},#{merId},#{termId},#{batchNo},#{traceNo},#{referNo},#{authCode},#{orderIdScan},#{source},#{md5},#{sendTime},#{createTime},#{status},#{payMedium},#{channelTermCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeDataDO tradeData);

    @Delete("DELETE FROM t_trade_data WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeDataProvider.class, method = "update")
    public int update(@Param("tradeData") TradeDataDO  tradeData);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_time_out",property = "payTimeOut"),@Result( column = "customer_info",property = "customerInfo"),@Result( column = "customer_ip",property = "customerIp"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_currency",property = "settleCurrency"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "succ_time",property = "succTime"),@Result( column = "org_mer_order_id",property = "orgMerOrderId"),@Result( column = "can_ref_amt",property = "canRefAmt"),@Result( column = "ref_cnt",property = "refCnt"),@Result( column = "ref_amt",property = "refAmt"),@Result( column = "bank_id",property = "bankId"),@Result( column = "pp_flag",property = "ppFlag"),@Result( column = "dc_type",property = "dcType"),@Result( column = "certify_id",property = "certifyId"),@Result( column = "msg_dest_id",property = "msgDestId"),@Result( column = "customer_nm",property = "customerNm"),@Result( column = "phone_no",property = "phoneNo"),@Result( column = "sub_open_id",property = "subOpenId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "order_no",property = "orderNo"),@Result( column = "order_time",property = "orderTime"),@Result( column = "order_info",property = "orderInfo"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "time_stamp",property = "timeStamp"),@Result( column = "trade_detail",property = "tradeDetail"),@Result( column = "mer_id",property = "merId"),@Result( column = "term_id",property = "termId"),@Result( column = "batch_no",property = "batchNo"),@Result( column = "trace_no",property = "traceNo"),@Result( column = "refer_no",property = "referNo"),@Result( column = "auth_code",property = "authCode"),@Result( column = "order_id_scan",property = "orderIdScan"),@Result( column = "send_time",property = "sendTime"),@Result( column = "create_time",property = "createTime"),@Result( column = "pay_medium",property = "payMedium"),@Result( column = "channel_term_code",property = "channelTermCode") })
    @SelectProvider(type = TradeDataProvider.class, method = "pageList")
    public List<TradeDataDO> pageList(@Param("tradeData") TradeDataDO tradeData, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeDataProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeData") TradeDataDO tradeData);

}