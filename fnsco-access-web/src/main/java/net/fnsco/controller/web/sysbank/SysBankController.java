package net.fnsco.controller.web.sysbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fnsco.api.sysbank.SysBankService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.SysBank;

/**
 * @desc 银行卡信息控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月7日 上午11:14:09
 */
@Controller
@RequestMapping("/web/bank")
public class SysBankController extends BaseController{
    
    @Autowired
    private SysBankService sysBankService;
    
    /**
     * merchatInfoIndex:(这里用一句话描述这个方法的作用) 表格分页查询
     *
     * @param sysBank
     * @param currentPageNum
     * @param pageSize
     * @return    设定文件
     * @return ResultPageDTO<MerchantCore>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/query")
    @ResponseBody
    public ResultPageDTO<SysBank> merchatInfoIndex(SysBank sysBank,Integer currentPageNum,Integer pageSize){
        logger.info("查询银行卡列表");
        return sysBankService.queryPageList(sysBank, currentPageNum, pageSize);
    }
}
