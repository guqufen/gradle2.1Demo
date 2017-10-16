package net.fnsco.web.controller.act;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.service.act.LoanApplyUserService;
import net.fnsco.order.service.act.entity.LoanApplyUserDO;

/**
 * @desc 贷款信息
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月16日 下午2:52:20
 */
@Controller
@RequestMapping(value = "/web/loanApply")
public class LoanApplyController extends BaseController {
    
    @Autowired
    private LoanApplyUserService loanApplyUserService;
    @Autowired
    private MerchantCoreDao      merchantCoreDao;
    /**
     * query:(分页查询)
     * @param loanApplyUserDO
     * @param currentPageNum
     * @param pageSize
     * @return    设定文件
     * @author    tangliang
     * @date      2017年10月16日 下午2:57:10
     * @return ResultPageDTO<LoanApplyUserDO>    DOM对象
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
//    @RequiresPermissions(value = { "sys:app:user:list" })
    public ResultPageDTO<LoanApplyUserDO> query(LoanApplyUserDO loanApplyUserDO,Integer currentPageNum,Integer pageSize){
        ResultPageDTO<LoanApplyUserDO> result  = loanApplyUserService.page(loanApplyUserDO, currentPageNum, pageSize);
        List<LoanApplyUserDO> datas  = result.getList();
        for (LoanApplyUserDO loanApplyUserDO2 : datas) {
            if(StringUtils.isNotBlank(loanApplyUserDO2.getInnnerCode())){
                MerchantCore merCore = merchantCoreDao.selectByInnerCode(loanApplyUserDO2.getInnnerCode());
                if(null != merCore){
                    loanApplyUserDO2.setMerName(merCore.getMerName());
                }
            }
        }
        return result;
    }
}
