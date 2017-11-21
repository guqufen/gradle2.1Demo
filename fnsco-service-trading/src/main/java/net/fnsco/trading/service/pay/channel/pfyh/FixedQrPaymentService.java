package net.fnsco.trading.service.pay.channel.pfyh;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.comm.ServiceConstant;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.modules.merchant.MerchantServiceImpl;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.QrUtil;
import net.fnsco.core.utils.dto.QrDTO;
import net.fnsco.trading.service.pay.channel.pfyh.dto.PfNotifyDTO;

/**
 * Created by sxf on 2017/7/12.
 */
@Service
public class FixedQrPaymentService extends BaseService {

    @Autowired
    private MerchantCoreService merchantCoreService;
    @Autowired
    private TradeDataService    tradeDataService;

    @Autowired
    private Environment         env;

    public String getFixedQrUrl(String innerCode) {
        String busiCode = "";
        if (Strings.isNullOrEmpty(innerCode)) {
            logger.error("固定二维码扫描参数为空");
        } else {
            List<MerchantChannel> channelList = merchantCoreService.findChannelByInnerCode(innerCode);
            for (MerchantChannel channel : channelList) {
                if (BigdataConstant.ChannelTypeEnum.PF.getCode().equals(channel.getChannelType())) {
                    busiCode = channel.getChannelMerId();
                }
            }
            if (CollectionUtils.isEmpty(channelList)) {
                logger.error("固定二维码扫描,商户不存在，innerCode = " + innerCode);
            }
        }
        String payUrl = env.getProperty("pf.fixed.pay.url") + "?id=" + busiCode;//+"&_type=json";
        return payUrl;
    }

    /**
     * 固定二维码浦发调用成功后回调函数
     * 
     * 
     *
     * @author Administrator
     * @throws Exception 
     * @date 2016年12月22日 下午3:46:01
     */
    public void fixedQrCallbackPF(PfNotifyDTO dto) {
        TradeDataDTO tradeDataDTO = new TradeDataDTO();
        tradeDataDTO.setMerId(dto.getBUSI_ID());
        tradeDataDTO.setChannelType(BigdataConstant.ChannelTypeEnum.PF.getCode());
        tradeDataDTO.setAmt(dto.getAMT());
        tradeDataDTO.setTxnType("1");//消费
        tradeDataDTO.setPayType("01");//二维码
        tradeDataDTO.setPayMedium(BigdataConstant.PayMediumEnum.FIX_QR.getCode());
        if (dto.getCHANNEL_TYPE().compareTo(1) == 0) {// 1   支付宝2   微信
            tradeDataDTO.setPaySubType(ServiceConstant.PaySubTypeEnum.ZFB_PAY.getCode());
        } else if (dto.getCHANNEL_TYPE().compareTo(2) == 0) {// 1   支付宝2   微信
            tradeDataDTO.setPaySubType(ServiceConstant.PaySubTypeEnum.WX_PAY.getCode());
        }
        tradeDataDTO.setSource(BigdataConstant.SourceEnum.PF.getCode());
        tradeDataDTO.setOrderNo(dto.getCHARGE_CODE());
        //tradeDataDTO.setTermId(dto.getDEV_ID());
        String txnTime = dto.getBEGIN_TIME();
        txnTime = txnTime.replaceAll(" ", "");
        txnTime = txnTime.replaceAll("-", "");
        txnTime = txnTime.replaceAll(":", "");
        tradeDataDTO.setOrderTime(txnTime);
        String succTime = dto.getEND_TIME();
        succTime = succTime.replaceAll(" ", "");
        succTime = succTime.replaceAll("-", "");
        succTime = succTime.replaceAll(":", "");
        tradeDataDTO.setTimeStamp(succTime);
        Integer state = dto.getSTATE();
        //tradeDataDTO.setRespMsg(BigdataConstant.payStateNameMap.get(state));
        tradeDataDTO.setRespCode(BigdataConstant.payStateCodeMap.get(state));
        tradeDataDTO.setSendTime(DateUtils.getNowTimeStr());
        tradeDataService.saveTradeData(tradeDataDTO);
    }

    public ResultDTO getQrImage(Integer id, HttpServletRequest request) {
        ResultDTO<MerchantCore> result = merchantCoreService.queryAllById(id);
        if (!result.isSuccess()) {
            return ResultDTO.fail("商户不存在" + id);
        }
        MerchantCore core = result.getData();
        String innerCode = core.getInnerCode();
        String host = env.getProperty("web.base.url");
        boolean flag = false;
        String content = "";
        List<MerchantChannel> channelList = merchantCoreService.findChannelByInnerCode(innerCode);
        for (MerchantChannel channel : channelList) {
            if (BigdataConstant.ChannelTypeEnum.PF.getCode().equals(channel.getChannelType())) {
                flag = true;
                content = env.getProperty(MerchantServiceImpl.TAICODE_BASE_URL);
            }else if (BigdataConstant.ChannelTypeEnum.JHF.getCode().equals(channel.getChannelType())) {
                flag = true;
                content = env.getProperty("fsf.qr.redrect.url");
            }
        }
        if (!flag) {
            logger.error("二维码生成出错，该商户未开通浦发扫码支付" + innerCode);
            return ResultDTO.fail("二维码生成出错，该商户未开通浦发扫码支付");
        }
         
        String format = env.getProperty("qr.format");
        String fileName = innerCode + "." + format;
        String filePath = env.getProperty("qr.filePath");
        if (Strings.isNullOrEmpty(filePath)) {
            filePath = request.getSession().getServletContext().getRealPath("") + "/qr/";
        }
        String fileUrl = env.getProperty("qr.fileUrl");
        File f = new File(filePath + fileName);
        if (f.exists()) {
            ResultDTO.success(fileUrl + fileName);
        }
        QrDTO qrDTO = new QrDTO();
        qrDTO.setFileName(fileName);
        qrDTO.setContent(content + "?innerCode=" + innerCode);
        qrDTO.setFilePath(filePath);
        qrDTO.setHeight(Integer.parseInt(env.getProperty("qr.height")));
        qrDTO.setFormat(format);
        qrDTO.setWidth(Integer.parseInt(env.getProperty("qr.width")));
        try {
            QrUtil.createImage(qrDTO);
        } catch (Exception ex) {
            logger.error("生成二维码出错", ex);
            return ResultDTO.fail("生成二维码出错");
        }
        return ResultDTO.success(fileUrl + fileName);
    }

}
