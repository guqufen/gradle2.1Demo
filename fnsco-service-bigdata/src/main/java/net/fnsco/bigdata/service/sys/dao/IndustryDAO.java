package net.fnsco.bigdata.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.bigdata.service.sys.dao.helper.IndustryProvider;
import net.fnsco.bigdata.service.sys.entity.IndustryDO;

import java.util.List;;

public interface IndustryDAO {

	@Results({@Result( column = "business_form",property = "businessForm"),@Result( column = "max_price",property = "maxPrice"),@Result( column = "interest_rate",property = "interestRate"),@Result( column = "pos_usage",property = "posUsage") })
    @Select("SELECT * FROM sys_industry WHERE id = #{id} limit 1")
    public IndustryDO getById(@Param("id") int id);

	@Insert("INSERT into sys_industry(id,code,business_form,first,third,fourth,status,remark,max_price,interest_rate,pos_usage) VALUES (#{id},#{code},#{businessForm},#{first},#{third},#{fourth},#{status},#{remark},#{maxPrice},#{interestRate},#{posUsage})")
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
    @Results({@Result( column = "business_form",property = "businessForm"),@Result( column = "max_price",property = "maxPrice"),@Result( column = "interest_rate",property = "interestRate"),@Result( column = "pos_usage",property = "posUsage") })
    @Select("select * FROM sys_industry")
    public List<IndustryDO> queryAll();
    
    @Results({@Result( column = "business_form",property = "businessForm") })
    @SelectProvider(type = IndustryProvider.class, method = "pageNameList")
    public List<IndustryDO> pageNameList(@Param("industry") IndustryDO industry, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = IndustryProvider.class, method = "pageNameListCount")
    public Integer pageNameListCount(@Param("industry") IndustryDO industry);
    
    /**
     * 按照code查询行业信息
     * @param code
     * @return
     */
    @Results({@Result( column = "business_form",property = "businessForm"),@Result( column = "max_price",property = "maxPrice"),@Result( column = "interest_rate",property = "interestRate"),@Result( column = "pos_usage",property = "posUsage") })
    @Select("SELECT * FROM sys_industry WHERE code = #{code} limit 1")
    public IndustryDO getByCode(@Param("code") String code);
    
    /**
     * 根据entityInnerCode查询行业信息
     * @param entityInnerCode
     * @return
     */
    @Results({@Result( column = "business_form",property = "businessForm"),@Result( column = "max_price",property = "maxPrice"),@Result( column = "interest_rate",property = "interestRate"),@Result( column = "pos_usage",property = "posUsage") })
    @Select("SELECT * FROM sys_industry WHERE `code` = (  SELECT industry_code FROM m_merchant_entity WHERE entity_inner_code= #{entityInnerCode} limit 1)")
    public IndustryDO getByEntityInnerCode(@Param("entityInnerCode") String entityInnerCode);
}