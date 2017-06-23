package net.fnsco.service.modules.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.domain.AppUser;

@Service
public class AppUserServiceImpl  extends BaseService implements AppUserService{
	@Autowired
	private AppUserDao MappUserDao;
	//注册
	@Override
	public ResultDTO<AppUser> insertAppUser(AppUser appUser) {
		ResultDTO<AppUser> result=new ResultDTO<AppUser>();
		int num=MappUserDao.selectByPrimaryKey(appUser.getMobile());
		if(num!=0){
		      return result.setError("1","该用户已经注册");
		}
		if(MappUserDao.insertSelective(appUser)){
			result.setData(appUser);
			return result.setSuccess("注册成功");
		}
		return result;
	}

	//修改密码
	@Override
	public ResultDTO<AppUser> modify(HttpServletResponse res,HttpServletRequest req){
		//旧密码
		String oldPassword=req.getParameter("oldPassword");
		//新密码
		String password=req.getParameter("password");
		String mobile=req.getParameter("mobile");
		ResultDTO<AppUser> result=new ResultDTO<AppUser>();
		AppUser mAppUser=new AppUser();
		//根据手机号查询用户是否存在获取原密码
		mAppUser=MappUserDao.selectByMobile(mobile);
		//查到的密码和原密码做比较
		if(!oldPassword.equals(mAppUser.getPassword())){
			return result.setError("原密码输入错误,请重新输入");
		}
		mAppUser.setPassword(password);
		mAppUser.setMobile(mobile);
		if(MappUserDao.updateByPrimaryKeySelective(mAppUser)){
			result.setSuccess("修改密码成功");
		}
		return result;
	}


}









