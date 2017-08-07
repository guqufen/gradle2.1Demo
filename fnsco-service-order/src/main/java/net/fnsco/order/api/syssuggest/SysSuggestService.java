package net.fnsco.order.api.syssuggest;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.dto.SysSuggestDTO;

public interface SysSuggestService {
	ResultPageDTO<SysSuggestDTO> queryPageList(SysSuggestDTO sysmsg,Integer currentPageNum, Integer perPageSize);
	
	ResultDTO<SysSuggestDTO> queryById(Integer id);
	
}
