package net.fnsco.bigdata.service.modules.merchant;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;

import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.service.dao.master.AgentDao;
import net.fnsco.bigdata.service.dao.master.AppUserMerchant1Dao;
import net.fnsco.bigdata.service.dao.master.MerchantBankDao;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantContactDao;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantFileDao;
import net.fnsco.bigdata.service.dao.master.MerchantFileTempDao;
import net.fnsco.bigdata.service.dao.master.MerchantPosDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.dao.master.MerchantUserRelDao;
import net.fnsco.bigdata.service.domain.Agent;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantFile;
import net.fnsco.bigdata.service.domain.MerchantFileTemp;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.core.utils.CodeUtil;

/**
 * @desc 商家基本信息 实现类
 * @author tangliang
 * @date 2017年6月21日 下午2:22:26
 */
@Service
public class MerchantCoreServiceImpl implements MerchantCoreService {

    private Logger              logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MerchantCoreDao     merchantCoreDao;

    @Autowired
    private MerchantContactDao  merchantContactDao;

    @Autowired
    private MerchantTerminalDao merchantTerminalDao;

    @Autowired
    private MerchantChannelDao  merchantChannelDao;

    @Autowired
    private MerchantFileDao     merchantFileDao;

    @Autowired
    private MerchantFileTempDao merchantFileTempDao;

    @Autowired
    private MerchantBankDao     merchantBankDao;

    @Autowired
    private AgentDao            agentDao;

    @Autowired
    private AppUserMerchant1Dao  appUserMerchantDao;

    @Autowired
    private MerchantUserRelDao  merchantUserRelDao;

    @Autowired
    private MerchantPosDao      merchantPosDao;

    /**
     * @todo 新增加商家
     * @author tangliang
     * @date 2017年6月21日 下午2:23:26
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAdd(net.fnsco.bigdata.service.domain.MerchantCore, int)
     */
    @Override
    @Transactional
    public ResultDTO<Integer> doAdd(HttpServletRequest request) {
        logger.info("开始添加MerchantCoreService.add,merchantInfo=");
        ResultDTO<Integer> result = new ResultDTO<>();

        MerchantCore merchantInfo = getParamesFormRequest(request);
        MerchantContact merchantContact = getParamesFormReqCont(request);
        MerchantTerminal merchantTerminal = getParamesFormReqTerm(request);
        MerchantChannel merchantChannel = getParamesFormReqChan(request);

        // 判断用户名/手机号是否重复
        int cor = merchantCoreDao.insertSelective(merchantInfo);
        int con = merchantContactDao.insertSelective(merchantContact);
        int ter = merchantTerminalDao.insertSelective(merchantTerminal);
        int chan = merchantChannelDao.insertSelective(merchantChannel);
        if (cor == 1 && con == 1 && ter == 1 && chan == 1) {
            fileHander(request);//处理文件
            result.success();
        } else {
            result.fail();
        }
        return result;
    }

    /**
     * @todo 条件分页查询
     * @author tangliang
     * @date 2017年6月22日 上午11:50:55.
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#queryMerchantCore(net.fnsco.bigdata.service.domain.MerchantCore)
     */
    @Override
    public ResultPageDTO<MerchantCore> queryMerchantCore(MerchantCore merchantCore, int currentPageNum, int perPageSize) {

        PageDTO<MerchantCore> pages = new PageDTO<>(currentPageNum, perPageSize, merchantCore);
        List<MerchantCore> datas = merchantCoreDao.queryPageList(pages);
        List<String> innerCodeList= Lists.newArrayList();
        for(MerchantCore core : datas){
            innerCodeList.add(core.getInnerCode());
        }
        Map<String,String> innerMap = Maps.newHashMap();
        if(!CollectionUtils.isEmpty(innerCodeList)){
            List<MerchantChannel> chanelList = merchantChannelDao.selectByInnerCodes(innerCodeList);
            for(MerchantChannel channel :chanelList){
                if(BigdataConstant.ChannelTypeEnum.PF.getCode().equals(channel.getChannelType())){
                    innerMap.put(channel.getInnerCode(), "1");
                }
            }
        }
        for(MerchantCore core : datas){
            String temp = innerMap.get(core.getInnerCode());
            if(temp !=null){
                core.setOpenFixQr(temp);
            }
        }
        int totalNum = merchantCoreDao.queryTotalByCondition(merchantCore);
        ResultPageDTO<MerchantCore> result = new ResultPageDTO<MerchantCore>(totalNum, datas);
        result.setCurrentPage(currentPageNum);

        return result;
    }
    @Override
    public List<MerchantCore> queryMerchantList(MerchantCore merchantCore) {

        PageDTO<MerchantCore> pages = new PageDTO<>(1, 10000, merchantCore);
        List<MerchantCore> datas = merchantCoreDao.queryPageList(pages);
        return datas;
    }

