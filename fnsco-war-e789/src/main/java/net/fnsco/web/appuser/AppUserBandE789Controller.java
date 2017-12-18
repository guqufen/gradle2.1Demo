package net.fnsco.web.appuser;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.dto.AppUserMerchantEntityDTO;

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
}
