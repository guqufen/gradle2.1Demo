package net.fnsco.bigdata.api.dto;

import java.util.Date;
import java.util.List;

import net.fnsco.core.base.DTO;

public class TradeDataDTO extends DTO {

    private String amt;          //金额，格式为“100.01”
    private String orderNo;      // 商户订单号（不唯一）
    private String orderTime;    // 订单时间戳
    private String orderInfo;    // 订单信息 
    private String batchBillNo;  // 撤销时会有  无用**

    private String payType;      // 支付方式
    private String referNo;      // 检索参考号 无用**
    private String timeStamp;    // 交易时间戳，拉卡拉完成时间
    private String tradeDetail;  // 交易详情（JSON串）

    private String merId;        // 商户号
    private String termId;       // 终端号
    private String batchNo;      // 批次号
    private String sysTraceNo;   // 凭证号
    private String authCode;     // 授权码
    private String orderIdScan;  // 扫码交易的订单号
    private String source;       //流水来源00拉卡拉机器
    private String md5;          //参数md5值
    private String sendTime;     //发送时间
    private String validate;     //是否校验1验证0不验证
    private String paySubType;   //---支付子类型

    private String startSendTime;//条件查询 发送开始时间
    private String endSendTime;  //条件查询 发送结束时间

    private String startTime;    //条件查询 交易开始时间
    private String endTime;      //条件查询 交易结束时间

    private String txnType;      //交易类型1消费2撤销

    private String respCode;    //应答码

    private String cardNo;       //卡号
    private String cardOrg;      // :卡组织，00 境内借记卡 ；01 境内贷记卡 ； 60 境外借记卡 ； 61 境外贷记卡
    
    private String 	innerCode;
    private String 	txnSubType;
    private String 	currency;
    private String 	settleDate;
    private String	dcType;
    private String	certifyId;
    private String	msgDestId;
    private String	payTimeOut;
    private String	subject;
    private String	body;
    private String	customerInfo;
    private String	customerIp;
    private String	tn;
    private String	respMsg;
    private String	succTime;
    private Date 	createTime;
    
    private String  channelTermCode;
    private String  legalPerson;
    
    public String getDcType() {
		return dcType;
	}

	public void setDcType(String dcType) {
		this.dcType = dcType;
	}

	public String getCertifyId() {
		return certifyId;
	}

	public void setCertifyId(String certifyId) {
		this.certifyId = certifyId;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
     * channelTermCode
     *
     * @return  the channelTermCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getChannelTermCode() {
        return channelTermCode;
    }

    /**
     * channelTermCode
     *
     * @param   channelTermCode    the channelTermCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setChannelTermCode(String channelTermCode) {
        this.channelTermCode = channelTermCode;
    }

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getTxnSubType() {
		return txnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getMsgDestId() {
		return msgDestId;
	}

	public void setMsgDestId(String msgDestId) {
		this.msgDestId = msgDestId;
	}

	public String getPayTimeOut() {
		return payTimeOut;
	}

	public void setPayTimeOut(String payTimeOut) {
		this.payTimeOut = payTimeOut;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getSuccTime() {
		return succTime;
	}

	public void setSuccTime(String succTime) {
		this.succTime = succTime;
	}

	
    private String merName;      //商户名称

    private String payMedium;    //支付媒介00pos机01app02台码
    private String channelType;  //渠道
    private String channelLkl;  //渠道拉卡拉
    private String channelPf;  //渠道浦发
    private String channelAn;  //渠道爱农
    private String channelFns;  //渠道法奈昇
    private String channelJhf;  //渠道聚惠分

    /**
     * payMedium
     *
     * @return  the payMedium
     * @since   CodingExample Ver 1.0
    */

    public String getPayMedium() {
        return payMedium;
    }

    /**
     * payMedium
     *
     * @param   payMedium    the payMedium to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayMedium(String payMedium) {
        this.payMedium = payMedium;
    }

    /**
     * channelType
     *
     * @return  the channelType
     * @since   CodingExample Ver 1.0
    */

    public String getChannelType() {
        return channelType;
    }

    /**
     * channelType
     *
     * @param   channelType    the channelType to set
     * @since   CodingExample Ver 1.0
     */

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }


	public String getChannelLkl() {
		return channelLkl;
	}

	public void setChannelLkl(String channelLkl) {
		this.channelLkl = channelLkl;
	}

	public String getChannelPf() {
		return channelPf;
	}

	public void setChannelPf(String channelPf) {
		this.channelPf = channelPf;
	}

	public String getChannelAn() {
		return channelAn;
	}

	public void setChannelAn(String channelAn) {
		this.channelAn = channelAn;
	}

	public String getChannelFns() {
		return channelFns;
	}

	public void setChannelFns(String channelFns) {
		this.channelFns = channelFns;
	}

	public String getChannelJhf() {
		return channelJhf;
	}

	public void setChannelJhf(String channelJhf) {
		this.channelJhf = channelJhf;
	}

	/**
>>>>>>> 66c23d160a3a16ae1deefd5afc452cde023a9778
     * startTime
     *
     * @return  the startTime
     * @since   CodingExample Ver 1.0
    */

