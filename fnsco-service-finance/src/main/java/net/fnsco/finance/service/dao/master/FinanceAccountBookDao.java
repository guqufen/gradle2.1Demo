package net.fnsco.finance.service.dao.master;

import java.util.List;

import net.fnsco.core.base.PageDTO;
import net.fnsco.finance.api.dto.AppUserEntityDTO;
import net.fnsco.finance.api.dto.FinanceEveryDayDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.service.domain.FinanceAccountBook;
import net.fnsco.finance.service.domain.FinanceIoType;

public interface FinanceAccountBookDao {
	
	List<FinanceEveryDayDTO> queryPageList(PageDTO<FinanceQueryDTO> pages);
	
	List<FinanceAccountBook> queryAmount(FinanceQueryDTO financeQuery);
	
	List<AppUserEntityDTO> queryEntityList(Integer id);
	
	List<FinanceIoType> queryIoTypeList();
	
	String queryShopInnerCode(String shopInnerCode);
	
	String queryAccountId(String accountId);
	
    int deleteByPrimaryKey(Integer id);

    //int insert(FinanceAccountBook record);

    //int insertSelective(FinanceAccountBook record);


    FinanceAccountBook selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(FinanceAccountBook record);

    //int updateByPrimaryKey(FinanceAccountBook record);
}