package net.fnsco.order.service.ad;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.order.api.sysuser.SysUserService;
import net.fnsco.order.service.ad.dao.AdDAO;
import net.fnsco.order.service.ad.entity.AdDO;
import net.fnsco.order.service.ad.entity.AdDTO;

@Service
public class AdService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdDAO adDAO;
	@Autowired
	private SysUserService sysUserService;

	// 分页
	public ResultPageDTO<AdDO> page(AdDO ad, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询AdService.page, ad=" + ad.toString());
		List<AdDO> pageList = this.adDAO.pageList(ad, pageNum, pageSize);
		for (AdDO adDO : pageList) {
			if(adDO.getCreateUserId()== null){
				continue;
			}
			String name = this.sysUserService.getNameById(adDO.getCreateUserId());
			adDO.setCreateUserName(name);
		}
		Integer count = this.adDAO.pageListCount(ad);
		ResultPageDTO<AdDO> pager = new ResultPageDTO<AdDO>(count, pageList);
		return pager;
	}

	// 添加
	public Integer doAdd(AdDO ad, int loginUserId) {
		logger.info("开始添加AdService.add,ad=" + ad.toString());
		ad.setCreateTime(new Date());
		ad.setUpdateTime(new Date());
		ad.setCreateUserId(loginUserId);
		this.adDAO.insert(ad);
		return ad.getId();
	}

	// 修改
	public Integer doUpdate(AdDO ad, Integer loginUserId) {
		logger.info("开始修改AdService.update,ad=" + ad.toString());
		ad.setUpdateTime(new Date());
		int rows = this.adDAO.update(ad);
		return rows;
	}

	// 删除
	public Integer doDelete(AdDO ad, Integer loginUserId) {
		logger.info("开始删除AdService.delete,ad=" + ad.toString());
		int rows = this.adDAO.deleteById(ad.getId());
		return rows;
	}

	// 查询
	public AdDO doQueryById(Integer id) {
		AdDO obj = this.adDAO.getById(id);
		return obj;
	}

	/**
	 * e789获取广告资讯
	 */
	public ResultDTO<Map<String, List>> queryAdList(Integer type,Integer deviceType) {
		List<AdDTO> adList = Lists.newArrayList();
		List<AdDTO> newsList = Lists.newArrayList();
		Map<String, List<AdDTO>> map = Maps.newHashMap();
		Integer count = 1;
		if(type==2){
			count = 5;
		}else if(type == 1){
			count = 7;
		}
		List<AdDO> allList = this.adDAO.queryAdList(type,deviceType,count);
		if (allList.isEmpty()) {
			return ResultDTO.success(map);
		}
		if(type == 2){
			for (AdDO adDO : allList) {
				if (adDO.getCategory() == null) {
					continue;
				}
				String img_path = adDO.getImg_path();
				if (img_path == null) {
					continue;
				}
				AdDTO adDTO = new AdDTO();
				// 获取图片路径
				String str2 = img_path.substring(img_path.indexOf("^")+1, img_path.length());
				String url = OssLoaclUtil.getFileUrl( OssLoaclUtil.getHeadBucketName(),str2);
				if (!Strings.isNullOrEmpty(url)) {
					adDTO.setImgPath(url);
				}
				adDTO.setSummary(adDO.getSummary());
				adDTO.setTitle(adDO.getTitle());
				adDTO.setUrl(adDO.getUrl());
				adList.add(adDTO);
				map.put("ad", adList);
			}
			return ResultDTO.success(map);
		}
		for (AdDO adDO : allList) {
			if (adDO.getCategory() == null) {
				continue;
			}
			String img_path = adDO.getImg_path();
			if (img_path == null) {
				continue;
			}
			AdDTO adDTO = new AdDTO();
			// 获取图片路径
			String str2 = img_path.substring(img_path.indexOf("^")+1, img_path.length());
			String url = OssLoaclUtil.getFileUrl(OssLoaclUtil.getHeadBucketName(), str2);
			if (!Strings.isNullOrEmpty(url)) {
				adDTO.setImgPath(url);
			}
			if (1 == adDO.getCategory()) {
				adDTO.setSummary(adDO.getSummary());
				adDTO.setTitle(adDO.getTitle());
				adDTO.setUrl(adDO.getUrl());
				adList.add(adDTO);
			} else if (2 == adDO.getCategory()) {
				adDTO.setSummary(adDO.getSummary());
				adDTO.setTitle(adDO.getTitle());
				adDTO.setUrl(adDO.getUrl());
				newsList.add(adDTO);
			}
				map.put("ad", adList);
				map.put("news", newsList);
			
		}
		return ResultDTO.success(map);

	}

	
	public ResultDTO<AdDO> queryById(Integer id) {
		ResultDTO<AdDO> result = new ResultDTO<>();
		AdDO adDO = this.adDAO.getById(id);
		return result.success(adDO);
		
	}
}