    public String getStartTime() {
        return startTime;
    }

    /**
     * startTime
     *
     * @param   startTime    the startTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * endTime
     *
     * @return  the endTime
     * @since   CodingExample Ver 1.0
    */

    public String getEndTime() {
        return endTime;
    }

    /**
     * endTime
     *
     * @param   endTime    the endTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * merName
     *
     * @return  the merName
     * @since   CodingExample Ver 1.0
    */

    public String getMerName() {
        return merName;
    }

    /**
     * merName
     *
     * @param   merName    the merName to set
     * @since   CodingExample Ver 1.0
     */

    public void setMerName(String merName) {
        this.merName = merName;
    }

    /**
     * cardOrg
     *
     * @return  the cardOrg
     * @since   CodingExample Ver 1.0
    */

    public String getCardOrg() {
        return cardOrg;
    }

    /**
     * cardOrg
     *
     * @param   cardOrg    the cardOrg to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    /**
     * cardNo
     *
     * @return  the cardNo
     * @since   CodingExample Ver 1.0
    */

    public String getCardNo() {
        return cardNo;
    }

    /**
     * cardNo
     *
     * @param   cardNo    the cardNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * txnType
     *
     * @return  the txnType
     * @since   CodingExample Ver 1.0
    */

    public String getTxnType() {
        return txnType;
    }

    /**
     * txnType
     *
     * @param   txnType    the txnType to set
     * @since   CodingExample Ver 1.0
     */

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

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
     * @return the paySubType
     */
    public String getPaySubType() {
        return paySubType;
    }

    /**
     * @param paySubType the paySubType to set
     */
    public void setPaySubType(String paySubType) {
        this.paySubType = paySubType;
    }

    /**
     * startSendTime
     *
     * @return  the startSendTime
     * @since   CodingExample Ver 1.0
    */

    public String getStartSendTime() {
        return startSendTime;
    }

    /**
     * startSendTime
     *
     * @param   startSendTime    the startSendTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartSendTime(String startSendTime) {
        this.startSendTime = startSendTime;
    }

    /**
     * endSendTime
     *
     * @return  the endSendTime
     * @since   CodingExample Ver 1.0
    */

    public String getEndSendTime() {
        return endSendTime;
    }

    /**
     * endSendTime
     *
     * @param   endSendTime    the endSendTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndSendTime(String endSendTime) {
        this.endSendTime = endSendTime;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return the sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the validate
     */
    public String getValidate() {
        return validate;
    }

    /**
     * @param validate the validate to set
     */
    public void setValidate(String validate) {
        this.validate = validate;
    }

    /**
     * @return the amt
     */
    public String getAmt() {
        return amt;
    }

    /**
     * @param amt the amt to set
     */
    public void setAmt(String amt) {
        this.amt = amt;
    }

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return the orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return the orderInfo
     */
    public String getOrderInfo() {
        return orderInfo;
    }

    /**
     * @param orderInfo the orderInfo to set
     */
    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * @return the batchBillNo
     */
    public String getBatchBillNo() {
        return batchBillNo;
    }

    /**
     * @param batchBillNo the batchBillNo to set
     */
    public void setBatchBillNo(String batchBillNo) {
        this.batchBillNo = batchBillNo;
    }

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return the referNo
     */
    public String getReferNo() {
        return referNo;
    }

    /**
     * @param referNo the referNo to set
     */
    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the tradeDetail
     */
    public String getTradeDetail() {
        return tradeDetail;
    }

    /**
     * @param tradeDetail the tradeDetail to set
     */
    public void setTradeDetail(String tradeDetail) {
        this.tradeDetail = tradeDetail;
    }

    /**
     * @return the merId
     */
    public String getMerId() {
        return merId;
    }

    /**
     * @param merId the merId to set
     */
    public void setMerId(String merId) {
        this.merId = merId;
    }

    /**
     * @return the termId
     */
    public String getTermId() {
        return termId;
    }

    /**
     * @param termId the termId to set
     */
    public void setTermId(String termId) {
        this.termId = termId;
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * @return the sysTraceNo
     */
    public String getSysTraceNo() {
        return sysTraceNo;
    }

    /**
     * @param sysTraceNo the sysTraceNo to set
     */
    public void setSysTraceNo(String sysTraceNo) {
        this.sysTraceNo = sysTraceNo;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the orderIdScan
     */
    public String getOrderIdScan() {
        return orderIdScan;
    }

    /**
     * @param orderIdScan the orderIdScan to set
     */
    public void setOrderIdScan(String orderIdScan) {
        this.orderIdScan = orderIdScan;
    }

    //    private String pay_tp;      //支付方式    
    //    private String order_no;    //订单号 
    //    private String batchbillno; //批次流水号   
    //    private String time_stamp;  //交易时间戳   
    //    private String reason;      //失败原因     
    //    private String remark;      //附加数据    
    //
    //    private String merid;       //  商户号
    //    private String termid;      //  终端号
    //    private String batchno;     //  批次号
    //    private String systraceno;  //  凭证号
    //    private String authcode;    //  授权号
    //    private String orderid_scan;//  扫码订单号

}
