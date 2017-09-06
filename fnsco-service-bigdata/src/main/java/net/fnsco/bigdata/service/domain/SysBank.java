package net.fnsco.bigdata.service.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * @desc WEB银行卡信息实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月7日 上午10:48:18
 */
public class SysBank {
    private Integer id;

    private String code;

    private String bankName;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private String branchBankName;

    private String anBankId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = branchBankName == null ? null : branchBankName.trim();
    }

    public String getAnBankId() {
        return anBankId;
    }

    public void setAnBankId(String anBankId) {
        this.anBankId = anBankId == null ? null : anBankId.trim();
    }
    @Override
    public String toString() {
        
        return JSONObject.toJSONString(this);
        
    }
    @Override
    public int hashCode() {
        
        return super.hashCode();
        
    }
}