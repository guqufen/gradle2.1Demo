package net.fnsco.web.controller.app.merchat.mershop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.MerchantShopDTO;
import net.fnsco.bigdata.api.dto.ShareIntegralDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.merchant.IntegralLogService;
import net.fnsco.order.service.domain.IntegralLog;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年11月3日 下午5:40:06
 */
@RestController
@RequestMapping(value = "/app/shareIntegral/", method = RequestMethod.POST)
@Api(value = "/app/shareIntegral/", tags = { "分享积分管理" })
public class AppShareIntegralController extends BaseController{
	
	@Autowired
	private IntegralLogService integralRuleLogService;
	
	/**
	 * queryAllMerchantShop:(分享成功后统计积分接口)
	 *
	 * @param  @param merchant
	 * @param  @return    设定文件
	 * @return ResultDTO<List<MerchantShopDTO>>    DOM对象
	 * @author tangliang
	 * @date   2017年11月3日 下午5:44:47
	 */
	@RequestMapping(value = "/recordIntegral")
	@ApiOperation(value = "记录分享成功后积分处理")
	public ResultDTO<List<MerchantShopDTO>> queryAllMerchantShop(@RequestBody ShareIntegralDTO merchant) {

		if (null == merchant.getEntityInnerCode()) {
			return ResultDTO.fail(BigdataConstant.APP_MER_ENTITY_INNERCODE_NULL);
		}
		
		String shareType = merchant.getShareType();
		String description = "";
		if("00".equals(shareType)) {
			description = ConstantEnum.IntegralTypeEnum.CODE_YQ07.getName();
		}else if("01".equals(shareType)) {
			description = ConstantEnum.IntegralTypeEnum.CODE_YQ03.getName();
		}else if("02".equals(shareType)) {
			description = ConstantEnum.IntegralTypeEnum.CODE_YQ04.getName();
		}else if("04".equals(shareType)) {
			description = ConstantEnum.IntegralTypeEnum.CODE_YQ05.getName();
		}else if("05".equals(shareType)) {
			description = ConstantEnum.IntegralTypeEnum.CODE_YQ06.getName();
		}else {
			description = ConstantEnum.IntegralTypeEnum.CODE_YQ01.getName();
		}
		
		integralRuleLogService.insert(merchant.getEntityInnerCode(), ConstantEnum.IntegralTypeEnum.CODE_YQ01.getCode(),description);
		
		return ResultDTO.success();
	}
}
