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
            SELECT(" m.*");
            FROM(TABLE_NAME+" m ");
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
            WHERE("del_flag=0");
            ORDER_BY("order_num asc limit " + start + ", " + limit );
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
            WHERE("del_flag=0");
            }}.toString();
    }
    
 
    
    public String pageList2(Map<String, Object> params) {
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
//        SELECT(" * FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0" );
        SELECT(" * FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0 "
        		+ "UNION "
        		+ "SELECT * FROM sys_dept where parent_id in (SELECT id FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0)"
        		+ "UNION "
        		+ "SELECT * FROM sys_dept where parent_id in (SELECT id FROM sys_dept where parent_id in (SELECT id FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0))"
        		+ "UNION "
        		+ "SELECT * FROM sys_dept where parent_id in (SELECT id FROM sys_dept where parent_id in (SELECT id FROM sys_dept where parent_id in (SELECT id FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0)))");
        
//        WHERE("del_flag=0");
        ORDER_BY("order_num asc limit " + start + ", " + limit );
        }}.toString();
    }
    
    
    public String pageListCount2(Map<String, Object> params) {
        DeptDO dept = (DeptDO) params.get("dept");
        return new SQL() {{
            SELECT("count(1) from (select *  FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0 "
        		+ "UNION "
        		+ "SELECT * FROM sys_dept where parent_id in (SELECT id FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0)"
        		+ "UNION "
        		+ "SELECT * FROM sys_dept where parent_id in (SELECT id FROM sys_dept where parent_id in (SELECT id FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0))"
        		+ "UNION "
        		+ "SELECT * FROM sys_dept where parent_id in (SELECT id FROM sys_dept where parent_id in (SELECT id FROM sys_dept where parent_id in (SELECT id FROM sys_dept WHERE `name` in ( SELECT department FROM sys_user WHERE agent_id = #{dept.agentId}) and del_flag=0)))");
//            FROM(TABLE_NAME);
//            if (dept.getId() != null) {
//                WHERE("id=#{dept.id}");
//            }
//            if (dept.getParentId() != null) {
//                WHERE("parent_id=#{dept.parentId}");
//            }
//            if (StringUtils.isNotBlank(dept.getName())){
//                WHERE("name=#{dept.name}");
//            }
//            if (dept.getOrderNum() != null) {
//                WHERE("order_num=#{dept.orderNum}");
//            }
//            WHERE("del_flag=0");
            }}.toString()+")t";
    }
}

