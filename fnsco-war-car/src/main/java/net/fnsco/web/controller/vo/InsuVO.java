package net.fnsco.web.controller.vo;

import io.swagger.annotations.ApiModelProperty;

public class InsuVO {
		@ApiModelProperty(value = "value", name = "value", example = "保险公司id")
	    private Integer value;

		@ApiModelProperty(value = "text", name = "text", example = "保险公司名称")
	    private String text;

		/**
		 * @return the value
		 */
		public Integer getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(Integer value) {
			this.value = value;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		
}
