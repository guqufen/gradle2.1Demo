package net.fnsco.auth.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.auth.service.sys.dao.DeptDAO;
import net.fnsco.auth.service.sys.dao.UserDAO;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class UserService extends BaseService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private UserDAO userDAO;
	 // 分页
	 public ResultPageDTO<UserDO> queryList(UserDO user, Integer pageNum, Integer pageSize) {
		 List<UserDO> data = userDAO.pageList(user,pageNum, pageSize);
		 for(UserDO time:data) {
			 Date li=time.getModifyTime();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String dateString = formatter.format(li);
			 time.setModifyTimeStr(dateString);
		 }
	        //返回根据条件查询的所有记录条数
	        int totalNum = userDAO.pageListCount(user);
	        //返回给页面总条数和每页查询的数据
	        ResultPageDTO<UserDO> result = new ResultPageDTO<UserDO>(totalNum, data);
	     return result;
	 }
	 public ResultDTO<String> deleteById(Integer[] id) {
		 for(int i=0;i<id.length;i++){			 
			int res = userDAO.deleteById(id[i]);
		 if (res != 1) {
             return ResultDTO.fail();
         }
	    } 
		 return new ResultDTO<>(true, id, CoreConstants.WEB_SAVE_OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));
		 }
}
