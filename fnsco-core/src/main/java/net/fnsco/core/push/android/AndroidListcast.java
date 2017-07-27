package net.fnsco.core.push.android;

import net.fnsco.core.push.AndroidNotification;

public class AndroidListcast extends AndroidNotification {
	public AndroidListcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }

}