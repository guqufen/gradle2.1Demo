package net.fnsco.service.dao.master;

import net.fnsco.service.domain.MerchantExt;
/**
 * @desc 商家扩展信息 DAO
 * @author tangliang
 * @date 2017年6月21日 下午3:08:42
 */
public interface MerchantExtDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantExt record);

    int insertSelective(MerchantExt record);
}