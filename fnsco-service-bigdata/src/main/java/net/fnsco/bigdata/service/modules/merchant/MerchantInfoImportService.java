package net.fnsco.bigdata.service.modules.merchant;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.api.dto.LKLMerchantSynchronizationDTO;
import net.fnsco.bigdata.api.dto.MerchantSynchronizationDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
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
    private MerchantTerminalDao merchantTerminalDao;
    @Autowired
    private Environment         env;

    private static final String IMAGE_PATH = "http://www.zheft.cn/static_img/";
    
    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(6);
    
    
    //LKL 批量导入客户
    public ResultDTO<String> merchantBatchImportToDBLKL(LKLMerchantSynchronizationDTO dto, Integer userId, Integer timeNum) throws ParseException {
        
        /**
         * LKL 批量导入客户
         * 商户信息，渠道类型，内部商户号，用户id，终端类型（00刷卡，01扫码）
         */
        ResultDTO<String> lklResult  = handlerMerchantCoreLKL(dto,"00",dto.getMerchantCode(),userId,dto.getTerminalType());
        if(!lklResult.isSuccess()){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
            return ResultDTO.failForMessage(lklResult.getData());
        }
        
        return ResultDTO.success();

    }
    

    private ResultDTO<String> handlerMerchantCoreLKL(LKLMerchantSynchronizationDTO dto, String channelType,String channelMerId,Integer userId,String terminalType) throws ParseException {
    	
    	String innerCode = null;
        Integer bankId = null;
        /**
		 * 判断商户是否存在
		 */
        MerchantCore lklMerchantcore = merchantCoreService.selectUniqueMer(dto.getPaperNum(), dto.getAccountNo(), channelType, channelMerId);
        /**
         * 商户基本信息
         */
        Integer merId = null;
        if (null != lklMerchantcore) {
            merId = lklMerchantcore.getId();
            innerCode = lklMerchantcore.getInnerCode();
        } else {
            innerCode = merchantCoreService.getInnerCode();
            /**
             * 商户银行卡信息
             */
            MerchantBank merchantBank = MerchantImportHelper.createMerchantBankLKL(innerCode, dto);
            try {
                bankId = merchantCoreService.doAddBanks(merchantBank);
            } catch (Exception e) {
                logger.error("导入商户数据异常", e);
                return ResultDTO.failForMessage("行数据的银行卡信息有误，导入失败");
            }
        }
        
        MerchantCore lklMerCore = MerchantImportHelper.createMerchantCoreLKL(merId, innerCode, dto);
        try {
            merchantCoreService.doAddMerCore(lklMerCore,String.valueOf(userId));
        } catch (Exception e) {
            logger.error("导入商户数据异常", e);
            return ResultDTO.failForMessage("行数据的基本数据信息有误，导入失败");
        }
        /**
         * 联系人处理
         */
        List<MerchantContact> contcactList = handlerContactLKL(innerCode,dto);
        try {
            merchantCoreService.doAddMerContact(contcactList);
        } catch (Exception e) {
            logger.error("导入商户数据异常", e);
            return ResultDTO.failForMessage("行数据的商户联系人信息有误，导入失败");
        }
        
        /**
         * 文件处理很慢，直接开启一个线程池执行
         */
        String taskInnerCode = innerCode;
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //文件处理
                saveFileToDB(dto.getFileInfos(), taskInnerCode);
            }
        });
        
        /**
         * 判断渠道有没有
         */
        MerchantChannel channel = merchantChannelDao.selectByInnerCodeAndChannelCode(innerCode, channelMerId, channelType);
        Integer channelId = null;
        String privateKye = null;
