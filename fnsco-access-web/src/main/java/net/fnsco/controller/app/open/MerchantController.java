package net.fnsco.controller.app.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.merchant.MerchantService;
import net.fnsco.controller.app.jo.MerchantJO;
import net.fnsco.core.base.BaseController;

@RestController
@RequestMapping(value = "/open/merchant")
public class MerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;

    /**
     * 获取商户编号
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getMerCode", method = RequestMethod.POST)
    @ApiOperation(value = "获取商户编号")
    public String getMerCode(@RequestBody MerchantJO merchant) {
        String randomCode = merchantService.getMerCode(merchant.getMerCode(), merchant.getChannelType());
        return success(randomCode).toJsonString();
    }
}