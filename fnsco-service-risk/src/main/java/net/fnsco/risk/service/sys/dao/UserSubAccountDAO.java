package net.fnsco.risk.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.sys.entity.UserSubAccountDO;
import net.fnsco.risk.service.sys.dao.helper.UserSubAccountProvider;

import java.util.List;;

public interface UserSubAccountDAO {

    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "account_balance",property = "accountBalance"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @Select("SELECT * FROM risk_user_sub_account WHERE id = #{id}")
    public UserSubAccountDO getById(@Param("id") int id);

    @Insert("INSERT into risk_user_sub_account(id,agent_id,account_balance,last_modify_time) VALUES (#{id},#{agentId},#{accountBalance},#{lastModifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserSubAccountDO userSubAccount);

    @Delete("DELETE FROM risk_user_sub_account WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserSubAccountProvider.class, method = "update")
    public int update(@Param("userSubAccount") UserSubAccountDO  userSubAccount);

    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "account_balance",property = "accountBalance"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @SelectProvider(type = UserSubAccountProvider.class, method = "pageList")
    public List<UserSubAccountDO> pageList(@Param("userSubAccount") UserSubAccountDO userSubAccount, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserSubAccountProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userSubAccount") UserSubAccountDO userSubAccount);

}