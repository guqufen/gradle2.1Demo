package net.fnsco.web.controller.pay.dto;

//"return_code;//SUCCESS",
//"return_msg;//OK",
//"appid;//wx0641180aafae217c",
//"mch_id;//1404880402",
//"sub_mch_id;//34611288",
//"nonce_str;//rd7gmVptY13oiX49",
//"sign;//5394C149B13B56BB23DC259C4F01A651",
//"result_code;//SUCCESS",
//"trade_type;//JSAPI",
//"trade_state;//SUCCESS",
//"openid;//oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
//"is_subscribe;//N",
//"bank_type;//CFT",
//"total_fee": 1,
//"fee_type;//CNY",
//"transaction_id;//4000902001201707140737596443",
//"out_trade_no;//20170714100026800198",
//"attach;//",
//"time_end;//20170714155930",
//"cash_fee": 1
public class PfWeixinDTO {
    private String return_code;   //SUCCESS",
    private String return_msg;    //OK",
    private String appid;         //wx0641180aafae217c",
    private String mch_id;        //1404880402",
    private String sub_mch_id;    //34611288",
    private String nonce_str;     //rd7gmVptY13oiX49",
    private String sign;          //5394C149B13B56BB23DC259C4F01A651",
    private String result_code;   //SUCCESS",
    private String trade_type;    //JSAPI",
    private String trade_state;   //SUCCESS",
    private String openid;        //oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
    private String is_subscribe;  //N",
    private String bank_type;     //CFT",
    private String total_fee;     //": 1,
    private String fee_type;      //CNY",
    private String transaction_id;//4000902001201707140737596443",
    private String out_trade_no;  //20170714100026800198",
    private String attach;        //",
    private String time_end;      //20170714155930",
    private String cash_fee;      //": 1

    /**
     * return_code
     *
     * @return  the return_code
     * @since   CodingExample Ver 1.0
    */

    public String getReturn_code() {
        return return_code;
    }

    /**
     * return_code
     *
     * @param   return_code    the return_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    /**
     * return_msg
     *
     * @return  the return_msg
     * @since   CodingExample Ver 1.0
    */

    public String getReturn_msg() {
        return return_msg;
    }

    /**
     * return_msg
     *
     * @param   return_msg    the return_msg to set
     * @since   CodingExample Ver 1.0
     */

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    /**
     * appid
     *
     * @return  the appid
     * @since   CodingExample Ver 1.0
    */

    public String getAppid() {
        return appid;
    }

    /**
     * appid
     *
     * @param   appid    the appid to set
     * @since   CodingExample Ver 1.0
     */

    public void setAppid(String appid) {
        this.appid = appid;
    }

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
     * sub_mch_id
     *
     * @return  the sub_mch_id
     * @since   CodingExample Ver 1.0
    */

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    /**
     * sub_mch_id
     *
     * @param   sub_mch_id    the sub_mch_id to set
     * @since   CodingExample Ver 1.0
     */

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
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
     * trade_state
     *
     * @return  the trade_state
     * @since   CodingExample Ver 1.0
    */

    public String getTrade_state() {
        return trade_state;
    }

    /**
     * trade_state
     *
     * @param   trade_state    the trade_state to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
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

}
