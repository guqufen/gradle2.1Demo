package net.fnsco.web.controller.e789.bill;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.e789.jo.BillJO;
import net.fnsco.web.controller.e789.vo.BillVO;

/**
 * @desc 账单信息业务控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:58:22
 */
@RestController
@RequestMapping(value = "/app/e789/bill", method = RequestMethod.POST)
@Api(value = "/app/e789/bill", tags = { "账单信息相关功能接口" })
public class BillContrller extends BaseController {
	
	/**
	 * queryBillList:(获取账单列表接口)
	 *
	 * @param  @param billJO
	 * @param  @return    设定文件
	 * @return ResultDTO<List<BillVO>>    DOM对象
	 * @author tangliang
	 * @date   2017年12月4日 下午6:04:22
	 */
	@RequestMapping(value = "/queryBillList")
    @ApiOperation(value = "获取账单列表接口")
    public ResultDTO<List<BillVO>> queryBillList(@RequestBody BillJO billJO) {
        return success(null);
    }
}
