package net.fnsco.api.doc.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.api.doc.comm.RegexUtil;
import net.fnsco.api.doc.service.user.UserBasicService;
import net.fnsco.api.doc.service.user.UserDetailService;
import net.fnsco.api.doc.service.user.entity.UserDetailDO;
import net.fnsco.api.doc.service.vo.UserInfo;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * 
		* <p>Title: 用户信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午3:53:04</p>
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserBasicService  userBasicService;

    @Autowired
    private UserDetailService userDetailService;

    /**
     * 
    		*@name 用户基本信息
    		*@Description  
    		*@CreateDate 2015年7月11日下午2:05:24
     */
    @RequestMapping("/auth/user/setting")
    @ResponseBody
    public ResultDTO getInfo(HttpServletRequest request, Model model) {
        UserInfo temp = (UserInfo) getSessionUser();
        Long userId = temp.getUserId();

        UserDetailDO userInfo = userDetailService.getDetailByUserId(userId);

        return ResultDTO.success(userInfo);
    }

    /**
     * 
    		*@name 更改用户信息
    		*@Description  
    		*@CreateDate 2015年7月11日下午2:05:24
     */
    @RequestMapping("/auth/user/json/update")
    @ResponseBody
    public ResultDTO update(HttpServletRequest request, String nickName) {
        if (Strings.isNullOrEmpty(nickName)) {
            ResultDTO.fail("用户昵称不能为空");
        }
        UserInfo temp = (UserInfo) getSessionUser();
        Long userId = temp.getUserId();
        UserDetailDO userDetail = userDetailService.getByUserId(userId);
        userDetail.setNickName(nickName);
        userDetail.setUserId(userId);
        userDetailService.update(userDetail);

        //更新缓存中的用户信息
        //UserInfo userInfo = getUserInfo(request);
        //userInfo.setNickName(nickName);
        //saveUserInfo(request, userInfo);

        return ResultDTO.success();
    }

    /**
     * 
    		*@name 更改密码
    		*@Description  
    		*@CreateDate 2015年8月6日下午5:14:18
     */
    @RequestMapping(value = "/auth/user/json/updatePasswd", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO updatePasswd(HttpServletRequest request, String oldPasswd, String newPasswd) {
        if (Strings.isNullOrEmpty(oldPasswd)) {
            ResultDTO.fail("原密码不能为空");
        }
        if (Strings.isNullOrEmpty(newPasswd)) {
            ResultDTO.fail("新密码不能为空");
        }

        UserInfo temp = (UserInfo) getSessionUser();
        Long userId = temp.getUserId();
        userBasicService.updatePasswd(userId, oldPasswd, newPasswd);

        return ResultDTO.success();
    }

    /**
     * 
    		*@name 发送更改邮箱授权码
    		*@Description  
    		*@CreateDate 2015年8月22日下午2:58:59
     */
    @RequestMapping("/auth/user/json/sendUpdateEmailCode")
    @ResponseBody
    public ResultDTO sendUpdateEmailCode(HttpServletRequest request, String passwd, String email) {
        if (!RegexUtil.isEmail(email)) {
            ResultDTO.fail("邮箱格式不正确");
        }
        UserInfo temp = (UserInfo) getSessionUser();
        Long userId = temp.getUserId();
        userBasicService.sendUpdateEmailCode(userId, passwd, email);

        return ResultDTO.success();
    }

    /**
     * 
    		*@name 更改邮箱
    		*@Description  
    		*@CreateDate 2015年8月22日下午2:58:59
     */
    @RequestMapping("/pass/user/updateEmail")
    @ResponseBody
    public ResultDTO updateEmail(String code) {
        if (Strings.isNullOrEmpty(code)) {
            ResultDTO.fail("授权码不能为空");
        }
        userBasicService.updateEmail(code);

        return ResultDTO.success();
    }

}
