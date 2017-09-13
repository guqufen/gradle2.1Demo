package net.fnsco.web.controller.pay.dto;

//"BEGIN_TIME": "2017-07-14 15:59:24",
//"END_TIME": "2017-07-14 15:59:30",
//"weixin": {
//    "return_code": "SUCCESS",
//    "return_msg": "OK",
//    "appid": "wx0641180aafae217c",
//    "mch_id": "1404880402",
//    "sub_mch_id": "34611288",
//    "nonce_str": "rd7gmVptY13oiX49",
//    "sign": "5394C149B13B56BB23DC259C4F01A651",
//    "result_code": "SUCCESS",
//    "trade_type": "JSAPI",
//    "trade_state": "SUCCESS",
//    "openid": "oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
//    "is_subscribe": "N",
//    "bank_type": "CFT",
//    "total_fee": 1,
//    "fee_type": "CNY",
//    "transaction_id": "4000902001201707140737596443",
//    "out_trade_no": "20170714100026800198",
//    "attach": "",
//    "time_end": "20170714155930",
//    "cash_fee": 1
//},
//"buyerId": "oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
//"charge_THIRD_CODE": "4000902001201707140737596443",
//"BUSI_ID": "952010565811",
//"CHARGE_CODE": "9c7fa961-dc93-4dc6-b511-d3116e6b01cf",
//"CHARGE_DOWN_CODE": "20170714100026800198",
//"CHANNEL_TYPE": 2,
//"STATE": 1,
//"OPER_ID": "oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
//"PAY_SUBJECT": "在线买单",
//"FUND_BILL_LIST": {
//    "TRADEFUNDBILL": [
//        {
//            "FUND_CHANNEL": "11",
//            "AMOUNT": 0.01
//        }
//    ]
//},
//"AMT": 1,
//"DEV_ID": "online"
public class PfNotifyDTO {
    private String      BUSI_ID;          //商户编号
    private String      BEGIN_TIME;       // 交易开始时间
    private String      END_TIME;         // 交易结束时间
    private String      buyerId;          // 用户标识
    private String      charge_THIRD_CODE;// 渠道交易流水号
    private String      OPER_ID;          // 操作员编号
    private String      DEV_ID;           // 设备编号
    private String      AMT;              // 交易金额分
    private Integer      CHANNEL_TYPE;     //   支付渠道
//    0   被扫接口传0自动判定渠道
//    1   支付宝
//    2   微信
//    3   招商银行
//    5   中国建设银行
//    9   农行银行
//    10  浦发银行
//    24  百度钱包
//    27  电信翼支付
//    31  QQ钱包
//    32  银联卡支付
//    34  支付宝手机网页支付

    private String      CHARGE_CODE;      //交易流水号
    private String      CHARGE_DOWN_CODE; //   交易下行流水号
    private Integer      STATE;            //订单状态
    //    0   待付款
    //    1   已付款
    //    2   已撤单
    //    3   申请撤单
    //    4   部分退款
    //    5   已退款
    //    6   交易关闭
    //    7   系统保留状态（查询时使用，同时查询退款和撤单记录）
    //    8   退款中（平台未返回退款结果）

    private String      PAY_SUBJECT;      // 支付信息描述

    private String      GOODS_DETAIL;     //   商品明细

    private PfWeixinDTO weixin;           //微信信息
    //private String      FUND_BILL_LIST;   //  付款渠道信息
    private PfAliDTO ali;//支付宝信息
    //以下未使用
    private String      MERCHANTPARA;     // 用户参数

    /**
     * ali
     *
     * @return  the ali
     * @since   CodingExample Ver 1.0
    */
    
    public PfAliDTO getAli() {
        return ali;
    }

    /**
     * ali
     *
     * @param   ali    the ali to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAli(PfAliDTO ali) {
        this.ali = ali;
    }

    /**
     * weixin
     *
     * @return  the weixin
     * @since   CodingExample Ver 1.0
    */

    public PfWeixinDTO getWeixin() {
        return weixin;
    }

    /**
     * weixin
     *
     * @param   weixin    the weixin to set
     * @since   CodingExample Ver 1.0
     */

    public void setWeixin(PfWeixinDTO weixin) {
        this.weixin = weixin;
    }

