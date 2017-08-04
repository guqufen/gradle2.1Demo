package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.service.domain.Version;

public interface VersionDao {
    //根据app编号和手机类型获取多条记录   
    List<Version> selectByPrimaryKey(@Param("appCode")String appCode,@Param("appType")int appType,@Param("version")String version);
}