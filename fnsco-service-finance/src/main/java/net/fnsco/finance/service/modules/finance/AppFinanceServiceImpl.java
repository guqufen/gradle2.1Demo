/**
 * 
 */
package net.fnsco.finance.service.modules.finance;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.finance.api.dto.AppUserEntityDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceDetailDTO;
import net.fnsco.finance.api.dto.FinanceEveryDayDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.finance.AppFinanceService;
import net.fnsco.finance.service.dao.master.FinanceAccountBookDao;
import net.fnsco.finance.service.domain.FinanceAccount;
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
		//通过appUserId查询商户信息
		String id = financeQuery.getAppUserId();
        List<AppUserEntityDTO> entityList = financeAccountBookDao.queryEntityList(id);
		financeBookKeepingDTO.setAppUserEntityDTOList(entityList);
		financeBookKeepingDTO.setEntityInnerCode(financeQuery.getEntityInnerCode());
		String nullEntityInnerCode=null;
		for(AppUserEntityDTO ase : entityList) {
			nullEntityInnerCode = ase.getEntityInnerCode();
			break;
		}
		if(financeQuery.getEntityInnerCode()==null) {
			financeQuery.setEntityInnerCode(nullEntityInnerCode);
			financeBookKeepingDTO.setEntityInnerCode(nullEntityInnerCode);
		}
		int mouth =0;
		if(Dates!= null){
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
				logger.error("格式转换出错" + date+ now + ",确保真确格式");
			    e.printStackTrace();
			}
			mouth = date-now;
		}else {
			Calendar calendar=Calendar.getInstance();
			//获得当前时间的年月，月份从0开始所以结果要加1
			financeBookKeepingDTO.setYear(calendar.get(Calendar.YEAR));
			financeBookKeepingDTO.setMonth(calendar.get(Calendar.MONTH)+1);
		}
		String startTime = DateUtils.getMouthStartTime(mouth);
		String endTime = DateUtils.getMouthEndTime(mouth);
		financeQuery.setStartTime(startTime);
		financeQuery.setEndTime(endTime);
		
		List<FinanceEveryDayDTO> datasLists=new ArrayList<FinanceEveryDayDTO>();
		List<String> datas = financeAccountBookDao.queryDates(financeQuery);
		for(String da : datas) {
			financeQuery.setHappenDate(da);
			FinanceEveryDayDTO financeEveryDay = new FinanceEveryDayDTO();
			BigDecimal spending = new BigDecimal(0);
			BigDecimal revenue = new BigDecimal(0);
			List<FinanceAccountBook> datasList = financeAccountBookDao.queryList(financeQuery);
			if(datasList!=null) {
				//循环处理记账支出收入
				for(FinanceAccountBook data : datasList) {
					if(data.getType()==0) {
						BigDecimal cash=data.getCash();
						BigDecimal spend = cash.negate();
						data.setCash(spend);
						spending = spending.add(cash);
					}else {
						data.setCash(data.getCash());
						revenue = revenue.add(data.getCash());
					}
				}
				financeEveryDay.setAccountBook(datasList);
			}
			//获取日期的星期以及天
			Date result = null;
			String week = null;
			int day = 0;
			try{  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				SimpleDateFormat sdf1= new SimpleDateFormat("dd");
				SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
				result = sdf.parse(da);  
				day =Integer.valueOf(sdf1.format(result)).intValue();
				week = dateFm.format(result);
			}  
			catch (ParseException e){ 
				logger.error("日期格式转换出错" + da+ day + week + ",确保真确格式");
				e.printStackTrace(); 
			}  
			financeEveryDay.setDay(day);
			financeEveryDay.setWeek(week);
			financeEveryDay.setSpending(spending);
			financeEveryDay.setRevenue(revenue);
			datasLists.add(financeEveryDay);
		} 
		
        financeBookKeepingDTO.setFinanceEveryDay(datasLists);
        List<FinanceAccountBook> amountList = financeAccountBookDao.queryAmount(financeQuery);
        for(FinanceAccountBook account : amountList) {
        	if(account.getType()==0) {
        		financeBookKeepingDTO.setTotalSpending(account.getCash());
        	}else {
        		financeBookKeepingDTO.setTotalRevenue(account.getCash());
        	}
        }
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
	
	/**
	 * 每日一记添加记账信息
	 */
	@Override
	@Transactional
	public ResultDTO addFinance(FinanceRecordDTO financeRecordDTO) {
		FinanceAccount financeAccount = new FinanceAccount();
		String shopInnerCode= financeRecordDTO.getShopInnerCode();
		financeAccount.setShopInnerCode(shopInnerCode);
		FinanceAccount account = financeAccountBookDao.queryShopInnerCode(financeAccount);
		//如果关联表中不存在该商品生成一个不重复的账套号
		if(account == null) {
			while (true) {
				String accountId = CodeUtil.generateAccountId("F");
				financeAccount.setAccountId(accountId);
				FinanceAccount fac = financeAccountBookDao.queryShopInnerCode(financeAccount);
	            if (fac == null) {
	            	financeAccount.setCreateTime(new Date());
	            	financeAccount.setAccountId(accountId);
	            	financeAccountBookDao.insertAccount(financeAccount);
	                break;
	            }
	        }
		}else {			
			financeRecordDTO.setAccountId(account.getAccountId());
		}	
		financeRecordDTO.setCreateTime(new Date());
		financeAccountBookDao.insertAccountBook(financeRecordDTO);
		
		return ResultDTO.success();
	}
	
	/**
	 * 编辑记账信息
	 */
	@Override
	@Transactional
	public ResultDTO modifyFinance(FinanceRecordDTO financeRecordDTO) {
		FinanceAccount financeAccount = new FinanceAccount();
		FinanceDetailDTO financeDetail=financeAccountBookDao.queryFinanceDetail(financeRecordDTO.getId());
		if(financeDetail.getShopInnerCode()==financeRecordDTO.getShopInnerCode()) {
			financeRecordDTO.setLastModefyTime(new Date());
			int i = financeAccountBookDao.updateAccountBook(financeRecordDTO);
			if(i < 1) {
				return ResultDTO.fail();
			}
		}else {
			financeAccount.setShopInnerCode(financeRecordDTO.getShopInnerCode());
			FinanceAccount account = financeAccountBookDao.queryShopInnerCode(financeAccount);
			//如果关联表中不存在该商品生成一个不重复的账套号
			if(account == null) {
				while (true) {
					String accountId = CodeUtil.generateAccountId("F");
					financeAccount.setAccountId(accountId);
					FinanceAccount fac = financeAccountBookDao.queryShopInnerCode(financeAccount);
		            if (fac == null) {
		            	financeAccount.setCreateTime(new Date());
		            	financeAccount.setAccountId(accountId);
		            	financeAccountBookDao.insertAccount(financeAccount);
		            	financeRecordDTO.setAccountId(accountId);
		                break;
		            }
		        }
			}else {			
				financeRecordDTO.setAccountId(account.getAccountId());
			}	
			financeRecordDTO.setLastModefyTime(new Date());
			int i = financeAccountBookDao.updateAccountBook(financeRecordDTO);
			if(i < 1) {
				return ResultDTO.fail();
			}
		}
		return ResultDTO.success();
	}
	/**
	 * 根据id查询记账详情
	 */
	@Override
	public ResultDTO<FinanceDetailDTO> queryFinanceDetailsById(Integer id) {
		FinanceDetailDTO financeDetail=financeAccountBookDao.queryFinanceDetail(id);
		String date = financeDetail.getDates();
		String week =null;
		try{  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
			Date result = sdf.parse(date);  
			week = dateFm.format(result);  
		}  
		catch (ParseException e){ 
			logger.error("日期格式转换出错" + week + ",确保真确格式");
			e.printStackTrace(); 
		}  
		financeDetail.setWeek(week);
		return ResultDTO.success(financeDetail);
	}
	
}