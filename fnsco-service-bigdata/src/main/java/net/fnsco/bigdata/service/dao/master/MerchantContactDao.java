package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.MerchantContact;

/**
 * @desc 商家联系信息DAO
 * @author tangliang
 * @date 2017年6月21日 下午2:45:50
 */
public interface MerchantContactDao {

    int insert(MerchantContact record);

    int insertSelective(MerchantContact record);
    
    int updateByPrimaryKeySelective(MerchantContact record);

    int updateByPrimaryKey(MerchantContact record);
    
    int deleteByPrimaryKey(Integer id);
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
     * 根据innercode查询
     * @param innerCode
     * @return
     */
   List<MerchantContact> queryByInnerCode(@Param("innerCode") String innerCode);
   //条件查询
   List<MerchantContact> queryListByCondition(MerchantContact merchantContact);
}