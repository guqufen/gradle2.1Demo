package net.fnsco.service.dao.master;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.MerchantTerminal;
/**
 * @desc 商家终端信息DAO
 * @author tangliang
 * @date 2017年6月22日 下午3:18:34
 */
public interface MerchantTerminalDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantTerminal record);

    int insertSelective(MerchantTerminal record);

    MerchantTerminal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantTerminal record);

    int updateByPrimaryKey(MerchantTerminal record);
    /**
     * 根据innercode查询出实体
     * @param innerCode
     * @return
     */
    MerchantTerminal selectByInnerCode(@Param("innerCode")String innerCode);
}