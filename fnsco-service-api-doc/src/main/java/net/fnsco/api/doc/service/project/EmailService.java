package net.fnsco.api.doc.service.project;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.project.dao.EmailDAO;
import net.fnsco.api.doc.service.project.entity.EmailDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class EmailService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private EmailDAO emailDAO;

 // 分页
 public ResultPageDTO<EmailDO> page(EmailDO email, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询EmailService.page, email=" + email.toString());
     List<EmailDO> pageList = this.emailDAO.pageList(email, pageNum, pageSize);
     Integer count = this.emailDAO.pageListCount(email);
   ResultPageDTO<EmailDO> pager =  new ResultPageDTO<EmailDO>(count,pageList);
     return pager;
 }
 
//查询所有模板
 public List<EmailDO> queryEmail() {
	 return this.emailDAO.getEmailList();
 }
 
//查询所有模板
public EmailDO queryEmailById(Integer id) {
	 return this.emailDAO.getById(id);
}

 // 添加
 public void doAdd (EmailDO email,int loginUserId) {
     logger.info("开始添加EmailService.add,email=" + email.toString());
     emailDAO.insert(email);
 }

 // 修改
 public Integer doUpdate (EmailDO email,Integer loginUserId) {
     logger.info("开始修改EmailService.update,email=" + email.toString());
     return this.emailDAO.update(email);
 }

 // 删除
 public void doDeleteById (Integer id,Integer loginUserId) {
     logger.info("开始删除EmailService.delete,email=" + loginUserId);
     emailDAO.deleteById(id);
 }

 // 查询
 public EmailDO doQueryById (Integer id) {
     EmailDO obj = this.emailDAO.getById(id);
     return obj;
 }
}