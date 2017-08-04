package net.fnsco.order.service.modules.sysappmsg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.order.api.sysappmsg.SysMsgAppFailService;
import net.fnsco.order.service.dao.master.SysMsgAppFailDao;
import net.fnsco.order.service.domain.SysMsgAppFail;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月19日 下午3:49:21
 */
@Service
public class SysMsgAppFailServiceImpl extends BaseService implements SysMsgAppFailService {
    
    @Autowired
    private SysMsgAppFailDao sysMsgAppFailDao;
    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#deleteByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月19日 下午3:49:21
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysMsgAppFailDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#insert(net.fnsco.order.service.domain.SysMsgAppFail)
     * @auth tangliang
     * @date 2017年7月19日 下午3:49:21
     */
    @Override
    public int insert(SysMsgAppFail record) {

        // TODO Auto-generated method stub
        return sysMsgAppFailDao.insert(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#insertSelective(net.fnsco.order.service.domain.SysMsgAppFail)
     * @auth tangliang
     * @date 2017年7月19日 下午3:49:21
     */
    @Override
    public int insertSelective(SysMsgAppFail record) {

        // TODO Auto-generated method stub
        return sysMsgAppFailDao.insertSelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#selectByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月19日 下午3:49:21
     */
    @Override
    public SysMsgAppFail selectByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysMsgAppFailDao.selectByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#updateByPrimaryKeySelective(net.fnsco.order.service.domain.SysMsgAppFail)
     * @auth tangliang
     * @date 2017年7月19日 下午3:49:21
     */
    @Override
    public int updateByPrimaryKeySelective(SysMsgAppFail record) {

        // TODO Auto-generated method stub
        return sysMsgAppFailDao.updateByPrimaryKeySelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#updateByPrimaryKey(net.fnsco.order.service.domain.SysMsgAppFail)
     * @auth tangliang
     * @date 2017年7月19日 下午3:49:21
     */
    @Override
    public int updateByPrimaryKey(SysMsgAppFail record) {

        // TODO Auto-generated method stub
        return sysMsgAppFailDao.updateByPrimaryKey(record);

    }
    /**
     * (non-Javadoc)条件查询
     * @see net.fnsco.order.api.sysappmsg.SysMsgAppFailService#queryFailMsg(net.fnsco.order.service.domain.SysMsgAppFail)
     * @auth tangliang
     * @date 2017年7月20日 下午1:40:28
     */
    @Override
    public List<SysMsgAppFail> queryFailMsg(SysMsgAppFail record) {
        
        // TODO Auto-generated method stub
        return sysMsgAppFailDao.queryFailMsg(record);
        
    }

}
