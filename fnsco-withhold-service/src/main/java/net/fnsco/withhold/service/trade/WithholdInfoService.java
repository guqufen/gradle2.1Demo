package net.fnsco.withhold.service.trade;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.withhold.service.sys.dao.BankCodeDAO;
import net.fnsco.withhold.service.sys.entity.BankCodeDO;
import net.fnsco.withhold.service.trade.dao.WithholdInfoDAO;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;

@Service
public class WithholdInfoService extends BaseService {

    private Logger          logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WithholdInfoDAO withholdInfoDAO;
    @Autowired
    private BankCodeDAO     bankCodeDAO;

    // 分页
    public ResultPageDTO<WithholdInfoDO> page(WithholdInfoDO withholdInfo, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询WithholdInfoService.page, withholdInfo=" + withholdInfo.toString());
        List<WithholdInfoDO> pageList = this.withholdInfoDAO.pageList(withholdInfo, pageNum, pageSize);
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
        withholdInfo.setCertifType("01");//证件类型
        withholdInfo.setAmountTotal(new BigDecimal(0.00));
        withholdInfo.setAccountType("01");
        withholdInfo.setStatus(1);
        withholdInfo.setCertifType("01");//设置身份证
        withholdInfo.setAccType("01");//帐号类型
        withholdInfo.setFailTotal(0);
        //计算扣款开始、结束日期
        Calendar calender = Calendar.getInstance();
        int month = now.getMonth();
       //设置扣款开始时间   当用户选择的日期比当前日期小的时候，就设置开始日期为下个月开始。当用户选择的日期比当前日期大的时候就设置从本月开始。
        //用户选择日期是当前日期时，在八点半之前包含八点半就从本月开月开始，大于八点半就从下个月开始扣款
        if (now.getDate() < Integer.valueOf(withholdInfo.getDebitDay())
            || (now.getDate() == Integer.valueOf(withholdInfo.getDebitDay()) && (now.getHours() == 8 && now.getMinutes() <= 30 || now.getHours() <= 7))) {

        } else {
            month = now.getMonth() + 1;
        }
        withholdInfo.setStartDate(DateUtils.getDateStrByInput(calender.get(Calendar.YEAR), month, Integer.valueOf(withholdInfo.getDebitDay())));

        withholdInfo.setEndDate(DateUtils.getDateStrByStrAdd(withholdInfo.getStartDate(), withholdInfo.getTotal() - 1));
        //设置爱农编号
        if (StringUtils.isEmpty(withholdInfo.getBankCard())) {
            return null;
        }
        BankCodeDO bankCodeDO = bankCodeDAO.getByCardNum(withholdInfo.getBankCard(), withholdInfo.getBankCard().length());
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
        return obj;
    }
}