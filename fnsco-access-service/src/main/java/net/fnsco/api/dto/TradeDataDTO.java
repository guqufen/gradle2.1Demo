package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class TradeDataDTO extends DTO{

    private String pay_tp;      //支付方式    
    private String order_no;    //订单号 
    private String batchbillno; //批次流水号   
    private String time_stamp;  //交易时间戳   
    private String reason;      //失败原因     
    private String remark;      //附加数据    

    private String merid;       //  商户号
    private String termid;      //  终端号
    private String batchno;     //  批次号
    private String systraceno;  //  凭证号
    private String authcode;    //  授权号
    private String orderid_scan;//  扫码订单号
    /**
     * @return the pay_tp
     */
    public String getPay_tp() {
        return pay_tp;
    }
    /**
     * @param pay_tp the pay_tp to set
     */
    public void setPay_tp(String pay_tp) {
        this.pay_tp = pay_tp;
    }
    /**
     * @return the order_no
     */
    public String getOrder_no() {
        return order_no;
    }
    /**
     * @param order_no the order_no to set
     */
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    /**
     * @return the batchbillno
     */
    public String getBatchbillno() {
        return batchbillno;
    }
    /**
     * @param batchbillno the batchbillno to set
     */
    public void setBatchbillno(String batchbillno) {
        this.batchbillno = batchbillno;
    }
    /**
     * @return the time_stamp
     */
    public String getTime_stamp() {
        return time_stamp;
    }
    /**
     * @param time_stamp the time_stamp to set
     */
    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }
    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @return the merid
     */
    public String getMerid() {
        return merid;
    }
    /**
     * @param merid the merid to set
     */
    public void setMerid(String merid) {
        this.merid = merid;
    }
    /**
     * @return the termid
     */
    public String getTermid() {
        return termid;
    }
    /**
     * @param termid the termid to set
     */
    public void setTermid(String termid) {
        this.termid = termid;
    }
    /**
     * @return the batchno
     */
    public String getBatchno() {
        return batchno;
    }
    /**
     * @param batchno the batchno to set
     */
    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
    /**
     * @return the systraceno
     */
    public String getSystraceno() {
        return systraceno;
    }
    /**
     * @param systraceno the systraceno to set
     */
    public void setSystraceno(String systraceno) {
        this.systraceno = systraceno;
    }
    /**
     * @return the authcode
     */
    public String getAuthcode() {
        return authcode;
    }
    /**
     * @param authcode the authcode to set
     */
    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }
    /**
     * @return the orderid_scan
     */
    public String getOrderid_scan() {
        return orderid_scan;
    }
    /**
     * @param orderid_scan the orderid_scan to set
     */
    public void setOrderid_scan(String orderid_scan) {
        this.orderid_scan = orderid_scan;
    }
    

}
