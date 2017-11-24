package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.bigdata.api.dto.MerChantCoreDetailDTO;
import net.fnsco.bigdata.api.dto.MerTerminalsDTO;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.PageDTO;
/**
 * @desc 商户基本信息DAO
 * @author tangliang
 * @date 2017年6月21日 下午1:48:46
 */
@Mapper
public interface MerchantCoreDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantCore record);

    int insertSelective(MerchantCore record);

    MerchantCore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantCore record);

    int updateByPrimaryKey(MerchantCore record);
    /**
     * selectBybusinessLicenseNum:(根据营业执照号码查询)
     * @param businessLicenseNum
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午1:39:29
     * @return MerchantCore    DOM对象
     */
    MerchantCore selectBybusinessLicenseNum(@Param("businessLicenseNum") String businessLicenseNum,@Param("accountNo")String accountNo);
    
    /**
     * 条件分页查询
     */
    List<MerchantCore> queryPageList(PageDTO<MerchantCore> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(MerchantCore record);
    
    /**
     * 根据条件查询所有数据
     * @param record
     * @return
     */
    List<MerchantCore> queryListByCondition(MerchantCore record);
    
    /**
     * 根据ID删除多个，真正删除
     * @param id
     * @return
     */
    int deleteByMultipleKey(Integer[] ids);
    
    /**
     * 更新状态
     * @param ids
     * @return
     */
    int updateStatusByMutipleKey(Integer[] ids);
    
    /**
     * 根据ID 查询出所有关联的数据
     * @param id
     * @return
     */
    MerchantCore queryAllById(Integer id);
    
    /**
     * 根据id查询出入建中信商户所需的数据
     * @param id
     * @return
     */
    MerchantCore queryAllByIdForAddZXMerc(Integer id);
    /**
     * 
     * queryAllByUseraId:(这里用一句话描述这个方法的作用) 根据登录的APPUSERID查询关联的商户实体
     * @param userId
     * @return    设定文件
     * @return List<MerchantCore>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerChantCoreDTO> queryAllByUseraId(@Param("userId")Integer userId);
    /**
     * 
     * queryAllByUseraId:(这里用一句话描述这个方法的作用) 根据登录的APPUSERID查询关联的商户实体
     * @param userId
     * @return    设定文件
     * @return List<MerchantCore>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    MerchantCore getMerChantCoreByInnerCode(@Param("innerCode")String innerCode);
    
    /**
     * getMerchantsScoresByUserId:根据登录的APPUSERID查询关联的商户实体以及积分
     * @param userId
     * @return
     */
    List<MerChantCoreDTO> getMerchantsScoresByUserId(@Param("userId")Integer userId);
    
    /**
     * getMerchantsScoresByUserId:根据登录的APPUSERID和商户号查询关联的商户实体以及积分
     * @param userId
     * @return
     */
    MerChantCoreDTO selectByEntityInnerCode(MerchantUserRel merchantUserRel);
    
    /**
     * queryDetailById:(这里用一句话描述这个方法的作用) 根据商家ID 查询详情
     *
     * @param merId
     * @return    设定文件
     * @return MerChantCoreDetailDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    MerChantCoreDetailDTO queryDetailById(@Param("merId") Integer merId);
    /**
     * queryMerTerminalsByUserId:(这里用一句话描述这个方法的作用) 根据用户信息查询终端列表
     *
     * @param userId
     * @return    设定文件
     * @return List<MerTerminalsDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerTerminalsDTO> queryMerTerminalsByUserId(@Param("userId") Integer userId);
    /**
     * 
     * selectByInnerCode:(根据商户号查询商户信息)
     *
     * @param innerCode
     * @return   MerchantCore    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    MerchantCore selectByInnerCode(@Param("innerCode") String innerCode);
    
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
    MerchantCore selectUniqueMer(@Param("cardNum") String cardNum,@Param("accountNo") String accountNo,@Param("channelType") String channelType,@Param("channelMerId") String channelMerId);

    @Select("Select etps_attr from m_merchant_core WHERE inner_code =#{innerCode} ")
	Integer getEtpsAttrByInnerCode(String innerCode);

    @Update("UPDATE m_merchant_core set STATUS = 5 WHERE inner_code = #{innerCode}")
	void updateStatusByInnerCode(String innerCode);

	

   
}