package net.fnsco.risk.service.product.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.product.entity.ProductDO;
import net.fnsco.risk.service.product.dao.helper.ProductProvider;

import java.util.List;;

public interface ProductDAO {

    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "amount_min",property = "amountMin"),@Result( column = "amount_max",property = "amountMax"),@Result( column = "rate_min",property = "rateMin"),@Result( column = "rate_max",property = "rateMax"),@Result( column = "pay_ability_min",property = "payAbilityMin"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @Select("SELECT * FROM risk_product WHERE id = #{id}")
    public ProductDO getById(@Param("id") int id);

    @Insert("INSERT into risk_product(id,agent_id,name,amount_min,amount_max,rate_min,rate_max,cycle,description,pay_ability_min,create_time,last_modify_time,status) VALUES (#{id},#{agentId},#{name},#{amountMin},#{amountMax},#{rateMin},#{rateMax},#{cycle},#{desc},#{payAbilityMin},#{createTime},#{lastModifyTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ProductDO product);

    @Delete("DELETE FROM risk_product WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProductProvider.class, method = "update")
    public int update(@Param("product") ProductDO  product);

    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "amount_min",property = "amountMin"),@Result( column = "amount_max",property = "amountMax"),@Result( column = "rate_min",property = "rateMin"),@Result( column = "rate_max",property = "rateMax"),@Result( column = "pay_ability_min",property = "payAbilityMin"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @SelectProvider(type = ProductProvider.class, method = "pageList")
    public List<ProductDO> pageList(@Param("product") ProductDO product, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProductProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("product") ProductDO product);

}