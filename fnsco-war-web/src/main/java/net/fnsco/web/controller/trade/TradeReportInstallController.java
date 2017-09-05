package net.fnsco.web.controller.trade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.push.AppPushService;
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
    
    @Autowired
    private AppPushService     appPushService;
    
    @RequestMapping("/install")
    @ResponseBody
    public ResultDTO<Object> installReport(String startTime,String endTime){
        if(Strings.isNullOrEmpty(startTime)||Strings.isNullOrEmpty(endTime)){
            return ResultDTO.fail();
        }
        tradeReportService.buildTradeReportDaTa(startTime, endTime);
        return ResultDTO.success("");
    }
    
    /**
     * installReport:(这里用一句话描述这个方法的作用)测试周报
     * @param startTime
     * @param endTime
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月1日 上午10:12:09
     * @return ResultDTO<Object>    DOM对象
     */
    @RequestMapping("/pushweekly")
    @ResponseBody
    public ResultDTO<Object> pushweekly(){
        appPushService.sendWeeklyDataMgs();
        return ResultDTO.success("");
    }
    
    public static void main(String[] args) {
        Date date = new Date(1504285484913l);
        System.out.println(date);
    }
}
