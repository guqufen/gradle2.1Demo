package net.fnsco.bigdata.service.bank.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.bigdata.service.bank.entity.AppUserBankDO;
public class AppUserBankProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "u_app_user_bank";

    public String update(Map<String, Object> params) {
        AppUserBankDO appUserBank = (AppUserBankDO) params.get("appUserBank");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(appUserBank.getAppUserId())){
            SET("app_user_id=#{appUserBank.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountType())){
            SET("account_type=#{appUserBank.accountType}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountNo())){
            SET("account_no=#{appUserBank.accountNo}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountName())){
            SET("account_name=#{appUserBank.accountName}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountCardId())){
            SET("account_card_id=#{appUserBank.accountCardId}");
        }
        if (StringUtils.isNotBlank(appUserBank.getSubBankName())){
            SET("sub_bank_name=#{appUserBank.subBankName}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankPrince())){
            SET("open_bank_prince=#{appUserBank.openBankPrince}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBank())){
            SET("open_bank=#{appUserBank.openBank}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankCity())){
            SET("open_bank_city=#{appUserBank.openBankCity}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankNum())){
            SET("open_bank_num=#{appUserBank.openBankNum}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountPhone())){
            SET("account_phone=#{appUserBank.accountPhone}");
        }
        WHERE("id = #{appUserBank.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppUserBankDO appUserBank = (AppUserBankDO) params.get("appUserBank");
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
        if (appUserBank.getId() != null) {
            WHERE("id=#{appUserBank.id}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAppUserId())){
            WHERE("app_user_id=#{appUserBank.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountType())){
            WHERE("account_type=#{appUserBank.accountType}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountNo())){
            WHERE("account_no=#{appUserBank.accountNo}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountName())){
            WHERE("account_name=#{appUserBank.accountName}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountCardId())){
            WHERE("account_card_id=#{appUserBank.accountCardId}");
        }
        if (StringUtils.isNotBlank(appUserBank.getSubBankName())){
            WHERE("sub_bank_name=#{appUserBank.subBankName}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankPrince())){
            WHERE("open_bank_prince=#{appUserBank.openBankPrince}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBank())){
            WHERE("open_bank=#{appUserBank.openBank}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankCity())){
            WHERE("open_bank_city=#{appUserBank.openBankCity}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankNum())){
            WHERE("open_bank_num=#{appUserBank.openBankNum}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountPhone())){
            WHERE("account_phone=#{appUserBank.accountPhone}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppUserBankDO appUserBank = (AppUserBankDO) params.get("appUserBank");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appUserBank.getId() != null) {
            WHERE("id=#{appUserBank.id}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAppUserId())){
            WHERE("app_user_id=#{appUserBank.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountType())){
            WHERE("account_type=#{appUserBank.accountType}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountNo())){
            WHERE("account_no=#{appUserBank.accountNo}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountName())){
            WHERE("account_name=#{appUserBank.accountName}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountCardId())){
            WHERE("account_card_id=#{appUserBank.accountCardId}");
        }
        if (StringUtils.isNotBlank(appUserBank.getSubBankName())){
            WHERE("sub_bank_name=#{appUserBank.subBankName}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankPrince())){
            WHERE("open_bank_prince=#{appUserBank.openBankPrince}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBank())){
            WHERE("open_bank=#{appUserBank.openBank}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankCity())){
            WHERE("open_bank_city=#{appUserBank.openBankCity}");
        }
        if (StringUtils.isNotBlank(appUserBank.getOpenBankNum())){
            WHERE("open_bank_num=#{appUserBank.openBankNum}");
        }
        if (StringUtils.isNotBlank(appUserBank.getAccountPhone())){
            WHERE("account_phone=#{appUserBank.accountPhone}");
        }
        }}.toString();
    }
}

