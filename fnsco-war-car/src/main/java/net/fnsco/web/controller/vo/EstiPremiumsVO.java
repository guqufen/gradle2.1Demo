package net.fnsco.web.controller.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class EstiPremiumsVO extends VO {
	@ApiModelProperty(value="预估保费",name="estiPremiums",example="预估保费")
	private BigDecimal estiPremiums;// 预估保费

	/**
	 * @return the estiPremiums
	 */
	public BigDecimal getEstiPremiums() {
		return estiPremiums;
	}

	/**
	 * @param estiPremiums the estiPremiums to set
	 */
	public void setEstiPremiums(BigDecimal estiPremiums) {
		this.estiPremiums = estiPremiums;
	}

}
