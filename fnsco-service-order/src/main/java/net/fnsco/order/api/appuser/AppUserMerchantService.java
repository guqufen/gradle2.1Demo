package net.fnsco.order.api.appuser;


import java.util.List;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.AppUserMerchantOutDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.api.dto.BandListDTO;

public interface AppUserMerchantService {
    //判断是否是店主并返回
    ResultDTO deletedBindPeople(BandDto bandDto);
    List<AppUserMerchantOutDTO> queryBindPeople(BandDto bandDto);
}
