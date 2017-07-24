package net.fnsco.withhold.service.trade.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;
import net.fnsco.withhold.service.trade.dao.helper.WithholdInfoProvider;

import java.util.List;;

public interface WithholdInfoDAO {

    @Select("SELECT * FROM w_withhold_info WHERE id = #{id}")
    public WithholdInfoDO getById(@Param("id") int id);

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "card_num",property = "cardNum"),@Result( column = "debit_day",property = "debitDay"),@Result( column = "amount_total",property = "amountTotal"),@Result( column = "bank_card",property = "bankCard"),@Result( column = "modify_user_id",property = "modifyUserId"),@Result( column = "modify_time",property = "modifyTime") })
    @Insert("INSERT into w_withhold_info(id,user_name,mobile,card_num,debit_day,amount,amount_total,bank_card,status,modify_user_id,modify_time,total) VALUES (#{id},#{userName},#{mobile},#{cardNum},#{debitDay},#{amount},#{amountTotal},#{bankCard},#{status},#{modifyUserId},#{modifyTime},#{total})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(WithholdInfoDO withholdInfo);

    @Delete("DELETE FROM w_withhold_info WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = WithholdInfoProvider.class, method = "update")
    public int update(@Param("withholdInfo") WithholdInfoDO  withholdInfo);

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "card_num",property = "cardNum"),@Result( column = "debit_day",property = "debitDay"),@Result( column = "amount_total",property = "amountTotal"),@Result( column = "bank_card",property = "bankCard"),@Result( column = "modify_user_id",property = "modifyUserId"),@Result( column = "modify_time",property = "modifyTime") })
    @SelectProvider(type = WithholdInfoProvider.class, method = "pageList")
    public List<WithholdInfoDO> pageList(@Param("withholdInfo") WithholdInfoDO withholdInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = WithholdInfoProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("withholdInfo") WithholdInfoDO withholdInfo);

}