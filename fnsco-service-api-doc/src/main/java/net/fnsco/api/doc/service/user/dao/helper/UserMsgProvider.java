package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserMsgDO;
public class UserMsgProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_msg";

    public String update(Map<String, Object> params) {
        UserMsgDO userMsg = (UserMsgDO) params.get("userMsg");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userMsg.getCreateDate() != null) {
            SET("create_date=#{userMsg.createDate}");
        }
        if (userMsg.getModifyDate() != null) {
            SET("modify_date=#{userMsg.modifyDate}");
        }
        if (userMsg.getSys() != null) {
            SET("sys=#{userMsg.sys}");
        }
        if (userMsg.getSysMsgId() != null) {
            SET("sys_msg_id=#{userMsg.sysMsgId}");
        }
        if (userMsg.getSenderId() != null) {
            SET("sender_id=#{userMsg.senderId}");
        }
        if (userMsg.getReceiverId() != null) {
            SET("receiver_id=#{userMsg.receiverId}");
        }
        if (userMsg.getPubDate() != null) {
            SET("pub_date=#{userMsg.pubDate}");
        }
        if (StringUtils.isNotBlank(userMsg.getTitle())){
            SET("title=#{userMsg.title}");
        }
        if (StringUtils.isNotBlank(userMsg.getContent())){
            SET("content=#{userMsg.content}");
        }
        if (StringUtils.isNotBlank(userMsg.getMsgType())){
            SET("msg_type=#{userMsg.msgType}");
        }
        if (userMsg.getDeal() != null) {
            SET("deal=#{userMsg.deal}");
        }
        if (userMsg.getDealDate() != null) {
            SET("deal_date=#{userMsg.dealDate}");
        }
        WHERE("id = #{userMsg.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserMsgDO userMsg = (UserMsgDO) params.get("userMsg");
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
        if (userMsg.getId() != null) {
            WHERE("id=#{userMsg.id}");
        }
        if (userMsg.getCreateDate() != null) {
            WHERE("create_date=#{userMsg.createDate}");
        }
        if (userMsg.getModifyDate() != null) {
            WHERE("modify_date=#{userMsg.modifyDate}");
        }
        if (userMsg.getSys() != null) {
            WHERE("sys=#{userMsg.sys}");
        }
        if (userMsg.getSysMsgId() != null) {
            WHERE("sys_msg_id=#{userMsg.sysMsgId}");
        }
        if (userMsg.getSenderId() != null) {
            WHERE("sender_id=#{userMsg.senderId}");
        }
        if (userMsg.getReceiverId() != null) {
            WHERE("receiver_id=#{userMsg.receiverId}");
        }
        if (userMsg.getPubDate() != null) {
            WHERE("pub_date=#{userMsg.pubDate}");
        }
        if (StringUtils.isNotBlank(userMsg.getTitle())){
            WHERE("title=#{userMsg.title}");
        }
        if (StringUtils.isNotBlank(userMsg.getContent())){
            WHERE("content=#{userMsg.content}");
        }
        if (StringUtils.isNotBlank(userMsg.getMsgType())){
            WHERE("msg_type=#{userMsg.msgType}");
        }
        if (userMsg.getDeal() != null) {
            WHERE("deal=#{userMsg.deal}");
        }
        if (userMsg.getDealDate() != null) {
            WHERE("deal_date=#{userMsg.dealDate}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserMsgDO userMsg = (UserMsgDO) params.get("userMsg");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userMsg.getId() != null) {
            WHERE("id=#{userMsg.id}");
        }
        if (userMsg.getCreateDate() != null) {
            WHERE("create_date=#{userMsg.createDate}");
        }
        if (userMsg.getModifyDate() != null) {
            WHERE("modify_date=#{userMsg.modifyDate}");
        }
        if (userMsg.getSys() != null) {
            WHERE("sys=#{userMsg.sys}");
        }
        if (userMsg.getSysMsgId() != null) {
            WHERE("sys_msg_id=#{userMsg.sysMsgId}");
        }
        if (userMsg.getSenderId() != null) {
            WHERE("sender_id=#{userMsg.senderId}");
        }
        if (userMsg.getReceiverId() != null) {
            WHERE("receiver_id=#{userMsg.receiverId}");
        }
        if (userMsg.getPubDate() != null) {
            WHERE("pub_date=#{userMsg.pubDate}");
        }
        if (StringUtils.isNotBlank(userMsg.getTitle())){
            WHERE("title=#{userMsg.title}");
        }
        if (StringUtils.isNotBlank(userMsg.getContent())){
            WHERE("content=#{userMsg.content}");
        }
        if (StringUtils.isNotBlank(userMsg.getMsgType())){
            WHERE("msg_type=#{userMsg.msgType}");
        }
        if (userMsg.getDeal() != null) {
            WHERE("deal=#{userMsg.deal}");
        }
        if (userMsg.getDealDate() != null) {
            WHERE("deal_date=#{userMsg.dealDate}");
        }
        }}.toString();
    }
}

