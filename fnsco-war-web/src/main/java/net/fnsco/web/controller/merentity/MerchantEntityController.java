package net.fnsco.web.controller.merentity;


import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.Strings;

import net.fnsco.bigdata.api.dto.ChannelMerchantDTO;
import net.fnsco.bigdata.api.merchant.MerchantEntityService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityCoreRefDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
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
	private MerchantEntityCoreRefDao merchantEntityCoreRefDao;
	

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
		int res = 0;
		merchantEntity.setLastModefyTimer(new Date());
		merchantEntity.setLastModefyUserId(getUserId());
		//根据商户性质获取商户种类
		if(merchantEntity.getEtps_attr() != null){
			int etps_tp = merchantEntityService.getEtps_TypeByEtps_attra(merchantEntity.getEtps_attr());
			merchantEntity.setEtps_tp(etps_tp);
		}
		//拼接详细信息
		
		if(null == merchantEntity.getId()) {
			merchantEntity.setCreateSource("0");
			merchantEntity.setCreateTimer(new Date());
			merchantEntity.setCardType("0");
			merchantEntity.setStatus(1);
			merchantEntity.setCreateUserId(getUserId());
			String entityInnerCode = merchantEntityService.getEntityInnerCode();
			merchantEntity.setEntityInnerCode(entityInnerCode);
			merchantEntity.setScores(new BigDecimal(0));
			res = merchantEntityService.insertSelective(merchantEntity);
		}else {
			res = merchantEntityService.updateByPrimaryKeySelective(merchantEntity);
		}
		
		if(res>0) {
			return ResultDTO.successForSubmit();
		}
		return ResultDTO.failForSave();
		
	}
	
	/**
	 * 按照id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merentity:delete" })
	public ResultDTO<String> deleteById(Integer id){
		if(id== null ) {
			return ResultDTO.failForMessage("ID为空!无法删除");
		}
		//先判断是否绑定商户，如果存在绑定，则不能删除。否则可以删除
		MerchantEntityCoreRef record = new MerchantEntityCoreRef();
		MerchantEntity entity = merchantEntityService.selectByPrimaryKey(id);
		record.setEntityInnerCode(entity.getEntityInnerCode());
		int total = merchantEntityCoreRefDao.countByCondition(record);
		if(total > 0) {
			return ResultDTO.failForMessage("该商户实体存在绑定关系，不能删除!");
		}
		
		MerchantEntity recordEntity = new MerchantEntity();
		recordEntity.setId(id);
		recordEntity.setStatus(0);
		int res = merchantEntityService.updateByPrimaryKeySelective(recordEntity);
		if(res > 0) {
			return ResultDTO.success();
		}
		return ResultDTO.failForUpdate();
	}
	/**
	 * 查询单个消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/querySingle", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merentity:list" })
	public ResultDTO<MerchantEntity> querySingle(Integer id ){
		if(id== null ) {
			return ResultDTO.failForMessage("ID为空!无法删除");
		}
		MerchantEntity entity = merchantEntityService.selectByPrimaryKey(id);
		return ResultDTO.success(entity);
	}
	
	/**
	 * @param merchantEntity
	 * @param currentPageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryChannelMer", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "m:merentity:list" })
	public ResultPageDTO<ChannelMerchantDTO> queryChannelMer(String entityInnerCode, Integer currentPageNum,
			Integer pageSize) {
		if(Strings.isStringEmpty(entityInnerCode)) {
			return null;
		}
		logger.info("查询渠道商户实体列表");
		return merchantEntityService.queryChannelMerPageList(entityInnerCode, currentPageNum, pageSize);
	}
}
