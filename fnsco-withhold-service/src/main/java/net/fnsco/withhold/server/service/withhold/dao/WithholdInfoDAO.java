package net.fnsco.withhold.server.service.withhold.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.server.service.withhold.entity.WithholdInfoDO;
import net.fnsco.withhold.server.service.withhold.dao.helper.WithholdInfoProvider;

import java.util.List;;

public interface WithholdInfoDAO {

    @Select("SELECT * FROM w_withhold_info WHERE id = #{id}")
    public WithholdInfoDO getById(@Param("id") int id);

    @Insert("INSERT into w_withhold_info(id,userName,mobile,cardNum,debitDay,amount,amountTotal,bankCard,status,modifyUserId,modifyTime,total) VALUES (#{id},#{userName},#{mobile},#{cardNum},#{debitDay},#{amount},#{amountTotal},#{bankCard},#{status},#{modifyUserId},#{modifyTime},#{total})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(WithholdInfoDO withholdInfo);

    @Delete("DELETE FROM w_withhold_info WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = WithholdInfoProvider.class, method = "update")
    public int update(@Param("withholdInfo") WithholdInfoDO  withholdInfo);

    @SelectProvider(type = WithholdInfoProvider.class, method = "pageList")
    public List<WithholdInfoDO> pageList(@Param("withholdInfo") WithholdInfoDO withholdInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = WithholdInfoProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("withholdInfo") WithholdInfoDO withholdInfo);

}