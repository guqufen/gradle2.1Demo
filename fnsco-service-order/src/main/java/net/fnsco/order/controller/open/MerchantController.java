package net.fnsco.order.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.merchant.MerchantService;
import net.fnsco.order.controller.app.jo.MerchantJO;

@RestController
@RequestMapping(value = "/open/merchant", method = RequestMethod.POST)
public class MerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;

    /**
     * 获取商户编号
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getMerCode")
    @ApiOperation(value = "获取商户编号")
    public ResultDTO getMerCode(@RequestBody MerchantJO merchant) {
        String randomCode = merchantService.getMerCode(merchant.getMerCode(), merchant.getChannelType());
        return success(randomCode);
    }
}