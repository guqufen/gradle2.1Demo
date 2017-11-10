package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.image.upload response.
 * 
 * @author auto create
 * @since 1.0, 2017-03-02 14:53:50
 */
public class ZhimaMerchantImageUploadResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8112265225531696452L;

	/** 
	 * 图片在芝麻存储中的url地址
	 */
	@ApiField("image_id")
	private String imageId;

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageId( ) {
		return this.imageId;
	}

}
