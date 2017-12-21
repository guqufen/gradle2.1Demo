package net.fnsco.trading.service.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.order.entity.TradeOrderExtDO;
import net.fnsco.trading.service.order.dao.helper.TradeOrderExtProvider;

import java.util.List;;

public interface TradeOrderExtDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM t_trade_order_ext WHERE id = #{id}")
    public TradeOrderExtDO getById(@Param("id") int id);

    @Insert("INSERT into t_trade_order_ext(id,order_no,name,mobile,card,create_time) VALUES (#{id},#{orderNo},#{name},#{mobile},#{card},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderExtDO tradeOrderExt);

    @Delete("DELETE FROM t_trade_order_ext WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderExtProvider.class, method = "update")
    public int update(@Param("tradeOrderExt") TradeOrderExtDO  tradeOrderExt);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = TradeOrderExtProvider.class, method = "pageList")
    public List<TradeOrderExtDO> pageList(@Param("tradeOrderExt") TradeOrderExtDO tradeOrderExt, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderExtProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrderExt") TradeOrderExtDO tradeOrderExt);

}