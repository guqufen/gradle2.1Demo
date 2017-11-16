package net.fnsco.risk.service.report.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import net.fnsco.risk.service.sys.entity.MerAllocationDO;

public class MercAllocationProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public String pageMerDataList(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID
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

		return new SQL() {
			{
				SELECT("ent.id, ent.entity_inner_code, ent.merc_name, ent.legal_person, ent.card_type, ent.card_num, ent.business_license_num, ent.regist_address");
				FROM("m_merchant_entity ent");
				// 商户所属代理商ID，默认为0-全部
				if (merAllocationDO.getAgentId() != null) {
					if (merAllocationDO.getAgentId() != 0) {
						WHERE("ent.agent_id=#{merAllocationDO.agentId}");
					}
				}
				// 商户名称不为空，则模糊查找
				if (!Strings.isNullOrEmpty(merAllocationDO.getMercName())) {
					WHERE("ent.merc_name like CONCAT('%', #{merAllocationDO.mercName}, '%')");
				}
				// 法人姓名不为空，则模糊查找
				if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
					WHERE("ent.legal_person like CONCAT('%' , #{merAllocationDO.legalPerson}, '%')");
				}
				// 必须是绑定关系
				WHERE("ent.entity_inner_code in (select entity_inner_code from risk_user_merc_rel WHERE agent_id =  #{agentId})");
				// 风控报告状态
				if (merAllocationDO.getReportStatus() != null) {
					if (merAllocationDO.getReportStatus() == 0) {// 默认全部,不做任何查询处理

					} else if (merAllocationDO.getReportStatus() == 1) {// 已生成,审核通过，status=1
						WHERE("ent.entity_inner_code in (select entity_inner_code from risk_report_info r where r.`status`=1)");
					} else if (merAllocationDO.getReportStatus() == 3) {// 生成中,待审核，status=0,审核失败2，提交带编辑4(除了1和3之外)
						WHERE("ent.entity_inner_code in (select entity_inner_code from risk_report_info r where r.`status` !=1 AND r.`status` !=3)");
					} else if (merAllocationDO.getReportStatus() == 2) {// 未生成,report表,待编辑3，或者该表不存在数据
						WHERE("(ent.entity_inner_code in (select distinct entity_inner_code from risk_report_info r where r.`status`=3) or ent.entity_inner_code not in (select distinct entity_inner_code from risk_report_info r))");
					}
				}
				ORDER_BY("id desc limit " + start + ", " + limit);
			}
		}.toString();
	}

	public String pageMerDataCount(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID

		return new SQL() {
			{
				SELECT("COUNT(*)");
				FROM("m_merchant_entity ent");
				// 商户所属代理商ID，默认为0-全部
				if (merAllocationDO.getAgentId() != null) {
					if (merAllocationDO.getAgentId() != 0) {
						WHERE("ent.agent_id=#{merAllocationDO.agentId}");
					}
				}
				// 商户名称不为空，则模糊查找
				if (!Strings.isNullOrEmpty(merAllocationDO.getMercName())) {
					WHERE("ent.merc_name like CONCAT('%', #{merAllocationDO.mercName}, '%')");
				}
				// 法人姓名不为空，则模糊查找
				if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
					WHERE("ent.legal_person like CONCAT('%' , #{merAllocationDO.legalPerson}, '%')");
				}
				// 必须是绑定关系
				WHERE("ent.entity_inner_code in (select entity_inner_code from risk_user_merc_rel WHERE agent_id =  #{agentId})");
				// 风控报告状态
				if (merAllocationDO.getReportStatus() != null) {
					if (merAllocationDO.getReportStatus() == 0) {// 默认全部,不做任何查询处理

					} else if (merAllocationDO.getReportStatus() == 1) {// 已生成,审核通过，status=1
						WHERE("ent.entity_inner_code in (select entity_inner_code from risk_report_info r where r.`status`=1)");
					} else if (merAllocationDO.getReportStatus() == 3) {// 生成中,待审核，status=0,审核失败2，提交带编辑4(除了1和3之外)
						WHERE("ent.entity_inner_code in (select entity_inner_code from risk_report_info r where r.`status` !=1 AND r.`status` !=3)");
					} else if (merAllocationDO.getReportStatus() == 2) {// 未生成,report表,待编辑3，或者该表没有数据
						WHERE("(ent.entity_inner_code in (select distinct entity_inner_code from risk_report_info r where r.`status`=3) or ent.entity_inner_code not in (select distinct entity_inner_code from risk_report_info r))");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * 商户分配-添加，需要查找实体商户表中，没有绑定关系且3个月内有交易数据的实体商户
	 * @param params
	 * @return
	 */
	public String pageAddMerDataList(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID
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

		return new SQL(){{
			SELECT("ent.id, ent.entity_inner_code, ent.merc_name, ent.legal_person, ent.card_type, ent.card_num, ent.business_license_num, ent.regist_address");
			FROM("m_merchant_entity ent");

			// 商户所属代理商ID，默认为0-全部
			if (merAllocationDO.getAgentId() != null) {
				if (merAllocationDO.getAgentId() != 0) {
					WHERE("ent.agent_id=#{merAllocationDO.agentId}");
				}
			}
			// 商户名称不为空，则模糊查找
			if ( !Strings.isNullOrEmpty(merAllocationDO.getMercName()) ) {
				WHERE("ent.merc_name like CONCAT('%', #{merAllocationDO.mercName}, '%')");
			}
			// 法人姓名不为空，则模糊查找
			if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
				WHERE("ent.legal_person like CONCAT('%' , #{merAllocationDO.legalPerson}, '%')");
			}
			// 商户分配-添加，没有绑定关系
			WHERE("ent.entity_inner_code not in (select entity_inner_code from risk_user_merc_rel WHERE agent_id =  #{agentId})");
			//3个月内有流水
			WHERE("(SELECT MAX(time_stamp) FROM t_trade_data t WHERE t.inner_code in( select distinct inner_code from m_merchant_core_entity_ref ref where ref.entity_inner_code = ent.entity_inner_code)) >= SUBDATE(CURDATE(), INTERVAL 3 MONTH)");
			ORDER_BY("id desc limit " + start + ", " + limit );
		}}.toString();

	}

	public String pageAddMerDataCount(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID
		
		return new SQL(){{
			SELECT("COUNT(*)");
			FROM("m_merchant_entity ent");

			// 商户所属代理商ID，默认为0-全部
			if (merAllocationDO.getAgentId() != null) {
				if (merAllocationDO.getAgentId() != 0) {
					WHERE("ent.agent_id=#{merAllocationDO.agentId}");
				}
			}
			// 商户名称不为空，则模糊查找
			if ( !Strings.isNullOrEmpty(merAllocationDO.getMercName()) ) {
				WHERE("ent.merc_name LIKE CONCAT('%', #{merAllocationDO.mercName}, '%')");
			}
			// 法人姓名不为空，则模糊查找
			if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
				WHERE("ent.legal_person LIKE CONCAT('%' , #{merAllocationDO.legalPerson}, '%')");
			}
			// 商户分配-添加，没有绑定关系
			WHERE("ent.entity_inner_code not in (select entity_inner_code from risk_user_merc_rel WHERE agent_id =  #{agentId})");
			//3个月内有流水
			WHERE("(SELECT MAX(time_stamp) FROM t_trade_data t WHERE t.inner_code in( select distinct inner_code from m_merchant_core_entity_ref ref where ref.entity_inner_code = ent.entity_inner_code)) >= SUBDATE(CURDATE(), INTERVAL 3 MONTH)");
		}}.toString();
	}
	
}
