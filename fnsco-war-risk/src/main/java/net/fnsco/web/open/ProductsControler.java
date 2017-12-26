package net.fnsco.web.open;

import java.util.Date;
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
import net.fnsco.risk.service.product.dto.AddProductsDTO;
import net.fnsco.risk.service.product.dto.QueryProductsDTO;
import net.fnsco.risk.service.product.entity.ProductDO;
import net.fnsco.risk.service.sys.WebUserOuterService;

/**
 * 
 * @desc 
 * @author   hjt
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月25日 下午3:31:46
 *
 */
@Controller
@RequestMapping(value = "/web/products", method = RequestMethod.POST)
@Api(value = "/web/products", tags = { "前端产品管理接口" })
public class ProductsControler extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
	private WebUserOuterService userOuterService;
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
    public ResultDTO queryList(QueryProductsDTO queryProductsDTO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer pageNum = params.get("currentPageNum");
        Integer pageSize = params.get("pageSize");
    	ProductDO productDO = new ProductDO();
    	productDO.setAgentId(queryProductsDTO.getAgentId());
    	productDO.setStatus(queryProductsDTO.getStatus());
        ResultPageDTO<ProductDO> pager = this.productService.page(productDO, pageNum, pageSize);
        return success(pager);
    }
    /**
     * 
     * addProducts:(新增产品)
     *
     * @param  @param productDO
     * @param  @return    设定文件
     * @return ResultDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "新增产品" , notes = "新增产品")
    @ResponseBody
    @RequestMapping(value = "/addProducts" , method = RequestMethod.POST)
    public ResultDTO addProducts(AddProductsDTO addProductsDTO) {
    	ProductDO productDO = new ProductDO();
    	productDO.setAgentId(addProductsDTO.getAgentId());
    	productDO.setName(addProductsDTO.getName());
    	productDO.setAmountMax(addProductsDTO.getAmountMax());
    	productDO.setAmountMin(addProductsDTO.getAmountMin());
    	productDO.setCycle(addProductsDTO.getCycle());
    	productDO.setDescription(addProductsDTO.getDesc());
    	productDO.setRateMax(addProductsDTO.getRateMax());
    	productDO.setRateMin(addProductsDTO.getRateMin());
    	productDO.setStatus("1");
    	productDO.setCreateTime(new Date());
    	productDO.setLastModifyTime(new Date());
    	productService.doAdd(productDO);
    	return success();
    }
    /**
     * 
     * editProducts:(编辑产品)
     *
     * @param  @param productDO
     * @param  @return    设定文件
     * @return ResultDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "编辑产品" , notes = "编辑产品")
    @ResponseBody
    @RequestMapping(value = "/editProducts" , method = RequestMethod.POST)
    public ResultDTO editProducts(ProductDO productDO) {
    	productDO.setLastModifyTime(new Date());
    	productService.doUpdate(productDO);
    	return success();
    }
    /**
     * 
     * queryDetail:(查看详情)
     *
     * @param  @param id
     * @param  @return    设定文件
     * @return ResultDTO<ProductDO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "查看详情" , notes = "查看详情")
    @ResponseBody
    @RequestMapping(value = "/queryDetail" , method = RequestMethod.POST)
    public ResultDTO<ProductDO> queryDetail(Integer id) {
    	ProductDO product = productService.doQueryById(id);
    	return success(product);
    }
    /**
     * 
     * delete:(删除产品)
     *
     * @param  @param id
     * @param  @return    设定文件
     * @return ResultDTO<ProductDO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "删除产品" , notes = "删除产品")
    @ResponseBody
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public ResultDTO<ProductDO> delete(Integer id) {
    	productService.deleteById(id);
    	return success();
    }
    /**
     * 
     * doStart:(启动产品)
     *
     * @param  @param id
     * @param  @return    设定文件
     * @return ResultDTO<ProductDO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "启动产品" , notes = "启动产品")
    @ResponseBody
    @RequestMapping(value = "/doStart" , method = RequestMethod.POST)
    public ResultDTO<ProductDO> doStart(Integer id) {
    	productService.startById(id);
    	return success();
    }
    /**
     * 
     * doDisuse:(停用产品)
     *
     * @param  @param id
     * @param  @return    设定文件
     * @return ResultDTO<ProductDO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @ApiOperation(value = "停用产品" , notes = "停用产品")
    @ResponseBody
    @RequestMapping(value = "/doDisuse" , method = RequestMethod.POST)
    public ResultDTO<ProductDO> doDisuse(Integer id) {
    	productService.disuseById(id);
    	return success();
    }
}
