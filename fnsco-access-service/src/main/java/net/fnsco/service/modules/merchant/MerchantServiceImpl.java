/**
 * 
 */
package net.fnsco.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.merchant.MerchantService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.service.dao.master.AliasDAO;
import net.fnsco.service.dao.master.MerchantChannelDao;
import net.fnsco.service.domain.Alias;
import net.fnsco.service.domain.MerchantChannel;
import net.fnsco.service.modules.helper.MerchantHelper;

/**@desc 商户相关操作
 * @author sxfei
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private AliasDAO           aliasDAO;
    @Autowired
    private MerchantChannelDao merchantChannelDao;

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
            List<Alias> list = aliasDAO.selectByRandomCode(randomCode);
            if (org.springframework.util.CollectionUtils.isEmpty(list)) {
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

}
