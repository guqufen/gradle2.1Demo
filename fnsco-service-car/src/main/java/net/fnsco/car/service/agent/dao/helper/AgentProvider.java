package net.fnsco.car.service.agent.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.agent.entity.AgentDO;
public class AgentProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_agent";

    public String update(Map<String, Object> params) {
        AgentDO agent = (AgentDO) params.get("agent");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(agent.getName())){
            SET("name=#{agent.name}");
        }
        if (agent.getType() != null) {
            SET("type=#{agent.type}");
        }
        if (agent.getProvinceid() != null) {
            SET("provinceId=#{agent.provinceid}");
        }
        if (StringUtils.isNotBlank(agent.getProvincename())){
            SET("provinceName=#{agent.provincename}");
        }
        if (agent.getCityid() != null) {
            SET("cityId=#{agent.cityid}");
        }
        if (StringUtils.isNotBlank(agent.getCityname())){
            SET("cityName=#{agent.cityname}");
        }
        if (agent.getAreaid() != null) {
            SET("areaId=#{agent.areaid}");
        }
        if (StringUtils.isNotBlank(agent.getAreaname())){
            SET("areaName=#{agent.areaname}");
        }
        if (StringUtils.isNotBlank(agent.getAddress())){
            SET("address=#{agent.address}");
        }
        if (agent.getSuggestCode() != null) {
            SET("suggest_code=#{agent.suggestCode}");
        }
        //////
        if (StringUtils.isNotBlank(agent.getMobile())){
            SET("mobile=#{agent.mobile}");
        }
        if (StringUtils.isNotBlank(agent.getShortName())){
            SET("short_name=#{agent.shortName}");
        }
        if (StringUtils.isNotBlank(agent.getPrincipal())){
            SET("principal=#{agent.principal}");
        }
        if (agent.getCreateTime()!=null){
            SET("create_time=#{agent.createTime}");
        }
        WHERE("id = #{agent.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AgentDO agent = (AgentDO) params.get("agent");
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
        if (agent.getId() != null) {
            WHERE("id=#{agent.id}");
        }
        if (StringUtils.isNotBlank(agent.getName())){
            WHERE("name=#{agent.name}");
        }
        if (agent.getType() != null) {
            WHERE("type=#{agent.type}");
        }
        if (agent.getProvinceid() != null) {
            WHERE("provinceId=#{agent.provinceid}");
        }
        if (StringUtils.isNotBlank(agent.getProvincename())){
            WHERE("provinceName=#{agent.provincename}");
        }
        if (agent.getCityid() != null) {
            WHERE("cityId=#{agent.cityid}");
        }
        if (StringUtils.isNotBlank(agent.getCityname())){
            WHERE("cityName=#{agent.cityname}");
        }
        if (agent.getAreaid() != null) {
            WHERE("areaId=#{agent.areaid}");
        }
        if (StringUtils.isNotBlank(agent.getAreaname())){
            WHERE("areaName=#{agent.areaname}");
        }
        if (StringUtils.isNotBlank(agent.getAddress())){
            WHERE("address=#{agent.address}");
        }
        if (agent.getSuggestCode() != null) {
            WHERE("suggest_code=#{agent.suggestCode}");
        }
        ///
        if (StringUtils.isNotBlank(agent.getMobile())){
        	WHERE("mobile=#{agent.mobile}");
        }
        if (StringUtils.isNotBlank(agent.getShortName())){
        	WHERE("short_name=#{agent.shortName}");
        }
        if (StringUtils.isNotBlank(agent.getPrincipal())){
        	WHERE("principal=#{agent.principal}");
        }
        if (agent.getCreateTime()!=null){
        	WHERE("create_time=#{agent.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AgentDO agent = (AgentDO) params.get("agent");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (agent.getId() != null) {
            WHERE("id=#{agent.id}");
        }
        if (StringUtils.isNotBlank(agent.getName())){
            WHERE("name=#{agent.name}");
        }
        if (agent.getType() != null) {
            WHERE("type=#{agent.type}");
        }
        if (agent.getProvinceid() != null) {
            WHERE("provinceId=#{agent.provinceid}");
        }
        if (StringUtils.isNotBlank(agent.getProvincename())){
            WHERE("provinceName=#{agent.provincename}");
        }
        if (agent.getCityid() != null) {
            WHERE("cityId=#{agent.cityid}");
        }
        if (StringUtils.isNotBlank(agent.getCityname())){
            WHERE("cityName=#{agent.cityname}");
        }
        if (agent.getAreaid() != null) {
            WHERE("areaId=#{agent.areaid}");
        }
        if (StringUtils.isNotBlank(agent.getAreaname())){
            WHERE("areaName=#{agent.areaname}");
        }
        if (StringUtils.isNotBlank(agent.getAddress())){
            WHERE("address=#{agent.address}");
        }
        if (agent.getSuggestCode() != null) {
            WHERE("suggest_code=#{agent.suggestCode}");
        }
        if (StringUtils.isNotBlank(agent.getMobile())){
        	WHERE("mobile=#{agent.mobile}");
        }
        if (StringUtils.isNotBlank(agent.getShortName())){
        	WHERE("short_name=#{agent.shortName}");
        }
        if (StringUtils.isNotBlank(agent.getPrincipal())){
        	WHERE("principal=#{agent.principal}");
        }
        if (agent.getCreateTime()!=null){
        	WHERE("create_time=#{agent.createTime}");
        }
        }}.toString();
    }
}

