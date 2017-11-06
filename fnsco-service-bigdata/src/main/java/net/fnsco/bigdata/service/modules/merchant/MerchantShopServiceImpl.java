package net.fnsco.bigdata.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.api.dto.MerShopDetailDTO;
import net.fnsco.bigdata.api.merchant.MerchantShopService;
import net.fnsco.bigdata.service.dao.master.MerchantShopDao;
import net.fnsco.bigdata.service.domain.MerchantShop;
import net.fnsco.core.utils.CodeUtil;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月31日 下午4:27:49
 */
@Service
public class MerchantShopServiceImpl implements MerchantShopService {
	
	@Autowired
	private MerchantShopDao merchantShopDao;
	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#deleteByPrimaryKey(java.lang.Integer)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {

		// TODO Auto-generated method stub
		return merchantShopDao.deleteByPrimaryKey(id);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#insert(net.fnsco.bigdata.service.domain.MerchantShop)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public int insert(MerchantShop record) {

		// TODO Auto-generated method stub
		return merchantShopDao.insert(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#insertSelective(net.fnsco.bigdata.service.domain.MerchantShop)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public int insertSelective(MerchantShop record) {

		// TODO Auto-generated method stub
		return merchantShopDao.insertSelective(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#selectByPrimaryKey(java.lang.Integer)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public MerchantShop selectByPrimaryKey(Integer id) {

		// TODO Auto-generated method stub
		return merchantShopDao.selectByPrimaryKey(id);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#updateByPrimaryKeySelective(net.fnsco.bigdata.service.domain.MerchantShop)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public int updateByPrimaryKeySelective(MerchantShop record) {

		// TODO Auto-generated method stub
		return merchantShopDao.updateByPrimaryKeySelective(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#updateByPrimaryKey(net.fnsco.bigdata.service.domain.MerchantShop)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public int updateByPrimaryKey(MerchantShop record) {

		// TODO Auto-generated method stub
		return merchantShopDao.updateByPrimaryKey(record);

	}

	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#selectByEntityInnerCode(java.lang.String)
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public List<MerShopDetailDTO> selectByEntityInnerCode(String entityInnerCode) {

		// TODO Auto-generated method stub
		return merchantShopDao.selectByEntityInnerCode(entityInnerCode);

	}

	/** 获取唯一的店铺商户号
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#getMerShopInnerCode()
	 * @author tangliang
	 * @date 2017年10月31日 下午4:27:49
	 */
	@Override
	public String getMerShopInnerCode() {

		String shopInnerCode = null;
		MerchantShop record = new MerchantShop();

        while (true) {
        	shopInnerCode = "S"+CodeUtil.generateMerchantCode("F");
            record.setEntityInnerCode(shopInnerCode);
            Integer total = merchantShopDao.queryTotalByCondition(record);
            if (total == 0) {
                break;
            }
        }
        return shopInnerCode;

	}
	
	/**
	 * 按照条件更新
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#updateByShopInnerCodeSelective(net.fnsco.bigdata.service.domain.MerchantShop)
	 * @author tangliang
	 * @date 2017年11月1日 上午10:15:00
	 */
	@Override
	public int updateByShopInnerCodeSelective(MerchantShop record) {
		
		// TODO Auto-generated method stub
		return merchantShopDao.updateByShopInnerCodeSelective(record);
		
	}
	
	/**
	 * (non-Javadoc)按照shopInnerCode删除
	 * @see net.fnsco.bigdata.api.merchant.MerchantShopService#deleteByShopInnerCode(java.lang.String)
	 * @author tangliang
	 * @date 2017年11月1日 上午11:43:20
	 */
	@Override
	public int deleteByShopInnerCode(String shopInnerCode) {
		
		// TODO Auto-generated method stub
		return merchantShopDao.deleteByShopInnerCode(shopInnerCode);
		
	}

}
