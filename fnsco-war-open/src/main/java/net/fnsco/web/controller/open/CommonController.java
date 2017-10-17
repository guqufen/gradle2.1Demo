package net.fnsco.web.controller.open;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.appuser.ConmmService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum.AppTypeEnum;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.service.domain.SysAppMessage;
import net.fnsco.web.controller.open.jo.CommJO;
import net.fnsco.web.controller.open.jo.LogJO;
import net.fnsco.web.controller.open.jo.MsgJO;

/**
 * 开放接口公共处理类
 * @author sxf
 *
 */
@RestController
@RequestMapping(value = "/open/comm", method = RequestMethod.POST)
@Api(value = "/open/comm", tags = { "公共接口" })
public class CommonController extends BaseController {
    @Autowired
    private Environment      env;
    @Autowired
    private ConmmService     versionService;
    @Autowired
    private SysAppMsgService sysAppMsgService;
    @Autowired
    private MerchantService  merchantService;

    /**
     * 获取APP下载地址
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/appDownUrl")
    @ApiOperation(value = "获取APP下载地址")
    public ResultDTO getMerCode() {
        return success(env.getProperty(ApiConstant.THIS_PROGREM_URL));
    }

    /**
     * 获取理财超市地址
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getFinaMarketUrl")
    @ApiOperation(value = "获取获取理财超市下载地址")
    public ResultDTO getFinaMarketUrl(@RequestBody CommJO commJO) {
        MerchantChannel merchantChannel = merchantService.getMerChannel(commJO.getMerCode(), "00");
        String url = env.getProperty(ApiConstant.FINA_MARKET_URL);
        if (merchantChannel != null) {
            url += "?innerCode="+merchantChannel.getInnerCode();
        }
        return success(url);
    }

    @RequestMapping(value = "/saveLog")
    @ApiOperation(value = "保存log")
    public ResultDTO saveLog(@RequestBody LogJO log) {
        //终端号
        logger.error(String.format("=====>拉卡拉日志：pos机sn码%s，终端号%s,log日志%s", log.getSnCode(), log.getTermCode(), log.getLogInfo()));
        return ResultDTO.success();
    }

    @RequestMapping(value = "/checkUpdate")
    @ApiOperation(value = "检查是否有新版本")
    public ResultDTO checkUpdate(@RequestBody CommJO commJO) {
        String version = commJO.getVersion();
        Integer type = commJO.getType();
        String[] versionArr = version.split("\\.");
        if (versionArr == null || versionArr.length != 3) {
            logger.warn("版本号格式错误,version=" + version);
            return ResultDTO.fail(ApiConstant.E_EDITION_ERROR);
        }
        String appCode = AppTypeEnum.LKL.getCode();
        VersionDTO sysVersionDTO = new VersionDTO();
        sysVersionDTO.setAppType(type);
        sysVersionDTO.setVersion(version);
        sysVersionDTO.setAppCode(appCode);
        ResultDTO resultDTO = versionService.queryLastVersionInfo(sysVersionDTO);
        return resultDTO;
    }

    @RequestMapping(value = "/getActivityList")
    @ApiOperation(value = "获取所有未过期活动列表")
    public ResultDTO getActivityList() {
        List<MsgJO> resultList = Lists.newArrayList();
        List<SysAppMessage> messageList = sysAppMsgService.queryActivityIng("3");
        for (SysAppMessage msg : messageList) {
            MsgJO jo = new MsgJO();
            jo.setId(msg.getId());
            jo.setDetailUrl(msg.getDetailUrl());
            jo.setImageUrl(msg.getImageUrl());
            jo.setModifyTime(DateUtils.dateFormat1ToStr(msg.getModifyTime()));
            jo.setMsgSubject(msg.getMsgSubject());
            jo.setMsgSubTitle(msg.getMsgSubTitle());
            resultList.add(jo);
        }
        return ResultDTO.success(resultList);
    }
}
