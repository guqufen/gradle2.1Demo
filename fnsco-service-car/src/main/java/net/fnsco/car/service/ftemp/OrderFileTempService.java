package net.fnsco.car.service.ftemp;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.car.service.ftemp.dao.OrderFileTempDAO;
import net.fnsco.car.service.ftemp.entity.OrderFileTempDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OrderFileTempService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private OrderFileTempDAO orderFileTempDAO;

 // 分页
 public ResultPageDTO<OrderFileTempDO> page(OrderFileTempDO orderFileTemp, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询OrderFileTempService.page, orderFileTemp=" + orderFileTemp.toString());
     List<OrderFileTempDO> pageList = this.orderFileTempDAO.pageList(orderFileTemp, pageNum, pageSize);
     Integer count = this.orderFileTempDAO.pageListCount(orderFileTemp);
   ResultPageDTO<OrderFileTempDO> pager =  new ResultPageDTO<OrderFileTempDO>(count,pageList);
     return pager;
 }

 // 添加
 public OrderFileTempDO doAdd (OrderFileTempDO orderFileTemp,int loginUserId) {
     logger.info("开始添加OrderFileTempService.add,orderFileTemp=" + orderFileTemp.toString());
     this.orderFileTempDAO.insert(orderFileTemp);
     return orderFileTemp;
 }

 // 修改
 public Integer doUpdate (OrderFileTempDO orderFileTemp,Integer loginUserId) {
     logger.info("开始修改OrderFileTempService.update,orderFileTemp=" + orderFileTemp.toString());
     int rows=this.orderFileTempDAO.update(orderFileTemp);
     return rows;
 }

 // 删除
 public Integer doDelete (OrderFileTempDO orderFileTemp,Integer loginUserId) {
     logger.info("开始删除OrderFileTempService.delete,orderFileTemp=" + orderFileTemp.toString());
     int rows=this.orderFileTempDAO.deleteById(orderFileTemp.getId());
     return rows;
 }

 // 查询
 public OrderFileTempDO doQueryById (Integer id) {
     OrderFileTempDO obj = this.orderFileTempDAO.getById(id);
     return obj;
 }
}