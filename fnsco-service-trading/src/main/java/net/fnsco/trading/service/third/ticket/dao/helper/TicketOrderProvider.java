package net.fnsco.trading.service.third.ticket.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
public class TicketOrderProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "thr_ticket_order";

    public String update(Map<String, Object> params) {
        TicketOrderDO ticketOrder = (TicketOrderDO) params.get("ticketOrder");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(ticketOrder.getAppUserId())){
            SET("app_user_id=#{ticketOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getOrderNo())){
            SET("order_no=#{ticketOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getPayOrderNo())){
            SET("pay_order_no=#{ticketOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainDate())){
            SET("train_date=#{ticketOrder.trainDate}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getFromStationCode())){
            SET("from_station_code=#{ticketOrder.fromStationCode}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getFromStationName())){
            SET("from_station_name=#{ticketOrder.fromStationName}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getToStationCode())){
            SET("to_station_code=#{ticketOrder.toStationCode}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getToStationName())){
            SET("to_station_name=#{ticketOrder.toStationName}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainCode())){
            SET("train_code=#{ticketOrder.trainCode}");
        }
        if (ticketOrder.getOrderAmount() != null) {
            SET("order_amount=#{ticketOrder.orderAmount}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainOrderNumber())){
            SET("train_order_number=#{ticketOrder.trainOrderNumber}");
        }
        if (ticketOrder.getPayTime() != null) {
            SET("pay_time=#{ticketOrder.payTime}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getRespMsg())){
            SET("resp_msg=#{ticketOrder.respMsg}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getRespCode())){
            SET("resp_code=#{ticketOrder.respCode}");
        }
        if (ticketOrder.getStatus() != null) {
            SET("status=#{ticketOrder.status}");
        }
        if (ticketOrder.getCreateTime() != null) {
            SET("create_time=#{ticketOrder.createTime}");
        }
        if (ticketOrder.getLastModifyTime() != null) {
            SET("last_modify_time=#{ticketOrder.lastModifyTime}");
        }
        WHERE("id = #{ticketOrder.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TicketOrderDO ticketOrder = (TicketOrderDO) params.get("ticketOrder");
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
        if (ticketOrder.getId() != null) {
            WHERE("id=#{ticketOrder.id}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getAppUserId())){
            WHERE("app_user_id=#{ticketOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getOrderNo())){
            WHERE("order_no=#{ticketOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{ticketOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainDate())){
            WHERE("train_date=#{ticketOrder.trainDate}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getFromStationCode())){
            WHERE("from_station_code=#{ticketOrder.fromStationCode}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getFromStationName())){
            WHERE("from_station_name=#{ticketOrder.fromStationName}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getToStationCode())){
            WHERE("to_station_code=#{ticketOrder.toStationCode}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getToStationName())){
            WHERE("to_station_name=#{ticketOrder.toStationName}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainCode())){
            WHERE("train_code=#{ticketOrder.trainCode}");
        }
        if (ticketOrder.getOrderAmount() != null) {
            WHERE("order_amount=#{ticketOrder.orderAmount}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainOrderNumber())){
            WHERE("train_order_number=#{ticketOrder.trainOrderNumber}");
        }
        if (ticketOrder.getPayTime() != null) {
            WHERE("pay_time=#{ticketOrder.payTime}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getRespMsg())){
            WHERE("resp_msg=#{ticketOrder.respMsg}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getRespCode())){
            WHERE("resp_code=#{ticketOrder.respCode}");
        }
        if (ticketOrder.getStatus() != null) {
            WHERE("status=#{ticketOrder.status}");
        }
        if (ticketOrder.getCreateTime() != null) {
            WHERE("create_time=#{ticketOrder.createTime}");
        }
        if (ticketOrder.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{ticketOrder.lastModifyTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TicketOrderDO ticketOrder = (TicketOrderDO) params.get("ticketOrder");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (ticketOrder.getId() != null) {
            WHERE("id=#{ticketOrder.id}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getAppUserId())){
            WHERE("app_user_id=#{ticketOrder.appUserId}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getOrderNo())){
            WHERE("order_no=#{ticketOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{ticketOrder.payOrderNo}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainDate())){
            WHERE("train_date=#{ticketOrder.trainDate}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getFromStationCode())){
            WHERE("from_station_code=#{ticketOrder.fromStationCode}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getFromStationName())){
            WHERE("from_station_name=#{ticketOrder.fromStationName}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getToStationCode())){
            WHERE("to_station_code=#{ticketOrder.toStationCode}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getToStationName())){
            WHERE("to_station_name=#{ticketOrder.toStationName}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainCode())){
            WHERE("train_code=#{ticketOrder.trainCode}");
        }
        if (ticketOrder.getOrderAmount() != null) {
            WHERE("order_amount=#{ticketOrder.orderAmount}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getTrainOrderNumber())){
            WHERE("train_order_number=#{ticketOrder.trainOrderNumber}");
        }
        if (ticketOrder.getPayTime() != null) {
            WHERE("pay_time=#{ticketOrder.payTime}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getRespMsg())){
            WHERE("resp_msg=#{ticketOrder.respMsg}");
        }
        if (StringUtils.isNotBlank(ticketOrder.getRespCode())){
            WHERE("resp_code=#{ticketOrder.respCode}");
        }
        if (ticketOrder.getStatus() != null) {
            WHERE("status=#{ticketOrder.status}");
        }
        if (ticketOrder.getCreateTime() != null) {
            WHERE("create_time=#{ticketOrder.createTime}");
        }
        if (ticketOrder.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{ticketOrder.lastModifyTime}");
        }
        }}.toString();
    }
}

