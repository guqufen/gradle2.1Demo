package net.fnsco.car.service.file;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.car.service.file.dao.OrderFileDAO;
import net.fnsco.car.service.file.entity.OrderFileDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OrderFileService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private OrderFileDAO orderFileDAO;

 // 分页
 public ResultPageDTO<OrderFileDO> page(OrderFileDO orderFile, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询OrderFileService.page, orderFile=" + orderFile.toString());
     List<OrderFileDO> pageList = this.orderFileDAO.pageList(orderFile, pageNum, pageSize);
     Integer count = this.orderFileDAO.pageListCount(orderFile);
   ResultPageDTO<OrderFileDO> pager =  new ResultPageDTO<OrderFileDO>(count,pageList);
     return pager;
 }

 // 添加
 public OrderFileDO doAdd (OrderFileDO orderFile,int loginUserId) {
     logger.info("开始添加OrderFileService.add,orderFile=" + orderFile.toString());
     this.orderFileDAO.insert(orderFile);
     return orderFile;
 }

 // 修改
 public Integer doUpdate (OrderFileDO orderFile,Integer loginUserId) {
     logger.info("开始修改OrderFileService.update,orderFile=" + orderFile.toString());
     int rows=this.orderFileDAO.update(orderFile);
     return rows;
 }

 // 删除
 public Integer doDelete (OrderFileDO orderFile,Integer loginUserId) {
     logger.info("开始删除OrderFileService.delete,orderFile=" + orderFile.toString());
     int rows=this.orderFileDAO.deleteById(orderFile.getId());
     return rows;
 }

 // 查询
 public OrderFileDO doQueryById (Integer id) {
     OrderFileDO obj = this.orderFileDAO.getById(id);
     return obj;
 }
}