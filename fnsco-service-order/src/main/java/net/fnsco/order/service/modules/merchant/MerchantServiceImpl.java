/**
 * 
 */
package net.fnsco.order.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.MerchantDTO;
import net.fnsco.order.api.dto.TradeMerchantDTO;
import net.fnsco.order.api.merchant.MerchantService;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.service.dao.master.AliasDAO;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.dao.master.MerchantCoreDao;
import net.fnsco.order.service.dao.master.MerchantUserRelDao;
import net.fnsco.order.service.domain.Alias;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.MerchantCore;
import net.fnsco.order.service.domain.MerchantUserRel;

/**@desc 商户相关操作
 * @author sxfei
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantServiceImpl extends BaseService implements MerchantService {

    @Autowired
    private AliasDAO            aliasDAO;
    @Autowired
    private MerchantCoreDao     merchantCoreDao;
    @Autowired
    private MerchantUserRelDao  merchantUserRelDao;
    @Autowired
    private SysAppMsgService    sysAppMsgService;
    @Autowired
    private AppUserService      appUserService;
    @Autowired
    private AppUserMerchantDao  appUserMerchantDao;
    
    @Override
    @Transactional
    public ResultDTO addMerChant(MerchantDTO merchantDTO) {
        String randomCode = merchantDTO.getRandomCode();
        if (Strings.isNullOrEmpty(randomCode)) {
            return ResultDTO.fail(ApiConstant.E_MERCHANT_CODE_NULL);//商铺码不能为空
        }
        randomCode = randomCode.toUpperCase();
        if (null == merchantDTO.getUserId() || 0 == merchantDTO.getUserId().intValue()) {
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);//用户ID不能为空
        }
        Alias alias = aliasDAO.selectByRandomCode(randomCode);
        if (null == alias) {
            return ResultDTO.fail(ApiConstant.E_MERCHANT_CODE_NOT_EXIST);//此商铺码不存在，请重新输入
        }
        Date deadLime = alias.getDeadline();
        Date currentDate = new Date();
        if (currentDate.after(deadLime)) {
            return ResultDTO.fail(ApiConstant.E_MERCHANT_CODE_OVERDUE);//些商铺码已过期，请到pos机查询最新的商铺码
        }
        MerchantUserRel merchantUserRel = merchantUserRelDao.selectByUserIdInnerCode(merchantDTO.getUserId(), alias.getInnerCode());
        if (null != merchantUserRel) {
            return ResultDTO.fail(ApiConstant.E_MERCHANT_ALREADY_REF);//已关联此商铺，请勿重复关联
        }
        MerchantCore merchantCore = merchantCoreDao.selectByInnerCode(alias.getInnerCode());
        if (null != merchantUserRel) {
            return ResultDTO.fail(ApiConstant.E_MERCHANT_IS_DEL);//此商户已删除，关联失败
        }
        MerchantUserRel muRel = new MerchantUserRel();
        muRel.setAppUserId(merchantDTO.getUserId());
        muRel.setInnerCode(alias.getInnerCode());
        muRel.setModefyTime(new Date());
        merchantUserRelDao.insert(muRel);
        //用户管理表新增记录
        AppUserMerchant dto = new AppUserMerchant();
        dto.setAppUserId(merchantDTO.getUserId());
        dto.setInnerCode(alias.getInnerCode());
        dto.setModefyTime(new Date());
        dto.setRoleId(ConstantEnum.AuthorTypeEnum.CLERK.getCode());
        appUserService.addAppUserMerchantRole(dto);
        //发送推送
        try {
            sysAppMsgService.pushMerChantMsg(alias.getInnerCode(), merchantDTO.getUserId());
        } catch (Exception ex) {
            logger.error("绑定商户发送消息失败", ex);
        }
        return ResultDTO.success();
    }
    
    /**
     * (non-Javadoc)查询是店主的商户信息
     * @see net.fnsco.order.api.merchant.MerchantService#getShopOwnerMerChant(net.fnsco.order.api.dto.MerchantDTO)
     * @author tangliang
     * @date 2017年8月3日 下午1:18:47
     */
    @Override
    public List<TradeMerchantDTO> getShopOwnerMerChant(MerchantDTO merchantDTO) {
        
        return appUserMerchantDao.selectByUserIdAndRoleId(merchantDTO.getUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
    }
    

}
