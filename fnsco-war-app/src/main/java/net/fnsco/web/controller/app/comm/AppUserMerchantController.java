package net.fnsco.web.controller.app.comm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserMerchantService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppUserMerchantOutDTO;
import net.fnsco.order.api.dto.BandDto;

@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)
public class AppUserMerchantController extends BaseController {
    @Autowired
    private AppUserMerchantService appUserMerchantService;

    @RequestMapping(value = "/queryBindPeople")
    @ApiOperation(value = "获取店铺绑定情况")
    @ResponseBody
    public ResultDTO queryBindPeople(@RequestBody BandDto bandDto) {
        List<AppUserMerchantOutDTO> result = appUserMerchantService.queryBindPeople(bandDto);
        if(result.size()==0){
            return ResultDTO.fail(ApiConstant.E_NOSHOPKEEPER_ERROR);
        }
        return ResultDTO.success(result);
    }
    
    @RequestMapping(value = "/queryBindRelation")
    @ApiOperation(value = "获取店铺全员绑定情况")
    @ResponseBody
    public ResultDTO queryBindRelation(@RequestBody BandDto bandDto) {
        List<AppUserMerchantOutDTO> result = appUserMerchantService.queryBindRelation(bandDto);
        if(result.size()==0){
            return ResultDTO.fail(ApiConstant.E_NOSHOPKEEPER_ERROR);
        }
        return ResultDTO.success(result);
    }
    
    @RequestMapping(value = "/deletedBindPeople")
    @ApiOperation(value = "删除用户绑定")
    @ResponseBody
    public ResultDTO deletedBindPeople(@RequestBody BandDto bandDto) {
        ResultDTO result = appUserMerchantService.deletedBindPeople(bandDto);
        return result;
    }
}



