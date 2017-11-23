package net.fnsco.api.doc.service.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.project.dao.ProjPrivilegeDAO;
import net.fnsco.api.doc.service.project.entity.ProjPrivilegeDO;
import net.fnsco.api.doc.web.dto.EmailInformDTO;
import net.fnsco.core.base.BaseService;

/**
 * @desc 项目基本信息service
 * @author   hjt
 *
 */
@Service
public class EmailInformService extends BaseService{
	@Autowired
	private ProjPrivilegeDAO projPrivilegeDAO;
	
    public void SendOutEmail(EmailInformDTO emailInform) {
    	
    }
}
