package net.fnsco.controller.app.merchat;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.MerChantCoreDTO;
import net.fnsco.api.dto.MerChantCoreDetailDTO;
import net.fnsco.api.dto.MerTerminalsDTO;
import net.fnsco.api.dto.MerchantDTO;
import net.fnsco.api.dto.TerminalDetailDTO;
import net.fnsco.api.dto.TerminalsDTO;
import net.fnsco.api.merchant.MerchantService;
import net.fnsco.controller.app.jo.TerminalJO;
import net.fnsco.controller.app.jo.UserMerchantJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

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
}
