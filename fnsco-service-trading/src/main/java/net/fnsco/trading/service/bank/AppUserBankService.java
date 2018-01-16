package net.fnsco.trading.service.bank;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.bank.dao.AppUserBankDAO;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.trading.service.bank.entity.BankNameAndTypeDTO;
import net.fnsco.trading.service.pay.channel.ebank.EbankService;
import net.fnsco.trading.service.pay.channel.ebank.entity.E4029Entity;

@Service
public class AppUserBankService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppUserBankDAO appUserBankDAO;
	@Autowired
	private EbankService ebankService;

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
	public Integer doAppAdd(Integer userId, String mobile, String bankCardNum, String bankCardholder, String id_card_num,Integer id) {
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
			
		}else{
			appUserBankDO.setBankName("银联");
		}
		appUserBankDO.setUpdateTime(new Date());
		Integer row = 0;
		appUserBankDO.setStatus("0");
		if(id != null){
			appUserBankDO.setId(id);
			row = appUserBankDAO.update(appUserBankDO);
		}else{
			appUserBankDO.setCreateTime(new Date());
			row = appUserBankDAO.insert(appUserBankDO);
		}
		
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

	/**
	 * 判断银行卡号是否存在
	 * getByBankNO:(这里用一句话描述这个方法的作用)
	 *
	 * @param  @param bankCardNum
	 * @param  @return    设定文件
	 * @return AppUserBankDO    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<AppUserBankDO> getByBankNO(Integer userId ,String bankCardNum) {
		return this.appUserBankDAO.getByBankNO(userId,bankCardNum);
		
	}

	/**
	 * 调用平安接口校验银行卡
	 * @param  @param oppAccNo 银行卡号
	 * @param  @param oppAccName  户名
	 * @param  @param mobile
	 * @param  @param idNo    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public ResultDTO validateBank2(String oppAccNo, String oppAccName, String mobile, String idNo) {
		//根据银行卡号获取卡类型和平安编号
		BankNameAndTypeDTO dto = appUserBankDAO.queryByCertifyId(oppAccNo, oppAccNo.length()+"");
		if(dto==null){
			return ResultDTO.fail();//未找到对应卡类型信息，请联系管理员
		}
		if(Strings.isNullOrEmpty(dto.getPaCode())){
			return ResultDTO.fail();//对应平安编号为空，请联系管理员
			
		}
		logger.error("平安编号="+dto.getPaCode());
		E4029Entity e4029Entity = new E4029Entity();
		e4029Entity.setOppAccNo(oppAccNo);
		e4029Entity.setOppAccName(oppAccName);
		e4029Entity.setOppBank(dto.getPaCode());
		if("DC".equals(dto.getType())){
			e4029Entity.setCardAcctFlag("0");
			
		}
		e4029Entity.setIdType("1");//身份证
		e4029Entity.setIdNo(idNo);
		e4029Entity.setMobile(mobile);
		e4029Entity.setTranFlag("1");//新增
		
		ebankService.E4029Trade(e4029Entity);
		
		return null;
		
	}
}