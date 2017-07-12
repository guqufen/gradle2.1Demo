package net.fnsco.controller.web.sysappmsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.api.sysappmsg.SysAppMsgService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.SysAppMessage;

/**
 * @desc app推送消息控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 上午9:52:53
 */
@Controller
@RequestMapping("/web/msg")
public class SysAppMsgController extends BaseController {
    
    @Autowired
    private SysAppMsgService sysAppMsgService;
    
    /**
     * appMsgIndex:(这里用一句话描述这个方法的作用) 分页查询
     *
     * @param sysmsg
     * @param currentPageNum
     * @param pageSize
     * @return    设定文件
     * @return ResultPageDTO<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/query")
    @ResponseBody
    public ResultPageDTO<SysAppMessage> appMsgIndex(SysAppMessage sysmsg,Integer currentPageNum,Integer pageSize){
        logger.info("查询推送消息列表");
        return sysAppMsgService.queryPageList(sysmsg, currentPageNum, pageSize);
    }
    
    /**
     * toAddMsg:(这里用一句话描述这个方法的作用) 新增加推送消息
     *
     * @param sysmsg
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public ResultDTO<String> toAddMsg(AppPushMsgInfoDTO sysmsg){
        return sysAppMsgService.doAddMsg(sysmsg);
    }
    
    /**
     * deleteMsg:(这里用一句话描述这个方法的作用) 删除单条数据
     *
     * @param id
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultDTO<String> deleteMsg(Integer id){
        return null;
    }
}
