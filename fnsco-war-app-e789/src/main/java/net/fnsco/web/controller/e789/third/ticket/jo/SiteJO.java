package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class SiteJO extends JO {
    @ApiModelProperty(value = "站点名称", example = "站点名称")

    private String siteName;

    /**
     * siteName
     *
     * @return  the siteName
     * @since   CodingExample Ver 1.0
    */

    public String getSiteName() {
        return siteName;
    }

    /**
     * siteName
     *
     * @param   siteName    the siteName to set
     * @since   CodingExample Ver 1.0
     */

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}
