package net.fnsco.controller.app.merchat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.MerchantDTO;
import net.fnsco.api.merchant.MerchantService;
import net.fnsco.controller.app.jo.MerchantJO;
import net.fnsco.controller.app.jo.UserMerchantJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.MerchantCore;
import net.fnsco.service.domain.MerchantTerminal;

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
     * 关联商铺
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/addMerChant")
    @ApiOperation(value = "关联商铺")
    public ResultDTO addMerChant(@RequestBody MerchantDTO merchant) {
        ResultDTO result = merchantService.addMerChant(merchant);
        return result;
    }

    /**
     * 
     * getMerChants:(这里用一句话描述这个方法的作用) 根据app用户ID查询关联的商家列表信息
     * @param userMerchant
     * @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getMerChant")
    @ApiOperation(value = "APP用户查询商家列表")
    public ResultDTO<List<MerchantCore>> getMerChants(@RequestBody UserMerchantJO userMerchant) {
        return merchantService.getMerchantsCoreByUserId(userMerchant.getUserId());
    }

    /**
     * getAllTerminal:(这里用一句话描述这个方法的作用) 查询用户终端信息
     *
     * @param userMerchant
     * @return    设定文件
     * @return ResultDTO<List<MerchantCore>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/getAllTerminal")
    @ApiOperation(value = "APP用户查询设备列表")
    public ResultDTO<List<MerchantTerminal>> getAllTerminal(@RequestBody UserMerchantJO userMerchant) {
        return merchantService.getMerchantTerminalByUserId(userMerchant.getUserId());
    }
}
