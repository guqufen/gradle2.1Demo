package net.fnsco.service.dao.master;

import java.util.List;

import net.fnsco.core.base.PageDTO;
import net.fnsco.service.domain.SysAppMessage;
/**
 * @desc APP信息推送实体操作DAO 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月11日 下午3:42:17
 *
 */
public interface SysAppMessageDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysAppMessage record);

    int insertSelective(SysAppMessage record);

    SysAppMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAppMessage record);

    int updateByPrimaryKey(SysAppMessage record);
    
    /**
     * 条件分页查询
     */
    List<SysAppMessage> queryPageList(PageDTO<SysAppMessage> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(SysAppMessage record);
}