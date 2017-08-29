package net.fnsco.order.service.dao.master;

import java.util.List;

import net.fnsco.core.base.PageDTO;
import net.fnsco.order.service.domain.SysBank;
/**
 * @desc 银行卡信息DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月7日 上午10:50:37
 *
 */
public interface SysBankDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysBank record);

    int insertSelective(SysBank record);

    SysBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysBank record);

    int updateByPrimaryKey(SysBank record);
    
    /**
     * 条件分页查询
     */
    List<SysBank> queryPageList(PageDTO<SysBank> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(SysBank record);
}