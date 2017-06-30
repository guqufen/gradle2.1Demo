package net.fnsco.service.dao.master;

import net.fnsco.service.domain.Version;

public interface SysVersionDao {
    Version selectSysVersion(String appCode,String appType);
}
