package net.fnsco.api.merchant;

import net.fnsco.api.dto.SysVersionDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.Version;

public interface SysVersionService {
    //版本更新
    ResultDTO<Object> Selective(SysVersionDTO sysVersionDTO);
}
