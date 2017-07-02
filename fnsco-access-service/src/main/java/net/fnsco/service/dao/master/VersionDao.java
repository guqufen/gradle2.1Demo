package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import net.fnsco.service.domain.Version;

public interface VersionDao {
    //根据app编号和手机类型获取多条记录   
    List<Version> selectByPrimaryKey(@Param("app_code")String appCode,@Param("app_type")int appType,@Param("version")String version);
}