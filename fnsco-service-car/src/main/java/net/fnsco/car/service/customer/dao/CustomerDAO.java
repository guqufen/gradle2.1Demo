package net.fnsco.car.service.customer.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.car.service.customer.dao.helper.CustomerProvider;
import net.fnsco.car.service.customer.entity.CustomerDO;

import java.util.List;;

public interface CustomerDAO {

    @Results({@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM car_customer WHERE id = #{id}")
    public CustomerDO getById(@Param("id") int id);

    @Insert("INSERT into car_customer(id,name,mobile,create_time) VALUES (#{id},#{name},#{mobile},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(CustomerDO customer);

    @Delete("DELETE FROM car_customer WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = CustomerProvider.class, method = "update")
    public int update(@Param("customer") CustomerDO  customer);

    @Results({@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = CustomerProvider.class, method = "pageList")
    public List<CustomerDO> pageList(@Param("customer") CustomerDO customer, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = CustomerProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("customer") CustomerDO customer);

  

}