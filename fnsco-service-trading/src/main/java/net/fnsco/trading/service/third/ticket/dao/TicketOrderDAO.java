package net.fnsco.trading.service.third.ticket.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.third.ticket.dao.helper.TicketOrderProvider;

import java.util.List;;

public interface TicketOrderDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "train_date",property = "trainDate"),@Result( column = "from_station_code",property = "fromStationCode"),@Result( column = "from_station_name",property = "fromStationName"),@Result( column = "to_station_code",property = "toStationCode"),@Result( column = "to_station_name",property = "toStationName"),@Result( column = "train_code",property = "trainCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @Select("SELECT * FROM thr_ticket_order WHERE id = #{id}")
    public TicketOrderDO getById(@Param("id") int id);

    @Insert("INSERT into thr_ticket_order(id,order_no,train_date,from_station_code,from_station_name,to_station_code,to_station_name,train_code,status,create_time,last_modify_time) VALUES (#{id},#{orderNo},#{trainDate},#{fromStationCode},#{fromStationName},#{toStationCode},#{toStationName},#{trainCode},#{status},#{createTime},#{lastModifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TicketOrderDO ticketOrder);

    @Delete("DELETE FROM thr_ticket_order WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TicketOrderProvider.class, method = "update")
    public int update(@Param("ticketOrder") TicketOrderDO  ticketOrder);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "train_date",property = "trainDate"),@Result( column = "from_station_code",property = "fromStationCode"),@Result( column = "from_station_name",property = "fromStationName"),@Result( column = "to_station_code",property = "toStationCode"),@Result( column = "to_station_name",property = "toStationName"),@Result( column = "train_code",property = "trainCode"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @SelectProvider(type = TicketOrderProvider.class, method = "pageList")
    public List<TicketOrderDO> pageList(@Param("ticketOrder") TicketOrderDO ticketOrder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TicketOrderProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("ticketOrder") TicketOrderDO ticketOrder);

}