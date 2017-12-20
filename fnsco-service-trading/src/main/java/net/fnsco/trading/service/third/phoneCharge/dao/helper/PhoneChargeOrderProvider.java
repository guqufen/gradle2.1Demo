package net.fnsco.trading.service.third.phoneCharge.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.third.phoneCharge.dto.PhoneChargeOrderDO;

import org.apache.commons.lang3.StringUtils;


public class PhoneChargeOrderProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "thr_PhoneCharge_order";

    public String update(Map<String, Object> params) {
        PhoneChargeOrderDO phoneChargeOrder = (PhoneChargeOrderDO) params.get("phoneChargeOrder");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(phoneChargeOrder.getType())){
            SET("type=#{phoneChargeOrder.type}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getAppUserId())){
            SET("app_user_id=#{phoneChargeOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getOrderNo())){
            SET("order_no=#{phoneChargeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPayOrderNo())){
            SET("pay_order_no=#{phoneChargeOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPhoneNo())){
            SET("phone_no=#{phoneChargeOrder.phoneNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPid())){
            SET("pid=#{phoneChargeOrder.pid}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getName())){
            SET("name=#{phoneChargeOrder.name}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getAmt())){
            SET("amt=#{phoneChargeOrder.amt}");
        }
        if (phoneChargeOrder.getStatus() != null) {
            SET("status=#{phoneChargeOrder.status}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getRespCode())){
            SET("resp_code=#{phoneChargeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getRespMsg())){
            SET("resp_msg=#{phoneChargeOrder.respMsg}");
        }
        if (phoneChargeOrder.getCreateTime() != null) {
            SET("create_time=#{phoneChargeOrder.createTime}");
        }
        if (phoneChargeOrder.getUpdateTime() != null) {
            SET("update_time=#{phoneChargeOrder.updateTime}");
        }
        WHERE("id = #{phoneChargeOrder.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        PhoneChargeOrderDO phoneChargeOrder = (PhoneChargeOrderDO) params.get("phoneChargeOrder");
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
        if (phoneChargeOrder.getId() != null) {
            WHERE("id=#{phoneChargeOrder.id}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getType())){
            WHERE("type=#{phoneChargeOrder.type}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getAppUserId())){
            WHERE("app_user_id=#{phoneChargeOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getOrderNo())){
            WHERE("order_no=#{phoneChargeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{phoneChargeOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPhoneNo())){
            WHERE("phone_no=#{phoneChargeOrder.phoneNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPid())){
            WHERE("pid=#{phoneChargeOrder.pid}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getName())){
            WHERE("name=#{phoneChargeOrder.name}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getAmt())){
            WHERE("amt=#{phoneChargeOrder.amt}");
        }
        if (phoneChargeOrder.getStatus() != null) {
            WHERE("status=#{phoneChargeOrder.status}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getRespCode())){
            WHERE("resp_code=#{phoneChargeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getRespMsg())){
            WHERE("resp_msg=#{phoneChargeOrder.respMsg}");
        }
        if (phoneChargeOrder.getCreateTime() != null) {
            WHERE("create_time=#{phoneChargeOrder.createTime}");
        }
        if (phoneChargeOrder.getUpdateTime() != null) {
            WHERE("update_time=#{phoneChargeOrder.updateTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        PhoneChargeOrderDO phoneChargeOrder = (PhoneChargeOrderDO) params.get("phoneChargeOrder");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (phoneChargeOrder.getId() != null) {
            WHERE("id=#{phoneChargeOrder.id}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getType())){
            WHERE("type=#{phoneChargeOrder.type}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getAppUserId())){
            WHERE("app_user_id=#{phoneChargeOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getOrderNo())){
            WHERE("order_no=#{phoneChargeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{phoneChargeOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPhoneNo())){
            WHERE("phone_no=#{phoneChargeOrder.phoneNo}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getPid())){
            WHERE("pid=#{phoneChargeOrder.pid}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getName())){
            WHERE("name=#{phoneChargeOrder.name}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getAmt())){
            WHERE("amt=#{phoneChargeOrder.amt}");
        }
        if (phoneChargeOrder.getStatus() != null) {
            WHERE("status=#{phoneChargeOrder.status}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getRespCode())){
            WHERE("resp_code=#{phoneChargeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(phoneChargeOrder.getRespMsg())){
            WHERE("resp_msg=#{phoneChargeOrder.respMsg}");
        }
        if (phoneChargeOrder.getCreateTime() != null) {
            WHERE("create_time=#{phoneChargeOrder.createTime}");
        }
        if (phoneChargeOrder.getUpdateTime() != null) {
            WHERE("update_time=#{phoneChargeOrder.updateTime}");
        }
        }}.toString();
    }
}

