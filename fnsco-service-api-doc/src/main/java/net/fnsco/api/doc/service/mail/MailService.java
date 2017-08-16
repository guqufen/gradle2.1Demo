package net.fnsco.api.doc.service.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.api.doc.service.vo.MailCfg;
import net.fnsco.core.base.BaseService;

/**
 * 
		* <p>Title: 采用apache-commons-mail发送</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午10:07:16</p>
 */
@Service
public class MailService extends BaseService {

    /** 邮件发送器 */
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment    env;

    public void sendMail(String toEmail, MailMsg mailMsg) {
        sendMail(env.getProperty(MailCfg.DEFAULT_FROM_EMAIL), env.getProperty(MailCfg.DEFAULT_FROM_NAME), toEmail, mailMsg);
    }

    public void sendMail(List<String> toEmailList, MailMsg mailMsg) {
        sendMail(env.getProperty(MailCfg.DEFAULT_FROM_EMAIL), env.getProperty(MailCfg.DEFAULT_FROM_NAME), toEmailList, mailMsg);
    }

    public void sendMail(String fromEmail, String fromName, String toEmail, MailMsg mailMsg) {
        List<String> toEmailList = new ArrayList<String>();
        toEmailList.add(toEmail);
        sendMail(fromEmail, fromName, toEmailList, mailMsg);
    }

    public void sendMail(String fromEmail, String fromName, List<String> toEmailList, MailMsg mailMsg) {
        switch (mailMsg.getType()) {
            case text:
                sendTextMail(fromEmail, fromName, toEmailList, mailMsg);
                break;

            case html:
                sendHtmlMail(fromEmail, fromName, toEmailList, mailMsg);
                break;

            case multi:
                sendMultiMail(fromEmail, fromName, toEmailList, mailMsg);
                break;

            default:
                break;
        }
    }

    //发送文本邮件
    private void sendTextMail(String fromEmail, String fromName, List<String> toEmailList, MailMsg mailMsg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);//发送者.
        String[] toStrs = new String[0];
        List<String> toList = toEmailList;
        if (null != toList) {
            toStrs = new String[toList.size()];
            toList.toArray(toStrs);
        }
        message.setTo(toStrs);
        message.setSubject(mailMsg.getSubject());//邮件主题.
        message.setText(mailMsg.getContent());//邮件内容.
        mailSender.send(message);//发送邮件
    }

    //发送html邮件
    private void sendHtmlMail(String fromEmail, String fromName, List<String> toEmailList, MailMsg mailMsg) {
        try {
            //String mailContent = VelocityUtil.render(mailBean.getMailVmName(),
            //mailBean.getMailContext());
            String mailContent = mailMsg.getContent();
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true, "UTF-8");
            mimeMessageHelper.setFrom(fromEmail, fromName);
            mimeMessageHelper.setSubject(mailMsg.getSubject());
            String[] toStrs = new String[0];
            List<String> toList = toEmailList;
            if (null != toList) {
                toStrs = new String[toList.size()];
                toList.toArray(toStrs);
            }
            mimeMessageHelper.setTo(toStrs);
            //                        String[] ccStrs = new String[0];
            //                        List<String> ccList =toEmailList;
            //                        if (null != ccList) {
            //                            ccStrs = new String[ccList.size()];
            //                            ccList.toArray(ccStrs);
            //                        }
            //            mimeMessageHelper.setCc(ccStrs);
            mimeMessageHelper.setText(mailContent, true);

            mailSender.send(msg);
            logger.info("发送邮件成功" + toEmailList.toString());
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
            throw new RuntimeException("发送邮件失败", e);
        }
    }

    //发送复合邮件
    private void sendMultiMail(String fromEmail, String fromName, List<String> toEmailList, MailMsg mailMsg) {
        try {
            //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            //基本设置.
            helper.setFrom(fromEmail);//发送者.
            String[] toStrs = new String[0];
            List<String> toList = toEmailList;
            if (null != toList) {
                toStrs = new String[toList.size()];
                toList.toArray(toStrs);
            }
            helper.setTo(toStrs);//接收者.
            helper.setSubject(mailMsg.getSubject());//邮件主题.
            helper.setText(mailMsg.getContent());//邮件内容.
            List<MailAttach> attList = mailMsg.getAttachList();
            //org.springframework.core.io.FileSystemResource下的:
            //附件1,获取文件对象.
            //FileSystemResource file1 = new FileSystemResource(new File("D:/test/head/head1.jpg"));
            //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。

            //helper.addAttachment("头像1.jpg",file1);
            //附件2
            for (MailAttach att : attList) {
                if (!Strings.isNullOrEmpty(att.getPath())) {
                    FileSystemResource file1 = new FileSystemResource(new File(att.getPath()));
                    helper.addAttachment(att.getName(), file1);
                }
                //                if (null != att.getUrl() && !Strings.isNullOrEmpty(att.getUrl().toString())) {
                //                    FileSystemResource file = new FileSystemResource(new File(att.getUrl().toURI()));
                //                    helper.addAttachment(att.getName(), file);
                //                }
            }

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
            throw new RuntimeException("发送邮件失败", e);
        }
    }

    public static InputStream getInputStreamByGet(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将服务器响应的数据流存到本地文件
    public static void saveData(InputStream is, File file) {
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        MailAttach att = new MailAttach();
        att.setUrl(new URL("http://avatar.csdn.net/C/9/7/1_linjx2004.jpg"));
        InputStream is = getInputStreamByGet(att.getUrl().toString());
        //String filePath = env.getProperty("fileUpload.url")
        File file1 = new File("d://temp/aa.jpg");
        saveData(is, file1);
        FileSystemResource file = new FileSystemResource(file1);
        System.out.println(file);
        //MessageFormat.format(pattern, arguments)
    }

    private void validate(MailMsg mailBean) {

        //        StringUtil.notNullOrEmpty(mailBean, "邮件对象为空");
        //        StringUtil.notNullOrEmpty(mailBean.getFrom(), "邮件发送人为空");
        //        StringUtil.notNullOrEmpty(mailBean.getFromName(), "邮件发送人姓名为空");
        //        StringUtil.notNullOrEmpty(mailBean.getToList(), "邮件接收人列表为空");
        //        StringUtil.notNullOrEmpty(mailBean.getSubject(), "邮件主题为空");
        //        StringUtil.notNullOrEmpty(mailBean.getMailContent(), "邮件内容不能为空");

    }
}