    /**
     * @todo 条件查询
     * @author tangliang
     * @date 2017年6月23日 上午10:39:47
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#queryAllByCondition(net.fnsco.bigdata.service.domain.MerchantCore)
     */
    @Override
    public List<MerchantCore> queryAllByCondition(MerchantCore merchantCore) {
        // TODO Auto-generated method stub
        merchantCore.setStatus(1);
        return merchantCoreDao.queryListByCondition(merchantCore);
    }

    /**
     * @todo 根据ID逻辑删除数据
     * @author tangliang
     * @date 2017年6月27日 上午10:30:05
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#deleteByIds(java.lang.Integer[])
     */
    @Transactional
    @Override
    public ResultDTO<Integer> deleteByIds(Integer[] ids) {
        // TODO Auto-generated method stub
        ResultDTO<Integer> result = null;
        int re = merchantCoreDao.updateStatusByMutipleKey(ids);
        if (re != 0) {
            merchantContactDao.deleteByMerCoreIds(ids);
            // merchantFileDao.deleteByMerCoreIds(ids);
            merchantChannelDao.deleteByMerCoreIds(ids);
            merchantBankDao.deleteByMerCoreIds(ids);
            merchantTerminalDao.deleteByMerCoreIds(ids);
            //根据id找到innerCode  删除店铺绑定关系表和用户角色表
            merchantUserRelDao.deleteByMerCoreIds(ids);
            appUserMerchantDao.deleteByMerCoreIds(ids);
            result = ResultDTO.success("删除成功!");
        } else {
            result = ResultDTO.fail("删除失败");
            logger.error("批量删除失败! ids = " + ids);
        }
        return result;
    }

    /**
     * @todo 根据ID查询出所有关联的数据
     * @author tangliang
     * @date 2017年6月27日 上午11:47:07
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#
     * queryAllById(java.lang.Integer)
     */
    @Override
    public ResultDTO<MerchantCore> queryAllById(Integer id) {
        // TODO Auto-generated method stub
        ResultDTO<MerchantCore> result = new ResultDTO<MerchantCore>();
        result.setData(merchantCoreDao.queryAllById(id));
        return result;
    }

    /**
     * (non-Javadoc)查询所有代理商
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#queryAllAgent()
     * @auth tangliang
     * @date 2017年6月30日 下午3:38:48
     */
    @Override
    public ResultDTO<List<Agent>> queryAllAgent() {
        return ResultDTO.success(agentDao.queryAll());
    }

