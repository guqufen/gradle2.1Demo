package net.fnsco.web.controller.merentity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantEntityService;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 商户实体控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午10:55:36
 */
@Controller
@RequestMapping(value = "/web/merchantentity")
public class MerchantEntityController extends BaseController {
	
	@Autowired
	private MerchantEntityService merchantEntityService;
	
	@Autowired
	private MerchantCoreService merchantCoreService;
	/**
	 * merchatInfoIndex:(分页查询功能)
	 *
	 * @param  @param merchantCore
	 * @param  @param currentPageNum
	 * @param  @param pageSize
	 * @param  @return    设定文件
	 * @return ResultPageDTO<MerchantCore>    DOM对象
	 * @author tangliang
	 * @date   2017年10月27日 上午10:56:47
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "m:merentity:list" })
	public ResultPageDTO<MerchantEntity> merchatEntityIndex(MerchantEntity merchantEntity, Integer currentPageNum,
			Integer pageSize) {
		if(2 == merchantEntity.getStatus()) {
			merchantEntity.setStatus(null);
		}
		logger.info("查询商户实体列表");
		return merchantEntityService.queryPageList(merchantEntity, currentPageNum, pageSize);
	}
	
	/**
	 * 新增加
	 * @param merchantEntity
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merentity:save" })
	public ResultDTO<String> addMerEntity(MerchantEntity merchantEntity){
		merchantEntity.setCreateSource("0");
		merchantEntity.setCreateTimer(new Date());
		merchantEntity.setLastModefyTimer(new Date());
		merchantEntity.setLastModefyUserId(getUserId());
		merchantEntity.setCardType("0");
		merchantEntity.setStatus(1);
		merchantEntity.setCreateUserId(getUserId());
		String entityInnerCode = merchantCoreService.getInnerCode();
		merchantEntity.setEntityInnerCode(entityInnerCode);
		merchantEntity.setScores(new BigDecimal(0));
		int res = merchantEntityService.insertSelective(merchantEntity);
		
		if(res>0) {
			return ResultDTO.success();
		}
		
		return ResultDTO.failForSave();
		
	}
}
