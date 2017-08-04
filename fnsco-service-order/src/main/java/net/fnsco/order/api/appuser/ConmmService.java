package net.fnsco.order.api.appuser;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.ProtocolDTO;
import net.fnsco.order.api.dto.SuggestDTO;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.dto.VersionResultDTO;

public interface ConmmService {
    //获取最新版本信息  
    ResultDTO queryLastVersionInfo(VersionDTO sysVersionDTO);
    //获取用户协议
    ResultDTO getProtocol(ProtocolDTO protocolDTO);
    //用户建议
    ResultDTO suggest(SuggestDTO suggestDTO);
}
