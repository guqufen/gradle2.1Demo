package net.fnsco.bigdata.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

/**
 * @desc APP商家列表实体
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年6月29日 下午5:50:19
 */

public class MerChantCoreDTO extends DTO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 2938074403134697431L;

	private Integer id;

	private String merName;

	private String legalPerson;

	private String registAddress;

	private String innerCode;// 内部商户号

	private BigDecimal scores;// 商户积分

	private String mercLevel;// 商户等级
	
	private String levelName;//等级名称
	
	private BigDecimal nextScores;// 下一级积分
	
	private String nextLevelName;//下一级名称
	
	private BigDecimal distScores;//积分差值
	
	private String levelIcon;//等级图标

	public String getLevelIcon() {
		return levelIcon;
	}

	public void setLevelIcon(String levelIcon) {
		this.levelIcon = levelIcon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getNextScores() {
		return nextScores;
	}

	public void setNextScores(BigDecimal nextScores) {
		this.nextScores = nextScores;
	}

	public String getNextLevelName() {
		return nextLevelName;
	}

	public void setNextLevelName(String nextLevelName) {
		this.nextLevelName = nextLevelName;
	}

	public BigDecimal getDistScores() {
		return distScores;
	}

	public void setDistScores(BigDecimal distScores) {
		this.distScores = distScores;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public BigDecimal getScores() {
		return scores;
	}

	public void setScores(BigDecimal scores) {
		this.scores = scores;
	}

	public String getMercLevel() {
		return mercLevel;
	}

	public void setMercLevel(String mercLevel) {
		this.mercLevel = mercLevel;
	}

	/**
	 * 能否生成台码
	 */
	private boolean canCreateTaiCode;

	/**
	 * canCreateTaiCode
	 *
	 * @return the canCreateTaiCode
	 * @since CodingExample Ver 1.0
	 */

	public boolean isCanCreateTaiCode() {
		return canCreateTaiCode;
	}

	/**
	 * canCreateTaiCode
	 *
	 * @param canCreateTaiCode
	 *            the canCreateTaiCode to set
	 * @since CodingExample Ver 1.0
	 */

	public void setCanCreateTaiCode(boolean canCreateTaiCode) {
		this.canCreateTaiCode = canCreateTaiCode;
	}

	/**
	 * id
	 *
	 * @return the id
	 * @since CodingExample Ver 1.0
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * id
	 *
	 * @param id
	 *            the id to set
	 * @since CodingExample Ver 1.0
	 */

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * merName
	 *
	 * @return the merName
	 * @since CodingExample Ver 1.0
	 */

	public String getMerName() {
		return merName;
	}

	/**
	 * merName
	 *
	 * @param merName
	 *            the merName to set
	 * @since CodingExample Ver 1.0
	 */

	public void setMerName(String merName) {
		this.merName = merName;
	}

	/**
	 * legalPerson
	 *
	 * @return the legalPerson
	 * @since CodingExample Ver 1.0
	 */

	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * legalPerson
	 *
	 * @param legalPerson
	 *            the legalPerson to set
	 * @since CodingExample Ver 1.0
	 */

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * registAddress
	 *
	 * @return the registAddress
	 * @since CodingExample Ver 1.0
	 */

	public String getRegistAddress() {
		return registAddress;
	}

	/**
	 * registAddress
	 *
	 * @param registAddress
	 *            the registAddress to set
	 * @since CodingExample Ver 1.0
	 */

	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

}
