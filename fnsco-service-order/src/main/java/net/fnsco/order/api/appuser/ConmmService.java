package net.fnsco.order.api.appuser;

import java.util.Map;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.ProtocolDTO;
import net.fnsco.order.api.dto.SuggestDTO;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.dto.VersionResultDTO;

public interface ConmmService {
    //获取最新版本信息  
    ResultDTO<VersionResultDTO> queryLastVersionInfo(VersionDTO sysVersionDTO);
    //获取用户协议
    ResultDTO<Map<String, String>> getProtocol(ProtocolDTO protocolDTO);
    //用户建议
    ResultDTO<String> suggest(SuggestDTO suggestDTO);
}
