package net.fnsco.web.controller.app.merchat.mershop;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.MerEntityDTO;
import net.fnsco.bigdata.api.dto.MerShopDTO;
import net.fnsco.bigdata.api.dto.MerchantShopDTO;
import net.fnsco.bigdata.api.merchant.MerchantShopService;
import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantShop;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.merchant.IntegralLogService;

/**
 * @desc 商户店铺管理接口控制器
 * @author tangliang
 * @since Ver 1.1
 * @Date 2017年10月26日 下午3:21:59
 */
@RestController
@RequestMapping(value = "/app/merchantshop/", method = RequestMethod.POST)
@Api(value = "/app/merchantshop/", tags = { "商户店铺管理" })
public class AppMerchantShopController extends BaseController {

	@Autowired
	private MerchantEntityDao merchantEntityDao;

	@Autowired
	private MerchantShopService merchantShopService;
	
	@Autowired
	private IntegralLogService integralRuleLogService;
	/**
	 * queryAllMerchantEntity:(查询商户实体列表)
	 *
	 * @param @param
	 *            merchant
	 * @param @return
	 *            设定文件
	 * @return ResultDTO<MerchantEntityDTO> DOM对象
	 * @author tangliang
	 * @date 2017年10月26日 下午3:28:23
	 */
	@RequestMapping(value = "/queryAllMerchantShop")
	@ApiOperation(value = "查询所有商户实体店铺列表")
	public ResultDTO<List<MerchantShopDTO>> queryAllMerchantShop(@RequestBody MerShopDTO merchant) {

		if (null == merchant.getUserId()) {
			return ResultDTO.fail(BigdataConstant.E_USERID_NULL);
		}
		List<MerchantShopDTO> datas = merchantEntityDao.queryAllShopDetail(merchant.getUserId());
		Iterator<MerchantShopDTO> it = datas.iterator();
		while(it.hasNext()){
			MerchantShopDTO x = it.next();
		    if(CollectionUtils.isEmpty(x.getEntitys())){
		        it.remove();
		    }
		}
		return ResultDTO.success(datas);
	}
	
	/**
	 * queryMerEntityList:(查询所有商户实体列表)
	 *
	 * @param  @param merchant
	 * @param  @return    设定文件
	 * @return ResultDTO<List<MerEntityDTO>>    DOM对象
	 * @author tangliang
	 * @date   2017年11月1日 下午2:56:28
	 */
	@RequestMapping(value = "/queryMerEntityList")
	@ApiOperation(value = "查询所有商户实体列表")
	public ResultDTO<List<MerEntityDTO>> queryMerEntityList(@RequestBody MerShopDTO merchant) {

		if (null == merchant.getUserId()) {
			return ResultDTO.fail(BigdataConstant.E_USERID_NULL);
		}
		List<MerEntityDTO> datas = merchantEntityDao.queryAllMerEntity(merchant.getUserId());
		return ResultDTO.success(datas);
	}
	
	/**
	 * addMerchantShop:(增加店铺接口)
	 *
	 * @param @param
	 *            merchant
	 * @param @return
	 *            设定文件
	 * @return ResultDTO<List<MerchantShopDTO>> DOM对象
	 * @author tangliang
	 * @date 2017年10月31日 下午4:16:06
	 */
	@Transactional
	@RequestMapping(value = "/addMerchantShop")
	@ApiOperation(value = "增加店铺信息接口")
	public ResultDTO<List<MerchantShopDTO>> addMerchantShop(@RequestBody MerShopDTO merchant) {

		if (Strings.isNullOrEmpty(merchant.getEntityInnerCode())) {
			return ResultDTO.fail(BigdataConstant.APP_MER_ENTITY_INNERCODE_NULL);
		}

		MerchantShop recored = new MerchantShop();
		recored.setAddress(merchant.getAddress());
		recored.setArea(merchant.getArea());
		recored.setEntityInnerCode(merchant.getEntityInnerCode());
		recored.setCreateTimer(new Date());
		recored.setCreateSource("1");// APP
		recored.setCreateUserId(getUserId());
		recored.setLastModefyTimer(new Date());
		recored.setLastModefyUserId(getUserId());
		recored.setShopName(merchant.getShopName());
		recored.setStatus(1);// 正常状态
		recored.setShopInnerCode(merchantShopService.getMerShopInnerCode());
		int res = merchantShopService.insertSelective(recored);
		if (res > 0) {
			//进行积分处理
			integralRuleLogService.insert(merchant.getEntityInnerCode(), ConstantEnum.IntegralTypeEnum.CODE_LR.getCode());
			return ResultDTO.successForSubmit();
		}
		return ResultDTO.fail();
	}

	/**
	 * updateMerchantShop:(更新商户店铺信息)
	 * 
	 * @param @param
	 *            merchant
	 * @param @return
	 *            设定文件
	 * @return ResultDTO<List<MerchantShopDTO>> DOM对象
	 * @author tangliang
	 * @date 2017年11月1日 上午10:00:50
	 */
	@RequestMapping(value = "/updateMerchantShop")
	@ApiOperation(value = "更新店铺信息接口")
	public ResultDTO<List<MerchantShopDTO>> updateMerchantShop(@RequestBody MerShopDTO merchant) {

		if (Strings.isNullOrEmpty(merchant.getShopInnerCode())) {
			return ResultDTO.fail(BigdataConstant.APP_MER_SHOP_INNERCODE_NULL);
		}

		MerchantShop recored = new MerchantShop();
		recored.setAddress(merchant.getAddress());
		recored.setArea(merchant.getArea());
		recored.setEntityInnerCode(merchant.getEntityInnerCode());
		recored.setLastModefyUserId(getUserId());
		recored.setLastModefyTimer(new Date());
		recored.setShopName(merchant.getShopName());
		recored.setShopInnerCode(merchant.getShopInnerCode());
		int res = merchantShopService.updateByShopInnerCodeSelective(recored);
		if (res > 0) {
			return ResultDTO.successForSubmit();
		}
		return ResultDTO.fail();
	}
	
	/**
	 * deleteMerchantShop:(删除店铺信息接口)
	 *
	 * @param  @param merchant
	 * @param  @return    设定文件
	 * @return ResultDTO<List<MerchantShopDTO>>    DOM对象
	 * @author tangliang
	 * @date   2017年11月1日 上午11:39:26
	 */
	@RequestMapping(value = "/deleteMerchantShop")
	@ApiOperation(value = "删除店铺信息接口")
	public ResultDTO<List<MerchantShopDTO>> deleteMerchantShop(@RequestBody MerShopDTO merchant) {

		if (Strings.isNullOrEmpty(merchant.getShopInnerCode())) {
			return ResultDTO.fail(BigdataConstant.APP_MER_SHOP_INNERCODE_NULL);
		}

		int res = merchantShopService.deleteByShopInnerCode(merchant.getShopInnerCode());
		if (res > 0) {
			return ResultDTO.success();
		}
		return ResultDTO.fail();
	}
}
