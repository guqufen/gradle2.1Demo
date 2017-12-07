package net.fnsco.trading.service.appUser.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.appUser.entity.AppUserDO;
import net.fnsco.trading.service.appUser.dao.helper.AppUserProvider;

import java.util.List;;

public interface AppUserDAO {

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "id_card_number",property = "idCardNumber"),@Result( column = "real_name",property = "realName"),@Result( column = "device_id",property = "deviceId"),@Result( column = "ges_state",property = "gesState"),@Result( column = "ges_password",property = "gesPassword"),@Result( column = "ges_trail",property = "gesTrail"),@Result( column = "pay_password",property = "payPassword"),@Result( column = "device_type",property = "deviceType"),@Result( column = "device_token",property = "deviceToken"),@Result( column = "password_error_num",property = "passwordErrorNum"),@Result( column = "password_error_date",property = "passwordErrorDate"),@Result( column = "reg_time",property = "regTime"),@Result( column = "last_login_time",property = "lastLoginTime"),@Result( column = "modify_time",property = "modifyTime"),@Result( column = "forced_login_out",property = "forcedLoginOut"),@Result( column = "head_image_path",property = "headImagePath"),@Result( column = "invite_entity_innner_code",property = "inviteEntityInnnerCode"),@Result( column = "invite_status",property = "inviteStatus") })
    @Select("SELECT * FROM u_app_user WHERE id = #{id}")
    public AppUserDO getById(@Param("id") int id);

    @Insert("INSERT into u_app_user(id,user_name,mobile,password,id_card_number,real_name,device_id,ges_state,ges_password,ges_trail,pay_password,device_type,device_token,password_error_num,password_error_date,remark,reg_time,last_login_time,modify_time,state,forced_login_out,sex,head_image_path,invite_entity_innner_code,invite_status) VALUES (#{id},#{userName},#{mobile},#{password},#{idCardNumber},#{realName},#{deviceId},#{gesState},#{gesPassword},#{gesTrail},#{payPassword},#{deviceType},#{deviceToken},#{passwordErrorNum},#{passwordErrorDate},#{remark},#{regTime},#{lastLoginTime},#{modifyTime},#{state},#{forcedLoginOut},#{sex},#{headImagePath},#{inviteEntityInnnerCode},#{inviteStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppUserDO appUser);

    @Delete("DELETE FROM u_app_user WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppUserProvider.class, method = "update")
    public int update(@Param("appUser") AppUserDO  appUser);

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "id_card_number",property = "idCardNumber"),@Result( column = "real_name",property = "realName"),@Result( column = "device_id",property = "deviceId"),@Result( column = "ges_state",property = "gesState"),@Result( column = "ges_password",property = "gesPassword"),@Result( column = "ges_trail",property = "gesTrail"),@Result( column = "pay_password",property = "payPassword"),@Result( column = "device_type",property = "deviceType"),@Result( column = "device_token",property = "deviceToken"),@Result( column = "password_error_num",property = "passwordErrorNum"),@Result( column = "password_error_date",property = "passwordErrorDate"),@Result( column = "reg_time",property = "regTime"),@Result( column = "last_login_time",property = "lastLoginTime"),@Result( column = "modify_time",property = "modifyTime"),@Result( column = "forced_login_out",property = "forcedLoginOut"),@Result( column = "head_image_path",property = "headImagePath"),@Result( column = "invite_entity_innner_code",property = "inviteEntityInnnerCode"),@Result( column = "invite_status",property = "inviteStatus") })
    @SelectProvider(type = AppUserProvider.class, method = "pageList")
    public List<AppUserDO> pageList(@Param("appUser") AppUserDO appUser, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppUserProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appUser") AppUserDO appUser);

}