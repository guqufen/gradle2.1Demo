package net.fnsco.order.api.appuser;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.AppUserMerchantOutDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.service.domain.AppUserMerchant;

public interface AppUserMerchantService {
    //判断是否是店主并返回
    ResultDTO deletedBindPeople(BandDto bandDto);
    List<AppUserMerchantOutDTO> queryBindPeople(BandDto bandDto);
    List<AppUserMerchantOutDTO> queryBindRelation(BandDto bandDto);
    
    /**
     * getMerchantsScoresByUserId：根据用户ID和内部商户号查询商户积分信息列表
     * @param userId:用户ID
     * @return
     */
    MerChantCoreDTO selectByEntityInnerCode(AppUserMerchant merchantUserRel);
    
    List<AppUserMerchant> selectByUserId( Integer appUserId);
}
