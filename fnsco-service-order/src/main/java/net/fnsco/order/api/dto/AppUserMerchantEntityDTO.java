package net.fnsco.order.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月18日 下午2:18:21
 */

public class AppUserMerchantEntityDTO extends DTO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 7871636185976655285L;
	
	private Integer id;
	
	/**
     * 用户名称
     */
    private String  userName;
    
    private String mobile;
    
    private Date regTime;
    
    private String regTimeStr;
    /**
     * 店铺名称集合字符串拼接
     */
    private String merNames;
    
    private String  idCardNumber;

	/**
	 * id
	 *
	 * @return  the id
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getId() {
		return id;
	}

	/**
	 * id
	 *
	 * @param   id    the id to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * userName
	 *
	 * @return  the userName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getUserName() {
		return userName;
	}

	/**
	 * userName
	 *
	 * @param   userName    the userName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * mobile
	 *
	 * @return  the mobile
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMobile() {
		return mobile;
	}

	/**
	 * mobile
	 *
	 * @param   mobile    the mobile to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * regTime
	 *
	 * @return  the regTime
	 * @since   CodingExample Ver 1.0
	*/
	
	public Date getRegTime() {
		return regTime;
	}

	/**
	 * regTime
	 *
	 * @param   regTime    the regTime to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	/**
	 * regTimeStr
	 *
	 * @return  the regTimeStr
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRegTimeStr() {
		return regTimeStr;
	}

	/**
	 * regTimeStr
	 *
	 * @param   regTimeStr    the regTimeStr to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRegTimeStr(String regTimeStr) {
		this.regTimeStr = regTimeStr;
	}

	/**
	 * merNames
	 *
	 * @return  the merNames
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMerNames() {
		return merNames;
	}

	/**
	 * merNames
	 *
	 * @param   merNames    the merNames to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMerNames(String merNames) {
		this.merNames = merNames;
	}

	/**
	 * idCardNumber
	 *
	 * @return  the idCardNumber
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getIdCardNumber() {
		return idCardNumber;
	}

	/**
	 * idCardNumber
	 *
	 * @param   idCardNumber    the idCardNumber to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
    
}
