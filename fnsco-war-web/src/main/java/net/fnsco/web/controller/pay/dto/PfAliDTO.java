package net.fnsco.web.controller.pay.dto;

//"ali": {
//    "trade_no;//2017071421001004070273111491",
//    "out_trade_no;//20170714100026802780",
//    "buyer_user_id;//2088102155294078",
//    "buyer_logon_id;//son***@gmail.com",
//    "trade_status;//TRADE_SUCCESS",
//    "total_fee;//0.01",
//    "fund_bill_list": {
//        "tradeFundBill": [
//            {
//                "fund_channel;//PCREDIT",
//                "amount": 0.01
//            }
//        ]
//    }
//},
public class PfAliDTO {
    private String trade_no;      //2017071421001004070273111491",
    private String out_trade_no;  //20170714100026802780",
    private String buyer_user_id; //2088102155294078",
    private String buyer_logon_id;//son***@gmail.com",
    private String trade_status;  //TRADE_SUCCESS",
    private String total_fee;     //0.01",

    /**
     * trade_no
     *
     * @return  the trade_no
     * @since   CodingExample Ver 1.0
    */

    public String getTrade_no() {
        return trade_no;
    }

    /**
     * trade_no
     *
     * @param   trade_no    the trade_no to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
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
     * buyer_user_id
     *
     * @return  the buyer_user_id
     * @since   CodingExample Ver 1.0
    */

    public String getBuyer_user_id() {
        return buyer_user_id;
    }

    /**
     * buyer_user_id
     *
     * @param   buyer_user_id    the buyer_user_id to set
     * @since   CodingExample Ver 1.0
     */

    public void setBuyer_user_id(String buyer_user_id) {
        this.buyer_user_id = buyer_user_id;
    }

    /**
     * buyer_logon_id
     *
     * @return  the buyer_logon_id
     * @since   CodingExample Ver 1.0
    */

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    /**
     * buyer_logon_id
     *
     * @param   buyer_logon_id    the buyer_logon_id to set
     * @since   CodingExample Ver 1.0
     */

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    /**
     * trade_status
     *
     * @return  the trade_status
     * @since   CodingExample Ver 1.0
    */

    public String getTrade_status() {
        return trade_status;
    }

    /**
     * trade_status
     *
     * @param   trade_status    the trade_status to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
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

}
