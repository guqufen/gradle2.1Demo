package net.fnsco.service.dao.master;

import net.fnsco.service.domain.Version;

public interface VersionDao {
    //根据app编号和手机类型获取一条记录
    Version selectSysVersion(String appCode ,String appType);
}