package net.fnsco.order.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.order.service.sys.entity.ImportErrorDO;
public class ImportErrorProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_import_error";

    public String update(Map<String, Object> params) {
        ImportErrorDO importError = (ImportErrorDO) params.get("importError");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(importError.getImportFileName())){
            SET("import_file_name=#{importError.importFileName}");
        }
        if (StringUtils.isNotBlank(importError.getData())){
            SET("data=#{importError.data}");
        }
        if (StringUtils.isNotBlank(importError.getErrorMsg())){
            SET("error_msg=#{importError.errorMsg}");
        }
        if (importError.getCreateTime() != null) {
            SET("create_time=#{importError.createTime}");
        }
        if (importError.getCreateUserId() != null) {
            SET("create_user_Id=#{importError.createUserId}");
        }
        if (importError.getRowNumber() != null) {
            SET("row_number=#{importError.rowNumber}");
        }
        WHERE("id = #{importError.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ImportErrorDO importError = (ImportErrorDO) params.get("importError");
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
        if (importError.getId() != null) {
            WHERE("id=#{importError.id}");
        }
        if (StringUtils.isNotBlank(importError.getImportFileName())){
            WHERE("import_file_name=#{importError.importFileName}");
        }
        if (StringUtils.isNotBlank(importError.getData())){
            WHERE("data=#{importError.data}");
        }
        if (StringUtils.isNotBlank(importError.getErrorMsg())){
            WHERE("error_msg=#{importError.errorMsg}");
        }
        if (importError.getCreateTime() != null) {
            WHERE("create_time=#{importError.createTime}");
        }
        if (importError.getCreateUserId() != null) {
            WHERE("create_user_Id=#{importError.createUserId}");
        }
        if (importError.getRowNumber() != null) {
            WHERE("row_number=#{importError.rowNumber}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ImportErrorDO importError = (ImportErrorDO) params.get("importError");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (importError.getId() != null) {
            WHERE("id=#{importError.id}");
        }
        if (StringUtils.isNotBlank(importError.getImportFileName())){
            WHERE("import_file_name=#{importError.importFileName}");
        }
        if (StringUtils.isNotBlank(importError.getData())){
            WHERE("data=#{importError.data}");
        }
        if (StringUtils.isNotBlank(importError.getErrorMsg())){
            WHERE("error_msg=#{importError.errorMsg}");
        }
        if (importError.getCreateTime() != null) {
            WHERE("create_time=#{importError.createTime}");
        }
        if (importError.getCreateUserId() != null) {
            WHERE("create_user_Id=#{importError.createUserId}");
        }
        if (importError.getRowNumber() != null) {
            WHERE("row_number=#{importError.rowNumber}");
        }
        }}.toString();
    }
}

