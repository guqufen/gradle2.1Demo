package net.fnsco.order.controller.web.merchant;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.WebMerchantPosDTO;
import net.fnsco.order.api.merchant.MerchantPosService;

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
    public ResultDTO<String> toAddPosInfos(@RequestBody WebMerchantPosDTO[] posInfos){
        List<WebMerchantPosDTO> params = Arrays.asList(posInfos);
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
}
