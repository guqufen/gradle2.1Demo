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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyShareHolderDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyShareHolderProvider;

import java.util.List;;

public interface DataCompanyShareHolderDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_share_holder WHERE id = #{id}")
    public DataCompanyShareHolderDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_share_holder(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyShareHolderDO dataCompanyShareHolder);

    @Delete("DELETE FROM risk_data_company_share_holder WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyShareHolderProvider.class, method = "update")
    public int update(@Param("dataCompanyShareHolder") DataCompanyShareHolderDO  dataCompanyShareHolder);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyShareHolderProvider.class, method = "pageList")
    public List<DataCompanyShareHolderDO> pageList(@Param("dataCompanyShareHolder") DataCompanyShareHolderDO dataCompanyShareHolder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyShareHolderProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyShareHolder") DataCompanyShareHolderDO dataCompanyShareHolder);

}