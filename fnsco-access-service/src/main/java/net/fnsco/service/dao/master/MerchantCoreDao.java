package net.fnsco.service.dao.master;

import org.apache.ibatis.annotations.Mapper;

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
}