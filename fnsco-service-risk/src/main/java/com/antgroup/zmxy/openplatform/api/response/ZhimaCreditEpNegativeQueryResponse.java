package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.domain.AssetInfo;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.ep.negative.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-05-16 15:50:38
 */
public class ZhimaCreditEpNegativeQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 3515419416433867734L;

	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 负面信息
	 */
	@ApiField("negative_info")
	private AssetInfo negativeInfo;

	/** 
	 * 当前页数
	 */
	@ApiField("page_num")
	private String pageNum;

	/** 
	 * 翻页每页的条数
	 */
	@ApiField("range")
	private String range;

	/** 
	 * 记录总条数
	 */
	@ApiField("total_count")
	private String totalCount;

	/** 
	 * 总页数
	 */
	@ApiField("total_page_num")
	private String totalPageNum;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setNegativeInfo(AssetInfo negativeInfo) {
		this.negativeInfo = negativeInfo;
	}
	public AssetInfo getNegativeInfo( ) {
		return this.negativeInfo;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageNum( ) {
		return this.pageNum;
	}

	public void setRange(String range) {
		this.range = range;
	}
	public String getRange( ) {
		return this.range;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalCount( ) {
		return this.totalCount;
	}

	public void setTotalPageNum(String totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public String getTotalPageNum( ) {
		return this.totalPageNum;
	}

}
