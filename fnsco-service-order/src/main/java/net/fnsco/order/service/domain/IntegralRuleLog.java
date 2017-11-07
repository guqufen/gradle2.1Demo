package net.fnsco.order.service.domain;

import java.util.Date;

import net.fnsco.core.base.DTO;

/**
 * 积分日志表：i_integral_log实体对象
 * 
 * @author Administrator
 *
 */
public class IntegralRuleLog extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 主键

	private String entityInnerCode;// 实体内部商户号

	private String ruleCode;// 积分规则代码

	private Integer integral;// 积分值

	private String integralDate;// 记分日期

	private String description;// 积分描述

	private Date createTime;// 创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getIntegralDate() {
		return integralDate;
	}

	public void setIntegralDate(String integralDate) {
		this.integralDate = integralDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static enum IntegralTypeEnum {
		/**
		 * CODE_POS01("001", "0") 001：表示CODE
		 * 0：表示类型，0-不封顶无条件；1-表示积分封顶，10分；2-表示次数，当天第1次有效；3-表示前3次
		 */
		CODE_POS01("001", "1", "POS收银交易金额>10"), 
		CODE_POS02("002", "1", "POS收银交易金额>=500"), 
		CODE_YQ01("003", "2", "邀请新商家分享连接-未知"), 
		CODE_YQ02("004", "0", "邀请新商家成功"), 
		CODE_SQ("005", "0", "申请新机新增POS机"), 
		CODE_LR("006", "3", "录入店铺信息新增店铺，前三次有效"), 
		CODE_JZ("007", "1", "每日记账，最多10分"),
		CODE_YQ03("008", "2", "邀请新商家分享连接-微信聊天"),
		CODE_YQ04("009", "2", "邀请新商家分享连接-微信朋友圈"),
		CODE_YQ05("010", "2", "邀请新商家分享连接-QQ聊天"),
		CODE_YQ06("011", "2", "邀请新商家分享连接-QQ空间"),
		CODE_YQ07("012", "2", "邀请新商家分享连接-新浪微博");
		

		private String code;
		private String type;
		private String name;

		private IntegralTypeEnum(String code, String type, String name) {
			this.code = code;
			this.name = name;
			this.type = type;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		public String getType() {
			return type;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		public static String getDataByCode(String code) {

			for (IntegralTypeEnum eopen : IntegralTypeEnum.values()) {
				if (eopen.code.equals(code)) {

					return eopen.type;
				}
			}
			return null;
		}
	}
}
