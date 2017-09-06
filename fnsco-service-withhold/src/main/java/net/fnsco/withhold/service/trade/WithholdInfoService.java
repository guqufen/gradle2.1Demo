package net.fnsco.withhold.service.trade;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.withhold.service.sys.UserService;
import net.fnsco.withhold.service.sys.dao.BankCodeDAO;
import net.fnsco.withhold.service.sys.entity.BankCodeDO;
import net.fnsco.withhold.service.sys.entity.UserDO;
import net.fnsco.withhold.service.trade.dao.ProductTypeDAO;
import net.fnsco.withhold.service.trade.dao.WithholdInfoDAO;
import net.fnsco.withhold.service.trade.entity.ProductTypeDO;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;

@Service
public class WithholdInfoService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WithholdInfoDAO withholdInfoDAO;
	@Autowired
	private BankCodeDAO bankCodeDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductTypeDAO productTypeDAO;
	
	// 分页
	public ResultPageDTO<WithholdInfoDO> page(WithholdInfoDO withholdInfo, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询WithholdInfoService.page, withholdInfo=" + withholdInfo.toString());
		List<WithholdInfoDO> pageList = this.withholdInfoDAO.pageList(withholdInfo, pageNum, pageSize);

		List<WithholdInfoDO> pageListNew = new ArrayList<>();
		for (WithholdInfoDO withholdInfoDO : pageList) {
		    
		    //根据productTypeCode查询出贷款类型
//		    ProductTypeDO dto=productTypeDAO.getByCode(withholdInfoDO.getProductTypeCode());
//		    if(dto!=null){
//		        withholdInfoDO.setProductTypeCode(dto.getName()); 
//		    }
		    
			// 根据最后修改人ID查询姓名
			if (null == withholdInfoDO.getModifyUserId()) {
				withholdInfoDO.setModifyUserName("--");
			} else {
				UserDO userDO = userService.doQueryById(withholdInfoDO.getModifyUserId());
				if (null == userDO) {
					withholdInfoDO.setModifyUserName("--");
				} else {
					withholdInfoDO.setModifyUserName(userDO.getName());
				}
			}

			// 银行卡号处理
			String bankCard = withholdInfoDO.getBankCard();
			withholdInfoDO.setBankCard(bankCard.substring(0, 4) + "****" + bankCard.substring(bankCard.length() - 4));

			// 提交时间处理
			Date date = withholdInfoDO.getModifyTime();
			if (null != date) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				String ss = sdf.format(date);
				withholdInfoDO.setModifyTimeStr(ss);
			} else {
				withholdInfoDO.setModifyTimeStr("--");
			}

			/**
			 * 扣款金额/次，已扣款总额，扣款总额、待扣款总额处理(保留两位小数)
			 */
			BigDecimal b1 = new BigDecimal(100.00);
			// 扣款金额/次
			BigDecimal amount = withholdInfoDO.getAmount();
			BigDecimal b2 = amount.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
			withholdInfoDO.setAmount(b2);// 设置扣款金额/次

			// 已扣款金额
			BigDecimal amountTotal = withholdInfoDO.getAmountTotal();
			b2 = amountTotal.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
			withholdInfoDO.setAmountTotal(b2);// 设置已扣款金额

			// 扣款总额
			Integer total = withholdInfoDO.getTotal();// 扣款次数
			BigDecimal allTotalAmt = amount.multiply(new BigDecimal(total));// 扣款总额
			b2 = allTotalAmt.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
			withholdInfoDO.setAllTotalAmt(b2);// 设置扣款总额

			// 待扣款总额
			BigDecimal payLeftAmt = allTotalAmt.subtract(amountTotal);// 总金额-已扣款金额
			b2 = payLeftAmt.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
			withholdInfoDO.setPayLeftAmt(b2);// 设置待扣款金额

//			pageListNew.add(withholdInfoDO);
		}
		Integer count = this.withholdInfoDAO.pageListCount(withholdInfo);
		ResultPageDTO<WithholdInfoDO> pager = new ResultPageDTO<WithholdInfoDO>(count, pageList);
		return pager;
	}

	// 添加
	public WithholdInfoDO doAdd(WithholdInfoDO withholdInfo, int loginUserId) {
		logger.info("开始添加WithholdInfoService.add,withholdInfo=" + withholdInfo.toString());
		Date now = new Date();
		withholdInfo.setModifyUserId(loginUserId);
		withholdInfo.setModifyTime(now);
		withholdInfo.setCertifType("01");// 证件类型
		withholdInfo.setAmountTotal(new BigDecimal(0.00));
		withholdInfo.setAccountType("01");
		withholdInfo.setStatus(3);
		withholdInfo.setCertifType("01");// 设置身份证
		withholdInfo.setAccType("01");// 帐号类型
		withholdInfo.setFailTotal(0);
		withholdInfo.setAmount(withholdInfo.getAmount().multiply(new BigDecimal(100)));// 单次扣款金额乘以100保存
		//处理身份证最后一位字母大写
		String str=withholdInfo.getCertifyId();
		//String st=str.substring(0,str.length() -1)+str.substring(str.length()-1,str.length()).toUpperCase();
		withholdInfo.setCertifyId(str.substring(0,str.length() -1)+str.substring(str.length()-1,str.length()).toUpperCase());
		// 设置爱农编号
		if (StringUtils.isEmpty(withholdInfo.getBankCard()) || withholdInfo.getBankCard().length() < 6) {
			return null;
		}
		BankCodeDO bankCodeDO = bankCodeDAO.getByCardNum(withholdInfo.getBankCard(),
				withholdInfo.getBankCard().length());
		if (null == bankCodeDO || StringUtils.isEmpty(bankCodeDO.getCode())) {
			return null;
		}
		withholdInfo.setAnBankId(bankCodeDO.getCode());
		this.withholdInfoDAO.insert(withholdInfo);
		return withholdInfo;
	}

	// 修改
	public Integer doUpdate(WithholdInfoDO withholdInfo, Integer loginUserId) {
		logger.info("开始修改WithholdInfoService.update,withholdInfo=" + withholdInfo.toString());
		Date now = new Date();
		//withholdInfo.setModifyUserId(loginUserId);
		withholdInfo.setModifyTime(now);
		//处理身份证最后一位字母大写
	     //更改状态为审核
	     if(withholdInfo.getStatus()==1){
	         /**
	          * 当用户选择的还款日期（day）小于当前日期的天时，从下个月开始算还款日期。当用户选择的日期（day）等于当前日期的天的时候，如果当前时间小于早上8:30的时候，就从本月开始，否则从下个月开始
	          */
	         // 计算扣款开始、结束日期
	         Calendar calender = Calendar.getInstance();
	         int month = now.getMonth();
	         if (now.getDate() < Integer.valueOf(withholdInfo.getDebitDay())
	                 || (now.getDate() == Integer.valueOf(withholdInfo.getDebitDay())
	                         && ((now.getHours() == 8 && now.getMinutes() <= 30) || now.getHours() <= 7))) {
	         } else {
	             month++;
	         }
	         withholdInfo.setStartDate(DateUtils.getDateStrByInput(calender.get(Calendar.YEAR), month,
	                 Integer.valueOf(withholdInfo.getDebitDay())));
	         withholdInfo.setEndDate(DateUtils.getDateStrByStrAdd(withholdInfo.getStartDate(), withholdInfo.getTotal() - 1));
	     }
	     //更改状态为编辑时 需要特殊处理的字段
	     if(withholdInfo.getStatus()==3){
	         withholdInfo.setAmount(withholdInfo.getAmount().multiply(new BigDecimal(100)));
	         withholdInfo.setAmountTotal(new BigDecimal(0.00));
	         withholdInfo.setFailTotal(0);
	         String str=withholdInfo.getCertifyId();     
	         withholdInfo.setCertifyId(str.substring(0,str.length() -1)+str.substring(str.length()-1,str.length()).toUpperCase());
	     }
		int rows = this.withholdInfoDAO.update(withholdInfo);
		return rows;
	}

	// 删除
	public Integer doDelete(WithholdInfoDO withholdInfo, Integer loginUserId) {
		logger.info("开始删除WithholdInfoService.delete,withholdInfo=" + withholdInfo.toString());
		int rows = this.withholdInfoDAO.deleteById(withholdInfo.getId());
		return rows;
	}

	// 查询
	public WithholdInfoDO doQueryById(Integer id) {
		WithholdInfoDO obj = this.withholdInfoDAO.getById(id);
		BigDecimal b1 = new BigDecimal(100.00);
        BigDecimal amount = obj.getAmount();
        BigDecimal b2 = amount.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
        obj.setAmount(b2);// 设置扣款金额/次  amount_total
        
        BigDecimal amountTotal = obj.getAmountTotal();
        BigDecimal b3 =amountTotal.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
        obj.setAmountTotal(b3);
		return obj;
	}
	//代扣类型查询
	public List<ProductTypeDO> queryWithholdType(){
	    List<ProductTypeDO> productTypeDO=this.productTypeDAO.query();
        return productTypeDO;
	}
	//代扣名称查询
