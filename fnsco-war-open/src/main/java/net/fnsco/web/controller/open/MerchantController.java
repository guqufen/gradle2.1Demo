package net.fnsco.web.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.open.jo.MerchantJO;

@RestController
@RequestMapping(value = "/open/merchant", method = RequestMethod.POST)
@Api(value = "/open/merchant", tags = { "开放接口商户相关接口" })
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

    /**
     * 更新商户pos机名称
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/updatePosName")
    @ApiOperation(value = "更新商户pos机名称")
    public ResultDTO updatePosName(@RequestBody MerchantJO merchant) {
        merchantService.updatePosName(merchant.getSnCode(), merchant.getPosName());
        return success();
    }

    /**
     * 获取商户pos机名称
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getPosName")
    @ApiOperation(value = "获取商户pos机名称")
    public ResultDTO getPosName(@RequestBody MerchantJO merchant) {
        String posName = merchantService.getPosName(merchant.getSnCode());
        return success(posName);
    }
}
