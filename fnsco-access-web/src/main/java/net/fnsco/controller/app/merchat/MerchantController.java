package net.fnsco.controller.app.merchat;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.TradeDataDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/app/merchant")
public class MerchantController extends BaseController {
    /**
     * 保存拉卡拉交易数据到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getMerCode", method = RequestMethod.GET)
    @ApiOperation(value = "保存交易流水")
    public ResultDTO saveTrade(@RequestAttribute String merCode) {
      
        return success();
    }
}
