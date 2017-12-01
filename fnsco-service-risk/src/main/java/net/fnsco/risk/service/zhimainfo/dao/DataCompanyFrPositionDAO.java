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
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrPositionDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataCompanyFrPositionProvider;

import java.util.List;;

public interface DataCompanyFrPositionDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM risk_data_company_fr_position WHERE id = #{id}")
    public DataCompanyFrPositionDO getById(@Param("id") int id);

    @Insert("INSERT into risk_data_company_fr_position(id,inner_code,key,value,source,type,create_time) VALUES (#{id},#{innerCode},#{key},#{value},#{source},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataCompanyFrPositionDO dataCompanyFrPosition);

    @Delete("DELETE FROM risk_data_company_fr_position WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataCompanyFrPositionProvider.class, method = "update")
    public int update(@Param("dataCompanyFrPosition") DataCompanyFrPositionDO  dataCompanyFrPosition);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataCompanyFrPositionProvider.class, method = "pageList")
    public List<DataCompanyFrPositionDO> pageList(@Param("dataCompanyFrPosition") DataCompanyFrPositionDO dataCompanyFrPosition, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataCompanyFrPositionProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataCompanyFrPosition") DataCompanyFrPositionDO dataCompanyFrPosition);

}