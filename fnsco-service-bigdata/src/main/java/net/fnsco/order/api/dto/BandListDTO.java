package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

//输出店员的手机号和userId
public class BandListDTO extends DTO{
    private String  mobile;
    private Integer userId;
    private String  userName;
    
    /**
     * userName
     *
     * @return  the userName
     * @since   CodingExample Ver 1.0
    */
    
    public String getUserName() {
        return userName;
    }
    /**
     * userName
     *
     * @param   userName    the userName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
