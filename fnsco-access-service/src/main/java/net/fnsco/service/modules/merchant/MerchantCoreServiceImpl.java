package net.fnsco.service.modules.merchant;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.api.merchant.MerchantCoreService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.dao.master.MerchantChannelDao;
import net.fnsco.service.dao.master.MerchantContactDao;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.dao.master.MerchantFileDao;
import net.fnsco.service.dao.master.MerchantFileTempDao;
import net.fnsco.service.dao.master.MerchantTerminalDao;
import net.fnsco.service.domain.MerchantChannel;
import net.fnsco.service.domain.MerchantContact;
import net.fnsco.service.domain.MerchantCore;
import net.fnsco.service.domain.MerchantFile;
import net.fnsco.service.domain.MerchantFileTemp;
import net.fnsco.service.domain.MerchantTerminal;

/**
 * @desc 商家基本信息 实现类
 * @author tangliang
 * @date 2017年6月21日 下午2:22:26
 */
@Service
public class MerchantCoreServiceImpl implements MerchantCoreService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MerchantCoreDao merchantCoreDao;
	
	@Autowired
	private MerchantContactDao merchantContactDao;
	
	@Autowired
	private MerchantTerminalDao merchantTerminalDao;
	
	@Autowired
	private MerchantChannelDao merchantChannelDao;
	
	@Autowired
	private MerchantFileDao merchantFileDao;
	
	@Autowired
	private MerchantFileTempDao merchantFileTempDao;
	
	/**
	 * @todo 新增加商家
	 * @author tangliang
	 * @date 2017年6月21日 下午2:23:26
	 * @see net.fnsco.api.merchant.MerchantCoreService#doAdd(net.fnsco.service.domain.MerchantCore, int)
	 */
	@Override
	@Transactional
	public ResultDTO<Integer> doAdd(HttpServletRequest request) {
		logger.info("开始添加MerchantCoreService.add,merchantInfo=" );
		ResultDTO<Integer> result = new ResultDTO<>();
		
		MerchantCore merchantInfo = getParamesFormRequest(request);
		MerchantContact merchantContact = getParamesFormReqCont(request);
		MerchantTerminal merchantTerminal = getParamesFormReqTerm(request);
		MerchantChannel merchantChannel = getParamesFormReqChan(request);
		
		// 判断用户名/手机号是否重复
		int cor = merchantCoreDao.insertSelective(merchantInfo);
		int con =merchantContactDao.insertSelective(merchantContact);
		int ter = merchantTerminalDao.insertSelective(merchantTerminal);
		int chan = merchantChannelDao.insertSelective(merchantChannel);
		if(cor ==1 && con ==1 && ter == 1 && chan == 1){
			fileHander(request);//处理文件
			result.setSuccess("添加商户成功");
		}else
		{
			result.setError("添加失败");
		}	
		return result;
	}
	/**
	 * @todo 条件分页查询
	 * @author tangliang
	 * @date 2017年6月22日 上午11:50:55
	 * @see net.fnsco.api.merchant.MerchantCoreService#queryMerchantCore(net.fnsco.service.domain.MerchantCore)
	 */
	@Override
	public ResultPageDTO<MerchantCore> queryMerchantCore(MerchantCore merchantCore,int currentPageNum,int perPageSize) {
		
		PageDTO<MerchantCore> pages = new PageDTO<>(currentPageNum,perPageSize,merchantCore);
		List<MerchantCore> datas = merchantCoreDao.queryPageList(pages);
		int totalNum = merchantCoreDao.queryTotalByCondition(merchantCore);
		ResultPageDTO<MerchantCore> result = new ResultPageDTO<MerchantCore>(totalNum,datas);
		result.setCurrentPage(currentPageNum);
		
		return result;
	}
	/**
	 * @todo 条件查询
	 * @author tangliang
	 * @date 2017年6月23日 上午10:39:47
	 * @see net.fnsco.api.merchant.MerchantCoreService#queryAllByCondition(net.fnsco.service.domain.MerchantCore)
	 */
	@Override
	public List<MerchantCore> queryAllByCondition(MerchantCore merchantCore) {
		// TODO Auto-generated method stub
		return merchantCoreDao.queryListByCondition(merchantCore);
	}
	
	/**
	 * @todo 根据ID逻辑删除数据
	 * @author tangliang
	 * @date 2017年6月27日 上午10:30:05
	 * @see net.fnsco.api.merchant.MerchantCoreService#deleteByIds(java.lang.Integer[])
	 */
	@Override
	public ResultDTO<Integer> deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		int re = merchantCoreDao.updateStatusByMutipleKey(ids);
		if(re == 1)
		{
			result.setSuccess("删除成功!");
		}else
		{
			result.setError("删除失败");
			logger.error("批量删除失败! ids = "+ ids);
		}	
		return result;
	}
	/**
	 * @todo 根据ID查询出所有关联的数据
	 * @author tangliang
	 * @date 2017年6月27日 上午11:47:07
	 * @see net.fnsco.api.merchant.MerchantCoreService#queryAllById(java.lang.Integer)
	 */
	@Override
	public ResultDTO<MerchantCore> queryAllById(Integer id) {
		// TODO Auto-generated method stub
		ResultDTO<MerchantCore> result = new ResultDTO<MerchantCore>();
		result.setData(merchantCoreDao.queryAllById(id));
		result.setSuccess("查询成功!");
		return result;
	}
	/**
	 * 处理文件信息
	 * @param request
	 */
	private void fileHander(HttpServletRequest request)
	{
		String fileIds = request.getParameter("fileIds");
		if(!StringUtils.isEmpty(fileIds))
		{
			String[] ids  = fileIds.split(",");
			for (String fileId : ids) {
				MerchantFileTemp fileTemp = merchantFileTempDao.selectByPrimaryKey(Integer.valueOf(fileId));
				if(null != fileTemp)
				{
					MerchantFile file = new MerchantFile();
					BeanUtils.copyProperties(fileTemp, file);
					merchantFileDao.insertSelective(file);
					merchantFileTempDao.deleteByPrimaryKey(Integer.valueOf(fileId));
				}	
			}
			
		}	
	}
	
	/**
	 * 在请求中获取该实体参数
	 * @param request
	 * @return
	 */
	private MerchantCore getParamesFormRequest(HttpServletRequest request){
		MerchantCore merchantCore = new MerchantCore();
		merchantCore.setInnerCode(request.getParameter("innerCode"));
		merchantCore.setMerName(request.getParameter("name"));
		merchantCore.setAbbreviation(request.getParameter("abbreviation"));
		merchantCore.setEnName(request.getParameter("enName"));
		merchantCore.setSignDate(request.getParameter("signDate"));
		merchantCore.setLegalPerson(request.getParameter("legalPerson"));
		merchantCore.setLegalPersonMobile(request.getParameter("legalPersonMobile"));
		merchantCore.setLegalPersonTel(request.getParameter("legalPersonTel"));
		merchantCore.setLegalValidCardType(request.getParameter("legalValidCardType"));
		merchantCore.setCardNum(request.getParameter("cardNum"));
		merchantCore.setCardValidTime(request.getParameter("cardValidTime"));
		merchantCore.setBusinessLicenseNum(request.getParameter("businessLicenseNum"));
		merchantCore.setBusinessLicenseValidTime(request.getParameter("businessLicenseValidTime"));
		merchantCore.setTaxRegistCode(request.getParameter("taxRegistCode"));
		merchantCore.setRegistAddress(request.getParameter("registAddress"));
		merchantCore.setMercFlag(request.getParameter("mercFlag"));
		merchantCore.setSource(0);
		merchantCore.setModifyUserId("");//待定
		merchantCore.setModifyTime(new Date());
		merchantCore.setStatus(1);
		return merchantCore;
	}
	
	/**
	 * 在请求中获取该实体参数
	 * @param request
	 * @return
	 */
	private MerchantContact getParamesFormReqCont(HttpServletRequest request){
		MerchantContact merchantContact = new MerchantContact();
		merchantContact.setInnerCode(request.getParameter("innerCode"));
		merchantContact.setContactName(request.getParameter("contactName"));
		merchantContact.setContactMobile(request.getParameter("contactMobile"));
		merchantContact.setContactEmail(request.getParameter("contactEmail"));
		merchantContact.setContactTelphone(request.getParameter("contactTelphone"));
		merchantContact.setContactJobs(request.getParameter("contactJobs"));
		return merchantContact;
	}
	
	/**
	 * 在请求中获取该实体参数
	 * @param request
	 * @return
	 */
	private MerchantTerminal getParamesFormReqTerm(HttpServletRequest request){
		MerchantTerminal merchantTerminal = new MerchantTerminal();
		merchantTerminal.setInnerCode(request.getParameter("innerCode"));
		merchantTerminal.setMerchantCode(request.getParameter("merchantCode"));
		merchantTerminal.setChannelId(Integer.valueOf(request.getParameter("channelId")));
		merchantTerminal.setChannelName(request.getParameter("channelName"));
		merchantTerminal.setTerminalCode(request.getParameter("terminalCode"));
		merchantTerminal.setInnerTermCode(request.getParameter("innerTermCode"));
		merchantTerminal.setSnCode(request.getParameter("snCode"));
		merchantTerminal.setTerminalBatch(request.getParameter("terminalBatch"));
		merchantTerminal.setTerminalPara(request.getParameter("terminalPara"));
		merchantTerminal.setChargesType(Integer.valueOf(request.getParameter("chargesType")));
		merchantTerminal.setDebitCardRate(request.getParameter("debitCardRate"));
		merchantTerminal.setCreditCardRate(request.getParameter("creditCardRate"));
		merchantTerminal.setDebitCardFee(Integer.valueOf(request.getParameter("debitCardFee")));
		merchantTerminal.setCreditCardFee(Integer.valueOf(request.getParameter("creditCardFee")));
		merchantTerminal.setDebitCardMaxFee(Integer.valueOf(request.getParameter("debitCardMaxFee")));
		merchantTerminal.setCreditCardMaxFee(Integer.valueOf(request.getParameter("creditCardMaxFee")));
		merchantTerminal.setDealSwitch(request.getParameter("dealSwitch"));
		merchantTerminal.setRecordState(request.getParameter("recordState"));
		merchantTerminal.setTermAuditState(request.getParameter("termAuditState"));
		merchantTerminal.setTermName(request.getParameter("termName"));
		merchantTerminal.setPosFactory(request.getParameter("posFactory"));
		merchantTerminal.setPosType(request.getParameter("posType"));
		merchantTerminal.setMercReferName(request.getParameter("mercReferName"));
		return merchantTerminal;
	}
	
	/**
	 * 在请求中获取该实体参数
	 * @param request
	 * @return
	 */
	private MerchantChannel getParamesFormReqChan(HttpServletRequest request){
		MerchantChannel merchantChannel = new MerchantChannel();
		merchantChannel.setInnerCode(request.getParameter("innerCode"));
		merchantChannel.setAgentId(Integer.valueOf(request.getParameter("agentId")));
		merchantChannel.setChannelType(request.getParameter("channelType"));
		merchantChannel.setChannelMerId(request.getParameter("channelMerId"));
		merchantChannel.setChannelMerKey(request.getParameter("channelMerKey"));
		merchantChannel.setCreateTime(new Date());
		merchantChannel.setModifyTime(new Date());
		merchantChannel.setModifyUserId(0);//待定
		return merchantChannel;
	}
	
}
