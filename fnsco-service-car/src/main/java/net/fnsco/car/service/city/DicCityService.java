package net.fnsco.car.service.city;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.car.service.city.dao.DicCityDAO;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DicCityService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DicCityDAO dicCityDAO;

 // 分页
 public ResultPageDTO<DicCityDO> page(DicCityDO dicCity, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DicCityService.page, dicCity=" + dicCity.toString());
     List<DicCityDO> pageList = this.dicCityDAO.pageList(dicCity, pageNum, pageSize);
     Integer count = this.dicCityDAO.pageListCount(dicCity);
   ResultPageDTO<DicCityDO> pager =  new ResultPageDTO<DicCityDO>(count,pageList);
     return pager;
 }

 // 添加
 public DicCityDO doAdd (DicCityDO dicCity,int loginUserId) {
     logger.info("开始添加DicCityService.add,dicCity=" + dicCity.toString());
     this.dicCityDAO.insert(dicCity);
     return dicCity;
 }

 // 修改
 public Integer doUpdate (DicCityDO dicCity,Integer loginUserId) {
     logger.info("开始修改DicCityService.update,dicCity=" + dicCity.toString());
     int rows=this.dicCityDAO.update(dicCity);
     return rows;
 }

 // 删除
 public Integer doDelete (DicCityDO dicCity,Integer loginUserId) {
     logger.info("开始删除DicCityService.delete,dicCity=" + dicCity.toString());
     int rows=this.dicCityDAO.deleteById(dicCity.getId());
     return rows;
 }

 // 查询
 public DicCityDO doQueryById (Integer id) {
     DicCityDO obj = this.dicCityDAO.getById(id);
     return obj;
 }

public List<DicCityDO> queryCityList() {
	List<DicCityDO> list = this.dicCityDAO.queryAll();
	return list;
}
}