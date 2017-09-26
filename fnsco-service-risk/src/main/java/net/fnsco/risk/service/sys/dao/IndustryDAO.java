package net.fnsco.risk.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.sys.entity.IndustryDO;
import net.fnsco.risk.service.sys.dao.helper.IndustryProvider;

import java.util.List;;

public interface IndustryDAO {

    @Results({@Result( column = "id",property = "id") ,@Result( column = "code",property = "code") ,@Result( column = "business_form",property = "businessForm"), @Result( column = "first",property = "first"),
        @Result( column = "third",property = "third"), @Result( column = "fourth",property = "fourth"), @Result( column = "status",property = "status"), @Result( column = "remark",property = "remark")})
    @Select("SELECT * FROM sys_industry WHERE id = #{id}")
    public IndustryDO getById(@Param("id") int id);

    @Insert("INSERT into sys_industry(id,code,business_form,first,third,fourth,status,remark) VALUES (#{id},#{code},#{businessForm},#{first},#{third},#{fourth},#{status},#{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(IndustryDO industry);

    @Delete("DELETE FROM sys_industry WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = IndustryProvider.class, method = "update")
    public int update(@Param("industry") IndustryDO  industry);

    @Results({@Result( column = "business_form",property = "businessForm") })
    @SelectProvider(type = IndustryProvider.class, method = "pageList")
    public List<IndustryDO> pageList(@Param("industry") IndustryDO industry, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = IndustryProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("industry") IndustryDO industry);

    /**
     * 查找所有的行业数据
     * @return
     */
    @Results({@Result( column = "id",property = "id") ,@Result( column = "code",property = "code") ,@Result( column = "business_form",property = "businessForm"), @Result( column = "first",property = "first"),
    	@Result( column = "third",property = "third"), @Result( column = "fourth",property = "fourth"), @Result( column = "status",property = "status"), @Result( column = "remark",property = "remark")})
    @Select("select * FROM sys_industry")
    public List<IndustryDO> queryAll();
}