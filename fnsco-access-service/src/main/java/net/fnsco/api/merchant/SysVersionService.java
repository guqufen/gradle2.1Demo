package net.fnsco.api.merchant;

import net.fnsco.api.dto.SysVersionDTO;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.core.base.ResultDTO;

public interface SysVersionService {
    //版本更新
    ResultDTO<Object> Selective(SysVersionDTO sysVersionDTO);
    VersionDTO queryVersionInfo(SysVersionDTO sysVersionDTO);
}
