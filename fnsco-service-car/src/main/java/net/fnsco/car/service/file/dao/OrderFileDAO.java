package net.fnsco.car.service.file.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.file.entity.OrderFileDO;
import net.fnsco.car.service.file.dao.helper.OrderFileProvider;

import java.util.List;;

public interface OrderFileDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM car_order_file WHERE id = #{id}")
    public OrderFileDO getById(@Param("id") int id);

    @Insert("INSERT into car_order_file(id,order_no,file_name,file_type,file_path,create_time) VALUES (#{id},#{orderNo},#{fileName},#{fileType},#{filePath},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OrderFileDO orderFile);

    @Delete("DELETE FROM car_order_file WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OrderFileProvider.class, method = "update")
    public int update(@Param("orderFile") OrderFileDO  orderFile);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = OrderFileProvider.class, method = "pageList")
    public List<OrderFileDO> pageList(@Param("orderFile") OrderFileDO orderFile, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OrderFileProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("orderFile") OrderFileDO orderFile);
    
    /**
     * getByOrderNo:(根据orderNo查找)
     *
     * @param  @param orderNo
     * @param  @return    设定文件
     * @return List<OrderFileDO>    DOM对象
     * @author tangliang
     * @date   2017年12月25日 上午11:35:32
     */
    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM car_order_file WHERE id = #{id}")
    public List<OrderFileDO> getByOrderNo(@Param("orderNo") String orderNo);

}