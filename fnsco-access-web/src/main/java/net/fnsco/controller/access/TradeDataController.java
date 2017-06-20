package net.fnsco.controller.access;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.TradeDataDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/app/trade")
public class TradeDataController extends BaseController {

    /**
     * 保存拉卡拉交易数据到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存交易流水")
    public ResultDTO saveTrade(@RequestBody TradeDataDTO tradeData) {
        return success();
    }

    /**
     * 分布查询交易流水信息
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
