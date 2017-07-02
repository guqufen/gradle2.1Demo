package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.api.dto.MerChantBankDTO;
import net.fnsco.service.domain.MerchantBank;
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
}