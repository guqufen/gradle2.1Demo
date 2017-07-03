package net.fnsco.controller.app.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.TradeDataQueryDTO;
import net.fnsco.api.trade.TradeDataService;
import net.fnsco.controller.app.jo.TradeDataJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.trade.TradeData;

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
    public ResultDTO queryList(@RequestBody TradeDataQueryDTO tradeQueryDTO) {
        logger.warn("/queryList查询交易流水入参:"+JSON.toJSONString(tradeQueryDTO));
        ResultPageDTO<TradeData> result = tradeDataService.queryAllByCondition(tradeQueryDTO);
        return success(result);
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
    public ResultDTO information(@RequestBody TradeDataQueryDTO tradeQueryDTO) {
        TradeData result = tradeDataService.queryByTradeId(tradeQueryDTO.getTradeId());
        return ResultDTO.success(result);
    }

}
