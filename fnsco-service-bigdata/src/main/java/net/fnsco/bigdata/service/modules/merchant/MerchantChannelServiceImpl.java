package net.fnsco.bigdata.service.modules.merchant;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.api.merchant.MerchantChannelService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;

@Service
public class MerchantChannelServiceImpl implements MerchantChannelService {
	@Autowired
	private MerchantChannelDao merchantChannelDao;

//	@Override
//	public void updateChannel_Merc_IdByInnerCode(String secMerId,Date modifyTime,String innerCode) {
//		
//		merchantChannelDao.updateChannelMercIdByInnerCode(secMerId,modifyTime,innerCode);
//	}

}
