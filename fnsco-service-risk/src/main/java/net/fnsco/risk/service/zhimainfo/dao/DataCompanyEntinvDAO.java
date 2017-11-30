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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyEntinvDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyEntinvProvider;

import java.util.List;;

public interface DataCompanyEntinvDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_entinv WHERE id = #{id}")
    public DataCompanyEntinvDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_entinv(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyEntinvDO dataCompanyEntinv);

    @Delete("DELETE FROM risk_data_company_entinv WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyEntinvProvider.class, method = "update")
    public int update(@Param("dataCompanyEntinv") DataCompanyEntinvDO  dataCompanyEntinv);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyEntinvProvider.class, method = "pageList")
    public List<DataCompanyEntinvDO> pageList(@Param("dataCompanyEntinv") DataCompanyEntinvDO dataCompanyEntinv, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyEntinvProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyEntinv") DataCompanyEntinvDO dataCompanyEntinv);

}