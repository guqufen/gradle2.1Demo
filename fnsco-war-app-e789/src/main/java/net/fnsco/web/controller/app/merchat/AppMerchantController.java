package net.fnsco.web.controller.app.merchat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.bigdata.api.dto.MerChantCoreDetailDTO;
import net.fnsco.bigdata.api.dto.MerTerminalsDTO;
import net.fnsco.bigdata.api.dto.MerchantDTO;
import net.fnsco.bigdata.api.dto.PosDetailDTO;
import net.fnsco.bigdata.api.dto.PosInfoDTO;
import net.fnsco.bigdata.api.dto.PosListDTO;
import net.fnsco.bigdata.api.dto.TerminalDetailDTO;
import net.fnsco.bigdata.api.dto.TerminalsDTO;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.TradeMerchantDTO;
import net.fnsco.order.api.merchant.MerchantOrderService;
import net.fnsco.web.controller.app.jo.TerminalJO;
import net.fnsco.web.controller.app.jo.UserMerchantJO;

/**
 * 
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年6月28日 下午3:45:14
 *
 */
@RestController
@RequestMapping(value = "/app/merchant", method = RequestMethod.POST)
@Api(value = "/app/merchant", tags = { "商户管理" })
public class AppMerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MerchantOrderService  merchantOrderService;

    /**
     * 关联商铺
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/addMerChant")
    @ApiOperation(value = "关联商铺")
    public ResultDTO addMerChant(@RequestBody MerchantDTO merchant) {
        ResultDTO result = merchantOrderService.addMerChant(merchant);
        return result;
    }

    /**
     * 
     * getMerChants:(这里用一句话描述这个方法的作用) 根据app用户ID查询关联的商家列表信息
     * @param userMerchant
     * @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getMerChant")
    @ApiOperation(value = "APP用户查询商家列表")
    public ResultDTO<List<MerChantCoreDTO>> getMerChants(@RequestBody UserMerchantJO userMerchant) {
        return merchantService.getMerchantsCoreByUserId(userMerchant.getUserId());
    }
    
    /**
     * getMerChantDetail:(这里用一句话描述这个方法的作用)查询商家详情
     *
     * @param userMerchant
     * @return    设定文件
     * @return ResultDTO<MerChantCoreDetailDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getMerChantDetail")
    @ApiOperation(value = "APP用户查询商家详情")
    public ResultDTO<MerChantCoreDetailDTO> getMerChantDetail(@RequestBody UserMerchantJO userMerchant) {
        return merchantService.getMerChantDetailById(userMerchant.getMerId());
    }

    /**
     * getShopOwnerMerChant:(这里用一句话描述这个方法的作用)根据userId查询是店主的商户信息
     *
     * @param merchant
     * @return    设定文件
     * @return ResultDTO<List<TradeMerchantDTO>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getShopOwnerMerChant")
    @ApiOperation(value = "APP用户查询店主商家列表")
    public ResultDTO<List<TradeMerchantDTO>> getShopOwnerMerChant(@RequestBody MerchantDTO merchant) {
       if(null == merchant.getUserId()){
           return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
       }
       List<TradeMerchantDTO> datas =  merchantOrderService.getShopOwnerMerChant(merchant);
       return ResultDTO.success(datas);
    }
    
    /**
     * getAllPosInfo:(这里用一句话描述这个方法的作用)获取所有POS机列表信息
     * @param merchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 上午10:52:27
     * @return ResultDTO<Object>    DOM对象
     */
    @RequestMapping(value = "/getAllPosInfo")
    @ApiOperation(value = "APP用户查询POS机信息列表")
    public ResultDTO<List<PosListDTO>> getAllPosInfo(@RequestBody MerchantDTO merchant){
        if(null == merchant.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        List<PosListDTO> dates = merchantService.getAllPosInfo(merchant);
        return ResultDTO.success(dates);
    }
    
    /**
     * getPosDetail:(这里用一句话描述这个方法的作用)获取单个POS机详情
     * @param merchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 上午10:53:17
     * @return ResultDTO<Object>    DOM对象
     */
    @RequestMapping(value = "/getPosDetail")
    @ApiOperation(value = "APP用户查询POS机详情")
    public ResultDTO<PosDetailDTO> getPosDetail(@RequestBody TerminalJO terminalJO){
        if(null == terminalJO.getPosId()){
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        PosDetailDTO datas = merchantService.getPosDetail(terminalJO.getPosId());
        return ResultDTO.success(datas);
    }
    
    /**
     * updatetPosInfo:(这里用一句话描述这个方法的作用)编辑单个POS机信息
     * @param merchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 上午10:54:06
     * @return ResultDTO<Object>    DOM对象
     */
    @RequestMapping(value = "/updatetPosInfo")
    @ApiOperation(value = "编辑修改POS机信息")
    public ResultDTO updatetPosInfo(@RequestBody TerminalJO terminalJO){
        if(null == terminalJO.getPosId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        if(!Strings.isNullOrEmpty(terminalJO.getPosName()) && terminalJO.getPosName().length() >19){
            return ResultDTO.fail(ApiConstant.E_STRING_TOO_LENGTH);
        }
        TerminalsDTO dto = new TerminalsDTO();
        dto.setTermName(terminalJO.getTermName());
        dto.setTerId(terminalJO.getTerId());
        dto.setPosId(terminalJO.getPosId());
        dto.setPosName(terminalJO.getPosName());
        boolean result = merchantService.updatePosInfo(dto);
        if(!result){
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }
    
    /**
     * getAllReportPos:(这里用一句话描述这个方法的作用)筛选页面获取所有POS列表
     * @param merchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月18日 下午5:02:32
     * @return ResultDTO<List<PosInfoDTO>>    DOM对象
     */
    @RequestMapping(value = "/getAllReportPos")
    @ApiOperation(value = "APP用户查询筛选页面POS机信息列表")
    public ResultDTO<List<PosInfoDTO>> getAllReportPos(@RequestBody MerchantDTO merchant){
        if(null == merchant.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        List<PosInfoDTO> dates = merchantService.getAllReportPos(merchant);
        return ResultDTO.success(dates);
    }
    
    /**
     * unBundMerchant:(解绑商户)
     * @param userMerchant
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月4日 上午11:09:11
     * @return ResultDTO<List<PosInfoDTO>>    DOM对象
     */
    @RequestMapping(value = "/unBundMerchant")
    @ApiOperation(value = "APP用户解绑商户")
    public ResultDTO<String> unBundMerchant(@RequestBody UserMerchantJO userMerchant){
        if(null == userMerchant.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        if(null == userMerchant.getMerId()){
            return ResultDTO.fail(ApiConstant.E_APP_ID_EMPTY);
        }
        boolean datas = merchantOrderService.deleteMerchantRelation(userMerchant.getMerId(), userMerchant.getUserId());
        if(!datas){
            return ResultDTO.fail();
        }
        return new ResultDTO(true, datas, ApiConstant.APP_DELETE_SUCCESS, ApiConstant.ERROR_MESSGE_MAP.get(ApiConstant.APP_DELETE_SUCCESS));
    }
}
