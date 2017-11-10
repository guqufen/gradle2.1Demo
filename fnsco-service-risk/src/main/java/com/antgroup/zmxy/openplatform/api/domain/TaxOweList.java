package com.antgroup.zmxy.openplatform.api.domain;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;

/**
 * 欠税信息列表
 *
 * @author auto create
 * @since 1.0, 2017-03-16 22:54:50
 */
public class TaxOweList extends ZhimaObject {

	private static final long serialVersionUID = 5232929423196539361L;

	/** 
	 * 欠税信息列表
	 */
	@ApiListField("tax_owe_list")
	@ApiField("tax_owe")
	private List<TaxOwe> taxOweList;

	public void setTaxOweList(List<TaxOwe> taxOweList) {
		this.taxOweList = taxOweList;
	}
	public List<TaxOwe> getTaxOweList( ) {
		return this.taxOweList;
	}

}
