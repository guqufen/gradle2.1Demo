package net.fnsco.service.service;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;

public interface AppUserService {
	//用户注册方法接口
	ResultDTO<Object> insertAppUser(AppUser appUser);
}
