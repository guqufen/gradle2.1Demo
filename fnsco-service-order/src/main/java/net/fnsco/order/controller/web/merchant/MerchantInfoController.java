package net.fnsco.order.controller.web.merchant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.merchant.MerchantCoreService;
import net.fnsco.order.service.domain.Agent;
import net.fnsco.order.service.domain.MerchantBank;
import net.fnsco.order.service.domain.MerchantChannel;
import net.fnsco.order.service.domain.MerchantContact;
import net.fnsco.order.service.domain.MerchantCore;
import net.fnsco.order.service.domain.MerchantTerminal;

/**@desc 商户信息控制器
 * @author tangliang
 * @date 2017年6月21日 下午7:39:22
 */
@Controller
@RequestMapping(value = "/web/merchantinfo")
public class MerchantInfoController extends BaseController{
	
	private final static Logger logger = LoggerFactory.getLogger(MerchantInfoController.class);
	
	@Autowired
	private MerchantCoreService  merchantCoreService;
	
	/**
	 * 跳转到商户信息首页
	 * @return
	 */
	@RequestMapping(value ="/query",method= RequestMethod.GET)
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
	@RequestMapping(value ="/queryList",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,List<MerchantCore>> queryList(MerchantCore merchantCore){
		Map<String,List<MerchantCore>> maps = new HashMap<String, List<MerchantCore>>();
		maps.put("data", merchantCoreService.queryAllByCondition(merchantCore));
		return maps;
	}
	
	/**
	 * 添加商户相关信息
	 * @param request
	 * @param merchantCore
	 * @param merchantFile
	 * @param merchantContact
	 * @return 
	 */
	@RequestMapping(value ="/toAddCore",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> toAddCore(MerchantCore merchantCore){
		ResultDTO<String> result = merchantCoreService.doAddMerCore(merchantCore);
		return result;
	}
	/**
	 * toAddContact:(这里用一句话描述这个方法的作用) 保存联系信息
	 *
	 * @param merchantCore
	 * @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/toAddContact",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> toAddContact(@RequestBody MerchantContact[] contacts){
	    List<MerchantContact> params = Arrays.asList(contacts);
        ResultDTO<String> result = merchantCoreService.doAddMerContact(params);
        return result;
    }
	/**
	 * toAddTerminal:(这里用一句话描述这个方法的作用)保存终端设备信息
	 *
	 * @param terminals
	 * @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/toAddTerminal",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> toAddTerminal(@RequestBody MerchantTerminal[] terminals){
        List<MerchantTerminal> params = Arrays.asList(terminals);
        ResultDTO<String> result = merchantCoreService.doAddMerTerminal(params);
        return result;
    }
	/**
	 * toAddChannel:(这里用一句话描述这个方法的作用)保存渠道信息
	 *
	 * @param channels
	 * @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/toAddChannel",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> toAddChannel(@RequestBody MerchantChannel[] channels){
        List<MerchantChannel> params = Arrays.asList(channels);
        ResultDTO<String> result = merchantCoreService.doAddMerChannel(params);
        return result;
    }
	/**
	 * toAddBank:(这里用一句话描述这个方法的作用)保存或修改银行卡信息
	 *
	 * @param banks
	 * @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/toAddBank",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> toAddBank(@RequestBody MerchantBank[] banks){
        List<MerchantBank> params = Arrays.asList(banks);
        ResultDTO<String> result = merchantCoreService.doAddMerBanks(params);
        return result;
    }
	
	/**
	 * 根据ID删除商户信息，逻辑删除（不是真正的删除数据，只是更新数据的状态）
	 * @param ids
	 * @return
	 */
	@RequestMapping(value ="/delete",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Integer> delete(@RequestParam(value="ids[]") Integer[] ids){
		logger.info("删除商户数据ids = "+ids);
		return merchantCoreService.deleteByIds(ids);
	}
	
	/**
	 * deleteContact:(这里用一句话描述这个方法的作用)删除联系方式
	 *
	 * @param id
	 * @return    设定文件
	 * @return ResultDTO<Integer>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/deleteContact",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<Integer> deleteContact(@RequestParam(value="id") Integer id){
        logger.info("删除商户联系方式数据id = "+id);
        return merchantCoreService.deleteByContact(id);
    }
	/**
	 * deleteChannel:(这里用一句话描述这个方法的作用)删除渠道信息
	 * @param id
	 * @return    设定文件
	 * @return ResultDTO<Integer>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/deleteChannel",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<Integer> deleteChannel(@RequestParam(value="id") Integer id){
        logger.info("删除商户渠道信息数据id = "+id);
        return merchantCoreService.deleteByChanel(id);
    }
	/**
	 * deleteTerminal:(这里用一句话描述这个方法的作用)删除终端信息
	 * @param id
	 * @return    设定文件
	 * @return ResultDTO<Integer>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/deleteTerminal",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<Integer> deleteTerminal(@RequestParam(value="id") Integer id){
        logger.info("删除商户终端信息数据id = "+id);
        return merchantCoreService.deleteByTerminal(id);
    }
	/**
	 * deleteBank:(这里用一句话描述这个方法的作用)删除银行卡信息
	 *
	 * @param id
	 * @return    设定文件
	 * @return ResultDTO<Integer>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/deleteBank",method= RequestMethod.POST)
    @ResponseBody
    public ResultDTO<Integer> deleteBank(@RequestParam(value="id") Integer id){
        logger.info("删除商户银行卡数据id = "+id);
        return merchantCoreService.deleteByBank(id);
    }
	/**
	 * 根据ID 查询所有的数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/queryAllById",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<MerchantCore> queryAllById(Integer id){
		logger.info("查询出商户所有关联数据id = "+id);
		return merchantCoreService.queryAllById(id);
	}
	
	/**
	 * queryAllAgent:(这里用一句话描述这个方法的作用)查询所有代理商
	 * @return    设定文件
	 * @return ResultDTO<List<Agent>>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	@RequestMapping(value ="/queryAgents",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<List<Agent>> queryAllAgent()
	{
	    return merchantCoreService.queryAllAgent();
	}
}
