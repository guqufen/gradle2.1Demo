package net.fnsco.web.controller.merchant.pos;

import java.util.List;

import net.fnsco.core.base.JO;

public class PosJO2 extends JO {
	private List<MerchantChannelJO2> poses;
	private String innerCode;

	public List<MerchantChannelJO2> getPoses() {
		return poses;
	}

	public void setPoses(List<MerchantChannelJO2> poses) {
		this.poses = poses;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

}
