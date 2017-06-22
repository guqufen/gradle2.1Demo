package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
}