package net.fnsco.bigdata.service.dao.master.terminal;

import java.util.List;


import net.fnsco.bigdata.service.domain.TerminalInformation;
import net.fnsco.core.base.PageDTO;

/**
 * 终端管理查询dao
 * @author Administrator
 *
 */
public interface TerminalDAO {

    /**
     * 条件分页查询
     */
    List<TerminalInformation> queryPageList(PageDTO<TerminalInformation> pages);
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(TerminalInformation record);
}