package net.fnsco.car.service.buy.dao;

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

import net.fnsco.car.service.buy.dao.helper.OrderBuyProvider;
import net.fnsco.car.service.buy.entity.OrderBuyDO;;

public interface OrderBuyDAO {

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "car_type_id",property = "carTypeId"),@Result( column = "car_model",property = "carModel"),@Result( column = "buy_type",property = "buyType"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @Select("SELECT * FROM car_order_buy WHERE id = #{id}")
    public OrderBuyDO getById(@Param("id") int id);

    @Insert("INSERT into car_order_buy(id,customer_id,city_id,car_type_id,car_sub_type_id,buy_type,suggest_code,create_time,last_update_time,status) VALUES (#{id},#{customerId},#{cityId},#{carTypeId},#{carSubTypeId},#{buyType},#{suggestCode},#{createTime},#{lastUpdateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OrderBuyDO orderBuy);

    @Delete("DELETE FROM car_order_buy WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OrderBuyProvider.class, method = "update")
    public int update(@Param("orderBuy") OrderBuyDO  orderBuy);

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "car_type_id",property = "carTypeId"),@Result( column = "car_model",property = "carModel"),@Result( column = "buy_type",property = "buyType"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @SelectProvider(type = OrderBuyProvider.class, method = "pageList")
    public List<OrderBuyDO> pageList(@Param("orderBuy") OrderBuyDO orderBuy, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OrderBuyProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("orderBuy") OrderBuyDO orderBuy);

}