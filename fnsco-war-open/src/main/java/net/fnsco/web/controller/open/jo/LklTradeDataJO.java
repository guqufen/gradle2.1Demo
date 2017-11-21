package net.fnsco.web.controller.open.jo;

public class LklTradeDataJO {
    private String mch_id;        // 商户号
    private String device_info;   //设备号
    private String nonce_str;     //随机字符串
    private String sign;          //签名
    private String result_code;   //业务结果  
    private String err_code;      //错误代码
    private String err_code_des;  //错误代码描述
    private String openid;        //用户标识
    private String trade_type;    //    交易类型
    private String total_fee;     //总金额 

    private String transaction_id;// 商户支付订单号(流水号/参考号)
    private String out_trade_no;  //    商户订单号
    private String attach;        //商家数据包
    private String time_end;      //支付完成时间
    private String pay_type;      //支付方式(卡类型标识) 
    private String card_no;       // 交易卡号
    private String pay_amt;       //  实际支付金额
    private String batchbillno;   //批次号
    private String systraceno;    // 凭证号
    private String orderid_scan;  //    扫码订单号
    private String refernumber;   //系统参考号 
    private String bank_type;     //    付款银行
    private String fee_type;      //货币种类 
    private String cash_fee;      //现金支付金额 
    private String cash_fee_type; //现金支付货币类型
    private String coupon_fee;    //代金券或立减优惠金额
    private String coupon_count;  //代金券或立减优惠使用数量   
    private String coupon_id_$n;  //代金券或立减优惠ID
    private String coupon_fee_$n; //单个代金券或立减优惠支付金额
    private String is_subscribe;  //   是否关注公众账号 

    /**
     * mch_id
     *
     * @return  the mch_id
     * @since   CodingExample Ver 1.0
    */

    public String getMch_id() {
        return mch_id;
    }

    /**
     * mch_id
     *
     * @param   mch_id    the mch_id to set
     * @since   CodingExample Ver 1.0
     */

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    /**
     * device_info
     *
     * @return  the device_info
     * @since   CodingExample Ver 1.0
    */

    public String getDevice_info() {
        return device_info;
    }

    /**
     * device_info
     *
     * @param   device_info    the device_info to set
     * @since   CodingExample Ver 1.0
     */

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    /**
     * nonce_str
     *
     * @return  the nonce_str
     * @since   CodingExample Ver 1.0
    */

    public String getNonce_str() {
        return nonce_str;
    }

    /**
     * nonce_str
     *
     * @param   nonce_str    the nonce_str to set
     * @since   CodingExample Ver 1.0
     */

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    /**
     * sign
     *
     * @return  the sign
     * @since   CodingExample Ver 1.0
    */

    public String getSign() {
        return sign;
    }

    /**
     * sign
     *
     * @param   sign    the sign to set
     * @since   CodingExample Ver 1.0
     */

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * result_code
     *
     * @return  the result_code
     * @since   CodingExample Ver 1.0
    */

    public String getResult_code() {
        return result_code;
    }

    /**
     * result_code
     *
     * @param   result_code    the result_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    /**
     * err_code
     *
     * @return  the err_code
     * @since   CodingExample Ver 1.0
    */

    public String getErr_code() {
        return err_code;
    }

    /**
     * err_code
     *
     * @param   err_code    the err_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    /**
     * err_code_des
     *
     * @return  the err_code_des
     * @since   CodingExample Ver 1.0
    */

    public String getErr_code_des() {
        return err_code_des;
    }

    /**
     * err_code_des
     *
     * @param   err_code_des    the err_code_des to set
     * @since   CodingExample Ver 1.0
     */

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    /**
     * openid
     *
     * @return  the openid
     * @since   CodingExample Ver 1.0
    */

    public String getOpenid() {
        return openid;
    }

    /**
     * openid
     *
     * @param   openid    the openid to set
     * @since   CodingExample Ver 1.0
     */

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * trade_type
     *
     * @return  the trade_type
     * @since   CodingExample Ver 1.0
    */

    public String getTrade_type() {
        return trade_type;
    }

    /**
     * trade_type
     *
     * @param   trade_type    the trade_type to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    /**
     * total_fee
     *
     * @return  the total_fee
     * @since   CodingExample Ver 1.0
    */

    public String getTotal_fee() {
        return total_fee;
    }

    /**
     * total_fee
     *
     * @param   total_fee    the total_fee to set
     * @since   CodingExample Ver 1.0
     */

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    /**
     * transaction_id
     *
     * @return  the transaction_id
     * @since   CodingExample Ver 1.0
    */

    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * transaction_id
     *
     * @param   transaction_id    the transaction_id to set
     * @since   CodingExample Ver 1.0
     */

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * out_trade_no
     *
     * @return  the out_trade_no
     * @since   CodingExample Ver 1.0
    */

    public String getOut_trade_no() {
        return out_trade_no;
    }

    /**
     * out_trade_no
     *
     * @param   out_trade_no    the out_trade_no to set
     * @since   CodingExample Ver 1.0
     */

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    /**
     * attach
     *
     * @return  the attach
     * @since   CodingExample Ver 1.0
    */

    public String getAttach() {
        return attach;
    }

    /**
     * attach
     *
     * @param   attach    the attach to set
     * @since   CodingExample Ver 1.0
     */

    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * time_end
     *
     * @return  the time_end
     * @since   CodingExample Ver 1.0
    */

    public String getTime_end() {
        return time_end;
    }

