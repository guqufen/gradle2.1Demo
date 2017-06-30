package net.fnsco.service.modules.merchant;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.SysVersionDTO;
import net.fnsco.api.merchant.SysVersionService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.SysVersionDao;
import net.fnsco.service.domain.SysVersion;

public class SysVersionServiceImpl extends BaseService implements SysVersionService{

    @Autowired
    private SysVersionDao sysVersionDao;
    
    @Override
    public ResultDTO<Object> insertSelective(SysVersionDTO sysVersionDTO) {
        String version=sysVersionDTO.getVersion();
        //非空判断
        if(Strings.isNullOrEmpty(version)){
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }else if(Strings.isNullOrEmpty(sysVersionDTO.getAppType())){
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }if(Strings.isNullOrEmpty(sysVersionDTO.getDeviceId())){
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
       //客户端版本号
        String[] versionArr = StringUtils.split(version, ".");
        if (versionArr == null || versionArr.length != 3) {
            logger.warn("版本号格式错误,version=" + version);
            return ResultDTO.fail();
        }
        String appCode="sqb";
        SysVersion av=sysVersionDao.selectSysVersion(appCode,sysVersionDTO.getAppType());
        Map<String, Object> map = new HashMap<>();
        //是否有更新
        int haveNewVersion = 0;
        //是否强制更新
        int forceUpdate = 0;
        
        //获取数据库最新版本号
        String[] newVersionArr = StringUtils.split(av.getVersion(), ".");
        //判断第一个数字大 强制更新
        for(int i=0;i<=2;i++){
            int newV =Integer.valueOf(newVersionArr[i]);
            int oldV = Integer.valueOf(versionArr[i]);
            //第一数字比较判断是否强制更新
            if(Integer.valueOf(newVersionArr[0]) > Integer.valueOf(versionArr[0])){
                haveNewVersion = 1;
                forceUpdate = 1;
                
            }
            //小版本更新
            if(Integer.valueOf(newVersionArr[1]) > Integer.valueOf(versionArr[1])){
                haveNewVersion = 1;
                forceUpdate = 1;
                
            }
          }
        
        //判断forceUpdate的值为1  强制更新
//        if(av.getForceUpdate()==1){
//            
//        }
        
        return null;
        
    }

}













