package net.fnsco.service.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.domain.MerchantCore;
import net.fnsco.service.service.MerchantCoreService;

/**
 * @desc 商家基本信息 实现类
 * @author tangliang
 * @date 2017年6月21日 下午2:22:26
 */
@Service
public class MerchantCoreServiceImpl implements MerchantCoreService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MerchantCoreDao merchantCoreDao;
	
	/**
	 * @todo 新增加商家
	 * @author tangliang
	 * @date 2017年6月21日 下午2:23:26
	 * @see net.fnsco.service.service.MerchantCoreService#doAdd(net.fnsco.service.domain.MerchantCore, int)
	 */
	@Override
	public ResultDTO<Integer> doAdd(MerchantCore merchantInfo, int loginUserId) {
		logger.info("开始添加MerchantCoreService.add,merchantInfo=" + merchantInfo.toString());
		ResultDTO<Integer> result = new ResultDTO<>();
		merchantInfo.setLastupdatetime(new Date());
		// 判断用户名/手机号是否重复
		merchantCoreDao.insertSelective(merchantInfo);
		return result;
	}

}
