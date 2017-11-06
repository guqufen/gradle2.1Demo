package net.fnsco.bigdata.api.constant;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

/**
 * @desc  接收参数实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月31日 下午4:17:23
 */

public class MerShopDTO extends DTO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 3412390292399294153L;
	
	@ApiModelProperty(value = "用户登录账号", required = true)
    private Integer userId;     //登录用户Id
	
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
	 * 实体商户内部商户号
	 */
	private String entityInnerCode;
	
	/**
	 * 分享类型
	 */
	private String shareType;
	
	/**
	 * shareType
	 *
	 * @return  the shareType
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getShareType() {
		return shareType;
	}

	/**
	 * shareType
	 *
	 * @param   shareType    the shareType to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	/**
	 * userId
	 *
	 * @return  the userId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getUserId() {
		return userId;
	}

	/**
	 * userId
	 *
	 * @param   userId    the userId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * entityInnerCode
	 *
	 * @return  the entityInnerCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	/**
	 * entityInnerCode
	 *
	 * @param   entityInnerCode    the entityInnerCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}
	
}
