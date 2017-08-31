package net.fnsco.bigdata.service.merchant.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.bigdata.service.merchant.entity.MerchantPosDO;
public class MerchantPosProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "m_merchant_pos";

    public String update(Map<String, Object> params) {
        MerchantPosDO merchantPos = (MerchantPosDO) params.get("merchantPos");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(merchantPos.getInnerCode())){
            SET("inner_code=#{merchantPos.innerCode}");
        }
        if (merchantPos.getChannelId() != null) {
            SET("channel_id=#{merchantPos.channelId}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosName())){
            SET("pos_name=#{merchantPos.posName}");
        }
        if (StringUtils.isNotBlank(merchantPos.getSnCode())){
            SET("sn_code=#{merchantPos.snCode}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosFactory())){
            SET("pos_factory=#{merchantPos.posFactory}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosType())){
            SET("pos_type=#{merchantPos.posType}");
        }
        if (StringUtils.isNotBlank(merchantPos.getStatus())){
            SET("status=#{merchantPos.status}");
        }
        if (merchantPos.getBankId() != null) {
            SET("bank_id=#{merchantPos.bankId}");
        }
        if (StringUtils.isNotBlank(merchantPos.getMercReferName())){
            SET("merc_refer_name=#{merchantPos.mercReferName}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosAddr())){
            SET("pos_addr=#{merchantPos.posAddr}");
        }
        if (merchantPos.getPosProvince() != null) {
            SET("pos_province=#{merchantPos.posProvince}");
        }
        if (merchantPos.getPosCity() != null) {
            SET("pos_city=#{merchantPos.posCity}");
        }
        if (merchantPos.getPosArea() != null) {
            SET("pos_area=#{merchantPos.posArea}");
        }
        WHERE("id = #{merchantPos.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        MerchantPosDO merchantPos = (MerchantPosDO) params.get("merchantPos");
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
        if (merchantPos.getId() != null) {
            WHERE("id=#{merchantPos.id}");
        }
        if (StringUtils.isNotBlank(merchantPos.getInnerCode())){
            WHERE("inner_code=#{merchantPos.innerCode}");
        }
        if (merchantPos.getChannelId() != null) {
            WHERE("channel_id=#{merchantPos.channelId}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosName())){
            WHERE("pos_name=#{merchantPos.posName}");
        }
        if (StringUtils.isNotBlank(merchantPos.getSnCode())){
            WHERE("sn_code=#{merchantPos.snCode}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosFactory())){
            WHERE("pos_factory=#{merchantPos.posFactory}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosType())){
            WHERE("pos_type=#{merchantPos.posType}");
        }
        if (StringUtils.isNotBlank(merchantPos.getStatus())){
            WHERE("status=#{merchantPos.status}");
        }
        if (merchantPos.getBankId() != null) {
            WHERE("bank_id=#{merchantPos.bankId}");
        }
        if (StringUtils.isNotBlank(merchantPos.getMercReferName())){
            WHERE("merc_refer_name=#{merchantPos.mercReferName}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosAddr())){
            WHERE("pos_addr=#{merchantPos.posAddr}");
        }
        if (merchantPos.getPosProvince() != null) {
            WHERE("pos_province=#{merchantPos.posProvince}");
        }
        if (merchantPos.getPosCity() != null) {
            WHERE("pos_city=#{merchantPos.posCity}");
        }
        if (merchantPos.getPosArea() != null) {
            WHERE("pos_area=#{merchantPos.posArea}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        MerchantPosDO merchantPos = (MerchantPosDO) params.get("merchantPos");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (merchantPos.getId() != null) {
            WHERE("id=#{merchantPos.id}");
        }
        if (StringUtils.isNotBlank(merchantPos.getInnerCode())){
            WHERE("inner_code=#{merchantPos.innerCode}");
        }
        if (merchantPos.getChannelId() != null) {
            WHERE("channel_id=#{merchantPos.channelId}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosName())){
            WHERE("pos_name=#{merchantPos.posName}");
        }
        if (StringUtils.isNotBlank(merchantPos.getSnCode())){
            WHERE("sn_code=#{merchantPos.snCode}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosFactory())){
            WHERE("pos_factory=#{merchantPos.posFactory}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosType())){
            WHERE("pos_type=#{merchantPos.posType}");
        }
        if (StringUtils.isNotBlank(merchantPos.getStatus())){
            WHERE("status=#{merchantPos.status}");
        }
        if (merchantPos.getBankId() != null) {
            WHERE("bank_id=#{merchantPos.bankId}");
        }
        if (StringUtils.isNotBlank(merchantPos.getMercReferName())){
            WHERE("merc_refer_name=#{merchantPos.mercReferName}");
        }
        if (StringUtils.isNotBlank(merchantPos.getPosAddr())){
            WHERE("pos_addr=#{merchantPos.posAddr}");
        }
        if (merchantPos.getPosProvince() != null) {
            WHERE("pos_province=#{merchantPos.posProvince}");
        }
        if (merchantPos.getPosCity() != null) {
            WHERE("pos_city=#{merchantPos.posCity}");
        }
        if (merchantPos.getPosArea() != null) {
            WHERE("pos_area=#{merchantPos.posArea}");
        }
        }}.toString();
    }
}

