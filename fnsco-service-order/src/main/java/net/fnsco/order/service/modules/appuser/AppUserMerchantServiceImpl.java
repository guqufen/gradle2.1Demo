package net.fnsco.order.service.modules.appuser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserMerchantService;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.AppUserMerchantOutDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.api.dto.BandListDTO;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.dao.master.MerchantCoreDao;
import net.fnsco.order.service.dao.master.MerchantUserRelDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.MerchantCore;

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
    /**
     * 根据用户u
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserMerchantService#queryBindPeople(net.fnsco.order.api.dto.BandDto)
     */
    @Override
    public List<AppUserMerchantOutDTO> queryBindPeople(BandDto bandDto) {
        List<AppUserMerchantOutDTO> datas = Lists.newArrayList();
        Integer appUserId = bandDto.getUserId();
        List<AppUserMerchant> merchantList = appUserMerchantDao.selectByPrimaryKey(appUserId,ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        if (CollectionUtils.isEmpty(merchantList)) {
            return datas;
        }
        for(AppUserMerchant it : merchantList){
            //根据innerCode查询出店铺名称
            MerchantCore res = merchantCoreDao.selectByInnerCode(it.getInnerCode());
            AppUserMerchantOutDTO dto=new AppUserMerchantOutDTO();
            dto.setInnerCode(it.getInnerCode());
            dto.setMerName(res.getMerName());
            //查询出店铺的店员集合
            List<AppUserMerchant> list = appUserMerchantDao.selectByInnerCode(it.getInnerCode(),ConstantEnum.AuthorTypeEnum.CLERK.getCode());
            List<BandListDTO> listDto=new ArrayList<BandListDTO>();
            //该店铺只有一个店主 没有其他店员
            if(CollectionUtils.isEmpty(list)){
                listDto=null;
            }
            for(AppUserMerchant li:list){
                BandListDTO bandList=new BandListDTO();
                bandList.setUserId((li.getAppUserId()));
                //根据appUserId查询到手机号
                AppUser user = appUserDao.selectAppUserById(li.getAppUserId());
                if(null != user){
                    bandList.setMobile(user.getMobile());
                    bandList.setUserName(user.getUserName());
                    listDto.add(bandList);
                }else{
                    logger.error(li.getAppUserId()+"该用户ID不存在!");
                }
               
            }
            dto.setBandListDTO(listDto);
            datas.add(dto);
        }
        return datas;
    }

    @Override
    @Transactional
    public ResultDTO deletedBindPeople(BandDto bandDto) {
        Integer appUserId = bandDto.getUserId();
        merchantUserRelDao.deleteByPrimaryKey(bandDto.getInnerCode(),appUserId);
        appUserMerchantDao.deleteByPrimaryKey(bandDto.getInnerCode(),appUserId);
        return ResultDTO.success();
    }
}










