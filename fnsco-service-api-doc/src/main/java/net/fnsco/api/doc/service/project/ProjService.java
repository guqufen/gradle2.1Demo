package net.fnsco.api.doc.service.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.project.dao.ProjDAO;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.core.base.BaseService;

/**
 * @desc 项目基本信息service
 * @author   hjt
 *
 */
@Service
public class ProjService extends BaseService{
	@Autowired
	private ProjDAO projDAO;
	
    public void add(ProjDO projDO) {
    	projDAO.insert(projDO);
    }
}
