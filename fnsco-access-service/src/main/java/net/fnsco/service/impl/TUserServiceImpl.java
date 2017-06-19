/**
 * 
 */
package net.fnsco.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.fnsco.dao.master.TUserDao;
import net.fnsco.domain.TUser;
import net.fnsco.service.TUserService;

/**@desc 
 * @author tangliang
 * @date 2017年6月19日 下午12:00:17
 */
@Service
public class TUserServiceImpl implements TUserService {
	
	@Autowired
	private TUserDao tuserDao;
	
	
	@Override
	public void save(TUser user) {
		// TODO Auto-generated method stub
		
		if(StringUtils.isEmpty(user.getId()))user.setId(new Random().nextInt(100));
		tuserDao.save(user);
	}

	/* (non-Javadoc)
	 * @see net.fnsco.service.TUserService#findById(java.lang.Integer)
	 */
	@Override
	public TUser findById(Integer id) {
		if(null == id)
		return null;
		
		return tuserDao.findById(id);
	}

}
