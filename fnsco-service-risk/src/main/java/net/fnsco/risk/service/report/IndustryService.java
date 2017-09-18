package net.fnsco.risk.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.risk.service.sys.dao.IndustryDAO;
import net.fnsco.risk.service.sys.entity.IndustryDO;

@Service
public class IndustryService extends BaseService{

	@Autowired
	private IndustryDAO industryDAO;
	
	/**
	 * 查询所有的数据
	 * @return
	 */
	public ResultDTO queryAll(){
		 List<IndustryDO> list = industryDAO.queryAll();
		 return ResultDTO.success(list);
	}
}
