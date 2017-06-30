/**
 * 
 */
package net.fnsco.service.modules.merchant;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.base.Strings;
import net.fnsco.api.dto.MerChantCoreDTO;
import net.fnsco.api.dto.MerChantCoreDetailDTO;
import net.fnsco.api.dto.MerTerminalsDTO;
import net.fnsco.api.dto.MerchantDTO;
import net.fnsco.api.merchant.MerchantService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.service.dao.master.AliasDAO;
import net.fnsco.service.dao.master.MerchantChannelDao;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.dao.master.MerchantTerminalDao;
import net.fnsco.service.domain.Alias;
import net.fnsco.service.domain.MerchantChannel;
import net.fnsco.service.domain.MerchantTerminal;
import net.fnsco.service.modules.helper.MerchantHelper;

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

    @Override
    public ResultDTO addMerChant(MerchantDTO merchantDTO) {
        String randomCode = merchantDTO.getRandomCode();
        if (Strings.isNullOrEmpty(randomCode)) {
            ResultDTO.fail();//商铺码不能为空
        }
        if (Strings.isNullOrEmpty(merchantDTO.getUserId())) {
            ResultDTO.fail();//用户ID不能为空
        }
        Alias alias= aliasDAO.selectByRandomCode(randomCode);
        if(null ==alias){
            
        }
        //此商铺码不存在，请重新输入
        //些商铺码已过期，请到pos机查询最新的商铺码
        return ResultDTO.success();
    }

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
     * @see net.fnsco.api.merchant.MerchantService#getMerchantsCoreByUserId(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月29日 下午2:10:31
     */
    @Override
    public ResultDTO<List<MerChantCoreDTO>> getMerchantsCoreByUserId(Integer userId) {
        List<MerChantCoreDTO> datas = merchantCoreDao.queryAllByUseraId(userId);
        ResultDTO<List<MerChantCoreDTO>> result = ResultDTO.success(datas);
        return result;

    }

    /**根据用户ID 查询出终端设备信息
     * (non-Javadoc)
     * @see net.fnsco.api.merchant.MerchantService#getMerchantTerminalByUserId(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月29日 下午4:43:23
     */
    @Override
    public ResultDTO<List<MerTerminalsDTO>> getMerchantTerminalByUserId(Integer userId) {
        ResultDTO<List<MerchantTerminal>> result = new ResultDTO<>();
        List<MerchantTerminal> datas = merchantTerminalDao.selectByUserId(userId);
        result.setData(datas);
        return result;
    }
    
    /**
     * 查询详情
     * (non-Javadoc)
     * @see net.fnsco.api.merchant.MerchantService#getMerChantDetailById(java.lang.Integer)
     * @auth tangliang
     * @date 2017年6月30日 上午11:19:42
     */
    @Override
    public ResultDTO<MerChantCoreDetailDTO> getMerChantDetailById(Integer merId) {
        MerChantCoreDetailDTO datas = merchantCoreDao.queryDetailById(merId);
        ResultDTO<MerChantCoreDetailDTO> result = ResultDTO.success(datas);
        return result;
        
    }

}
