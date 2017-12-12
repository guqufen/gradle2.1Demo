package net.fnsco.car.service.ftemp.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.ftemp.entity.OrderFileTempDO;
import net.fnsco.car.service.ftemp.dao.helper.OrderFileTempProvider;

import java.util.List;;

public interface OrderFileTempDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM car_order_file_temp WHERE id = #{id}")
    public OrderFileTempDO getById(@Param("id") int id);

    @Insert("INSERT into car_order_file_temp(id,order_no,file_name,file_type,file_path,create_time) VALUES (#{id},#{orderNo},#{fileName},#{fileType},#{filePath},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OrderFileTempDO orderFileTemp);

    @Delete("DELETE FROM car_order_file_temp WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OrderFileTempProvider.class, method = "update")
    public int update(@Param("orderFileTemp") OrderFileTempDO  orderFileTemp);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = OrderFileTempProvider.class, method = "pageList")
    public List<OrderFileTempDO> pageList(@Param("orderFileTemp") OrderFileTempDO orderFileTemp, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OrderFileTempProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("orderFileTemp") OrderFileTempDO orderFileTemp);

}