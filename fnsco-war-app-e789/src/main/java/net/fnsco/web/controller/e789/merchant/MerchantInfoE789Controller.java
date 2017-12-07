package net.fnsco.web.controller.e789.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.service.dao.master.MerchantBankDao;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.merchantentity.AppUserMerchantEntityService;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.vo.MerchantInfoVO;

/**
 * @desc 商户信息控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:09:43
 */
@RestController
@RequestMapping(value = "/app2c/merchant", method = RequestMethod.POST)
@Api(value = "/app2c/merchantInfo", tags = { "我的-商户信息相关功能接口" })
public class MerchantInfoE789Controller extends BaseController {
	
	@Autowired
	private AppUserMerchantEntityService appUserMerchantEntityService;
	@Autowired
	private MerchantBankDao merchantBankDao;
	/**
	 * queryBalance:(获取商户信息)
	 *
	 * @param  @param commonJO
	 * @param  @return    设定文件
	 * @return ResultDTO<MerchantInfoVO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 上午10:18:24
	 */
	@RequestMapping(value = "/queryMerchant")
    @ApiOperation(value = "我的-商户信息-获取商户信息接口")
    public ResultDTO<MerchantInfoVO> queryBalance(@RequestBody CommonJO commonJO) {
		if(null == commonJO.getUserId()) {
 			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
 		}
		
		MerchantInfoVO resultVO =  new MerchantInfoVO();
		MerchantEntity merchantEntity = appUserMerchantEntityService.queryMerInfoByUserId(commonJO.getUserId());
		if(null != merchantEntity) {
			resultVO.setBusinessLicenseNum(merchantEntity.getBusinessLicenseNum());
			resultVO.setLegalPerson(merchantEntity.getLegalPerson());
			resultVO.setLegalPersonMobile(merchantEntity.getLegalPersonMobile());
			resultVO.setMerName(merchantEntity.getMercName());
			resultVO.setCardNum(merchantEntity.getCardNum());
			
		}
		MerchantBank merchantBank = merchantBankDao.selectByAppUserId(commonJO.getUserId());
		if(null != merchantBank) {
			resultVO.setOpenBank(merchantBank.getOpenBank());
			resultVO.setAccountNo(merchantBank.getAccountNo());
		}
        return success(resultVO);
    }
}
