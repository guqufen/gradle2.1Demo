package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.MerchantFile;

/**
 * @desc 商家文件DAO
 * @author tangliang
 * @date 2017年6月21日 上午10:22:16
 */
public interface MerchantFileDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantFile record);

    int insertSelective(MerchantFile record);

    MerchantFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantFile record);

    int updateByPrimaryKey(MerchantFile record);
    
    /**
     * deleteByInnerCode:(删除)
     * @param innerCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月22日 上午10:58:11
     * @return int    DOM对象
     */
    int deleteByInnerCodeAndFileType(@Param("innerCode") String innerCode,@Param("fileType")String fileType);
    
    /**
     * 根据条件查询
     * @param record
     * @return
     */
    List<MerchantFile> queryByCondition(MerchantFile record);
    
    /**
     * 根据innercode查询
     * @param innerCode
     * @return
     */
    List<MerchantFile> queryByInnerCode(@Param("innerCode") String innerCode);
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
}