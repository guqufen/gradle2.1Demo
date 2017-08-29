/**
 * 
 */
package net.fnsco.order.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.NumberUtil;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.MerChantCoreDTO;
import net.fnsco.order.api.dto.MerChantCoreDetailDTO;
import net.fnsco.order.api.dto.MerTerminalsDTO;
import net.fnsco.order.api.dto.MerchantDTO;
import net.fnsco.order.api.dto.PosDetailDTO;
import net.fnsco.order.api.dto.PosInfoDTO;
import net.fnsco.order.api.dto.PosListDTO;
import net.fnsco.order.api.dto.TerminalDetailDTO;
import net.fnsco.order.api.dto.TerminalInfoDTO;
import net.fnsco.order.api.dto.TerminalsDTO;
import net.fnsco.order.api.merchant.MerchantService;
import net.fnsco.order.service.dao.master.AliasDAO;
import net.fnsco.order.service.dao.master.MerchantChannelDao;
import net.fnsco.order.service.dao.master.MerchantCoreDao;
import net.fnsco.order.service.dao.master.MerchantPosDao;
import net.fnsco.order.service.dao.master.MerchantTerminalDao;
import net.fnsco.order.service.domain.Alias;
import net.fnsco.order.service.domain.MerchantChannel;
import net.fnsco.order.service.domain.MerchantPos;
import net.fnsco.order.service.domain.MerchantTerminal;
import net.fnsco.order.service.modules.helper.MerchantHelper;

