package net.fnsco.risk.web.admin.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import net.fnsco.risk.service.report.MercAllocationService;
import net.fnsco.risk.service.sys.WebUserOuterService;
import net.fnsco.risk.service.sys.entity.AgentDO;
import net.fnsco.risk.service.sys.entity.WebUserDO;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;

@Controller
@RequestMapping(value = "/web/addUser", method = RequestMethod.POST)
@Api(value = "/web/addUser", tags = { "" })
public class AddUserController extends BaseController {

	 @Autowired
	 private WebUserOuterService userOuterService;

	 @Autowired
	 private MercAllocationService merAllocationService;
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
		/**
		 * 查询所有类型
		 * @return
		 */
		@RequestMapping(value="/queryType",method=RequestMethod.POST)
		@ResponseBody
		@Transactional
		public ResultDTO<List<AgentDO>> queryType(){
			ResultDTO<List<AgentDO>> list = userOuterService.queryType();
			return list;
		}
		
		/**
		 * 商户分配页面信息查询
		 * @param webUserDO:外部用户信息对象
		 * @param merName:商户名称。
		 * @param pageNum
		 * @param pageSize
		 * @return
		 * 通过商户名称模糊查询步骤：
		 * 1、商户名称模糊查询出商户信息mcht_core，取出内部商户号inner_code<list集合>
		 * 2、根据inner_code去外部绑定表查找该内部商户绑定的agent_id<list集合>
		 * 3、通过agent_id去外部用户信息表查找用户信息<list集合>
		 * 
		 */
		@RequestMapping(value = "/queryOuterUser",method= RequestMethod.GET)
		@ResponseBody
		 public ResultDTO<WebUserOuterDO> queryAllUser(WebUserOuterDO webUserDO,String merName, @RequestParam("currentPageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
			
			List<Integer> agentList = null;
			//如果商户名称不为空，则先查找找商户inner_code，再查找绑定的agent_id
			if(StringUtils.isNotBlank(merName)){
				
				//通过商户名称模糊查询内部商户号，未找到则返回失败
				List<String> merList = merAllocationService.getByMerName(merName);
				if(merList.size() == 0){
					return ResultDTO.fail("该商户不存在！");
				}
				
				//通过商户号查找agent_id,未找到则返回失败
				agentList = merAllocationService.getByInnerCodeList(merList);
				if(agentList.size() == 0){
					return ResultDTO.fail("商户对应的代理商未找到！");
				}
			}
			
			ResultPageDTO<WebUserOuterDO> result=userOuterService.pageMercAllo(webUserDO, agentList, pageNum, pageSize);
			
			
		     return success(result);
		 }
}