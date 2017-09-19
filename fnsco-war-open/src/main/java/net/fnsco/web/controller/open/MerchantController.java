package net.fnsco.web.controller.open;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.bigdata.service.modules.merchant.MerchantInfoImportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.web.controller.open.jo.MerchantJO;

@RestController
@RequestMapping(value = "/open/merchant", method = RequestMethod.POST)
@Api(value = "/open/merchant", tags = { "开放接口商户相关接口" })
public class MerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private MerchantInfoImportService merchantInfoImportService;
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
        if(null == posName){
            return fail();
        }
        return success(posName);
    }
    
    @RequestMapping(value = "/isHaveAppUser")
    @ApiOperation(value = "获取商户pos机名称")
    public ResultDTO isHaveAppUser(@RequestBody MerchantJO merchant) {
        List<String> innerCodeList = merchantService.getMerchantAppUser(merchant.getSnCode());
        if(CollectionUtils.isEmpty(innerCodeList)){
            return success("0");
        }
        for(String innerCode: innerCodeList){
            List<MerchantUserRel> tempList =appUserService.getAppUserMerchantByInnerCode(innerCode);
            if(!CollectionUtils.isEmpty(tempList)){
                return success("1");
            }
        }
        
        return success(false);
    }
    
    /**
     * importMerchant:(导入商户信息接口)
     * @param merchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月19日 上午9:32:10
     * @return ResultDTO    DOM对象
     */
    @RequestMapping(value = "/importMerchant")
    @ApiOperation(value = "导入商户信息")
    public ResultDTO importMerchant(@RequestBody List<Object[]> customerList) {
        try {
             return merchantInfoImportService.merchantBatchImportToDB(customerList, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return success(false);
    }
}
