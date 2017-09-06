/**
 * 
 */
package net.fnsco.order.api.merchant;

import java.util.List;

import net.fnsco.bigdata.api.dto.MerchantDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.TradeMerchantDTO;

/**@desc 商户相关服务接口
 * @author sxfei
 * @date 2017年6月21日 上午10:12:23
 */
public interface MerchantOrderService {

    ResultDTO addMerChant(MerchantDTO merchantDTO);
    
    /**
     * getShopOwnerMerChant:(这里用一句话描述这个方法的作用)根据userId查询是店主的商户信息
     *
     * @param merchantDTO
     * @return    设定文件
     * @return List<TradeMerchantDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeMerchantDTO> getShopOwnerMerChant(MerchantDTO merchantDTO);
    
    /**
     * 
     * deleteMerchantRelation:(这里用一句话描述这个方法的作用)
     * @param userMerchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月4日 上午11:13:18
     * @return boolean    DOM对象
     */
    boolean deleteMerchantRelation(Integer merId,Integer userId);
}
