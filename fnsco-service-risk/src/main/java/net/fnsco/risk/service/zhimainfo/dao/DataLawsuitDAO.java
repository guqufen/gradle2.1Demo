package net.fnsco.risk.service.zhimainfo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataLawsuitProvider;

import java.util.List;;

public interface DataLawsuitDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_lawsuit WHERE id = #{id}")
    public DataLawsuitDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_lawsuit(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataLawsuitDO dataLawsuit);

    @Delete("DELETE FROM risk_data_lawsuit WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataLawsuitProvider.class, method = "update")
    public int update(@Param("dataLawsuit") DataLawsuitDO  dataLawsuit);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataLawsuitProvider.class, method = "pageList")
    public List<DataLawsuitDO> pageList(@Param("dataLawsuit") DataLawsuitDO dataLawsuit, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataLawsuitProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataLawsuit") DataLawsuitDO dataLawsuit);

}