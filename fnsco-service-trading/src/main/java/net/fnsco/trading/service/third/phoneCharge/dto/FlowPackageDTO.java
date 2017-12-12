package net.fnsco.trading.service.third.phoneCharge.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

/**
 * 流量套餐
 * 
 * @author Administrator
 *
 */
public class FlowPackageDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "套餐ID", example = "套餐ID")
	private String id;
	@ApiModelProperty(value = "套餐流量名称", example = "套餐流量名称")
	private String p;
	@ApiModelProperty(value = "套餐流量值", example = "套餐流量值")
	private String v;
	@ApiModelProperty(value = "价格", example = "价格")
	private String inprice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public String toString() {
		return "{套餐ID:"+id+",套餐流量名称:"+p+",套餐流量值:"+v+",价格"+inprice+"}";
	}
}
