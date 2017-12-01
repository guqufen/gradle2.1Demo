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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyAlterDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyAlterProvider;

import java.util.List;;

public interface DataCompanyAlterDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_alter WHERE id = #{id}")
    public DataCompanyAlterDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_alter(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyAlterDO dataCompanyAlter);

    @Delete("DELETE FROM risk_data_company_alter WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyAlterProvider.class, method = "update")
    public int update(@Param("dataCompanyAlter") DataCompanyAlterDO  dataCompanyAlter);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyAlterProvider.class, method = "pageList")
    public List<DataCompanyAlterDO> pageList(@Param("dataCompanyAlter") DataCompanyAlterDO dataCompanyAlter, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyAlterProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyAlter") DataCompanyAlterDO dataCompanyAlter);

}