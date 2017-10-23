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
import net.fnsco.risk.service.sys.entity.UserSubAccountSeqDO;
import net.fnsco.risk.service.sys.dao.helper.UserSubAccountSeqProvider;

import java.util.List;;

public interface UserSubAccountSeqDAO {

    @Results({@Result( column = "account_balance",property = "accountBalance"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_user_sub_account_seq WHERE id = #{id}")
    public UserSubAccountSeqDO getById(@Param("id") int id);

    @Insert("INSERT into risk_user_sub_account_seq(id,account_balance,remark,create_time) VALUES (#{id},#{accountBalance},#{remark},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserSubAccountSeqDO userSubAccountSeq);

    @Delete("DELETE FROM risk_user_sub_account_seq WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserSubAccountSeqProvider.class, method = "update")
    public int update(@Param("userSubAccountSeq") UserSubAccountSeqDO  userSubAccountSeq);

    @Results({@Result( column = "account_balance",property = "accountBalance"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = UserSubAccountSeqProvider.class, method = "pageList")
    public List<UserSubAccountSeqDO> pageList(@Param("userSubAccountSeq") UserSubAccountSeqDO userSubAccountSeq, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserSubAccountSeqProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userSubAccountSeq") UserSubAccountSeqDO userSubAccountSeq);

}