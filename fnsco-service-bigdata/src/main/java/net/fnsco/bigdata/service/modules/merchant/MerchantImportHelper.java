package net.fnsco.bigdata.service.modules.merchant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.common.base.Strings;

import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantFile;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.utils.DateUtils;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月14日 下午4:33:10
 */

public class MerchantImportHelper {
    
    /**
     * createMerchantCore:(创建MerchantCore实例)
     * @throws ParseException    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:37:06
     * @return MerchantCore    DOM对象
     */
    public static MerchantCore createMerchantCore(String innerCode,String mername,String businesslicensenum,String cardnum,String legalperson,String channelMerchant,String legalpersonmobile,
                                                  String cardvalidtimeStr,String businesslicensevalidtimeStr,String registaddress,String mercflag,String taxRegistCode,String createTime) throws ParseException{
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
        
        return merchantCore;
    }
    
    /**
     * createMerchantContact:(创建MerchantContact实例)
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:39:44
     * @return List<MerchantContact>    DOM对象
     */
    public static List<MerchantContact> createMerchantContact(String innerCode,String contactname,String contactmobile,String contactemail,String financeLinkMan,String financeLinkManTel,String financeLinkManEmail,
                                                              String merPrincipal,String merPrincipalTel,String merPrincipalEmail){
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
        
        return contcactList;
    }
    /**
     * createMerchantBank:(创建一个MerchantBank实例)
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:43:28
     * @return MerchantBank    DOM对象
     */
    public static MerchantBank createMerchantBank(String innerCode,String accountname,String accountno,String accounttype,String accountcardid,String subbankname,String openBankNum){
        MerchantBank merchantBank = new MerchantBank();
        merchantBank.setInnerCode(innerCode);
        merchantBank.setAccountName(accountname);
        merchantBank.setAccountNo(accountno);
        if(!Strings.isNullOrEmpty(accounttype)){
            if(accounttype.contains("private")){
                accounttype = "1";
            }else if(accounttype.contains("public")){
                accounttype = "0";
            }
        }
        merchantBank.setAccountType(accounttype);
        merchantBank.setAccountCardId(accountcardid);
        merchantBank.setSubBankName(subbankname);
        merchantBank.setOpenBankNum(openBankNum);
        
        return merchantBank;
    }
    
    /**
     * createMerchantChannel:(创建一个MerchantChannel实例)    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:46:39
     * @return void    DOM对象
     */
    public static MerchantChannel createMerchantChannel(String innerCode,String merchantCode,String channelType,Integer userId,String privateKye){
        MerchantChannel merchantChannel = new MerchantChannel();
        merchantChannel.setInnerCode(innerCode);
        merchantChannel.setChannelMerId(merchantCode);
        merchantChannel.setChannelType(channelType);
        merchantChannel.setCreateTime(new Date());
        merchantChannel.setModifyTime(new Date());
        merchantChannel.setModifyUserId(userId);
        merchantChannel.setChannelMerKey(privateKye);
        
        return merchantChannel;
    }
    /**
     * createMerchantPos:(创建一个MerchantPos实例)
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:50:21
     * @return MerchantPos    DOM对象
     */
    public static MerchantPos createMerchantPos(String innerCode,String mercrefername,String posType,String posFactory,String sncode,String posaddr,Integer bankId,Integer channelId){
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
        
        return merchantPos;
    }
    /**
     * createMerchantTerminal:(创建一个MerchantTerminal实例)
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:53:37
     * @return MerchantTerminal    DOM对象
     */
    public static MerchantTerminal createMerchantTerminal(String innerCode,String debitCardRate,String debitCardMaxFee,String debitCardFee,String creditCardRate,String creditCardFee,String creditCardMaxFee,
                                                          Integer posId,String innerTermCode,String terminalCode,String alipayFee,String wechatFee,String terminalType,String termName){
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
        merchantTerminal1.setTermName(termName);
        merchantTerminal1.setTerminalType(terminalType);
        merchantTerminal1.setTerminalCode(innerTermCode);
        merchantTerminal1.setInnerTermCode(terminalCode);
        merchantTerminal1.setAlipayFee(alipayFee);
        merchantTerminal1.setWechatFee(wechatFee);
        
        return merchantTerminal1;
    }
    
    /**
     * createMerchantFile:(创建一个MerchantFile实例)
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午5:48:07
     * @return MerchantFile    DOM对象
     */
    public static MerchantFile createMerchantFile(String innerCode,String fileName,String fileType,String filePath){
        MerchantFile file = new MerchantFile();
        file.setInnerCode(innerCode);
        file.setFileName(fileName);
        file.setFileType(fileType);
        file.setFilePath(filePath);
        file.setCreateTime(new Date());
        
        return file;
    }
}
