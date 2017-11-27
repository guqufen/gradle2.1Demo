package net.fnsco.api.doc.web.project;


import java.beans.Transient;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.service.project.ProjService;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.api.doc.service.user.dao.UserBasicDAO;
import net.fnsco.api.doc.service.vo.UserInfo;
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
@Api(value = "/web", tags = { "新增项目" })
public class ProjController extends BaseController{
    
    @Autowired
    private ProjService projService;
    @Autowired
    private UserBasicDAO userBasicDAO;
   
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增项目", notes = "新增项目")
    public ResultDTO addProject(ProjDO projDO){
    	//获取当前登录的用户
    	UserInfo user = (UserInfo) getSessionUser();
    	Long userId = userBasicDAO.queryUserIdByEmail(user.getEmail());
    	projDO.setUserId(userId);
    	projDO.setCreateDate(new Date());
    	projDO.setModifyDate(new Date());
    	projService.add(projDO);
    	boolean succsss = projService.exportJson(projDO.getName(),projDO.getJsonStr());
    	if(!succsss){
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }
}
