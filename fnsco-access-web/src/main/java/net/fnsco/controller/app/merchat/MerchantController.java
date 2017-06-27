package net.fnsco.controller.app.merchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.merchant.MerchantService;
import net.fnsco.controller.app.jo.MerchantJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/app/merchant")
public class MerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;

    /**
     * 保存拉卡拉交易数据到库
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
