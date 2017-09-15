package net.fnsco.bigdata.service.modules.merchant;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantFileDao;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantFile;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;

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
    @Autowired
    private MerchantFileDao     merchantFileDao;

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
                //                String mercReferName = String.valueOf(objs[46]);

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
                if (merchantcore == null) {
                    innerCode = merchantCoreService.getInnerCode();
                    MerchantCore merchantCore = MerchantImportHelper.createMerchantCore(innerCode, mername, businesslicensenum, cardnum, legalperson, channelMerchant, legalpersonmobile,
                        cardvalidtimeStr, businesslicensevalidtimeStr, registaddress, mercflag, taxRegistCode, createTime);

                    try {
                        merchantCoreService.doAddMerCore(merchantCore);
                    } catch (Exception e) {
                        logger.error("第" + timeNum + "行数据的基本数据信息有误，导入失败" + e);
                        return ResultDTO.fail("第" + timeNum + "行数据的基本数据信息有误，导入失败");
                    }

                    /**
                     * 商户联系人信息
                     */
                    List<MerchantContact> contcactList = MerchantImportHelper.createMerchantContact(innerCode, contactname, contactmobile, contactemail, financeLinkMan, financeLinkManTel,
                        financeLinkManEmail, merPrincipal, merPrincipalTel, merPrincipalEmail);
                    try {
                        merchantCoreService.doAddMerContact(contcactList);
                    } catch (Exception e) {
                        return ResultDTO.fail("第" + timeNum + "行数据的商户联系人信息有误，导入失败");
                    }
                    /**
                     * 商户银行卡信息
                     */
                    MerchantBank merchantBank = MerchantImportHelper.createMerchantBank(innerCode, accountname, accountno, accounttype, accountcardid, subbankname, openBankNum);
                    try {
                        bankId = merchantCoreService.doAddBanks(merchantBank);
                    } catch (Exception e) {
                        return ResultDTO.fail("第" + timeNum + "行数据的银行卡信息有误，导入失败");
                    }

                    //文件处理
                    saveFileToDB(fileInfos, innerCode);

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
                    MerchantChannel merchantChannel = MerchantImportHelper.createMerchantChannel(innerCode, merchantCode, channelType, userId, privateKye);
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
                MerchantPos merchantPos = MerchantImportHelper.createMerchantPos(innerCode, mercrefername, posType, posFactory, sncode, posaddr, bankId, channelId);
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
                String alipayFee = null;
                String wechatFee = null;
                try {
                    // 支付宝费率转换
                    String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
                    BigDecimal bigDecimal1 = new BigDecimal(zfb1);
                    BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                    alipayFee = String.valueOf(zfb.doubleValue());
                    // 微信费率转换
                    String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
                    BigDecimal bigDecimal2 = new BigDecimal(wx1);
                    BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                    wechatFee = String.valueOf(wx.doubleValue());
                } catch (Exception e) {
                    return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
                }

                MerchantTerminal merchantTerminal1 = MerchantImportHelper.createMerchantTerminal(innerCode, debitCardRate, debitCardMaxFee, debitCardFee, creditCardRate, creditCardFee,
                    creditCardMaxFee, posId, innerTermCode, terminalCode, alipayFee, wechatFee);
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

    /**
     * saveFileToDB:(处理文件信息)
     * @param fileInfos    设定文件
     * @author    tangliang
     * @date      2017年9月14日 下午5:39:32
     * @return void    DOM对象
     */
    private void saveFileToDB(String fileInfos, String innerCode) {
        if (Strings.isNullOrEmpty(fileInfos)) {
            logger.error("文件信息为空");
            return;
        }

        String[] fileMap = fileInfos.split(",");

        for (String files : fileMap) {
            if (Strings.isNullOrEmpty(files)) {
                continue;
            }

            String singleFile[] = files.split("=");

            String fileType = singleFile[0];
            String filePath = singleFile[1];
            /**
             * 此处需要下载，上传OSS，再获取图片路径和名称
             */
            MerchantFile merchantFile = MerchantImportHelper.createMerchantFile(innerCode, "", fileType, filePath);
            merchantFileDao.insertSelective(merchantFile);
        }
    }
}
