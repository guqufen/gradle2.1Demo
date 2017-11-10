package com.antgroup.zmxy.openplatform.api.domain;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;

/**
 * 税务信息
 *
 * @author auto create
 * @since 1.0, 2017-08-06 00:21:45
 */
public class TaxInfo extends ZhimaObject {

	private static final long serialVersionUID = 4499453224341879379L;

	/** 
	 * null
	 */
	@ApiListField("tax_invoice")
	@ApiField("ep_info")
	private List<EpInfo> taxInvoice;

	public void setTaxInvoice(List<EpInfo> taxInvoice) {
		this.taxInvoice = taxInvoice;
	}
	public List<EpInfo> getTaxInvoice( ) {
		return this.taxInvoice;
	}

}
