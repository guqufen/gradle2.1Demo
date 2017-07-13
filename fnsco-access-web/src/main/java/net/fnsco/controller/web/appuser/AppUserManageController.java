package net.fnsco.controller.web.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.api.appuser.AppUserService;
import net.fnsco.api.dto.AppUserManageDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc APP用户管理控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月13日 下午1:31:13
 */
@Controller
@RequestMapping("/web/appsuser")
public class AppUserManageController extends BaseController {
    
    @Autowired
    private AppUserService AppUserService;
    
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
    public ResultPageDTO<AppUserManageDTO> appUserIndex(AppUserManageDTO sysmsg,Integer currentPageNum,Integer pageSize){
        return AppUserService.queryPageList(sysmsg, currentPageNum, pageSize);
    }
    
}
