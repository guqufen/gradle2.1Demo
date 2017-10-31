package net.fnsco.web.controller.app.merchat.mershop;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.constant.MerShopDTO;
import net.fnsco.bigdata.api.dto.MerchantShopDTO;
import net.fnsco.bigdata.api.merchant.MerchantShopService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantShop;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc 商户店铺管理接口控制器
 * @author   tangliang
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午3:21:59
 */
@RestController
@RequestMapping(value = "/app/merchantshop/", method = RequestMethod.POST)
@Api(value = "/app/merchant/shop", tags = { "商户店铺管理" })
public class AppMerchantShopController extends BaseController {
	
	@Autowired
	private MerchantEntityDao merchantEntityDao;
	
	@Autowired
	private MerchantShopService merchantShopService;
	/**
	 * queryAllMerchantEntity:(查询商户实体列表)
	 *
	 * @param  @param merchant
	 * @param  @return    设定文件
	 * @return ResultDTO<MerchantEntityDTO>    DOM对象
	 * @author tangliang
	 * @date   2017年10月26日 下午3:28:23
	 */
	 @RequestMapping(value = "/queryAllMerchantShop")
	 @ApiOperation(value = "查询所有商户实体店铺列表")
	public ResultDTO<List<MerchantShopDTO>> queryAllMerchantShop(@RequestBody MerShopDTO merchant) {
		
		 if(null == merchant.getUserId()) {
			 return ResultDTO.fail(BigdataConstant.E_USERID_NULL);
		 }
		 List<MerchantShopDTO> datas = merchantEntityDao.queryAllShopDetail(merchant.getUserId());
		 return ResultDTO.success(datas);
	}
	 
	 /**
	  * addMerchantShop:(增加店铺接口)
	  *
	  * @param  @param merchant
	  * @param  @return    设定文件
	  * @return ResultDTO<List<MerchantShopDTO>>    DOM对象
	  * @author tangliang
	  * @date   2017年10月31日 下午4:16:06
	  */
	 @RequestMapping(value = "/addMerchantShop")
	 @ApiOperation(value = "增加店铺接口")
	public ResultDTO<List<MerchantShopDTO>> addMerchantShop(@RequestBody MerShopDTO merchant) {
		
		 if(Strings.isNullOrEmpty(merchant.getEntityInnerCode())) {
			 return ResultDTO.fail(BigdataConstant.APP_MER_ENTITY_INNERCODE_NULL);
		 }
		 
		 MerchantShop recored = new MerchantShop();
		 recored.setAddress(merchant.getAddress());
		 recored.setArea(merchant.getArea());
		 recored.setEntityInnerCode(merchant.getEntityInnerCode());
		 recored.setCreateTimer(new Date());
		 recored.setCreateSource("1");//APP
		 recored.setCreateUserId(getUserId());
		 recored.setLastModefyTimer(new Date());
		 recored.setLastModefyUserId(getUserId());
		 recored.setShopName(merchant.getShopName());
		 recored.setStatus(1);//正常状态
		 recored.setShopInnerCode(merchantShopService.getMerShopInnerCode());
		 int res = merchantShopService.insertSelective(recored);
		 if(res >0) {
			 return ResultDTO.success();
		 }
		 return ResultDTO.fail();
	}
}
