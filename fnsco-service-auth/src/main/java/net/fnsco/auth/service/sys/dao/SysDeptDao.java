package net.fnsco.auth.service.sys.dao;


import net.fnsco.auth.service.sys.entity.SysDeptEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 部门管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
}
