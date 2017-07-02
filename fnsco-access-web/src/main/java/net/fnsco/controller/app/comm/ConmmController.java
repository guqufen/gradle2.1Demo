package net.fnsco.controller.app.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.ProtocolDTO;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.merchant.ConmmService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)

public class ConmmController extends BaseController{
    @Autowired
    private ConmmService conmmService;
   
    @RequestMapping(value = "/checkUpdate")
    @ApiOperation(value = "版本更新")
    @ResponseBody
    public ResultDTO<Object> checkUpdate(@RequestBody VersionDTO sysVersionDTO) {
      ResultDTO result = conmmService.checkUpdate(sysVersionDTO);
      return result;
    }
    
    @RequestMapping(value = "/getProtocol")
    @ApiOperation(value = "用户协议")
    @ResponseBody
    public ResultDTO<Object> getProtocol(@RequestBody ProtocolDTO protocolDTO) {
      ResultDTO result = conmmService.getProtocol(protocolDTO);
      return result;
    }
    
}
