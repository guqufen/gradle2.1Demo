/**
 * 
 */
package net.fnsco.api.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.Agent;
import net.fnsco.service.domain.MerchantCore;

/**@desc 商家基本信息
 * @author tangliang
 * @date 2017年6月21日 下午2:19:07
 */
public interface MerchantCoreService {
	
	/**
	 * 保存商家信息
	 * @param merchantInfo
	 * @param loginUserId
	 * @return
	 */
	public ResultDTO<Integer> doAdd(HttpServletRequest request);
	
	/**
	 * doAddMerCore:(这里用一句话描述这个方法的作用)添加商户基本信息
	 *
	 * @param merchantCore
	 * @return    设定文件
	 * @return ResultDTO<Integer>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public ResultDTO<String> doAddMerCore(MerchantCore merchantCore);
	
	/**
	 * 条件查询 根据商家基本信息条件分页查询
	 * @param merchantCore
	 * @return
	 */
	public ResultPageDTO<MerchantCore> queryMerchantCore(MerchantCore merchantCore,int currentPageNum,int perPageSize);
	
	/**
	 * 条件查询所有数据
	 * @param merchantCore
	 * @return
	 */
	public List<MerchantCore> queryAllByCondition(MerchantCore merchantCore);
	
	/**
	 * 根据ID删除数据
	 * @param ids
	 * @return
	 */
	public ResultDTO<Integer> deleteByIds(Integer[] ids);
	
	/**
	 * 根据ID 查询所有数据
	 * @param id
	 * @return
	 */
	public ResultDTO<MerchantCore> queryAllById(Integer id);
	
	/**
	 * queryAllAgent:(这里用一句话描述这个方法的作用) 查询所有代理商
	 *
	 * @return    设定文件
	 * @return ResultDTO<List<Agent>>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public ResultDTO<List<Agent>> queryAllAgent();
}