//	public ProductTypeDO queryWithholdName(String code){
//	    ProductTypeDO productTypeDO=this.productTypeDAO.getByCode(code);
//        return productTypeDO;
//	}
	//分页查询
	public ResultPageDTO<ProductTypeDO> pageProductType(ProductTypeDO productTypeDO, Integer pageNum, Integer pageSize) {
	    logger.info("开始分页查询WithholdInfoService.page, withholdInfo=" + productTypeDO.toString());
        List<ProductTypeDO> pageList = this.productTypeDAO.pageList(productTypeDO, pageNum, pageSize);
        Integer count = this.productTypeDAO.pageListCount(productTypeDO);
        ResultPageDTO<ProductTypeDO> pager = new ResultPageDTO<ProductTypeDO>(count, pageList);
        return pager;
	}
	//新增代扣类型
    public int doAddProductType(ProductTypeDO productTypeDO){
        productTypeDO.setStatus("1");
        productTypeDO.setModifyTime(new Date());
        int count =this.productTypeDAO.insert(productTypeDO);
        return count; 
    }
    //更新代扣状态

    public int updateProductType(ProductTypeDO productTypeDO) {
        int count =this.productTypeDAO.update(productTypeDO);
        return count;
    }
    //根据id查询代扣名称
    public ResultDTO queryProductTypeById(Integer id) {
        ProductTypeDO dto=this.productTypeDAO.getById(id);
        return ResultDTO.success(dto);
    }
    
}