package net.fnsco.web.open;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.product.ProductService;
import net.fnsco.risk.service.product.entity.ProductDO;
import net.fnsco.risk.service.report.ReportService;
import net.fnsco.risk.service.report.entity.ReportInfoDO;

/**
 * 
 * @deprecated 风控+前端产品管理controller
 * @author   hjt
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月25日 下午2:07:47
 *
 */
@Controller
@RequestMapping(value = "/web/products", method = RequestMethod.POST)
@Api(value = "/web/products", tags = { "前端产品管理接口" })
public class ProductsControler extends BaseController {
    @Autowired
    private ProductService productService;

    /**
     * 
     * page:(产品管理分页查询)
     *
     * @param  @param productDO
     * @param  @return    设定文件
     * @return ResultDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public ResultDTO page(ProductDO productDO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer pageNum = params.get("currentPageNum");
        Integer pageSize = params.get("pageSize");
        ResultPageDTO<ProductDO> pager = this.productService.page(productDO, pageNum, pageSize);
        return success(pager);
    }
    
}
