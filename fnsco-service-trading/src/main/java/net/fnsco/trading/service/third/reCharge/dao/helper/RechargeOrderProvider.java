package net.fnsco.trading.service.third.reCharge.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.third.reCharge.dto.RechargeOrderDO;

import org.apache.commons.lang3.StringUtils;


public class RechargeOrderProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "thr_recharge_order";

    public String update(Map<String, Object> params) {
        RechargeOrderDO rechargeOrder = (RechargeOrderDO) params.get("rechargeOrder");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(rechargeOrder.getAppUserId())){
            SET("app_user_id=#{rechargeOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getType())){
            SET("type=#{rechargeOrder.type}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getOrderNo())){
            SET("order_no=#{rechargeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getPayOrderNo())){
            SET("pay_order_no=#{rechargeOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getMobile())){
            SET("mobile=#{rechargeOrder.mobile}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getName())){
            SET("type_name=#{rechargeOrder.name}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getAmt())){
            SET("amount=#{rechargeOrder.amt}");
        }
        if (rechargeOrder.getStatus() != null) {
            SET("status=#{rechargeOrder.status}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getRespCode())){
            SET("resp_code=#{rechargeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getRespMsg())){
            SET("resp_msg=#{rechargeOrder.respMsg}");
        }
        if (rechargeOrder.getCreateTime() != null) {
            SET("create_time=#{rechargeOrder.createTime}");
        }
        if (rechargeOrder.getUpdateTime() != null) {
            SET("update_time=#{rechargeOrder.updateTime}");
        }
        WHERE("id = #{rechargeOrder.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        RechargeOrderDO rechargeOrder = (RechargeOrderDO) params.get("rechargeOrder");
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
        if (rechargeOrder.getId() != null) {
            WHERE("id=#{rechargeOrder.id}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getAppUserId())){
            WHERE("app_user_id=#{rechargeOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getType())){
            WHERE("type=#{rechargeOrder.type}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getOrderNo())){
            WHERE("order_no=#{rechargeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{rechargeOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getMobile())){
            WHERE("mobile=#{rechargeOrder.mobile}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getName())){
            WHERE("type_name=#{rechargeOrder.name}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getAmt())){
            WHERE("amount=#{rechargeOrder.amt}");
        }
        if (rechargeOrder.getStatus() != null) {
            WHERE("status=#{rechargeOrder.status}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getRespCode())){
            WHERE("resp_code=#{rechargeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getRespMsg())){
            WHERE("resp_msg=#{rechargeOrder.respMsg}");
        }
        if (rechargeOrder.getCreateTime() != null) {
            WHERE("create_time=#{rechargeOrder.createTime}");
        }
        if (rechargeOrder.getUpdateTime() != null) {
            WHERE("update_time=#{rechargeOrder.updateTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        RechargeOrderDO rechargeOrder = (RechargeOrderDO) params.get("rechargeOrder");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (rechargeOrder.getId() != null) {
            WHERE("id=#{rechargeOrder.id}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getAppUserId())){
            WHERE("app_user_id=#{rechargeOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getType())){
            WHERE("type=#{rechargeOrder.type}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getOrderNo())){
            WHERE("order_no=#{rechargeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{rechargeOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getMobile())){
            WHERE("mobile=#{rechargeOrder.mobile}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getName())){
            WHERE("type_name=#{rechargeOrder.name}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getAmt())){
            WHERE("amount=#{rechargeOrder.amt}");
        }
        if (rechargeOrder.getStatus() != null) {
            WHERE("status=#{rechargeOrder.status}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getRespCode())){
            WHERE("resp_code=#{rechargeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(rechargeOrder.getRespMsg())){
            WHERE("resp_msg=#{rechargeOrder.respMsg}");
        }
        if (rechargeOrder.getCreateTime() != null) {
            WHERE("create_time=#{rechargeOrder.createTime}");
        }
        if (rechargeOrder.getUpdateTime() != null) {
            WHERE("update_time=#{rechargeOrder.updateTime}");
        }
        }}.toString();
    }
}

