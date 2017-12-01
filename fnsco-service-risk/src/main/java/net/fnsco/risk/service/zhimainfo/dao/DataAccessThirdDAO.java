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
import net.fnsco.risk.service.zhimainfo.entity.DataAccessThirdDO;
import net.fnsco.risk.service.zhimainfo.dao.helper.DataAccessThirdProvider;

import java.util.List;;

public interface DataAccessThirdDAO {

    @Results({@Result( column = "biz_no",property = "bizNo"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM rist_data_access_third WHERE id = #{id}")
    public DataAccessThirdDO getById(@Param("id") int id);

    @Insert("INSERT into rist_data_access_third(id,type,key,biz_no,channel,create_time) VALUES (#{id},#{type},#{key},#{bizNo},#{channel},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DataAccessThirdDO dataAccessThird);

    @Delete("DELETE FROM rist_data_access_third WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DataAccessThirdProvider.class, method = "update")
    public int update(@Param("dataAccessThird") DataAccessThirdDO  dataAccessThird);

    @Results({@Result( column = "biz_no",property = "bizNo"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = DataAccessThirdProvider.class, method = "pageList")
    public List<DataAccessThirdDO> pageList(@Param("dataAccessThird") DataAccessThirdDO dataAccessThird, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DataAccessThirdProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dataAccessThird") DataAccessThirdDO dataAccessThird);

}