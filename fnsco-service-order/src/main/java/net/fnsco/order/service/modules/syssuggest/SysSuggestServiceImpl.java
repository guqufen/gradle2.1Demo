package net.fnsco.order.service.modules.syssuggest;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.dto.SysSuggestDTO;
import net.fnsco.order.api.syssuggest.SysSuggestService;
import net.fnsco.order.service.dao.master.SysSuggestDao;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysSuggestServiceImpl extends BaseService implements SysSuggestService {
	 	@Autowired
	    private SysSuggestDao                     syssuggestDao;
	@Override
	public ResultPageDTO<SysSuggestDTO> queryPageList(SysSuggestDTO record, Integer currentPageNum, Integer perPageSize) {
		  //分页器实例化     实例化当前页和每条显示的记录数 还有传过来的参数  手机号和店铺名等条件称封装到一个实体里面
        	String start=record.getStartTime();
        	String end=record.getEndTime();
        if(start!=null){
        	start=start+" 00:00:00";
//        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");//设置日期格式
//        	df.format(start);
        }
        if(end!=null){
        	end=end+" 23:59:59";
//        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");//设置日期格式
//        	df.format(end);
        }
        PageDTO<SysSuggestDTO> params = new PageDTO<SysSuggestDTO>(currentPageNum, perPageSize, record);
        //调用Dao方法时可以使用上面封装的实体         AppUserManageDTO即使用户返回给用户的实体 也用来传递参数
        List<SysSuggestDTO> data = syssuggestDao.queryPageList(params);
        //返回根据条件查询的所有记录条数
        int totalNum = syssuggestDao.queryTotalByCondition(record);
        //返回给页面总条数和每页查询的数据
        ResultPageDTO<SysSuggestDTO> result = new ResultPageDTO<SysSuggestDTO>(totalNum, data);
        return result;
	}
	/**
	 * 根据ID查询出所有关联的数据
	 */
	@Override
	public ResultDTO<SysSuggestDTO> queryById(Integer id) {
		// TODO Auto-generated method stub
		ResultDTO<SysSuggestDTO> result = new ResultDTO<SysSuggestDTO>();
        result.setData(syssuggestDao.queryById(id));
        return result;
	}
    

}
