package net.fnsco.trading.service.merchant;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.merchant.dao.AppUserMerchantDAO;
import net.fnsco.trading.service.merchant.entity.AppUserMerchantDO;

@Service
public class AppUserMerchantService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AppUserMerchantDAO appUserMerchantDAO;

 // 分页
 public ResultPageDTO<AppUserMerchantDO> page(AppUserMerchantDO appUserMerchant, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppUserMerchantService.page, appUserMerchant=" + appUserMerchant.toString());
     List<AppUserMerchantDO> pageList = this.appUserMerchantDAO.pageList(appUserMerchant, pageNum, pageSize);
     Integer count = this.appUserMerchantDAO.pageListCount(appUserMerchant);
   ResultPageDTO<AppUserMerchantDO> pager =  new ResultPageDTO<AppUserMerchantDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppUserMerchantDO doAdd (AppUserMerchantDO appUserMerchant,int loginUserId) {
     logger.info("开始添加AppUserMerchantService.add,appUserMerchant=" + appUserMerchant.toString());
     this.appUserMerchantDAO.insert(appUserMerchant);
     return appUserMerchant;
 }

 // 修改
 public Integer doUpdate (AppUserMerchantDO appUserMerchant,Integer loginUserId) {
     logger.info("开始修改AppUserMerchantService.update,appUserMerchant=" + appUserMerchant.toString());
     int rows=this.appUserMerchantDAO.update(appUserMerchant);
     return rows;
 }

 // 删除
 public Integer doDelete (AppUserMerchantDO appUserMerchant,Integer loginUserId) {
     logger.info("开始删除AppUserMerchantService.delete,appUserMerchant=" + appUserMerchant.toString());
     int rows=this.appUserMerchantDAO.deleteById(appUserMerchant.getId());
     return rows;
 }

 // 查询
 public AppUserMerchantDO doQueryById (Integer id) {
     AppUserMerchantDO obj = this.appUserMerchantDAO.getById(id);
     return obj;
 }

public String getInnerCodeByUserId(String entityInnerCode) {
	String innerCode = this.appUserMerchantDAO.getInnerCodeByUserId(entityInnerCode);
	return innerCode;
}
}