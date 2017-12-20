package net.fnsco.trading.service.third.phoneCharge.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.third.phoneCharge.dao.helper.PhoneChargeOrderProvider;
import net.fnsco.trading.service.third.phoneCharge.dto.PhoneChargeOrderDO;

import java.util.List;;

public interface PhoneChargeOrderDAO {

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "order_no",property = "orderNo"),@Result( column = "pay_order_no",property = "payOrderNo"),@Result( column = "phone_no",property = "phoneNo"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime") })
    @Select("SELECT * FROM thr_PhoneCharge_order WHERE id = #{id}")
    public PhoneChargeOrderDO getById(@Param("id") int id);

    @Insert("INSERT into thr_PhoneCharge_order(id,type,app_user_id,order_no,pay_order_no,phone_no,pid,name,amt,status,resp_code,resp_msg,create_time,update_time) VALUES (#{id},#{type},#{appUserId},#{orderNo},#{payOrderNo},#{phoneNo},#{pid},#{name},#{amt},#{status},#{respCode},#{respMsg},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(PhoneChargeOrderDO phoneChargeOrder);

    @Delete("DELETE FROM thr_PhoneCharge_order WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = PhoneChargeOrderProvider.class, method = "update")
    public int update(@Param("phoneChargeOrder") PhoneChargeOrderDO  phoneChargeOrder);

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "order_no",property = "orderNo"),@Result( column = "pay_order_no",property = "payOrderNo"),@Result( column = "phone_no",property = "phoneNo"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime") })
    @SelectProvider(type = PhoneChargeOrderProvider.class, method = "pageList")
    public List<PhoneChargeOrderDO> pageList(@Param("phoneChargeOrder") PhoneChargeOrderDO phoneChargeOrder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = PhoneChargeOrderProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("phoneChargeOrder") PhoneChargeOrderDO phoneChargeOrder);

}