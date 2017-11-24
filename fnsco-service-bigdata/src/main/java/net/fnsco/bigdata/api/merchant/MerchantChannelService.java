package net.fnsco.bigdata.api.merchant;

import java.util.Date;

public interface MerchantChannelService {
	
	void updateChannel_Merc_IdByInnerCode(String secMerId,Date modifyTime,String innerCode);

}
