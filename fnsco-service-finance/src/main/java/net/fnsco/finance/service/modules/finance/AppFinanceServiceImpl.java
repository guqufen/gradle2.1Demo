/**
 * 
 */
package net.fnsco.finance.service.modules.finance;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.finance.api.dto.AppUserEntityDTO;
import net.fnsco.finance.api.dto.AppUserShopDTO;
import net.fnsco.finance.api.dto.FinanceBookKeepingDTO;
import net.fnsco.finance.api.dto.FinanceDetailDTO;
import net.fnsco.finance.api.dto.FinanceEveryDayDTO;
import net.fnsco.finance.api.dto.FinanceQueryDTO;
import net.fnsco.finance.api.dto.FinanceRecordDTO;
import net.fnsco.finance.api.dto.IoTypeAndShopDTO;
import net.fnsco.finance.api.finance.AppFinanceService;
import net.fnsco.finance.service.dao.master.FinanceAccountBookDao;
import net.fnsco.finance.service.domain.FinanceAccount;
import net.fnsco.finance.service.domain.FinanceAccountBook;
import net.fnsco.finance.service.domain.FinanceIoType;
import net.fnsco.order.api.merchant.IntegralRuleLogService;


/**
 * 记账相关实现类
 * @author hjt
 *
 */
@Service
public class AppFinanceServiceImpl extends BaseService implements AppFinanceService{
	
	@Autowired
	private FinanceAccountBookDao financeAccountBookDao;
	@Autowired
	private IntegralRuleLogService integralRuleLogService;
	
	@Autowired
	private Environment env;
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
        if(entityList.size()==0) {
        	return ResultDTO.fail("没有绑定商户");
        }
		financeBookKeepingDTO.setAppUserEntityDTOList(entityList);
		String nullEntityInnerCode=null;
		String nullMercName=null;
		for(AppUserEntityDTO ase : entityList) {
			nullEntityInnerCode = ase.getEntityInnerCode();
			nullMercName = ase.getMercName();
			break;
		}
		int mouth =0;
		if(financeQuery.getEntityInnerCode()==null || "".equals(financeQuery.getEntityInnerCode())||Dates== null || "".equals(Dates)) {
			financeQuery.setEntityInnerCode(nullEntityInnerCode);
			financeBookKeepingDTO.setEntityInnerCode(nullEntityInnerCode);
			financeBookKeepingDTO.setMercName(nullMercName);
			
			Calendar calendar=Calendar.getInstance();
			//获得当前时间的年月，月份从0开始所以结果要加1
			financeBookKeepingDTO.setYear(calendar.get(Calendar.YEAR));
			financeBookKeepingDTO.setMonth(calendar.get(Calendar.MONTH)+1);
		}else {
			financeBookKeepingDTO.setEntityInnerCode(financeQuery.getEntityInnerCode());
			for(AppUserEntityDTO aed : entityList) {
				if(aed.getEntityInnerCode().equals(financeQuery.getEntityInnerCode())){
					financeBookKeepingDTO.setMercName(aed.getMercName());
					break;
				}
			}
			SimpleDateFormat in = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat out = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();
			 Date dt = null; 
			    try { 
			      dt = in.parse(Dates); 
			      cal.setTime(dt); 
			    } catch (ParseException e) { 
			    	logger.error("日期格式转换出错" + dt  + ",确保真确格式");
			    	return ResultDTO.fail("日期格式转换出错");
			    } 
			    financeBookKeepingDTO.setYear(cal.get(Calendar.YEAR));
				financeBookKeepingDTO.setMonth(cal.get(Calendar.MONTH) + 1);
			Date nowTime=new Date();
			String nowDate =out.format(nowTime);
			String format=null;
			try {
				format = out.format(in.parse(Dates));
			}catch (ParseException e) {
				logger.error("日期格式转换出错" + format  + ",确保真确格式");
				return ResultDTO.fail("日期格式转换出错");
			}
			int date =0;
			int now = 0;
			try {
			    date = Integer.valueOf(format).intValue();
			    now  = Integer.valueOf(nowDate).intValue();
			} catch (NumberFormatException e) {
				logger.error("格式转换出错" + date+ now + ",确保真确格式");
				return ResultDTO.fail("日期格式转换出错");
			}
			mouth = date-now;
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
					String url = data.getIcoUrlGray();
					String prefix = env.getProperty("app.base.url");
					String icoUrl = prefix+url;
					data.setIcoUrl(icoUrl);
					if(data.getType()==0) {
						BigDecimal cashDec=data.getCashDec();
						BigDecimal spend = cashDec.negate();
						data.setCash(getYuan(spend).toString());
						spending = spending.add(cashDec);
					}else {
						data.setCash("+"+getYuan(data.getCashDec()).toString());
						revenue = revenue.add(data.getCashDec());
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
				result = sdf.parse(da);
				day =Integer.valueOf(sdf1.format(result)).intValue();
				week = getWeekCh(da);
			}  
			catch (ParseException e){ 
				logger.error("日期格式转换出错" + da+ day + week + ",确保真确格式");
				return ResultDTO.fail("日期格式转换出错");
			}  
			financeEveryDay.setDay(day);
			financeEveryDay.setWeek(week);
			financeEveryDay.setSpending(getYuan(spending).toString());
			financeEveryDay.setRevenue(getYuan(revenue).toString());
			datasLists.add(financeEveryDay);
		} 
		
