package com.antgroup.zmxy.openplatform.api.response;

import java.util.List;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;
import com.antgroup.zmxy.openplatform.api.domain.ContactAnalyzeUnit;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.contact.analyze.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-04-25 15:59:23
 */
public class ZhimaCreditContactAnalyzeQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8763747788499296422L;

	/** 
	 * 芝麻信用对于每一次调用的唯一标示，可用于后期对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 业务结果： 当有芝麻分用户数>=3时 返回true 并且返回均值 当有芝麻分用户数<3时 返回false 且不返回均值
	 */
	@ApiField("biz_success")
	private Boolean bizSuccess;

	/** 
	 * 关系人验证信息单元列表
	 */
	@ApiListField("contact_analyze_units")
	@ApiField("contact_analyze_unit")
	private List<ContactAnalyzeUnit> contactAnalyzeUnits;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setBizSuccess(Boolean bizSuccess) {
		this.bizSuccess = bizSuccess;
	}
	public Boolean getBizSuccess( ) {
		return this.bizSuccess;
	}

	public void setContactAnalyzeUnits(List<ContactAnalyzeUnit> contactAnalyzeUnits) {
		this.contactAnalyzeUnits = contactAnalyzeUnits;
	}
	public List<ContactAnalyzeUnit> getContactAnalyzeUnits( ) {
		return this.contactAnalyzeUnits;
	}

}
