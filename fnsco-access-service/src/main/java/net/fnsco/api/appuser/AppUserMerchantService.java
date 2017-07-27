package net.fnsco.api.appuser;


import java.util.List;

import net.fnsco.api.dto.AppUserMerchantOutDTO;
import net.fnsco.api.dto.BandDto;
import net.fnsco.api.dto.BandListDTO;
import net.fnsco.core.base.ResultDTO;

public interface AppUserMerchantService {
    //判断是否是店主并返回
    ResultDTO deletedBindPeople(BandDto bandDto);
    List<AppUserMerchantOutDTO> queryBindPeople(BandDto bandDto);
}
