package net.fnsco.api.appuser;

import net.fnsco.api.dto.ProtocolDTO;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.dto.VersionResultDTO;
import net.fnsco.core.base.ResultDTO;

public interface ConmmService {
    //版本更新  
    ResultDTO checkUpdate(VersionDTO sysVersionDTO);
    VersionResultDTO queryVersionInfo(VersionDTO sysVersionDTO);
    //获取用户协议
    ResultDTO getProtocol(ProtocolDTO protocolDTO);
}
