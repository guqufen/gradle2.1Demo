package net.fnsco.bigdata.service.modules.merchant;

import org.springframework.beans.factory.annotation.Autowired;

import net.fnsco.bigdata.api.merchant.MerchantEntityDevService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityDevDao;
import net.fnsco.bigdata.service.domain.MerchantEntityDev;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午10:59:03
 */

public class MerchantEntityDevServiceImple implements MerchantEntityDevService {
	
	@Autowired
	private MerchantEntityDevDao merchantEntityDao;
	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityDevService#deleteByPrimaryKey(java.lang.Integer)
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
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityDevService#insert(net.fnsco.bigdata.service.domain.MerchantEntityDev)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int insert(MerchantEntityDev record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.insert(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityDevService#insertSelective(net.fnsco.bigdata.service.domain.MerchantEntityDev)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int insertSelective(MerchantEntityDev record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.insertSelective(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityDevService#selectByPrimaryKey(java.lang.Integer)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public MerchantEntityDev selectByPrimaryKey(Integer id) {

		// TODO Auto-generated method stub
		return merchantEntityDao.selectByPrimaryKey(id);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityDevService#updateByPrimaryKeySelective(net.fnsco.bigdata.service.domain.MerchantEntityDev)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int updateByPrimaryKeySelective(MerchantEntityDev record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.updateByPrimaryKeySelective(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantEntityDevService#updateByPrimaryKey(net.fnsco.bigdata.service.domain.MerchantEntityDev)
	 * @author tangliang
	 * @date 2017年10月27日 上午10:59:03
	 */
	@Override
	public int updateByPrimaryKey(MerchantEntityDev record) {

		// TODO Auto-generated method stub
		return merchantEntityDao.updateByPrimaryKey(record);

	}

}
