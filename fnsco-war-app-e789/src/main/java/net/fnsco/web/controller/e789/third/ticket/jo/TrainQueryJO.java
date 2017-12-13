package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;

public class TrainQueryJO {
    @ApiModelProperty(value = "起点站", example = "起点站")
    private String startSite;

    @ApiModelProperty(value = "终点站", example = "终点站")
    private String endSite;

    @ApiModelProperty(value = "乘车日期", example = "乘车日期")
    private String buyDate;

    /**
     * startSite
     *
     * @return  the startSite
     * @since   CodingExample Ver 1.0
    */

    public String getStartSite() {
        return startSite;
    }

    /**
     * startSite
     *
     * @param   startSite    the startSite to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartSite(String startSite) {
        this.startSite = startSite;
    }

    /**
     * endSite
     *
     * @return  the endSite
     * @since   CodingExample Ver 1.0
    */

    public String getEndSite() {
        return endSite;
    }

    /**
     * endSite
     *
     * @param   endSite    the endSite to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndSite(String endSite) {
        this.endSite = endSite;
    }

    /**
     * buyDate
     *
     * @return  the buyDate
     * @since   CodingExample Ver 1.0
    */

    public String getBuyDate() {
        return buyDate;
    }

    /**
     * buyDate
     *
     * @param   buyDate    the buyDate to set
     * @since   CodingExample Ver 1.0
     */

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

}
