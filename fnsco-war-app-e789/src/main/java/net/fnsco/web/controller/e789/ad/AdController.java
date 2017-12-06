package net.fnsco.web.controller.e789.ad;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.e789.jo.AccountBalanceJO;
import net.fnsco.web.controller.e789.jo.AppAdJO;
import net.fnsco.web.controller.e789.vo.AccountBalanceVO;
import net.fnsco.web.controller.e789.vo.AppAdVO;

/**
 * @desc 广告资讯控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:01:12
 */
@RestController
@RequestMapping(value = "/app2c/e789/ad", method = RequestMethod.POST)
@Api(value = "/app2c/e789/ad", tags = { "广告资讯相关功能接口" })
public class AdController extends BaseController {
	
	/**
	 * 
	 * @param accountBalanceJO
	 * @return
	 */
 	@RequestMapping(value = "/queryAd")
    @ApiOperation(value = "查询广告资讯")
    public ResultDTO<AppAdVO> queryAdList(@RequestBody AppAdJO appAdJO) {
 		
        return success(null);
    }

}