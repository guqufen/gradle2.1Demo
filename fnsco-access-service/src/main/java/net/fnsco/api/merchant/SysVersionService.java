package net.fnsco.api.merchant;

import net.fnsco.api.dto.SysVersionDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.SysVersion;

public interface SysVersionService {
    //版本更新
    ResultDTO<Object> insertSelective(SysVersionDTO sysVersionDTO);
}
