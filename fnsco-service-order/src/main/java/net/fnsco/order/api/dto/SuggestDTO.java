package net.fnsco.order.api.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class SuggestDTO extends DTO{
	
	@ApiModelProperty(value="id",example="不用填写")
    private Integer id;
	@ApiModelProperty(value="类型",example="类型")
    private String type;
	@ApiModelProperty(value="内容",example="内容")
    private String content;
	@ApiModelProperty(value="用户ID",example="用户ID")
    private Integer userId;
	@ApiModelProperty(value="用户手机号",example="用户手机号")
    private String mobile;
	@ApiModelProperty(value="提交时间",example="不用填写")
    private Date submitTime;
	@ApiModelProperty(value="处理人id",example="不用填写")
    private String replyUserId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Date replyTime;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
