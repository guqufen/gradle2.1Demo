package net.fnsco.service.dao.master;

import java.util.List;

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
    /**
     * selectByUserId:(这里用一句话描述这个方法的作用) 根据用户ID查询出所有的终端信息
     *
     * @param userId
     * @return    设定文件
     * @return List<MerchantTerminal>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerchantTerminal> selectByUserId(@Param("userId") Integer userId);
}