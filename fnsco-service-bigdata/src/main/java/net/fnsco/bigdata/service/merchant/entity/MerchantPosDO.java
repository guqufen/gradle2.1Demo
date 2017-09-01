package net.fnsco.bigdata.service.merchant.entity;


public class MerchantPosDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 内部商户号 15位
     */
    private String innerCode;

    /**
     * 渠道ID
     */
    private Integer channelId;

    /**
     * pos机名称
     */
    private String posName;

    /**
     * POS机SN码
     */
    private String snCode;

    /**
     * 机具厂家
     */
    private String posFactory;

    /**
     * 机具型号
     */
    private String posType;

    /**
     * 状态0注销1正常
     */
    private String status;

    /**
     * 银行卡ID
     */
    private Integer bankId;

    /**
     * 签购单参考名
     */
    private String mercReferName;

    /**
     * POS装机地址
     */
    private String posAddr;

    /**
     * POS装机地址省份
     */
    private Integer posProvince;

    /**
     * POS装机地址市
     */
    private Integer posCity;

    /**
     * POS装机地址区
     */
    private Integer posArea;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getPosFactory() {
        return posFactory;
    }

    public void setPosFactory(String posFactory) {
        this.posFactory = posFactory;
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getMercReferName() {
        return mercReferName;
    }

    public void setMercReferName(String mercReferName) {
        this.mercReferName = mercReferName;
    }

    public String getPosAddr() {
        return posAddr;
    }

    public void setPosAddr(String posAddr) {
        this.posAddr = posAddr;
    }

    public Integer getPosProvince() {
        return posProvince;
    }

    public void setPosProvince(Integer posProvince) {
        this.posProvince = posProvince;
    }

    public Integer getPosCity() {
        return posCity;
    }

    public void setPosCity(Integer posCity) {
        this.posCity = posCity;
    }

    public Integer getPosArea() {
        return posArea;
    }

    public void setPosArea(Integer posArea) {
        this.posArea = posArea;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", innerCode="+ innerCode + ", channelId="+ channelId + ", posName="+ posName + ", snCode="+ snCode + ", posFactory="+ posFactory + ", posType="+ posType + ", status="+ status + ", bankId="+ bankId + ", mercReferName="+ mercReferName + ", posAddr="+ posAddr + ", posProvince="+ posProvince + ", posCity="+ posCity + ", posArea="+ posArea + "]";
    }
}