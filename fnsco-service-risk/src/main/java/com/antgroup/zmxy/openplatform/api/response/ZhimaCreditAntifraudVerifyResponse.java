package com.antgroup.zmxy.openplatform.api.response;

import java.util.List;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.antifraud.verify response.
 * 
 * @author auto create
 * @since 1.0, 2017-10-13 15:08:55
 */
public class ZhimaCreditAntifraudVerifyResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8461551556545179298L;

	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 决策结果，可空，取值当前为REJECT\REVIEW\PASS，产品定制使用。根据产品定制配置，对结果进行决策返回
	 */
	@ApiField("decision_result")
	private String decisionResult;

	/** 
	 * 方案ID，可空，产品定制使用。在线可能会存在多个方案并行，方案ID标识当前请求使用的在线方案
	 */
	@ApiField("solution_id")
	private String solutionId;

	/** 
	 * 欺诈信息验证，输出验证码verifyCode列表,verifyCode和文案的映射关系参见"产品接口说明"
	 */
	@ApiListField("verify_code")
	@ApiField("string")
	private List<String> verifyCode;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setDecisionResult(String decisionResult) {
		this.decisionResult = decisionResult;
	}
	public String getDecisionResult( ) {
		return this.decisionResult;
	}

	public void setSolutionId(String solutionId) {
		this.solutionId = solutionId;
	}
	public String getSolutionId( ) {
		return this.solutionId;
	}

	public void setVerifyCode(List<String> verifyCode) {
		this.verifyCode = verifyCode;
	}
	public List<String> getVerifyCode( ) {
		return this.verifyCode;
	}

}
