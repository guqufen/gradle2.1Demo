package net.fnsco.web.controller.open;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.dto.MerchantSynchronizationDO;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.bigdata.service.modules.merchant.MerchantInfoImportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.web.controller.open.jo.MerchantJO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/open/merchant", method = RequestMethod.POST)
@Api(value = "/open/merchant", tags = { "商户相关接口" })
public class MerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AppUserService  appUserService;
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
        if (null == posName) {
            return fail();
        }
        return success(posName);
    }

    /**
     * 
     * isHaveAppUser:(判断是否有appUser用户，0没有，1有但没登录过，2有且登录过)
     *
     * @param merchant
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/isHaveAppUser")
    @ApiOperation(value = "获取商户pos机名称")
    public ResultDTO isHaveAppUser(@RequestBody MerchantJO merchant) {
        List<String> innerCodeList = merchantService.getMerchantAppUser(merchant.getSnCode());
        if (CollectionUtils.isEmpty(innerCodeList)) {
            return success("0");
        }
        String flag = "0";
        for (String innerCode : innerCodeList) {
            List<MerchantUserRel> tempList = appUserService.getAppUserMerchantByInnerCode(innerCode);
            if (!CollectionUtils.isEmpty(tempList) && "0".equals(flag)) {
                flag = "1";
            }
            for (MerchantUserRel userRel : tempList) {
                AppUser user = appUserService.selectAppUserById(userRel.getAppUserId());
                if (user.getLastLoginTime() != null) {
                    flag = "2";
                    break;
                }
            }
            if ("2".equals(flag)) {
                break;
            }
        }
        return success(flag);
    }
    
    /**
     * importData:(导入数据)
     * @param merchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月21日 下午2:40:58
     * @return ResultDTO    DOM对象
     */
    @RequestMapping(value = "/importData")
    @ApiOperation(value = "导入商户所有数据")
    public ResultDTO importData(String datas) {
        
        logger.info("收到商户数据:"+datas);
        
        JSONArray jsonArray = JSONArray.fromObject(datas);
        List<MerchantSynchronizationDO> params = Lists.newArrayList();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = JSONObject.fromObject(obj);
            MerchantSynchronizationDO merdo =  (MerchantSynchronizationDO) JSONObject.toBean(jsonObject, MerchantSynchronizationDO.class);
            params.add(merdo);
        }
        
        List<Object[]> customerList = MerchantSynchronizationDO.installListDatas(params);
        
        try {
            ResultDTO<String> result = merchantInfoImportService.merchantBatchImportToDB(customerList, 1);
            logger.info("同步商户数据结果"+result);
            return result;
        } catch (ParseException e) {
            
            e.printStackTrace();
            
        }
        return ResultDTO.fail();
    }
}
