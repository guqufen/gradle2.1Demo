package net.fnsco.web.controller.app.jo;

import net.fnsco.core.base.JO;

public class CommJO extends JO {
    //版本号
    private String  version;
    //安卓
    private Integer type;

    /**
     * type
     *
     * @return  the type
     * @since   CodingExample Ver 1.0
    */

    public Integer getType() {
        return type;
    }

    /**
     * type
     *
     * @param   type    the type to set
     * @since   CodingExample Ver 1.0
     */

    public void setType(Integer type) {
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
