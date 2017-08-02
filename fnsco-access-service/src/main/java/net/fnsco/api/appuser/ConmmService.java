package net.fnsco.api.appuser;

import net.fnsco.api.dto.ProtocolDTO;
import net.fnsco.api.dto.SuggestDTO;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.dto.VersionResultDTO;
import net.fnsco.core.base.ResultDTO;

public interface ConmmService {
    //获取最新版本信息  
    ResultDTO queryLastVersionInfo(VersionDTO sysVersionDTO);
    //获取用户协议
    ResultDTO getProtocol(ProtocolDTO protocolDTO);
    //用户建议
    ResultDTO suggest(SuggestDTO suggestDTO);
}
