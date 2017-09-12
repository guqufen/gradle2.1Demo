package net.fnsco.bigdata.service.modules.merchant;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.api.dto.SnCodeDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoImportService {
	@Autowired
	private MerchantCoreService merchantCoreService;
	@Autowired
	private MerchantPosService merchantPosService;
	// 批量导入客户
	@Transactional
	public ResultDTO<String> merchantBatchImportToDB(List<Object[]> customerList, Integer userId) throws ParseException {
		// 循环便利customList数组，将其中excel每一行的数据分批导入数据库
		if (customerList.size() != 0) {
			// excel导出的空数据是“null”，赋值一个空字符串
			int timeNum = 2;
			for (Object[] objs : customerList) {
				timeNum = timeNum + 1;
				for (int i = 0; i < objs.length; i++) {
					if (objs[i] == null) {
						objs[i] = "";
					}
				}
				// 工商注册名称（协议签约一致）
				String mername = String.valueOf(objs[0]);
				// 营业执照注册号
				String businesslicensenum = String.valueOf(objs[1]);
				// 法人身份证号码
				String cardnum = String.valueOf(objs[2]);
				// 法人姓名
				String legalperson = String.valueOf(objs[3]);
				// 法人手机号
				String legalpersonmobile = String.valueOf(objs[4]);
				// 证件有效期
				String cardvalidtimeStr = String.valueOf(objs[5]);
				// 营业执照有效期
				String businesslicensevalidtimeStr = String.valueOf(objs[6]);
				// 商户注册地址
				String registaddress = String.valueOf(objs[7]);
				// 商户标签
				String mercflag = String.valueOf(objs[8]);
				// 代理商
				Integer agentid = Integer.parseInt(String.valueOf(objs[9]));
				// 商户入网注册名称
				String abbreviation = String.valueOf(objs[10]);
				// 签购单名称
				String mercrefername = String.valueOf(objs[11]);
				// 商户联系人
				String contactname = String.valueOf(objs[12]);
				// 联系电话
				String contactmobile = String.valueOf(objs[13]);
				// 装机地址
				String posaddr = String.valueOf(objs[14]);
				// 开户类型
				String accounttype = String.valueOf(objs[15]);
				// 开户人身份证
				String accountcardid = String.valueOf(objs[16]);
				// 入账人
				String accountname = String.valueOf(objs[17]);
				// 入账账号
				String accountno = String.valueOf(objs[18]);
				// 开户行
				String subbankname = String.valueOf(objs[19]);
				// 邮箱
				String contactemail = String.valueOf(objs[20]);
				// 刷卡扣率-借记卡
				String jjk0 = String.valueOf(objs[21]);
				// 刷卡扣率-贷记卡
				String creditcardrate = String.valueOf(objs[22]);
				// 扫码扣率
				String xx = String.valueOf(objs[23]);
				// 渠道商户号
				String channelmerid = String.valueOf(objs[24]);
				// 一号pos机
				// 备注/1号机具SN
				String sncode1 = String.valueOf(objs[25]);
				// 终端编号刷卡
				String terminalcode11 = String.valueOf(objs[26]);
				// 终端编号扫码
				String terminalcode12 = String.valueOf(objs[27]);
				// 二号pos机
				// 备注/2号机具SN
				String sncode2 = String.valueOf(objs[28]);
				// 终端编号刷卡
				String terminalcode21 = String.valueOf(objs[29]);
				// 终端编号扫码
				String terminalcode22 = String.valueOf(objs[30]);
				// 三号pos机
				// 备注/3号机具SN
				String sncode3 = String.valueOf(objs[31]);
				// 终端编号刷卡
				String terminalcode31 = String.valueOf(objs[32]);
				// 终端编号扫码
				String terminalcode32 = String.valueOf(objs[33]);

				String innerCode = merchantCoreService.getInnerCode();
				/**
				 * 商户基本信息
				 */
				// 创建一个商户基础信息实体类对象接收商户及信息
				MerchantCore merchantCore = new MerchantCore();
				merchantCore.setInnerCode(innerCode);
				merchantCore.setMerName(mername);
				merchantCore.setBusinessLicenseNum(businesslicensenum);
				merchantCore.setLegalValidCardType("0");
				merchantCore.setCardNum(cardnum);
				merchantCore.setLegalPerson(legalperson);
				merchantCore.setAbbreviation(abbreviation);
				merchantCore.setLegalPersonMobile(legalpersonmobile);
				// excel中导出的时间是“EEE MMM dd HH:mm:ss z yyyy”类型的String类，将他转换成"yyyy/MM/dd"
				SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
				Date date1 = sdf1.parse(cardvalidtimeStr);
				sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				String cardvalidtime = sdf1.format(date1);
				SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
				Date date2 = sdf2.parse(businesslicensevalidtimeStr);
				sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				String businesslicensevalidtime = sdf2.format(date2);
				merchantCore.setCardValidTime(cardvalidtime);
				merchantCore.setBusinessLicenseValidTime(businesslicensevalidtime);
				merchantCore.setRegistAddress(registaddress);
				merchantCore.setMercFlag(mercflag);
				merchantCore.setAgentId(agentid);
				try {
					// 商户基本信息保存
					merchantCoreService.doAddMerCore(merchantCore);
				} catch (Exception e) {
					return ResultDTO.fail("第" + timeNum + "行数据的基本数据信息有误，导入失败");
				}

				/**
				 * 商户联系人信息
				 */
				// 创建一个商户联系人信息实体类对象接收商户联系人信息
				MerchantContact merchantContact = new MerchantContact();
				merchantContact.setInnerCode(innerCode);
				merchantContact.setContactName(contactname);
				merchantContact.setContactMobile(contactmobile);
				merchantContact.setContactEmail(contactemail);
				List<MerchantContact> contcactList = new ArrayList<MerchantContact>();
				contcactList.add(merchantContact);
				try {
					// 商户联系人信息保存
					merchantCoreService.doAddMerContact(contcactList);
				} catch (Exception e) {
					return ResultDTO.fail("第" + timeNum + "行数据的商户联系人信息有误，导入失败");
				}
				/**
				 * 渠道信息--设置默认值
				 */
				// 创建一个渠道信息实体类对象接收渠道信息
				MerchantChannel merchantChannel = new MerchantChannel();
				merchantChannel.setInnerCode(innerCode);
				merchantChannel.setChannelMerId(channelmerid);
				merchantChannel.setChannelType("00");
				merchantChannel.setCreateTime(new Date());
				merchantChannel.setModifyTime(new Date());
				merchantChannel.setModifyUserId(userId);
				Integer channelId=null;
				try {
					// 渠道信息保存
					channelId = merchantCoreService.doAddChannel(merchantChannel);
				} catch (Exception e) {
					return ResultDTO.fail("第" + timeNum + "行数据的渠道信息有误，导入失败");
				}

				/**
				 * 商户银行卡信息
				 */
				// 创建一个商户银行卡信息实体类对象接收商户银行卡信息
				MerchantBank merchantBank = new MerchantBank();
				merchantBank.setInnerCode(innerCode);
				merchantBank.setAccountName(accountname);
				merchantBank.setAccountNo(accountno);
				merchantBank.setAccountType(accounttype);
				merchantBank.setAccountCardId(accountcardid);
				merchantBank.setSubBankName(subbankname);
				Integer bankId=null;
				try {
					bankId = merchantCoreService.doAddBanks(merchantBank);
				} catch (Exception e) {
					return ResultDTO.fail("第" + timeNum + "行数据的银行卡信息有误，导入失败");
				}
				// 银行卡信息保存
				

				// 判断有多少个pos机
				List<SnCodeDTO> posList = new ArrayList<>();
				SnCodeDTO snCode1 = new SnCodeDTO();
				snCode1.setNum(1);
				snCode1.setSncode(sncode1);
				snCode1.setTerminalcode1(terminalcode11);
				snCode1.setTerminalcode2(terminalcode12);
				posList.add(snCode1);
				if (!"".equals(sncode2)) {
					SnCodeDTO snCode2 = new SnCodeDTO();
					snCode2.setNum(2);
					snCode2.setSncode(sncode1);
					snCode2.setTerminalcode1(terminalcode21);
					snCode2.setTerminalcode2(terminalcode22);
					posList.add(snCode2);
				}
				if (!"".equals(sncode3)) {
					SnCodeDTO snCode3 = new SnCodeDTO();
					snCode3.setNum(3);
					snCode3.setSncode(sncode1);
					snCode3.setTerminalcode1(terminalcode31);
					snCode3.setTerminalcode2(terminalcode32);
					posList.add(snCode3);
				}
				for (SnCodeDTO sn : posList) {
					/**
					 * 商戶pos机信息
					 */
					// 创建一个商户pos信息实体类对象接收商户pos信息
					MerchantPos merchantPos = new MerchantPos();
					merchantPos.setInnerCode(innerCode);
					merchantPos.setMercReferName(mercrefername);
					merchantPos.setPosType("APOS A8");
					merchantPos.setPosFactory("福建联迪商用设备有限公司");
					merchantPos.setSnCode(sn.getSncode());
					merchantPos.setPosAddr(posaddr);
					merchantPos.setStatus("1");
					merchantPos.setPosName(sn.getNum() + "号POS机");
					// 获取银行卡ID
					merchantPos.setBankId(bankId);
					// 获取渠道ID
					merchantPos.setChannelId(channelId);
					Integer posId = null;
					try {
						// pos机信息保存
						 posId = merchantPosService.insertPos(merchantPos);
					} catch (Exception e) {
						return ResultDTO.fail("第" + timeNum + "行数据的Pos机信息有误，导入失败");
					}

					/**
					 * 商户终端信息
					 */
					// 创建一个merchantTerminal1来接受刷卡的终端信息
					MerchantTerminal merchantTerminal1 = new MerchantTerminal();
					merchantTerminal1.setInnerCode(innerCode);
					// 借记卡转小数
					if ("".equals(jjk0)) {
						merchantTerminal1.setDebitCardRate(null);
						merchantTerminal1.setDebitCardMaxFee(null);
					} else {
						String jjk1 = jjk0.substring(0, jjk0.indexOf("%"));
						BigDecimal bigDecimal0 = new BigDecimal(jjk1);
						BigDecimal jjk = bigDecimal0.divide(new BigDecimal("100")).setScale(6,
								BigDecimal.ROUND_HALF_UP);
						merchantTerminal1.setDebitCardRate(String.valueOf(jjk.doubleValue()));
						// 借记卡费率封顶值
						String max1 = jjk0.substring(jjk0.indexOf("+"));
						Integer max = Integer.valueOf(max1);
						merchantTerminal1.setDebitCardMaxFee(max);
					}
					merchantTerminal1.setCreditCardRate(creditcardrate);
					merchantTerminal1.setPosId(posId);
					merchantTerminal1.setTermName("刷卡");
					merchantTerminal1.setTerminalType("00");
					merchantTerminal1.setTerminalCode(sn.getTerminalcode1());

					// 创建一个merchantTerminal2来接受扫码的终端信息
					MerchantTerminal merchantTerminal2 = new MerchantTerminal();
					merchantTerminal2.setInnerCode(innerCode);
					// 支付宝微信费率分割
					// String xx = String.valueOf(objs[23]);

					if ("".equals(xx)) {
						merchantTerminal2.setAlipayFee(null);
						merchantTerminal2.setWechatFee(null);
					} else {
						try {
							// 支付宝费率转换
							String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
							BigDecimal bigDecimal1 = new BigDecimal(zfb1);
							BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6,
									BigDecimal.ROUND_HALF_UP);
							merchantTerminal2.setAlipayFee(String.valueOf(zfb.doubleValue()));
							// 微信费率转换
							String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
							BigDecimal bigDecimal2 = new BigDecimal(wx1);
							BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6,
									BigDecimal.ROUND_HALF_UP);
							merchantTerminal2.setWechatFee(String.valueOf(wx.doubleValue()));
							merchantTerminal2.setPosId(posId);

							merchantTerminal2.setTermName("扫码");
							merchantTerminal2.setTerminalType("01");
						} catch (Exception e) {
							return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
						}

					}
					merchantTerminal2.setTerminalCode(sn.getTerminalcode2());
					// 把2个终端信息打包成List
					List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
					terminalList.add(merchantTerminal1);
					terminalList.add(merchantTerminal2);
					try {
						// 终端信息保存
						merchantCoreService.doAddMerTerminal(terminalList);
					} catch (Exception e) {
						return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
					}
				}
			}
			return ResultDTO.success();
		}
		// return b;
		return ResultDTO.fail("没有导入数据，Excel为空");
	}
}
