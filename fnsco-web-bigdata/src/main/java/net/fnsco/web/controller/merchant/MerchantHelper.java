package net.fnsco.web.controller.merchant;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import net.fnsco.bigdata.api.dto.WebMerchantPosDTO2;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.web.controller.merchant.pos.MerchantChannelJO2;

public class MerchantHelper {
	public static List<WebMerchantPosDTO2> toPosDTO2(List<MerchantChannelJO2> channelJO, String innerCode,
			Integer userId) {
		List<WebMerchantPosDTO2> result = Lists.newArrayList();
		WebMerchantPosDTO2 posDto = new WebMerchantPosDTO2();
		MerchantChannelJO2 jo = channelJO.get(0);

		MerchantChannel channel = jo.getMerChannel();
		channel.setInnerCode(innerCode);
		channel.setModifyTime(new Date());
		channel.setModifyUserId(userId);
		posDto.setMerChannel(channel);
		List<MerchantPos> posInfos = Lists.newArrayList();
		posDto.setPosInfos(posInfos);

		List<MerchantPos> posJOList = jo.getPosDeviceInfos();
		for (MerchantPos temp : posJOList) {
			temp.setInnerCode(innerCode);
			posInfos.add(temp);
		}
		List<MerchantTerminal> terminals = Lists.newArrayList();
		posDto.setTerminals(terminals);
		List<MerchantTerminal> terminalList = jo.getTerminaInfos();
		for (MerchantTerminal temp : terminalList) {
			temp.setInnerCode(innerCode);
			terminals.add(temp);
		}
		result.add(posDto);

		return result;
	}

}
