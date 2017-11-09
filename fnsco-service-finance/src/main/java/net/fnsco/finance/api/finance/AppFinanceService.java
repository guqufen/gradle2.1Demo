/**
 * 
 */
package net.fnsco.finance.api.finance;



import java.util.List;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.finance.api.dto.AppUserShopDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceDetailDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.dto.IoTypeAndShopDTO;
import net.fnsco.finance.api.dto.QueryDetailDTO;
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
	List<FinanceIoType> queryIoType();
	/**
	 * 查询店铺信息
	 * @return
	 */
	List<AppUserShopDTO> queryShop(String entityInnerCode);
	
	/**
	 * 每日一记新增记录
	 * @param financeRecordDTO
	 * @return
	 */
	void addFinance(FinanceRecordDTO financeRecordDTO);
	
	/**
	 * 编辑记账信息
	 * @param financeRecordDTO
	 * @return
	 */
	int modifyFinance(FinanceRecordDTO financeRecordDTO);
	/**
	 * 查询当日的记账详情
	 * @param queryDetailDTO
	 * @return
	 */
	ResultDTO<FinanceDetailDTO> queryFinanceDetailsById(Integer id);
	/**
	 * 更具id删除记账数据
	 * @param id
	 * @return
	 */
	void deleteFinanceById(Integer id);
}
