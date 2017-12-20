package net.fnsco.web.controller.e789.bank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.bank.AppUserBankService;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
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
@Api(value = "/app2c/bankcard", tags = { "我的-银行卡相关功能接口" })
public class BankCardController extends BaseController {
    @Autowired
    private AppUserBankService appUserBankService;
    @Autowired
    private AppUserService        appUserService;

    /**
     * 新增银行卡
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/bindBankCard")
    @ApiOperation(value = "绑定银行卡页-新增银行卡")
    public ResultDTO<BindBankCardVO> addBankJO(@RequestBody BindBankCardJO jo) {
    	BindBankCardVO bindBankCardVO = new BindBankCardVO();
    	String userId = jo.getUserId();
    	String mobile = jo.getMobile();
    	String bankCardNum = jo.getBankCardNum();
    	String bankCardholder = jo.getBankCardholder();
    	//判断是否已进行身份认证
    	if(Strings.isNullOrEmpty(userId)||Strings.isNullOrEmpty(mobile)||Strings.isNullOrEmpty(bankCardNum)||Strings.isNullOrEmpty(bankCardholder)){
    		return ResultDTO.fail(TradeConstants.E_PARAMETER_NOT_NULL);
    	}
    	String id_card_num = appUserService.getIdAuth(Integer.parseInt(userId));
    	if(Strings.isNullOrEmpty(id_card_num)){
    		return ResultDTO.fail(TradeConstants.NOT_ID_AUTH);//未认证
		}
    	//校验银行卡信息
    	
    	//保存银行卡信息
    	Integer row = appUserBankService.doAppAdd(Integer.parseInt(userId),mobile,bankCardNum,bankCardholder,id_card_num);
    	if(row == 1){
    		return ResultDTO.success();
    	}else{
    		return ResultDTO.fail();
    	}
    	
    	
    	
    	
    }

    /**
     * 解绑银行卡
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/unBindBankCard")
    @ApiOperation(value = "银行卡信息页-解绑银行卡")
//    @ApiImplicitParam(name = "xxx", value = "解绑银行卡", required = false, dataType="Xxx",paramType="body")
    public ResultDTO<UnBindBankCardVO> deleteBankJO(@RequestBody  UnBindBankCardJO jo) {
    	String bankId = jo.getBankID();
    	if(Strings.isNullOrEmpty(bankId)){
    		return ResultDTO.fail(TradeConstants.E_PARAMETER_NOT_NULL);
    	}
    	Integer row = appUserBankService.unBindBankCard(Integer.parseInt(bankId));
    	if(row == 1){
    		return ResultDTO.success("解绑成功");
    		
    	}else{
    		return ResultDTO.fail("解绑失败");
    	}
    }

    /**
     * 银行卡列表查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getBankCardList")
    @ApiOperation(value = "银行卡信息页-银行卡列表查询")
    public ResultDTO<List<BankListVO>> getBankJOList(@RequestBody BankListJO bankListJO) {
    	List<BankListVO> bankList = Lists.newArrayList();
    	String userId = bankListJO.getUserId();
    	if(StringUtils.isNullOrEmpty(userId)){
    		return ResultDTO.fail(TradeConstants.E_PARAMETER_NOT_NULL);
    	}
    	List<AppUserBankDO> list = appUserBankService.getBankList(userId);
    	if(list.size()>0){
    		for (AppUserBankDO appUserBankDO : list) {
    			BankListVO vo = new BankListVO();
    			int length = appUserBankDO.getAccountNo().length();
    			String transCardNo = appUserBankDO.getAccountNo();
    			if(length>10){
    				transCardNo = transCardNo.substring(0, 6)+"******"+transCardNo.substring(length-4);
    			}
    			vo.setCardNum(transCardNo);//卡号
    			vo.setBankName(appUserBankDO.getBank_name());//银行卡名称
    			//转换卡类型
    			vo.setType(TradeConstants.BankTypeEnum.getNameByCode(appUserBankDO.getType()));
    			bankList.add(vo);
			}
    	}
    	return ResultDTO.success(bankList);
    }
}
