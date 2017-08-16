package net.fnsco.api.doc.service.mail;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

import net.fnsco.api.doc.comm.CfgConstants;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.StringUtil;
import net.fnsco.core.utils.TaskUtils;

/**
 * 
		* <p>Title: 邮件发送工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午3:12:40</p>
 */
@Service
public class MailUtilService {
    @Autowired
    private MailService mailService;
    @Autowired
    private Environment env;

    /**
     * 
    		*@name 发送邮件，使用service账号
    		*@Description  
    		*@CreateDate 2015年8月22日下午3:14:03
     */
    public void send(final String toEmail, String subject, String content) {
        final MailMsg mailMsg = new MailMsg();
        mailMsg.setSubject(subject);
        mailMsg.setType(MailMsgType.html);
        mailMsg.setContent(content);

        TaskUtils.execAsyn(new Runnable() {
            @Override
            public void run() {
                mailService.sendMail(toEmail, mailMsg);
            }
        });
    }

    /**
     * 
    		*@name 发送邮件，使用service账号
    		*@Description  
    		*@CreateDate 2015年8月22日下午3:14:03
     */
    public void send(final String toEmail, String subject, String tmplName, Map<String, Object> model) {
        if (model == null) {
            model = Maps.newHashMap();
        }
        model.put("webName", env.getProperty(CfgConstants.WEB_NAME));
        model.put("webSite", env.getProperty(CfgConstants.WEB_SITE));
        model.put("webEmail", env.getProperty(CfgConstants.WEB_SERVICE_EMAIL));
        model.put("webTel", env.getProperty(CfgConstants.WEB_SERVICE_TEL));
        model.put("sendDate", DateUtils.dateFormatToStr(new Date()));
        String tmpl = loadTmpl(tmplName);//"mail-tmpl/email-update.ftl");
        String content = StringUtil.process(tmpl, model);
        final MailMsg mailMsg = new MailMsg();
        mailMsg.setSubject(subject);
        mailMsg.setType(MailMsgType.html);
        mailMsg.setContent(content);

        TaskUtils.execAsyn(new Runnable() {
            @Override
            public void run() {
                mailService.sendMail(toEmail, mailMsg);
            }
        });
    }

    /**
     * 
    		*@name 发送变更通知，使用notice账号
    		*@Description  
    		*@CreateDate 2015年10月24日下午4:33:09
     */
    public void sendNotice(final List<String> emailList, String subject, String content) {
        if (CollectionUtils.isEmpty(emailList)) {
            return;
        }

        final MailMsg mailMsg = new MailMsg();
        mailMsg.setSubject(subject);
        mailMsg.setContent(content);
        mailMsg.setType(MailMsgType.multi);
        /**
         * 通知发送者邮箱
         */
        final String NOTICE_FROM_EMAIL = env.getProperty("notice.from.email");

        /**
         * 通知发送者名称
         */
        final String NOTICE_FROM_NAME = env.getProperty("notice.from.name");

        TaskUtils.execAsyn(new Runnable() {
            @Override
            public void run() {
                mailService.sendMail(NOTICE_FROM_EMAIL, NOTICE_FROM_NAME, emailList, mailMsg);
            }
        });
    }

    //加载模板内容
    private static String loadTmpl(String fileName) {
        Resource fileRource = new ClassPathResource(fileName);
        try {
            return IOUtils.toString(fileRource.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