    /**
     * bUSI_ID
     *
     * @return  the bUSI_ID
     * @since   CodingExample Ver 1.0
    */

    public String getBUSI_ID() {
        return BUSI_ID;
    }

    /**
     * bUSI_ID
     *
     * @param   bUSI_ID    the bUSI_ID to set
     * @since   CodingExample Ver 1.0
     */

    public void setBUSI_ID(String bUSI_ID) {
        BUSI_ID = bUSI_ID;
    }

    /**
     * bEGIN_TIME
     *
     * @return  the bEGIN_TIME
     * @since   CodingExample Ver 1.0
    */

    public String getBEGIN_TIME() {
        return BEGIN_TIME;
    }

    /**
     * bEGIN_TIME
     *
     * @param   bEGIN_TIME    the bEGIN_TIME to set
     * @since   CodingExample Ver 1.0
     */

    public void setBEGIN_TIME(String bEGIN_TIME) {
        BEGIN_TIME = bEGIN_TIME;
    }

    /**
     * eND_TIME
     *
     * @return  the eND_TIME
     * @since   CodingExample Ver 1.0
    */

    public String getEND_TIME() {
        return END_TIME;
    }

    /**
     * eND_TIME
     *
     * @param   eND_TIME    the eND_TIME to set
     * @since   CodingExample Ver 1.0
     */

    public void setEND_TIME(String eND_TIME) {
        END_TIME = eND_TIME;
    }

    /**
     * buyerId
     *
     * @return  the buyerId
     * @since   CodingExample Ver 1.0
    */

    public String getBuyerId() {
        return buyerId;
    }

    /**
     * buyerId
     *
     * @param   buyerId    the buyerId to set
     * @since   CodingExample Ver 1.0
     */

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * charge_THIRD_CODE
     *
     * @return  the charge_THIRD_CODE
     * @since   CodingExample Ver 1.0
    */

    public String getCharge_THIRD_CODE() {
        return charge_THIRD_CODE;
    }

    /**
     * charge_THIRD_CODE
     *
     * @param   charge_THIRD_CODE    the charge_THIRD_CODE to set
     * @since   CodingExample Ver 1.0
     */

    public void setCharge_THIRD_CODE(String charge_THIRD_CODE) {
        this.charge_THIRD_CODE = charge_THIRD_CODE;
    }

    /**
     * oPER_ID
     *
     * @return  the oPER_ID
     * @since   CodingExample Ver 1.0
    */

    public String getOPER_ID() {
        return OPER_ID;
    }

    /**
     * oPER_ID
     *
     * @param   oPER_ID    the oPER_ID to set
     * @since   CodingExample Ver 1.0
     */

    public void setOPER_ID(String oPER_ID) {
        OPER_ID = oPER_ID;
    }

    /**
     * dEV_ID
     *
     * @return  the dEV_ID
     * @since   CodingExample Ver 1.0
    */

    public String getDEV_ID() {
        return DEV_ID;
    }

    /**
     * dEV_ID
     *
     * @param   dEV_ID    the dEV_ID to set
     * @since   CodingExample Ver 1.0
     */

    public void setDEV_ID(String dEV_ID) {
        DEV_ID = dEV_ID;
    }

    /**
     * aMT
     *
     * @return  the aMT
     * @since   CodingExample Ver 1.0
    */

    public String getAMT() {
        return AMT;
    }

    /**
     * aMT
     *
     * @param   aMT    the aMT to set
     * @since   CodingExample Ver 1.0
     */

    public void setAMT(String aMT) {
        AMT = aMT;
    }

    

    /**
     * cHANNEL_TYPE
     *
     * @return  the cHANNEL_TYPE
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getCHANNEL_TYPE() {
        return CHANNEL_TYPE;
    }

    /**
     * cHANNEL_TYPE
     *
     * @param   cHANNEL_TYPE    the cHANNEL_TYPE to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setCHANNEL_TYPE(Integer cHANNEL_TYPE) {
        CHANNEL_TYPE = cHANNEL_TYPE;
    }

    /**
     * sTATE
     *
     * @param   sTATE    the sTATE to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setSTATE(Integer sTATE) {
        STATE = sTATE;
    }

    /**
     * cHARGE_CODE
     *
     * @return  the cHARGE_CODE
     * @since   CodingExample Ver 1.0
    */

