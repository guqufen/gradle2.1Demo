package net.fnsco.order.service.modules.sysconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.PermissionsDTO;
import net.fnsco.core.base.BaseService;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.config.SysConfigService;
import net.fnsco.order.api.dto.AppConfigDTO;
import net.fnsco.order.service.dao.master.SysConfigDao;
import net.fnsco.order.service.domain.SysConfig;

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
    private SysConfigDao        sysConfigDao;
    @Autowired
    private Environment         env;

    private static final String baseUrl = "h5.base.url";
    private static final String lklbaseUrl = "lklh5.base.url";

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.config.SysConfigService#deleteByPrimaryKey(java.lang.Integer)
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
     * @see net.fnsco.order.api.config.SysConfigService#insert(net.fnsco.order.service.domain.SysConfig)
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
     * @see net.fnsco.order.api.config.SysConfigService#insertSelective(net.fnsco.order.service.domain.SysConfig)
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
     * @see net.fnsco.order.api.config.SysConfigService#selectByPrimaryKey(java.lang.Integer)
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
     * @see net.fnsco.order.api.config.SysConfigService#updateByPrimaryKeySelective(net.fnsco.order.service.domain.SysConfig)
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
     * @see net.fnsco.order.api.config.SysConfigService#updateByPrimaryKey(net.fnsco.order.service.domain.SysConfig)
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
     * @see net.fnsco.order.api.config.SysConfigService#selectByCondition(net.fnsco.order.service.domain.SysConfig)
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
     * @see net.fnsco.order.api.config.SysConfigService#getValueUrl(net.fnsco.order.api.dto.AppConfigDTO)
     * @author tangliang
     * @date 2017年8月2日 下午3:21:21
     */
    @Override
    public String getValueUrl(AppConfigDTO appConfigDTO) {
        SysConfig record = new SysConfig();
        record.setType(appConfigDTO.getType());
        record.setName(appConfigDTO.getName());
        SysConfig config = selectByCondition(record);
        if (null == config) {
            return null;
        }
        String value = config.getValue();
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        String value1 = env.getProperty(baseUrl) + value;
        if("01".equals(config.getType()) && "02".equals(config.getName())) {
            value1 = env.getProperty(lklbaseUrl) + value;
        }

        String temp = FrameworkConstant.TOKEN_ID + appConfigDTO.getUserId();
        String trueTokenId = Md5Util.getInstance().md5(temp);
        if (value.indexOf("?") > 0) {
            config.setValue(value1 + "&");
        } else {
            config.setValue(value1 + "?");
        }
        return config.getValue() + "userId=" + appConfigDTO.getUserId() + "&tokenId=" + trueTokenId;
    }

    /**
     * 
     * (non-Javadoc)
     * @see net.fnsco.order.api.config.SysConfigService#selectAllByCondition(net.fnsco.order.service.domain.SysConfig)
     */
    @Override
    public List<SysConfig> selectAllByCondition(SysConfig record) {
        return sysConfigDao.selectAllByCondition(record);

    }

    /**
     * 通过商户积分查询商户vip等级
     */
	@Override
	public SysConfig selectLevelByScores(SysConfig record) {

		return sysConfigDao.selectLevelByScores(record);
	}

	@Override
	public SysConfig selectNextLevelByScores(SysConfig record) {
		// TODO Auto-generated method stub
		return sysConfigDao.selectNextLevelByScores(record);
	}

	/**
     * 通过type查询最大值，便于积分判断
     * @param type
     * @return
     */
    public SysConfig selectMaxByType(String type){
    	return sysConfigDao.selectMaxByType(type);
    }

	@Override
	public List<PermissionsDTO> selectLevelPrivilege() {		
		return sysConfigDao.selectLevelPrivilege();
	}
}
