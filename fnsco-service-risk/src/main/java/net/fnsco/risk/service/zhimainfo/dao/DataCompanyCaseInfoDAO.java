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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyCaseInfoDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyCaseInfoProvider;

import java.util.List;;

public interface DataCompanyCaseInfoDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_case_info WHERE id = #{id}")
    public DataCompanyCaseInfoDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_case_info(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyCaseInfoDO dataCompanyCaseInfo);

    @Delete("DELETE FROM risk_data_company_case_info WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyCaseInfoProvider.class, method = "update")
    public int update(@Param("dataCompanyCaseInfo") DataCompanyCaseInfoDO  dataCompanyCaseInfo);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyCaseInfoProvider.class, method = "pageList")
    public List<DataCompanyCaseInfoDO> pageList(@Param("dataCompanyCaseInfo") DataCompanyCaseInfoDO dataCompanyCaseInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyCaseInfoProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyCaseInfo") DataCompanyCaseInfoDO dataCompanyCaseInfo);

}