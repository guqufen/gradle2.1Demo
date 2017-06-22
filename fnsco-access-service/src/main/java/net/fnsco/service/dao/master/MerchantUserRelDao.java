package net.fnsco.service.dao.master;

import net.fnsco.service.domain.MerchantUserRel;
/**
 * @desc 商家跟APP用户关系DAO
 * @author tangliang
 * @date 2017年6月22日 下午4:00:15
 */
public interface MerchantUserRelDao {

    int insert(MerchantUserRel record);

    int insertSelective(MerchantUserRel record);


}