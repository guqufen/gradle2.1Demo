package net.fnsco.controller.app.merchat;

import java.util.Date;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String sendCode="111111_22222";
		 String missM = sendCode.substring(sendCode.lastIndexOf("_")+1, sendCode.length());
			
		long oldTime = Long.valueOf(missM);
		System.out.println(oldTime);
			
	}

}
