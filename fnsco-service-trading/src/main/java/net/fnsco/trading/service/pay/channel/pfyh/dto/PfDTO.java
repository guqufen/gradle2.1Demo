package net.fnsco.trading.service.pay.channel.pfyh.dto;

public class PfDTO {
    private PfNotifyDTO PAY_NODIFY;//

    /**
     * pAY_NODIFY
     *
     * @return  the pAY_NODIFY
     * @since   CodingExample Ver 1.0
    */

    public PfNotifyDTO getPAY_NODIFY() {
        return PAY_NODIFY;
    }

    /**
     * pAY_NODIFY
     *
     * @param   pAY_NODIFY    the pAY_NODIFY to set
     * @since   CodingExample Ver 1.0
     */

    public void setPAY_NODIFY(PfNotifyDTO pAY_NODIFY) {
        PAY_NODIFY = pAY_NODIFY;
    }

}
/*{
    "PAY_NODIFY": {
        "BEGIN_TIME": "2017-07-14 15:59:24",
        "END_TIME": "2017-07-14 15:59:30",
        "weixin": {
            "return_code": "SUCCESS",
            "return_msg": "OK",
            "appid": "wx0641180aafae217c",
            "mch_id": "1404880402",
            "sub_mch_id": "34611288",
            "nonce_str": "rd7gmVptY13oiX49",
            "sign": "5394C149B13B56BB23DC259C4F01A651",
            "result_code": "SUCCESS",
            "trade_type": "JSAPI",
            "trade_state": "SUCCESS",
            "openid": "oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
            "is_subscribe": "N",
            "bank_type": "CFT",
            "total_fee": 1,
            "fee_type": "CNY",
            "transaction_id": "4000902001201707140737596443",
            "out_trade_no": "20170714100026800198",
            "attach": "",
            "time_end": "20170714155930",
            "cash_fee": 1
        },
        "buyerId": "oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
        "charge_THIRD_CODE": "4000902001201707140737596443",
        "BUSI_ID": "952010565811",
        "CHARGE_CODE": "9c7fa961-dc93-4dc6-b511-d3116e6b01cf",
        "CHARGE_DOWN_CODE": "20170714100026800198",
        "CHANNEL_TYPE": 2,
        "STATE": 1,
        "OPER_ID": "oBsaFwxyN7WjaiJc8jMANl9Vu_Ew",
        "PAY_SUBJECT": "在线买单",
        "FUND_BILL_LIST": {
            "TRADEFUNDBILL": [
                {
                    "FUND_CHANNEL": "11",
                    "AMOUNT": 0.01
                }
            ]
        },
        "AMT": 1,
        "DEV_ID": "online"
    }
}*/