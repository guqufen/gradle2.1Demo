package net.fnsco.trading.service.pay.channel.zxyh.dto;

/*功能介绍2.1.8 微信主扫消费
步骤（1）：进入商家网页，用户挑选需购买商
品，选择支付方式为扫码支付，发起购买流程；
步骤（2）：商户调用本接口，得到二维码URL；
步骤（3）：商户根据URL生成二维码，供用户扫码支付；
步骤（4）：用户扫码并支付成功，商户后台得到支付成功的通知。

*/
public class ActiveWeiXinDTO {
	 public void init(String merId) {
	        this.setSignMethod("02");//02-MD5  03-RSA
	        this.setTxnType("01");//消费
	        this.setTxnSubType("010130");//主扫码消费
	        this.setChannelType("6002");//商户互联网渠道
	        this.setMerId(merId);//普通商户或一级商户的商户号
	        this.setPayAccessType("02");
	        this.setCurrencyType("156"); //默认是156：人民币

	    }
	
	
	
	
    private String encoding;                  //  编码方式        M   String(10)  UTF-8
    private String signMethod;                // 签名方法      M   String(2)   签名方式：
    //    02：MD5
    //    03：RSA
    private String signAture;                 //签名  M   String(1024)    填写对报文摘要的签名
    private String txnType;                   // 交易类型 M   String(2)   01：消费；
    private String txnSubType;                //交易子类型 M   String(6)   010302：支付宝二清主扫码消费
    private String channelType;               //接入渠道 M   String(4)   6002：商户互联网渠道;
    private String payAccessType;			//02：接口支付
    private String backEndUrl;                //后台通知地址 M   String(512) 接收支付网关异步通知回调地址，POST方式
    private String merId;                     //商户编号  M   String(15)  普通商户或一级商户的商户号
    private String secMerId;                  // 分账子商户号   C   String(15)  使用分账功能时上传，是与merId关联的分账子商户号
    private String termId;                    // 终端编号 C   String(8)   区分终端，商户自定义
    private String termIp;                    //客户端真实IP M   String(16)  发起支付的客户端真实IP
    private String orderId;                   //商户订单号  M   String(32)  商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
    private String orderTime;                 //交易起始时间   M   String(14)  订单生成时间，格式为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒表示为20091225091010
    private String productId;					//商品ID C 此id为二维码中包含的商品ID，商户自行定义。
    private String orderBody;                 //商品描述  M   String(20)  商品或支付单简要描述
    private String orderDetail;               //商品详细 C   String(100) 商品详细描述
    private String orderGoodsTag;				//商品标记，代金券或立减优惠功能的参数
    
    private String txnAmt;                    //交易金额  M   String(12)  订单总金额(交易单位为分，例:1.23元=123)，只能整数
    private String currencyType;              //交易币种     M   String(3)   默认是156：人民币
    private String accountFlag;               //分账标识 C   String(1)   使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号secMerId必传Y-分账 其它或不传-不分账
    private String secMerFeeRate;             // 分账子商户交易费率 C   String(16)  使用分账功能时上传，是分账子商户secMerId的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户merId的费率低
    private String attach;						// 附加数据 c 仅微信、QQ钱包适用；附加数据，在支付通知中原样返回，该字段主要用于商户携带订单的自定义数据，要求格式如下： bank_mch_name=xxxxx&bank_mch_id=xxxx&商户自定义参数
    private String limitPay;					//指定支付方式	C	String(32)	仅微信适用；no_credit--指定不能使用信用卡支付
//    private String disablePayChannels;        //要禁用的信用渠道  C   String(256) 禁用支付渠道 creditCard  信用卡；credit_group所有信用渠道，包含（信用卡，花呗）

    private String needBankType;              // 是否需要获取支付行类型   C   String(1)   传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
    private String independentTransactionFlag;//独立商户交易标识    C   String(1)   传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易
    private String orderType;					//R	String(1)	京东支付必填，固定值：0或者1（0：：实物，1：虚拟）

    public String getPayAccessType() {
		return payAccessType;
	}

	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderGoodsTag() {
		return orderGoodsTag;
	}

