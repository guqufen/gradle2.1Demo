package net.fnsco.auth.web.dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.auth.service.UserService;
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
	 * 通过id查询删除对象（状态改-1）
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/deleteUserById",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deleteUserById(@RequestParam(value="id[]") Integer[] id){
		ResultDTO<String> result = userService.deleteById(id);
		return success(result);
	}
}














