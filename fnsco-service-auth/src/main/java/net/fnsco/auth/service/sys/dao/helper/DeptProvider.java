package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.DeptDO;
public class DeptProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_dept";

    public String update(Map<String, Object> params) {
        DeptDO dept = (DeptDO) params.get("dept");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (dept.getParentId() != null) {
            SET("parent_id=#{dept.parentId}");
        }
        if (StringUtils.isNotBlank(dept.getName())){
            SET("name=#{dept.name}");
        }
        if (dept.getOrderNum() != null) {
            SET("order_num=#{dept.orderNum}");
        }
        if (dept.getDelFlag() != null) {
            SET("del_flag=#{dept.delFlag}");
        }
        WHERE("id = #{dept.id}");
        }}.toString();
    }
    public String pageList(Map<String, Object> params) {
        DeptDO dept = (DeptDO) params.get("dept");
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
        if (dept.getId() != null) {
            WHERE("id=#{dept.id}");
        }
        if (dept.getParentId() != null) {
            WHERE("parent_id=#{dept.parentId}");
        }
        if (StringUtils.isNotBlank(dept.getName())){
            WHERE("name=#{dept.name}");
        }
        if (dept.getOrderNum() != null) {
            WHERE("order_num=#{dept.orderNum}");
        }
        if (dept.getDelFlag() != null) {
            WHERE("del_flag=#{dept.delFlag}");
        }
        WHERE("del_flag=0");
        ORDER_BY("id  limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DeptDO dept = (DeptDO) params.get("dept");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dept.getId() != null) {
            WHERE("id=#{dept.id}");
        }
        if (dept.getParentId() != null) {
            WHERE("parent_id=#{dept.parentId}");
        }
        if (StringUtils.isNotBlank(dept.getName())){
            WHERE("name=#{dept.name}");
        }
        if (dept.getOrderNum() != null) {
            WHERE("order_num=#{dept.orderNum}");
        }
        if (dept.getDelFlag() != null) {
            WHERE("del_flag=#{dept.delFlag}");
        }
        }}.toString();
    }
}

