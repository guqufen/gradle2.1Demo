package net.fnsco.service.modules.sysconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.api.config.SysConfigService;
import net.fnsco.api.dto.AppConfigDTO;
import net.fnsco.core.base.BaseService;
import net.fnsco.service.dao.master.SysConfigDao;
import net.fnsco.service.domain.SysConfig;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月2日 下午3:18:27
 */
@Service
public class SysConfigServiceImpl extends BaseService implements SysConfigService {
    
    @Autowired
    private SysConfigDao sysConfigDao;
    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#deleteByPrimaryKey(java.lang.Integer)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysConfigDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#insert(net.fnsco.service.domain.SysConfig)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public int insert(SysConfig record) {

        // TODO Auto-generated method stub
        return sysConfigDao.insert(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#insertSelective(net.fnsco.service.domain.SysConfig)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public int insertSelective(SysConfig record) {

        // TODO Auto-generated method stub
        return sysConfigDao.insertSelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#selectByPrimaryKey(java.lang.Integer)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public SysConfig selectByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysConfigDao.selectByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#updateByPrimaryKeySelective(net.fnsco.service.domain.SysConfig)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public int updateByPrimaryKeySelective(SysConfig record) {

        // TODO Auto-generated method stub
        return sysConfigDao.updateByPrimaryKeySelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#updateByPrimaryKey(net.fnsco.service.domain.SysConfig)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public int updateByPrimaryKey(SysConfig record) {

        // TODO Auto-generated method stub
        return sysConfigDao.updateByPrimaryKey(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.config.SysConfigService#selectByCondition(net.fnsco.service.domain.SysConfig)
     * @author tangliang
     * @date 2017年8月2日 下午3:18:27
     */
    @Override
    public SysConfig selectByCondition(SysConfig record) {

        // TODO Auto-generated method stub
        return sysConfigDao.selectByCondition(record);

    }
    
    /**
     * (non-Javadoc)获取地址
     * @see net.fnsco.api.config.SysConfigService#getValueUrl(net.fnsco.api.dto.AppConfigDTO)
     * @author tangliang
     * @date 2017年8月2日 下午3:21:21
     */
    @Override
    public String getValueUrl(AppConfigDTO appConfigDTO) {
        SysConfig record = new SysConfig();
        record.setName(appConfigDTO.getType());
        SysConfig config = selectByCondition(record);
        String value = config.getValue();
        if(Strings.isNullOrEmpty(value)){
            return null;
        }
       return config.getValue()+"?userId="+appConfigDTO.getUserId();
    }

}
