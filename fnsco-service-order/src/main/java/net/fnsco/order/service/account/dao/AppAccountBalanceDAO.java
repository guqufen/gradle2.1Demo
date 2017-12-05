package net.fnsco.order.service.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.order.service.account.entity.AppAccountBalanceDO;
import net.fnsco.order.service.account.dao.helper.AppAccountBalanceProvider;

import java.util.List;;

public interface AppAccountBalanceDAO {

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "freeze_amount",property = "freezeAmount"),@Result( column = "update_time",property = "updateTime"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM u_app_account_balance WHERE id = #{id}")
    public AppAccountBalanceDO getById(@Param("id") int id);

    @Insert("INSERT into u_app_account_balance(id,app_user_id,fund,freeze_amount,update_time,create_time) VALUES (#{id},#{appUserId},#{fund},#{freezeAmount},#{updateTime},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppAccountBalanceDO appAccountBalance);

    @Delete("DELETE FROM u_app_account_balance WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppAccountBalanceProvider.class, method = "update")
    public int update(@Param("appAccountBalance") AppAccountBalanceDO  appAccountBalance);

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "freeze_amount",property = "freezeAmount"),@Result( column = "update_time",property = "updateTime"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = AppAccountBalanceProvider.class, method = "pageList")
    public List<AppAccountBalanceDO> pageList(@Param("appAccountBalance") AppAccountBalanceDO appAccountBalance, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppAccountBalanceProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appAccountBalance") AppAccountBalanceDO appAccountBalance);

}