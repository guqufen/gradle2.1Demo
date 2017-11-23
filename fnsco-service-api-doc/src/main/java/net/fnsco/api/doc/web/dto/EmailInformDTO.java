package net.fnsco.api.doc.web.dto;

import net.fnsco.core.base.DTO;

public class EmailInformDTO extends DTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 邮件标题
     */
    private String subject;
    
    /**
     * 通知角色类型
     */
    private Integer roleType;
    
    /**
     * 其他通知邮箱
     */
    private String otherSubject;
    
    /**
     * 内容
     */
    private String content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getOtherSubject() {
		return otherSubject;
	}

	public void setOtherSubject(String otherSubject) {
		this.otherSubject = otherSubject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}
