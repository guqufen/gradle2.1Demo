package net.fnsco.order.controller.web.merchant;

import java.util.List;

import com.google.common.collect.Lists;

import net.fnsco.order.api.dto.WebMerchantPosDTO;
import net.fnsco.order.api.dto.WebMerchantTerminalDTO;
import net.fnsco.order.controller.web.merchant.jo.MerchantChannelJO;
import net.fnsco.order.controller.web.merchant.jo.MerchantPosJO;
import net.fnsco.order.service.domain.MerchantChannel;
import net.fnsco.order.service.domain.MerchantPos;
import net.fnsco.order.service.domain.MerchantTerminal;

public class MerchantHelper {
    public static List<WebMerchantPosDTO> toPosDTO(List<MerchantChannelJO> channelJO,String innerCode) {
        List<WebMerchantPosDTO> result = Lists.newArrayList();
        for (MerchantChannelJO jo : channelJO) {
            WebMerchantPosDTO posDto = new WebMerchantPosDTO();
            MerchantChannel channel =jo.getMerChannel();
            channel.setInnerCode(innerCode);
            posDto.setMerChannel(channel);
            List<WebMerchantTerminalDTO> posInfos = Lists.newArrayList();
            posDto.setPosInfos(posInfos);
           
            List<MerchantPosJO> posJOList = jo.getPosInfos();
            for (MerchantPosJO temp : posJOList) {
            	 temp.setInnerCode(innerCode);
            	 WebMerchantTerminalDTO terminalDto = new WebMerchantTerminalDTO();
                //POS机子信息
                MerchantPos merchantPos = temp.toMerchantPos();
                //终端信息
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
