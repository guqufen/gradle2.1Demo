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
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.MerChantCoreDTO;
import net.fnsco.order.api.dto.MerChantCoreDetailDTO;
import net.fnsco.order.api.dto.MerTerminalsDTO;
import net.fnsco.order.api.dto.MerchantDTO;
import net.fnsco.order.api.dto.PosDetailDTO;
import net.fnsco.order.api.dto.PosInfoDTO;
import net.fnsco.order.api.dto.PosListDTO;
import net.fnsco.order.api.dto.TerminalDetailDTO;
import net.fnsco.order.api.dto.TerminalsDTO;
import net.fnsco.order.api.dto.TradeMerchantDTO;
import net.fnsco.order.api.merchant.MerchantService;
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

    /**
     * 关联商铺
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/addMerChant")
    @ApiOperation(value = "关联商铺")
    public ResultDTO addMerChant(@RequestBody MerchantDTO merchant) {
        ResultDTO result = merchantService.addMerChant(merchant);
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
     * getAllTerminal:(这里用一句话描述这个方法的作用) 查询用户终端信息
     *
     * @param userMerchant
     * @return    设定文件
     * @return ResultDTO<List<MerchantCore>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/getAllTerminal")
    @ApiOperation(value = "APP用户查询设备列表")
    public ResultDTO<List<MerTerminalsDTO>> getAllTerminal(@RequestBody UserMerchantJO userMerchant) {
        return merchantService.getMerchantTerminalByUserId(userMerchant.getUserId());
    }
    
    /**
     * getTerminalDetail:(这里用一句话描述这个方法的作用)查询终端详情
     *
     * @param userMerchant
     * @return    设定文件
     * @return ResultDTO<TerminalDetailDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/getTerminalDetail")
    @ApiOperation(value = "APP用户查询设备详情")
    public ResultDTO<TerminalDetailDTO> getTerminalDetail(@RequestBody UserMerchantJO userMerchant){
        return merchantService.getTerminalDetailByTerId(userMerchant.getTerId());
    }
    
    /**
     * updateTerName:(这里用一句话描述这个方法的作用)修改设备名称
     *
     * @param terminalJO
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/updatetTerminalName")
    @ApiOperation(value = "APP用户修改设备信息")
    public ResultDTO<TerminalsDTO> updateTerName(@RequestBody TerminalJO terminalJO){
        logger.info("APP修改设备名称操作!");
        TerminalsDTO terminalsDTO = new TerminalsDTO();
        terminalsDTO.setId(terminalJO.getTerId());
        terminalsDTO.setTermName(terminalJO.getTermName());
        return merchantService.updateTerminal(terminalsDTO);
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
       List<TradeMerchantDTO> datas =  merchantService.getShopOwnerMerChant(merchant);
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
}
