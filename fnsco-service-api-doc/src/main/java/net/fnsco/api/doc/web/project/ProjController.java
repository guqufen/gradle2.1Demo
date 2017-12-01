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
import net.fnsco.api.doc.service.project.ProjService;
import net.fnsco.api.doc.service.project.entity.EmailDO;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.api.doc.service.user.dao.UserBasicDAO;
import net.fnsco.api.doc.service.vo.UserInfo;
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
@Api(value = "/web", tags = { "项目管理" })
public class ProjController extends BaseController{
    
    @Autowired
    private ProjService projService;
    @Autowired
    private UserBasicDAO userBasicDAO;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MailService mailService;
    
    @ApiOperation(value = "分页查询项目信息", notes = "分页查询项目信息")
    @RequestMapping(value = "/queryProj", method = RequestMethod.GET)
    @ResponseBody
    public ResultPageDTO<ProjDO> queryProj(ProjDO projDO, Integer currentPageNum, Integer pageSize) {
        return projService.queryProj(projDO, currentPageNum, pageSize);
    }
   
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增项目", notes = "新增项目")
    public ResultDTO addProject(ProjDO projDO){
    	String filePath = request.getSession().getServletContext().getRealPath("");
    	//获取当前登录的用户
    	UserInfo user = (UserInfo) getSessionUser();
    	long userId = userBasicDAO.queryUserIdByEmail(user.getEmail());
    	projDO.setUserId(userId);
    	projDO.setCreateDate(new Date());
    	projDO.setModifyDate(new Date());
    	int i =projService.add(projDO);
    	if(i<1) {
    		return ResultDTO.fail();
    	}
    	boolean succsss = projService.exportJson(filePath,"proj"+projDO.getId(),projDO.getJsonStr());
    	if(!succsss){
            return ResultDTO.fail();
        }
    	String url="jsonTxt/"+"proj"+projDO.getId()+".json";
    	ProjDO proj = new ProjDO();
    	proj.setId(projDO.getId());
    	proj.setUrl(url);
    	int m = projService.update(proj);
    	if(i<m) {
    		return ResultDTO.fail();
    	}
    	EmailDO	emailInform  = emailService.queryEmailById(projDO.getEmailId());
    	List<String> emailList = new ArrayList<>();
    	String email = emailInform.getRoleType();
    	if(!Strings.isNullOrEmpty(email)) {
    		String a[] = email.split(";"); 
    		for(int j=0;j<a.length;j++) {
    			emailList.add(a[j]);
    		}
    		String otherEmail = emailInform.getOtherSubject();
    		if(!Strings.isNullOrEmpty(otherEmail)) {
    			String b[] = otherEmail.split(";"); 
        		for(int k=0;k<b.length;k++) {
        			emailList.add(b[k]);
        		}
    		}
    	}
    	MailMsg mailMsg = new MailMsg();
    	mailMsg.setSubject(emailInform.getSubject());
    	mailMsg.setContent(emailInform.getContent());
    	mailMsg.setType(MailMsgType.text);
    	mailService.sendMail(emailList, mailMsg);
        return ResultDTO.success();
    }
    
    @ApiOperation(value = "修改项目", notes = "修改项目")
    @RequestMapping(value = "/modifProjById", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO modifProjById(ProjDO projDO) {
    	String filePath = request.getSession().getServletContext().getRealPath("");
    	//获取当前登录的用户
    	UserInfo user = (UserInfo) getSessionUser();
    	long userId = userBasicDAO.queryUserIdByEmail(user.getEmail());
    	projDO.setUserId(userId);
    	projDO.setModifyDate(new Date());
    	boolean succsss = projService.modifProj(filePath,projDO);
    	if(!succsss){
            return ResultDTO.fail();
        }
    	EmailDO	emailInform  = emailService.queryEmailById(projDO.getEmailId());
    	List<String> emailList = new ArrayList<>();
    	String email = emailInform.getRoleType();
    	if(!Strings.isNullOrEmpty(email)) {
    		String a[] = email.split(";"); 
    		for(int j=0;j<a.length;j++) {
    			emailList.add(a[j]);
    		}
    		String otherEmail = emailInform.getOtherSubject();
    		if(!Strings.isNullOrEmpty(otherEmail)) {
    			String b[] = otherEmail.split(";"); 
        		for(int k=0;k<b.length;k++) {
        			emailList.add(b[k]);
        		}
    		}
    	}
    	MailMsg mailMsg = new MailMsg();
    	mailMsg.setSubject(emailInform.getSubject());
    	mailMsg.setContent(emailInform.getContent());
    	mailMsg.setType(MailMsgType.text);
    	mailService.sendMail(emailList, mailMsg);
    	return ResultDTO.success();
    }
    
    @ApiOperation(value = "查询项目详情", notes = "查询项目详情")
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<ProjDO> queryDetailById(Integer id) {
    	String filePath = request.getSession().getServletContext().getRealPath("");
    	ProjDO proj = projService.queryById(filePath,id);
    	return ResultDTO.success(proj);
    }
}
