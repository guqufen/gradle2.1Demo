package net.fnsco.bigdata.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.bigdata.service.sys.dao.helper.PayCategroryWxProvider;
import net.fnsco.bigdata.service.sys.entity.PayCategroryWxDO;

import java.util.List;;

public interface PayCategroryWxDAO {

    @Results({@Result( column = "etps_attr",property = "etpsAttr"),@Result( column = "group_id",property = "groupId"),@Result( column = "group_name",property = "groupName"),@Result( column = "categrory_id",property = "categroryId"),@Result( column = "categrory_name",property = "categroryName") })
    @Select("SELECT * FROM sys_pay_categrory_wx WHERE id = #{id}")
    public PayCategroryWxDO getById(@Param("id") int id);

    @Insert("INSERT into sys_pay_categrory_wx(id,etps_attr,group_id,group_name,categrory_id,categrory_name) VALUES (#{id},#{etpsAttr},#{groupId},#{groupName},#{categroryId},#{categroryName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(PayCategroryWxDO payCategroryWx);

    @Delete("DELETE FROM sys_pay_categrory_wx WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = PayCategroryWxProvider.class, method = "update")
    public int update(@Param("payCategroryWx") PayCategroryWxDO  payCategroryWx);

    @Results({@Result( column = "etps_attr",property = "etpsAttr"),@Result( column = "group_id",property = "groupId"),@Result( column = "group_name",property = "groupName"),@Result( column = "categrory_id",property = "categroryId"),@Result( column = "categrory_name",property = "categroryName") })
    @SelectProvider(type = PayCategroryWxProvider.class, method = "pageList")
    public List<PayCategroryWxDO> pageList(@Param("payCategroryWx") PayCategroryWxDO payCategroryWx, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = PayCategroryWxProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("payCategroryWx") PayCategroryWxDO payCategroryWx);

    @Results({@Result( column = "group_id",property = "groupId"),@Result( column = "group_name",property = "groupName")})
    @Select("SELECT DISTINCT(group_id) group_id,group_name FROM sys_pay_categrory_wx WHERE etps_attr like concat(concat('%',#{etpsAttrStr}),'%')  ")
	public List<PayCategroryWxDO> getFirstCategrotyListByEtpAttr(@Param("etpsAttrStr")  String etpsAttrStr);
    
    @Results({@Result( column = "categrory_id",property = "categroryId"),@Result( column = "categrory_name",property = "categroryName")})
    @Select("SELECT categrory_id,categrory_name FROM sys_pay_categrory_wx Where etps_attr like concat(concat('%',#{etpsAttrStr}),'%')  and group_id=#{group_id} ")
	public List<PayCategroryWxDO> getSecondCategrotyListByContion(@Param("etpsAttrStr") String etpsAttrStr,@Param("group_id")  Integer group_id);

}