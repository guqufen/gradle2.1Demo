package net.fnsco.web.controller.merchant;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.WebMerchantPosDTO;
import net.fnsco.bigdata.api.dto.WebMerchantPosDTO2;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityCoreRefDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.merchant.IntegralLogService;
import net.fnsco.web.controller.merchant.jo.MerchantChannelJO;
import net.fnsco.web.controller.merchant.jo.MerchantPosJO;
import net.fnsco.web.controller.merchant.jo.PosJO;
import net.fnsco.web.controller.merchant.pos.MerchantChannelJO2;
import net.fnsco.web.controller.merchant.pos.PosJO2;
/**
 * @desc Pos相关业务控制器
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年8月17日 上午9:33:47
 */
@Controller
@RequestMapping(value = "/web/merchantpos")
public class MerchantPosController extends BaseController {

	@Autowired
	private MerchantPosService merchantPosService;
	@Autowired
	private IntegralLogService integralRuleLogService;
	@Autowired
	private MerchantEntityCoreRefDao merchantEntityCoreRefDao;
	@Autowired
	private MerchantTerminalDao merchantTerminalDao;

	/**
	 * toAddTerminal:(这里用一句话描述这个方法的作用)添加POS信息
	 * 
	 * @param posInfos
	 * @return 设定文件
	 * @author tangliang
	 * @date 2017年8月17日 上午11:37:59
	 * @return ResultDTO<String> DOM对象
	 */
	@Transactional
//	@RequestMapping(value = "/toAddPosInfos", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merchant:add" })
	public ResultDTO<String> toAddPosInfos(@RequestBody PosJO pos) {
		// List<MerchantChannelJO> temp = Arrays.asList(posInfos);
		List<MerchantChannelJO> posInfos = pos.getPoses();
		countPosScores(posInfos, pos.getInnerCode());
		WebUserDTO obj = (WebUserDTO) session.getAttribute(FrameworkConstant.SESSION_USER_KEY);
		List<WebMerchantPosDTO> params = MerchantHelper.toPosDTO(posInfos, pos.getInnerCode(), obj.getId());
		ResultDTO<String> result = merchantPosService.savePosInfo(params);
		return result;
	}

	/**
	 * toAddTerminal:添加POS信息（pos和终端分离）
	 * 
	 * @param posInfos
	 * @return
	 * @author bhl
	 * @date
	 * @return ResultDTO<String> DOM对象
	 */
	@Transactional
	@RequestMapping(value = "/toAddPosInfos", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merchant:add" })
	public ResultDTO<String> toAddPosInfos2(@RequestBody PosJO2 pos) {
		List<MerchantChannelJO2> posInfos = pos.getPoses();
		countPosScores2(posInfos.get(0).getPosDeviceInfos(), pos.getInnerCode());
		WebUserDTO obj = (WebUserDTO) session.getAttribute(FrameworkConstant.SESSION_USER_KEY);
		List<WebMerchantPosDTO2> params = MerchantHelper.toPosDTO2(posInfos, pos.getInnerCode(), obj.getId());
		ResultDTO<String> result = merchantPosService.savePosInfo2(params);
		return result;
	}

	/**
	 * deletePosInfo:(这里用一句话描述这个方法的作用)删除POS机信息
	 * 
	 * @param id
	 * @return 设定文件
	 * @author tangliang
	 * @date 2017年8月17日 下午2:11:23
	 * @return ResultDTO<String> DOM对象
	 */
//	@RequestMapping(value = "/deletePosInfo", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merchant:delete" })
	public ResultDTO deletePosInfo(@RequestParam(value = "id") Integer id) {
		if (null == id) {
			return ResultDTO.fail();
		}
		boolean result = merchantPosService.deleteByPosId(id);
		if (!result) {
			return ResultDTO.fail();
		}
		return ResultDTO.success();
	}
	//新方法
	@RequestMapping(value = "/deletePosInfo", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merchant:delete" })
	public ResultDTO deletePosInfo2(@RequestParam(value = "id") Integer id) {
		if (null == id) {
			return ResultDTO.fail();
		}
		int result = merchantPosService.deleteByPrimaryKey(id);
		if (result == 1 ) {
			return ResultDTO.fail();
		}
		return ResultDTO.success();
	}

	@RequestMapping(value = "/deleteTerminalInfo", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "m:merchant:delete" })
	public ResultDTO deleteTerminalInfo(@RequestParam(value = "id") Integer id) {
		if (null == id) {
			return ResultDTO.fail();
		}
		int result = merchantTerminalDao.deleteByPrimaryKey(id);
		if (result == 1 ) {
			return ResultDTO.fail();
		}
		return ResultDTO.success();
	}
	
	
	
	/**
	 * getPosInfo:(这里用一句话描述这个方法的作用)获取所有银行卡信息
	 * 
	 * @param innerCode
	 * @return 设定文件
	 * @author tangliang
	 * @date 2017年8月18日 下午5:42:15
	 * @return ResultDTO DOM对象
	 */
	@RequestMapping(value = "/getBankInfo", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "m:merchant:list" })
	public ResultDTO<List<MerchantBank>> getBankInfo(@RequestParam(value = "innerCode") String innerCode) {
		if (Strings.isNullOrEmpty(innerCode)) {
			return ResultDTO.fail();
		}
		List<MerchantBank> datas = merchantPosService.queryWebByInnerCode(innerCode);
		return ResultDTO.success(datas);
	}

	/**
	 * countPosScores:(根据POS机信息来统计积分，只有新增加POS机子才去统计积分)
	 *
	 * @param @param
	 *            posInfos 设定文件
	 * @return void DOM对象
	 * @author tangliang
	 * @date 2017年11月2日 下午2:25:31
	 */
	
	private void countPosScores2(List<MerchantPos> params, String innerCode) {
		for (MerchantPos merchantPos : params) {
			if (null == merchantPos.getId()) {
				// 等于空，才统计积分情况
				if (!Strings.isNullOrEmpty(innerCode)) {
					MerchantEntityCoreRef mecr = merchantEntityCoreRefDao.selectByInnerCodeLimit1(innerCode);
					if (null != mecr && !Strings.isNullOrEmpty(mecr.getEntityInnerCode())) {
						integralRuleLogService.insert(mecr.getEntityInnerCode(),
								ConstantEnum.IntegralTypeEnum.CODE_SQ.getCode());
					}
				}

			}
		}
	}
	
	
	private void countPosScores(List<MerchantChannelJO> params, String innerCode) {

		for (MerchantChannelJO webMerchantPosDTO : params) {
			List<MerchantPosJO> posInfos = webMerchantPosDTO.getPosInfos();
			for (MerchantPosJO webMerchantTerminalDTO : posInfos) {
				// 等于空，才统计积分情况
				if (null == webMerchantTerminalDTO.getPosId()) {
					if (!Strings.isNullOrEmpty(innerCode)) {
						MerchantEntityCoreRef mecr = merchantEntityCoreRefDao.selectByInnerCodeLimit1(innerCode);
						if (null != mecr && !Strings.isNullOrEmpty(mecr.getEntityInnerCode())) {
							integralRuleLogService.insert(mecr.getEntityInnerCode(),
									ConstantEnum.IntegralTypeEnum.CODE_SQ.getCode());
						}
					}

				}
			}
		}
	}

}
