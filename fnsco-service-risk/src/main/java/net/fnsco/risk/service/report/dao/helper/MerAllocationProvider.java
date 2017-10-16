package net.fnsco.risk.service.report.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.risk.service.sys.entity.MerAllocationDO;

public class MerAllocationProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public String pageMerDataList(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID
		Integer pageNum = (Integer) params.get("pageNum");
		Integer pageSize = (Integer) params.get("pageSize");
		Integer type = (Integer) params.get("type");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		int start = (pageNum - 1) * pageSize;
		int limit = pageSize;

		// 直接用字符串写需要注意，字串之间的空格，容易出现因没给空格导致语法错误
		String sql = "SELECT distinct c.id, c.inner_code, c.mer_name, c.abbreviation, c.en_name, "
				+ "c.sign_date, c.legal_person, c.legal_person_mobile, c.legal_person_tel, "
				+ "case when c.legal_valid_card_type = '0' then '身份证' when c.legal_valid_card_type = '1' then '护照' end as legal_valid_card_type,  "
				+ "c.card_num, c.card_valid_time, c.business_license_num, c.business_license_valid_time,"
				+ "c.tax_regist_code, c.regist_address, c.merc_flag, "
				+ "case when c.source=0 then 'WEB' when c.source=1 then 'App' when c.source=2 then '浙付通' else '未知' end as sourceStr, "
				+ "c.modify_user_id, c.modify_time, c.status, c.source, (SELECT name from m_agent where id =" + agentId
				+ ") as agentStr" + " from m_merchant_core c where 1=1";

		// 商户所属代理商ID，默认为0-全部
		if (merAllocationDO.getAgentId() != null) {
			if (merAllocationDO.getAgentId() != 0) {
				sql = sql + " AND c.agent_id=" + merAllocationDO.getAgentId();
			}
		}

		// 商户名称不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getMerName())) {
			sql = sql + " AND c.mer_name like '%" + merAllocationDO.getMerName() + "%'";
		}

		// 法人姓名不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
			sql = sql + " AND c.legal_person like '%" + merAllocationDO.getLegalPerson() + "%'";
		}

		// type:0-属于；1-不属于
		if (type == 0) {
			sql = sql + " AND c.inner_code in (Select inner_code from risk_user_merc_rel WHERE agent_id = " + agentId
					+ ")";
		} else if (type == 1) {
			sql = sql + " AND c.inner_code not in (Select inner_code from risk_user_merc_rel WHERE agent_id = " + agentId
					+ ")";
		}

		// 风控报告状态
		if (merAllocationDO.getReportStatus() != null) {
			if (merAllocationDO.getReportStatus() == 0) {// 默认全部,不做任何查询处理

			} else if (merAllocationDO.getReportStatus() == 1) {// 已生成,审核通过，status=1
				sql = sql + " AND c.inner_code in (select inner_code from risk_report_info r where r.`status`=1)";
			} else if (merAllocationDO.getReportStatus() == 3) {// 生成中,待审核，status=0,待编辑3，审核失败2，提交带编辑4(除了1之外)
				sql = sql + " AND c.inner_code in (select inner_code from risk_report_info r where r.`status` !=1)";
			} else if (merAllocationDO.getReportStatus() == 2) {// 未生成,report表没有数据
				sql = sql + " AND c.inner_code not in (select inner_code from risk_report_info r)";
			}
		}

		sql = sql + " group by id order by c.id desc limit " + start + ", " + limit;

		logger.info("pageMerData:" + sql);
		return sql;

	}
    
	public String pageMerDataCount(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID
		Integer type = (Integer) params.get("type");

		String sql = "SELECT COUNT(1) from m_merchant_core c where 1=1";

		// 商户所属代理商ID，默认为0-全部
		if (merAllocationDO.getAgentId() != null) {
			if (merAllocationDO.getAgentId() != 0) {
				sql = sql + " AND c.agent_id=" + merAllocationDO.getAgentId();
			}
		}

		// 商户名称不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getMerName())) {
			sql = sql + " AND c.mer_name like '%" + merAllocationDO.getMerName() + "%'";
		}

		// 法人姓名不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
			sql = sql + " AND c.legal_person like '%" + merAllocationDO.getLegalPerson() + "%'";
		}
		// type:0-属于；1-不属于，同时agent_id等于当前用户agent_id
		if (type == 0) {
			sql = sql + " AND c.inner_code in (Select inner_code from risk_user_merc_rel WHERE agent_id = " + agentId
					+ ")";
		} else if (type == 1) {
			sql = sql + " AND c.inner_code not in (Select inner_code from risk_user_merc_rel WHERE agent_id = " + agentId
					+ ")";
		}

		// 风控报告状态
		if (merAllocationDO.getReportStatus() != null) {
			if (merAllocationDO.getReportStatus() == 0) {// 默认全部,不做任何查询处理

			} else if (merAllocationDO.getReportStatus() == 1) {// 已生成,审核通过，status=1
				sql = sql + " AND c.inner_code in (select inner_code from risk_report_info r where r.`status`=1)";
			} else if (merAllocationDO.getReportStatus() == 3) {// 生成中,待审核，status=0,待编辑3，审核失败2，提交带编辑4(除了1之外)
				sql = sql + " AND c.inner_code in (select inner_code from risk_report_info r where r.`status` !=1)";
			} else if (merAllocationDO.getReportStatus() == 2) {// 未生成,report表没有数据
				sql = sql + " AND c.inner_code not in (select inner_code from risk_report_info r)";
			}
		}

		logger.info("pageMerData:" + sql);
		return sql;
	}
	
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

		// 直接用字符串写需要注意，字串之间的空格，容易出现因没给空格导致语法错误
		String sql = "SELECT distinct c.id, c.inner_code, c.mer_name, c.abbreviation, c.en_name, "
				+ "c.sign_date, c.legal_person, c.legal_person_mobile, c.legal_person_tel, "
				+ "case when c.legal_valid_card_type = '0' then '身份证' when c.legal_valid_card_type = '1' then '护照' end as legal_valid_card_type,  "
				+ "c.card_num, c.card_valid_time, c.business_license_num, c.business_license_valid_time,"
				+ "c.tax_regist_code, c.regist_address, c.merc_flag, "
				+ "case when c.source=0 then 'WEB' when c.source=1 then 'App' when c.source=2 then '浙付通' else '未知' end as sourceStr, "
				+ "c.modify_user_id, c.modify_time, c.status, c.source, (SELECT name from m_agent where id =" + agentId
				+ ") as agentStr" + " from m_merchant_core c where 1=1";

		// 商户所属代理商ID，默认为0-全部
		if (merAllocationDO.getAgentId() != null) {
			if (merAllocationDO.getAgentId() != 0) {
				sql = sql + " AND c.agent_id=" + merAllocationDO.getAgentId();
			}
		}

		// 商户名称不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getMerName())) {
			sql = sql + " AND c.mer_name like '%" + merAllocationDO.getMerName() + "%'";
		}

		// 法人姓名不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
			sql = sql + " AND c.legal_person like '%" + merAllocationDO.getLegalPerson() + "%'";
		}

		// 新增，不属于
		sql = sql + " AND c.inner_code not in (Select inner_code from risk_user_merc_rel WHERE agent_id = " + agentId
				+ ")";

		sql = sql + " group by id order by c.id desc limit " + start + ", " + limit;

		logger.info("pageMerData:" + sql);
		return sql;

	}

	public String pageAddMerDataCount(Map<String, Object> params) {

		MerAllocationDO merAllocationDO = (MerAllocationDO) params.get("merAllocationDO");
		Integer agentId = (Integer) params.get("agentId");// 当前外部用户所属代理商ID

		String sql = "SELECT COUNT(1) from m_merchant_core c where 1=1";

		// 商户所属代理商ID，默认为0-全部
		if (merAllocationDO.getAgentId() != null) {
			if (merAllocationDO.getAgentId() != 0) {
				sql = sql + " AND c.agent_id=" + merAllocationDO.getAgentId();
			}
		}

		// 商户名称不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getMerName())) {
			sql = sql + " AND c.mer_name like '%" + merAllocationDO.getMerName() + "%'";
		}

		// 法人姓名不为空，则模糊查找
		if (StringUtils.isNotBlank(merAllocationDO.getLegalPerson())) {
			sql = sql + " AND c.legal_person like '%" + merAllocationDO.getLegalPerson() + "%'";
		}
		// 新增，不属于
		sql = sql + " AND c.inner_code not in (Select inner_code from risk_user_merc_rel WHERE agent_id = " + agentId
				+ ")";

		logger.info("pageMerData:" + sql);
		return sql;

	}
	
}
