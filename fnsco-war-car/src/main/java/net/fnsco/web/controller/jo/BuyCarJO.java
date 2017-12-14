package net.fnsco.web.controller.jo;

import io.swagger.annotations.ApiModelProperty;

public class BuyCarJO {

		@ApiModelProperty(value = "购车人", example = "购车人")
		private String name;
		@ApiModelProperty(value = "手机号",  example = "手机号")
		private String mobile;
	    /**
	     * 所在城市
	     */
		@ApiModelProperty(value = "所在城市", example = "所在城市")
	    private Integer cityId;

	    /**
	     * 汽车品牌
	     */
		@ApiModelProperty(value = "汽车品牌", example = "汽车品牌")
	    private Integer carTypeId;

	    /**
	     * 汽车型号
	     */
		@ApiModelProperty(value = "汽车型号",  example = "汽车型号")
	    private Integer carSubTypeId;

	    /**
	     * 金融方案
	     */
		@ApiModelProperty(value = "金融方案", example = "金融方案")
	    private String buyType;

	    /**
	     * 推荐码
	     */
		@ApiModelProperty(value = "推荐码",  example = "推荐码")
	    private String suggestCode;

		/**
	     * 验证码
	     */
		@ApiModelProperty(value = "验证码",  example = "验证码")
		private String verCode;
		
		
		/**
		 * @return the verCode
		 */
		public String getVerCode() {
			return verCode;
		}

		/**
		 * @param verCode the verCode to set
		 */
		public void setVerCode(String verCode) {
			this.verCode = verCode;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the mobile
		 */
		public String getMobile() {
			return mobile;
		}

		/**
		 * @param mobile the mobile to set
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public Integer getCityId() {
	        return cityId;
	    }

	    public void setCityId(Integer cityId) {
	        this.cityId = cityId;
	    }

	    public Integer getCarTypeId() {
	        return carTypeId;
	    }

	    public void setCarTypeId(Integer carTypeId) {
	        this.carTypeId = carTypeId;
	    }

	    public Integer getCarSubTypeId() {
	        return carSubTypeId;
	    }

	    public void setCarSubTypeId(Integer carSubTypeId) {
	        this.carSubTypeId = carSubTypeId;
	    }

	    public String getBuyType() {
	        return buyType;
	    }

	    public void setBuyType(String buyType) {
	        this.buyType = buyType;
	    }

	    public String getSuggestCode() {
	        return suggestCode;
	    }

	    public void setSuggestCode(String suggestCode) {
	        this.suggestCode = suggestCode;
	    }


}
