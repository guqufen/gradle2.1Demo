package net.fnsco.trading.service.third.ticket;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.trading.service.third.ticket.dto.ticketsAvailableDTO;
import net.fnsco.trading.service.third.ticket.util.TrainTicketsUtil;

@Service
public class TicketService extends BaseService {

    public List<ticketsAvailableDTO> queryTicketList(String startSite, String endSite, String buyDate) {
        List<ticketsAvailableDTO> resultList = Lists.newArrayList();
        if (Strings.isNullOrEmpty(startSite)) {
            return resultList;
        }
        if (Strings.isNullOrEmpty(endSite)) {
            return resultList;
        }
        if (Strings.isNullOrEmpty(buyDate)) {
            return resultList;
        }
        //Map data = getTicketsAvailable(buyDate, startSite, endSite);
        String data = TrainTicketsUtil.getTicketsAvailable("2017-12-26", "HGH", "VNP");
        if (Strings.isNullOrEmpty(data)) {
            return resultList;
        }
        resultList = JSON.parseArray(data, ticketsAvailableDTO.class);
        return resultList;
    }

}
