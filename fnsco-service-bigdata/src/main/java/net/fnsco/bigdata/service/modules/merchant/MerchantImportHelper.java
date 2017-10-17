package net.fnsco.bigdata.service.modules.merchant;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.MerchantSynchronizationDTO;
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

public class MerchantImportHelper{
    
    private static Logger logger = LoggerFactory.getLogger(MerchantImportHelper.class);
    /**
     * createMerchantCore:(创建MerchantCore实例)
     * @throws ParseException    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午4:37:06
     * @return MerchantCore    DOM对象
     */
    public static MerchantCore createMerchantCore(Integer id,String innerCode,MerchantSynchronizationDTO dto) throws ParseException{
        MerchantCore merchantCore = new MerchantCore();
        merchantCore.setId(id);
        merchantCore.setInnerCode(innerCode);
        merchantCore.setMerName(dto.getMerchant());
        merchantCore.setBusinessLicenseNum(dto.getBusinessLicenseNum());
        merchantCore.setLegalValidCardType("0");
        merchantCore.setCardNum(dto.getPaperNum());
        merchantCore.setLegalPerson(dto.getMerLegalPerson());
        merchantCore.setAbbreviation(dto.getChannelMerchant());
        merchantCore.setLegalPersonMobile(dto.getLegalPersonTel());
        
        String cardvalidtime = dto.getPaperValidTime();
        String businesslicensevalidtime = dto.getBusinessLicenseValidTime();
        // excel中导出的时间是“EEE MMM dd HH:mm:ss z yyyy”类型的String类，将他转换成"yyyy/MM/dd"
//        String cardvalidtime = null;
//        if(!Strings.isNullOrEmpty(dto.getPaperValidTime())){
//            SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
//            Date date1 = sdf1.parse(dto.getPaperValidTime());
//            sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//            cardvalidtime = sdf1.format(date1);
//            
//        }
//        String businesslicensevalidtime = null;
//        if(!Strings.isNullOrEmpty(dto.getBusinessLicenseValidTime())){
//            SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
//            Date date2 = sdf2.parse(dto.getBusinessLicenseValidTime());
//            sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//            businesslicensevalidtime = sdf2.format(date2);
//        }
        
        merchantCore.setCardValidTime(cardvalidtime);
        merchantCore.setBusinessLicenseValidTime(businesslicensevalidtime);
        merchantCore.setRegistAddress(dto.getMerRegistAddress());
        merchantCore.setMercFlag(dto.getMercFlag());
        merchantCore.setSource(2);//浙付通导入
        merchantCore.setAgentId(1);//默认
        merchantCore.setTaxRegistCode(dto.getTaxRegistCode());
        if(!Strings.isNullOrEmpty(dto.getCreateTime())){
            merchantCore.setModifyTime(DateUtils.formateToDate(dto.getCreateTime()));
        }
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
    public static List<MerchantContact> createMerchantContact(String innerCode,MerchantSynchronizationDTO dto){
     // 创建一个商户联系人信息实体类对象接收商户联系人信息
        MerchantContact merchantContact = new MerchantContact();
        merchantContact.setInnerCode(innerCode);
        merchantContact.setContactName(dto.getLinkMan());
        merchantContact.setContactMobile(dto.getLinkManTel());
        merchantContact.setContactEmail(dto.getLinkManEmail());
        MerchantContact merchantContact1 = new MerchantContact();
        merchantContact1.setInnerCode(innerCode);
        merchantContact1.setContactName(dto.getFinanceLinkMan());
        merchantContact1.setContactMobile(dto.getFinanceLinkManTel());
        merchantContact1.setContactEmail(dto.getFinanceLinkManEmail());
        MerchantContact merchantContact2 = new MerchantContact();
        merchantContact2.setInnerCode(innerCode);
        merchantContact2.setContactName(dto.getMerPrincipal());
        merchantContact2.setContactMobile(dto.getMerPrincipalTel());
        merchantContact2.setContactEmail(dto.getMerPrincipalEmail());
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
    public static MerchantBank createMerchantBank(String innerCode,MerchantSynchronizationDTO dto){
        String accounttype = dto.getAccountType();
        if(!Strings.isNullOrEmpty(accounttype)){
            if(accounttype.contains("private")){
                accounttype = "0";
            }else{
                accounttype = "1";
            }
        }
        MerchantBank merchantBank = new MerchantBank();
        merchantBank.setInnerCode(innerCode);
        merchantBank.setAccountName(dto.getAccountName());
        merchantBank.setAccountNo(dto.getAccountNo());
        merchantBank.setAccountType(accounttype);
        merchantBank.setAccountCardId(dto.getAccountHolderID());
        merchantBank.setSubBankName(dto.getSubBankName());
        merchantBank.setOpenBankNum(dto.getOpenBankNum());
        merchantBank.setOpenBank(dto.getOpenBank());
        merchantBank.setOpenBankPrince(dto.getOpenBankPrince());
        merchantBank.setOpenBankCity(dto.getOpenBankCity());
        
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
    public static MerchantPos createMerchantPos(Integer id,String innerCode,Integer bankId,Integer channelId,MerchantSynchronizationDTO dto,String posName){
     // 创建一个商户pos信息实体类对象接收商户pos信息
        MerchantPos merchantPos = new MerchantPos();
        merchantPos.setId(id);
        merchantPos.setInnerCode(innerCode);
        merchantPos.setMercReferName(dto.getSalesSlip());
        merchantPos.setPosType(dto.getPosType());
        merchantPos.setPosFactory(dto.getPosFactory());
        merchantPos.setSnCode(dto.getSnCode());
        merchantPos.setPosAddr(dto.getMerInstallArea());
        merchantPos.setStatus("1");
        if(Strings.isNullOrEmpty(posName)){
            posName = dto.getSnCode() + "号POS机";
        }
        merchantPos.setPosName(posName);
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
    public static MerchantTerminal createMerchantTerminal(Integer id,String innerCode,Integer posId,String terminalType,MerchantSynchronizationDTO dto){
        
        String xx = dto.getXx();
        String alipayFee = "0.00";
        String wechatFee = "0.00";
        String debitCardRate = dto.getDebitCardRate();
        String creditCardRate = dto.getCreditCardRate();
        String debitCardMaxFee = dto.getDebitCardMaxFee();
        String creditCardMaxFee = dto.getCreditCardMaxFee();
        String debitCardFee = dto.getDebitCardFee();
        String creditCardFee = dto.getCreditCardFee();
        try {
            // 支付宝费率转换
            String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
            if (NumberUtils.isNumber(zfb1)) {
                BigDecimal bigDecimal1 = new BigDecimal(zfb1);
                BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                alipayFee = String.valueOf(zfb.doubleValue());
            }

            // 微信费率转换
            String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
            if (NumberUtils.isNumber(wx1)) {
                BigDecimal bigDecimal2 = new BigDecimal(wx1);
                BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                wechatFee = String.valueOf(wx.doubleValue());
            }
            //转换单位
            if (NumberUtils.isNumber(debitCardRate)) {
                BigDecimal bigDecimal2 = new BigDecimal(debitCardRate);
                BigDecimal temp = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                debitCardRate = String.valueOf(temp.doubleValue());
            }
            if (NumberUtils.isNumber(creditCardRate)) {
                BigDecimal bigDecimal2 = new BigDecimal(creditCardRate);
                BigDecimal temp = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                creditCardRate = String.valueOf(temp.doubleValue());
            }
            if (NumberUtils.isNumber(debitCardMaxFee)) {
                BigDecimal bigDecimal2 = new BigDecimal(debitCardMaxFee);
                BigDecimal temp = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                debitCardMaxFee = String.valueOf(temp.intValue());
            }
            if (NumberUtils.isNumber(creditCardMaxFee)) {
                BigDecimal bigDecimal2 = new BigDecimal(creditCardMaxFee);
                BigDecimal temp = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                creditCardMaxFee = String.valueOf(temp.intValue());
            }
            if (NumberUtils.isNumber(debitCardFee)) {
                BigDecimal bigDecimal2 = new BigDecimal(debitCardFee);
                BigDecimal temp = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                debitCardFee = String.valueOf(temp.intValue());
            }
            if (NumberUtils.isNumber(creditCardFee)) {
                BigDecimal bigDecimal2 = new BigDecimal(creditCardFee);
                BigDecimal temp = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                creditCardFee = String.valueOf(temp.intValue());
            }
        } catch (Exception e) {
            logger.error("转换终端参数出错", e);
            throw new RuntimeException(e);
        }
        
        
        MerchantTerminal merchantTerminal1 = new MerchantTerminal();
        merchantTerminal1.setInnerCode(innerCode);
        merchantTerminal1.setId(id);
        
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
        String termName = "扫码";
        if ("00".equals(terminalType)) {
            termName = "刷卡";
        }
        merchantTerminal1.setTermName(termName);
        merchantTerminal1.setTerminalType(terminalType);
        merchantTerminal1.setTerminalCode(dto.getInnerTermCode());
        merchantTerminal1.setChannelTerminalCode(dto.getTerminalCode());
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
