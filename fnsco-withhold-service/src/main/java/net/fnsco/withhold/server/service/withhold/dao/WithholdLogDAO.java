package net.fnsco.withhold.server.service.withhold.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.server.service.withhold.entity.WithholdLogDO;
import net.fnsco.withhold.server.service.withhold.dao.helper.WithholdLogProvider;

import java.util.List;;

public interface WithholdLogDAO {

    @Select("SELECT * FROM w_withhold_log WHERE id = #{id}")
    public WithholdLogDO getById(@Param("id") int id);

    @Insert("INSERT into w_withhold_log(id,withholdId,amount,status,failReason,debitDay) VALUES (#{id},#{withholdId},#{amount},#{status},#{failReason},#{debitDay})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(WithholdLogDO withholdLog);

    @Delete("DELETE FROM w_withhold_log WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = WithholdLogProvider.class, method = "update")
    public int update(@Param("withholdLog") WithholdLogDO  withholdLog);

    @SelectProvider(type = WithholdLogProvider.class, method = "pageList")
    public List<WithholdLogDO> pageList(@Param("withholdLog") WithholdLogDO withholdLog, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = WithholdLogProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("withholdLog") WithholdLogDO withholdLog);

}