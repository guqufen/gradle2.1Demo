package net.fnsco.order.service.act.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.order.service.act.entity.LoanApplyUserDO;
import net.fnsco.order.service.act.dao.helper.LoanApplyUserProvider;

import java.util.List;;

public interface LoanApplyUserDAO {

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "contact_num",property = "contactNum"),@Result( column = "card_type",property = "cardType"),@Result( column = "card_num",property = "cardNum"),@Result( column = "loan_type",property = "loanType"),@Result( column = "innner_code",property = "innnerCode") })
    @Select("SELECT * FROM act_loan_apply_user WHERE id = #{id}")
    public LoanApplyUserDO getById(@Param("id") int id);

    @Insert("INSERT into act_loan_apply_user(id,user_name,contact_num,card_type,card_num,loan_type,innner_code) VALUES (#{id},#{userName},#{contactNum},#{cardType},#{cardNum},#{loanType},#{innnerCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(LoanApplyUserDO loanApplyUser);

    @Delete("DELETE FROM act_loan_apply_user WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = LoanApplyUserProvider.class, method = "update")
    public int update(@Param("loanApplyUser") LoanApplyUserDO  loanApplyUser);

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "contact_num",property = "contactNum"),@Result( column = "card_type",property = "cardType"),@Result( column = "card_num",property = "cardNum"),@Result( column = "loan_type",property = "loanType"),@Result( column = "innner_code",property = "innnerCode") })
    @SelectProvider(type = LoanApplyUserProvider.class, method = "pageList")
    public List<LoanApplyUserDO> pageList(@Param("loanApplyUser") LoanApplyUserDO loanApplyUser, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = LoanApplyUserProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("loanApplyUser") LoanApplyUserDO loanApplyUser);

}