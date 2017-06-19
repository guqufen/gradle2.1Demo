/**
 * 
 */
package net.fnsco.service.service;

import net.fnsco.service.domain.TUser;

/**@desc 
 * @author tangliang
 * @date 2017年6月19日 上午11:59:44
 */
public interface TUserService {
	
	void save(TUser user);
	
	TUser findById(Integer id);
}
