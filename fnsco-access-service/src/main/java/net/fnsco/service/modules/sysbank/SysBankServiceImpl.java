package net.fnsco.service.modules.sysbank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.sysbank.SysBankService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.service.dao.master.SysBankDao;
import net.fnsco.service.domain.SysBank;

/**
 * @desc 银行信息service实现类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月7日 上午10:56:16
 */
@Service
public class SysBankServiceImpl extends BaseService implements SysBankService {
    
    @Autowired
    private SysBankDao sysBankDao;
    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysbank.SysBankService#deleteByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月7日 上午10:56:16
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysBankDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysbank.SysBankService#insert(net.fnsco.service.domain.SysBank)
     * @auth tangliang
     * @date 2017年7月7日 上午10:56:16
     */
    @Override
    public int insert(SysBank record) {

        // TODO Auto-generated method stub
        return sysBankDao.insert(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysbank.SysBankService#insertSelective(net.fnsco.service.domain.SysBank)
     * @auth tangliang
     * @date 2017年7月7日 上午10:56:16
     */
    @Override
    public int insertSelective(SysBank record) {

        // TODO Auto-generated method stub
        return sysBankDao.insertSelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysbank.SysBankService#selectByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月7日 上午10:56:16
     */
    @Override
    public SysBank selectByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysBankDao.selectByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysbank.SysBankService#updateByPrimaryKeySelective(net.fnsco.service.domain.SysBank)
     * @auth tangliang
     * @date 2017年7月7日 上午10:56:16
     */
    @Override
    public int updateByPrimaryKeySelective(SysBank record) {

        // TODO Auto-generated method stub
        return sysBankDao.updateByPrimaryKeySelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysbank.SysBankService#updateByPrimaryKey(net.fnsco.service.domain.SysBank)
     * @auth tangliang
     * @date 2017年7月7日 上午10:56:16
     */
    @Override
    public int updateByPrimaryKey(SysBank record) {

        // TODO Auto-generated method stub
        return sysBankDao.updateByPrimaryKey(record);

    }
    /**
     * (non-Javadoc) 分页查询银行卡信息
     * @see net.fnsco.api.sysbank.SysBankService#queryPageList(net.fnsco.service.domain.SysBank, int, int)
     * @auth tangliang
     * @date 2017年7月7日 上午11:18:44
     */
    @Override
    public ResultPageDTO<SysBank> queryPageList(SysBank record, int currentPageNum, int perPageSize) {
        
        PageDTO<SysBank> params = new PageDTO<SysBank>(currentPageNum,perPageSize,record);
        List<SysBank> data = sysBankDao.queryPageList(params);
        int totalNum = sysBankDao.queryTotalByCondition(record);
        ResultPageDTO<SysBank> result = new ResultPageDTO<SysBank>(totalNum,data);
        return result;
        
    }

}