        financeBookKeepingDTO.setFinanceEveryDay(datasLists);
        List<FinanceAccountBook> amountList = financeAccountBookDao.queryAmount(financeQuery);
        for(FinanceAccountBook account : amountList) {
        	if(account.getType()==0) {
        		financeBookKeepingDTO.setTotalSpending(getYuan(account.getCashDec()).toString());
        	}else {
        		financeBookKeepingDTO.setTotalRevenue(getYuan(account.getCashDec()).toString());
        	}
        }
        BigDecimal bdc = new BigDecimal(0.00);
        if(financeBookKeepingDTO.getTotalRevenue()==null) {
        	financeBookKeepingDTO.setTotalRevenue(getYuan(bdc).toString());
        }
        if(financeBookKeepingDTO.getTotalSpending()==null) {
        	financeBookKeepingDTO.setTotalSpending(getYuan(bdc).toString());;
        }
		return ResultDTO.success(financeBookKeepingDTO);
	}
	
	/**
	 * 查询收支子类型
	 */
	@Override
	public ResultDTO<IoTypeAndShopDTO> queryIoTypeAndShop(String entityInnerCode) {
		IoTypeAndShopDTO ioTypeAndShopDTO = new IoTypeAndShopDTO();
		List<FinanceIoType> ioType= financeAccountBookDao.queryIoTypeList();
		for(FinanceIoType io : ioType) {
			String url = io.getIcoUrl();
			String prefix = env.getProperty("app.base.url");
			String icoUrl = prefix+url;
			io.setIcoUrl(icoUrl);
		}
		ioTypeAndShopDTO.setFinanceIoTypeList(ioType);
		List<AppUserShopDTO> shopList=financeAccountBookDao.queryShopList(entityInnerCode);
		ioTypeAndShopDTO.setAppUserShopDTOList(shopList);
		return ResultDTO.success(ioTypeAndShopDTO);
	}
	
	/**
	 * 每日一记添加记账信息
	 */
	@Override
	@Transactional
	public ResultDTO addFinance(FinanceRecordDTO financeRecordDTO) {
		financeRecordDTO.setCash(getFen(financeRecordDTO.getCash()));
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
		String entityInnerCode = financeRecordDTO.getEntityInnerCode();		
		integralRuleLogService.insert(entityInnerCode, "007");
		return ResultDTO.success();
	}
	
	/**
	 * 编辑记账信息
	 */
	@Override
	@Transactional
	public ResultDTO modifyFinance(FinanceRecordDTO financeRecordDTO) {
		financeRecordDTO.setCash(getFen(financeRecordDTO.getCash()));
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
		String url = financeDetail.getIcoUrl();
		String prefix = env.getProperty("app.base.url");
		String icoUrl = prefix+url;
		financeDetail.setIcoUrl(icoUrl);
		if(financeDetail.getType()==0) {
			BigDecimal cashDec=financeDetail.getCashDec();
			BigDecimal spend = cashDec.negate();
			financeDetail.setCash(getYuan(spend).toString());
		}else {
			BigDecimal cashDec = financeDetail.getCashDec();
			financeDetail.setCash("+"+getYuan(cashDec).toString());
		}
		String date = financeDetail.getHappenDate();
		financeDetail.setWeek(getWeekCh(date));
		return ResultDTO.success(financeDetail);
	}
	
	@Override
	public ResultDTO deleteFinanceById(Integer id) {
		int a = financeAccountBookDao.deleteFinanceById(id);
		if(a<1) {
			return ResultDTO.fail("删除失败"); 
		}
		return ResultDTO.success(); 
	}
	/**
	 * 分转元
	 * @param bigDecimal
	 * @return
	 */
	private BigDecimal getYuan(BigDecimal bigDecimal) {
		BigDecimal bdYuan=bigDecimal.divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP);
		return bdYuan;	
	}
	/**
	 * 元转分
	 * @param bigDecimal
	 * @return
	 */
	private BigDecimal getFen(BigDecimal bigDecimal) {
		BigDecimal bdFen=bigDecimal.multiply(new BigDecimal(100));
		return bdFen;	
	}
	
	private String getWeekCh(String  dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date result =null;
		try {
			result = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("日期格式转换出错确保真确格式"+dateStr,e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(result);
		int weekoFdAY = cal.get(Calendar.DAY_OF_WEEK);
		
		switch (weekoFdAY) {
		case 7:
			return "星期六";
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		}
		return null;
	}
}
