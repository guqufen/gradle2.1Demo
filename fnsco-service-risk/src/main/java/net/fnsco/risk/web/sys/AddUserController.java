package net.fnsco.risk.web.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.sys.WebUserOuterService;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;

@Controller
@RequestMapping(value = "/web/addUser", method = RequestMethod.POST)
@Api(value = "/web/addUser", tags = { "" })
public class AddUserController extends BaseController {

	 @Autowired
	    private WebUserOuterService userOuterService;
	 /**
		 * 页面信息查询
		 * @param dept
		 * @param pageNum
		 * @param pageSize
		 * @return
		 */
		@RequestMapping(value = "/query",method= RequestMethod.GET)
		@ResponseBody
		 public ResultDTO<WebUserOuterDO> query(WebUserOuterDO webUserDO,@RequestParam("currentPageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
			 ResultPageDTO<WebUserOuterDO> result=userOuterService.page(webUserDO, pageNum, pageSize);
		     return success(result);
		 }
		/**
		 * 通过id查询删除对象（状态改0）
		 * @param id
		 * @return
		 *//*
		@RequestMapping(value ="/deleteUserById",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<String> deleteUserById(@RequestParam(value="id[]") Integer[] id){
			//获取当前登录的用户
		    WebUserDTO adminUser = (WebUserDTO) getSessionUser();
		    Integer userId=adminUser.getId();
		    for (int i = 0; i < id.length; i++) {
		    	if(id[i]==AuthConstant.SUPER_ADMIN) {
		    		return ResultDTO.fail(AuthConstant.E_NOT_DELEET_ADMIN);
		    	}
		    	if(userId==id[i]) {
		    		return ResultDTO.fail(AuthConstant.E_NOT_DELEET_ONESELF);
		    	}
			}
			ResultDTO<String> result = userService.deleteById(id);
			return success(result);
		}
		*//**
		 * 添加用户
		 * @param dept
		 * @return
		 *//*
		@RequestMapping(value ="/toAdd",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<String> toAdd(UserDO user){
			userService.doAddUser(user);
			return ResultDTO.successForSave(null);
		}
		*//**
		 * 通过id查询修改对象的数据
		 * @param id
		 * @return
		 *//*
		@RequestMapping(value ="/queryUserById",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<UserDO> queryUserById(Integer id){
			UserDO result = userService.queryUserById(id);
			return success(result);
		}
		*//**
		 * 通过用户名查询是否重复
		 * @param name
		 * @return
		 *//*
		@RequestMapping(value ="/queryUserByName",method= RequestMethod.POST)
		@ResponseBody
		public boolean queryUserByName(String name){
			boolean result = userService.queryUserByName(name);
			return result;
		}
		*//**
		 * 用户信息修改
		 * @param dept
		 * @return
		 *//*
		@RequestMapping(value ="/toEdit",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<String> toEdit(UserDO user){
			//获取当前登录的用户
		    WebUserDTO adminUser = (WebUserDTO) getSessionUser();
		    Integer userId=adminUser.getId();
		    user.setModifyUserId(userId);
		    Integer id=user.getId();
		    Integer status=user.getStatus();
		    if(status==2) {
		    	if(id==AuthConstant.SUPER_ADMIN) {
		    		return ResultDTO.fail(AuthConstant.E_NOT_DELEET_ADMIN);
		    	}
		    	if(userId==id) {
		    		return ResultDTO.fail(AuthConstant.E_NOT_DELEET_ONESELF);
		    	}
		    }
			ResultDTO<String> result = userService.toEditDept(user);
			return result;
		}*/
}