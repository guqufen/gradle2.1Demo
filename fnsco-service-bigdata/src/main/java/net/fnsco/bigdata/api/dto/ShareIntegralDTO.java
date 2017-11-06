package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年11月6日 下午2:31:27
 */

public class ShareIntegralDTO extends DTO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -4311952190195183640L;
	
	/**
	 * 实体商户内部商户号
	 */
	private String entityInnerCode;
	
	/**
	 * 分享类型 ("00":新浪微博,"01":微信聊天,"02":微信朋友圈,"04":QQ聊天,"05":QQ空间)
	 */
	private String shareType;

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
	
}
