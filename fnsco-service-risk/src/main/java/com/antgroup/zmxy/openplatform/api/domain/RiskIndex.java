package com.antgroup.zmxy.openplatform.api.domain;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;

/**
 * 风险指数
 *
 * @author auto create
 * @since 1.0, 2017-01-20 11:12:05
 */
public class RiskIndex extends ZhimaObject {

	private static final long serialVersionUID = 3499487847524755835L;

	/** 
	 * 城市名称
	 */
	@ApiField("city_name")
	private String cityName;

	/** 
	 * 一级行业代码
	 */
	@ApiField("industry_code_one")
	private String industryCodeOne;

	/** 
	 * 二级行业代码
	 */
	@ApiField("industry_code_two")
	private String industryCodeTwo;

	/** 
	 * 从201401开始，每月对应的风险指数
	 */
	@ApiListField("month_risk_index")
	@ApiField("month_risk_index")
	private List<MonthRiskIndex> monthRiskIndex;

	/** 
	 * 省份名称
	 */
	@ApiField("province_name")
	private String provinceName;

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityName( ) {
		return this.cityName;
	}

	public void setIndustryCodeOne(String industryCodeOne) {
		this.industryCodeOne = industryCodeOne;
	}
	public String getIndustryCodeOne( ) {
		return this.industryCodeOne;
	}

	public void setIndustryCodeTwo(String industryCodeTwo) {
		this.industryCodeTwo = industryCodeTwo;
	}
	public String getIndustryCodeTwo( ) {
		return this.industryCodeTwo;
	}

	public void setMonthRiskIndex(List<MonthRiskIndex> monthRiskIndex) {
		this.monthRiskIndex = monthRiskIndex;
	}
	public List<MonthRiskIndex> getMonthRiskIndex( ) {
		return this.monthRiskIndex;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceName( ) {
		return this.provinceName;
	}

}
