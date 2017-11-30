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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrinvDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyFrinvProvider;

import java.util.List;;

public interface DataCompanyFrinvDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_frinv WHERE id = #{id}")
    public DataCompanyFrinvDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_frinv(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyFrinvDO dataCompanyFrinv);

    @Delete("DELETE FROM risk_data_company_frinv WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyFrinvProvider.class, method = "update")
    public int update(@Param("dataCompanyFrinv") DataCompanyFrinvDO  dataCompanyFrinv);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyFrinvProvider.class, method = "pageList")
    public List<DataCompanyFrinvDO> pageList(@Param("dataCompanyFrinv") DataCompanyFrinvDO dataCompanyFrinv, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyFrinvProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyFrinv") DataCompanyFrinvDO dataCompanyFrinv);

}