package net.fnsco.withhold.web.open;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@Controller
@RequestMapping(value = "/open", method = RequestMethod.POST)
@Api(value = "/open", tags = { "支付回调接口" })
public class PayCallback extends BaseController {
    // 分页
    @ApiOperation(value = "浦发代收回调接口", notes = "浦发代收回调接口")
    @ResponseBody
    @RequestMapping(value = "/pfWithholdCallback", method = RequestMethod.GET)
    public ResultDTO pfWithholdCallback() {
        Map<String, String> parameterMap = getParameterMap();
         
        return ResultDTO.success();
    }
}
