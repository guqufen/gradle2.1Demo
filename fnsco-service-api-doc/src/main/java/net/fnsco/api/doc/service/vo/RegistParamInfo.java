package net.fnsco.api.doc.service.vo;

/**
 * 
		* <p>Title: 注册相关参数</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日下午12:50:54</p>
 */
public class RegistParamInfo {
    /** 邮箱*/
    private String email;

    /** 手机号*/
    private String phone;

    /** 昵称*/
    private String nickName;

    /** 密码*/
    private String password;

    /** 登陆ip*/
    private String registIp;

    /** 登陆类型*/
    private String loginType;

    /** 手机验证码*/
    private String smsCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    /**
     * loginType
     *
     * @return  the loginType
     * @since   CodingExample Ver 1.0
    */

    public String getLoginType() {
        return loginType;
    }

    /**
     * loginType
     *
     * @param   loginType    the loginType to set
     * @since   CodingExample Ver 1.0
     */

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
