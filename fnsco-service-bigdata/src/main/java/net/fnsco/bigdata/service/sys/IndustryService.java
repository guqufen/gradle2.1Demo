package net.fnsco.bigdata.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.service.sys.dao.IndustryDAO;
import net.fnsco.bigdata.service.sys.entity.IndustryDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

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
	
	public ResultPageDTO pageNameList(IndustryDO industryDO,Integer page,Integer rows){
		List<IndustryDO> list = industryDAO.pageNameList(industryDO, page, rows);
		Integer total = industryDAO.pageNameListCount(industryDO);
		ResultPageDTO<IndustryDO> pager = new ResultPageDTO<>(total, list);
		 return pager;
	}
	
	public ResultDTO getById(IndustryDO industryDO){
		IndustryDO industryDO2 = industryDAO.getById(industryDO.getId());
		return ResultDTO.success(industryDO);
	}
}
