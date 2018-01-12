package net.fnsco.web.withdraw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.dto.AppUserInfoDTO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.dto.TradeWithdrawDTO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

/**
 * 
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2018年1月4日 下午4:22:53
 */
@Controller
@RequestMapping(value = "/web/e789/withdraw")
public class TradeWithdrawController extends BaseController{
	
	@Autowired
	private TradeWithdrawService tradeWithdrawService;
	 @Autowired
	 private AppUserService        appUserService;
	/**
	 * 
	 * @param tradeWithdraw
	 * @param currentPageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public ResultPageDTO<TradeWithdrawDTO> query(TradeWithdrawDO tradeWithdraw, Integer currentPageNum,Integer pageSize) {
		tradeWithdraw.setTradeSubType(20);
		ResultPageDTO<TradeWithdrawDO> page = tradeWithdrawService.page(tradeWithdraw, currentPageNum, pageSize);
		List<TradeWithdrawDO> pageList = page.getList();
		List<TradeWithdrawDTO> tradeList = new ArrayList<TradeWithdrawDTO>();
		Integer count = page.getTotal();
		for(TradeWithdrawDO trade : pageList) {
        	AppUserInfoDTO appUserInfoDTO = appUserService.getMyselfInfo(trade.getAppUserId());
        	BigDecimal amount = trade.getAmount();
        	amount = amount.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        	TradeWithdrawDTO tradeWithdrawDTO =new TradeWithdrawDTO();
        	tradeWithdrawDTO.setId(trade.getId());
        	tradeWithdrawDTO.setUserName(appUserInfoDTO.getUserName());
        	tradeWithdrawDTO.setMobile(appUserInfoDTO.getMoblie());
        	tradeWithdrawDTO.setOrderNo(trade.getOrderNo());
        	tradeWithdrawDTO.setAmount(amount);
        	tradeWithdrawDTO.setBankAccountNo(trade.getBankAccountNo());
        	tradeWithdrawDTO.setCreateTime(trade.getCreateTime());
        	tradeWithdrawDTO.setStatus(trade.getStatus());
        	tradeList.add(tradeWithdrawDTO);
        }
		ResultPageDTO<TradeWithdrawDTO> pager = new ResultPageDTO<TradeWithdrawDTO>(count, tradeList);
		return pager;
	}
	
	
	@RequestMapping(value = "/queryWithdraw", method = RequestMethod.GET)
	@ResponseBody
	public ResultPageDTO<TradeWithdrawDTO> queryWithdraw(TradeWithdrawDO tradeWithdraw, Integer currentPageNum,Integer pageSize) {
		tradeWithdraw.setTradeSubType(20);
		ResultPageDTO<TradeWithdrawDO> page = tradeWithdrawService.page(tradeWithdraw, currentPageNum, pageSize);
		List<TradeWithdrawDO> pageList = page.getList();
		List<TradeWithdrawDTO> tradeList = new ArrayList<TradeWithdrawDTO>();
		Integer count = page.getTotal();
		for(TradeWithdrawDO trade : pageList) {
        	AppUserInfoDTO appUserInfoDTO = appUserService.getMyselfInfo(trade.getAppUserId());
        	BigDecimal amount = trade.getAmount();
        	amount = amount.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        	TradeWithdrawDTO tradeWithdrawDTO =new TradeWithdrawDTO();
        	tradeWithdrawDTO.setId(trade.getId());
        	tradeWithdrawDTO.setUserName(appUserInfoDTO.getUserName());
        	tradeWithdrawDTO.setMobile(appUserInfoDTO.getMoblie());
        	tradeWithdrawDTO.setOrderNo(trade.getOrderNo());
        	tradeWithdrawDTO.setAmount(amount);
        	tradeWithdrawDTO.setBankAccountNo(trade.getBankAccountNo());
        	tradeWithdrawDTO.setCreateTime(trade.getCreateTime());
        	tradeWithdrawDTO.setStatus(trade.getStatus());
        	tradeList.add(tradeWithdrawDTO);
        }
		ResultPageDTO<TradeWithdrawDTO> pager = new ResultPageDTO<TradeWithdrawDTO>(count, tradeList);
		return pager;
	}
	/**
	 * transferAccounts:(根据ID 修改状态)
	 *
	 * @param  @param id
	 * @param  @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @author tangliang
	 * @date   2018年1月12日 上午10:21:01
	 */
	@PostMapping(value = "/transferAccounts")
	@ResponseBody
	@RequiresPermissions(value = {"sys:withdraw:update"})
	public ResultDTO<String> transferAccounts(Integer id){
		TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
		tradeWithdraw.setId(id);
		tradeWithdraw.setStatus(3);
		tradeWithdraw.setUpdateTime(new Date());
		tradeWithdrawService.doUpdate(tradeWithdraw);
		
		return ResultDTO.success();
	}
}
