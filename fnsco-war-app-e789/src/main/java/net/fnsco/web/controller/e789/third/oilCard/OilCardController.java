package net.fnsco.web.controller.e789.third.oilCard;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.e789.third.ticket.vo.SiteVO;


@RestController
@RequestMapping(value = "/app2c/oilCard", method = RequestMethod.POST)
@Api(value = "/app2c/oilCard", tags = { "账户-油卡功能相关接口" })
public class OilCardController extends BaseController {
	
	
	@RequestMapping(value = "/queryShelf")
    @ApiOperation(value = "加油卡货架查询")
    public ResultDTO<List<SiteVO>> queryShelf() {
        return success();
    }
	
	@RequestMapping(value = "/topup")
    @ApiOperation(value = "加油卡充值下单")
    public ResultDTO<List<SiteVO>> topup() {
        return success();
    }
	
	@RequestMapping(value = "/queryOrder")
    @ApiOperation(value = "订单结果查询")
    public ResultDTO<List<SiteVO>> queryOrder() {
        return success();
    }
}
