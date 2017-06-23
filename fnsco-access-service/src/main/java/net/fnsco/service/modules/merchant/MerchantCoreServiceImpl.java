package net.fnsco.service.modules.merchant;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.merchant.MerchantCoreService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.domain.MerchantCore;

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
	 * @see net.fnsco.api.merchant.MerchantCoreService#doAdd(net.fnsco.service.domain.MerchantCore, int)
	 */
	@Override
	public ResultDTO<Integer> doAdd(MerchantCore merchantInfo, int loginUserId) {
		logger.info("开始添加MerchantCoreService.add,merchantInfo=" + merchantInfo.toString());
		ResultDTO<Integer> result = new ResultDTO<>();
//		merchantInfo.setLastupdatetime(new Date());
		// 判断用户名/手机号是否重复
		merchantCoreDao.insertSelective(merchantInfo);
		return result;
	}
	/**
	 * @todo 条件分页查询
	 * @author tangliang
	 * @date 2017年6月22日 上午11:50:55
	 * @see net.fnsco.api.merchant.MerchantCoreService#queryMerchantCore(net.fnsco.service.domain.MerchantCore)
	 */
	@Override
	public ResultPageDTO<MerchantCore> queryMerchantCore(MerchantCore merchantCore,int currentPageNum,int perPageSize) {
		
		PageDTO<MerchantCore> pages = new PageDTO<>(currentPageNum,perPageSize,merchantCore);
		List<MerchantCore> datas = merchantCoreDao.queryPageList(pages);
		int totalNum = merchantCoreDao.queryTotalByCondition(merchantCore);
		ResultPageDTO<MerchantCore> result = new ResultPageDTO<MerchantCore>(totalNum,datas);
		result.setCurrentPage(currentPageNum);
		
		return result;
	}
	/**
	 * @todo 条件查询
	 * @author tangliang
	 * @date 2017年6月23日 上午10:39:47
	 * @see net.fnsco.api.merchant.MerchantCoreService#queryAllByCondition(net.fnsco.service.domain.MerchantCore)
	 */
	@Override
	public List<MerchantCore> queryAllByCondition(MerchantCore merchantCore) {
		// TODO Auto-generated method stub
		return merchantCoreDao.queryListByCondition(merchantCore);
	}
	
	
	
}
