/**
 * 
 */
package net.fnsco.dao.master;

import net.fnsco.domain.TUser;

/**@desc 
 * @author tangliang
 * @date 2017年6月19日 上午11:51:32
 */
public interface TUserDao {
	
	void save(TUser user);
	
	TUser findById(Integer id);
}
