package net.fnsco.trading.service.merchantentity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.merchantentity.dao.AppUserMerchantEntityDAO;
import net.fnsco.trading.service.merchantentity.entity.AppUserMerchantEntityDO;

@Service
public class AppUserMerchantEntityService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppUserMerchantEntityDAO appUserMerchantEntityDAO;
	
	@Autowired
	private MerchantEntityDao merchantEntityDao;

	// 分页
	public ResultPageDTO<AppUserMerchantEntityDO> page(AppUserMerchantEntityDO appUserMerchantEntity, Integer pageNum,
			Integer pageSize) {
		logger.info(
				"开始分页查询AppUserMerchantEntityService.page, appUserMerchantEntity=" + appUserMerchantEntity.toString());
		List<AppUserMerchantEntityDO> pageList = this.appUserMerchantEntityDAO.pageList(appUserMerchantEntity, pageNum,
				pageSize);
		Integer count = this.appUserMerchantEntityDAO.pageListCount(appUserMerchantEntity);
		ResultPageDTO<AppUserMerchantEntityDO> pager = new ResultPageDTO<AppUserMerchantEntityDO>(count, pageList);
		return pager;
	}

	// 添加
	public AppUserMerchantEntityDO doAdd(AppUserMerchantEntityDO appUserMerchantEntity, int loginUserId) {
		logger.info("开始添加AppUserMerchantEntityService.add,appUserMerchantEntity=" + appUserMerchantEntity.toString());
		this.appUserMerchantEntityDAO.insert(appUserMerchantEntity);
		return appUserMerchantEntity;
	}

	// 修改
	public Integer doUpdate(AppUserMerchantEntityDO appUserMerchantEntity, Integer loginUserId) {
		logger.info(
				"开始修改AppUserMerchantEntityService.update,appUserMerchantEntity=" + appUserMerchantEntity.toString());
		int rows = this.appUserMerchantEntityDAO.update(appUserMerchantEntity);
		return rows;
	}

	// 删除
	public Integer doDelete(AppUserMerchantEntityDO appUserMerchantEntity, Integer loginUserId) {
		logger.info(
				"开始删除AppUserMerchantEntityService.delete,appUserMerchantEntity=" + appUserMerchantEntity.toString());
		int rows = this.appUserMerchantEntityDAO.deleteById(appUserMerchantEntity.getId());
		return rows;
	}

	// 查询
	public AppUserMerchantEntityDO doQueryById(Integer id) {
		AppUserMerchantEntityDO obj = this.appUserMerchantEntityDAO.getById(id);
		return obj;
	}

	public MerchantEntity queryMerInfoByUserId(Integer appUserId) {
		return merchantEntityDao.selectByAppUserId(appUserId);
	}

}