package net.fnsco.web.controller.merchant;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.WebMerchantPosDTO;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.web.controller.merchant.jo.MerchantChannelJO;
import net.fnsco.web.controller.merchant.jo.PosJO;

/**
 * @desc Pos相关业务控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月17日 上午9:33:47
 */
@Controller
@RequestMapping(value = "/web/merchantpos")
public class MerchantPosController extends BaseController {
    
    @Autowired
    private MerchantPosService merchantPosService;
    
    /**
     * toAddTerminal:(这里用一句话描述这个方法的作用)添加POS信息
     * @param posInfos
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 上午11:37:59
     * @return ResultDTO<String>    DOM对象
     */
    @RequestMapping(value ="/toAddPosInfos",method= RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = { "m:merchant:add"})
    public ResultDTO<String> toAddPosInfos(@RequestBody PosJO pos){
       // List<MerchantChannelJO> temp = Arrays.asList(posInfos);
    	List<MerchantChannelJO> posInfos =pos.getPoses();
    	WebUserDTO obj = (WebUserDTO) session.getAttribute(FrameworkConstant.SESSION_USER_KEY);
        List<WebMerchantPosDTO> params = MerchantHelper.toPosDTO(posInfos,pos.getInnerCode(),obj.getId());
        ResultDTO<String> result = merchantPosService.savePosInfo(params);
        return result;
    }
    
    /**
     * deletePosInfo:(这里用一句话描述这个方法的作用)删除POS机信息
     * @param id
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:11:23
     * @return ResultDTO<String>    DOM对象
     */
    @RequestMapping(value = "/deletePosInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = { "m:merchant:delete" })
    public ResultDTO deletePosInfo(@RequestParam(value="id") Integer id){
        if (null == id) {
            return ResultDTO.fail();
        }
        boolean result = merchantPosService.deleteByPosId(id);
        if(!result){
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }
    
    /**
     * getPosInfo:(这里用一句话描述这个方法的作用)获取所有银行卡信息
     * @param innerCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月18日 下午5:42:15
     * @return ResultDTO    DOM对象
     */
    @RequestMapping(value = "/getBankInfo",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:merchant:list" })
    public ResultDTO<List<MerchantBank>> getBankInfo(@RequestParam(value="innerCode") String innerCode){
        if (Strings.isNullOrEmpty(innerCode)) {
            return ResultDTO.fail();
        }
        List<MerchantBank> datas = merchantPosService.queryWebByInnerCode(innerCode);
        return ResultDTO.success(datas);
    }
}
