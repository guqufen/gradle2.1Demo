package net.fnsco.finance.service.dao.master;

import java.util.List;

import net.fnsco.finance.api.dto.AppUserEntityDTO;
import net.fnsco.finance.api.dto.FinanceDetailDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.dto.QueryDetailDTO;
import net.fnsco.finance.service.domain.FinanceAccount;
import net.fnsco.finance.service.domain.FinanceAccountBook;
import net.fnsco.finance.service.domain.FinanceIoType;

public interface FinanceAccountBookDao {
	
	List<String> queryDates(FinanceQueryDTO financeQuery);
	
	List<FinanceAccountBook> queryList(FinanceQueryDTO financeQuery);
	
	List<FinanceAccountBook> queryAmount(FinanceQueryDTO financeQuery);
	
	List<AppUserEntityDTO> queryEntityList(String id);
	
	List<FinanceIoType> queryIoTypeList();
	
	FinanceAccount queryShopInnerCode(FinanceAccount financeAccount);
	
    int deleteByPrimaryKey(Integer id);

    int insertAccount(FinanceAccount fa);

    int insertAccountBook(FinanceRecordDTO financeRecordDTO);


    FinanceAccountBook selectByPrimaryKey(Integer id);

    int updateAccountBook(FinanceRecordDTO financeRecordDTO);

    FinanceDetailDTO queryFinanceDetail(Integer id);

}