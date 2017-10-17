package net.fnsco.order.service.act.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.order.service.act.entity.LoanApplyUserDO;
public class LoanApplyUserProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "act_loan_apply_user";

    public String update(Map<String, Object> params) {
        LoanApplyUserDO loanApplyUser = (LoanApplyUserDO) params.get("loanApplyUser");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(loanApplyUser.getUserName())){
            SET("user_name=#{loanApplyUser.userName}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getContactNum())){
            SET("contact_num=#{loanApplyUser.contactNum}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getCardType())){
            SET("card_type=#{loanApplyUser.cardType}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getCardNum())){
            SET("card_num=#{loanApplyUser.cardNum}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getLoanType())){
            SET("loan_type=#{loanApplyUser.loanType}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getInnerCode())){
            SET("inner_code=#{loanApplyUser.innerCode}");
        }
        WHERE("id = #{loanApplyUser.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        LoanApplyUserDO loanApplyUser = (LoanApplyUserDO) params.get("loanApplyUser");
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
        if (loanApplyUser.getId() != null) {
            WHERE("id=#{loanApplyUser.id}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getUserName())){
            WHERE("user_name=#{loanApplyUser.userName}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getContactNum())){
            WHERE("contact_num=#{loanApplyUser.contactNum}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getCardType())){
            WHERE("card_type=#{loanApplyUser.cardType}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getCardNum())){
            WHERE("card_num like  CONCAT('%',#{loanApplyUser.cardNum},'%')");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getLoanType())){
            WHERE("loan_type=#{loanApplyUser.loanType}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getInnerCode())){
            WHERE("inner_code=#{loanApplyUser.innerCode}");
        }
        if(StringUtils.isNotBlank(loanApplyUser.getMerName())){
            WHERE("inner_code in (select inner_code from m_merchant_core where mer_name like CONCAT('%',#{loanApplyUser.merName},'%'))");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        LoanApplyUserDO loanApplyUser = (LoanApplyUserDO) params.get("loanApplyUser");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (loanApplyUser.getId() != null) {
            WHERE("id=#{loanApplyUser.id}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getUserName())){
            WHERE("user_name=#{loanApplyUser.userName}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getContactNum())){
            WHERE("contact_num=#{loanApplyUser.contactNum}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getCardType())){
            WHERE("card_type=#{loanApplyUser.cardType}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getCardNum())){
            WHERE("card_num like CONCAT('%',#{loanApplyUser.cardNum},'%')");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getLoanType())){
            WHERE("loan_type=#{loanApplyUser.loanType}");
        }
        if (StringUtils.isNotBlank(loanApplyUser.getInnerCode())){
            WHERE("inner_code=#{loanApplyUser.innerCode}");
        }
        if(StringUtils.isNotBlank(loanApplyUser.getMerName())){
            WHERE("inner_code in (select inner_code from m_merchant_core where mer_name like CONCAT('%',#{loanApplyUser.merName},'%'))");
        }
        }}.toString();
    }
}

