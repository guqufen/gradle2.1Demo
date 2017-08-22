package net.fnsco.order.controller.web.sysappmsg;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssUtil;
import net.fnsco.order.api.dto.AppPushMsgInfoDTO;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.service.domain.SysAppMessage;

/**
 * @desc app推送消息控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 上午9:52:53
 */
@Controller
@RequestMapping(value = "/web/msg")
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
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "sys:msg:list" })
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
    @RequestMapping(value = "/doAdd",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = { "sys:msg:save" })
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
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = { "sys:msg:delete" })
    public ResultDTO<String> deleteMsg(Integer id){
        return sysAppMsgService.deleteMsg(id);
    }
    
    /**
     * querySingle:(这里用一句话描述这个方法的作用)查询单条数据详情
     *
     * @param id
     * @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/querySingle",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = { "sys:msg:list" })
    public ResultDTO<SysAppMessage> querySingle(Integer id){
        if(null == id){
            return ResultDTO.fail();
        }
        SysAppMessage message = sysAppMsgService.selectByPrimaryKey(id);
        if(StringUtils.isNotEmpty(message.getImageUrl())){
            String path = message.getImageUrl().substring(message.getImageUrl().indexOf("^")+1);
            String imageURL =  OssUtil.getFileUrl(OssUtil.getHeadBucketName(), path);
            message.setImageUrl(imageURL);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setSendTimeStr(sdf.format(message.getSendTime()));
        return ResultDTO.success(message);
    }
}
