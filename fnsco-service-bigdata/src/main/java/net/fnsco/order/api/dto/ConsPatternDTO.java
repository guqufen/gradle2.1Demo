package net.fnsco.order.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

/**
 * 
 * @desc 消费模式数据
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年8月5日 下午4:41:05
 *
 */
public class ConsPatternDTO extends DTO {
	/**
     * 交易总笔数
     */
    private Integer orderNumTotal;
    
    /**
     * 交易总金额
     */
    private BigDecimal turnoverTotal;
    
    /**
     * 查询开始日期
     */
    private String startDate;
    
    /**
     * 查询结束日期
     */
    private String endDate;
    private Integer    wxOrderNumTot;   //  微信总笔数
    private BigDecimal wxTurnoverTot;   //   微信总金额
    private Integer    aliOrderNumTot;  // 支付宝总笔数
    private BigDecimal aliTurnoverTot;  // 支付宝总金额
    private Integer    bankOrderNumTot; //银行卡总笔数
    private BigDecimal bankTurnoverTot; // 银行卡总金额
    private Integer    otherOrderNumTot;//   其他总笔数
    private BigDecimal otherTurnoverTot;//   其他总金额

    public Integer getOrderNumTotal() {
		return orderNumTotal;
	}

	public void setOrderNumTotal(Integer orderNumTotal) {
		this.orderNumTotal = orderNumTotal;
	}

	public BigDecimal getTurnoverTotal() {
		return turnoverTotal;
	}

	public void setTurnoverTotal(BigDecimal turnoverTotal) {
		this.turnoverTotal = turnoverTotal;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
     * wxOrderNumTot
     *
     * @return  the wxOrderNumTot
     * @since   CodingExample Ver 1.0
    */

    public Integer getWxOrderNumTot() {
        return wxOrderNumTot;
    }

    /**
     * wxOrderNumTot
     *
     * @param   wxOrderNumTot    the wxOrderNumTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setWxOrderNumTot(Integer wxOrderNumTot) {
        this.wxOrderNumTot = wxOrderNumTot;
    }

    /**
     * wxTurnoverTot
     *
     * @return  the wxTurnoverTot
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getWxTurnoverTot() {
        return wxTurnoverTot;
    }

    /**
     * wxTurnoverTot
     *
     * @param   wxTurnoverTot    the wxTurnoverTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setWxTurnoverTot(BigDecimal wxTurnoverTot) {
        this.wxTurnoverTot = wxTurnoverTot;
    }

    /**
     * aliOrderNumTot
     *
     * @return  the aliOrderNumTot
     * @since   CodingExample Ver 1.0
    */

    public Integer getAliOrderNumTot() {
        return aliOrderNumTot;
    }

    /**
     * aliOrderNumTot
     *
     * @param   aliOrderNumTot    the aliOrderNumTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setAliOrderNumTot(Integer aliOrderNumTot) {
        this.aliOrderNumTot = aliOrderNumTot;
    }

    /**
     * aliTurnoverTot
     *
     * @return  the aliTurnoverTot
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getAliTurnoverTot() {
        return aliTurnoverTot;
    }

    /**
     * aliTurnoverTot
     *
     * @param   aliTurnoverTot    the aliTurnoverTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setAliTurnoverTot(BigDecimal aliTurnoverTot) {
        this.aliTurnoverTot = aliTurnoverTot;
    }

    /**
     * bankOrderNumTot
     *
     * @return  the bankOrderNumTot
     * @since   CodingExample Ver 1.0
    */

    public Integer getBankOrderNumTot() {
        return bankOrderNumTot;
    }

    /**
     * bankOrderNumTot
     *
     * @param   bankOrderNumTot    the bankOrderNumTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setBankOrderNumTot(Integer bankOrderNumTot) {
        this.bankOrderNumTot = bankOrderNumTot;
    }

    /**
     * bankTurnoverTot
     *
     * @return  the bankTurnoverTot
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getBankTurnoverTot() {
        return bankTurnoverTot;
    }

    /**
     * bankTurnoverTot
     *
     * @param   bankTurnoverTot    the bankTurnoverTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setBankTurnoverTot(BigDecimal bankTurnoverTot) {
        this.bankTurnoverTot = bankTurnoverTot;
    }

    /**
     * otherOrderNumTot
     *
     * @return  the otherOrderNumTot
     * @since   CodingExample Ver 1.0
    */

    public Integer getOtherOrderNumTot() {
        return otherOrderNumTot;
    }

    /**
     * otherOrderNumTot
     *
     * @param   otherOrderNumTot    the otherOrderNumTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setOtherOrderNumTot(Integer otherOrderNumTot) {
        this.otherOrderNumTot = otherOrderNumTot;
    }

    /**
     * otherTurnoverTot
     *
     * @return  the otherTurnoverTot
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getOtherTurnoverTot() {
        return otherTurnoverTot;
    }

    /**
     * otherTurnoverTot
     *
     * @param   otherTurnoverTot    the otherTurnoverTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setOtherTurnoverTot(BigDecimal otherTurnoverTot) {
        this.otherTurnoverTot = otherTurnoverTot;
    }

}
