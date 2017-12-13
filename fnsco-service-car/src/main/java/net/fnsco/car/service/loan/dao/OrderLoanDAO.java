package net.fnsco.car.service.loan.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.loan.entity.OrderLoanDO;
import net.fnsco.car.service.loan.dao.helper.OrderLoanProvider;

import java.util.List;;

public interface OrderLoanDAO {

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @Select("SELECT * FROM car_order_loan WHERE id = #{id}")
    public OrderLoanDO getById(@Param("id") int id);

    @Insert("INSERT into car_order_loan(id,customer_id,city_id,amount,suggest_code,create_time,last_update_time,status,car_type_id,car_sub_type_id) VALUES (#{id},#{customerId},#{cityId},#{amount},#{suggestCode},#{createTime},#{lastUpdateTime},#{status},#{carTypeId},#{carSubTypeId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OrderLoanDO orderLoan);

    @Delete("DELETE FROM car_order_loan WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OrderLoanProvider.class, method = "update")
    public int update(@Param("orderLoan") OrderLoanDO  orderLoan);

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @SelectProvider(type = OrderLoanProvider.class, method = "pageList")
    public List<OrderLoanDO> pageList(@Param("orderLoan") OrderLoanDO orderLoan, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OrderLoanProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("orderLoan") OrderLoanDO orderLoan);

}