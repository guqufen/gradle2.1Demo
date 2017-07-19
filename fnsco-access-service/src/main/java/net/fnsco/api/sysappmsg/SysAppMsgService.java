package net.fnsco.api.sysappmsg;

import java.util.List;

import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.api.dto.PushMsgInfoDTO;
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
     * queryExecuteData:(这里用一句话描述这个方法的作用) 查询待执行任务数据
     *
     * @return    设定文件
     * @return List<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysAppMessage> queryExecuteData();
    /**
     * queryListByCondition:(这里用一句话描述这个方法的作用)根据条件查询
     *
     * @param record
     * @return    设定文件
     * @return List<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysAppMessage> queryListByCondition(SysAppMessage record);
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
    /**
     * queryMsgList:(这里用一句话描述这个方法的作用) APP获取推送消息列表
     *
     * @param userId
     * @param hasRead
     * @return    设定文件
     * @return ResultDTO<List<AppPushMsgInfoDTO>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<ResultPageDTO<AppPushMsgInfoDTO>> queryMsgList(Integer userId,boolean hasRead,Integer phoneType,Integer currentPageNum,Integer perPageSize);
    /**
     * pushMerChantMsg:(这里用一句话描述这个方法的作用) 店员加入商家通知
     *
     * @param innerCode
     * @param userId    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    void pushMerChantMsg(String innerCode,Integer userId);
    /**
     * queryNewsCount:(这里用一句话描述这个方法的作用)查询新消息条数
     *
     * @param userId
     * @param phoneType
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<PushMsgInfoDTO> queryNewsCount(Integer userId,boolean hasRead,Integer phoneType);
    /**
     * readPushMsg:(这里用一句话描述这个方法的作用)记录用户读取时间
     *
     * @param userId
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    void readPushMsg(Integer userId);
    
    /**
     * queryList:(这里用一句话描述这个方法的作用)
     *
     * @param ids
     * @return    设定文件
     * @return ResultDTO<AppPushMsgInfoDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<List<AppPushMsgInfoDTO>> queryListByIds(List<Integer> ids);
}
