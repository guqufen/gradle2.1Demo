package net.fnsco.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.cluster.AppUserDao;
import net.fnsco.service.dao.cluster.CityDao;
import net.fnsco.service.domain.AppUser;
import net.fnsco.service.domain.City;
import net.fnsco.service.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private CityDao cityDao; // 从数据源

	@Override
	public ResultDTO<Object> insertAppUser(AppUser appUser) {
		ResultDTO<Object> result = new ResultDTO<Object>();
		// 如果传递参数为null
		if (null == appUser) {
			result.setError("注册参数为null");
			return result;
		}
		// 手机号为空
		if (StringUtils.isEmpty(appUser.getPhonenum())) {
			result.setError("注册手机号码为null");
			return result;
		}
		// 手机号已经注册
		 City city = cityDao.findByName("温岭市");
		 int num =appUserDao.getByPhoneNum1(appUser.getPhonenum());
		 if(num != 0){result.setError("该手机号码已经注册");return result;}
		// 注册成功
		if (appUserDao.insertAppUser(appUser)) {
			return result.success("注册成功");
		}
		;
		return result;
	}

}