/**@desc 商户相关操作
 * @author sxfei
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantServiceImpl extends BaseService implements MerchantService {

    @Autowired
    private AliasDAO            aliasDAO;
    @Autowired
    private MerchantChannelDao  merchantChannelDao;
    @Autowired
    private MerchantCoreDao     merchantCoreDao;
    @Autowired
    private MerchantTerminalDao merchantTerminalDao;
    @Autowired
    private MerchantPosDao      merchantPosDao;
    

    /**
      * 
      * @param merNum 商户号
      * @param channelType
      * @return
      */
    public String getMerCode(String merCode, String channelType) {
        //商户代号
        String randomCode = "";
        MerchantChannel merchantChannel = merchantChannelDao.selectByMerCode(merCode, channelType);
        if (null == merchantChannel) {
            logger.error("渠道商户不存在" + merCode + ":" + channelType);
            return randomCode;
        }
        String innerCode = merchantChannel.getInnerCode();
        //查询所有的
        List<Alias> aliasList = aliasDAO.selectByInnerCode(innerCode);
        for (Alias alias : aliasList) {
            randomCode = alias.getRandomCode();
            Date validate = alias.getValidateTime();
            if (validate.after(new Date())) {
                return randomCode;
            }
        }
        randomCode = MerchantHelper.getMerCode();
        while (true) {
            Alias alias = aliasDAO.selectByRandomCode(randomCode);
            if (null == alias) {
                break;
            }
            randomCode = MerchantHelper.getMerCode();
        }
        Alias alias = new Alias();
        alias.setInnerCode(innerCode);
        alias.setRandomCode(randomCode);
        alias.setCreateTime(new Date());
        alias.setDeadline(DateUtils.getDayEndTime(7));//+7天
        alias.setValidateTime(DateUtils.getDayEndTime(6));//+6天
        aliasDAO.insert(alias);

        return randomCode;
    }

    /**
     * (non-Javadoc) 根据用户ID 查询商户列表
     * @see net.fnsco.order.api.merchant.MerchantService#getMerchantsCoreByUserId(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月29日 下午2:10:31
     */
    @Override
    public ResultDTO<List<MerChantCoreDTO>> getMerchantsCoreByUserId(Integer userId) {
        if (null == userId) {
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        List<MerChantCoreDTO> datas = merchantCoreDao.queryAllByUseraId(userId);
        ResultDTO<List<MerChantCoreDTO>> result = ResultDTO.success(datas);
        return result;

    }

    /**根据用户ID 查询出终端设备信息
     * (non-Javadoc)
     * @see net.fnsco.order.api.merchant.MerchantService#getMerchantTerminalByUserId(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月29日 下午4:43:23
     */
    @Override
    public ResultDTO<List<MerTerminalsDTO>> getMerchantTerminalByUserId(Integer userId) {
        if (null == userId) {
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        List<MerTerminalsDTO> datas = merchantCoreDao.queryMerTerminalsByUserId(userId);
        ResultDTO<List<MerTerminalsDTO>> result = ResultDTO.success(datas);
        return result;
    }

    /**
     * 查询详情
     * (non-Javadoc)
     * @see net.fnsco.order.api.merchant.MerchantService#getMerChantDetailById(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月30日 上午11:19:42
     */
    @Override
    public ResultDTO<MerChantCoreDetailDTO> getMerChantDetailById(Integer merId) {
        if (null == merId) {
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        MerChantCoreDetailDTO datas = merchantCoreDao.queryDetailById(merId);
        ResultDTO<MerChantCoreDetailDTO> result = ResultDTO.success(datas);
        return result;

    }

    /**
     * (non-Javadoc)查询设备详情
     * @see net.fnsco.order.api.merchant.MerchantService#getTerminalDetailByTerId(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月30日 下午1:45:17
     */
    @Override
    public ResultDTO<TerminalDetailDTO> getTerminalDetailByTerId(Integer terId) {
        if (null == terId) {
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        TerminalDetailDTO data = merchantTerminalDao.queryDetailById(terId);
        return ResultDTO.success(data);
    }

    /**
     * (non-Javadoc) 更新设备名称
     * @see net.fnsco.order.api.merchant.MerchantService#updateTerminal(net.fnsco.order.api.dto.TerminalsDTO)
     * @auth tangliang
     * @date 2017年6月30日 下午2:20:24
     */
    @Override
    public ResultDTO<TerminalsDTO> updateTerminal(TerminalsDTO terminalsDTO) {

        if (null == terminalsDTO.getId()) {
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        MerchantTerminal terminal = new MerchantTerminal();
        BeanUtils.copyProperties(terminalsDTO, terminal);
        int res = merchantTerminalDao.updateByPrimaryKeySelective(terminal);

        if (res != 1) {
            return ResultDTO.fail(ApiConstant.E_UPDATE_FAIL);
        }
        return ResultDTO.success(terminalsDTO);

    }
    
    /**
     * (non-Javadoc)获取POS机列表
     * @see net.fnsco.order.api.merchant.MerchantService#getAllPosInfo(net.fnsco.order.api.dto.MerchantDTO)
     * @author tangliang
     * @date 2017年8月16日 下午1:15:44
     */
    @Override
    public List<PosListDTO> getAllPosInfo(MerchantDTO merchantDTO) {
        return merchantPosDao.selectAllPosInfo(merchantDTO.getUserId());
    }
    
    /**
     * (non-Javadoc)获取单个POS详情
     * @see net.fnsco.order.api.merchant.MerchantService#getPosDetail(java.lang.Integer)
     * @author tangliang
     * @date 2017年8月16日 下午2:15:19
     */
    @Override
    public PosDetailDTO getPosDetail(Integer posId) {
        PosDetailDTO data = merchantPosDao.selectDetailById(posId);
        List<TerminalInfoDTO> terNames = data.getTerNames();
        if(!CollectionUtils.isEmpty(terNames)){
            int i = 1;
            for (TerminalInfoDTO terminalInfoDTO : terNames) {
                String title = NumberUtil.TER_TITLE_NAME+NumberUtil.numToUpper(i);
                terminalInfoDTO.setTerTitle(title);
                i++;
            }
        }
        return data;
    }
    
    /**
     * (non-Javadoc)更新POS机信息
     * @see net.fnsco.order.api.merchant.MerchantService#updatePosInfo(net.fnsco.order.controller.app.jo.TerminalJO)
     * @author tangliang
     * @date 2017年8月16日 下午2:50:42
     */
    @Override
    public boolean updatePosInfo(TerminalsDTO dto) {
        
        MerchantPos mecord = new MerchantPos();
        mecord.setId(dto.getPosId());
        mecord.setPosName(dto.getPosName());
        return merchantPosDao.updateByPrimaryKeySelective(mecord)> 0 ?true:false;
        
    }
    /**
     * (non-Javadoc)查询用户下所有的POS列表 不带商户信息的
     * @see net.fnsco.order.api.merchant.MerchantService#getAllReportPos(net.fnsco.order.api.dto.MerchantDTO)
     * @author tangliang
     * @date 2017年8月18日 下午5:01:20
     */
    @Override
    public List<PosInfoDTO> getAllReportPos(MerchantDTO merchantDTO) {
        return merchantPosDao.selectAllByUserId(merchantDTO.getUserId());
    }

}
