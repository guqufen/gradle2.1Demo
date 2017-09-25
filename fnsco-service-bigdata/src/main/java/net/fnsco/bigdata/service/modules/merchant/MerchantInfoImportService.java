package net.fnsco.bigdata.service.modules.merchant;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.dao.master.MerchantBankDao;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantContactDao;
import net.fnsco.bigdata.service.dao.master.MerchantFileDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantFile;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.core.utils.OssLoaclUtil;

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
    @Autowired
    private MerchantContactDao  merchantContactDao;
    @Autowired
    private MerchantBankDao     merchantBankDao;
    @Autowired
    private MerchantTerminalDao merchantTerminalDao;
    @Autowired
    private Environment         env;
    
    private static final String IMAGE_PATH = "http://www.zheft.cn/static_img/";
    
    // 批量导入客户
    @Transactional
    public ResultDTO<String> merchantBatchImportToDB(List<Object[]> customerList, Integer userId) throws ParseException {
        // 循环便利customList数组，将其中excel每一行的数据分批导入数据库
        
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        
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
                //createTime
                String createTime = String.valueOf(objs[27]);
                // busiCode
                String busiCode = String.valueOf(objs[28]);
                String privateKye = String.valueOf(objs[29]);
                String taxRegistCode = String.valueOf(objs[30]);
                // 扫码扣率
                String xx = String.valueOf(objs[31]);
                //文件信息
                String fileInfos = String.valueOf(objs[32]);
                // 一号pos机
                // 备注/1号机具SN
                String sncode = String.valueOf(objs[33]);
                //debitCardRate
                String debitCardRate = String.valueOf(objs[34]);
                //creditCardRate
                String creditCardRate = String.valueOf(objs[35]);
                //debitCardFee
                String debitCardFee = String.valueOf(objs[36]);
                //creditCardFee
                String creditCardFee = String.valueOf(objs[37]);
                //debitCardMaxFee
                String debitCardMaxFee = String.valueOf(objs[38]);
                //creditCardMaxFee
                String creditCardMaxFee = String.valueOf(objs[39]);
                //posType
                String posType = String.valueOf(objs[40]);
                //posFactory
                String posFactory = String.valueOf(objs[41]);
                //merchantCode
                String merchantCode = String.valueOf(objs[42]);
                //terminalCode
                String terminalCode = String.valueOf(objs[43]);
                //innerTermCode
                String innerTermCode = String.valueOf(objs[44]);

                /**
                 * 导入之前要先验证business_license_num 营业执照号码保持唯一,如果存在，则不新加商户，只加该商户其余属性。
                 */
                if (Strings.isNullOrEmpty(businesslicensenum)) {
                    logger.error("第" + timeNum + "营业执照为空 不能入库!");
                    continue;
                }
                String  innerCode = null;
                Integer bankId = null;
                MerchantCore merchantcore = merchantCoreService.selectBybusinessLicenseNum(businesslicensenum);
                /**
                 * 商户基本信息
                 */
                Integer merId = null;
                if(null != merchantcore){
                    merId = merchantcore.getId();
                    innerCode = merchantcore.getInnerCode();
                    merchantContactDao.deleteByInnerCode(innerCode);
                    merchantBankDao.deleteByInnerCode(innerCode);
                }else{
                    innerCode = merchantCoreService.getInnerCode();
                }
                
                MerchantCore merchantCore = MerchantImportHelper.createMerchantCore(merId,innerCode, mername, businesslicensenum, cardnum, legalperson, channelMerchant, legalpersonmobile,
                    cardvalidtimeStr, businesslicensevalidtimeStr, registaddress, mercflag, taxRegistCode, createTime);

                try {
                    merchantCoreService.doAddMerCore(merchantCore);
                } catch (Exception e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                    logger.error("第" + timeNum + "行数据的商户联系人信息有误，导入失败" + e);
                    return ResultDTO.fail("第" + timeNum + "行数据的商户联系人信息有误，导入失败");
                }
                /**
                 * 商户银行卡信息
                 */
                MerchantBank merchantBank = MerchantImportHelper.createMerchantBank(innerCode, accountname, accountno, accounttype, accountcardid, subbankname, openBankNum);
                try {
                    bankId = merchantCoreService.doAddBanks(merchantBank);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("第" + timeNum + "行数据的银行卡信息有误，导入失败" + e);
                    return ResultDTO.fail("第" + timeNum + "行数据的银行卡信息有误，导入失败");
                }
                
                /**
                 * 文件处理很慢，直接开启一个线程池执行
                 */
                String taskInnerCode = innerCode;
                cachedThreadPool.execute(new Runnable() {  
                    @Override  
                    public void run() {  
                      //文件处理
                        saveFileToDB(fileInfos, taskInnerCode);
                    }  
                });  
                
             
                /**
                 * 渠道信息--根据innerCode+merchantCode+channelType判断唯一性，如果存在，则获取ID ，否则新增加
                 */
                if (Strings.isNullOrEmpty(merchantCode)) {
                    logger.error("第" + timeNum + "渠道商户号为空 不能入库!");
                    continue;
                }
                
                /**
                 * 判断法奈昇渠道有没有
                 */
                MerchantChannel channel = merchantChannelDao.selectByInnerCodeAndChannelCode(innerCode, merchantCode,"03");
                Integer channelId = null;
                if (null == channel) {
                    // 新增加一个法奈昇的渠道 
                    MerchantChannel merchantChannel = MerchantImportHelper.createMerchantChannel(innerCode, merchantCode, "03", userId, privateKye);
                    try {
                        // 渠道信息保存
                        channelId = merchantCoreService.doAddChannel(merchantChannel);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("第" + timeNum + "行数据的渠道信息有误，导入失败" + e);
                        return ResultDTO.fail("第" + timeNum + "行数据的渠道信息有误，导入失败");
                    }
                    
                } else {
                    channelId = channel.getId();
                }
                /**
                 * 爱农
                 */
                MerchantChannel ainongChannel = merchantChannelDao.selectByInnerCodeAndChannelCode(innerCode, merchantCode,"02");
                if(null == ainongChannel){
                    createChanelAndPosAndTer(innerCode, merchantCode, userId, privateKye, mercrefername, posType, posFactory, sncode, posaddr, bankId, xx, debitCardRate,
                        debitCardMaxFee, debitCardFee, creditCardRate, creditCardFee, creditCardMaxFee, innerTermCode, terminalCode, timeNum, "02", "01");
                }
                
                /**
                 * 浦发
                 */
                if(!Strings.isNullOrEmpty(busiCode)){
                    createChanelAndPosAndTer(innerCode, busiCode, userId, privateKye, mercrefername, posType, posFactory, sncode, posaddr, bankId, xx,
                        debitCardRate, debitCardMaxFee, debitCardFee, creditCardRate, creditCardFee, creditCardMaxFee, innerTermCode, terminalCode, timeNum, "01", "00");
                }
                
                /**
                 * 商戶pos机信息
                 */
                MerchantPos posInfo = merchantPosService.selectBySnCodeAndInnerCode(sncode, innerCode);
                Integer posId = null;
                if(null != posInfo){
                    posId = posInfo.getId();
                }
                MerchantPos merchantPos = MerchantImportHelper.createMerchantPos(posId,innerCode, mercrefername, posType, posFactory, sncode, posaddr, bankId, channelId);
                
                if(null == posInfo){
                    try {
                        // pos机信息保存
                        posId = merchantPosService.insertPos(merchantPos);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("第" + timeNum + "行数据的Pos机信息有误，导入失败" + e);
                        return ResultDTO.fail("第" + timeNum + "行数据的Pos机信息有误，导入失败");
                    }
                }else{
                    merchantPosService.updateByPrimaryKeySelective(merchantPos);
                }

                /**
                 * 商户终端信息
                 */
                String alipayFee = "0.00";
                String wechatFee = "0.00";
                try {
                    // 支付宝费率转换
                    String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
                    if(NumberUtils.isNumber(zfb1)){
                        BigDecimal bigDecimal1 = new BigDecimal(zfb1);
                        BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                        alipayFee = String.valueOf(zfb.doubleValue());
                    }
                    // 微信费率转换
                    String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
                    if(NumberUtils.isNumber(wx1)){
                        BigDecimal bigDecimal2 = new BigDecimal(wx1);
                        BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                        wechatFee = String.valueOf(wx.doubleValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("第" + timeNum + "行数据的商户终端信息有误，导入失败") ;
                    return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
                }
                
                MerchantTerminal merchantTerminal = merchantTerminalDao.selectByTerminalType(posId, innerCode, "00");
                Integer terId = null;
                if(null != merchantTerminal){
                    terId = merchantTerminal.getId();
                }
                MerchantTerminal merchantTerminal1 = MerchantImportHelper.createMerchantTerminal(terId,innerCode, debitCardRate, debitCardMaxFee, debitCardFee, creditCardRate, creditCardFee,
                    creditCardMaxFee, posId, innerTermCode, terminalCode, alipayFee, wechatFee,"00","刷卡");
                // 把1个终端信息打包成List
                List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
                terminalList.add(merchantTerminal1);
                try {
                    // 终端信息保存
                    merchantCoreService.doAddMerTerminal(terminalList);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("第" + timeNum + "行数据的商户终端信息有误，导入失败" + e);
                    return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
                }
            }
            return ResultDTO.success();
        }
        
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
                logger.error("文件内容为空!");
                continue;
            }

            String singleFile[] = files.split("=");
            
            if(singleFile.length < 2){
                logger.error("文件格式不正确!"+singleFile);
                continue;
            }
            String fileType = singleFile[0];
            String filePath = singleFile[1];
            
            /**
             * 校验、如果存在，则舍去多余图片
             */
            MerchantFile record = new MerchantFile();
            record.setFileType(fileType);
            record.setInnerCode(innerCode);
            List<MerchantFile> datas = merchantFileDao.queryByCondition(record);
            /**
             * 此处需要先下载，再上传OSS，再获取图片路径和名称
             */
            String urlPath = uploadFile(filePath);
            if(Strings.isNullOrEmpty(urlPath)){
                continue;
            }
            MerchantFile merchantFile = MerchantImportHelper.createMerchantFile(innerCode, filePath, fileType, urlPath);
            if(CollectionUtils.isNotEmpty(datas)){
                merchantFileDao.deleteByInnerCodeAndFileType(innerCode, fileType);
            }
            merchantFileDao.insertSelective(merchantFile);
        }
    }
    
    /**
     * uploadFile:(获取OSS的URL)
     * @param filePath
     * @param fileKey
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月20日 上午9:35:31
     * @return String    DOM对象
     */
    private String uploadFile(String filePath){
        
        if(Strings.isNullOrEmpty(filePath)){
            return null;
        }
        String line = System.getProperty("file.separator");// 文件分割符
        // 保存文件的路径
        String prefix = filePath.substring(filePath.lastIndexOf(".") + 1);
        // 数据库存的路径
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String stry = this.env.getProperty("fileUpload.url") + line + year;// +"\\"+month+"\\";
        File yearPath = new File(stry);
        // 如果文件夹不存在则创建
        if (!yearPath.exists()) {
            logger.info("年份目录不存在");
            yearPath.mkdirs();
        } else {
            logger.info("年份目录已存在");
        }

        String strm = this.env.getProperty("fileUpload.url") + line + year + line + month + line;
        File monthPath = new File(strm);
        if (!monthPath.exists()) {
            logger.info("月份目录不存在");
            monthPath.mkdirs();
        } else {
            logger.info("月份目录已存在");
        }

        String yearMonthPath = year + line + month + line;
        String newFileName = System.currentTimeMillis() + "." + prefix;
        String fileKey = year + "/" + month + "/" + newFileName;
        String filepath = yearMonthPath + newFileName;

        String fileURL = this.env.getProperty("fileUpload.url") + line + filepath;
       
        
        filePath = IMAGE_PATH+filePath;
        
        try {
            //上传阿里云OSS文件服务器
            FileUtils.getFileInputStreamByUrl(filePath,fileURL);
            OssLoaclUtil.uploadFile(fileURL, fileKey);
            String newUrl = OssLoaclUtil.getHeadBucketName() + "^" + fileKey;
            return newUrl;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传失败！" + e);
//            throw new RuntimeException();
        }
        return null;
    }
    
    /**
     * createChanelAndPosAndTer:(新入库渠道POS以及渠道一起)    设定文件
     * @author    tangliang
     * @date      2017年9月15日 上午11:08:22
     * @return void    DOM对象
     */
    private ResultDTO<Object> createChanelAndPosAndTer(String innerCode,String merchantCode,Integer userId,String privateKye,String mercrefername,String posType,String posFactory,String sncode,String posaddr,Integer bankId,
                                          String xx,String debitCardRate,String debitCardMaxFee,String debitCardFee,String creditCardRate,String creditCardFee,
                                          String creditCardMaxFee,String innerTermCode,String terminalCode,Integer timeNum,String channelType,String terminalType){
        MerchantChannel pufaChannel = merchantChannelDao.selectByInnerCodeAndChannelCode(innerCode, merchantCode,channelType);
        if(null == pufaChannel){
            MerchantChannel merchantChannel = MerchantImportHelper.createMerchantChannel(innerCode, merchantCode, channelType, userId, privateKye);
            Integer pufaChannelId = null;
            try {
                // 渠道信息保存
                pufaChannelId = merchantCoreService.doAddChannel(merchantChannel);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultDTO.fail("第" + timeNum + "行数据的渠道信息有误，导入失败");
            }
            /**
             * 商戶pos机信息
             */
            MerchantPos merchantPos = MerchantImportHelper.createMerchantPos(null,innerCode, mercrefername, posType, posFactory, sncode, posaddr, bankId, pufaChannelId);
            Integer posId = null;
            try {
                // pos机信息保存
                posId = merchantPosService.insertPos(merchantPos);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultDTO.fail("第" + timeNum + "行数据的Pos机信息有误，导入失败");
            }

            /**
             * 商户终端信息
             */
            String alipayFee = "0.00";
            String wechatFee = "0.00";
            try {
                // 支付宝费率转换
                String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
                if(NumberUtils.isNumber(zfb1)){
                    BigDecimal bigDecimal1 = new BigDecimal(zfb1);
                    BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                    alipayFee = String.valueOf(zfb.doubleValue());
                }
               
                // 微信费率转换
                String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
                if(NumberUtils.isNumber(wx1)){
                    BigDecimal bigDecimal2 = new BigDecimal(wx1);
                    BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
                    wechatFee = String.valueOf(wx.doubleValue());
                }
              
            } catch (Exception e) {
                e.printStackTrace();
                return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
            }
            
            String termName = "扫码";
            if("00".equals(terminalType)){
                termName = "刷卡";
            }
            
            MerchantTerminal merchantTerminal1 = MerchantImportHelper.createMerchantTerminal(null,innerCode, debitCardRate, debitCardMaxFee, debitCardFee, creditCardRate, creditCardFee,
                creditCardMaxFee, posId, innerTermCode, terminalCode, alipayFee, wechatFee,terminalType,termName);
            // 把1个终端信息打包成List
            List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
            terminalList.add(merchantTerminal1);
            try {
                // 终端信息保存
                merchantCoreService.doAddMerTerminal(terminalList);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultDTO.fail("第" + timeNum + "行数据的商户终端信息有误，导入失败");
            }
        }
        
        return ResultDTO.success();
    }
}
