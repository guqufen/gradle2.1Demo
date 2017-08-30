package net.fnsco.withhold.service.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.withhold.service.trade.dao.helper.ProductTypeProvider;
import net.fnsco.withhold.service.trade.dao.helper.TradeDataProvider;
import net.fnsco.withhold.service.trade.entity.ProductTypeDO;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;

public interface ProductTypeDAO {
    @Results({
        @Result(column = "id", property = "id"),@Result(column = "status", property = "status"),
        @Result(column = "name", property = "name"),@Result(column = "modify_time", property = "modifyTime")
    })
    @Select("SELECT * FROM w_product_type WHERE status=1")
    public List<ProductTypeDO> query();
    
    @Results({
        @Result(column = "id", property = "id"),@Result(column = "status", property = "status"),
        @Result(column = "name", property = "name"),@Result(column = "modify_time", property = "modifyTime")
    })
    @Select("SELECT * FROM w_product_type WHERE id = #{id}")
    public ProductTypeDO getById(@Param("id") int id);
    
    @Insert("INSERT into w_product_type(id,name,status,modify_time) VALUES (#{id},#{name},#{status},#{modifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(ProductTypeDO productTypeDO);
    
    @UpdateProvider(type = ProductTypeProvider.class, method = "update")
    public int update(@Param("productType") ProductTypeDO productType);
    
    
    @Results({
        @Result(column = "id", property = "id"),
        @Result(column = "name", property = "name"), @Result(column = "status", property = "status"),@Result(column = "modify_time", property = "modifyTime")
    })
    @SelectProvider(type = ProductTypeProvider.class, method = "pageList")
    public List<ProductTypeDO> pageList(@Param("productType") ProductTypeDO productType, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProductTypeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("productType") ProductTypeDO productType);
}
