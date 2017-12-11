package net.fnsco.car.service.safe.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.safe.entity.OrderSafeDO;
import net.fnsco.car.service.safe.dao.helper.OrderSafeProvider;

import java.util.List;;

public interface OrderSafeDAO {

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "car_original_price",property = "carOriginalPrice"),@Result( column = "insu_company_id",property = "insuCompanyId"),@Result( column = "esti_premiums",property = "estiPremiums"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @Select("SELECT * FROM car_order_safe WHERE id = #{id}")
    public OrderSafeDO getById(@Param("id") int id);

    @Insert("INSERT into car_order_safe(id,customer_id,city_id,car_original_price,insu_company_id,esti_premiums,suggest_code,create_time,last_update_time,status) VALUES (#{id},#{customerId},#{cityId},#{carOriginalPrice},#{insuCompanyId},#{estiPremiums},#{suggestCode},#{createTime},#{lastUpdateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OrderSafeDO orderSafe);

    @Delete("DELETE FROM car_order_safe WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OrderSafeProvider.class, method = "update")
    public int update(@Param("orderSafe") OrderSafeDO  orderSafe);

    @Results({@Result( column = "customer_id",property = "customerId"),@Result( column = "city_id",property = "cityId"),@Result( column = "car_original_price",property = "carOriginalPrice"),@Result( column = "insu_company_id",property = "insuCompanyId"),@Result( column = "esti_premiums",property = "estiPremiums"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_update_time",property = "lastUpdateTime") })
    @SelectProvider(type = OrderSafeProvider.class, method = "pageList")
    public List<OrderSafeDO> pageList(@Param("orderSafe") OrderSafeDO orderSafe, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OrderSafeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("orderSafe") OrderSafeDO orderSafe);

}