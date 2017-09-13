package net.fnsco.web.controller.pay;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.comm.ServiceConstant;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.QrUtil;
import net.fnsco.core.utils.dto.QrDTO;
import net.fnsco.freamwork.aop.HttpHelper;
import net.fnsco.order.api.push.AppPushService;
import net.fnsco.web.controller.pay.dto.PfDTO;
import net.fnsco.web.controller.pay.dto.PfNotifyDTO;

/**
 * Created by sxf on 2017/7/12.
 */
@Controller
@RequestMapping("/web/pay")
@Api(value = "/web/pay", tags = { "固定二维码，台码功能" })
public class FixedQrPayForPfAction extends BaseController {

    @Autowired
    private MerchantCoreService merchantCoreService;
    @Autowired
    private TradeDataService    tradeDataService;
    @Autowired
    private AppPushService      appPushService;

    @Autowired
    private Environment         env;

    @RequestMapping("/fixedQr")
    public void onlinePay(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String busiCode = "";
        String innerCode = req.getParameter("innerCode");
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
        res.sendRedirect(payUrl);
        //return "redirect:" + payUrl + "?id=" + busiCode;
        //return new ModelAndView(new RedirectView(payUrl));
        //http://www.sssyin.cn/online/pay/?id=952010565811
        //return "redirect:/" +"ups/merchantInfo";
    }

    /**
     * 固定二维码浦发调用成功后回调函数
     * 
     * http://223.95.82.228:8080/posp-admin/onlinePay/fixedqr/callbackPF.htm?_type=json
     *
     * @author Administrator
     * @throws Exception 
     * @date 2016年12月22日 下午3:46:01
     */
    @ResponseBody
    @RequestMapping(value = "/fixedQr/callbackPF", produces = "text/html;charset=UTF-8")
    public String fixedQrCallbackPF(HttpServletRequest req) throws Exception {
        TreeMap<String, String> result = new TreeMap<>();
        result.put("resultmsg", "更新成功！");
        result.put("resultcode", "200");
        String bodyStr = HttpHelper.getBodyString(req);
        logger.error("固定二维码收到浦发回调：" + bodyStr);
        if (Strings.isNullOrEmpty(bodyStr)) {
            logger.error("固定二维码收到浦发回调数据为空");
            return JSON.toJSONString(result);
        }
        String sign1 = req.getParameter("sign");//获取到的sign
        String xmlsign = bodyStr.replaceAll("\\s", "");
        String timestamp = sign1.substring(sign1.indexOf(":") + 1);

        PfDTO pfDto = JSON.parseObject(bodyStr, PfDTO.class);
        PfNotifyDTO dto = pfDto.getPAY_NODIFY();
        MerchantChannel merchantChannel = merchantCoreService.findChannelByMerId(dto.getBUSI_ID(), BigdataConstant.ChannelTypeEnum.PF.getCode());
        //生成签名规则 md5(timestamp+key+xml):timestamp
        String s = md5Encrypt(timestamp + merchantChannel.getChannelMerKey() + xmlsign) + ":" + timestamp;
        if (s.equals(sign1)) {
            logger.error("浦发回调签名正确");
        } else {
            logger.error("浦发回调签名错误");
        }
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
        tradeDataDTO.setTermId(dto.getDEV_ID());
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
        //发送台码提醒消息
        sendMsg(tradeDataDTO.getAmt(), merchantChannel.getInnerCode());
        return JSON.toJSONString(result);
    }

    private void sendMsg(String tempAmt, String innerCode) {
        appPushService.sendFixQRMgs(innerCode, "浙付通到账" + tempAmt + "元");
    }

    //商户导出
    @RequestMapping("/getQrImage")
    @ResponseBody
    public ResultDTO getQrImage(@RequestParam("id") Integer id) {
        ResultDTO<MerchantCore> result = merchantCoreService.queryAllById(id);
        if (!result.isSuccess()) {
            return ResultDTO.fail("商户不存在" + id);
        }
        MerchantCore core = result.getData();
        String innerCode = core.getInnerCode();
        String host = env.getProperty("web.base.url");
        boolean flag = false;
        List<MerchantChannel> channelList = merchantCoreService.findChannelByInnerCode(innerCode);
        for (MerchantChannel channel : channelList) {
            if (BigdataConstant.ChannelTypeEnum.PF.getCode().equals(channel.getChannelType())) {
                flag = true;
            }
        }
        if (!flag) {
            logger.error("二维码生成出错，该商户未开通浦发扫码支付" + innerCode);
            return ResultDTO.fail("二维码生成出错，该商户未开通浦发扫码支付");
        }
        String content = env.getProperty("qr.redrect.url");
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

    /**
     * Return the webapp directory.
     * 
     * @return
     */

    private static String md5Encrypt(String str) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
