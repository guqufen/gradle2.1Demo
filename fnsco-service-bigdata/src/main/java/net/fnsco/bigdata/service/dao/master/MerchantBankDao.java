package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.BankCardTypeDTO;
import net.fnsco.bigdata.api.dto.MerChantBankDTO;
import net.fnsco.bigdata.service.domain.MerchantBank;
/**
 * @desc 商家银行卡信息DAO
 * @author tangliang
 * @date 2017年6月22日 下午3:09:29
 */
public interface MerchantBankDao {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_merchant_bank
     *
     * @mbggenerated Thu Jun 22 15:05:54 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_merchant_bank
     *
     * @mbggenerated Thu Jun 22 15:05:54 CST 2017
     */
    int insert(MerchantBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_merchant_bank
     *
     * @mbggenerated Thu Jun 22 15:05:54 CST 2017
     */
    int insertSelective(MerchantBank record); 
    	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_merchant_bank
     *
     * @mbggenerated Thu Jun 22 15:05:54 CST 2017
     */
    MerchantBank selectByPrimaryKey(Integer id);
    /**
     * deleteByInnerCode:(根据innercode删除数据)
     * @param innerCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月22日 上午10:54:50
     * @return int    DOM对象
     */
    int deleteByInnerCode(@Param("innerCode") String innerCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_merchant_bank
     *
     * @mbggenerated Thu Jun 22 15:05:54 CST 2017
     */
    int updateByPrimaryKeySelective(MerchantBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_merchant_bank
     *
     * @mbggenerated Thu Jun 22 15:05:54 CST 2017
     */
    int updateByPrimaryKey(MerchantBank record);
    /**
     * queryByInnerCode:(这里用一句话描述这个方法的作用) 根据innercode查询银行卡信息列表
     *
     * @param innerCode
     * @return    设定文件
     * @return List<MerChantBankDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerChantBankDTO> queryByInnerCode(@Param("innerCode") String innerCode);
    /**
     * queryWebByInnerCode:(这里用一句话描述这个方法的作用)后台管理系统用到
     *
     * @param innerCode
     * @return    设定文件
     * @return List<MerchantBank>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerchantBank> queryWebByInnerCode(@Param("innerCode") String innerCode);
    /**
     * deleteByMerCoreIds:(这里用一句话描述这个方法的作用) 根据core实体IDS删除关联数据
     *
     * @param ids
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int deleteByMerCoreIds(Integer[] ids);
    
    /**
     * countBanksByInnerCodeAndAccountNo:(根据innerCode和accountNo获取数量)
     * @param innerCode
     * @param accountNo
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月19日 下午4:29:06
     * @return int    DOM对象
     */
    int countBanksByInnerCodeAndAccountNo(@Param("innerCode") String innerCode,@Param("accountNo") String accountNo);
    
    /**
     * selectByAppUserId:(根据appUserId查询实体商户结构下的银行卡信息)
     *
     * @param  @param userId
     * @param  @return    设定文件
     * @return MerchantBank    DOM对象
     * @author tangliang
     * @date   2017年12月7日 下午2:23:59
     */
    MerchantBank selectByAppUserId(@Param("userId") Integer userId);

    BankCardTypeDTO queryByCertifyId(@Param("bankCardNum") String bankCardNum,@Param("cardTotalLength")  String cardTotalLength);
}