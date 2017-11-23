package net.fnsco.api.doc.web.project;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.service.project.PrivilegeService;
import net.fnsco.api.doc.service.project.entity.ProjPrivilegeDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc
 * @author   hjt
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月10日 上午10:22:41
 */
@Controller
@RequestMapping(value = "/web")
@Api(value = "/web", tags = { "项目基本信息" })
public class PrivilegeController extends BaseController{
    
    @Autowired
    private PrivilegeService privilegeService;
   
    @RequestMapping(value = "/addPrivilege", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存项目基本信息", notes = "保存项目基本信息")
    public ResultDTO addPrivilege(ProjPrivilegeDO projPrivilege){
    	projPrivilege.setCreateDate(new Date());
    	privilegeService.add(projPrivilege);
        return ResultDTO.success();
    }
}
