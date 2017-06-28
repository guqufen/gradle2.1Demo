package net.fnsco.service.dao.master;

import java.util.List;

import net.fnsco.service.domain.MerchantFileTemp;
/**
 * @desc 临时文件信息DAO接口 
 * @author tangliang
 * @date 2017年6月27日 下午5:27:41
 */
public interface MerchantFileTempDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantFileTemp record);

    int insertSelective(MerchantFileTemp record);

    MerchantFileTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantFileTemp record);

    int updateByPrimaryKey(MerchantFileTemp record);
    
    /**
     * 根据条件查询
     * @param record
     * @return
     */
    List<MerchantFileTemp> queryByCondition(MerchantFileTemp record);
    
}