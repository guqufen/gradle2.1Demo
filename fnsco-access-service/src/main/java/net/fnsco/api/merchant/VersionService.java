package net.fnsco.api.merchant;

import net.fnsco.api.dto.VersionDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.Version;

public interface VersionService {
    //版本更新
    ResultDTO<Object> Selective(VersionDTO sysVersionDTO);
}
