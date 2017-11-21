package net.fnsco.web.controller.merchant;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import net.fnsco.bigdata.api.dto.WebMerchantPosDTO;
import net.fnsco.bigdata.api.dto.WebMerchantPosDTO2;
import net.fnsco.bigdata.api.dto.WebMerchantTerminalDTO;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.web.controller.merchant.jo.MerchantChannelJO;
import net.fnsco.web.controller.merchant.jo.MerchantPosJO;
import net.fnsco.web.controller.merchant.pos.MerchantChannelJO2;
public class MerchantHelper {
	public static List<WebMerchantPosDTO2> toPosDTO2(List<MerchantChannelJO2> channelJO, String innerCode,
			Integer userId) {
		List<WebMerchantPosDTO2> result = Lists.newArrayList();
		WebMerchantPosDTO2 posDto = new WebMerchantPosDTO2();
		 MerchantChannelJO2 jo =channelJO.get(0);
			
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
	
	
	
	
	public static List<WebMerchantPosDTO> toPosDTO(List<MerchantChannelJO> channelJO, String innerCode,
			Integer userId) {
		List<WebMerchantPosDTO> result = Lists.newArrayList();
		for (MerchantChannelJO jo : channelJO) {
			WebMerchantPosDTO posDto = new WebMerchantPosDTO();
			MerchantChannel channel = jo.getMerChannel();
			channel.setInnerCode(innerCode);
			channel.setModifyTime(new Date());
			channel.setModifyUserId(userId);
			posDto.setMerChannel(channel);
			List<WebMerchantTerminalDTO> posInfos = Lists.newArrayList();
			posDto.setPosInfos(posInfos);

			List<MerchantPosJO> posJOList = jo.getPosInfos();
			for (MerchantPosJO temp : posJOList) {
				temp.setInnerCode(innerCode);
				WebMerchantTerminalDTO terminalDto = new WebMerchantTerminalDTO();
				// POS机子信息
				MerchantPos merchantPos = temp.toMerchantPos();
				// 终端信息
				List<MerchantTerminal> terminals = temp.toTerminalList();
				terminalDto.setMerchantPos(merchantPos);
				terminalDto.setTerminals(terminals);
				posInfos.add(terminalDto);
			}
			result.add(posDto);
		}
		return result;
	}

	
}
