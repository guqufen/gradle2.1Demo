package net.fnsco.api.doc.service.project;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.project.dao.ProjDAO;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.core.base.BaseService;

/**
 * @desc 查询项目菜单service
 * @author   hjt
 *
 */
@Service
public class ProjectMenuistService extends BaseService{
	@Autowired
	private ProjDAO projDAO;
	
    public List<ProjDO> queryMenuist() {
    	return projDAO.queryMenuist();
    }
}
