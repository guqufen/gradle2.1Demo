package net.fnsco.web.controller.app.merchat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.bigdata.api.dto.PermissionsDTO;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.config.SysConfigService;
import net.fnsco.order.api.constant.ConstantEnum.IntegralTypeEnum;
import net.fnsco.order.api.dto.IntegralRuleDTO;
import net.fnsco.order.api.merchant.IntegralRuleService;
import net.fnsco.order.service.domain.IntegralRule;
import net.fnsco.order.service.domain.SysConfig;

@RestController
@RequestMapping(value="/app/integral/merchant", method=RequestMethod.POST)
@Api(value = "/app/integral/merchant", tags = { "商户积分等级" })
public class AppMerchentLevelController extends BaseController{

	@Autowired
	private SysConfigService sysConfigService;//主要用于查询，获取经验和等级列表
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private IntegralRuleService integralRuleService;
	@Autowired
	private Environment env;
	
	// 商户等级查询(根据userid查询该用户下所有实体商户<等级和积分信息>列表)
	@RequestMapping("/queryMercScore")
	public ResultDTO queryMercScore(@RequestBody MerchantUserRel merchantUserRel) {

		if (null == merchantUserRel.getAppUserId()) {
			logger.error("入参ID为null");
			return ResultDTO.fail("入参ID为null");
		}

		List<MerChantCoreDTO> datas = merchantService.getMerchantsScoresByUserId(merchantUserRel.getAppUserId());

		String prefix = env.getProperty("app.base.url");
		// 根据商户已有积分查询商户所属等级(v1-v7)
		for (MerChantCoreDTO merChantCoreDTO : datas) {
			if(merChantCoreDTO.getScores() == null){
				merChantCoreDTO.setScores(new BigDecimal(0));
			}

			SysConfig sysConfig = new SysConfig();
			sysConfig.setType(IntegralTypeEnum.INTEGRAL_TYPE.getCode());//type="11"
			SysConfig sysconfigMax = sysConfigService.selectMaxByType(IntegralTypeEnum.INTEGRAL_TYPE.getCode());//通过type查找最大值

			//如果积分大于查找出来的最大值，则为最高等级，通过找出来的最大的给当前赋值
			if(merChantCoreDTO.getScores().longValue() > Long.parseLong(sysconfigMax.getValue())){
				merChantCoreDTO.setMercLevel(sysconfigMax.getName());// vip等级：v1-v7
				merChantCoreDTO.setLevelName(sysconfigMax.getRemark());// vip名称
				merChantCoreDTO.setLevelIcon(prefix + sysconfigMax.getKeep3());
			}else{
				sysConfig.setValue(merChantCoreDTO.getScores().toString());
				SysConfig sysConfig2 = sysConfigService.selectLevelByScores(sysConfig);//根据积分查询所属等级
				merChantCoreDTO.setMercLevel(sysConfig2.getName());// vip等级：v1-v7
				merChantCoreDTO.setLevelName(sysConfig2.getRemark());// vip名称
				merChantCoreDTO.setLevelIcon(prefix + sysConfig2.getKeep3());
			}
		}

		// 通过userId查询绑定的商户信息(内部商户号，商户名称)
		return success(datas);
	}

