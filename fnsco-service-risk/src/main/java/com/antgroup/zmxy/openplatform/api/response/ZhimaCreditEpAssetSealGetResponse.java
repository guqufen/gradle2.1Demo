package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.domain.AssetInfo;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.ep.asset.seal.get response.
 * 
 * @author auto create
 * @since 1.0, 2017-04-12 17:33:14
 */
public class ZhimaCreditEpAssetSealGetResponse extends ZhimaResponse {

	private static final long serialVersionUID = 7583568288583845566L;

	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 资产查封信息
	 */
	@ApiField("user_asset_seal_info")
	private AssetInfo userAssetSealInfo;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setUserAssetSealInfo(AssetInfo userAssetSealInfo) {
		this.userAssetSealInfo = userAssetSealInfo;
	}
	public AssetInfo getUserAssetSealInfo( ) {
		return this.userAssetSealInfo;
	}

}
