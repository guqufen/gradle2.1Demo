package net.fnsco.web.controller.jo;

import io.swagger.annotations.ApiModelProperty;

public class BuyCarJO {

		@ApiModelProperty(value = "name", name = "name", example = "购车人")
		private String name;
		@ApiModelProperty(value = "mobile", name = "mobile", example = "手机号")
		private String mobile;
	    /**
	     * 所在城市
	     */
		@ApiModelProperty(value = "cityId", name = "cityId", example = "所在城市")
	    private Integer cityId;

	    /**
	     * 汽车品牌
	     */
		@ApiModelProperty(value = "carTypeId", name = "carTypeId", example = "汽车品牌")
	    private Integer carTypeId;

	    /**
	     * 汽车型号
	     */
		@ApiModelProperty(value = "carModel", name = "carModel", example = "汽车型号")
	    private String carModel;

	    /**
	     * 金融方案
	     */
		@ApiModelProperty(value = "buyType", name = "buyType", example = "金融方案")
	    private String buyType;

	    /**
	     * 推荐码
	     */
		@ApiModelProperty(value = "suggestCode", name = "suggestCode", example = "推荐码")
	    private Integer suggestCode;

		/**
	     * 验证码
	     */
		@ApiModelProperty(value = "verCode", name = "verCode", example = "验证码")
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

	    public String getCarModel() {
	        return carModel;
	    }

	    public void setCarModel(String carModel) {
	        this.carModel = carModel;
	    }

	    public String getBuyType() {
	        return buyType;
	    }

	    public void setBuyType(String buyType) {
	        this.buyType = buyType;
	    }

	    public Integer getSuggestCode() {
	        return suggestCode;
	    }

	    public void setSuggestCode(Integer suggestCode) {
	        this.suggestCode = suggestCode;
	    }


}
