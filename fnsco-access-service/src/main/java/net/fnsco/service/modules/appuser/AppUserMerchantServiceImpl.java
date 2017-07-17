package net.fnsco.service.modules.appuser;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import net.fnsco.api.appuser.AppUserMerchantService;
import net.fnsco.api.dto.AppUserMerchantOutDTO;
import net.fnsco.api.dto.BandDto;
import net.fnsco.api.dto.BandListDTO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.dao.master.AppUserMerchantDao;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.dao.master.MerchantUserRelDao;
import net.fnsco.service.domain.AppUserMerchant;
import net.fnsco.service.domain.MerchantCore;

@Service
public class AppUserMerchantServiceImpl extends BaseService implements AppUserMerchantService {
    @Autowired
    private AppUserMerchantDao appUserMerchantDao;
    @Autowired
    private MerchantCoreDao    merchantCoreDao;
    @Autowired
    private AppUserDao         appUserDao;
    @Autowired
    private MerchantUserRelDao merchantUserRelDao;
    @Override
    public ResultDTO queryBindPeople(BandDto bandDto) {
        Integer appUserId = bandDto.getUserId();
        //返回多个店铺的店主       AppUserMerchantOutDTO
        List<AppUserMerchant> merchantList = appUserMerchantDao.selectByPrimaryKey(appUserId, "1");
        if (CollectionUtils.isEmpty(merchantList)) {
            return ResultDTO.fail("该用户不是店主");
        }
        List<AppUserMerchantOutDTO> datas = Lists.newArrayList();
        for(AppUserMerchant object : merchantList){
            //根据innerCode查询出店铺名称
            MerchantCore res = merchantCoreDao.selectByInnerCode(object.getInnerCode());
            AppUserMerchantOutDTO dto=new AppUserMerchantOutDTO();
            dto.setInnerCode(object.getInnerCode());
            dto.setMerName(res.getMerName());
            //查询出店铺的店员集合
            List<AppUserMerchant> list = appUserMerchantDao.selectByInnerCode(object.getInnerCode(), "2");
            List<BandListDTO> listDto=new ArrayList<BandListDTO>();
            //该店铺只有一个店主 没有其他店员
            if(CollectionUtils.isEmpty(list)){
                listDto.add(null);
            }
            for(AppUserMerchant li:list){
                BandListDTO bandList=new BandListDTO();
                bandList.setUserId((li.getAppUserId()));
                //根据appUserId查询到手机号
                bandList.setMobile(appUserDao.selectAppUserById(li.getAppUserId()).getMobile());
                listDto.add(bandList);
            }
            dto.setBandListDTO(listDto);
            datas.add(dto);
        }
        return ResultDTO.success(datas);
    }

    @Override
    public ResultDTO deletedBindPeople(BandDto bandDto) {
        Integer appUserId = bandDto.getUserId();
        if(merchantUserRelDao.deleteByPrimaryKey(bandDto.getInnerCode(),appUserId)==0){
            return ResultDTO.fail("删除绑定失败");
        };
        if(appUserMerchantDao.deleteByPrimaryKey(bandDto.getInnerCode(),appUserId)==0){
            return ResultDTO.fail("删除绑定失败");
        };
        return ResultDTO.success("删除绑定成功");
    }

}











