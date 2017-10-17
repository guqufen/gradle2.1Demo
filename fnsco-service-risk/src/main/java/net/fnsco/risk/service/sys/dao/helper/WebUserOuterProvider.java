package net.fnsco.risk.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.sys.entity.WebUserOuterDO;
public class WebUserOuterProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_web_user_outer";

    public String update(Map<String, Object> params) {
        WebUserOuterDO webUserOuter = (WebUserOuterDO) params.get("webUserOuter");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (webUserOuter.getType() != null) {
            SET("type=#{webUserOuter.type}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getName())){
            SET("name=#{webUserOuter.name}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getPassword())){
            SET("password=#{webUserOuter.password}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getRealName())){
            SET("real_name=#{webUserOuter.realName}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getMobile())){
            SET("mobile=#{webUserOuter.mobile}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getEmail())){
            SET("email=#{webUserOuter.email}");
        }
        if (webUserOuter.getSex() != null) {
            SET("sex=#{webUserOuter.sex}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getAliasName())){
            SET("alias_name=#{webUserOuter.aliasName}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getDepartment())){
            SET("department=#{webUserOuter.department}");
        }
        if (webUserOuter.getAgentId() != null) {
            SET("agent_id=#{webUserOuter.agentId}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getRemark())){
            SET("remark=#{webUserOuter.remark}");
        }
        if (webUserOuter.getModifyTime() != null) {
            SET("modify_time=#{webUserOuter.modifyTime}");
        }
        if (webUserOuter.getModifyUserId() != null) {
            SET("modify_user_id=#{webUserOuter.modifyUserId}");
        }
        if (webUserOuter.getCreaterTime() != null) {
            SET("creater_time=#{webUserOuter.createrTime}");
        }
        WHERE("id = #{webUserOuter.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        WebUserOuterDO webUserOuter = (WebUserOuterDO) params.get("webUserOuter");
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
        if (webUserOuter.getId() != null) {
            WHERE("id=#{webUserOuter.id}");
        }
        if (webUserOuter.getType() != null) {
            WHERE("type=#{webUserOuter.type}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getName())){
            WHERE("name like CONCAT('%',#{webUserOuter.name},'%')");
        }
        if (StringUtils.isNotBlank(webUserOuter.getPassword())){
            WHERE("password=#{webUserOuter.password}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getRealName())){
            WHERE("real_name=#{webUserOuter.realName}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getMobile())){
            WHERE("mobile=#{webUserOuter.mobile}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getEmail())){
            WHERE("email=#{webUserOuter.email}");
        }
        if (webUserOuter.getSex() != null) {
            WHERE("sex=#{webUserOuter.sex}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getAliasName())){
            WHERE("alias_name=#{webUserOuter.aliasName}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getDepartment())){
            WHERE("department=#{webUserOuter.department}");
        }
        if (webUserOuter.getAgentId() != null) {
            WHERE("agent_id=#{webUserOuter.agentId}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getRemark())){
            WHERE("remark=#{webUserOuter.remark}");
        }
        if (webUserOuter.getModifyTime() != null) {
            WHERE("modify_time=#{webUserOuter.modifyTime}");
        }
        if (webUserOuter.getModifyUserId() != null) {
            WHERE("modify_user_id=#{webUserOuter.modifyUserId}");
        }
        if (webUserOuter.getCreaterTime() != null) {
            WHERE("creater_time=#{webUserOuter.createrTime}");
        }
        if (webUserOuter.getStatus() != null) {
            WHERE("status=#{webUserOuter.status}");
        }
        WHERE("status != 0");
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        WebUserOuterDO webUserOuter = (WebUserOuterDO) params.get("webUserOuter");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (webUserOuter.getId() != null) {
            WHERE("id=#{webUserOuter.id}");
        }
        if (webUserOuter.getType() != null) {
            WHERE("type=#{webUserOuter.type}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getName())){
            WHERE("name like CONCAT('%',#{webUserOuter.name},'%')");
        }
        if (StringUtils.isNotBlank(webUserOuter.getPassword())){
            WHERE("password=#{webUserOuter.password}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getRealName())){
            WHERE("real_name=#{webUserOuter.realName}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getMobile())){
            WHERE("mobile=#{webUserOuter.mobile}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getEmail())){
            WHERE("email=#{webUserOuter.email}");
        }
        if (webUserOuter.getSex() != null) {
            WHERE("sex=#{webUserOuter.sex}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getAliasName())){
            WHERE("alias_name=#{webUserOuter.aliasName}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getDepartment())){
            WHERE("department=#{webUserOuter.department}");
        }
        if (webUserOuter.getAgentId() != null) {
            WHERE("agent_id=#{webUserOuter.agentId}");
        }
        if (StringUtils.isNotBlank(webUserOuter.getRemark())){
            WHERE("remark=#{webUserOuter.remark}");
        }
        if (webUserOuter.getModifyTime() != null) {
            WHERE("modify_time=#{webUserOuter.modifyTime}");
        }
        if (webUserOuter.getModifyUserId() != null) {
            WHERE("modify_user_id=#{webUserOuter.modifyUserId}");
        }
        if (webUserOuter.getCreaterTime() != null) {
            WHERE("creater_time=#{webUserOuter.createrTime}");
        }
        if (webUserOuter.getStatus() != null) {
            WHERE("status=#{webUserOuter.status}");
        }
        WHERE("status!=0");
        }}.toString();
    }

	@SuppressWarnings("unchecked")
	public String pageMerAlloList(Map<String, Object> params) {

		WebUserOuterDO webUserOuter = (WebUserOuterDO) params.get("webUserOuter");
		Integer pageNum = (Integer) params.get("pageNum");
		Integer pageSize = (Integer) params.get("pageSize");
		List<Integer> agentList = (List<Integer>) params.get("agentList");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		int start = (pageNum - 1) * pageSize;
		int limit = pageSize;

		String sql = "select * from risk_web_user_outer where 1=1 ";

		if (StringUtils.isNotBlank(webUserOuter.getDepartment().trim())) {
			sql = sql + "AND department like '%" + webUserOuter.getDepartment().trim() + "%'";
		}
		if (agentList != null) {
			String str = Joiner.on(",").join(agentList);
			sql = sql + "AND agent_id in (" + str + ")";
		}
		sql = sql + " order by id desc limit " + start + ", " + limit;
		logger.info("pageMerAlloList:" + sql);
		return sql;
	}

	@SuppressWarnings("unchecked")
	public String pageMerAlloListCount(Map<String, Object> params) {

		String department = (String) params.get("department");
		List<Integer> agentList = (List<Integer>) params.get("agentList");

		String sql = "select count(1) from risk_web_user_outer where 1=1 ";

		if (StringUtils.isNotBlank(department.trim())) {
			sql = sql + "AND department like '%" + department.trim() + "%'";
		}
		if (agentList != null) {
			String str = Joiner.on(",").join(agentList);
			sql = sql + "AND agent_id in (" + str + ")";
		}
		logger.info("pageMerAlloListCount:" + sql);
		return sql;
	}
}

