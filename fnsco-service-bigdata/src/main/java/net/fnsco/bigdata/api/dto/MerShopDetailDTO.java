package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午3:48:38
 */

public class MerShopDetailDTO extends DTO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商户店铺名称
	 */
	private String shopName;
	
	/**
	 * 店铺地址
	 */
	private String address;
	
	/**
	 * 店铺面积
	 */
	private String area;
	
	private String shopInnerCode;
	
	/**
	 * shopInnerCode
	 *
	 * @return  the shopInnerCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getShopInnerCode() {
		return shopInnerCode;
	}

	/**
	 * shopInnerCode
	 *
	 * @param   shopInnerCode    the shopInnerCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setShopInnerCode(String shopInnerCode) {
		this.shopInnerCode = shopInnerCode;
	}

	/**
	 * shopName
	 *
	 * @return  the shopName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getShopName() {
		return shopName;
	}

	/**
	 * shopName
	 *
	 * @param   shopName    the shopName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * address
	 *
	 * @return  the address
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getAddress() {
		return address;
	}

	/**
	 * address
	 *
	 * @param   address    the address to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * area
	 *
	 * @return  the area
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getArea() {
		return area;
	}

	/**
	 * area
	 *
	 * @param   area    the area to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setArea(String area) {
		this.area = area;
	}

}