    public String getCHARGE_CODE() {
        return CHARGE_CODE;
    }

    /**
     * cHARGE_CODE
     *
     * @param   cHARGE_CODE    the cHARGE_CODE to set
     * @since   CodingExample Ver 1.0
     */

    public void setCHARGE_CODE(String cHARGE_CODE) {
        CHARGE_CODE = cHARGE_CODE;
    }

    /**
     * cHARGE_DOWN_CODE
     *
     * @return  the cHARGE_DOWN_CODE
     * @since   CodingExample Ver 1.0
    */

    public String getCHARGE_DOWN_CODE() {
        return CHARGE_DOWN_CODE;
    }

    /**
     * cHARGE_DOWN_CODE
     *
     * @param   cHARGE_DOWN_CODE    the cHARGE_DOWN_CODE to set
     * @since   CodingExample Ver 1.0
     */

    public void setCHARGE_DOWN_CODE(String cHARGE_DOWN_CODE) {
        CHARGE_DOWN_CODE = cHARGE_DOWN_CODE;
    }

    /**
     * pAY_SUBJECT
     *
     * @return  the pAY_SUBJECT
     * @since   CodingExample Ver 1.0
    */

    public String getPAY_SUBJECT() {
        return PAY_SUBJECT;
    }

    /**
     * pAY_SUBJECT
     *
     * @param   pAY_SUBJECT    the pAY_SUBJECT to set
     * @since   CodingExample Ver 1.0
     */

    public void setPAY_SUBJECT(String pAY_SUBJECT) {
        PAY_SUBJECT = pAY_SUBJECT;
    }

    /**
     * gOODS_DETAIL
     *
     * @return  the gOODS_DETAIL
     * @since   CodingExample Ver 1.0
    */

    public String getGOODS_DETAIL() {
        return GOODS_DETAIL;
    }

    /**
     * gOODS_DETAIL
     *
     * @param   gOODS_DETAIL    the gOODS_DETAIL to set
     * @since   CodingExample Ver 1.0
     */

    public void setGOODS_DETAIL(String gOODS_DETAIL) {
        GOODS_DETAIL = gOODS_DETAIL;
    }

     

    /**
     * sTATE
     *
     * @return  the sTATE
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getSTATE() {
        return STATE;
    }

    /**
     * mERCHANTPARA
     *
     * @return  the mERCHANTPARA
     * @since   CodingExample Ver 1.0
    */

    public String getMERCHANTPARA() {
        return MERCHANTPARA;
    }

    /**
     * mERCHANTPARA
     *
     * @param   mERCHANTPARA    the mERCHANTPARA to set
     * @since   CodingExample Ver 1.0
     */

    public void setMERCHANTPARA(String mERCHANTPARA) {
        MERCHANTPARA = mERCHANTPARA;
    }

}
/*{
    "PAY_NODIFY": {
        "BEGIN_TIME": "2017-07-14 16:33:15",
        "END_TIME": "2017-07-14 16:33:19",
        "ali": {
            "trade_no": "2017071421001004070273111491",
            "out_trade_no": "20170714100026802780",
            "buyer_user_id": "2088102155294078",
            "buyer_logon_id": "son***@gmail.com",
            "trade_status": "TRADE_SUCCESS",
            "total_fee": "0.01",
            "fund_bill_list": {
                "tradeFundBill": [
                    {
                        "fund_channel": "PCREDIT",
                        "amount": 0.01
                    }
                ]
            }
        },
        "buyerId": "2088102155294078",
        "charge_THIRD_CODE": "2017071421001004070273111491",
        "BUSI_ID": "952010565811",
        "CHARGE_CODE": "9f8bba13b03247de9b75952bfd5a45f8",
        "CHARGE_DOWN_CODE": "20170714100026802780",
        "CHANNEL_TYPE": 1,
        "STATE": 1,
        "OPER_ID": "2088102155294078",
        "PAY_SUBJECT": "在线买单",
        "FUND_BILL_LIST": {
            "TRADEFUNDBILL": [
                {
                    "FUND_CHANNEL": "90",
                    "AMOUNT": 0.01
                }
            ]
        },
        "AMT": 1,
        "DEV_ID": "online"
    }
}*/