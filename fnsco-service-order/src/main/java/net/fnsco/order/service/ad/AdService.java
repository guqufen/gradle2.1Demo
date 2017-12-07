package net.fnsco.order.service.ad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.service.ad.dao.AdDAO;
import net.fnsco.order.service.ad.entity.AdDO;

@Service
public class AdService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AdDAO adDAO;

 // 分页
 public ResultPageDTO<AdDO> page(AdDO ad, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AdService.page, ad=" + ad.toString());
     List<AdDO> pageList = this.adDAO.pageList(ad, pageNum, pageSize);
     Integer count = this.adDAO.pageListCount(ad);
   ResultPageDTO<AdDO> pager =  new ResultPageDTO<AdDO>(count,pageList);
     return pager;
 }

 // 添加
 public AdDO doAdd (AdDO ad,int loginUserId) {
     logger.info("开始添加AdService.add,ad=" + ad.toString());
     this.adDAO.insert(ad);
     return ad;
 }

 // 修改
 public Integer doUpdate (AdDO ad,Integer loginUserId) {
     logger.info("开始修改AdService.update,ad=" + ad.toString());
     int rows=this.adDAO.update(ad);
     return rows;
 }

 // 删除
 public Integer doDelete (AdDO ad,Integer loginUserId) {
     logger.info("开始删除AdService.delete,ad=" + ad.toString());
     int rows=this.adDAO.deleteById(ad.getId());
     return rows;
 }

 // 查询
 public AdDO doQueryById (Integer id) {
     AdDO obj = this.adDAO.getById(id);
     return obj;
 }


 
 /**
  * e789获取广告资讯
  */
 public ResultDTO<Map<String, List>> queryAdList() {
	 	List<AdDO> adList = null;
		List<AdDO> newsList = null;
		Map<String, List> map = new HashMap<>();
		List<AdDO> allList = this.adDAO.queryAdList();
		if(allList.isEmpty()){
			return ResultDTO.failForMessage("未发现相关信息");
		}
		for (AdDO adDO : allList) {
			if(StringUtils.equals("1", adDO.getCategory().toString())){
				adList.add(adDO);
			}else{
				newsList.add(adDO);
			}
			map.put("ad", adList);
			map.put("news", newsList);
		}
		
		return ResultDTO.success(map);
	}
}