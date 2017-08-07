package net.fnsco.api.doc.service.user.entity;

import java.util.Date;

public class UserCubeDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 总注册用户数
     */
    private Integer totalRegistCount;

    /**
     * 日注册用户数
     */
    private Integer dayRegistCount;

    /**
     * 日登录用户数
     */
    private Integer dayLoginCount;

    /**
     * 日登陆老用户数
     */
    private Integer dayOldLoginCount;

    /**
     * 总项目数
     */
    private Integer totalProjCount;

    /**
     * 日新增项目数
     */
    private Integer dayProjCount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getTotalRegistCount() {
        return totalRegistCount;
    }

    public void setTotalRegistCount(Integer totalRegistCount) {
        this.totalRegistCount = totalRegistCount;
    }

    public Integer getDayRegistCount() {
        return dayRegistCount;
    }

    public void setDayRegistCount(Integer dayRegistCount) {
        this.dayRegistCount = dayRegistCount;
    }

    public Integer getDayLoginCount() {
        return dayLoginCount;
    }

    public void setDayLoginCount(Integer dayLoginCount) {
        this.dayLoginCount = dayLoginCount;
    }

    public Integer getDayOldLoginCount() {
        return dayOldLoginCount;
    }

    public void setDayOldLoginCount(Integer dayOldLoginCount) {
        this.dayOldLoginCount = dayOldLoginCount;
    }

    public Integer getTotalProjCount() {
        return totalProjCount;
    }

    public void setTotalProjCount(Integer totalProjCount) {
        this.totalProjCount = totalProjCount;
    }

    public Integer getDayProjCount() {
        return dayProjCount;
    }

    public void setDayProjCount(Integer dayProjCount) {
        this.dayProjCount = dayProjCount;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", totalRegistCount="+ totalRegistCount + ", dayRegistCount="+ dayRegistCount + ", dayLoginCount="+ dayLoginCount + ", dayOldLoginCount="+ dayOldLoginCount + ", totalProjCount="+ totalProjCount + ", dayProjCount="+ dayProjCount + "]";
    }
}