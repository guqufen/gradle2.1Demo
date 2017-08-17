package net.fnsco.auth.web.dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.auth.service.UserService;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;


/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web/auth/user")
public class UserManageController extends BaseController {
	@Autowired
	private UserService userService;
	

	/**
	 * 页面信息查询
	 * @param dept
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/query",method= RequestMethod.GET)
	@ResponseBody
	 public ResultDTO<UserDO> query(UserDO user,@RequestParam("currentPageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
		 ResultPageDTO<UserDO> result=userService.queryList(user, pageNum, pageSize);
	     return success(result);
	 }
	/**
	 * 通过id查询删除对象（状态改0）
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/deleteUserById",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deleteUserById(@RequestParam(value="id[]") Integer[] id){
		ResultDTO<String> result = userService.deleteById(id);
		return success(result);
	}
	/**
	 * 添加用户
	 * @param dept
	 * @return
	 */
	@RequestMapping(value ="/toAdd",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> toAdd(UserDO user){
		ResultDTO<String> result = userService.doAddUser(user);
		return result;
	}
	/**
	 * 通过id查询修改对象的数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/queryUserById",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<UserDO> queryUserById(Integer id){
		UserDO result = userService.queryUserById(id);
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
		boolean result = userService.queryUserByName(name);
		return result;
	}
	/**
	 * 用户信息修改
	 * @param dept
	 * @return
	 */
	@RequestMapping(value ="/toEdit",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> toEdit(UserDO user){
		ResultDTO<String> result = userService.toEditDept(user);
		return result;
	}
}














