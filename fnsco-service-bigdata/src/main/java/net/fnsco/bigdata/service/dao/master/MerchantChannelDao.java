package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.MerchantChannel;

/**
 * @desc 商户渠道DAO
 * @author tangliang
 * @date 2017年6月22日 下午4:07:59
 */
public interface MerchantChannelDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantChannel record);

    int insertSelective(MerchantChannel record);

    MerchantChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantChannel record);

    int updateByPrimaryKey(MerchantChannel record);

    MerchantChannel selectByMerCode(@Param("merCode") String merCode, @Param("channelType") String channelType);
    
    /**
     * selectByInnerCodeAndChannelCode:(根据内部商务号和channelMerId回去渠道信息)
     * @param innerCode
     * @param channelType
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午2:37:44
     * @return MerchantChannel    DOM对象
     */
    MerchantChannel selectByInnerCodeAndChannelCode(@Param("innerCode") String innerCode, @Param("channelMerId") String channelMerId,@Param("channelType")String channelType);
    MerchantChannel selectByInnerCodeType(@Param("innerCode") String innerCode, @Param("channelType")String channelType);
    /**
     * 根据innercode查询
     * @param innerCode
     * @return
     */
    List<MerchantChannel> selectByInnerCode(@Param("innerCode") String innerCode);
    /**
     * 根据innercode查询
     * @param innerCode
     * @return
     */
    List<MerchantChannel> selectByInnerCodes(@Param("innerCodes") List<String> innerCodes);
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
     * 
     * countCanCreateTaiCode:(根据商家ID查询该商家是否可以生成台码)
     * @param merId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月13日 上午9:38:19
     * @return int    DOM对象
     */
    int countCanCreateTaiCode(@Param("merId") Integer merId);
    
    int countCanCreateTai(@Param("innerCode") String  innerCode);
}