	public void setOrderGoodsTag(String orderGoodsTag) {
		this.orderGoodsTag = orderGoodsTag;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
     * encoding
     *
     * @return  the encoding
     * @since   CodingExample Ver 1.0
    */

    public String getEncoding() {
        return encoding;
    }

    /**
     * encoding
     *
     * @param   encoding    the encoding to set
     * @since   CodingExample Ver 1.0
     */

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * signMethod
     *
     * @return  the signMethod
     * @since   CodingExample Ver 1.0
    */

    public String getSignMethod() {
        return signMethod;
    }

    /**
     * signMethod
     *
     * @param   signMethod    the signMethod to set
     * @since   CodingExample Ver 1.0
     */

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    /**
     * signAture
     *
     * @return  the signAture
     * @since   CodingExample Ver 1.0
    */

    public String getSignAture() {
        return signAture;
    }

    /**
     * signAture
     *
     * @param   signAture    the signAture to set
     * @since   CodingExample Ver 1.0
     */

    public void setSignAture(String signAture) {
        this.signAture = signAture;
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
     * txnSubType
     *
     * @return  the txnSubType
     * @since   CodingExample Ver 1.0
    */

    public String getTxnSubType() {
        return txnSubType;
    }

    /**
     * txnSubType
     *
     * @param   txnSubType    the txnSubType to set
     * @since   CodingExample Ver 1.0
     */

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
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

    /**
     * backEndUrl
     *
     * @return  the backEndUrl
     * @since   CodingExample Ver 1.0
    */

    public String getBackEndUrl() {
        return backEndUrl;
    }

    /**
     * backEndUrl
     *
     * @param   backEndUrl    the backEndUrl to set
     * @since   CodingExample Ver 1.0
     */

    public void setBackEndUrl(String backEndUrl) {
        this.backEndUrl = backEndUrl;
    }

    /**
     * merId
     *
     * @return  the merId
     * @since   CodingExample Ver 1.0
    */

    public String getMerId() {
        return merId;
    }

    /**
     * merId
     *
     * @param   merId    the merId to set
     * @since   CodingExample Ver 1.0
     */

    public void setMerId(String merId) {
        this.merId = merId;
    }

    /**
     * secMerId
     *
     * @return  the secMerId
     * @since   CodingExample Ver 1.0
    */

    public String getSecMerId() {
        return secMerId;
    }

    /**
     * secMerId
     *
     * @param   secMerId    the secMerId to set
     * @since   CodingExample Ver 1.0
     */

    public void setSecMerId(String secMerId) {
        this.secMerId = secMerId;
    }

    /**
     * termId
     *
     * @return  the termId
     * @since   CodingExample Ver 1.0
    */

    public String getTermId() {
        return termId;
    }

    /**
     * termId
     *
     * @param   termId    the termId to set
     * @since   CodingExample Ver 1.0
     */

    public void setTermId(String termId) {
        this.termId = termId;
    }

    /**
     * termIp
     *
     * @return  the termIp
     * @since   CodingExample Ver 1.0
    */

    public String getTermIp() {
        return termIp;
    }

    /**
     * termIp
     *
     * @param   termIp    the termIp to set
     * @since   CodingExample Ver 1.0
     */

    public void setTermIp(String termIp) {
        this.termIp = termIp;
    }

    /**
     * orderId
     *
     * @return  the orderId
     * @since   CodingExample Ver 1.0
    */

    public String getOrderId() {
        return orderId;
    }

    /**
     * orderId
     *
     * @param   orderId    the orderId to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * orderTime
     *
     * @return  the orderTime
     * @since   CodingExample Ver 1.0
    */

    public String getOrderTime() {
        return orderTime;
    }

    /**
     * orderTime
     *
     * @param   orderTime    the orderTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * orderBody
     *
     * @return  the orderBody
     * @since   CodingExample Ver 1.0
    */

    public String getOrderBody() {
        return orderBody;
    }

    /**
     * orderBody
     *
     * @param   orderBody    the orderBody to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderBody(String orderBody) {
        this.orderBody = orderBody;
    }

    /**
     * orderDetail
     *
     * @return  the orderDetail
     * @since   CodingExample Ver 1.0
    */

    public String getOrderDetail() {
        return orderDetail;
    }

    /**
     * orderDetail
     *
     * @param   orderDetail    the orderDetail to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    /**
     * txnAmt
     *
     * @return  the txnAmt
     * @since   CodingExample Ver 1.0
    */

    public String getTxnAmt() {
        return txnAmt;
    }

    /**
     * txnAmt
     *
     * @param   txnAmt    the txnAmt to set
     * @since   CodingExample Ver 1.0
     */

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    /**
     * currencyType
     *
     * @return  the currencyType
     * @since   CodingExample Ver 1.0
    */

    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * currencyType
     *
     * @param   currencyType    the currencyType to set
     * @since   CodingExample Ver 1.0
     */

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * accountFlag
     *
     * @return  the accountFlag
     * @since   CodingExample Ver 1.0
    */

    public String getAccountFlag() {
        return accountFlag;
    }

    /**
     * accountFlag
     *
     * @param   accountFlag    the accountFlag to set
     * @since   CodingExample Ver 1.0
     */

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    /**
     * secMerFeeRate
     *
     * @return  the secMerFeeRate
     * @since   CodingExample Ver 1.0
    */

    public String getSecMerFeeRate() {
        return secMerFeeRate;
    }

    /**
     * secMerFeeRate
     *
     * @param   secMerFeeRate    the secMerFeeRate to set
     * @since   CodingExample Ver 1.0
     */

    public void setSecMerFeeRate(String secMerFeeRate) {
        this.secMerFeeRate = secMerFeeRate;
    }

    /**
     * needBankType
     *
     * @return  the needBankType
     * @since   CodingExample Ver 1.0
    */

    public String getNeedBankType() {
        return needBankType;
    }

    /**
     * needBankType
     *
     * @param   needBankType    the needBankType to set
     * @since   CodingExample Ver 1.0
     */

    public void setNeedBankType(String needBankType) {
        this.needBankType = needBankType;
    }

    /**
     * independentTransactionFlag
     *
     * @return  the independentTransactionFlag
     * @since   CodingExample Ver 1.0
    */

    public String getIndependentTransactionFlag() {
        return independentTransactionFlag;
    }

    /**
     * independentTransactionFlag
     *
     * @param   independentTransactionFlag    the independentTransactionFlag to set
     * @since   CodingExample Ver 1.0
     */

    public void setIndependentTransactionFlag(String independentTransactionFlag) {
        this.independentTransactionFlag = independentTransactionFlag;
    }

}
