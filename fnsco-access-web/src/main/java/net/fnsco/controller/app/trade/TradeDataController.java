package net.fnsco.controller.app.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fnsco.api.trade.TradeDataService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/app/trade")
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
    @ApiIgnore //使用该注解忽略这个API
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultDTO queryTradeDate(@RequestParam(value = "userName", required = true) String userName) {
        ResultPageDTO resultPage = new ResultPageDTO();
        resultPage.setTotal(90);
        return success(resultPage);
    }
}
