package net.fnsco.web.controller.trade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.QueryBandDTO;
import net.fnsco.order.api.push.AppPushService;
import net.fnsco.order.api.trade.TradeReportService;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;

/**
 * @desc  报表初始化接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月7日 下午1:17:34
 */
@Controller
@RequestMapping(value = "/web/report")
public class TradeReportInstallController extends BaseController {
    
    @Autowired
    private TradeReportService tradeReportService;
    
    @Autowired
    private AppPushService     appPushService;
    
    @Autowired
    private AppUserDao                     appUserDao;
    
    @Autowired
    private AppUserMerchantDao             appUserMerchantDao;
    /**
     * 
     * installReport:(批量统计报表数据)
     *
     * @param startTime
     * @param endTime
     * @return   ResultDTO<Object>    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/install")
    @ResponseBody
    public ResultDTO<Object> installReport(String startTime,String endTime){
        if(Strings.isNullOrEmpty(startTime)||Strings.isNullOrEmpty(endTime)){
            return ResultDTO.fail();
        }
        tradeReportService.buildTradeReportDaTa(startTime, endTime);
        return ResultDTO.success("");
    }
    
    /**
     * installReport:(这里用一句话描述这个方法的作用)测试周报
     * @param startTime
     * @param endTime
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月1日 上午10:12:09
     * @return ResultDTO<Object>    DOM对象
     */
    @RequestMapping("/pushweekly")
    @ResponseBody
    public ResultDTO<Object> pushweekly(){
        appPushService.sendWeeklyDataMgs();
        return ResultDTO.success("");
    }
    
    @RequestMapping("/updateMerRole")
    @ResponseBody
    public ResultDTO<Object> updateMerRole(){
        List<AppUser> allUsers = appUserDao.selectAllValid();
        for (AppUser appUserDTO : allUsers) {
            List<QueryBandDTO> list=appUserDao.selectInnercode(appUserDTO.getMobile());
            if (!CollectionUtils.isEmpty(list)){
                for(QueryBandDTO li:list){
                    AppUserMerchant dto=new AppUserMerchant();
                    dto.setAppUserId(appUserDTO.getId());
                    dto.setInnerCode(li.getInnerCode());
                    dto.setModefyTime(new Date());
                    dto.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
                    appUserMerchantDao.updateByUserIdAndInnerCode(dto);
            }
        }
        }
        return null;
    }
}
