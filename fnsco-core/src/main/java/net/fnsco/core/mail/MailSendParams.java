package net.fnsco.core.mail;

import com.alibaba.fastjson.JSON;

/**
 * @desc 发送邮件参数类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年1月23日 下午4:00:30
 */

public class MailSendParams {
	
	private String nick;//发送者称呼
	
	private String toTarget;//收件人
	
	private String toAddress;//发送目标邮箱地址
	
	private String sendText;//发送内容
	
	private String sendSubject;//发送邮件主题
	
	/**
	 * sendSubject
	 *
	 * @return  the sendSubject
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getSendSubject() {
		return sendSubject;
	}

	/**
	 * sendSubject
	 *
	 * @param   sendSubject    the sendSubject to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setSendSubject(String sendSubject) {
		this.sendSubject = sendSubject;
	}

	/**
	 * nick
	 *
	 * @return  the nick
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getNick() {
		return nick;
	}

	/**
	 * nick
	 *
	 * @param   nick    the nick to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * toTarget
	 *
	 * @return  the toTarget
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getToTarget() {
		return toTarget;
	}

	/**
	 * toTarget
	 *
	 * @param   toTarget    the toTarget to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setToTarget(String toTarget) {
		this.toTarget = toTarget;
	}

	/**
	 * toAddress
	 *
	 * @return  the toAddress
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * toAddress
	 *
	 * @param   toAddress    the toAddress to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * sendText
	 *
	 * @return  the sendText
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getSendText() {
		return sendText;
	}

	/**
	 * sendText
	 *
	 * @param   sendText    the sendText to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setSendText(String sendText) {
		this.sendText = sendText;
	}
	
	@Override
	public String toString() {
		
		// TODO Auto-generated method stub
		return JSON.toJSONString(this);
		
	}
	
	@Override
	public int hashCode() {
		
		// TODO Auto-generated method stub
		return toTarget.hashCode()+toAddress.hashCode();
		
	}
}
