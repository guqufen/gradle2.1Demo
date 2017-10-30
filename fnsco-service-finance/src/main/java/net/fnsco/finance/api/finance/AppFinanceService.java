/**
 * 
 */
package net.fnsco.finance.api.finance;



import net.fnsco.core.base.ResultDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.service.domain.FinanceIoType;

/**
 * 记账相关接口
 * @author hjt
 *
 */
public interface AppFinanceService {
	/**
	 * 查询每日记账信息流水（根据内部商户号，时间）
	 * @param financeQuery
	 * @return
	 */
	ResultDTO<FinanceBookKeepingDTO> queryFinanceDayList(FinanceQueryDTO financeQuery);
	/**
	 * 查询收支子类型
	 * @return
	 */
	ResultDTO<FinanceIoType> queryIoTypeList();
	
	/**
	 * 每日一记新增记录
	 * @return
	 */
	ResultDTO addFinance(FinanceRecordDTO financeRecordDTO);
}
