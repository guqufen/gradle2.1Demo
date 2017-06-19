package net.fnsco.controller.access;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@RestController
@RequestMapping(value = "/app/trade", method = RequestMethod.POST)
public class TradeDataController extends BaseController {

    /**
     * 保存拉卡拉交易数据到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ResultDTO findByName(@RequestParam(value = "userName", required = true) String userName) {
        return success();
    }
    
    /**
     * 分布查询交易流水信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultDTO queryTradeDate(@RequestParam(value = "userName", required = true) String userName) {
        ResultPageDTO resultPage = new ResultPageDTO();
        resultPage.setTotal(90);
        return success(resultPage);
    }
}