    /**
     * time_end
     *
     * @param   time_end    the time_end to set
     * @since   CodingExample Ver 1.0
     */

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    /**
     * pay_type
     *
     * @return  the pay_type
     * @since   CodingExample Ver 1.0
    */

    public String getPay_type() {
        return pay_type;
    }

    /**
     * pay_type
     *
     * @param   pay_type    the pay_type to set
     * @since   CodingExample Ver 1.0
     */

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    /**
     * card_no
     *
     * @return  the card_no
     * @since   CodingExample Ver 1.0
    */

    public String getCard_no() {
        return card_no;
    }

    /**
     * card_no
     *
     * @param   card_no    the card_no to set
     * @since   CodingExample Ver 1.0
     */

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    /**
     * pay_amt
     *
     * @return  the pay_amt
     * @since   CodingExample Ver 1.0
    */

    public String getPay_amt() {
        return pay_amt;
    }

    /**
     * pay_amt
     *
     * @param   pay_amt    the pay_amt to set
     * @since   CodingExample Ver 1.0
     */

    public void setPay_amt(String pay_amt) {
        this.pay_amt = pay_amt;
    }

    /**
     * batchbillno
     *
     * @return  the batchbillno
     * @since   CodingExample Ver 1.0
    */

    public String getBatchbillno() {
        return batchbillno;
    }

    /**
     * batchbillno
     *
     * @param   batchbillno    the batchbillno to set
     * @since   CodingExample Ver 1.0
     */

    public void setBatchbillno(String batchbillno) {
        this.batchbillno = batchbillno;
    }

    /**
     * systraceno
     *
     * @return  the systraceno
     * @since   CodingExample Ver 1.0
    */

    public String getSystraceno() {
        return systraceno;
    }

    /**
     * systraceno
     *
     * @param   systraceno    the systraceno to set
     * @since   CodingExample Ver 1.0
     */

    public void setSystraceno(String systraceno) {
        this.systraceno = systraceno;
    }

    /**
     * orderid_scan
     *
     * @return  the orderid_scan
     * @since   CodingExample Ver 1.0
    */

    public String getOrderid_scan() {
        return orderid_scan;
    }

    /**
     * orderid_scan
     *
     * @param   orderid_scan    the orderid_scan to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderid_scan(String orderid_scan) {
        this.orderid_scan = orderid_scan;
    }

    /**
     * refernumber
     *
     * @return  the refernumber
     * @since   CodingExample Ver 1.0
    */

    public String getRefernumber() {
        return refernumber;
    }

    /**
     * refernumber
     *
     * @param   refernumber    the refernumber to set
     * @since   CodingExample Ver 1.0
     */

    public void setRefernumber(String refernumber) {
        this.refernumber = refernumber;
    }

    /**
     * bank_type
     *
     * @return  the bank_type
     * @since   CodingExample Ver 1.0
    */

    public String getBank_type() {
        return bank_type;
    }

    /**
     * bank_type
     *
     * @param   bank_type    the bank_type to set
     * @since   CodingExample Ver 1.0
     */

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    /**
     * fee_type
     *
     * @return  the fee_type
     * @since   CodingExample Ver 1.0
    */

    public String getFee_type() {
        return fee_type;
    }

    /**
     * fee_type
     *
     * @param   fee_type    the fee_type to set
     * @since   CodingExample Ver 1.0
     */

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    /**
     * cash_fee
     *
     * @return  the cash_fee
     * @since   CodingExample Ver 1.0
    */

    public String getCash_fee() {
        return cash_fee;
    }

    /**
     * cash_fee
     *
     * @param   cash_fee    the cash_fee to set
     * @since   CodingExample Ver 1.0
     */

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    /**
     * cash_fee_type
     *
     * @return  the cash_fee_type
     * @since   CodingExample Ver 1.0
    */

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    /**
     * cash_fee_type
     *
     * @param   cash_fee_type    the cash_fee_type to set
     * @since   CodingExample Ver 1.0
     */

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    /**
     * coupon_fee
     *
     * @return  the coupon_fee
     * @since   CodingExample Ver 1.0
    */

    public String getCoupon_fee() {
        return coupon_fee;
    }

    /**
     * coupon_fee
     *
     * @param   coupon_fee    the coupon_fee to set
     * @since   CodingExample Ver 1.0
     */

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    /**
     * coupon_count
     *
     * @return  the coupon_count
     * @since   CodingExample Ver 1.0
    */

    public String getCoupon_count() {
        return coupon_count;
    }

    /**
     * coupon_count
     *
     * @param   coupon_count    the coupon_count to set
     * @since   CodingExample Ver 1.0
     */

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    /**
     * coupon_id_$n
     *
     * @return  the coupon_id_$n
     * @since   CodingExample Ver 1.0
    */

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    /**
     * coupon_id_$n
     *
     * @param   coupon_id_$n    the coupon_id_$n to set
     * @since   CodingExample Ver 1.0
     */

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    /**
     * coupon_fee_$n
     *
     * @return  the coupon_fee_$n
     * @since   CodingExample Ver 1.0
    */

    public String getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    /**
     * coupon_fee_$n
     *
     * @param   coupon_fee_$n    the coupon_fee_$n to set
     * @since   CodingExample Ver 1.0
     */

    public void setCoupon_fee_$n(String coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
    }

    /**
     * is_subscribe
     *
     * @return  the is_subscribe
     * @since   CodingExample Ver 1.0
    */

    public String getIs_subscribe() {
        return is_subscribe;
    }

    /**
     * is_subscribe
     *
     * @param   is_subscribe    the is_subscribe to set
     * @since   CodingExample Ver 1.0
     */

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

}
