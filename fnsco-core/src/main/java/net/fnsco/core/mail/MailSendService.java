package net.fnsco.core.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.TaskUtils;

/**
 * @desc 邮件发送service类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年1月23日 下午3:52:36
 */
@Service
public class MailSendService extends BaseService{
	
	@Autowired
	private Environment env;
	/**
	 * sendSystemMail:(发送系统邮件，发送方为公司)
	 *
	 * @param  @param params    设定文件
	 * @return void    DOM对象
	 * @author tangliang
	 * @date   2018年1月23日 下午4:26:16
	 */
	public void sendSystemMail(MailSendParams params,JavaMailSender mailSender) {
		params.setNick("杭州法奈昇科技有限公司");
		sendMail(params,mailSender);
	}
	
	/**
	 * sendMail:(发送邮件)
	 *
	 * @param  MailSendParams params    参数MailSendParams中的sendText属性 可以为文本，可以为html字符串 不能为文件类型
	 * @return void    DOM对象
	 * @author tangliang
	 * @date   2018年1月23日 下午4:22:11
	 */
	public void sendMail(MailSendParams params,JavaMailSender mailSender) {
		
		if(Strings.isNullOrEmpty(params.getToAddress())) {
			logger.error("发送邮件参数错误!"+params);
			return ;
		}
		/**
		 * 异步发送邮件
		 */
		TaskUtils.execAsyn(new Runnable() {
			@Override
			public void run() {
				MimeMessage message = null;
				String nick = "";
				if(!Strings.isNullOrEmpty(params.getNick())) {
					try {
						nick = javax.mail.internet.MimeUtility.encodeText(params.getNick());
					} catch (Exception e) {
						logger.error("发送短信nick编码错误!", e);
					}
				}
				
				try {
					message = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
					helper.setFrom(new InternetAddress(nick + " <" + env.getProperty("spring.mail.username") + ">"));
					helper.setTo(params.getToAddress());
					helper.setSubject(params.getSendSubject());
					helper.setText(params.getSendText(), true);
				} catch (MessagingException e) {
					logger.error("邮件发送失败");
				}
				mailSender.send(message);
				logger.info("邮件发送成功!发送内容:"+params.toString());
			}
		});
    }
}
