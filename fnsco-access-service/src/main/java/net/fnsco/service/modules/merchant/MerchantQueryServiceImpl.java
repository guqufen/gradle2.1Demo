/**
 * 
 */
package net.fnsco.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.merchant.MerchantQueryService;
import net.fnsco.core.base.BaseService;
import net.fnsco.service.dao.master.AliasDAO;
import net.fnsco.service.dao.master.MerchantChannelDao;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.dao.master.MerchantTerminalDao;
import net.fnsco.service.dao.master.MerchantUserRelDao;
import net.fnsco.service.domain.MerchantTerminal;

/**@desc 商户相关操作
 * @author sxfei
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantQueryServiceImpl extends BaseService implements MerchantQueryService {

    @Autowired
    private AliasDAO            aliasDAO;
    @Autowired
    private MerchantChannelDao  merchantChannelDao;
    @Autowired
    private MerchantCoreDao     merchantCoreDao;
    @Autowired
    private MerchantTerminalDao merchantTerminalDao;
    @Autowired
    private MerchantUserRelDao  merchantUserRelDao;
    @Override
    public List<MerchantTerminal> selectTerminalByIdList(List<Integer> innerIdList) {
        
        // TODO Auto-generated method stub
        return null;
        
    }

}
