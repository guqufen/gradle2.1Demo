package net.fnsco.withhold.service.withhold.dao.helper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.withhold.service.withhold.entity.WithholdInfoDO;

import org.apache.commons.lang3.StringUtils;
public class WithholdInfoProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "w_withhold_info";

    public String update(Map<String, Object> params) {
        WithholdInfoDO withholdInfo = (WithholdInfoDO) params.get("withholdInfo");
        BEGIN();
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(withholdInfo.getUserName())){
            SET("userName=#{withholdInfo.userName}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getMobile())){
            SET("mobile=#{withholdInfo.mobile}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getCardNum())){
            SET("cardNum=#{withholdInfo.cardNum}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getDebitDay())){
            SET("debitDay=#{withholdInfo.debitDay}");
        }
        if (withholdInfo.getAmount() != null) {
            SET("amount=#{withholdInfo.amount}");
        }
        if (withholdInfo.getAmountTotal() != null) {
            SET("amountTotal=#{withholdInfo.amountTotal}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getBankCard())){
            SET("bankCard=#{withholdInfo.bankCard}");
        }
        if (withholdInfo.getStatus() != null) {
            SET("status=#{withholdInfo.status}");
        }
        if (withholdInfo.getModifyUserId() != null) {
            SET("modifyUserId=#{withholdInfo.modifyUserId}");
        }
        if (withholdInfo.getModifyTime() != null) {
            SET("modifyTime=#{withholdInfo.modifyTime}");
        }
        if (withholdInfo.getTotal() != null) {
            SET("total=#{withholdInfo.total}");
        }
        WHERE("id = #{withholdInfo.id}");
        String sql = SQL();
        return sql;
    }

    public String pageList(Map<String, Object> params) {
        WithholdInfoDO withholdInfo = (WithholdInfoDO) params.get("withholdInfo");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        BEGIN();
        SELECT("*");
        FROM(TABLE_NAME);
        if (withholdInfo.getId() != null) {
            WHERE("id=#{withholdInfo.id}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getUserName())){
            WHERE("userName=#{withholdInfo.userName}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getMobile())){
            WHERE("mobile=#{withholdInfo.mobile}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getCardNum())){
            WHERE("cardNum=#{withholdInfo.cardNum}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getDebitDay())){
            WHERE("debitDay=#{withholdInfo.debitDay}");
        }
        if (withholdInfo.getAmount() != null) {
            WHERE("amount=#{withholdInfo.amount}");
        }
        if (withholdInfo.getAmountTotal() != null) {
            WHERE("amountTotal=#{withholdInfo.amountTotal}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getBankCard())){
            WHERE("bankCard=#{withholdInfo.bankCard}");
        }
        if (withholdInfo.getStatus() != null) {
            WHERE("status=#{withholdInfo.status}");
        }
        if (withholdInfo.getModifyUserId() != null) {
            WHERE("modifyUserId=#{withholdInfo.modifyUserId}");
        }
        if (withholdInfo.getModifyTime() != null) {
            WHERE("modifyTime=#{withholdInfo.modifyTime}");
        }
        if (withholdInfo.getTotal() != null) {
            WHERE("total=#{withholdInfo.total}");
        }
        ORDER_BY("id desc");
        String sql = SQL();
        int start = 0;
        int limit = 0;
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        start = (pageNum - 1) * pageSize;
        limit = pageSize;
        sql += " limit " + start + ", " + limit;
        return sql;
    }

    public String pageListCount(Map<String, Object> params) {
        WithholdInfoDO withholdInfo = (WithholdInfoDO) params.get("withholdInfo");
        BEGIN();
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (withholdInfo.getId() != null) {
            WHERE("id=#{withholdInfo.id}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getUserName())){
            WHERE("userName=#{withholdInfo.userName}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getMobile())){
            WHERE("mobile=#{withholdInfo.mobile}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getCardNum())){
            WHERE("cardNum=#{withholdInfo.cardNum}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getDebitDay())){
            WHERE("debitDay=#{withholdInfo.debitDay}");
        }
        if (withholdInfo.getAmount() != null) {
            WHERE("amount=#{withholdInfo.amount}");
        }
        if (withholdInfo.getAmountTotal() != null) {
            WHERE("amountTotal=#{withholdInfo.amountTotal}");
        }
        if (StringUtils.isNotBlank(withholdInfo.getBankCard())){
            WHERE("bankCard=#{withholdInfo.bankCard}");
        }
        if (withholdInfo.getStatus() != null) {
            WHERE("status=#{withholdInfo.status}");
        }
        if (withholdInfo.getModifyUserId() != null) {
            WHERE("modifyUserId=#{withholdInfo.modifyUserId}");
        }
        if (withholdInfo.getModifyTime() != null) {
            WHERE("modifyTime=#{withholdInfo.modifyTime}");
        }
        if (withholdInfo.getTotal() != null) {
            WHERE("total=#{withholdInfo.total}");
        }
        String sql = SQL();
        return sql;
    }
}

