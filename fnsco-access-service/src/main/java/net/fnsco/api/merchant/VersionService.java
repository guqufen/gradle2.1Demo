package net.fnsco.api.merchant;

import net.fnsco.api.dto.VersionDTO;
import net.fnsco.core.base.ResultDTO;

public interface VersionService {
    //版本更新  
    ResultDTO<Object> checkUpdate(VersionDTO sysVersionDTO);
}
