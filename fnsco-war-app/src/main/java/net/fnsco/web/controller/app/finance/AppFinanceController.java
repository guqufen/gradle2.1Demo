package net.fnsco.web.controller.app.finance;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.finance.api.dto.AppUserShopDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.dto.IoTypeAndShopDTO;
import net.fnsco.finance.api.finance.AppFinanceService;
import net.fnsco.finance.service.domain.FinanceAccountBook;
import net.fnsco.finance.service.domain.FinanceIoType;
import net.fnsco.order.api.merchant.IntegralLogService;


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
	@Autowired
	private IntegralLogService integralRuleLogService;
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
    public ResultDTO<IoTypeAndShopDTO> getIoType(@RequestBody FinanceQueryDTO financeQuery) {
    	List<FinanceIoType> ioList = appFinanceService.queryIoType();
    	String entityInnerCode =  financeQuery.getEntityInnerCode();
    	List<AppUserShopDTO> shopList = appFinanceService.queryShop(entityInnerCode);
    	IoTypeAndShopDTO ioTypeAndShop = new IoTypeAndShopDTO();
    	ioTypeAndShop.setFinanceIoTypeList(ioList);
    	ioTypeAndShop.setAppUserShopDTOList(shopList);
        return  ResultDTO.success(ioTypeAndShop);
    } 
    /**
     * 新增记账信息
     * @param financeRecordDTO
     * @return
     */
    @RequestMapping(value = "/addFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "新增记账信息")
    public ResultDTO addFinance(@RequestBody FinanceRecordDTO financeRecordDTO) {
        int i= appFinanceService.addFinance(financeRecordDTO);
        String entityInnerCode = financeRecordDTO.getEntityInnerCode();		
		integralRuleLogService.insert(entityInnerCode, "007");
		return ResultDTO.success();
    } 
    
    @RequestMapping(value = "/modifyFinance" , method = RequestMethod.POST)
    @ApiOperation(value = "修改记账信息")
    public ResultDTO modifyFinance(@RequestBody FinanceRecordDTO financeRecordDTO) {
    	int i =appFinanceService.modifyFinance(financeRecordDTO);
    	if(i==0) {
    		ResultDTO.fail("记账信息修改失败");
    	}
    	 return ResultDTO.success();
    } 
    
    @RequestMapping(value = "/getFinanceDetails" , method = RequestMethod.POST)
    @ApiOperation(value = "查询记账详情")
    public ResultDTO getFinanceDetails(@RequestBody FinanceAccountBook financeAccountBook) {
    	Integer id= financeAccountBook.getId();
    	ResultDTO dayResultPage = appFinanceService.queryFinanceDetailsById(id);
        return dayResultPage;
    } 
    
    @RequestMapping(value = "/deleteFinanceById" , method = RequestMethod.POST)
    @ApiOperation(value = "删除记账")
    public void deleteFinanceById(@RequestBody FinanceAccountBook financeAccountBook) {
    	Integer id= financeAccountBook.getId();
    	appFinanceService.deleteFinanceById(id);
    } 
}
