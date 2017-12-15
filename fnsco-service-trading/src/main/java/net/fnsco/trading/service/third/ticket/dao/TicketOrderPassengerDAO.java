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
import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;
import net.fnsco.trading.service.third.ticket.dao.helper.TicketOrderPassengerProvider;

import java.util.List;;

public interface TicketOrderPassengerDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "passenger_id",property = "passengerId"),@Result( column = "passenger_name",property = "passengerName"),@Result( column = "ticket_type",property = "ticketType"),@Result( column = "ticket_type_name",property = "ticketTypeName"),@Result( column = "card_type_id",property = "cardTypeId"),@Result( column = "card_type_name",property = "cardTypeName"),@Result( column = "card_num",property = "cardNum"),@Result( column = "seat_code",property = "seatCode"),@Result( column = "seat_name",property = "seatName"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @Select("SELECT * FROM thr_ticket_order_passenger WHERE id = #{id}")
    public TicketOrderPassengerDO getById(@Param("id") int id);

    @Insert("INSERT into thr_ticket_order_passenger(id,order_no,passenger_id,passenger_name,ticket_type,ticket_type_name,card_type_id,card_type_name,card_num,price,seat_code,seat_name,status,create_time,last_modify_time) VALUES (#{id},#{orderNo},#{passengerId},#{passengerName},#{ticketType},#{ticketTypeName},#{cardTypeId},#{cardTypeName},#{cardNum},#{price},#{seatCode},#{seatName},#{status},#{createTime},#{lastModifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TicketOrderPassengerDO ticketOrderPassenger);

    @Delete("DELETE FROM thr_ticket_order_passenger WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TicketOrderPassengerProvider.class, method = "update")
    public int update(@Param("ticketOrderPassenger") TicketOrderPassengerDO  ticketOrderPassenger);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "passenger_id",property = "passengerId"),@Result( column = "passenger_name",property = "passengerName"),@Result( column = "ticket_type",property = "ticketType"),@Result( column = "ticket_type_name",property = "ticketTypeName"),@Result( column = "card_type_id",property = "cardTypeId"),@Result( column = "card_type_name",property = "cardTypeName"),@Result( column = "card_num",property = "cardNum"),@Result( column = "seat_code",property = "seatCode"),@Result( column = "seat_name",property = "seatName"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @SelectProvider(type = TicketOrderPassengerProvider.class, method = "pageList")
    public List<TicketOrderPassengerDO> pageList(@Param("ticketOrderPassenger") TicketOrderPassengerDO ticketOrderPassenger, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TicketOrderPassengerProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("ticketOrderPassenger") TicketOrderPassengerDO ticketOrderPassenger);

}