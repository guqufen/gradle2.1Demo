package net.fnsco.web.controller.e789.account;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.e789.jo.AccountBalanceJO;
import net.fnsco.web.controller.e789.vo.AccountBalanceVO;

/**
 * @desc 身份证认证相关功能控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午9:45:15
 */
@RestController
@RequestMapping(value = "/app2c/e789/id", method = RequestMethod.POST)
@Api(value = "/app2c/e789/id", tags = { "身份证认证相关功能接口" })
public class IDAuthenticationController extends BaseController {
	
	
	@RequestMapping(value = "/auth")
    @ApiOperation(value = "身份证认证接口")
    public ResultDTO<AccountBalanceVO> idAuth(@RequestBody AccountBalanceJO accountBalanceJO) {
 		
        return success(null);
    }
}
