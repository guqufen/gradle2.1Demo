package net.fnsco.car.service.ftemp.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.ftemp.entity.OrderFileTempDO;
public class OrderFileTempProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_order_file_temp";

    public String update(Map<String, Object> params) {
        OrderFileTempDO orderFileTemp = (OrderFileTempDO) params.get("orderFileTemp");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(orderFileTemp.getOrderNo())){
            SET("order_no=#{orderFileTemp.orderNo}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFileName())){
            SET("file_name=#{orderFileTemp.fileName}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFileType())){
            SET("file_type=#{orderFileTemp.fileType}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFilePath())){
            SET("file_path=#{orderFileTemp.filePath}");
        }
        if (orderFileTemp.getCreateTime() != null) {
            SET("create_time=#{orderFileTemp.createTime}");
        }
        WHERE("id = #{orderFileTemp.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OrderFileTempDO orderFileTemp = (OrderFileTempDO) params.get("orderFileTemp");
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
        if (orderFileTemp.getId() != null) {
            WHERE("id=#{orderFileTemp.id}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getOrderNo())){
            WHERE("order_no=#{orderFileTemp.orderNo}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFileName())){
            WHERE("file_name=#{orderFileTemp.fileName}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFileType())){
            WHERE("file_type=#{orderFileTemp.fileType}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFilePath())){
            WHERE("file_path=#{orderFileTemp.filePath}");
        }
        if (orderFileTemp.getCreateTime() != null) {
            WHERE("create_time=#{orderFileTemp.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OrderFileTempDO orderFileTemp = (OrderFileTempDO) params.get("orderFileTemp");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (orderFileTemp.getId() != null) {
            WHERE("id=#{orderFileTemp.id}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getOrderNo())){
            WHERE("order_no=#{orderFileTemp.orderNo}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFileName())){
            WHERE("file_name=#{orderFileTemp.fileName}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFileType())){
            WHERE("file_type=#{orderFileTemp.fileType}");
        }
        if (StringUtils.isNotBlank(orderFileTemp.getFilePath())){
            WHERE("file_path=#{orderFileTemp.filePath}");
        }
        if (orderFileTemp.getCreateTime() != null) {
            WHERE("create_time=#{orderFileTemp.createTime}");
        }
        }}.toString();
    }
}

