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

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.SnCodeDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;

/**
 * @desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoImportService extends BaseService {
    @Autowired
    private MerchantCoreService merchantCoreService;
    @Autowired
    private MerchantPosService  merchantPosService;
    @Autowired
    private MerchantChannelDao  merchantChannelDao;

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
                // channelMerchant// 商户入网注册名称
                String channelMerchant = String.valueOf(String.valueOf(objs[9]));

                //				String abbreviation = String.valueOf(objs[10]);
                // 签购单名称
                String mercrefername = String.valueOf(objs[10]);
                // 商户联系人
                String contactname = String.valueOf(objs[11]);
                // 联系电话
                String contactmobile = String.valueOf(objs[12]);
                //邮箱
                String contactemail = String.valueOf(objs[13]);
                //财务联系人信息
                String financeLinkMan = String.valueOf(objs[14]);
                String financeLinkManTel = String.valueOf(objs[15]);
                String financeLinkManEmail = String.valueOf(objs[16]);
                //商户负责人信息
                String merPrincipal = String.valueOf(objs[17]);
                String merPrincipalTel = String.valueOf(objs[18]);
                String merPrincipalEmail = String.valueOf(objs[19]);
                // 装机地址
                String posaddr = String.valueOf(objs[20]);
                // 开户类型
                String accounttype = String.valueOf(objs[21]);
                // 开户人身份证
                String accountcardid = String.valueOf(objs[22]);
                // 入账人
                String accountname = String.valueOf(objs[23]);
                // 入账账号
                String accountno = String.valueOf(objs[24]);
                // 开户行
                String subbankname = String.valueOf(objs[25]);
                String openBankNum = String.valueOf(objs[26]);
                ;
                // channelType
                String channelType = String.valueOf(objs[27]);
                //createTime
                String createTime = String.valueOf(objs[28]);
                // busiCode
                String busiCode = String.valueOf(objs[29]);
                String privateKye = String.valueOf(objs[30]);
                String taxRegistCode = String.valueOf(objs[31]);
                // 扫码扣率
                String xx = String.valueOf(objs[32]);
                //文件信息
                String fileInfos = String.valueOf(objs[33]);
                // 一号pos机
                // 备注/1号机具SN
                String sncode = String.valueOf(objs[34]);
                //debitCardRate
                String debitCardRate = String.valueOf(objs[35]);
                //creditCardRate
                String creditCardRate = String.valueOf(objs[36]);
                //debitCardFee
                String debitCardFee = String.valueOf(objs[37]);
                //creditCardFee
                String creditCardFee = String.valueOf(objs[38]);
                //debitCardMaxFee
                String debitCardMaxFee = String.valueOf(objs[39]);
                //creditCardMaxFee
                String creditCardMaxFee = String.valueOf(objs[40]);
                //posType
                String posType = String.valueOf(objs[41]);
                //posFactory
                String posFactory = String.valueOf(objs[42]);
                //merchantCode
                String merchantCode = String.valueOf(objs[43]);
                //terminalCode
                String terminalCode = String.valueOf(objs[44]);
                //innerTermCode
                String innerTermCode = String.valueOf(objs[45]);
                //mercReferName
                String mercReferName = String.valueOf(objs[46]);

                /**
                 * 导入之前要先验证business_license_num 营业执照号码保持唯一,如果存在，则不新加商户，只加该商户其余属性。
                 */
                if (Strings.isNullOrEmpty(businesslicensenum)) {
                    logger.error("第" + timeNum + "营业执照为空 不能入库!");
                    continue;
                }
                String innerCode = null;
                Integer bankId = null;
                MerchantCore merchantcore = merchantCoreService.selectBybusinessLicenseNum(businesslicensenum);
                /**
                 * 商户基本信息
                 */
                if (merchantCode == null) {
                    innerCode = merchantCoreService.getInnerCode();
                    // 创建一个商户基础信息实体类对象接收商户及信息
                    MerchantCore merchantCore = new MerchantCore();
                    merchantCore.setInnerCode(innerCode);
                    merchantCore.setMerName(mername);
                    merchantCore.setBusinessLicenseNum(businesslicensenum);
                    merchantCore.setLegalValidCardType("0");
                    merchantCore.setCardNum(cardnum);
                    merchantCore.setLegalPerson(legalperson);
                    merchantCore.setAbbreviation(channelMerchant);
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
                    merchantCore.setAgentId(1);//默认
                    merchantCore.setTaxRegistCode(taxRegistCode);
                    merchantCore.setModifyTime(DateUtils.formateToDate(createTime));
                    merchantCore.setStatus(1);

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
                    MerchantContact merchantContact1 = new MerchantContact();
                    merchantContact1.setInnerCode(innerCode);
                    merchantContact1.setContactName(financeLinkMan);
                    merchantContact1.setContactMobile(financeLinkManTel);
                    merchantContact1.setContactEmail(financeLinkManEmail);
                    MerchantContact merchantContact2 = new MerchantContact();
                    merchantContact2.setInnerCode(innerCode);
                    merchantContact2.setContactName(merPrincipal);
                    merchantContact2.setContactMobile(merPrincipalTel);
                    merchantContact2.setContactEmail(merPrincipalEmail);
                    List<MerchantContact> contcactList = new ArrayList<MerchantContact>();
                    contcactList.add(merchantContact);
                    contcactList.add(merchantContact1);
                    contcactList.add(merchantContact2);
                    try {
                        // 商户联系人信息保存
                        merchantCoreService.doAddMerContact(contcactList);
                    } catch (Exception e) {
                        return ResultDTO.fail("第" + timeNum + "行数据的商户联系人信息有误，导入失败");
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
                    merchantBank.setOpenBankNum(openBankNum);
                    try {
                        bankId = merchantCoreService.doAddBanks(merchantBank);
                    } catch (Exception e) {
                        return ResultDTO.fail("第" + timeNum + "行数据的银行卡信息有误，导入失败");
                    }
                    // 银行卡信息保存
                    //文件处理
                } else {
                    innerCode = merchantcore.getInnerCode();
                }

                /**
                 * 渠道信息--根据innerCode+merchantCode判断唯一性，如果存在，则获取ID ，否则新增加
                 */
                if (Strings.isNullOrEmpty(merchantCode)) {
                    logger.error("第" + timeNum + "渠道商户号为空 不能入库!");
                    continue;
                }
                MerchantChannel channel = merchantChannelDao.selectByInnerCodeAndChannelCode(innerCode, merchantCode);
                Integer channelId = null;
                if (null == channel) {
                    // 创建一个渠道信息实体类对象接收渠道信息
                    MerchantChannel merchantChannel = new MerchantChannel();
                    merchantChannel.setInnerCode(innerCode);
                    merchantChannel.setChannelMerId(merchantCode);
                    merchantChannel.setChannelType(channelType);
                    merchantChannel.setCreateTime(new Date());
                    merchantChannel.setModifyTime(new Date());
                    merchantChannel.setModifyUserId(userId);
                    merchantChannel.setChannelMerKey(privateKye);
                    try {
                        // 渠道信息保存
                        channelId = merchantCoreService.doAddChannel(merchantChannel);
                    } catch (Exception e) {
                        return ResultDTO.fail("第" + timeNum + "行数据的渠道信息有误，导入失败");
                    }
                } else {
                    channelId = channel.getId();
                }

                /**
                 * 商戶pos机信息
                 */
                // 创建一个商户pos信息实体类对象接收商户pos信息
                MerchantPos merchantPos = new MerchantPos();
                merchantPos.setInnerCode(innerCode);
                merchantPos.setMercReferName(mercrefername);
                merchantPos.setPosType(posType);
                merchantPos.setPosFactory(posFactory);
                merchantPos.setSnCode(sncode);
                merchantPos.setPosAddr(posaddr);
                merchantPos.setStatus("1");
                merchantPos.setPosName(sncode + "号POS机");
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
                
                merchantTerminal1.setDebitCardRate(debitCardRate);
                if(!Strings.isNullOrEmpty(debitCardMaxFee)){
                    merchantTerminal1.setDebitCardMaxFee(Integer.valueOf(debitCardMaxFee));
                }
                if(!Strings.isNullOrEmpty(debitCardFee)){
                    merchantTerminal1.setDebitCardFee(Integer.valueOf(debitCardFee));
                }
               
                merchantTerminal1.setCreditCardRate(creditCardRate);
                if(!Strings.isNullOrEmpty(creditCardFee)){
                    merchantTerminal1.setCreditCardFee(Integer.valueOf(creditCardFee));
                }
                if(!Strings.isNullOrEmpty(creditCardMaxFee)){
                    merchantTerminal1.setCreditCardMaxFee(Integer.valueOf(creditCardMaxFee));
                }
                
                merchantTerminal1.setPosId(posId);
                merchantTerminal1.setTermName("刷卡");
                merchantTerminal1.setTerminalType("00");
                merchantTerminal1.setTerminalCode(innerTermCode);
                
                try {
                    // 支付宝费率转换
                    String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
                    BigDecimal bigDecimal1 = new BigDecimal(zfb1);
                    BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                    merchantTerminal1.setAlipayFee(String.valueOf(zfb.doubleValue()));
                    // 微信费率转换
                    String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
                    BigDecimal bigDecimal2 = new BigDecimal(wx1);
                    BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                    merchantTerminal1.setWechatFee(String.valueOf(wx.doubleValue()));
                    merchantTerminal1.setPosId(posId);

                    merchantTerminal1.setTermName("扫码");
                    merchantTerminal1.setTerminalType("01");
                } catch (Exception e) {
                    return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
                }
                
                // 把1个终端信息打包成List
                List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
                terminalList.add(merchantTerminal1);
                try {
                    // 终端信息保存
                    merchantCoreService.doAddMerTerminal(terminalList);
                } catch (Exception e) {
                    return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
                }
            }
            return ResultDTO.success();
        }
        // return b;
        return ResultDTO.fail("没有导入数据，Excel为空");
    }
}
