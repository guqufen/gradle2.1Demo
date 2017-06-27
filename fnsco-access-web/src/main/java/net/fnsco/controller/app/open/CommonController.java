package net.fnsco.controller.app.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.merchant.MerchantService;
import net.fnsco.controller.app.jo.MerchantJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/open/comm")
public class CommonController extends BaseController {
    @Autowired
    private MerchantService merchantService;

    /**
     * 获取APP下载地址
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/appDownUrl", method = RequestMethod.POST)
    @ApiOperation(value = "获取APP下载地址")
    public ResultDTO getMerCode(@RequestBody MerchantJO merchant) {
        return success("http://www.zheft.cn/download");
    }
}
