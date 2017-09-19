package net.fnsco.order.service.modules.appuser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantUserRelDao;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.order.api.appuser.AppUserMerchantService;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.AppUserMerchantOutDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.api.dto.BandListDTO;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;

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

    //查询店铺全员绑定情况
    @Override
    public List<AppUserMerchantOutDTO> queryBindRelation(BandDto bandDto) {
        List<AppUserMerchantOutDTO> datas = Lists.newArrayList();
        Integer appUserId = bandDto.getUserId();
        List<AppUserMerchant> merchantList = appUserMerchantDao.selectByPrimaryKey(appUserId,null);
        if (CollectionUtils.isEmpty(merchantList)) {
            return datas;
        }
        for(AppUserMerchant it : merchantList){
          //根据innerCode查询出店铺名称
            MerchantCore res = merchantCoreDao.selectByInnerCode(it.getInnerCode());
            AppUserMerchantOutDTO dto=new AppUserMerchantOutDTO();
            dto.setInnerCode(it.getInnerCode());
            dto.setMerName(res.getMerName());
            //查询店铺下店主和店员绑定情况
            List<AppUserMerchant> list = appUserMerchantDao.selectByInnerCode(it.getInnerCode(),null);
            List<BandListDTO> listDto=new ArrayList<BandListDTO>();
            //该店铺只有一个店主 没有其他店员
            if(CollectionUtils.isEmpty(list)){
                listDto=null;
            }
            //遍历每一条店员和店铺的绑定关系
            //如果该店铺下是店主
            if(it.getRoleId().equals(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode())){
                for(AppUserMerchant li:list){
                    BandListDTO bandList=new BandListDTO();
                    bandList.setUserId((li.getAppUserId()));
                    bandList.setIsShopkeeper(li.getRoleId());
                    //根据appUserId查询到手机号
                    AppUser user = appUserDao.selectAppUserById(li.getAppUserId());
                    //用户必须存在
                    if(null != user){
                        bandList.setMobile(user.getMobile());
                        bandList.setUserName(user.getUserName());
                        //新增加头像属性
                        String headImagePath = user.getHeadImagePath();
                        if(!Strings.isNullOrEmpty(headImagePath)){
                            String path = headImagePath.substring(headImagePath.indexOf("^")+1);
                            bandList.setHeadImagePath(OssLoaclUtil.getForeverFileUrl(OssLoaclUtil.getHeadBucketName(), path));
                        }
                        if(li.getRoleId().equals(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode())){
                            bandList.setIsDelete("2");
                        }else{
                            bandList.setIsDelete("1");
                        }
                        if(it.getRoleId().equals(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode())&&it.getAppUserId().equals(li.getAppUserId())){
                            listDto.add(0,bandList);
                        }else{
                            listDto.add(bandList);
                        }
                        
                    }else{
                        logger.error(li.getAppUserId()+"该用户ID不存在!");
                    }
                }
            }
          //如果该店铺下是店员
            if(it.getRoleId().equals(ConstantEnum.AuthorTypeEnum.CLERK.getCode())){
                for(AppUserMerchant li:list){
                    BandListDTO bandList=new BandListDTO();
                    bandList.setUserId((li.getAppUserId()));
                    bandList.setIsShopkeeper(li.getRoleId());
                    //根据appUserId查询到手机号
                    AppUser user = appUserDao.selectAppUserById(li.getAppUserId());
                    //用户必须存在
                    if(null != user){
                        bandList.setMobile(user.getMobile());
                        bandList.setUserName(user.getUserName());
                        bandList.setIsDelete("2");
                        //新增加头像属性
                        String headImagePath = user.getHeadImagePath();
                        if(!Strings.isNullOrEmpty(headImagePath)){
                            String path = headImagePath.substring(headImagePath.indexOf("^")+1);
                            bandList.setHeadImagePath(OssLoaclUtil.getForeverFileUrl(OssLoaclUtil.getHeadBucketName(), path));
                        }
                        listDto.add(bandList);
                    }else{
                        logger.error(li.getAppUserId()+"该用户ID不存在!");
                    }
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











