package net.fnsco.service.modules.sysappmsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.sysappmsg.SysMsgAppSuccService;
import net.fnsco.core.base.BaseService;
import net.fnsco.service.dao.master.SysMsgAppSuccDao;
import net.fnsco.service.domain.SysMsgAppSucc;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月19日 下午3:23:23
 */
@Service
public class SysMsgAppSuccServiceImpl extends BaseService implements SysMsgAppSuccService {
    
    @Autowired
    private SysMsgAppSuccDao sysMsgAppSuccDao;
    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysMsgAppSuccService#deleteByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月19日 下午3:23:23
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysMsgAppSuccDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysMsgAppSuccService#insert(net.fnsco.service.domain.SysMsgAppSucc)
     * @auth tangliang
     * @date 2017年7月19日 下午3:23:23
     */
    @Override
    public int insert(SysMsgAppSucc record) {

        // TODO Auto-generated method stub
        return sysMsgAppSuccDao.insert(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysMsgAppSuccService#insertSelective(net.fnsco.service.domain.SysMsgAppSucc)
     * @auth tangliang
     * @date 2017年7月19日 下午3:23:23
     */
    @Override
    public int insertSelective(SysMsgAppSucc record) {

        // TODO Auto-generated method stub
        return sysMsgAppSuccDao.insertSelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysMsgAppSuccService#selectByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月19日 下午3:23:23
     */
    @Override
    public SysMsgAppSucc selectByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysMsgAppSuccDao.selectByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysMsgAppSuccService#updateByPrimaryKeySelective(net.fnsco.service.domain.SysMsgAppSucc)
     * @auth tangliang
     * @date 2017年7月19日 下午3:23:23
     */
    @Override
    public int updateByPrimaryKeySelective(SysMsgAppSucc record) {

        // TODO Auto-generated method stub
        return sysMsgAppSuccDao.updateByPrimaryKeySelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysMsgAppSuccService#updateByPrimaryKey(net.fnsco.service.domain.SysMsgAppSucc)
     * @auth tangliang
     * @date 2017年7月19日 下午3:23:23
     */
    @Override
    public int updateByPrimaryKey(SysMsgAppSucc record) {

        // TODO Auto-generated method stub
        return sysMsgAppSuccDao.updateByPrimaryKey(record);

    }

}
