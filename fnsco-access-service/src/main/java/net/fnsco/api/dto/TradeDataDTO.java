package net.fnsco.api.dto;

public class TradeDataDTO {

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

}
