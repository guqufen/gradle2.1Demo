package net.fnsco.trading.service.bank;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.bank.dao.AppUserBankDAO;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.trading.service.bank.entity.BankNameAndTypeDTO;

@Service
public class AppUserBankService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppUserBankDAO appUserBankDAO;

	// 分页
	public ResultPageDTO<AppUserBankDO> page(AppUserBankDO appUserBank, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询AppUserBankService.page, appUserBank=" + appUserBank.toString());
		List<AppUserBankDO> pageList = this.appUserBankDAO.pageList(appUserBank, pageNum, pageSize);
		Integer count = this.appUserBankDAO.pageListCount(appUserBank);
		ResultPageDTO<AppUserBankDO> pager = new ResultPageDTO<AppUserBankDO>(count, pageList);
		return pager;
	}

	// 添加
	public AppUserBankDO doAdd(AppUserBankDO appUserBank, int loginUserId) {
		logger.info("开始添加AppUserBankService.add,appUserBank=" + appUserBank.toString());
		this.appUserBankDAO.insert(appUserBank);
		return appUserBank;
	}

	// 修改
	public Integer doUpdate(AppUserBankDO appUserBank, Integer loginUserId) {
		logger.info("开始修改AppUserBankService.update,appUserBank=" + appUserBank.toString());
		int rows = this.appUserBankDAO.update(appUserBank);
		return rows;
	}

	// 删除
	public Integer doDelete(AppUserBankDO appUserBank, Integer loginUserId) {
		logger.info("开始删除AppUserBankService.delete,appUserBank=" + appUserBank.toString());
		int rows = this.appUserBankDAO.deleteById(appUserBank.getId());
		return rows;
	}

	// 查询
	public AppUserBankDO doQueryById(Integer id) {
		AppUserBankDO obj = this.appUserBankDAO.getById(id);
		return obj;
	}

	public Integer unBindBankCard(Integer bankId) {
		int rows = this.appUserBankDAO.unBindBankCard(bankId);
		return rows;

	}

	/**
	 * app端获取银行卡列表 getBankList:(这里用一句话描述这个方法的作用)
	 *
	 * @param @param
	 *            parseInt
	 * @param @return
	 *            设定文件
	 * @return List<AppUserBankDO> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<AppUserBankDO> getBankList(String userId) {
		List<AppUserBankDO> list = this.appUserBankDAO.getBankList(userId);

		return list;

	}

	/**
	 * 保存e789App银行卡信息
	 * doAppAdd:(这里用一句话描述这个方法的作用)
	 *
	 * @param  @param userId
	 * @param  @param mobile
	 * @param  @param bankCardNum
	 * @param  @param bankCardholder
	 * @param  @param id_card_num    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Integer doAppAdd(Integer userId, String mobile, String bankCardNum, String bankCardholder, String id_card_num) {
		// 根据银行卡号获取银行名称和卡类型
		String cardTotalLength = String.valueOf(bankCardNum.trim().length());
		BankNameAndTypeDTO dto = appUserBankDAO.queryByCertifyId(bankCardNum, cardTotalLength);
		AppUserBankDO appUserBankDO = new AppUserBankDO();
		appUserBankDO.setAppUserId(userId);
		appUserBankDO.setAccountPhone(mobile);
		appUserBankDO.setAccountName(bankCardholder);
		appUserBankDO.setAccountNo(bankCardNum);
		appUserBankDO.setAccountCardId(id_card_num);
		if(dto != null){
			appUserBankDO.setType(dto.getType());
			appUserBankDO.setBankName(dto.getBank_name());
			
		}
		appUserBankDO.setCreateTime(new Date());
		appUserBankDO.setUpdateTime(new Date());
		Integer row = appUserBankDAO.insert(appUserBankDO);
		return row;
	}
	/**
	 * 感觉用户id查询是否绑定银行卡
	 * @param appUserId
	 * @return
	 */
	public AppUserBankDO QueryByAppUserId(Integer appUserId) {
		AppUserBankDO appUserBank = this.appUserBankDAO.getByAppUserId(appUserId);
		return appUserBank;
	}
}