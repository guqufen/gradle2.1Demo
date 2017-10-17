package net.fnsco.bigdata.api.terminal;

import net.fnsco.bigdata.service.domain.TerminalInformation;
import net.fnsco.core.base.ResultPageDTO;

public interface TerminalService {

    /**
     * queryTradeData:(这里用一句话描述这个方法的作用) web分页查询
     * @param merchantCore
     * @param currentPageNum
     * @param perPageSize
     * @return    设定文件
     * @return ResultPageDTO<TradeDataDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultPageDTO<TerminalInformation> queryTerminalData(TerminalInformation TerminalInformation, int currentPageNum, int perPageSize);
}
