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
import net.fnsco.risk.service.trade.entity.TradePayBillDO;
import net.fnsco.risk.service.trade.dao.helper.TradePayBillProvider;

import java.util.List;;

public interface TradePayBillDAO {

    @Results({@Result( column = "txn_amount",property = "txnAmount"),@Result( column = "create_time",property = "createTime"),@Result( column = "txn_businness_id",property = "txnBusinnessId"),@Result( column = "txn_bussiness_type",property = "txnBussinessType") })
    @Select("SELECT * FROM risk_trade_pay_bill WHERE id = #{id}")
    public TradePayBillDO getById(@Param("id") int id);

    @Insert("INSERT into risk_trade_pay_bill(id,txn_amount,remark,create_time,txn_businness_id,txn_bussiness_type) VALUES (#{id},#{txnAmount},#{remark},#{createTime},#{txnBusinnessId},#{txnBussinessType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradePayBillDO tradePayBill);

    @Delete("DELETE FROM risk_trade_pay_bill WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradePayBillProvider.class, method = "update")
    public int update(@Param("tradePayBill") TradePayBillDO  tradePayBill);

    @Results({@Result( column = "txn_amount",property = "txnAmount"),@Result( column = "create_time",property = "createTime"),@Result( column = "txn_businness_id",property = "txnBusinnessId"),@Result( column = "txn_bussiness_type",property = "txnBussinessType") })
    @SelectProvider(type = TradePayBillProvider.class, method = "pageList")
    public List<TradePayBillDO> pageList(@Param("tradePayBill") TradePayBillDO tradePayBill, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradePayBillProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradePayBill") TradePayBillDO tradePayBill);

}