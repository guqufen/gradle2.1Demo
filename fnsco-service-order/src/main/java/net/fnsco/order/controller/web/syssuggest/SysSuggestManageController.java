package net.fnsco.order.controller.web.syssuggest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.dto.SysSuggestDTO;
import net.fnsco.order.api.syssuggest.SysSuggestService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web/syssuggest")
public class SysSuggestManageController extends BaseController {
    
    @Autowired
    private SysSuggestService sysSuggestService;
    /**
     * 查询,跳转到商户信息首页
     * @param sysmsg
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/query",method= RequestMethod.GET)
    @ResponseBody
    public ResultPageDTO<SysSuggestDTO> query(SysSuggestDTO sysmsg,@RequestParam("currentPageNum") Integer currentPageNum,@RequestParam("pageSize") Integer pageSize){
        return sysSuggestService.queryPageList(sysmsg, currentPageNum, pageSize);
    }
    /**
     * 根据ID 查询详情
     * @param id
     * @return
     */
	@RequestMapping(value ="/querySuggestInfo",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<SysSuggestDTO> querySuggestById(Integer id){
		logger.info("查询出商户所有关联数据id = "+id);
		return sysSuggestService.queryById(id);
	}
    
}














