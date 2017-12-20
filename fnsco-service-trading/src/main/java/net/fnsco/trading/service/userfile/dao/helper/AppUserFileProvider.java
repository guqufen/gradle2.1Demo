package net.fnsco.trading.service.userfile.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.userfile.entity.AppUserFileDO;
public class AppUserFileProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "u_app_user_file";

    public String update(Map<String, Object> params) {
        AppUserFileDO appUserFile = (AppUserFileDO) params.get("appUserFile");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (appUserFile.getAppUserId()!= null){
            SET("app_user_id=#{appUserFile.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFileName())){
            SET("file_name=#{appUserFile.fileName}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFileType())){
            SET("file_type=#{appUserFile.fileType}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFilePath())){
            SET("file_path=#{appUserFile.filePath}");
        }
        if (appUserFile.getCreateTime() != null) {
            SET("create_time=#{appUserFile.createTime}");
        }
        WHERE("id = #{appUserFile.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppUserFileDO appUserFile = (AppUserFileDO) params.get("appUserFile");
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
        if (appUserFile.getId() != null) {
            WHERE("id=#{appUserFile.id}");
        }
        if (appUserFile.getAppUserId()!= null){
            WHERE("app_user_id=#{appUserFile.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFileName())){
            WHERE("file_name=#{appUserFile.fileName}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFileType())){
            WHERE("file_type=#{appUserFile.fileType}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFilePath())){
            WHERE("file_path=#{appUserFile.filePath}");
        }
        if (appUserFile.getCreateTime() != null) {
            WHERE("create_time=#{appUserFile.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppUserFileDO appUserFile = (AppUserFileDO) params.get("appUserFile");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appUserFile.getId() != null) {
            WHERE("id=#{appUserFile.id}");
        }
        if (appUserFile.getAppUserId()!= null){
            WHERE("app_user_id=#{appUserFile.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFileName())){
            WHERE("file_name=#{appUserFile.fileName}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFileType())){
            WHERE("file_type=#{appUserFile.fileType}");
        }
        if (StringUtils.isNotBlank(appUserFile.getFilePath())){
            WHERE("file_path=#{appUserFile.filePath}");
        }
        if (appUserFile.getCreateTime() != null) {
            WHERE("create_time=#{appUserFile.createTime}");
        }
        }}.toString();
    }
}

