package net.fnsco.web.controller.merchant.jo;

import java.util.List;

import net.fnsco.core.base.JO;

public class PosJO extends JO {
	private List<MerchantChannelJO> poses;
	private String innerCode;

	public List<MerchantChannelJO> getPoses() {
		return poses;
	}

	public void setPoses(List<MerchantChannelJO> poses) {
		this.poses = poses;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

}
