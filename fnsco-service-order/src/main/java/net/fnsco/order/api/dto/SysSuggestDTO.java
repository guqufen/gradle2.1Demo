package net.fnsco.order.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

public class SysSuggestDTO extends DTO{
		private int id;
		
	 	public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		private String content;

	    public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
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

		private Integer userId;

	    private String mobile;

	    private Date submitTime;
}
