package net.fnsco.web.controller.app.finance;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.dto.QueryDetailDTO;
import net.fnsco.finance.api.finance.AppFinanceService;
import net.fnsco.finance.service.domain.FinanceAccountBook;
import net.fnsco.finance.service.domain.FinanceIoType;


/**
 * 查询记账信息
 * @author hjt
 *
 */
@RestController
@RequestMapping(value = "/app/finance")
public class AppFinanceController extends BaseController {

    
	@Autowired
	private AppFinanceService appFinanceService;
	/**
	 * 根据app用户id，实体商户以及时间查询当前用户的记账信息
	 * @param financeQuery
	 * @return
	 */
    @RequestMapping(value = "/getFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "查询记账信息")
    public ResultDTO<FinanceBookKeepingDTO> getFinance (@RequestBody FinanceQueryDTO financeQuery) {
    	ResultDTO<FinanceBookKeepingDTO> dayResultPage = appFinanceService.queryFinanceDayList(financeQuery);
        return dayResultPage;
    }
    /**
     * 查询收支子类型数据
     * @return
     */
    @RequestMapping(value = "/getIoType" , method = RequestMethod.POST)
    @ApiOperation(value = "查询收支子类型信息")
    public ResultDTO modifyFinance() {
    	ResultDTO<FinanceIoType> IoType = appFinanceService.queryIoTypeList();
        return IoType;
    } 
    /**
     * 新增记账信息
     * @param financeRecordDTO
     * @return
     */
    @RequestMapping(value = "/addFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "新增记账信息")
    public ResultDTO addFinance(@RequestBody FinanceRecordDTO financeRecordDTO) {
        return appFinanceService.addFinance(financeRecordDTO);
    } 
    
    @RequestMapping(value = "/modifyFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "修改记账信息")
    public ResultDTO modifyFinance(@RequestBody FinanceRecordDTO financeRecordDTO) {
    	 return appFinanceService.modifyFinance(financeRecordDTO);
    } 
    
    @RequestMapping(value = "/getFinanceDetails" , method = RequestMethod.POST)
    @ApiOperation(value = "查询记账详情")
    public ResultDTO getFinanceDetails(@RequestBody FinanceAccountBook financeAccountBook) {
    	Integer id= financeAccountBook.getId();
    	ResultDTO dayResultPage = appFinanceService.queryFinanceDetailsById(id);
        return dayResultPage;
    } 
}
