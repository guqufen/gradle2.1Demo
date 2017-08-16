package net.fnsco.api.doc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.service.project.ProjectService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月10日 上午10:22:41
 */
@Controller
@RequestMapping(value = "/web")
@Api(value = "/web", tags = { "项目管理" })
public class ProjectController extends BaseController{
    
    @Autowired
    private ProjectService projectService;
    /**
     * exportJson:(这里用一句话描述这个方法的作用)导入JSON数据项目
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月10日 上午10:48:31
     * @return ResultDTO    DOM对象
     */
    @RequestMapping(value = "/exportJson", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "导入JSON数据", notes = "导入JSON数据")
    public ResultDTO exportJson(@RequestParam String jsonParams){
        boolean succsss = projectService.exportJson(jsonParams);
        if(!succsss){
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }
    
}
