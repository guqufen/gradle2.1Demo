package net.fnsco.web.controller.trade;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.trade.TradeReportService;

/**
 * @desc  报表初始化接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月7日 下午1:17:34
 */
@Controller
@RequestMapping(value = "/web/report")
public class TradeReportInstallController extends BaseController {
    
    @Autowired
    private TradeReportService tradeReportService;
    
    @RequestMapping("/install")
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:list" })
    public ResultDTO<Object> installReport(String startTime,String endTime){
        if(Strings.isNullOrEmpty(startTime)||Strings.isNullOrEmpty(endTime)){
            return ResultDTO.fail();
        }
        tradeReportService.buildTradeReportDaTa(startTime, endTime);
        return ResultDTO.success("");
    }
}
