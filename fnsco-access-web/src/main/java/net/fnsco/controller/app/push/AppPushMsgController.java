package net.fnsco.controller.app.push;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.api.dto.PushMsgInfoDTO;
import net.fnsco.api.sysappmsg.SysAppMsgService;
import net.fnsco.controller.app.jo.AppPushMsgJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
/**
 * @desc 推送消息APP接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 下午1:10:19
 */
@RestController
@RequestMapping(value = "/app/push", method = RequestMethod.POST)
public class AppPushMsgController extends BaseController{
    
    @Autowired
    private SysAppMsgService sysAppMsgService;
    /**
     * list:(这里用一句话描述这个方法的作用) 获取消息列表
     * @param appPushMsgJO 
     * @return    设定文件
     * @return ResultDTO<List<AppPushMsgInfoDTO>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/list")
    @ApiOperation(value = "获取推送消息列表")
    public ResultDTO<ResultPageDTO<AppPushMsgInfoDTO>> list(@RequestBody AppPushMsgJO appPushMsgJO){
        return sysAppMsgService.queryMsgList(appPushMsgJO.getUserId(), appPushMsgJO.isHasRead(),appPushMsgJO.getPhoneType(),appPushMsgJO.getCurrentPageNum(),appPushMsgJO.getPerPageSize());
    }
    /**
     * queryNewsCount:(这里用一句话描述这个方法的作用) 获取新消息数据条数
     *
     * @param appPushMsgJO
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/newsCount")
    @ApiOperation(value = "获取推送未读消息条数")
    public ResultDTO<PushMsgInfoDTO> queryNewsCount(@RequestBody AppPushMsgJO appPushMsgJO){
        return sysAppMsgService.queryNewsCount(appPushMsgJO.getUserId(), appPushMsgJO.isHasRead(),appPushMsgJO.getPhoneType());
    }
    
    /**
     * readPushMsg:(这里用一句话描述这个方法的作用)记录用户已经读取
     *
     * @param appPushMsgJO
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/read")
    @ApiOperation(value = "记录用户读取消息时间")
    public ResultDTO<String> readPushMsg(@RequestBody AppPushMsgJO appPushMsgJO){
        if(null == appPushMsgJO.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        sysAppMsgService.readPushMsg(appPushMsgJO.getUserId());
        return ResultDTO.success();
    }
    /**
     * queryList:(这里用一句话描述这个方法的作用)根据ID查询结果
     *
     * @param appPushMsgJO
     * @return    设定文件
     * @return ResultDTO<List<AppPushMsgInfoDTO>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/query")
    @ApiOperation(value = "根据ID获取推送消息列表")
    public ResultDTO<List<AppPushMsgInfoDTO>> queryList(@RequestBody AppPushMsgJO appPushMsgJO){
        return sysAppMsgService.queryListByIds(appPushMsgJO.getMsgIds());
    }
    
}
