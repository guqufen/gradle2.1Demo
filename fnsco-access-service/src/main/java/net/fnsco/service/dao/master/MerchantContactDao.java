package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.MerchantContact;

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
     * 根据innercode查询
     * @param innerCode
     * @return
     */
   List<MerchantContact> queryByInnerCode(@Param("innerCode") String innerCode);
   //条件查询
   List<MerchantContact> queryListByCondition(MerchantContact merchantContact);
}