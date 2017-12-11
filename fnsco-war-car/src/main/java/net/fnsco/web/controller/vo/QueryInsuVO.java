package net.fnsco.web.controller.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.car.service.config.entity.ConfigDO;
import net.fnsco.core.base.VO;

public class QueryInsuVO extends VO {
	@ApiModelProperty(value="保险公司列表",name="insuList",example="保险公司列表")
	private List<String> insuList;// 保险公司列表

	/**
	 * @return the insuList
	 */
	public List<String> getInsuList() {
		return insuList;
	}

	/**
	 * @param insuList the insuList to set
	 */
	public void setInsuList(List<String> insuList) {
		this.insuList = insuList;
	}

}
