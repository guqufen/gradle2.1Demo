package net.fnsco.car.service.file.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.file.entity.OrderFileDO;
public class OrderFileProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_order_file";

    public String update(Map<String, Object> params) {
        OrderFileDO orderFile = (OrderFileDO) params.get("orderFile");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(orderFile.getOrderNo())){
            SET("order_no=#{orderFile.orderNo}");
        }
        if (StringUtils.isNotBlank(orderFile.getFileName())){
            SET("file_name=#{orderFile.fileName}");
        }
        if (StringUtils.isNotBlank(orderFile.getFileType())){
            SET("file_type=#{orderFile.fileType}");
        }
        if (StringUtils.isNotBlank(orderFile.getFilePath())){
            SET("file_path=#{orderFile.filePath}");
        }
        if (orderFile.getCreateTime() != null) {
            SET("create_time=#{orderFile.createTime}");
        }
        WHERE("id = #{orderFile.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OrderFileDO orderFile = (OrderFileDO) params.get("orderFile");
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
        if (orderFile.getId() != null) {
            WHERE("id=#{orderFile.id}");
        }
        if (StringUtils.isNotBlank(orderFile.getOrderNo())){
            WHERE("order_no=#{orderFile.orderNo}");
        }
        if (StringUtils.isNotBlank(orderFile.getFileName())){
            WHERE("file_name=#{orderFile.fileName}");
        }
        if (StringUtils.isNotBlank(orderFile.getFileType())){
            WHERE("file_type=#{orderFile.fileType}");
        }
        if (StringUtils.isNotBlank(orderFile.getFilePath())){
            WHERE("file_path=#{orderFile.filePath}");
        }
        if (orderFile.getCreateTime() != null) {
            WHERE("create_time=#{orderFile.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OrderFileDO orderFile = (OrderFileDO) params.get("orderFile");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (orderFile.getId() != null) {
            WHERE("id=#{orderFile.id}");
        }
        if (StringUtils.isNotBlank(orderFile.getOrderNo())){
            WHERE("order_no=#{orderFile.orderNo}");
        }
        if (StringUtils.isNotBlank(orderFile.getFileName())){
            WHERE("file_name=#{orderFile.fileName}");
        }
        if (StringUtils.isNotBlank(orderFile.getFileType())){
            WHERE("file_type=#{orderFile.fileType}");
        }
        if (StringUtils.isNotBlank(orderFile.getFilePath())){
            WHERE("file_path=#{orderFile.filePath}");
        }
        if (orderFile.getCreateTime() != null) {
            WHERE("create_time=#{orderFile.createTime}");
        }
        }}.toString();
    }
}

