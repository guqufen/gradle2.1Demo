package net.fnsco.order.service.dao.master;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.core.base.PageDTO;
import net.fnsco.order.api.dto.SysSuggestDTO;
import net.fnsco.order.service.domain.SysSuggest;


public interface SysSuggestDao {
    int insert(SysSuggest record);
    /**
	 * queryPageList:(这里用一句话描述这个方法的作用)条件分页查询
	 *
	 * @param pages
	 * @return    设定文件
	 * @return List<AppUserManageDTO>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
    List<SysSuggestDTO> queryPageList(PageDTO<SysSuggestDTO> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(SysSuggestDTO record);
    /**
     * 根据ID查询出所有关联的数据
     * @param id
     * @return
     */
    SysSuggestDTO queryById(Integer id);
}