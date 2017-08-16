package net.fnsco.api.doc.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.comm.RegexUtil;
import net.fnsco.api.doc.service.user.RegistService;
import net.fnsco.api.doc.service.user.UserBasicService;
import net.fnsco.api.doc.service.vo.RegistParamInfo;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * 
		* <p>Title: 新用户注册处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:04:09</p>
 */
@Controller
@RequestMapping("/web/regist")
@Api(value = "/web/regist", tags = { "用户注册" })
public class RegistController extends BaseController {
    @Autowired
    private RegistService    registService;

    @Autowired
    private UserBasicService userBasicService;

    /**
     * 
    		*@name 通过邮箱注册
    		*@Description  
    		*@CreateDate 2015年8月8日下午2:20:03
     */
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResultDTO regist(@RequestParam String loginName, @RequestParam String passwd, @RequestParam String nickName) {
        if (Strings.isNullOrEmpty(loginName)) {
            ResultDTO.fail("登录名不能为空");
        }
        if (Strings.isNullOrEmpty(passwd)) {
            ResultDTO.fail("密码不能为空");
        }
        if (Strings.isNullOrEmpty(nickName)) {
           ResultDTO.fail("昵称不能为空");
        }
        //目前默认为邮箱注册
        RegistParamInfo paramInfo = new RegistParamInfo();
        paramInfo.setLoginType("邮箱");
        paramInfo.setEmail(loginName);
        paramInfo.setNickName(nickName);
        paramInfo.setPassword(passwd);
        paramInfo.setRegistIp(getIp());
//        if (validCode.equals("")) {
//            return ResultDTO.fail("验证码错误");
//        }
        ResultDTO result = registService.registByEmail(paramInfo);
        if (!result.isSuccess()){
            return result;
        }
        return ResultDTO.success(loginName);
    }

    /**
     * 
    		*@name 重新发送激活授权码
    		*@Description  
    		*@CreateDate 2015年8月6日下午5:14:18
     */
    @RequestMapping(value = "/json/sendActiveCode", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "重新发送激活授权码", notes = "重新发送激活授权码")
    public ResultDTO sendActiveCode(@RequestParam String email) {
        if (RegexUtil.isEmail(email)) {
            ResultDTO.fail("邮箱格式错误");
        }
        registService.sendActiveCode(email);
        return ResultDTO.success();
    }

    /**
     * 
    		*@name 激活账号
    		*@Description  
    		*@CreateDate 2015年8月23日下午5:54:19
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ApiOperation(value = "激活账号", notes = "激活账号")
    public String active(@RequestParam String code) {
        if (Strings.isNullOrEmpty(code)) {
            ResultDTO.fail("激活码不能为空");
        }
        ResultDTO result = registService.activeByEmail(code);
        if(!result.isSuccess()){
            ResultDTO.fail("激活码不能为空");
        }
        return "redirect:/login.html";
    }

    /**
     * 
     *@name 判断邮箱是否已存在
     *@Description  
     *@CreateDate 2015年8月24日下午6:36:46
     */
    @RequestMapping(value = "/validEmail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "判断邮箱是否已存在", notes = "判断邮箱是否已存在")
    public ResultDTO isEmailExist(@RequestParam String email) {
        boolean result = userBasicService.isEmailExist(email, null);
        return ResultDTO.success(result);
    }
}
