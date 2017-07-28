package net.fnsco.withhold.web.trade;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.withhold.service.trade.TradeDataService;
import net.fnsco.withhold.service.trade.WithholdService;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;

@Controller
@RequestMapping(value = "/web/tradeData", method = RequestMethod.POST)
@Api(value = "/web/tradeData", tags = { "代扣交易管理" })
public class TradeDataController extends BaseController {

    @Autowired
    private TradeDataService tradeDataService;
    @Autowired
    private WithholdService withholdService;
    // 列表页
    @RequestMapping("/list")
    public String list() {
        return "";
    }

    // 分页
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "collectPayment", method = RequestMethod.GET)
    public ResultDTO collectPayment() {
        withholdService.collectPayment(0);
        return ResultDTO.success();
    }

    // 分页
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ResultDTO page(@RequestBody TradeDataDO tradeData) {
        logger.info("开始分页查询TradeDataController.page, tradeData=" + tradeData.toString());
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "page", "rows" });
        Integer page = params.get("page");
        Integer rows = params.get("rows");
        ResultPageDTO<TradeDataDO> pager = this.tradeDataService.page(tradeData, page, rows);
        return success(pager);
    }

    // 详情
    @ApiOperation(value = "查询详情", notes = "查询详情")
    @ResponseBody
    @RequestMapping(value = "detail")
    public ResultDTO detail(@RequestParam String id) {
        TradeDataDO result = this.tradeDataService.doQueryById(NumberUtils.toInt(id));
        return success(result);
    }
}