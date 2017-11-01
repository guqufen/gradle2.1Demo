package net.fnsco.web.controller.app.finance;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.finance.AppFinanceService;
import net.fnsco.finance.service.domain.FinanceIoType;


/**
 * 查询记账信息
 * @author hjt
 *
 */
@RestController
@RequestMapping(value = "/app/finance", method = RequestMethod.POST)
public class AppFinanceController extends BaseController {

    
	@Autowired
	private AppFinanceService appFinanceService;
	/**
	 * 根据app用户id，实体商户以及时间查询当前用户的记账信息
	 * @param financeQuery
	 * @return
	 */
    @RequestMapping(value = "/getFinance " , method = RequestMethod.POST)
    @ApiOperation(value = "查询记账信息")
    public ResultDTO<FinanceBookKeepingDTO> getFinance (FinanceQueryDTO financeQuery) {
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
    
    @RequestMapping(value = "/addFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "新增记账信息")
    public ResultDTO addFinance(FinanceRecordDTO financeRecordDTO) {
        return appFinanceService.addFinance(financeRecordDTO);
    } 
    
    @RequestMapping(value = "/modifyFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "修改记账信息")
    public ResultDTO modifyFinance(FinanceQueryDTO financeQuery) {
    	ResultDTO<FinanceBookKeepingDTO> dayResultPage = appFinanceService.queryFinanceDayList(financeQuery);
        return null;
    } 
    
    @RequestMapping(value = "/getFinanceDetails" , method = RequestMethod.POST)
    @ApiOperation(value = "查询记账详情")
    public ResultDTO getFinanceDetails(FinanceQueryDTO financeQuery) {
    	ResultDTO<FinanceBookKeepingDTO> dayResultPage = appFinanceService.queryFinanceDayList(financeQuery);
        return null;
    } 
}
