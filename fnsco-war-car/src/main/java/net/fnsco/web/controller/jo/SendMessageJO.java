package net.fnsco.web.controller.jo;

import io.swagger.annotations.ApiModelProperty;

public class SendMessageJO {

		
		@ApiModelProperty(value = "手机号",  example = "手机号")
		private String mobile;
		@ApiModelProperty(value = "类型", example = "类型")
		private String type;
		/**
		 * mobile
		 *
		 * @return  the mobile
		 * @since   CodingExample Ver 1.0
		*/
		
		public String getMobile() {
			return mobile;
		}
		/**
		 * mobile
		 *
		 * @param   mobile    the mobile to set
		 * @since   CodingExample Ver 1.0
		 */
		
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		/**
		 * type
		 *
		 * @return  the type
		 * @since   CodingExample Ver 1.0
		*/
		
		public String getType() {
			return type;
		}
		/**
		 * type
		 *
		 * @param   type    the type to set
		 * @since   CodingExample Ver 1.0
		 */
		
		public void setType(String type) {
			this.type = type;
		}
	   
		

}
