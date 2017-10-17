package net.fnsco.bigdata.service.modules.terminal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.fnsco.bigdata.api.terminal.TerminalService;
import net.fnsco.bigdata.service.dao.master.terminal.TerminalDAO;
import net.fnsco.bigdata.service.domain.TerminalInformation;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * 终端查询服务类
 * @author 
 *
 */
@Service
public class TerminalServiceImpl extends BaseService implements TerminalService {
    @Autowired
    private TerminalDAO        terminalDAO;
    /**
     * 条件分页查询
     */
    @Override
    public ResultPageDTO<TerminalInformation> queryTerminalData(TerminalInformation terminalInformation, int currentPageNum, int perPageSize) {
        PageDTO<TerminalInformation> pages = new PageDTO<TerminalInformation>(currentPageNum, perPageSize, terminalInformation);
        List<TerminalInformation> datas = terminalDAO.queryPageList(pages);
        int total = terminalDAO.queryTotalByCondition(terminalInformation);
        ResultPageDTO<TerminalInformation> result = new ResultPageDTO<TerminalInformation>(total, datas);
        return result;
    }
}
