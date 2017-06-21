package net.fnsco.service.domain;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;

/**
 * @desc 商家联系人信息实体
 * @author tangliang
 * @date 2017年6月21日 下午2:33:25
 */
public class MerchantContact extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8307574239429757415L;

	private Integer id;

    private String innercode;

    private String name;

    private String mobile;

    private String email;

    private String telphone;

    private String jobs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnercode() {
        return innercode;
    }

    public void setInnercode(String innercode) {
        this.innercode = innercode == null ? null : innercode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs == null ? null : jobs.trim();
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return JSONObject.toJSONString(this);
    }
    
    /**
     * @todo toString和hashCode重写
     * @author tangliang
     * @date 2017年6月21日 下午2:44:21
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	int result = 17;  
        result = result * 31 + name.hashCode();  
        result = result * 31 + id;  
        return result;  
    }
}