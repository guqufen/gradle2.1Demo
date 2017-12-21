package net.fnsco.trading.service.third.reCharge.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.third.reCharge.dao.helper.RechargeOrderProvider;
import net.fnsco.trading.service.third.reCharge.entity.RechargeOrderDO;

import java.util.Date;
import java.util.List;;

public interface RechargeOrderDAO {

	@Results({@Result( column = "app_user_id",property = "appUserId"),
    	@Result( column = "order_no",property = "orderNo"),
    	@Result( column = "pay_order_no",property = "payOrderNo"),
    	@Result( column = "type",property = "type"),
    	@Result( column = "type_name",property = "name"),
    	@Result( column = "mobile",property = "mobile"),
    	@Result( column = "amount",property = "amt"),
    	@Result( column = "status",property = "status"),
    	@Result( column = "resp_code",property = "respCode"),
    	@Result( column = "resp_msg",property = "respMsg"),
    	@Result( column = "create_time",property = "createTime"),
    	@Result( column = "update_time",property = "updateTime") })
    @Select("SELECT * FROM thr_recharge_order WHERE id = #{id}")
    public RechargeOrderDO getById(@Param("id") int id);

    @Insert("INSERT into thr_recharge_order(id,app_user_id,type,order_no,pay_order_no,mobile,type_name,amount,status,resp_code,resp_msg,create_time,update_time) VALUES (#{id},#{appUserId},#{type},#{orderNo},#{payOrderNo},#{mobile},#{name},#{amt},#{status},#{respCode},#{respMsg},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(RechargeOrderDO rechargeOrder);

    @Delete("DELETE FROM thr_recharge_order WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = RechargeOrderProvider.class, method = "update")
    public int update(@Param("rechargeOrder") RechargeOrderDO  rechargeOrder);

    @Results({@Result( column = "app_user_id",property = "appUserId"),
    	@Result( column = "order_no",property = "orderNo"),
    	@Result( column = "pay_order_no",property = "payOrderNo"),
    	@Result( column = "type",property = "type"),
    	@Result( column = "type_name",property = "name"),
    	@Result( column = "mobile",property = "mobile"),
    	@Result( column = "amount",property = "amt"),
    	@Result( column = "status",property = "status"),
    	@Result( column = "resp_code",property = "respCode"),
    	@Result( column = "resp_msg",property = "respMsg"),
    	@Result( column = "create_time",property = "createTime"),
    	@Result( column = "update_time",property = "updateTime") })
    @SelectProvider(type = RechargeOrderProvider.class, method = "pageList")
    public List<RechargeOrderDO> pageList(@Param("rechargeOrder") RechargeOrderDO rechargeOrder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = RechargeOrderProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("rechargeOrder") RechargeOrderDO rechargeOrder);
    
    @Results({@Result( column = "app_user_id",property = "appUserId"),
    	@Result( column = "order_no",property = "orderNo"),
    	@Result( column = "pay_order_no",property = "payOrderNo"),
    	@Result( column = "type",property = "type"),
    	@Result( column = "type_name",property = "name"),
    	@Result( column = "mobile",property = "mobile"),
    	@Result( column = "amount",property = "amt"),
    	@Result( column = "status",property = "status"),
    	@Result( column = "resp_code",property = "respCode"),
    	@Result( column = "resp_msg",property = "respMsg"),
    	@Result( column = "create_time",property = "createTime"),
    	@Result( column = "update_time",property = "updateTime") })
    @Select("select * from thr_recharge_order where status=0 and create_time >= startDate")
    public List<RechargeOrderDO> queryPhoneCharge(@Param("startDate") Date startDate);

    @Results({@Result( column = "app_user_id",property = "appUserId"),
    	@Result( column = "order_no",property = "orderNo"),
    	@Result( column = "pay_order_no",property = "payOrderNo"),
    	@Result( column = "type",property = "type"),
    	@Result( column = "type_name",property = "name"),
    	@Result( column = "mobile",property = "mobile"),
    	@Result( column = "amount",property = "amt"),
    	@Result( column = "status",property = "status"),
    	@Result( column = "resp_code",property = "respCode"),
    	@Result( column = "resp_msg",property = "respMsg"),
    	@Result( column = "create_time",property = "createTime"),
    	@Result( column = "update_time",property = "updateTime") })
    @Select("SELECT * FROM thr_recharge_order WHERE order_no = #{orderNo}")
    public RechargeOrderDO getByOrderNo(@Param("orderNo") String orderNo);
}