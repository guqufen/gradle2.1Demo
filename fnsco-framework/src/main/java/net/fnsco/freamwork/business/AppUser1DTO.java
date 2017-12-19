package net.fnsco.freamwork.business;

public class AppUser1DTO {
    private Integer id;

    private String  userName;

    //是否强制退出标志1强0不强
    private Integer forcedLoginOut;
    //是否同一台机器登录
    private String  deviceId;
    

    /**
     * deviceId
     *
     * @return  the deviceId
     * @since   CodingExample Ver 1.0
    */
    
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * deviceId
     *
     * @param   deviceId    the deviceId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */

    public Integer getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */

    public void setId(Integer id) {
        this.id = id;
    }

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

    /**
     * forcedLoginOut
     *
     * @return  the forcedLoginOut
     * @since   CodingExample Ver 1.0
    */

    public Integer getForcedLoginOut() {
        return forcedLoginOut;
    }

    /**
     * forcedLoginOut
     *
     * @param   forcedLoginOut    the forcedLoginOut to set
     * @since   CodingExample Ver 1.0
     */

    public void setForcedLoginOut(Integer forcedLoginOut) {
        this.forcedLoginOut = forcedLoginOut;
    }

}