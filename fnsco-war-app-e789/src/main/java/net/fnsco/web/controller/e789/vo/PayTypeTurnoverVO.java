package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:39:56
 */

public class PayTypeTurnoverVO extends VO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -6139842225299775525L;
	
	@ApiModelProperty(value = "条件查询开始日期")
	private String startDate;
	
	@ApiModelProperty(value = "条件查询结束日期")
	private String endDate;
	
	/**
	 * 扫码笔数
	 */
	@ApiModelProperty(value = "扫码笔数")
	private Integer qRNum;
	
	/**
	 * 扫码交易额s
	 */
	@ApiModelProperty(value = "扫码交易额")
	private String qRTurnover;
	
	/**
	 * 台码交易笔数
	 */
	@ApiModelProperty(value = "台码交易笔数")
	private Integer taiKNum;
	
	/**
	 * 台码交易额
	 */
	@ApiModelProperty(value = "台码交易额")
	private String taiKTurnover;
	
	/**
	 * 分闪付交易笔数
	 */
	@ApiModelProperty(value = "分闪付交易笔数")
	private Integer fenshanfuNum;
	
	/**
	 * 分闪付交易额
	 */
	@ApiModelProperty(value = "分闪付交易额")
	private String fenshanfu;

	/**
	 * startDate
	 *
	 * @return  the startDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getStartDate() {
		return startDate;
	}

	/**
	 * startDate
	 *
	 * @param   startDate    the startDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * endDate
	 *
	 * @return  the endDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getEndDate() {
		return endDate;
	}

	/**
	 * endDate
	 *
	 * @param   endDate    the endDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * qRNum
	 *
	 * @return  the qRNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getqRNum() {
		return qRNum;
	}

	/**
	 * qRNum
	 *
	 * @param   qRNum    the qRNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setqRNum(Integer qRNum) {
		this.qRNum = qRNum;
	}

	/**
	 * qRTurnover
	 *
	 * @return  the qRTurnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getqRTurnover() {
		return qRTurnover;
	}

	/**
	 * qRTurnover
	 *
	 * @param   qRTurnover    the qRTurnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setqRTurnover(String qRTurnover) {
		this.qRTurnover = qRTurnover;
	}

	/**
	 * taiKNum
	 *
	 * @return  the taiKNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getTaiKNum() {
		return taiKNum;
	}

	/**
	 * taiKNum
	 *
	 * @param   taiKNum    the taiKNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTaiKNum(Integer taiKNum) {
		this.taiKNum = taiKNum;
	}

	/**
	 * taiKTurnover
	 *
	 * @return  the taiKTurnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTaiKTurnover() {
		return taiKTurnover;
	}

	/**
	 * taiKTurnover
	 *
	 * @param   taiKTurnover    the taiKTurnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTaiKTurnover(String taiKTurnover) {
		this.taiKTurnover = taiKTurnover;
	}

	/**
	 * fenshanfuNum
	 *
	 * @return  the fenshanfuNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getFenshanfuNum() {
		return fenshanfuNum;
	}

	/**
	 * fenshanfuNum
	 *
	 * @param   fenshanfuNum    the fenshanfuNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setFenshanfuNum(Integer fenshanfuNum) {
		this.fenshanfuNum = fenshanfuNum;
	}

	/**
	 * fenshanfu
	 *
	 * @return  the fenshanfu
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getFenshanfu() {
		return fenshanfu;
	}

	/**
	 * fenshanfu
	 *
	 * @param   fenshanfu    the fenshanfu to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setFenshanfu(String fenshanfu) {
		this.fenshanfu = fenshanfu;
	}
	
}
