package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc 商户实体DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午3:27:27
 */

public class MerchantShopDTO extends DTO{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 实体商户内部商户号
	 */
	private String entityInnerCode;
	
	/**
	 * 实体商户名称
	 */
	private String mercName;
	
	/**
	 * 店铺列表
	 */
	private List<MerShopDetailDTO> entitys;


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

	/**
	 * mercName
	 *
	 * @return  the mercName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMercName() {
		return mercName;
	}

	/**
	 * mercName
	 *
	 * @param   mercName    the mercName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMercName(String mercName) {
		this.mercName = mercName;
	}

	/**
	 * entitys
	 *
	 * @return  the entitys
	 * @since   CodingExample Ver 1.0
	*/
	
	public List<MerShopDetailDTO> getEntitys() {
		return entitys;
	}

	/**
	 * entitys
	 *
	 * @param   entitys    the entitys to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setEntitys(List<MerShopDetailDTO> entitys) {
		this.entitys = entitys;
	}
	
}
