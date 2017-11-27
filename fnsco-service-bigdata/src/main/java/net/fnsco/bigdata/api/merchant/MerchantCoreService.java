/**
 * 
 */
package net.fnsco.bigdata.api.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fnsco.bigdata.service.domain.Agent;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

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
    ResultDTO<String> doAddMerCore(MerchantCore merchantCore,String userId);

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
     * 批量导入渠道信息保存
     * @param merchantBank
     * @return
     */
    Integer doAddChannel(MerchantChannel merchantChannel);
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
     * 批量导入银行卡保存
     * @param merchantBank
     * @return
     */
    Integer doAddBanks(MerchantBank merchantBank);

    /**
     * 条件查询 根据商家基本信息条件分页查询
     * @param merchantCore
     * @return
     */
    ResultPageDTO<MerchantCore> queryMerchantCore(MerchantCore merchantCore, int currentPageNum, int perPageSize);
    /**
     * 多条件查询 根据基本信息查询分页查，后导出
     * @param tradeDataDTO
     * @return
     */
    List<MerchantCore> queryMerchantList(MerchantCore merchantCore);
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
    /**
     * 生成innoCode
     * @return
     */
    String getInnerCode();
    
    List<MerchantChannel> findChannelByInnerCode(String innerCode);
    MerchantChannel findChannelByMerId(String merId,String channelType);
    
    /**
     * selectBybusinessLicenseNum:(根据营业执照号码查询)
     * @param businessLicenseNum
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午1:39:29
     * @return MerchantCore    DOM对象
     */
    MerchantCore selectBybusinessLicenseNum(String businessLicenseNum,String accountNo);
    
    /**
     * selectUniqueMer:(身份证+银行卡+通道类型+通道商户号)
     * @param cardNum
     * @param accountNo
     * @param channelType
     * @param channelMerId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年10月10日 下午1:15:41
     * @return MerchantCore    DOM对象
     */
    MerchantCore selectUniqueMer(String cardNum, String accountNo,String channelType,String channelMerId);
    /**
     * 
     * queryEntityCoreRefByInnerCode:(根据内部商务号查询渠道商户与实体商户的关系)
     *
     * @param innerCode
     * @return   List<MerchantEntityCoreRef>    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerchantEntityCoreRef> queryEntityCoreRefByInnerCode(String innerCode);

    MerchantCoreEntityZxyhDTO  queryZXYHInfoById(Integer id);

//	void updateStatusByInnerCode(String innerCode);

	void updateInfoByInnerCode(String innerCode, String secMerId);
    
    
}
