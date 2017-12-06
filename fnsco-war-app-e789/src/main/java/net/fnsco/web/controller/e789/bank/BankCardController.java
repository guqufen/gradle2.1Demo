package net.fnsco.web.controller.e789.bank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.web.controller.e789.jo.BankListJO;
import net.fnsco.web.controller.e789.jo.BindBankCardJO;
import net.fnsco.web.controller.e789.jo.UnBindBankCardJO;
import net.fnsco.web.controller.e789.vo.BankListVO;
import net.fnsco.web.controller.e789.vo.BindBankCardVO;
import net.fnsco.web.controller.e789.vo.UnBindBankCardVO;

/**
 * 
 * @desc 聚惠分分期付相关产品功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date     2017年10月27日 上午11:53:16
 *
 */
@RestController
@RequestMapping(value = "/app2c/bankcard", method = RequestMethod.POST)
@Api(value = "/app2c/bankcard", tags = { "银行卡相关功能接口" })
public class BankCardController extends BaseController {
    @Autowired
    private MerchantService   merchantService;
    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private Environment       env;

    /**
     * 新增银行卡
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/bindBankCard")
    @ApiOperation(value = "绑定银行卡")
    public ResultDTO<BindBankCardVO> addBankJO(@RequestBody BindBankCardJO jo) {
    	BindBankCardVO bindBankCardVO = new BindBankCardVO();
    	//校验是否已进行身份认证
    	String userId = jo.getUserId();
    	String mobile = jo.getMobile();
    	String bankCardNum = jo.getBankCardNum();
    	String bankCardholder = jo.getBankCardholder();
    	
    	
        return success(bindBankCardVO);
    }

    /**
     * 解绑银行卡
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/unBindBankCard")
    @ApiOperation(value = "解绑银行卡")
    @ApiImplicitParam(name = "xxx", value = "解绑银行卡", required = false, dataType="Xxx",paramType="body")
    public ResultDTO<UnBindBankCardVO> deleteBankJO( @RequestBody  UnBindBankCardJO unBindBankCardJO ) {
    	UnBindBankCardVO unBindBankCardVO = new UnBindBankCardVO();
    	
        return ResultDTO.success();
    }

    /**
     * 银行卡列表查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getBankCardList")
    @ApiOperation(value = "银行卡列表查询")
    public ResultDTO<List<BankListVO>> getBankJOList(@RequestBody BankListJO bankListJO) {
    	List<BankListVO> bankList = new ArrayList<>();
    	
        return success(bankList);
    }
}
