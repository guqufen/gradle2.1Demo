package net.fnsco.bigdata.service.dao.master;

import org.apache.ibatis.annotations.Param;

public interface AppUserMerchant1Dao {
    int deleteByMerCoreIds(Integer[] ids);
    
    String selectInnerCodeByUserId(@Param("userId") Integer userId);
}