    /**
     * 处理文件信息
     * @param request
     */
    private void fileHander(HttpServletRequest request) {
        String fileIds = request.getParameter("fileIds");
        if (!StringUtils.isEmpty(fileIds)) {
            String[] ids = fileIds.split(",");
            for (String fileId : ids) {
                MerchantFileTemp fileTemp = merchantFileTempDao.selectByPrimaryKey(Integer.valueOf(fileId));
                if (null != fileTemp) {
                    MerchantFile file = new MerchantFile();
                    BeanUtils.copyProperties(fileTemp, file);
                    file.setId(null);
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
    private MerchantCore getParamesFormRequest(HttpServletRequest request) {
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
    private MerchantContact getParamesFormReqCont(HttpServletRequest request) {
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
    private MerchantTerminal getParamesFormReqTerm(HttpServletRequest request) {
        MerchantTerminal merchantTerminal = new MerchantTerminal();
        merchantTerminal.setInnerCode(request.getParameter("innerCode"));
        //        merchantTerminal.setMerchantCode(request.getParameter("merchantCode"));
        //        merchantTerminal.setChannelId(Integer.valueOf(request.getParameter("channelId")));
        //        merchantTerminal.setChannelName(request.getParameter("channelName"));
        merchantTerminal.setTerminalCode(request.getParameter("terminalCode"));
        merchantTerminal.setInnerTermCode(request.getParameter("innerTermCode"));
        //        merchantTerminal.setSnCode(request.getParameter("snCode"));
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
        //        merchantTerminal.setPosFactory(request.getParameter("posFactory"));
        //        merchantTerminal.setPosType(request.getParameter("posType"));
//        merchantTerminal.setMercReferName(request.getParameter("mercReferName"));
        return merchantTerminal;
    }

    /**
     * 在请求中获取该实体参数
     * @param request
     * @return
     */
    private MerchantChannel getParamesFormReqChan(HttpServletRequest request) {
        MerchantChannel merchantChannel = new MerchantChannel();
        merchantChannel.setInnerCode(request.getParameter("innerCode"));
        //		merchantChannel.setAgentId(Integer.valueOf(request.getParameter("agentId")));
        merchantChannel.setChannelType(request.getParameter("channelType"));
        merchantChannel.setChannelMerId(request.getParameter("channelMerId"));
        merchantChannel.setChannelMerKey(request.getParameter("channelMerKey"));
        merchantChannel.setCreateTime(new Date());
        merchantChannel.setModifyTime(new Date());
        merchantChannel.setModifyUserId(0);//待定
        return merchantChannel;
    }

    /**
     * (non-Javadoc)添加商家基本信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddMerCore(net.fnsco.bigdata.service.domain.MerchantCore)
     * @auth tangliang
     * @date 2017年6月30日 下午4:04:57
     */
    @Override
    public ResultDTO<String> doAddMerCore(MerchantCore merchantCore) {

        if (null == merchantCore.getId()) {
            merchantCore.setSource(0);
            merchantCore.setModifyUserId("admin");//待定
            merchantCore.setModifyTime(new Date());
            merchantCore.setStatus(1);
            int res = merchantCoreDao.insertSelective(merchantCore);
            if (res != 1) {
                return ResultDTO.fail();
            }
        } else {
            merchantCoreDao.updateByPrimaryKeySelective(merchantCore);
            merchantCore = merchantCoreDao.selectByPrimaryKey(merchantCore.getId());
        }

        return new ResultDTO<>(true, merchantCore.getInnerCode(), CoreConstants.WEB_SAVE_OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));

    }

    /**
     * (non-Javadoc)保存联系信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddMerContact(java.util.List)
     * @auth tangliang
     * @date 2017年6月30日 下午6:15:20
     */
    @Transactional
    @Override
    public ResultDTO<String> doAddMerContact(List<MerchantContact> merchantContacts) {

        if (CollectionUtils.isEmpty(merchantContacts)) {
            return ResultDTO.fail();
        }
        String innerCode = "";
        for (MerchantContact merchantContact : merchantContacts) {
            if (null != merchantContact.getId()) {
                merchantContactDao.updateByPrimaryKeySelective(merchantContact);
            } else {
                merchantContactDao.insertSelective(merchantContact);
                innerCode = merchantContact.getInnerCode();
            }

        }
        return new ResultDTO<>(true, innerCode, CoreConstants.WEB_SAVE_OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));

    }

    /**
     * (non-Javadoc)保存终端信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddMerTerminal(java.util.List)
     * @auth tangliang
     * @date 2017年7月1日 上午9:51:47
     */
    @Override
    public ResultDTO<String> doAddMerTerminal(List<MerchantTerminal> merchantTerminals) {

        if (CollectionUtils.isEmpty(merchantTerminals)) {
            return ResultDTO.fail();
        }
        String innerCode = "";
        for (MerchantTerminal merchantTerminal : merchantTerminals) {
            if (null != merchantTerminal.getId()) {
                merchantTerminalDao.updateByPrimaryKeySelective(merchantTerminal);
            } else {
                merchantTerminalDao.insertSelective(merchantTerminal);
            }
            innerCode = merchantTerminal.getInnerCode();
        }
        return new ResultDTO<>(true, innerCode, CoreConstants.WEB_SAVE_OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));

    }

    /**
     * (non-Javadoc)保存渠道信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddChannel(java.util.List)
     * @auth tangliang
     * @date 2017年7月1日 上午9:59:00
     */
    @Transactional
    @Override
    public ResultDTO<String> doAddMerChannel(List<MerchantChannel> merchantChannels) {

        if (CollectionUtils.isEmpty(merchantChannels)) {
            return ResultDTO.fail();
        }
        String innerCode = "";
        for (MerchantChannel merchantChannel : merchantChannels) {
            MerchantChannel res = merchantChannelDao.selectByMerCode(merchantChannel.getChannelMerId(), merchantChannel.getChannelType());
            if (null != merchantChannel.getId()) {
                if (res != null && merchantChannel.getId() != res.getId()) {
                    return ResultDTO.fail(BigdataConstant.WEB_MER_CHANNEL_NOTUNIQUE);
                }
                merchantChannelDao.updateByPrimaryKeySelective(merchantChannel);
            } else {
                if (null != res) {
                    return ResultDTO.fail(BigdataConstant.WEB_MER_CHANNEL_NOTUNIQUE);
                }
                merchantChannelDao.insertSelective(merchantChannel);
                innerCode = merchantChannel.getInnerCode();
            }
        }
        return new ResultDTO<>(true, innerCode, CoreConstants.WEB_SAVE_OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));

    }
    /**
     * (non-Javadoc)批量保存渠道信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddChannel(java.util.List)
     * @auth tangliang
     * @date 2017年7月1日 上午9:59:00
     */
    @Transactional
    @Override
    public Integer doAddChannel(MerchantChannel merchantChannel) {

        if (null==merchantChannel) {
            return 0;
        }
        int i=merchantChannelDao.insertSelective(merchantChannel);
        if(i!=1) {
        	return 0;
        }
        return merchantChannel.getId();

    }
    /**
     * (non-Javadoc) 保存银行卡信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddMerBanks(java.util.List)
     * @auth tangliang
     * @date 2017年7月1日 下午2:03:47
     */
    @Override
    public ResultDTO<String> doAddMerBanks(List<MerchantBank> merchantBanks) {

        if (CollectionUtils.isEmpty(merchantBanks)) {
            return ResultDTO.fail();
        }
        String innerCode = "";
        for (MerchantBank merchantBank : merchantBanks) {
            if (null != merchantBank.getId()) {
                merchantBankDao.updateByPrimaryKeySelective(merchantBank);
            } else {
                merchantBankDao.insertSelective(merchantBank);
                innerCode = merchantBank.getInnerCode();
            }
        }
        return new ResultDTO<>(true, innerCode, CoreConstants.WEB_SAVE_OK, CoreConstants.ERROR_MESSGE_MAP.get(CoreConstants.WEB_SAVE_OK));
    }
    
    /**
     * (non-Javadoc) 批量导入excel保存银行卡信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#doAddMerBanks(java.util.List)
     * @auth tangliang
     * @date 2017年7月1日 下午2:03:47
     */
    @Override
    public Integer doAddBanks(MerchantBank merchantBank) {

        if (null == merchantBank) {
            return 0;
        }
       int i= merchantBankDao.insertSelective(merchantBank);
       if(i!=1) {
       	return 0;
       }
        return merchantBank.getId();
    }
    
    @Override
    public ResultDTO<Integer> deleteByContact(Integer id) {

        if (null == id) {
            return ResultDTO.fail();
        }
        merchantContactDao.deleteByPrimaryKey(id);
        return ResultDTO.success(id);

    }

    @Override
    public ResultDTO<Integer> deleteByTerminal(Integer id) {

        if (null == id) {
            return ResultDTO.fail();
        }
        merchantTerminalDao.deleteByPrimaryKey(id);
        return ResultDTO.success(id);

    }

    /**
     * (non-Javadoc)删除渠道的时候，需要删除下面的POS机以及终端信息
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#deleteByChanel(java.lang.Integer)
     * @author tangliang
     * @date 2017年8月17日 下午1:39:35
     */
    @Override
    public ResultDTO<Integer> deleteByChanel(Integer id) {
        if (null == id) {
            return ResultDTO.fail();
        }
        merchantTerminalDao.deleteByChannelId(id);
        merchantPosDao.deleteByChannelId(id);
        merchantChannelDao.deleteByPrimaryKey(id);
        return ResultDTO.success(id);
    }

    @Override
    public ResultDTO<Integer> deleteByBank(Integer id) {
        if (null == id) {
            return ResultDTO.fail();
        }
        merchantBankDao.deleteByPrimaryKey(id);
        return ResultDTO.success(id);

    }

    @Override
    public MerchantCore queryByInnerCode(String innerCode) {
        MerchantCore merchantCore = merchantCoreDao.selectByInnerCode(innerCode);
        return merchantCore;
    }
    
    /**
     * (non-Javadoc)产生新的innerCode，必须保持全库唯一
     * @see net.fnsco.bigdata.api.merchant.MerchantCoreService#getInnerCode()
     * @author tangliang
     * @date 2017年9月8日 下午4:14:19
     */
	@Override
	public String getInnerCode() {
	    String innerCode = null;
        MerchantCore record = new MerchantCore();
        
        while(true){
            innerCode = CodeUtil.generateMerchantCode("F");
            record.setInnerCode(innerCode);
            List<MerchantCore> datas  = merchantCoreDao.queryListByCondition(record);
            if(CollectionUtils.isEmpty(datas)){
                break;
            }
        }
		return innerCode;
	}
	@Override
    public List<MerchantChannel> findChannelByInnerCode(String innerCode){
	    return merchantChannelDao.selectByInnerCode(innerCode);
	}
	@Override
    public MerchantChannel findChannelByMerId(String merId,String channelType){
	    return merchantChannelDao.selectByMerCode(merId,channelType);
	}
}
