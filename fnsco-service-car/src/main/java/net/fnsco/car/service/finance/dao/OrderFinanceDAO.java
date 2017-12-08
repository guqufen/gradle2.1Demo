package net.fnsco.car.service.finance.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.finance.entity.OrderFinanceDO;
import net.fnsco.car.service.finance.dao.helper.OrderFinanceProvider;

import java.util.List;;

public interface OrderFinanceDAO {

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "buy_type",property = "buyType"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @Select("SELECT * FROM car_order_finance WHERE id = #{id}")
    public OrderFinanceDO getById(@Param("id") int id);

    @Insert("INSERT into car_order_finance(id,customer_id,city_id,buy_type,suggest_code,create_time,last_update_time,status) VALUES (#{id},#{customerId},#{cityId},#{buyType},#{suggestCode},#{createTime},#{lastUpdateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OrderFinanceDO orderFinance);

    @Delete("DELETE FROM car_order_finance WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OrderFinanceProvider.class, method = "update")
    public int update(@Param("orderFinance") OrderFinanceDO  orderFinance);

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "buy_type",property = "buyType"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @SelectProvider(type = OrderFinanceProvider.class, method = "pageList")
    public List<OrderFinanceDO> pageList(@Param("orderFinance") OrderFinanceDO orderFinance, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OrderFinanceProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("orderFinance") OrderFinanceDO orderFinance);

}