//        if("01".equals(channelType)){
//            privateKye = dto.getPrivateKye();
//        }
        if (null == channel) {
            // 新增加一个拉卡拉的渠道 
            MerchantChannel merchantChannel = MerchantImportHelper.createMerchantChannel(innerCode, channelMerId, channelType, userId, privateKye);
            try {
                // 渠道信息保存
                channelId = merchantCoreService.doAddChannel(merchantChannel);
            } catch (Exception e) {
                logger.error("导入商户数据异常", e);
                return ResultDTO.failForMessage("行数据的渠道信息有误，导入失败");
            }

        } else {
            channelId = channel.getId();
        }
        
        /**
         * 商戶pos机信息
         */
        String posName = null;
        if("01".equals(channelType) || "02".equals(channelType)){
            dto.setSnCode("");
            dto.setPosFactory("");
            dto.setPosType("");
            dto.setSalesSlip("");
//            dto.setInnerTermCode("");
            dto.setTerminalCode("");
            dto.setMerInstallArea("");
        }
        
        if("01".equals(channelType)){
            posName = "台码";
        }else if("02".equals(channelType)){
            dto.setXx("支付宝0.55% 微信0.6%");
            posName = "二维码";
        }
        MerchantPos posInfo = merchantPosService.selectBySnCodeAndInnerCode(dto.getSnCode(), innerCode,channelId);
        Integer posId = null;
        if (null != posInfo) {
            posId = posInfo.getId();
        }
        
        MerchantPos merchantPos = MerchantImportHelper.createMerchantPosLKL(posId, innerCode, bankId, channelId,dto,posName);

        if (null == posInfo) {
            try {
                // pos机信息保存
                posId = merchantPosService.insertPos(merchantPos);
            } catch (Exception e) {
                logger.error("导入商户数据异常", e);
                return ResultDTO.failForMessage("行数据的Pos机信息有误，导入失败");
            }
        } else {
            merchantPosService.updateByPrimaryKeySelective(merchantPos);
        }
        
        MerchantTerminal merchantTerminal = merchantTerminalDao.selectByTerminalType(merchantPos.getChannelTerminalCode(), innerCode, terminalType);
        Integer terId = null;
        if (null != merchantTerminal) {
            terId = merchantTerminal.getId();
        }
        MerchantTerminal merchantTerminal1 = MerchantImportHelper.createMerchantTerminalLKL(terId, innerCode,terminalType,dto);
        // 把1个终端信息打包成List
        List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
        terminalList.add(merchantTerminal1);
        try {
            // 终端信息保存
            merchantCoreService.doAddMerTerminal(terminalList);
        } catch (Exception e) {
            logger.error("导入商户数据异常", e);
            return ResultDTO.failForMessage("行数据的商户终端信息有误，导入失败");
        }
        
        return ResultDTO.success();
		
	}


	// 批量导入客户
    public ResultDTO<String> merchantBatchImportToDB(MerchantSynchronizationDTO dto, Integer userId, Integer timeNum) throws ParseException {
        
        /**
         * 法奈昇渠道商户
         * 商户信息，内部商户号，用户id，终端类型（00刷卡，01扫码）
         */
        ResultDTO<String> fnsResult  = handlerMerchantCore(dto,"03",dto.getMerchantCode(),userId,"00");
        if(!fnsResult.isSuccess()){
            return ResultDTO.failForMessage(fnsResult.getData());
        }
        
        /**
         * 浦发
         */
        if (!Strings.isNullOrEmpty(dto.getBusiCode())) {
            ResultDTO<String> pufaResult  = handlerMerchantCore(dto,"01",dto.getBusiCode(),userId,"01");
            if (!pufaResult.isSuccess()) {
                logger.error("导入商户数据异常");
                return ResultDTO.failForMessage(pufaResult.getData());
            }
        }
        
        /**
         * 爱农
         */
        ResultDTO<String> aiResult  = handlerMerchantCore(dto,"02",dto.getInnerCode(),userId,"01");
        if (!aiResult.isSuccess()) {
            return ResultDTO.failForMessage(aiResult.getData());
        }
        
        return ResultDTO.success();

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

            if (singleFile.length < 2) {
                logger.error("文件格式不正确!" + singleFile);
                continue;
            }
            String fileType = singleFile[0];
            String filePath = singleFile[1];
            if (!Strings.isNullOrEmpty(filePath) && filePath.contains("|")) {
                filePath = filePath.split("\\|")[0];
            }

            /**
             * 校验、如果存在，则舍去多余图片
             */
            MerchantFile record = new MerchantFile();
            record.setFileType(fileType);
            record.setInnerCode(innerCode);
            List<MerchantFile> datas = merchantFileDao.queryByCondition(record);
            if (CollectionUtils.isNotEmpty(datas)) {
                logger.info(innerCode +"商户存在文件类型:"+fileType+"存在!");
                continue;
            }
            
            /**
             * 此处需要先下载，再上传OSS，再获取图片路径和名称
             */
            String urlPath = uploadFile(filePath);
            if (Strings.isNullOrEmpty(urlPath)) {
                continue;
            }
            MerchantFile merchantFile = MerchantImportHelper.createMerchantFile(innerCode, filePath, fileType, urlPath);
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
    private String uploadFile(String filePath) {

        if (Strings.isNullOrEmpty(filePath)) {
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

        filePath = IMAGE_PATH + filePath;

        try {
            //上传阿里云OSS文件服务器
            FileUtils.getFileInputStreamByUrl(filePath, fileURL);
            OssLoaclUtil.uploadFile(fileURL, fileKey);
            String newUrl = OssLoaclUtil.getHeadBucketName() + "^" + fileKey;
            FileUtils.delFile(fileURL);
            return newUrl;
        } catch (Exception e) {
            logger.error("上传失败！", e);
        }
        return null;
    }

    
    /**
     * handlerContact:(处理联系人过滤)
     * @param innerCode
     * @param dto
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月28日 上午11:07:23
     * @return List<MerchantContact>    DOM对象
     */
    private List<MerchantContact> handlerContact(String innerCode,MerchantSynchronizationDTO dto){
        /**
         * 商户联系人信息
         */
        List<MerchantContact> contcactList = MerchantImportHelper.createMerchantContact(innerCode, dto);
        List<MerchantContact> result = Lists.newArrayList();
        for (MerchantContact merchantContact : contcactList) {
            int count = merchantContactDao.countContactByContactMobile(innerCode, merchantContact.getContactMobile());
            if(count == 0){
                result.add(merchantContact);
            }
        }
        return result;
    }
    
    private List<MerchantContact> handlerContactLKL(String innerCode,LKLMerchantSynchronizationDTO dto){
        /**
         * 商户联系人信息
         */
        List<MerchantContact> contcactList = MerchantImportHelper.createMerchantContactLKL(innerCode, dto);
        List<MerchantContact> result = Lists.newArrayList();
        for (MerchantContact merchantContact : contcactList) {
            int count = merchantContactDao.countContactByContactMobile(innerCode, merchantContact.getContactMobile());
            if(count == 0){
                result.add(merchantContact);
            }
        }
        return result;
    }
    
    /**
     * handlerMerchantCore:(处理不同渠道商户信息)
     * @param dto
     * @param channelType
     * @return
     * @throws ParseException    设定文件
     * @author    tangliang
     * @date      2017年10月10日 下午1:44:50
     * @return ResultDTO<String>    DOM对象
     */
    @Transactional
    private ResultDTO<String> handlerMerchantCore(MerchantSynchronizationDTO dto,String channelType,String channelMerId,Integer userId,String terminalType) throws ParseException{
        
        String innerCode = null;
        Integer bankId = null;
        /**
         * 判断渠道存在不
         */
        MerchantCore fnsMerchantcore = merchantCoreService.selectUniqueMer(dto.getPaperNum(), dto.getAccountNo(), channelType, channelMerId);
        /**
         * 商户基本信息
         */
        Integer merId = null;
        if (null != fnsMerchantcore) {
            merId = fnsMerchantcore.getId();
            innerCode = fnsMerchantcore.getInnerCode();
        } else {
            innerCode = merchantCoreService.getInnerCode();
            /**
             * 商户银行卡信息
             */
            MerchantBank merchantBank = MerchantImportHelper.createMerchantBank(innerCode, dto);
            try {
                bankId = merchantCoreService.doAddBanks(merchantBank);
            } catch (Exception e) {
                logger.error("导入商户数据异常", e);
                return ResultDTO.failForMessage("行数据的银行卡信息有误，导入失败");
            }
        }
        
        MerchantCore fnsMerCore = MerchantImportHelper.createMerchantCore(merId, innerCode, dto);
        try {
            merchantCoreService.doAddMerCore(fnsMerCore,String.valueOf(userId));
        } catch (Exception e) {
            logger.error("导入商户数据异常", e);
            return ResultDTO.failForMessage("行数据的基本数据信息有误，导入失败");
        }
        /**
         * 联系人处理
         */
        List<MerchantContact> contcactList = handlerContact(innerCode,dto);
        try {
            merchantCoreService.doAddMerContact(contcactList);
        } catch (Exception e) {
            logger.error("导入商户数据异常", e);
            return ResultDTO.failForMessage("行数据的商户联系人信息有误，导入失败");
        }
        
        /**
         * 文件处理很慢，直接开启一个线程池执行
         */
        String taskInnerCode = innerCode;
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //文件处理
                saveFileToDB(dto.getFileInfos(), taskInnerCode);
            }
        });
        
        /**
         * 判断渠道有没有
         */
        MerchantChannel channel = merchantChannelDao.selectByInnerCodeAndChannelCode(innerCode, channelMerId, channelType);
        Integer channelId = null;
        String privateKye = null;
        if("01".equals(channelType)){
            privateKye = dto.getPrivateKye();
        }
        if (null == channel) {
            // 新增加一个法奈昇的渠道 
            MerchantChannel merchantChannel = MerchantImportHelper.createMerchantChannel(innerCode, channelMerId, channelType, userId, privateKye);
            try {
                // 渠道信息保存
                channelId = merchantCoreService.doAddChannel(merchantChannel);
            } catch (Exception e) {
                logger.error("导入商户数据异常", e);
                return ResultDTO.failForMessage("行数据的渠道信息有误，导入失败");
            }

        } else {
            channelId = channel.getId();
        }
        
        /**
         * 商戶pos机信息
         */
        String posName = null;
        if("01".equals(channelType) || "02".equals(channelType)){
            dto.setSnCode("");
            dto.setPosFactory("");
            dto.setPosType("");
            dto.setSalesSlip("");
            dto.setInnerTermCode("");
            dto.setTerminalCode("");
            dto.setMerInstallArea("");
        }
        
        if("01".equals(channelType)){
            posName = "台码";
        }else if("02".equals(channelType)){
            dto.setXx("支付宝0.55% 微信0.6%");
            posName = "二维码";
        }
        MerchantPos posInfo = merchantPosService.selectBySnCodeAndInnerCode(dto.getSnCode(), innerCode,channelId);
        Integer posId = null;
        if (null != posInfo) {
            posId = posInfo.getId();
        }
        
        MerchantPos merchantPos = MerchantImportHelper.createMerchantPos(posId, innerCode, bankId, channelId,dto,posName);

        if (null == posInfo) {
            try {
                // pos机信息保存
                posId = merchantPosService.insertPos(merchantPos);
            } catch (Exception e) {
                logger.error("导入商户数据异常", e);
                return ResultDTO.failForMessage("行数据的Pos机信息有误，导入失败");
            }
        } else {
            merchantPosService.updateByPrimaryKeySelective(merchantPos);
        }
        
        MerchantTerminal merchantTerminal = merchantTerminalDao.selectByTerminalType(merchantPos.getChannelTerminalCode(), innerCode, terminalType);
        Integer terId = null;
        if (null != merchantTerminal) {
            terId = merchantTerminal.getId();
        }
        MerchantTerminal merchantTerminal1 = MerchantImportHelper.createMerchantTerminal(terId, innerCode,terminalType,dto);
        // 把1个终端信息打包成List
        List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
        terminalList.add(merchantTerminal1);
        try {
            // 终端信息保存
            merchantCoreService.doAddMerTerminal(terminalList);
        } catch (Exception e) {
            logger.error("导入商户数据异常", e);
            return ResultDTO.failForMessage("行数据的商户终端信息有误，导入失败");
        }
        
        return ResultDTO.success();
    }
}
