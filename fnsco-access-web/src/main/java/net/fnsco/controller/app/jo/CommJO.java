package net.fnsco.controller.app.jo;

import net.fnsco.core.base.JO;

public class CommJO extends JO {
    //版本号
    private String version;
    //安卓
    private String type;
    
    /**
     * type
     *
     * @return  the type
     * @since   CodingExample Ver 1.0
    */
    
    public String getType() {
        return type;
    }

    /**
     * type
     *
     * @param   type    the type to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setType(String type) {
        this.type = type;
    }

    /**
     * version
     *
     * @return  the version
     * @since   CodingExample Ver 1.0
    */

    public String getVersion() {
        return version;
    }

    /**
     * version
     *
     * @param   version    the version to set
     * @since   CodingExample Ver 1.0
     */

    public void setVersion(String version) {
        this.version = version;
    }

}
