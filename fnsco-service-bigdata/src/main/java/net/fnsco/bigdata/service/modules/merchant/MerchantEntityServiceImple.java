package net.fnsco.bigdata.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.api.dto.ChannelMerchantDTO;
import net.fnsco.bigdata.api.dto.IndustryDTO;
import net.fnsco.bigdata.api.merchant.MerchantEntityService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.CodeUtil;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午10:59:03
 */
@Service
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
	
	/**
	 * 分页查询
	 */
	@Override
	public ResultPageDTO<MerchantEntity> queryPageList(MerchantEntity record, Integer currentPageNum,
			Integer pageSize) {
		
		 PageDTO<MerchantEntity> pages = new PageDTO<MerchantEntity>(currentPageNum, pageSize, record);
		 List<MerchantEntity> datas = merchantEntityDao.queryPageList(pages);
		 Integer total = merchantEntityDao.queryTotalByCondition(record);
		 ResultPageDTO<MerchantEntity> result = new ResultPageDTO<MerchantEntity>(total, datas);
	     result.setCurrentPage(currentPageNum);
	     
		return result;
	}

	@Override
	public int addMerEntity(MerchantEntity record) {
		
		return 0;
	}
	
	/**
	 * 生成唯一最新的innerCode
	 */
	@Override
	public String getEntityInnerCode() {
		String entityInnerCode = null;
		MerchantEntity record = new MerchantEntity();

        while (true) {
        	entityInnerCode = "E"+CodeUtil.generateMerchantCode("F");
            record.setEntityInnerCode(entityInnerCode);
            Integer total = merchantEntityDao.queryTotalByCondition(record);
            if (total == 0) {
                break;
            }
        }
        return entityInnerCode;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public ResultPageDTO<ChannelMerchantDTO> queryChannelMerPageList(String entityInnerCode, Integer currentPageNum,
			Integer pageSize) {
		
		PageDTO<String> pages = new PageDTO<String>(currentPageNum, pageSize, entityInnerCode);
		List<ChannelMerchantDTO> datas = merchantEntityDao.queryPageChannelMerByEntityInnerCode(pages);
		 Integer total = merchantEntityDao.queryCountChannelMerByEntityInnerCode(entityInnerCode);
		ResultPageDTO<ChannelMerchantDTO> result = new ResultPageDTO<ChannelMerchantDTO>(total, datas);
		return result;
	}

	/**
	 * 
	 */
	@Override
	public Integer getEtpsTypeByEtpsAttra(int etps_attr) {
		switch (etps_attr) {
		case 1:
			return 1;
		case 2:
			return 1;
		case 3:
			return 1;
		case 4:
			return 1;
		case 5:
			return 2;
		case 7:
			return 1;
		}
		return 1;
	}

	public ResultPageDTO<IndustryDTO> pageNameList(IndustryDTO industryDO, Integer page, Integer rows) {
		 PageDTO<IndustryDTO> pages = new PageDTO<IndustryDTO>(page, rows, industryDO);
		List<IndustryDTO> list = merchantEntityDao.pageNameList(pages);
		Integer total = merchantEntityDao.pageNameListCount(industryDO);
		ResultPageDTO<IndustryDTO> pager = new ResultPageDTO<>(total, list);
		return pager;
	}

}
