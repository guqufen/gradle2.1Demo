package net.fnsco.controller.app.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.trade.TradeDataService;
import net.fnsco.controller.app.jo.TradeDataJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * 交易流水处理类
 * @author sxf
 *
 */
@RestController
@RequestMapping(value = "/app/trade", method = RequestMethod.POST)
public class TradeDataController extends BaseController {
    @Autowired
    private Environment      env;
    @Autowired
    private TradeDataService tradeDataService;

    /**
     * 查询交易流水信息
     *
     * @param userName
     * @return
     */
    //@ApiIgnore //使用该注解忽略这个API
    @RequestMapping(value = "/queryList")
    @ApiOperation(value = "查询交易流水")
    public ResultDTO queryList(@RequestBody TradeDataJO tradeData) {
        ResultPageDTO resultPage = new ResultPageDTO();
        resultPage.setTotal(90);
        return success(resultPage);
    }
    /**
     * 查询交易流水信息
     *
     * @param userName
     * @return
     */
    //@ApiIgnore //使用该注解忽略这个API
    @RequestMapping(value = "/information")
    @ApiOperation(value = "查询交易流水")
    public ResultDTO information(@RequestBody TradeDataJO tradeData) {
        ResultPageDTO resultPage = new ResultPageDTO();
        resultPage.setTotal(90);
        return success(resultPage);
    }
    
}
