package net.fnsco.api.sysbank;

import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.domain.SysBank;

/**
 * @desc 银行信息service接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @Date	 2017年7月7日 上午10:54:48
 */

public interface SysBankService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBank record);

    int insertSelective(SysBank record);

    SysBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysBank record);

    int updateByPrimaryKey(SysBank record);
    
    /**
     * queryPageList:(这里用一句话描述这个方法的作用) 分页查询银行卡信息
     *
     * @param record
     * @param currentPageNum
     * @param perPageSize
     * @return    设定文件
     * @return ResultPageDTO<SysBank>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultPageDTO<SysBank> queryPageList(SysBank record,int currentPageNum, int perPageSize);
}
