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
import net.fnsco.api.doc.service.project.EmailInformService;
import net.fnsco.api.doc.service.user.UserBasicService;
import net.fnsco.api.doc.web.dto.EmailInformDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

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
    private EmailInformService emailInformService;
    
    @Autowired
    private UserBasicService userBasicService;
    
    @Autowired
    private MailService mailService;
   
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
