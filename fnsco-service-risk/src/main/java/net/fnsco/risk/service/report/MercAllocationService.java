package net.fnsco.risk.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.dao.MercAllocationDAO;
import net.fnsco.risk.service.report.dao.UserMercRelDAO;
import net.fnsco.risk.service.report.entity.UserMercRelDO;
import net.fnsco.risk.service.sys.entity.MerAllocationDO;

@Service
public class MercAllocationService extends BaseService{

	@Autowired
	private MercAllocationDAO merAllocationDAO;
	
	@Autowired
	private UserMercRelDAO userMercRelDAO;
	
	
	public ResultPageDTO getAddMerData(MerAllocationDO merAllocationDO, Integer agentId, Integer pageNum,
			Integer pageSize) {
		List<MerAllocationDO> pageList = merAllocationDAO.pageAddMerDataList(merAllocationDO, agentId, pageNum,
				pageSize);

		Integer count = merAllocationDAO.pageAddMerDataCount(merAllocationDO, agentId);

		ResultPageDTO<MerAllocationDO> pager = new ResultPageDTO<MerAllocationDO>(count, pageList);
		return pager;
	}
	
	/**
	 * 获取商户分配的数据
	 * @param agentId：代理商ID
	 * @param type：操作类型：0-详情(绑定过)；1-设置(未绑定过)
	 * @param pageNum：页码
	 * @param pageSize：每页数据条数
	 * @return
	 */
	public ResultPageDTO getMerData(MerAllocationDO merAllocationDO, Integer agentId, Integer pageNum, Integer pageSize){

		List<MerAllocationDO> pageList = merAllocationDAO.pageMerDataList(merAllocationDO, agentId,  pageNum, pageSize);
		
		Integer count = merAllocationDAO.pageMerDataCount(merAllocationDO, agentId);
		
        ResultPageDTO<MerAllocationDO> pager = new ResultPageDTO<MerAllocationDO>(count, pageList);
		return pager;
	}
	
	/**
	 * 批量绑定商户和代理商关系
	 * @param agentId
	 * @param innerCode
	 * @return
	 */
	public ResultDTO addMerAllo(Integer agentId, String[] innerCode){

		for (int i = 0; i < innerCode.length; i++) {
			UserMercRelDO userMercRel = new UserMercRelDO();
			userMercRel.setAgentId(agentId);
			userMercRel.setInnerCode(innerCode[i]);
			userMercRelDAO.insert(userMercRel);
		}
		return ResultDTO.success();
	}
	
	/**
	 * 解除商户和代理商关系
	 * @param agentId
	 * @param innerCode
	 * @return
	 */
	public ResultDTO delMerAllo(Integer agentId, String[] innerCode) {
		for (int i = 0; i < innerCode.length; i++) {
			UserMercRelDO userMercRel = new UserMercRelDO();
			userMercRel.setAgentId(agentId);
			userMercRel.setInnerCode(innerCode[i]);
			userMercRelDAO.deleteByagentInnerId(userMercRel);
		}

		return ResultDTO.success();
	}
	
	/**
	 * 通过商户名称模糊查找内部商户号<集合>
	 * @param merName:商户名称
	 * @return
	 */
	public List<String> getByMerName(String merName){
		List<String> list = merAllocationDAO.getByMerName(merName);
		return list;
	}
	
	public List<Integer> getByInnerCodeList(List<String> merCodeList){
		List<Integer> list = userMercRelDAO.getByInnerCodeList(merCodeList);
		return list;
	}
}