	/**
	 *  商户等级详情查询(包括积分和下一级)
	 * @param userMerchant：需要带entityInnerCode和userId来查询某个商户的积分等级数据
	 * @return
	 */
	@RequestMapping("/queryMercLevelDetail")
	public ResultDTO<MerChantCoreDTO> queryMercLevelDetail(@RequestBody MerchantUserRel merchantUserRel) {

		// 判空
		if (StringUtils.isBlank(merchantUserRel.getEntityInnerCode())) {
			logger.error("入参实体商户号为空");
			return ResultDTO.fail("入参实体商户号为空");
		}

		// 获取该条商户信息
		MerChantCoreDTO merChantCoreDTO = merchantService.selectByEntityInnerCode(merchantUserRel);
		if (null == merChantCoreDTO) {
			logger.error("该用户ID下未绑定该商户");
			return fail("该用户ID下未绑定该商户");
		}

		String prefix = env.getProperty("app.base.url");

		// 根据商户已有积分查询商户所属等级(v1-v7)
		if (merChantCoreDTO.getScores() == null) {

			merChantCoreDTO.setScores(new BigDecimal(0));
		}
		SysConfig sysConfig = new SysConfig();
		sysConfig.setType(IntegralTypeEnum.INTEGRAL_TYPE.getCode());// type="11"
		SysConfig sysconfigMax = sysConfigService.selectMaxByType(IntegralTypeEnum.INTEGRAL_TYPE.getCode());// 通过type查找最大值

		// 判断是否是最高级会员
		sysConfig.setValue(merChantCoreDTO.getScores().toString());
		SysConfig sysConfig3 = sysConfigService.selectNextLevelByScores(sysConfig);// 根据积分查询下一级等级

		// 找出来的为空，说明是最高级会员，设置下一级vip数据全为null
		if (null == sysConfig3) {
			merChantCoreDTO.setMercLevel(sysconfigMax.getName());// vip等级：v1-v7
			merChantCoreDTO.setLevelName(sysconfigMax.getRemark());// vip名称
			merChantCoreDTO.setNextLevelName(null);// 下一级名称
			merChantCoreDTO.setNextScores(merChantCoreDTO.getScores());// 设置下一级积分为当前商户积分
			merChantCoreDTO.setDistScores(null);// 积分差值为0
			merChantCoreDTO.setDescription("已达到最高级别");
			merChantCoreDTO.setLevelIcon(prefix + sysconfigMax.getKeep3());

		} else {// 不为空，说明不是最高级，有下一级别
			SysConfig sysConfig2 = sysConfigService.selectLevelByScores(sysConfig);// 根据积分查询所属等级
			merChantCoreDTO.setMercLevel(sysConfig2.getName());//当前级别
			merChantCoreDTO.setLevelName(sysConfig2.getRemark());//当前级别名称
			merChantCoreDTO.setNextLevelName(sysConfig3.getRemark());// 下一级vip名称
			BigDecimal b1 = new BigDecimal(sysConfig2.getValue());// 获取下一级vip积分
			merChantCoreDTO.setNextScores(b1);// 设置下一级积分
			merChantCoreDTO.setDistScores(b1.subtract(merChantCoreDTO.getScores()));// 积分差值
			merChantCoreDTO.setLevelIcon(prefix + sysConfig2.getKeep3());
			merChantCoreDTO.setDescription("距离'" + sysConfig3.getRemark() + "'还差" + merChantCoreDTO.getDistScores() + "积分");
		}
		
		List<PermissionsDTO> permissionsList = sysConfigService.selectLevelPrivilege();
		String name = merChantCoreDTO.getMercLevel();
		for(PermissionsDTO permissions : permissionsList) {
			String lvName = permissions.getVipLevel();
			if(name.compareTo(lvName)<0) {
				permissions.setStatus(0);
				permissions.setIcoUrl(prefix + permissions.getIcoUrlGray());
			}else {
				permissions.setStatus(1);
				permissions.setIcoUrl(prefix + permissions.getIcoUrl());
			}
		}
		merChantCoreDTO.setPermissionsList(permissionsList);
		// 通过userId查询绑定的商户信息(内部商户号，商户名称)
		return success(merChantCoreDTO);
	}

	// 等级列表查询(v1-v7,普通商家-至尊商家，xxxx积分)
	@RequestMapping("/queryLevelList")
	public ResultDTO queryLevelList(@RequestBody MerchantUserRel merchantUserRel) {

		SysConfig sysConfig = new SysConfig();
		sysConfig.setType(IntegralTypeEnum.INTEGRAL_TYPE.getCode());// 等级列表(v1-v7)，type类型为11
		List<SysConfig> list = sysConfigService.selectAllByCondition(sysConfig);

		String prefix = env.getProperty("app.base.url");
		for (SysConfig sysConfig2 : list) {
			sysConfig2.setKeep2(prefix + sysConfig2.getKeep2());
		}
		return success(list);
	}
	
	// 商户等级详情页面下的获取经验格子
	@RequestMapping("/queryExpList")
	public ResultDTO queryExpList(@RequestBody MerchantUserRel merchantUserRel) {

		SysConfig sysConfig = new SysConfig();
		sysConfig.setType("10");// 等级列表(v1-v7)，type类型为11
		List<SysConfig> list = sysConfigService.selectAllByCondition(sysConfig);
		
		String prefix = env.getProperty("app.base.url");
		for (SysConfig sysConfig2 : list) {
			sysConfig2.setKeep2(prefix + sysConfig2.getKeep2());
		}
		return success(list);
	}
	
	@RequestMapping("/queryRules")
	public ResultDTO queryRules(@RequestBody MerchantUserRel merchantUserRel) {

		List<String> nameList = integralRuleService.queryDistinctName();
		
		List<IntegralRuleDTO> integralRuleDTOList = new ArrayList<>();

		for (String string : nameList) {
			IntegralRule integralRule = new IntegralRule();
			integralRule.setName(string);
			List<IntegralRule> list = integralRuleService.queryListByCondition(integralRule);
			IntegralRuleDTO integralRuleDTO = new IntegralRuleDTO();
			integralRuleDTO.setRuleName(string);
			integralRuleDTO.setRuleConditionList(list);
			integralRuleDTOList.add(integralRuleDTO);
		}

		return success(integralRuleDTOList);
	}
}
