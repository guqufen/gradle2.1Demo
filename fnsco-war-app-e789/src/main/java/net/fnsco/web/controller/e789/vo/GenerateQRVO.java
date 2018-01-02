package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class GenerateQRVO extends VO {
	
	
    @ApiModelProperty(value = "二维码url", name = "url", example = "weixin://wxpay/bizpayurl?pr=d2qgy0w")
    private String url;
    
    @ApiModelProperty(value = "商户订单号", name = "orderNo", example = "商户系统内部的订单号 M ,32 个字符内，有后台生成")
    private String orderNo;
    
    @ApiModelProperty(value = "返回码", name = "respCode")
    private String respCode;

  

	/**
	 * respCode
	 *
	 * @return  the respCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRespCode() {
		return respCode;
	}

	/**
	 * respCode
	 *
	 * @param   respCode    the respCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * orderNo
	 *
	 * @return  the orderNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNo
	 *
	 * @param   orderNo    the orderNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
     * url
     *
     * @return  the url
     * @since   CodingExample Ver 1.0
    */

    public String getUrl() {
        return url;
    }

    /**
     * url
     *
     * @param   url    the url to set
     * @since   CodingExample Ver 1.0
     */

    public void setUrl(String url) {
        this.url = url;
    }


}
