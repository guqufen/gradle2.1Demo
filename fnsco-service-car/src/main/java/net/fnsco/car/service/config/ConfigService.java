package net.fnsco.car.service.config;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.car.service.config.dao.ConfigDAO;
import net.fnsco.car.service.config.entity.ConfigDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class ConfigService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private ConfigDAO configDAO;

 //查询所有保险公司列表
 public List<ConfigDO> queryAll() {
	 return configDAO.getAll();
 }
 //根据保险公司名字查询id
 public Integer queryIdByName(String name) {
	 return configDAO.getId(name);
 }
 //查询首页总金额以及销售量
 public ConfigDO queryIndex() {
	 return configDAO.getIndex();
 }
 // 分页
 public ResultPageDTO<ConfigDO> page(ConfigDO config, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询ConfigService.page, config=" + config.toString());
     List<ConfigDO> pageList = this.configDAO.pageList(config, pageNum, pageSize);
     Integer count = this.configDAO.pageListCount(config);
   ResultPageDTO<ConfigDO> pager =  new ResultPageDTO<ConfigDO>(count,pageList);
     return pager;
 }

 // 添加
 public ConfigDO doAdd (ConfigDO config,int loginUserId) {
     logger.info("开始添加ConfigService.add,config=" + config.toString());
     this.configDAO.insert(config);
     return config;
 }

 // 修改
 public Integer doUpdate (ConfigDO config,Integer loginUserId) {
     logger.info("开始修改ConfigService.update,config=" + config.toString());
     int rows=this.configDAO.update(config);
     return rows;
 }

 // 删除
 public Integer doDelete (ConfigDO config,Integer loginUserId) {
     logger.info("开始删除ConfigService.delete,config=" + config.toString());
     int rows=this.configDAO.deleteById(config.getId());
     return rows;
 }

 // 查询
 public ConfigDO doQueryById (Integer id) {
     ConfigDO obj = this.configDAO.getById(id);
     return obj;
 }
}