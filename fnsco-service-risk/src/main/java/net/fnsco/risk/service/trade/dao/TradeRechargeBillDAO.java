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
import net.fnsco.risk.service.trade.entity.TradeRechargeBillDO;
import net.fnsco.risk.service.trade.dao.helper.TradeRechargeBillProvider;

import java.util.List;;

public interface TradeRechargeBillDAO {

    @Results({@Result( column = "rxe_amount",property = "rxeAmount"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_trade_recharge_bill WHERE id = #{id}")
    public TradeRechargeBillDO getById(@Param("id") int id);

    @Insert("INSERT into risk_trade_recharge_bill(id,rxe_amount,remark,create_time) VALUES (#{id},#{rxeAmount},#{remark},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeRechargeBillDO tradeRechargeBill);

    @Delete("DELETE FROM risk_trade_recharge_bill WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeRechargeBillProvider.class, method = "update")
    public int update(@Param("tradeRechargeBill") TradeRechargeBillDO  tradeRechargeBill);

    @Results({@Result( column = "rxe_amount",property = "rxeAmount"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = TradeRechargeBillProvider.class, method = "pageList")
    public List<TradeRechargeBillDO> pageList(@Param("tradeRechargeBill") TradeRechargeBillDO tradeRechargeBill, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeRechargeBillProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeRechargeBill") TradeRechargeBillDO tradeRechargeBill);

}