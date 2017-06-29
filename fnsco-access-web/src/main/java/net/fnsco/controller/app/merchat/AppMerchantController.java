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

/**
 * 
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年6月28日 下午3:45:14
 *
 */
@RestController
@RequestMapping(value = "/app/merchant", method = RequestMethod.POST)
public class AppMerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;

    /**
     * 获取商户编号
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/addMerChant")
    @ApiOperation(value = "关联商铺")
    public ResultDTO addMerChant(@RequestBody MerchantJO merchant) {
        String randomCode = merchantService.getMerCode(merchant.getMerCode(), merchant.getChannelType());
        return success(randomCode);
    }

}
