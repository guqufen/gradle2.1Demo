package net.fnsco.trading.service.third.ticket.entity;

import java.util.Date;

public class TicketSiteDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点拼音名
     */
    private String pyName;

    /**
     * 站点代码
     */
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date lastModifyTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", name="+ name + ", pyName="+ pyName + ", code="+ code + ", createTime="+ createTime + ", lastModifyTime="+ lastModifyTime + "]";
    }
}