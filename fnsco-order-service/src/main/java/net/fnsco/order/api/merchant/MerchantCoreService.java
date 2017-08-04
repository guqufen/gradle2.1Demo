/**
 * 
 */
package net.fnsco.order.api.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.service.domain.Agent;
import net.fnsco.order.service.domain.MerchantBank;
import net.fnsco.order.service.domain.MerchantChannel;
import net.fnsco.order.service.domain.MerchantContact;
import net.fnsco.order.service.domain.MerchantCore;
import net.fnsco.order.service.domain.MerchantTerminal;

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
    ResultDTO<Integer> doAdd(HttpServletRequest request);

    /**
     * doAddMerCore:(这里用一句话描述这个方法的作用)添加商户基本信息
     *
     * @param merchantCore
     * @return    设定文件
     * @return ResultDTO<Integer>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> doAddMerCore(MerchantCore merchantCore);

    /**
     * doAddMerContact:(这里用一句话描述这个方法的作用) 保存联系方式
     *
     * @param merchantCore
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> doAddMerContact(List<MerchantContact> merchantContact);

    /**
     * 
     * doAddMerTerminal:(这里用一句话描述这个方法的作用)保存终端信息
     *
     * @param merchantTerminal
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> doAddMerTerminal(List<MerchantTerminal> merchantTerminal);

    /**
     * doAddChannel:(这里用一句话描述这个方法的作用)保存渠道信息
     *
     * @param merchantChannel
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> doAddMerChannel(List<MerchantChannel> merchantChannel);

    /**
     * doAddMerBanks:(这里用一句话描述这个方法的作用)保存银行卡信息
     *
     * @param merchantBanks
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> doAddMerBanks(List<MerchantBank> merchantBanks);

    /**
     * 条件查询 根据商家基本信息条件分页查询
     * @param merchantCore
     * @return
     */
    ResultPageDTO<MerchantCore> queryMerchantCore(MerchantCore merchantCore, int currentPageNum, int perPageSize);

    /**
     * 条件查询所有数据
     * @param merchantCore
     * @return
     */
     List<MerchantCore> queryAllByCondition(MerchantCore merchantCore);

    /**
     * 根据ID删除数据
     * @param ids
     * @return
     */
     ResultDTO<Integer> deleteByIds(Integer[] ids);

    /**
     * deleteByContact:(这里用一句话描述这个方法的作用)根据ID删除联系方式
     *
     * @param id
     * @return    设定文件
     * @return ResultDTO<Integer>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
     ResultDTO<Integer> deleteByContact(Integer id);

    /**
     * deleteByTerminal:(这里用一句话描述这个方法的作用)根据ID删除终端信息
     *
     * @param id
     * @return    设定文件
     * @return ResultDTO<Integer>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<Integer> deleteByTerminal(Integer id);

    ResultDTO<Integer> deleteByChanel(Integer id);

    ResultDTO<Integer> deleteByBank(Integer id);

    /**
     * 根据ID 查询所有数据
     * @param id
     * @return
     */
    ResultDTO<MerchantCore> queryAllById(Integer id);

    /**
     * 
     * queryByInnerCode:(根据内部商务号查询商户信息)
     *
     * @param innerCode
     * @return   MerchantCore    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    MerchantCore queryByInnerCode(String innerCode);

    /**
     * queryAllAgent:(这里用一句话描述这个方法的作用) 查询所有代理商
     *
     * @return    设定文件
     * @return ResultDTO<List<Agent>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<List<Agent>> queryAllAgent();
}
