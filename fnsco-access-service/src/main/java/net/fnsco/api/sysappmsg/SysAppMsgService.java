package net.fnsco.api.sysappmsg;

import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.SysAppMessage;

/**
 * @desc  APP推送消息服务类接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 上午9:44:49
 */

public interface SysAppMsgService {
    
    int deleteByPrimaryKey(Integer id);

    int insert(SysAppMessage record);

    int insertSelective(SysAppMessage record);

    SysAppMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAppMessage record);

    int updateByPrimaryKey(SysAppMessage record);
    /**
     * queryPageList:(这里用一句话描述这个方法的作用) 分页条件查询
     *
     * @param record
     * @param currentPageNum
     * @param perPageSize
     * @return    设定文件
     * @return ResultPageDTO<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultPageDTO<SysAppMessage> queryPageList(SysAppMessage record,int currentPageNum, int perPageSize);
    /**
     * doAddMsg:(这里用一句话描述这个方法的作用) 新增加发布推送消息
     * @param record
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> doAddMsg(AppPushMsgInfoDTO record);
    /**
     * 
     * deleteMsg:(这里用一句话描述这个方法的作用) 删除单条数据
     * @param id
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> deleteMsg(Integer id);
}
