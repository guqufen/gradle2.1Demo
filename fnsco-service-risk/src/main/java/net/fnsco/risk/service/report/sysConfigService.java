package net.fnsco.risk.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.risk.service.sys.dao.ConfigDAO;
import net.fnsco.risk.service.sys.entity.ConfigDO;

@Service
public class sysConfigService {

	@Autowired
	private ConfigDAO configDAO;
	
	public ResultDTO getByType(String type){
		List<ConfigDO> list = configDAO.getByType(type);
		return ResultDTO.success(list);
	}
}
