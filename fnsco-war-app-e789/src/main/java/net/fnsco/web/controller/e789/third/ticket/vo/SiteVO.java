package net.fnsco.web.controller.e789.third.ticket.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class SiteVO extends VO {
    @ApiModelProperty(value = "站点名称", example = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "站点拼音名称", example = "站点拼音名称")
    private String sitePyName;
    @ApiModelProperty(value = "站点代码", example = "站点代码")
    private String siteCode;

    /**
     * sitePyName
     *
     * @param   sitePyName    the sitePyName to set
     * @since   CodingExample Ver 1.0
     */

    public void setSitePyName(String sitePyName) {
        this.sitePyName = sitePyName;
    }

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

    /**
     * sitePyName
     *
     * @return  the sitePyName
     * @since   CodingExample Ver 1.0
    */

    public String getSitePyName() {
        return sitePyName;
    }

    /**
     * sitePyName
     *
     * @param   sitePyName    the sitePyName to set
     * @since   CodingExample Ver 1.0
     */

    public void resultList(String sitePyName) {
        this.sitePyName = sitePyName;
    }

    /**
     * siteCode
     *
     * @return  the siteCode
     * @since   CodingExample Ver 1.0
    */

    public String getSiteCode() {
        return siteCode;
    }

    /**
     * siteCode
     *
     * @param   siteCode    the siteCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

}
