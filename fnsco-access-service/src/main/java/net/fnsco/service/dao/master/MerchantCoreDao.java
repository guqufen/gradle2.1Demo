package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.fnsco.api.dto.MerChantCoreDTO;
import net.fnsco.api.dto.MerChantCoreDetailDTO;
import net.fnsco.api.dto.MerTerminalsDTO;
import net.fnsco.core.base.PageDTO;
import net.fnsco.service.domain.MerchantCore;
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
}