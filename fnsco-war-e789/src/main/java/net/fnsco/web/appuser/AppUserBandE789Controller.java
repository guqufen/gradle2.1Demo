package net.fnsco.web.appuser;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.dto.AppUserMerchantEntityDTO;
import net.fnsco.trading.service.merchantentity.dao.AppUserMerchantEntityDAO;
import net.fnsco.trading.service.merchantentity.entity.AppUserMerchantEntityDO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月18日 下午2:12:55
 */
@Controller
@RequestMapping(value = "/web/e789/appsuser")
public class AppUserBandE789Controller {
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppUserMerchantEntityDAO appUserMerchantEntityDAO;
	/**
	 * appMsgIndex:(这里用一句话描述这个方法的作用) 分页查询
	 *
	 * @param sysmsg
	 * @param currentPageNum
	 * @param pageSize
	 * @return 设定文件
	 * @return ResultPageDTO<SysAppMessage> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:user:list" })
	public ResultPageDTO<AppUserMerchantEntityDTO> appUserIndex(AppUserMerchantEntityDTO sysmsg, Integer currentPageNum,
			Integer pageSize) {
		return appUserService.queryPageList(sysmsg, currentPageNum, pageSize);
	}
	
	/**
	 * bindMerchantEntity:(E789绑定)
	 *
	 * @param  @param entity
	 * @param  @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @author tangliang
	 * @date   2017年12月18日 下午3:06:51
	 */
	@RequestMapping(value = "/bindMerchantEntity", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> bindMerchantEntity(AppUserMerchantEntityDO entity) {
		if(Strings.isNullOrEmpty(entity.getEntityInnerCode()) || null == entity.getAppUserId()) {
			return ResultDTO.failForMessage("入参不正确!");
		}
		AppUserMerchantEntityDO appUserMerchantEntity = appUserMerchantEntityDAO.selectByEntityInnerCode(entity.getEntityInnerCode(), entity.getAppUserId());
		if(null != appUserMerchantEntity) {
			appUserMerchantEntity.setModefyTime(new Date());
			appUserMerchantEntity.setEntityInnerCode(entity.getEntityInnerCode());
			appUserMerchantEntityDAO.update(appUserMerchantEntity);
		}else {
			entity.setModefyTime(new Date());
			appUserMerchantEntityDAO.insert(appUserMerchantEntity);
		}
		return ResultDTO.successForSubmit();
	}
}
