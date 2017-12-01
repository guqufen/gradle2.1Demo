package net.fnsco.api.doc.service.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.api.doc.service.project.dao.helper.EmailProvider;
import net.fnsco.api.doc.service.project.entity.EmailDO;

import java.util.List;;

public interface EmailDAO {

    @Results({@Result( column = "email_name",property = "emailName"),@Result( column = "role_type",property = "roleType"),@Result( column = "other_subject",property = "otherSubject"),@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId") })
    @Select("SELECT * FROM t_email WHERE id = #{id}")
    public EmailDO getById(@Param("id") int id);

    @Insert("INSERT into t_email(id,email_name,subject,role_type,other_subject,content,create_date,modify_date,user_id) VALUES (#{id},#{emailName},#{subject},#{roleType},#{otherSubject},#{content},#{createDate},#{modifyDate},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(EmailDO email);

    @Delete("DELETE FROM t_email WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = EmailProvider.class, method = "update")
    public int update(@Param("email") EmailDO  email);

    @Results({@Result( column = "email_name",property = "emailName"),@Result( column = "role_type",property = "roleType"),@Result( column = "other_subject",property = "otherSubject"),@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId") })
    @SelectProvider(type = EmailProvider.class, method = "pageList")
    public List<EmailDO> pageList(@Param("email") EmailDO email, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = EmailProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("email") EmailDO email);

}