package net.fnsco.bigdata.service.domain;
/**
 * @desc 商家信息扩张信息类 
 * @author tangliang
 * @date 2017年6月21日 下午3:07:17
 */
public class MerchantExt {
	
	private Integer id;

    private String innerCode;

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
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }
}