package net.fnsco.bigdata.service.modules.merchant;

import org.springframework.beans.factory.annotation.Autowired;

import net.fnsco.bigdata.api.merchant.MerchantEntityService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午10:59:03
 */

public class MerchantEntityServiceImple implements MerchantEntityService {
	
	@Autowired
	private MerchantEntityDao merchantEntityDao;
	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityService#deleteByPrimaryKey(java.lang.Integer)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {

		// TODO Auto-generated method stub
		return merchantEntityDao.deleteByPrimaryKey(id);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityService#insert(net.fnsco.bigdata.service.domain.MerchantEntity)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int insert(MerchantEntity record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.insert(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityService#insertSelective(net.fnsco.bigdata.service.domain.MerchantEntity)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int insertSelective(MerchantEntity record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.insertSelective(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityService#selectByPrimaryKey(java.lang.Integer)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public MerchantEntity selectByPrimaryKey(Integer id) {

		// TODO Auto-generated method stub
		return merchantEntityDao.selectByPrimaryKey(id);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityService#updateByPrimaryKeySelective(net.fnsco.bigdata.service.domain.MerchantEntity)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int updateByPrimaryKeySelective(MerchantEntity record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.updateByPrimaryKeySelective(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityService#updateByPrimaryKey(net.fnsco.bigdata.service.domain.MerchantEntity)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int updateByPrimaryKey(MerchantEntity record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.updateByPrimaryKey(record);

	}

}
