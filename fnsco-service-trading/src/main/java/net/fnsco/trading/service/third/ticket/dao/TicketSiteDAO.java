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
import net.fnsco.trading.service.third.ticket.entity.TicketSiteDO;
import net.fnsco.trading.service.third.ticket.dao.helper.TicketSiteProvider;

import java.util.List;;

public interface TicketSiteDAO {

    @Results({@Result( column = "py_name",property = "pyName"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @Select("SELECT * FROM thr_ticket_site WHERE id = #{id}")
    public TicketSiteDO getById(@Param("id") int id);

    @Insert("INSERT into thr_ticket_site(id,name,py_name,code,create_time,last_modify_time) VALUES (#{id},#{name},#{pyName},#{code},#{createTime},#{lastModifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TicketSiteDO ticketSite);

    @Delete("DELETE FROM thr_ticket_site WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TicketSiteProvider.class, method = "update")
    public int update(@Param("ticketSite") TicketSiteDO  ticketSite);

    @Results({@Result( column = "py_name",property = "pyName"),@Result( column = "create_time",property = "createTime"),@Result( column = "last_modify_time",property = "lastModifyTime") })
    @SelectProvider(type = TicketSiteProvider.class, method = "pageList")
    public List<TicketSiteDO> pageList(@Param("ticketSite") TicketSiteDO ticketSite, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TicketSiteProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("ticketSite") TicketSiteDO ticketSite);

}