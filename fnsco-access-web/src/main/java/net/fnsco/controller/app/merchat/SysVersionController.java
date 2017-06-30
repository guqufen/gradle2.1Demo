package net.fnsco.controller.app.merchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.AppUserDTO;
import net.fnsco.api.dto.SysVersionDTO;
import net.fnsco.api.merchant.AppUserService;
import net.fnsco.api.merchant.SysVersionService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)

public class SysVersionController extends BaseController{
    @Autowired
    private SysVersionService sysVersionService;

    @RequestMapping(value = "/update")
    @ApiOperation(value = "版本更新")
    @ResponseBody
    public ResultDTO<Object> register(@RequestBody SysVersionDTO sysVersionDTO) {
        ResultDTO result = sysVersionService.insertSelective(sysVersionDTO);
        return result;
    }
}
