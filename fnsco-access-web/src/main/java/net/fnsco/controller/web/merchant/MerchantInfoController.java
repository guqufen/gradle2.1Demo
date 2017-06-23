package net.fnsco.controller.web.merchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.api.merchant.MerchantCoreService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.MerchantCore;

/**@desc 商户信息控制器
 * @author tangliang
 * @date 2017年6月21日 下午7:39:22
 */
@Controller
@RequestMapping("/web/merchantinfo")
public class MerchantInfoController extends BaseController{
	
	private final static Logger logger = LoggerFactory.getLogger(MerchantInfoController.class);
	
	@Autowired
	private MerchantCoreService  merchantCoreService;
	
	/**
	 * 跳转到商户信息首页
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public ResultPageDTO<MerchantCore> merchatInfoIndex(MerchantCore merchantCore,Integer currentPageNum,Integer pageSize){
		logger.info("查询商户列表");
		return merchantCoreService.queryMerchantCore(merchantCore, currentPageNum, pageSize);
	}
	/**
	 * 获取表格数据
	 * @param merchantCore
	 * @return
	 */
	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String,List<MerchantCore>> queryList(MerchantCore merchantCore){
		Map<String,List<MerchantCore>> maps = new HashMap<String, List<MerchantCore>>();
		maps.put("data", merchantCoreService.queryAllByCondition(merchantCore));
		return maps;
	}
}
