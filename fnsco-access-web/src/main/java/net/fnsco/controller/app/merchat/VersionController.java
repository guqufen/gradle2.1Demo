package net.fnsco.controller.app.merchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.merchant.VersionService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)

public class VersionController extends BaseController{
    @Autowired
    private VersionService sysVersionService;
   
    @RequestMapping(value = "/checkUpdate")
    @ApiOperation(value = "版本更新")
    @ResponseBody
    public ResultDTO<Object> checkUpdate(@RequestBody VersionDTO sysVersionDTO) {
      ResultDTO result = sysVersionService.checkUpdate(sysVersionDTO);
      return result;
    }
}
