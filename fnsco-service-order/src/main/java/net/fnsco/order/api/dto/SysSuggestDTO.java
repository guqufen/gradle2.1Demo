package net.fnsco.order.api.dto;

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


		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		private String userName;

	    public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		private String mobile;

	    private String submitTime;
	    private String startTime;
	    private String endTime;

		public String getSubmitTime() {
			return submitTime;
		}

		public void setSubmitTime(String submitTime) {
			this.submitTime = submitTime;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
}

