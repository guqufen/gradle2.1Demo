package net.fnsco.risk.service.product;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.product.dao.ProductDAO;
import net.fnsco.risk.service.product.entity.ProductDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class ProductService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private ProductDAO productDAO;

 // 分页
 public ResultPageDTO<ProductDO> page(ProductDO product, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询ProductService.page, product=" + product.toString());
     List<ProductDO> pageList = this.productDAO.pageList(product, pageNum, pageSize);
     Integer count = this.productDAO.pageListCount(product);
     ResultPageDTO<ProductDO> pager =  new ResultPageDTO<ProductDO>(count,pageList);
     return pager;
 }

 // 添加
 public ProductDO doAdd (ProductDO product,int loginUserId) {
     logger.info("开始添加ProductService.add,product=" + product.toString());
     this.productDAO.insert(product);
     return product;
 }

 // 修改
 public Integer doUpdate (ProductDO product,Integer loginUserId) {
     logger.info("开始修改ProductService.update,product=" + product.toString());
     int rows=this.productDAO.update(product);
     return rows;
 }

 // 删除
 public Integer doDelete (ProductDO product,Integer loginUserId) {
     logger.info("开始删除ProductService.delete,product=" + product.toString());
     int rows=this.productDAO.deleteById(product.getId());
     return rows;
 }

 // 查询
 public ProductDO doQueryById (Integer id) {
     ProductDO obj = this.productDAO.getById(id);
     return obj;
 }
}