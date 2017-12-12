package net.fnsco.trading.service.phoneCharge.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

/**
 * 检测号码支持的流量套餐DTO
 * 
 * @author Administrator
 *
 */
public class FlowPackageCheckDTO extends DTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="支持城市",example="支持城市")
	private String city;
	@ApiModelProperty(value="运营商",example="运营商")
	private String company;
	@ApiModelProperty(value="运营商ID",example="运营商ID")
	private String companytype;
	@ApiModelProperty(value="支持类型1：全国 2：城市",example="支持类型1：全国 2：城市")
	private String type;
	@ApiModelProperty(value="套餐名称",example="套餐名称")
	private String name;
	@ApiModelProperty(value="流量套餐列表",example="流量套餐列表")
	private List<FlowPackageDTO> flows;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FlowPackageDTO> getFlows() {
		return flows;
	}

	public void setFlows(List<FlowPackageDTO> flows) {
		this.flows = flows;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
