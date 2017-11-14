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
import net.fnsco.trading.service.pay.channel.pfyh.FixedQrPaymentService;
import net.fnsco.trading.service.pay.channel.pfyh.dto.PfDTO;
import net.fnsco.trading.service.pay.channel.pfyh.dto.PfNotifyDTO;

/**
 * Created by sxf on 2017/7/12.
 */
@Controller
@RequestMapping("/web/pay")
@Api(value = "/web/pay", tags = { "固定二维码，台码功能" })
public class FixedQrPayForPfAction extends BaseController {

    @Autowired
    private MerchantCoreService   merchantCoreService;
    @Autowired
    private TradeDataService      tradeDataService;
    @Autowired
    private AppPushService        appPushService;

    @Autowired
    private Environment           env;
    @Autowired
    private FixedQrPaymentService fixedQrPaymentService;

    @RequestMapping("/fixedQr")
    public void onlinePay(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String innerCode = req.getParameter("innerCode");
        String payUrl = fixedQrPaymentService.getFixedQrUrl(innerCode);
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
        fixedQrPaymentService.fixedQrCallbackPF(dto);
        //发送台码提醒消息
        sendMsg(dto.getAMT(), merchantChannel.getInnerCode());
        return JSON.toJSONString(result);
    }

    @RequestMapping("/sendTestQrMsg")
    @ResponseBody
    public ResultDTO sendTestQrMsg(@RequestParam("innerCode") String innerCode, @RequestParam("amt") String amt) {
        sendMsg(amt, innerCode);
        return ResultDTO.success();
    }

    private void sendMsg(String strAmt, String innerCode) {
        String tempAmt = "";
        if (strAmt.length() == 1) {
            tempAmt = "0.0" + strAmt;
        } else if (strAmt.length() == 2) {
            tempAmt = "0." + strAmt;
        } else if (strAmt.length() > 2) {
            String temp = strAmt.substring(strAmt.length() - 2, strAmt.length());
            if (temp.equals("00")) {
                tempAmt = strAmt.substring(0, strAmt.length() - 2);
            } else {
                tempAmt = strAmt.substring(0, strAmt.length() - 2) + "." + temp;
            }
        }
        appPushService.sendFixQRMsg(innerCode, "数钱吧到账" + tempAmt + "元");
    }

    //商户台码生成
    @RequestMapping("/getQrImage")
    @ResponseBody
    public ResultDTO getQrImage(@RequestParam("id") Integer id) {
        return fixedQrPaymentService.getQrImage(id,request);
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
