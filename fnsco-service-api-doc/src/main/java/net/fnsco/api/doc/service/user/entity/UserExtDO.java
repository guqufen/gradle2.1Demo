package net.fnsco.api.doc.service.user.entity;

import java.util.Date;

public class UserExtDO {

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
     * 用户id
     */
    private Long userId;

    /**
     * 最后获取系统消息时间
     */
    private Date lastFetchSysMsgDate;



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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastFetchSysMsgDate() {
        return lastFetchSysMsgDate;
    }

    public void setLastFetchSysMsgDate(Date lastFetchSysMsgDate) {
        this.lastFetchSysMsgDate = lastFetchSysMsgDate;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", userId="+ userId + ", lastFetchSysMsgDate="+ lastFetchSysMsgDate + "]";
    }
}