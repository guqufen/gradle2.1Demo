package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.web.controller.e789.jo.CommonJO;

public class DeletePassengerJO extends CommonJO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "乘客id", example = "乘客id")
    private Integer passengerId;
	/**
	 * @return the passengerId
	 */
	public Integer getPassengerId() {
		return passengerId;
	}
	/**
	 * @param passengerId the passengerId to set
	 */
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	

}
