package net.fnsco.service.dao.master;

import net.fnsco.service.domain.SysVersion;

public interface SysVersionDao {
    SysVersion selectSysVersion(String appCode,String appType);
}
