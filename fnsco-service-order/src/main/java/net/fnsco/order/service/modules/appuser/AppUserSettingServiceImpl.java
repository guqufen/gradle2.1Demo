package net.fnsco.order.service.modules.appuser;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.order.api.appuser.AppUserSettingService;
import net.fnsco.order.api.dto.AppSettingDTO;
import net.fnsco.order.api.dto.AppUserSettingDTO;
import net.fnsco.order.service.dao.master.AppUserSettingDao;
import net.fnsco.order.service.domain.AppUserSetting;

/**
 * @desc  app用户设置service实现类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月12日 下午2:58:41
 */
@Service
public class AppUserSettingServiceImpl extends BaseService implements AppUserSettingService {
    
    @Autowired
    private AppUserSettingDao appUserSettingDao;
    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserSettingService#deleteByPrimaryKey(java.lang.Integer)
     * @author tangliang
     * @date 2017年9月12日 下午2:58:41
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return appUserSettingDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserSettingService#insert(net.fnsco.order.service.domain.AppUserSetting)
     * @author tangliang
     * @date 2017年9月12日 下午2:58:41
     */
    @Override
    public int insert(AppUserSetting record) {

        // TODO Auto-generated method stub
        return appUserSettingDao.insert(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserSettingService#insertSelective(net.fnsco.order.service.domain.AppUserSetting)
     * @author tangliang
     * @date 2017年9月12日 下午2:58:41
     */
    @Override
    public int insertSelective(AppUserSetting record) {

        // TODO Auto-generated method stub
        return appUserSettingDao.insertSelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserSettingService#selectByPrimaryKey(java.lang.Integer)
     * @author tangliang
     * @date 2017年9月12日 下午2:58:41
     */
    @Override
    public AppUserSetting selectByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return appUserSettingDao.selectByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserSettingService#updateByPrimaryKeySelective(net.fnsco.order.service.domain.AppUserSetting)
     * @author tangliang
     * @date 2017年9月12日 下午2:58:41
     */
    @Override
    public int updateByPrimaryKeySelective(AppUserSetting record) {

        // TODO Auto-generated method stub
        return appUserSettingDao.updateByPrimaryKeySelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserSettingService#updateByPrimaryKey(net.fnsco.order.service.domain.AppUserSetting)
     * @author tangliang
     * @date 2017年9月12日 下午2:58:41
     */
    @Override
    public int updateByPrimaryKey(AppUserSetting record) {

        // TODO Auto-generated method stub
        return appUserSettingDao.updateByPrimaryKey(record);

    }
    
    /**
     * (non-Javadoc) 根据app用户id获取设置状态信息,当没有状态时，初始化默认状态数据，有状态数据时，直接返回
     * @see net.fnsco.order.api.appuser.AppUserSettingService#installSettingStatus(java.lang.Integer)
     * @author tangliang
     * @date 2017年9月12日 下午3:13:54
     */
    @Override
    public List<AppUserSettingDTO> installSettingStatus(Integer userId) {
        //校验入参
        if(null == userId){
            return null;
        }
        
        List<AppUserSettingDTO> datas = appUserSettingDao.selectAllByUserId(userId);
        if(CollectionUtils.isNotEmpty(datas) && datas.size() == 3){
            return datas;
        }
        //当不全时 初始化默认数据
        AppUserSetting record = new AppUserSetting();
        record.setCreateTime(new Date());
        record.setNoticeType("0");
        record.setOpenStatus("1");
        record.setUserId(userId);
        record.setUpdateTime(record.getCreateTime());
        appUserSettingDao.insertSelective(record);
        AppUserSetting record1 = new AppUserSetting();
        record1.setCreateTime(new Date());
        record1.setNoticeType("1");
        record1.setOpenStatus("1");
        record1.setUserId(userId);
        record1.setUpdateTime(record1.getCreateTime());
        appUserSettingDao.insertSelective(record1);
        AppUserSetting record3 = new AppUserSetting();
        record3.setCreateTime(new Date());
        record3.setNoticeType("2");
        record3.setOpenStatus("1");
        record3.setUserId(userId);
        record3.setUpdateTime(record3.getCreateTime());
        appUserSettingDao.insertSelective(record3);
        List<AppUserSettingDTO> result = appUserSettingDao.selectAllByUserId(userId);
        return result;
        
    }
    /**
     * (non-Javadoc)重载方法 输入参数为DTO
     * @see net.fnsco.order.api.appuser.AppUserSettingService#updateByPrimaryKeySelective(net.fnsco.order.api.dto.AppSettingDTO)
     * @author tangliang
     * @date 2017年9月12日 下午4:50:53
     */
    @Override
    public int updateByPrimaryKeySelective(AppSettingDTO records) {
        
        AppUserSetting record = new AppUserSetting();
        record.setUserId(records.getUserId());
        record.setOpenStatus(records.getOpenStatus());
        record.setNoticeType(records.getNoticeType());
        record.setUpdateTime(new Date());
        
        return appUserSettingDao.updateByCondition(record);
        
    }

}
