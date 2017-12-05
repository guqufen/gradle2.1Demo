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
import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDetailDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataLawsuitDetailProvider;

import java.util.List;;

public interface DataLawsuitDetailDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_lawsuit_detail WHERE id = #{id}")
    public DataLawsuitDetailDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_lawsuit_detail(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataLawsuitDetailDO dataLawsuitDetail);

    @Delete("DELETE FROM risk_data_lawsuit_detail WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataLawsuitDetailProvider.class, method = "update")
    public int update(@Param("dataLawsuitDetail") DataLawsuitDetailDO  dataLawsuitDetail);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataLawsuitDetailProvider.class, method = "pageList")
    public List<DataLawsuitDetailDO> pageList(@Param("dataLawsuitDetail") DataLawsuitDetailDO dataLawsuitDetail, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataLawsuitDetailProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataLawsuitDetail") DataLawsuitDetailDO dataLawsuitDetail);

}