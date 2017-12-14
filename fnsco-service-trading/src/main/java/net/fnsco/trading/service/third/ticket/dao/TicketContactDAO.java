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
import net.fnsco.trading.service.third.ticket.entity.TicketContactDO;
import net.fnsco.trading.service.third.ticket.dao.helper.TicketContactProvider;

import java.util.List;;

public interface TicketContactDAO {

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "card_type",property = "cardType"),@Result( column = "card_num",property = "cardNum"),@Result( column = "ticket_type",property = "ticketType") })
    @Select("SELECT * FROM thr_ticket_contact WHERE id = #{id}")
    public TicketContactDO getById(@Param("id") int id);

    @Insert("INSERT into thr_ticket_contact(id,app_user_id,name,mobile,card_type,card_num,ticket_type) VALUES (#{id},#{appUserId},#{name},#{mobile},#{cardType},#{cardNum},#{ticketType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TicketContactDO ticketContact);

    @Delete("DELETE FROM thr_ticket_contact WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TicketContactProvider.class, method = "update")
    public int update(@Param("ticketContact") TicketContactDO  ticketContact);

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "card_type",property = "cardType"),@Result( column = "card_num",property = "cardNum"),@Result( column = "ticket_type",property = "ticketType") })
    @SelectProvider(type = TicketContactProvider.class, method = "pageList")
    public List<TicketContactDO> pageList(@Param("ticketContact") TicketContactDO ticketContact, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TicketContactProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("ticketContact") TicketContactDO ticketContact);

}