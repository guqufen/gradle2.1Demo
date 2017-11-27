package net.fnsco.api.doc.web.project;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.service.project.ProjectMenuistService;
import net.fnsco.api.doc.service.project.entity.ProjDO;
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
@Api(value = "/web", tags = { "查询项目菜单" })
public class ProjectMenuistController extends BaseController{
    
    @Autowired
    private ProjectMenuistService projectMenuistService;
   
    @RequestMapping(value = "/queryProjectMenuist", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询项目菜单", notes = "查询项目菜单")
    public ResultDTO<List<ProjDO>> queryProjectMenuist(){
    	List<ProjDO> list = projectMenuistService.queryMenuist();
        return ResultDTO.success(list);
    }
}
