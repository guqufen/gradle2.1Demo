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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyBasicInfoDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyBasicInfoProvider;

import java.util.List;;

public interface DataCompanyBasicInfoDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_basicInfo WHERE id = #{id}")
    public DataCompanyBasicInfoDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_basicInfo(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyBasicInfoDO dataCompanyBasicInfo);

    @Delete("DELETE FROM risk_data_company_basicInfo WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyBasicInfoProvider.class, method = "update")
    public int update(@Param("dataCompanyBasicInfo") DataCompanyBasicInfoDO  dataCompanyBasicInfo);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyBasicInfoProvider.class, method = "pageList")
    public List<DataCompanyBasicInfoDO> pageList(@Param("dataCompanyBasicInfo") DataCompanyBasicInfoDO dataCompanyBasicInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyBasicInfoProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyBasicInfo") DataCompanyBasicInfoDO dataCompanyBasicInfo);

}