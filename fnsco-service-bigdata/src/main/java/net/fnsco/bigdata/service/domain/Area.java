package net.fnsco.bigdata.service.domain;

/**
 * 地区码
 * @author yx
 *
 */
public class Area {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 上级地址id
	 */
	private Integer supper_id;

	/**
	 * 级别
	 */
	private Integer level;

	private String zxyh_code;
	public String getZxyh_code() {
		return zxyh_code;
	}

	public void setZxyh_code(String zxyh_code) {
		this.zxyh_code = zxyh_code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSupper_id() {
		return supper_id;
	}

	public void setSupper_id(Integer supper_id) {
		this.supper_id = supper_id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
