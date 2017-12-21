package net.fnsco.web.controller.e789.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.service.modules.appuser.AppUserServiceImpl;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.bank.AppUserBankService;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.web.controller.e789.bank.juhedemo.JuheDemo;
import net.fnsco.web.controller.e789.jo.BankListJO;
import net.fnsco.web.controller.e789.jo.BindBankCardJO;
import net.fnsco.web.controller.e789.jo.UnBindBankCardJO;
import net.fnsco.web.controller.e789.jo.ValidateBankJO;
import net.fnsco.web.controller.e789.vo.BankListVO;
import net.fnsco.web.controller.e789.vo.BindBankCardVO;
import net.fnsco.web.controller.e789.vo.UnBindBankCardVO;
import net.fnsco.web.controller.e789.vo.ValidateBankVO;

/**
 * 银行卡相关接口
 * @deprecated 
 * @author   binghui.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月21日 下午4:47:42
 *
 */
@RestController
@RequestMapping(value = "/app2c/bankcard", method = RequestMethod.POST)
@Api(value = "/app2c/bankcard", tags = { "我的-银行卡相关功能接口" })
public class BankCardController extends BaseController {
	@Autowired
	private AppUserBankService appUserBankService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private Environment env;
	


	@RequestMapping(value = "/validateBank")
	@ApiOperation(value = "银行卡信息页-校验银行卡")
	public ResultDTO<ValidateBankVO> validateBank(@RequestBody ValidateBankJO jo) {
		ValidateBankVO vo = new ValidateBankVO();
		// 根据userid 获取身份证号
		String idcard = appUserService.getIdAuth(jo.getUserId());
		String strUrl = env.getProperty("jh.bank.validate.url");
		String method = "POST";
		Map<String, String> params = new HashMap<>();
		params.put("key", env.getProperty("jh.bank.appkey"));
		params.put("realname", jo.getName());// 名字
		params.put("idcard", idcard);// 身份证
		params.put("bankcard", jo.getCardNum());// 卡号
		params.put("mobile", jo.getMobile());// 预留手机号\
		JSONObject jaJsonObject = new JSONObject();
		try {
			String result = JuheDemo.net(strUrl, params, method);
			jaJsonObject = JSONObject.parseObject(result);
			Integer error_code = jaJsonObject.getInteger("error_code");
			if(error_code == 0){
				JSONObject jaJsonObject2 = jaJsonObject.getJSONObject("result");
				if(jaJsonObject2 != null){
					vo.setError_code(error_code);
					vo.setReason(jaJsonObject.getString("reason"));
					vo.setMessage(jaJsonObject2.getString("message"));
					vo.setRes(jaJsonObject2.getString("res"));
					if("1".equals(jaJsonObject2.getString("res"))){
						return ResultDTO.success(vo); 
						
					}else{
						return ResultDTO.fail(vo);
					}
						
				}else{
					vo.setError_code(error_code);
					vo.setReason(jaJsonObject.getString("reason"));
					return ResultDTO.fail(vo);
				}
			}else{
				vo.setError_code(error_code);
				vo.setReason(jaJsonObject.getString("reason"));
				return ResultDTO.fail(vo);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.fail();
	}

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
		// 判断是否已进行身份认证
		if (Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(mobile) || Strings.isNullOrEmpty(bankCardNum)
				|| Strings.isNullOrEmpty(bankCardholder)) {
			return ResultDTO.fail(TradeConstants.E_PARAMETER_NOT_NULL);
		}
		String id_card_num = appUserService.getIdAuth(Integer.parseInt(userId));
		if (Strings.isNullOrEmpty(id_card_num)) {
			return ResultDTO.fail(TradeConstants.NOT_ID_AUTH);// 未认证
		}
		//验证码校验
		appUserService.validateCode(jo.getDeviceId(), jo.getCode(), "2"+jo.getMobile());
		// 保存银行卡信息
		Integer row = appUserBankService.doAppAdd(Integer.parseInt(userId), mobile, bankCardNum, bankCardholder,
				id_card_num);
		if (row == 1) {
			return ResultDTO.success();
		} else {
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
	public ResultDTO<UnBindBankCardVO> deleteBankJO(@RequestBody UnBindBankCardJO jo) {
		String bankId = jo.getBankID();
		if (Strings.isNullOrEmpty(bankId)) {
			return ResultDTO.fail(TradeConstants.E_PARAMETER_NOT_NULL);
		}
		Integer row = appUserBankService.unBindBankCard(Integer.parseInt(bankId));
		if (row == 1) {
			return ResultDTO.success("解绑成功");

		} else {
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
		if (StringUtils.isNullOrEmpty(userId)) {
			return ResultDTO.fail(TradeConstants.E_PARAMETER_NOT_NULL);
		}
		List<AppUserBankDO> list = appUserBankService.getBankList(userId);
		if (list.size() > 0) {
			for (AppUserBankDO appUserBankDO : list) {
				BankListVO vo = new BankListVO();
				int length = appUserBankDO.getAccountNo().length();
				String transCardNo = appUserBankDO.getAccountNo();
				if (length > 10) {
					transCardNo = transCardNo.substring(0, 6) + "******" + transCardNo.substring(length - 4);
				}
				vo.setCardNum(transCardNo);// 卡号
				vo.setBankName(appUserBankDO.getBankName());// 银行卡名称
				// 转换卡类型
				vo.setType(TradeConstants.BankTypeEnum.getNameByCode(appUserBankDO.getType()));
				vo.setId(appUserBankDO.getId());
				bankList.add(vo);
			}
		}
		return ResultDTO.success(bankList);
	}
}
