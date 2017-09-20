package net.fnsco.risk.web.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.sys.WebUserOuterService;
import net.fnsco.risk.service.sys.entity.WebUserDO;
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
		 */
		@RequestMapping(value ="/deleteUserById",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<String> deleteUserById(@RequestParam(value="id") Integer id){
			//获取当前登录的用户
		    WebUserDO adminUser = (WebUserDO) getSessionUser();
		   // Integer userId=adminUser.getId();
		    ResultDTO<String> result=userOuterService.doDelete(adminUser, id);
			return result;
		}
		/**
		 * 添加用户
		 * @param dept
		 * @return
		 */
		@RequestMapping(value ="/toAdd",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<String> toAdd(WebUserOuterDO user){
			userOuterService.doAdd(user);
			return ResultDTO.successForSave(null);
		}
		/**
		 * 通过id查询修改对象的数据
		 * @param id
		 * @return
		 */
		@RequestMapping(value ="/queryUserById",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<WebUserOuterDO> queryUserById(Integer id){
			WebUserOuterDO result = userOuterService.doQueryById(id);
			return success(result);
		}
		/**
		 * 通过用户名查询是否重复
		 * @param name
		 * @return
		 */
		@RequestMapping(value ="/queryUserByName",method= RequestMethod.POST)
		@ResponseBody
		public boolean queryUserByName(String name){
			boolean result = userOuterService.getUserByName(name);
			return result;
		}
		/**
		 * 用户信息修改
		 * @param dept
		 * @return
		 */
		@RequestMapping(value ="/toEdit",method= RequestMethod.POST)
		@ResponseBody
		public ResultDTO<String> toEdit(WebUserOuterDO user){
			//获取当前登录的用户
		    WebUserDO adminUser = (WebUserDO) getSessionUser();
		    Integer userId=adminUser.getId();
		    user.setModifyUserId(userId);
			Integer rows = userOuterService.doUpdate(user);
			return ResultDTO.success();
		}
		/**
		 * 通过id停用
		 * @param dept  
		 * @return
		 */
		@RequestMapping(value ="/toDisuse",method= RequestMethod.POST)
		@ResponseBody
		@Transactional
		public ResultDTO<String> toDisuse(Integer[] id){
			for(int i=0;i<id.length;i++) {
				Integer rows = userOuterService.doDisuse(id[i]);
			}
			return ResultDTO.success();
		}
		/**
		 * 通过id启用
		 * @param dept
		 * @return
		 */
		@RequestMapping(value ="/toStart",method= RequestMethod.POST)
		@ResponseBody
		@Transactional
		public ResultDTO<String> toStart(Integer[] id){
			for(int i=0;i<id.length;i++) {
				Integer rows = userOuterService.doStart(id[i]);
			}
			return ResultDTO.success();
		}
}