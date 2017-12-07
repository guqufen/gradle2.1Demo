package net.fnsco.trading.service.appUser.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.appUser.entity.AppUserDO;
public class AppUserProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "u_app_user";

    public String update(Map<String, Object> params) {
        AppUserDO appUser = (AppUserDO) params.get("appUser");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(appUser.getUserName())){
            SET("user_name=#{appUser.userName}");
        }
        if (StringUtils.isNotBlank(appUser.getMobile())){
            SET("mobile=#{appUser.mobile}");
        }
        if (StringUtils.isNotBlank(appUser.getPassword())){
            SET("password=#{appUser.password}");
        }
        if (StringUtils.isNotBlank(appUser.getIdCardNumber())){
            SET("id_card_number=#{appUser.idCardNumber}");
        }
        if (StringUtils.isNotBlank(appUser.getRealName())){
            SET("real_name=#{appUser.realName}");
        }
        if (StringUtils.isNotBlank(appUser.getDeviceId())){
            SET("device_id=#{appUser.deviceId}");
        }
        if (appUser.getGesState() != null) {
            SET("ges_state=#{appUser.gesState}");
        }
        if (StringUtils.isNotBlank(appUser.getGesPassword())){
            SET("ges_password=#{appUser.gesPassword}");
        }
        if (appUser.getGesTrail() != null) {
            SET("ges_trail=#{appUser.gesTrail}");
        }
        if (StringUtils.isNotBlank(appUser.getPayPassword())){
            SET("pay_password=#{appUser.payPassword}");
        }
        if (appUser.getDeviceType() != null) {
            SET("device_type=#{appUser.deviceType}");
        }
        if (StringUtils.isNotBlank(appUser.getDeviceToken())){
            SET("device_token=#{appUser.deviceToken}");
        }
        if (appUser.getPasswordErrorNum() != null) {
            SET("password_error_num=#{appUser.passwordErrorNum}");
        }
        if (appUser.getPasswordErrorDate() != null) {
            SET("password_error_date=#{appUser.passwordErrorDate}");
        }
        if (StringUtils.isNotBlank(appUser.getRemark())){
            SET("remark=#{appUser.remark}");
        }
        if (appUser.getRegTime() != null) {
            SET("reg_time=#{appUser.regTime}");
        }
        if (appUser.getLastLoginTime() != null) {
            SET("last_login_time=#{appUser.lastLoginTime}");
        }
        if (appUser.getModifyTime() != null) {
            SET("modify_time=#{appUser.modifyTime}");
        }
        if (appUser.getState() != null) {
            SET("state=#{appUser.state}");
        }
        if (appUser.getForcedLoginOut() != null) {
            SET("forced_login_out=#{appUser.forcedLoginOut}");
        }
        if (appUser.getSex() != null) {
            SET("sex=#{appUser.sex}");
        }
        if (StringUtils.isNotBlank(appUser.getHeadImagePath())){
            SET("head_image_path=#{appUser.headImagePath}");
        }
        if (StringUtils.isNotBlank(appUser.getInviteEntityInnnerCode())){
            SET("invite_entity_innner_code=#{appUser.inviteEntityInnnerCode}");
        }
        if (appUser.getInviteStatus() != null) {
            SET("invite_status=#{appUser.inviteStatus}");
        }
        WHERE("id = #{appUser.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppUserDO appUser = (AppUserDO) params.get("appUser");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (appUser.getId() != null) {
            WHERE("id=#{appUser.id}");
        }
        if (StringUtils.isNotBlank(appUser.getUserName())){
            WHERE("user_name=#{appUser.userName}");
        }
        if (StringUtils.isNotBlank(appUser.getMobile())){
            WHERE("mobile=#{appUser.mobile}");
        }
        if (StringUtils.isNotBlank(appUser.getPassword())){
            WHERE("password=#{appUser.password}");
        }
        if (StringUtils.isNotBlank(appUser.getIdCardNumber())){
            WHERE("id_card_number=#{appUser.idCardNumber}");
        }
        if (StringUtils.isNotBlank(appUser.getRealName())){
            WHERE("real_name=#{appUser.realName}");
        }
        if (StringUtils.isNotBlank(appUser.getDeviceId())){
            WHERE("device_id=#{appUser.deviceId}");
        }
        if (appUser.getGesState() != null) {
            WHERE("ges_state=#{appUser.gesState}");
        }
        if (StringUtils.isNotBlank(appUser.getGesPassword())){
            WHERE("ges_password=#{appUser.gesPassword}");
        }
        if (appUser.getGesTrail() != null) {
            WHERE("ges_trail=#{appUser.gesTrail}");
        }
        if (StringUtils.isNotBlank(appUser.getPayPassword())){
            WHERE("pay_password=#{appUser.payPassword}");
        }
        if (appUser.getDeviceType() != null) {
            WHERE("device_type=#{appUser.deviceType}");
        }
        if (StringUtils.isNotBlank(appUser.getDeviceToken())){
            WHERE("device_token=#{appUser.deviceToken}");
        }
        if (appUser.getPasswordErrorNum() != null) {
            WHERE("password_error_num=#{appUser.passwordErrorNum}");
        }
        if (appUser.getPasswordErrorDate() != null) {
            WHERE("password_error_date=#{appUser.passwordErrorDate}");
        }
        if (StringUtils.isNotBlank(appUser.getRemark())){
            WHERE("remark=#{appUser.remark}");
        }
        if (appUser.getRegTime() != null) {
            WHERE("reg_time=#{appUser.regTime}");
        }
        if (appUser.getLastLoginTime() != null) {
            WHERE("last_login_time=#{appUser.lastLoginTime}");
        }
        if (appUser.getModifyTime() != null) {
            WHERE("modify_time=#{appUser.modifyTime}");
        }
        if (appUser.getState() != null) {
            WHERE("state=#{appUser.state}");
        }
        if (appUser.getForcedLoginOut() != null) {
            WHERE("forced_login_out=#{appUser.forcedLoginOut}");
        }
        if (appUser.getSex() != null) {
            WHERE("sex=#{appUser.sex}");
        }
        if (StringUtils.isNotBlank(appUser.getHeadImagePath())){
            WHERE("head_image_path=#{appUser.headImagePath}");
        }
        if (StringUtils.isNotBlank(appUser.getInviteEntityInnnerCode())){
            WHERE("invite_entity_innner_code=#{appUser.inviteEntityInnnerCode}");
        }
        if (appUser.getInviteStatus() != null) {
            WHERE("invite_status=#{appUser.inviteStatus}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppUserDO appUser = (AppUserDO) params.get("appUser");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appUser.getId() != null) {
            WHERE("id=#{appUser.id}");
        }
        if (StringUtils.isNotBlank(appUser.getUserName())){
            WHERE("user_name=#{appUser.userName}");
        }
        if (StringUtils.isNotBlank(appUser.getMobile())){
            WHERE("mobile=#{appUser.mobile}");
        }
        if (StringUtils.isNotBlank(appUser.getPassword())){
            WHERE("password=#{appUser.password}");
        }
        if (StringUtils.isNotBlank(appUser.getIdCardNumber())){
            WHERE("id_card_number=#{appUser.idCardNumber}");
        }
        if (StringUtils.isNotBlank(appUser.getRealName())){
            WHERE("real_name=#{appUser.realName}");
        }
        if (StringUtils.isNotBlank(appUser.getDeviceId())){
            WHERE("device_id=#{appUser.deviceId}");
        }
        if (appUser.getGesState() != null) {
            WHERE("ges_state=#{appUser.gesState}");
        }
        if (StringUtils.isNotBlank(appUser.getGesPassword())){
            WHERE("ges_password=#{appUser.gesPassword}");
        }
        if (appUser.getGesTrail() != null) {
            WHERE("ges_trail=#{appUser.gesTrail}");
        }
        if (StringUtils.isNotBlank(appUser.getPayPassword())){
            WHERE("pay_password=#{appUser.payPassword}");
        }
        if (appUser.getDeviceType() != null) {
            WHERE("device_type=#{appUser.deviceType}");
        }
        if (StringUtils.isNotBlank(appUser.getDeviceToken())){
            WHERE("device_token=#{appUser.deviceToken}");
        }
        if (appUser.getPasswordErrorNum() != null) {
            WHERE("password_error_num=#{appUser.passwordErrorNum}");
        }
        if (appUser.getPasswordErrorDate() != null) {
            WHERE("password_error_date=#{appUser.passwordErrorDate}");
        }
        if (StringUtils.isNotBlank(appUser.getRemark())){
            WHERE("remark=#{appUser.remark}");
        }
        if (appUser.getRegTime() != null) {
            WHERE("reg_time=#{appUser.regTime}");
        }
        if (appUser.getLastLoginTime() != null) {
            WHERE("last_login_time=#{appUser.lastLoginTime}");
        }
        if (appUser.getModifyTime() != null) {
            WHERE("modify_time=#{appUser.modifyTime}");
        }
        if (appUser.getState() != null) {
            WHERE("state=#{appUser.state}");
        }
        if (appUser.getForcedLoginOut() != null) {
            WHERE("forced_login_out=#{appUser.forcedLoginOut}");
        }
        if (appUser.getSex() != null) {
            WHERE("sex=#{appUser.sex}");
        }
        if (StringUtils.isNotBlank(appUser.getHeadImagePath())){
            WHERE("head_image_path=#{appUser.headImagePath}");
        }
        if (StringUtils.isNotBlank(appUser.getInviteEntityInnnerCode())){
            WHERE("invite_entity_innner_code=#{appUser.inviteEntityInnnerCode}");
        }
        if (appUser.getInviteStatus() != null) {
            WHERE("invite_status=#{appUser.inviteStatus}");
        }
        }}.toString();
    }
}

