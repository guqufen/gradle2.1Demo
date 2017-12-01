package net.fnsco.api.doc.web.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.service.mail.MailMsg;
import net.fnsco.api.doc.service.mail.MailMsgType;
import net.fnsco.api.doc.service.mail.MailService;
import net.fnsco.api.doc.service.project.EmailService;
import net.fnsco.api.doc.service.project.entity.EmailDO;
import net.fnsco.api.doc.service.user.UserBasicService;
import net.fnsco.api.doc.service.user.dao.UserBasicDAO;
import net.fnsco.api.doc.service.vo.UserInfo;
import net.fnsco.api.doc.web.dto.EmailInformDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc
 * @author   hjt
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月10日 上午10:22:41
 */
@Controller
@RequestMapping(value = "/web")
@Api(value = "/web", tags = { "变更通知" })
public class EmailInformController extends BaseController{
    
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserBasicService userBasicService;
    @Autowired
    private UserBasicDAO userBasicDAO;
    @Autowired
    private MailService mailService;
   
    @ApiOperation(value = "分页查询邮件模板信息", notes = "分页查询邮件模板信息")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public ResultPageDTO<EmailDO> page(EmailDO email, Integer currentPageNum, Integer pageSize) {
        return emailService.page(email, currentPageNum, pageSize);
    }
    
    @ApiOperation(value = "查询邮件模板信息", notes = "查询邮件模板信息")
    @RequestMapping(value = "/queryEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<List<EmailDO>> queryEmail() {
    	List<EmailDO> list = emailService.queryEmail();
    	return ResultDTO.success(list);
    }
    
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增邮件模板", notes = "新增邮件模板")
    public ResultDTO doAdd (EmailDO email){
    	//获取当前登录的用户
    	UserInfo user = (UserInfo) getSessionUser();
    	long userIdl = userBasicDAO.queryUserIdByEmail(user.getEmail());
    	int userId = (int)userIdl;
    	email.setUserId(userId);
    	email.setCreateDate(new Date());
    	email.setModifyDate(new Date());
    	emailService.doAdd(email,userId);
        return ResultDTO.success();
    }
    
    @ApiOperation(value = "修改邮件模板", notes = "修改邮件模板")
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO doUpdate (EmailDO email) {
    	//获取当前登录的用户
    	UserInfo user = (UserInfo) getSessionUser();
    	long userIdl = userBasicDAO.queryUserIdByEmail(user.getEmail());
    	int userId = (int)userIdl;
    	email.setUserId(userId);
    	email.setModifyDate(new Date());
    	Integer i = emailService.doUpdate(email, userId);
    	if(i<1) {
    		return ResultDTO.fail();
    	}
    	return ResultDTO.success();
    }
    
    @ApiOperation(value = "查询邮件模板详情", notes = "查询邮件模板详情")
    @RequestMapping(value = "/doQueryById", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<EmailDO> doQueryById(Integer id) {
    	EmailDO email = emailService.doQueryById(id);
    	return ResultDTO.success(email);
    }
    
    @ApiOperation(value = "删除邮件模板", notes = "删除邮件模板")
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO doDelete(Integer id) {
    	//获取当前登录的用户
    	UserInfo user = (UserInfo) getSessionUser();
    	long userIdl = userBasicDAO.queryUserIdByEmail(user.getEmail());
    	int userId = (int)userIdl;
    	emailService.doDeleteById(id,userId);
    	return ResultDTO.success();
    }
    
    @RequestMapping(value = "/emailInform", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "接口变更通知", notes = "接口变更通知")
    public ResultDTO emailInform(EmailInformDTO emailInform){
    	List<String> emailList = new ArrayList<>();
    	if(emailInform.getRoleType()==1) {
    		emailList = userBasicService.queryUserBasicEmail();
    	}
    	String otherEmail = emailInform.getOtherSubject();
    	if(!Strings.isNullOrEmpty(otherEmail)) {
    		String a[] = otherEmail.split(";"); 
    		for(int i=0;i<a.length;i++) {
    			emailList.add(a[i]);
    		}
    	}
    	MailMsg mailMsg = new MailMsg();
    	mailMsg.setSubject(emailInform.getSubject());
    	mailMsg.setContent(emailInform.getContent());
    	mailMsg.setType(MailMsgType.text);
    	mailService.sendMail(emailList, mailMsg);
        return ResultDTO.success();
    }
}
