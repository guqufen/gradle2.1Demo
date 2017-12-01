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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyPersonDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyPersonProvider;

import java.util.List;;

public interface DataCompanyPersonDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_person WHERE id = #{id}")
    public DataCompanyPersonDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_person(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyPersonDO dataCompanyPerson);

    @Delete("DELETE FROM risk_data_company_person WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyPersonProvider.class, method = "update")
    public int update(@Param("dataCompanyPerson") DataCompanyPersonDO  dataCompanyPerson);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyPersonProvider.class, method = "pageList")
    public List<DataCompanyPersonDO> pageList(@Param("dataCompanyPerson") DataCompanyPersonDO dataCompanyPerson, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyPersonProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyPerson") DataCompanyPersonDO dataCompanyPerson);

}