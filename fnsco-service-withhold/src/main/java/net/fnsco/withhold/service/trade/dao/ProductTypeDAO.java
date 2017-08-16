package net.fnsco.withhold.service.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import net.fnsco.withhold.service.trade.entity.ProductTypeDO;

public interface ProductTypeDAO {
    @Results({
        @Result(column = "id", property = "id"),@Result(column = "code", property = "code"),
        @Result(column = "name", property = "name")
    })
    @Select("SELECT * FROM w_product_type")
    public List<ProductTypeDO> query();
    
    
    @Results({
        @Result(column = "name", property = "name")
    })
    @Select("SELECT * FROM w_product_type WHERE code=#{code}")
    public ProductTypeDO getByCode(@Param("code") String code);
}
