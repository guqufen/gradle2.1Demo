package net.fnsco.web.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.pfyh.FixedQrPaymentService;

/**
 * Created by sxf on 2017/7/12.
 */
@Controller
@RequestMapping("/web/pay")
@Api(value = "/web/pay", tags = { "固定二维码，台码功能" })
public class FixedQrPayForPfAction extends BaseController {

    @Autowired
    private Environment           env;
    @Autowired
    private FixedQrPaymentService fixedQrPaymentService;

    //商户台码生成
    @RequestMapping("/getQrImage")
    @ResponseBody
    public ResultDTO getQrImage(@RequestParam("id") Integer id) {
        return fixedQrPaymentService.getQrImage(id, request);
    }

}
