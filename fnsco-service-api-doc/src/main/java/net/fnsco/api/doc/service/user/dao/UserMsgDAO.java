package net.fnsco.api.doc.service.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.api.doc.service.user.entity.UserMsgDO;
import net.fnsco.api.doc.service.user.dao.helper.UserMsgProvider;

import java.util.List;;

public interface UserMsgDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "sys_msg_id",property = "sysMsgId"),@Result( column = "sender_id",property = "senderId"),@Result( column = "receiver_id",property = "receiverId"),@Result( column = "pub_date",property = "pubDate"),@Result( column = "msg_type",property = "msgType"),@Result( column = "deal_date",property = "dealDate") })
    @Select("SELECT * FROM t_user_msg WHERE id = #{id}")
    public UserMsgDO getById(@Param("id") int id);

    @Insert("INSERT into t_user_msg(id,create_date,modify_date,sys,sys_msg_id,sender_id,receiver_id,pub_date,title,content,msg_type,deal,deal_date) VALUES (#{id},#{createDate},#{modifyDate},#{sys},#{sysMsgId},#{senderId},#{receiverId},#{pubDate},#{title},#{content},#{msgType},#{deal},#{dealDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserMsgDO userMsg);

    @Delete("DELETE FROM t_user_msg WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserMsgProvider.class, method = "update")
    public int update(@Param("userMsg") UserMsgDO  userMsg);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "sys_msg_id",property = "sysMsgId"),@Result( column = "sender_id",property = "senderId"),@Result( column = "receiver_id",property = "receiverId"),@Result( column = "pub_date",property = "pubDate"),@Result( column = "msg_type",property = "msgType"),@Result( column = "deal_date",property = "dealDate") })
    @SelectProvider(type = UserMsgProvider.class, method = "pageList")
    public List<UserMsgDO> pageList(@Param("userMsg") UserMsgDO userMsg, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserMsgProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userMsg") UserMsgDO userMsg);

}