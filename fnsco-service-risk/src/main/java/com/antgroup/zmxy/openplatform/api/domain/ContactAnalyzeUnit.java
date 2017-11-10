package com.antgroup.zmxy.openplatform.api.domain;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

/**
 * 关系人分析结果信息单元
有三个属性  name 指标名称   value  平均值  coverage 覆盖人数
name说明
-	芝麻分均值  avg_score 
-	6个月授权金融机构的平均值  avg_auth_fin_last_6m_cnt
-	6个月逾期的平均值  avg_ovd_order_6m_cnt
 *
 * @author auto create
 * @since 1.0, 2017-03-31 15:08:50
 */
public class ContactAnalyzeUnit extends ZhimaObject {

	private static final long serialVersionUID = 7621318217817143739L;

	/** 
	 * 关系人信息分析覆盖人数
	 */
	@ApiField("coverage")
	private String coverage;

	/** 
	 * name 指标名称, 目前有以下三个取值：
-	芝麻分均值  avg_score 
-	6个月授权金融机构的平均值  avg_auth_fin_last_6m_cnt
-	6个月逾期的平均值  avg_ovd_order_6m_cnt
	 */
	@ApiField("name")
	private String name;

	/** 
	 * 关系人分析查询 name 对应属性的分析值
	 */
	@ApiField("value")
	private String value;

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	public String getCoverage( ) {
		return this.coverage;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName( ) {
		return this.name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValue( ) {
		return this.value;
	}

}
