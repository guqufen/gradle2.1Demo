/**
 * 
 */
package net.fnsco.finance.service.modules.finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.finance.api.dto.AppUserEntityDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceEveryDayDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.finance.AppFinanceService;
import net.fnsco.finance.service.dao.master.FinanceAccountBookDao;
import net.fnsco.finance.service.domain.FinanceAccountBook;
import net.fnsco.finance.service.domain.FinanceIoType;


/**
 * 记账相关实现类
 * @author hjt
 *
 */
@Service
public class AppFinanceServiceImpl extends BaseService implements AppFinanceService{
	
	@Autowired
	private FinanceAccountBookDao financeAccountBookDao;
	
	/**
	 * 记账首页每日数据分页查询（每次显示5个）
	 */
	@Override
	public ResultDTO<FinanceBookKeepingDTO> queryFinanceDayList(FinanceQueryDTO financeQuery) {
		String Dates = financeQuery.getDates();
		FinanceBookKeepingDTO financeBookKeepingDTO	= new FinanceBookKeepingDTO();
		financeBookKeepingDTO.setEntityInnerCode(financeQuery.getEntityInnerCode());
		int mouth =0;
		if(!Dates.equals(null)) {
			SimpleDateFormat in = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat out = new SimpleDateFormat("yyyyMM");
			Date nowTime=new Date();
			String nowDate =out.format(nowTime);
			String format=null;
			try {
				format = out.format(in.parse(Dates));
			}catch (ParseException e) {
				logger.error("日期格式转换出错" + format  + ",确保真确格式");
				e.printStackTrace();
			}
			int date =0;
			int now = 0;
			try {
			    date = Integer.valueOf(format).intValue();
			    now  = Integer.valueOf(nowDate).intValue();
			} catch (NumberFormatException e) {
				logger.error("日期格式转换出错" + date+ now + ",确保真确格式");
			    e.printStackTrace();
			}
			mouth = date-now;
		}else {
			Calendar calendar=Calendar.getInstance();
			//获得当前时间的年月，月份从0开始所以结果要加1
			financeBookKeepingDTO.setYear(calendar.get(Calendar.YEAR));
			financeBookKeepingDTO.setMonth(calendar.get(Calendar.MONTH)+1);
		}
		Date startTime = DateUtils.getMouthStartTime(mouth);
		Date endTime = DateUtils.getMouthEndTime(mouth);
		financeQuery.setStartTime(startTime);
		financeQuery.setEndTime(endTime);
		int currentPageNum=1;
		int perPageSize=5;
		PageDTO<FinanceQueryDTO> pages = new PageDTO<FinanceQueryDTO>(currentPageNum, perPageSize, financeQuery);
		
        List<FinanceEveryDayDTO> datasList = financeAccountBookDao.queryPageList(pages);
        financeBookKeepingDTO.setFinanceEveryDay(datasList);
        List<FinanceAccountBook> amountList = financeAccountBookDao.queryAmount(financeQuery);
        for(FinanceAccountBook account : amountList) {
        	if(account.getType()==0) {
        		financeBookKeepingDTO.setTotalSpending(account.getCash());
        	}else {
        		financeBookKeepingDTO.setTotalRevenue(account.getCash());
        	}
        }
        //通过appUserId查询商户信息
        List<AppUserEntityDTO> entityList = financeAccountBookDao.queryEntityList(financeQuery.getAppUserId());
		financeBookKeepingDTO.setAppUserEntityDTOList(entityList);
		return ResultDTO.success(financeBookKeepingDTO);
	}
	/**
	 * 查询收支子类型
	 */
	@Override
	public ResultDTO<FinanceIoType> queryIoTypeList() {
		List<FinanceIoType> ioType= financeAccountBookDao.queryIoTypeList();
		return ResultDTO.success(ioType);
	}

	@Override
	public ResultDTO addFinance(FinanceRecordDTO financeRecordDTO) {
		financeRecordDTO.getCreateTimeStr();
		String shopInnerCode = financeAccountBookDao.queryShopInnerCode(financeRecordDTO.getShopInnerCode());
		String accountId=null;
		//如果关联表中不存在该商品生成一个不重复的账套号
		if(shopInnerCode ==null || shopInnerCode.isEmpty()) {
			while (true) {
				accountId = CodeUtil.generateAccountId("F");
				String id = financeAccountBookDao.queryAccountId(accountId);
	            if (id ==null || id.isEmpty()) {
	                break;
	            }
	        }
		}
		return ResultDTO.success();
	}
	
}
