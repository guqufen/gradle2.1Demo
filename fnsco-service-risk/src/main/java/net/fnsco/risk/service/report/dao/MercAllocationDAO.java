package net.fnsco.risk.service.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import net.fnsco.risk.service.report.dao.helper.MercAllocationProvider;
import net.fnsco.risk.service.sys.entity.MerAllocationDO;

public interface MercAllocationDAO {

	@Results({@Result(column="id",property="id"),@Result(column="inner_code",property="innerCode"),@Result(column="mer_name",property="merName"),
		@Result(column="abbreviation",property="abbreviation"),@Result(column="en_name",property="enName"),@Result(column="sign_date",property="signDate"),
		@Result(column="legal_person",property="legalPerson"),@Result(column="legal_person_mobile",property="legalPersonMobile"),@Result(column="legal_person_tel",property="legalPersonTel"),
		@Result(column="legal_valid_card_type",property="legalValidCardType"),@Result(column="card_num",property="cardNum"),@Result(column="card_valid_time",property="cardValidTime"),
		@Result(column="business_license_num",property="businessLicenseNum"),@Result(column="business_license_valid_time",property="businessLicenseValidTime"),@Result(column="tax_regist_code",property="taxRegistCode"),
		@Result(column="regist_address",property="registAddress"),@Result(column="merc_flag",property="mercFlag"),@Result(column="source",property="source"),@Result(column="sourceStr",property="sourceStr"),@Result(column="modify_user_id",property="modifyUserId"),
		@Result(column="modify_time",property="modifyTime"),@Result(column="status",property="status"),@Result(column="agent_id",property="agentId"),
		@Result(column="agentStr",property="agentStr")})
	@SelectProvider(type=MercAllocationProvider.class, method="pageAddMerDataList")
	public List<MerAllocationDO> pageAddMerDataList(@Param("merAllocationDO") MerAllocationDO merAllocationDO,@Param("agentId")Integer agentId, @Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);//type:0-详情查询；1-添加查询

	@SelectProvider(type=MercAllocationProvider.class, method="pageAddMerDataCount")
	public Integer pageAddMerDataCount(@Param("merAllocationDO") MerAllocationDO merAllocationDO,@Param("agentId")Integer agentId);

	@Results({@Result(column="id",property="id"),@Result(column="inner_code",property="innerCode"),@Result(column="mer_name",property="merName"),
		@Result(column="abbreviation",property="abbreviation"),@Result(column="en_name",property="enName"),@Result(column="sign_date",property="signDate"),
		@Result(column="legal_person",property="legalPerson"),@Result(column="legal_person_mobile",property="legalPersonMobile"),@Result(column="legal_person_tel",property="legalPersonTel"),
		@Result(column="legal_valid_card_type",property="legalValidCardType"),@Result(column="card_num",property="cardNum"),@Result(column="card_valid_time",property="cardValidTime"),
		@Result(column="business_license_num",property="businessLicenseNum"),@Result(column="business_license_valid_time",property="businessLicenseValidTime"),@Result(column="tax_regist_code",property="taxRegistCode"),
		@Result(column="regist_address",property="registAddress"),@Result(column="merc_flag",property="mercFlag"),@Result(column="source",property="source"),@Result(column="sourceStr",property="sourceStr"),@Result(column="modify_user_id",property="modifyUserId"),
		@Result(column="modify_time",property="modifyTime"),@Result(column="status",property="status"),@Result(column="agent_id",property="agentId"),
		@Result(column="agentStr",property="agentStr")})
	@SelectProvider(type=MercAllocationProvider.class, method="pageMerDataList")
//	public List<MerAllocationDO> pageMerDataList(@Param("merAllocationDO") MerAllocationDO merAllocationDO,@Param("agentId")Integer agentId,@Param("type")Integer type, @Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);//type:0-详情查询；1-添加查询
	public List<MerAllocationDO> pageMerDataList(@Param("merAllocationDO") MerAllocationDO merAllocationDO,@Param("agentId")Integer agentId, @Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);

	@SelectProvider(type=MercAllocationProvider.class, method="pageMerDataCount")
//	public Integer pageMerDataCount(@Param("merAllocationDO") MerAllocationDO merAllocationDO,@Param("agentId")Integer agentId,@Param("type")Integer type);//type:0-详情查询；1-添加查询
	public Integer pageMerDataCount(@Param("merAllocationDO") MerAllocationDO merAllocationDO,@Param("agentId")Integer agentId);

	/**
	 * 通过商户名称查找实体商户号
	 * @param merName
	 * @return
	 */
	@Select("select entity_inner_code from m_merchant_core where merc_name like CONCAT('%', #{merName}, '%')")
	public List<String> getByMerName(@Param("merName")String merName);

}
