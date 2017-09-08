package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

public class SnCodeDTO extends DTO {
	private String sncode;
	// 终端编号刷卡
	private String terminalcode1;
	// 终端编号扫码
	private String terminalcode2;
	
	private Integer num;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSncode() {
		return sncode;
	}
	public void setSncode(String sncode) {
		this.sncode = sncode;
	}
	public String getTerminalcode1() {
		return terminalcode1;
	}
	public void setTerminalcode1(String terminalcode1) {
		this.terminalcode1 = terminalcode1;
	}
	public String getTerminalcode2() {
		return terminalcode2;
	}
	public void setTerminalcode2(String terminalcode2) {
		this.terminalcode2 = terminalcode2;
	}
}
