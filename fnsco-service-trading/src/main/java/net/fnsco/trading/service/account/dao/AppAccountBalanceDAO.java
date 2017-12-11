package net.fnsco.trading.service.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.account.entity.AppAccountBalanceDO;
import net.fnsco.trading.service.account.dao.helper.AppAccountBalanceProvider;

import java.math.BigDecimal;
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
    
    /**
     * getByAppUserId:(根据appUserId查询帐号信息)
     *
     * @param  @param appUserId
     * @param  @return    设定文件
     * @return AppAccountBalanceDO    DOM对象
     * @author tangliang
     * @date   2017年12月7日 上午9:46:48
     */
    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "freeze_amount",property = "freezeAmount"),@Result( column = "update_time",property = "updateTime"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM u_app_account_balance WHERE appUserId = #{appUserId} limit 1")
    public AppAccountBalanceDO getByAppUserId(@Param("appUserId") int appUserId);
    
    /**
     * updateFund:(更新余额)
     *
     * @param  @param fund
     * @param  @param appUserId
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年12月11日 上午11:38:13
     */
    @Update("UPDATE u_app_account_balance SET fund = fund - #{fund} WHERE app_user_id = #{appUserId} AND fund = fund - #{fund} >=0")
    public int updateFund(@Param("fund") BigDecimal  fund,@Param("appUserId") int appUserId);

}