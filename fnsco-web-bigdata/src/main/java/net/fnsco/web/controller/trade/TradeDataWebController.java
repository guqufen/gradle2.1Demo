package net.fnsco.web.controller.trade;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import net.fnsco.auth.service.UserService;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.bigdata.api.constant.BigdataConstant.ChannelTypeEnum;
import net.fnsco.bigdata.api.constant.BigdataConstant.DataSourceEnum;
import net.fnsco.bigdata.api.constant.BigdataConstant.PayMediumEnum;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.LklTradeDataService;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.bigdata.comm.ServiceConstant.PayTypeEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.bigdata.service.domain.trade.TradeDataLkl;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.ExcelUtils;
import net.fnsco.core.utils.ReadExcel;
import net.fnsco.core.utils.StringUtil;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.service.sys.TradeDataImportService;
import net.fnsco.order.service.sys.entity.ImportErrorDO;
import net.sf.json.JSONObject;

/**
 * @desc 交易统计控制器
 * @author tangliang
 * @date 2017年6月28日 下午2:22:46
 */
@Controller
@RequestMapping(value = "/web/trade")
@Api(value = "/web", tags = { "交易统计控制器" })
public class TradeDataWebController extends BaseController {

    @Autowired
    private TradeDataService       tradeDataService;
    @Autowired
    private TradeDataImportService tradeDataImportService;
    @Autowired
    private LklTradeDataService    tradeDataLklService;
    @Autowired
    private UserService   userService; 
    
    private ExecutorService        cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * 交易统计分页查询
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:list" })
    public ResultPageDTO<TradeData> query(TradeDataDTO tradeDataDTO, Integer currentPageNum, Integer pageSize) {
    	Integer userId = this.getUserId();
    	Integer agentId = null;
    	UserDO user = userService.getUserById(userId);
    	if(user == null){
    		return null;
    	}
    	if(user.getType() == 2){//该用户是代理商用户
    		agentId = user.getAgentId();
    	}
    	
        return tradeDataService.queryTradeData(tradeDataDTO, currentPageNum, pageSize,agentId);
    }
    
    /**
     * 查询总金额
     * @param tradeDataDTO
     * @return
     */
    @RequestMapping(value="queryTotalAmount",method=RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:list" })
    public ResultDTO<String>  queryTotalAmount(TradeDataDTO tradeDataDTO){
    	Integer userId = this.getUserId();
    	Integer agentId = null;
    	UserDO user = userService.getUserById(userId);
    	if(user == null){
    		return null;
    	}
    	if(2 == user.getType()){//该用户是代理商用户
    		agentId = user.getAgentId();
    	}
    	String total= tradeDataService.queryTotalAmount(tradeDataDTO,agentId);
    	return ResultDTO.success(total);
    }
    
    /**
     * 拉卡拉交易统计分页查询
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryLkl", method = RequestMethod.GET)
    @ResponseBody
//    @RequiresPermissions(value = { "m:trade:list" })
    public ResultPageDTO<TradeDataLkl> queryLkl(TradeDataDTO tradeDataDTO, Integer currentPageNum, Integer pageSize) {
        return tradeDataLklService.queryTradeData(tradeDataDTO, currentPageNum, pageSize);
    }
    
    /**
     * 拉卡拉查询总金额
     * @param tradeDataDTO
     * @return
     */
    @RequestMapping(value="queryLklTotalAmount",method=RequestMethod.POST)
    @ResponseBody
//    @RequiresPermissions(value = { "m:trade:list" })
    public ResultDTO<String>  queryLklTotalAmount(TradeDataDTO tradeDataDTO){
    	String total= tradeDataLklService.queryTotalAmount(tradeDataDTO);
    	return ResultDTO.success(total);
    }
    
    /**
     * 交易流水excel导出
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @param req
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:export" })
    public void export(TradeDataDTO tradeDataDTO, HttpServletRequest req, HttpServletResponse response) throws IOException {
    	if("null".equals(tradeDataDTO.getChannelType())) {
    		tradeDataDTO.setChannelType(null);
    	}
    	System.out.println(new Date());
        List<TradeData> dataList = tradeDataService.queryDataList(tradeDataDTO);
        // 转换日期显示
        System.out.println(new Date());
        if (dataList != null) {
            for (TradeData merchantdo : dataList) {
                // 支付方式
                if ("00".equals(merchantdo.getPayType())) {
                    merchantdo.setPayType("刷卡");
                } else if ("01".equals(merchantdo.getPayType())) {
                    merchantdo.setPayType("扫码");
                }else if ("02".equals(merchantdo.getPayType())){
                	merchantdo.setPayType("分期付");
                }
                // 处理金额
                if (!Strings.isNullOrEmpty(merchantdo.getAmt())) {
                    BigDecimal str = new BigDecimal(merchantdo.getAmt());
                    double resu = str.divide(new BigDecimal(100)).doubleValue();
                    DecimalFormat df = new DecimalFormat("#0.00");
                    merchantdo.setAmt(df.format(resu));
                }
                // 处理来源
                if (!Strings.isNullOrEmpty(merchantdo.getSource())) {
                    if ("00".equals(merchantdo.getSource())) {
                        merchantdo.setSource("拉卡拉");
                    } else {
                        merchantdo.setSource("其他");
                    }
                }
                // 处理交易类型
                if (!Strings.isNullOrEmpty(merchantdo.getTxnType()) ) {
                    if ("1".equals(merchantdo.getTxnType())) {
                        merchantdo.setTxnType("消费");
                    } else if ("2".equals(merchantdo.getTxnType())) {
                        merchantdo.setTxnType("撤销");
                    }
                }
                // 借贷类型
                if (!Strings.isNullOrEmpty(merchantdo.getDcType()) ) {
                    if ("00".equals(merchantdo.getDcType())) {
                        merchantdo.setDcType("境内借记卡");
                    } else if ("01 ".equals(merchantdo.getDcType())) {
                        merchantdo.setDcType("境内贷记卡");
                    }else if (" 60 ".equals(merchantdo.getDcType())) {
                        merchantdo.setDcType("境外借记卡");
                    }else if (" 61 ".equals(merchantdo.getDcType())) {
                        merchantdo.setDcType("境外贷记卡");
                    }

                }
                // 处理状态
                if ("0".equals(merchantdo.getStatus())) {
                    merchantdo.setStatus("非正常交易");
                } else if ("1".equals(merchantdo.getStatus())) {
                    merchantdo.setStatus("正常交易");
                }
                String li0 = merchantdo.getTimeStamp();
                String li1 = merchantdo.getOrderTime();
                Date li2 = merchantdo.getCreateTime();
                if (li0 != null) {
                    String year = li0.substring(0, 4);
                    String month = li0.substring(4, 6);
                    String day = li0.substring(6, 8);
                    String hour = li0.substring(8, 10);
                    String minutes = li0.substring(10, 12);
                    String seconds = li0.substring(12, 14);
                    String dateString0 = year + "-" + month + "-" + day + ' ' + hour + ':' + minutes + ':' + seconds;
                    merchantdo.setTimeStamp(dateString0);
                }
                if (li1 != null) {
                    String year = li1.substring(0, 4);
                    String month = li1.substring(4, 6);
                    String day = li1.substring(6, 8);
                    String hour = li1.substring(8, 10);
                    String minutes = li1.substring(10, 12);
                    String seconds = li1.substring(12, 14);
                    String dateString1 = year + "-" + month + "-" + day + ' ' + hour + ':' + minutes + ':' + seconds;
                    merchantdo.setOrderTime(dateString1);
                }
                if (li2 != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString2 = formatter.format(li2);
                    merchantdo.setCreateTimeStr(dateString2);
                }
            }
        }

        JSONObject jObject = new JSONObject();
        jObject.put("data", dataList);
        List<TradeData> list = (List<TradeData>) jObject.get("data");
        String itemMark = "orderNo,orderIdScan,merName,innerCode,snCode,referNo,txnType,merId,timeStamp,payType,amt,certifyId,dcType,orderTime,termId,batchNo,sysTraceNo,authCode,source,createTimeStr,status";
        String itemParap = "订单号,扫码交易的订单号,商户名,内部商户号,终端SN码,参考号,交易类型,结算商户号,支付时间,支付方式,交易金额(元),持卡人卡号,卡类型,订单时间,终端号,批次号,凭证号,授权码,来源,创建时间,状态";

        String[] itemMarks = itemMark.split(",");// 键
        String[] itemParaps = itemParap.split(",");// 列头

        HSSFWorkbook workbook = ExcelUtils.getInputStream(itemParaps.length, itemMarks, itemParaps, list, "交易流水");

        response.setContentType("application/vnd.ms-excel;");
        String nowStr = DateUtils.getNowYMDStr();
        String fileName = "交易流水" + nowStr + ".xls";
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));// 设定输出文件头

        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);

        ouputStream.flush();
        ouputStream.close();
    }

    @RequestMapping(value = "/down", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:export" })
    public void down() throws IOException {
        String filePath = request.getSession().getServletContext().getRealPath("");
        filePath = filePath + "template/交易流水导入模板.xlsx";
        String fileName = "交易流水导入模板.xlsx";
        //解析excel，获取交易流水信息集合。
        ReadExcel.downTemplate(filePath, fileName, response);
    }

    /**
        * 
        * doImport:(商户数据导入)
        *
        * @return   Map<String,String>    返回Result对象
        * @throws 
        * @since  CodingExample　Ver 1.1
        */
    @RequestMapping(value = "/doImport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> doImport() {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile file = fileMap.get("excel_file_trade");
        // 判断文件是否为空
        if (file == null) {
            return null;
        }
        // 获取文件名
        String name = file.getOriginalFilename();
        // 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0) {
            return null;
        }
        // 创建处理EXCEL
        ReadExcel readExcel = new ReadExcel();
        // 解析excel，获取客户信息集合。
        List<Object[]> customerList = readExcel.getExcelInfo(name, file);
        // 获取当前登录的用户
        WebUserDTO adminUser = (WebUserDTO) getSessionUser();
        Integer userId = adminUser.getId();
        // 批量导入。参数：文件名，文件。
        ResultDTO<String> result = null;
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //文件处理
                tradeBatchImportToDB(customerList, name);
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("data", "success");
        return map;
    }

    // 批量导入交易流水
    private ResultDTO<String> tradeBatchImportToDB(List<Object[]> customerList, String fileName) {
        // 循环便利customList数组，将其中excel每一行的数据分批导入数据库
        if (customerList.size() == 0) {
            return ResultDTO.fail("没有导入数据，Excel为空");
        }
        // excel导出的空数据是“null”，赋值一个空字符串
        int timeNum = 2;
        for (Object[] objs : customerList) {
            if (objs.length < 32) {
                Object[] tempObj = new Object[32];
                System.arraycopy(objs, 0, tempObj, 0, objs.length);
                objs = tempObj;
            }
            logger.error("正在导入第"+timeNum+"条数据");
            importTradeData(objs, timeNum, fileName);
            timeNum = timeNum + 1;
        }
        return ResultDTO.success();
    }

    private void importTradeData(Object[] objs, int timeNum, String fileName) {
        // 内部商户号
        String innercode = StringUtil.valueOf(objs[0]);
        // 交易类型
        String txntype = StringUtil.valueOf(objs[1]);
        // 交易子类型
        String txnsubtype = StringUtil.valueOf(objs[2]);
        // 交易币种
        String currency = StringUtil.valueOf(objs[3]);
        // 应答码
        String respcode = StringUtil.valueOf(objs[4]);
        // 清算日期
        String settledate = StringUtil.valueOf(objs[5]);
        // 卡号
        String certifyid = StringUtil.valueOf(objs[6]);
        // 通道号
        String msgdestid = StringUtil.valueOf(objs[7]);
        // 渠道类型
        String channeltype = StringUtil.valueOf(objs[8]);
        // 交易金额
        String amt = StringUtil.valueOf(objs[9]);
        //支付方式
        String paytype = StringUtil.valueOf(objs[10]);
        //交易子类型
        String paysubtype = StringUtil.valueOf(objs[11]);
        //交易时间
        String timestamp = StringUtil.valueOf(objs[12]);
        //通道商户号
        String merid = StringUtil.valueOf(objs[13]);
        //通道终端号
        String channeltermcode = StringUtil.valueOf(objs[14]);
        //批次号
        String batchno = StringUtil.valueOf(objs[15]);
        //终端流水号
        String traceno = StringUtil.valueOf(objs[16]);
        //参考号
        String referno = StringUtil.valueOf(objs[17]);
        //授权码
        String authcode = StringUtil.valueOf(objs[18]);
        //来源
        String source = StringUtil.valueOf(objs[19]);
        //交易传送时间
        String sendtime = StringUtil.valueOf(objs[20]);
        //记录创建时间
        String createtime = StringUtil.valueOf(objs[21]);
        //支付超时时间
        String paytimeout = StringUtil.valueOf(objs[22]);
        //商品标题
        String subject = StringUtil.valueOf(objs[23]);
        //商品描述
        String body = StringUtil.valueOf(objs[24]);
        //银行卡验证信息及身份信息
        String customerinfo = StringUtil.valueOf(objs[25]);
        //持卡人IP
        String customerip = StringUtil.valueOf(objs[26]);
        //受理订单号
        String tn = StringUtil.valueOf(objs[27]);
        //应答信息
        String respmsg = StringUtil.valueOf(objs[28]);
        //交易成功时间
        String succtime = StringUtil.valueOf(objs[29]);
        //内部终端号
        String termid = StringUtil.valueOf(objs[30]);
        //支付媒介00pos机01app02台码
        String payMedium = StringUtil.valueOf(objs[31]);
        //  String innerCode = merchantCoreService.getInnerCode();
        TradeDataDTO tradeData = new TradeDataDTO();
        try {
            tradeData.setInnerCode(innercode);
            tradeData.setTxnType(txntype);
            tradeData.setTxnSubType(txnsubtype);
            tradeData.setCurrency(currency);
            tradeData.setRespCode(respcode);
            tradeData.setSettleDate(settledate);
            String type = tradeDataService.queryByCertifyId(certifyid);
            String dcType = "";
            if ("DC".equals(type)) {
                dcType = "00 境内借记卡";
            } else if ("CC".equals(type)) {
                dcType = "01 境内货记卡";
            }
            tradeData.setCardOrg(dcType);
            tradeData.setCardNo(certifyid);
            tradeData.setMsgDestId(msgdestid);
            tradeData.setChannelType(channeltype);
            tradeData.setAmt(amt);
            tradeData.setPayType(paytype);
            tradeData.setPaySubType(paysubtype);
            tradeData.setTimeStamp(timestamp);
            tradeData.setMerId(merid);
            tradeData.setChannelTermCode(channeltermcode);
            tradeData.setBatchNo(batchno);
            tradeData.setSysTraceNo(traceno);
            tradeData.setReferNo(referno);
            tradeData.setAuthCode(authcode);
            tradeData.setSource(source);
            tradeData.setSendTime(sendtime);
            tradeData.setPayTimeOut(paytimeout);
            tradeData.setSubject(subject);
            tradeData.setBody(body);
            tradeData.setCustomerInfo(customerinfo);
            tradeData.setCustomerIp(customerip);
            //tradeData.setTn(tn);
            tradeData.setRespMsg(respmsg);
            tradeData.setSuccTime(succtime);
            tradeData.setTermId(termid);
            tradeData.setPayMedium(payMedium);
            tradeData.setOrderNo(tn);
            //日期格式转换
            Date createTime = null;
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            if (!"".equals(createtime)) {
                //String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
                //createtime = createtime.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                createTime = sf.parse(createtime);
            }
            tradeData.setCreateTime(new Date());
            if (null != createTime) {
                tradeData.setOrderTime(sf.format(createTime));
            }
            //交易流水打包导入
            tradeData.setMd5(Md5Util.encrypt(JSON.toJSONString(tradeData)));
            ResultDTO result = validate(tradeData);
            if (result.isSuccess()) {
                tradeDataService.saveTradeData(tradeData);
            } else {
                logger.error("第" + timeNum + "行交易流水信息校验有误");
                ImportErrorDO importError = new ImportErrorDO();
                importError.setCreateTime(new Date());
                importError.setCreateUserId(getUserId());
                String errorMsg = result.getMessage();
                importError.setErrorMsg(errorMsg);
                String data = JSON.toJSONString(tradeData);
                importError.setData(data);
                importError.setImportFileName(fileName);
                importError.setRowNumber(timeNum);
                saveImportErrorLog(importError);
            }
        } catch (Exception e) {
            logger.error("第" + timeNum + "行交易流水信息有误，导入失败", e);
            ImportErrorDO importError = new ImportErrorDO();
            importError.setCreateTime(new Date());
            importError.setCreateUserId(getUserId());
            String errorMsg = e.getMessage();
            importError.setErrorMsg(errorMsg);
            String data = JSON.toJSONString(tradeData);
            importError.setData(data);
            importError.setImportFileName(fileName);
            importError.setRowNumber(timeNum);
            saveImportErrorLog(importError);
        }
    }
    private void saveImportErrorLog(ImportErrorDO importError){
        String errorMsg = importError.getErrorMsg();
        if (errorMsg.length() > 2000) {
            errorMsg.substring(0, 2000);
        }
        importError.setErrorMsg(errorMsg);
        String data = importError.getData();
        if (data.length() > 4000) {
            data.substring(0, 4000);
        }
        importError.setData(data);
        tradeDataImportService.saveImportError(importError);
    }
    private ResultDTO validate(TradeDataDTO tradeData) {
        String payType = tradeData.getPayType();
        //tradeData.setCardOrg(ConstantEnum.DcTypeEnum.getNameByCode(code));
        if (Strings.isNullOrEmpty(PayTypeEnum.getNameByCode(payType))) {
            return fail("支付类型必须为00刷卡01二维码");
        }
        if ("其它".equals(PaySubTypeAllEnum.getNameByCode(tradeData.getPaySubType()))) {
            return fail("支付子类型必须为00刷卡01微信支付02支付宝支付等等");
        }
        //tradeData.setPayType(ServiceConstant.PAY_TYPE_MAP.get(payType));
        //tradeData.setPaySubType(ServiceConstant.PAY_SUB_TYPE_MAP.get(payType));
        if (Strings.isNullOrEmpty(TradeStateEnum.getNameByCode(tradeData.getRespCode()))) {
            return fail("回应状态必须为1001成功1002失败1111进行中");
        }
        //tradeData.setRespCode(ServiceConstant.TradeStateEnum.SUCCESS.getCode());
        //tradeData.setPayMedium(BigdataConstant.PayMediumEnum.FIX_QR.getCode());
        if (Strings.isNullOrEmpty(PayMediumEnum.getNameByCode(tradeData.getPayMedium()))) {
            return fail("支付媒介必须为00pos机01app02台码");
        }
        if (Strings.isNullOrEmpty(DataSourceEnum.getNameByCode(tradeData.getSource()))) {
            return fail("数据来源必须为00拉卡拉01导入02同步");
        }
        if (Strings.isNullOrEmpty(ChannelTypeEnum.getNameByCode(tradeData.getChannelType()))) {
            return fail("渠道类型必须为00拉卡拉01浦发02爱农03法奈昇");
        }
        return success();
    }
    
    
    /**
     * 交易流水excel导出
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @param req
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportLkl", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:export" })
    public void exportLkl(TradeDataDTO tradeDataDTO, HttpServletRequest req, HttpServletResponse response) throws IOException {
    	if("null".equals(tradeDataDTO.getChannelType())) {
    		tradeDataDTO.setChannelType(null);
    	}
        List<TradeDataLkl> dataList = tradeDataLklService.queryDataList(tradeDataDTO);
        // 转换日期显示
        if (dataList != null) {
            for (TradeDataLkl merchantdo : dataList) {
                // 支付方式
                if ("00".equals(merchantdo.getPayType())) {
                    merchantdo.setPayType("刷卡");
                } else if ("01".equals(merchantdo.getPayType())) {
                    merchantdo.setPayType("扫码");
                }else if ("02".equals(merchantdo.getPayType())){
                	merchantdo.setPayType("分期付");
                }
                // 处理金额
                if (null != merchantdo.getAmt()) {
                    BigDecimal str = new BigDecimal(merchantdo.getAmt());
                    double resu = str.divide(new BigDecimal(100)).doubleValue();
                    DecimalFormat df = new DecimalFormat("#0.00");
                    merchantdo.setAmt(df.format(resu));
                }
                // 处理来源
                if (null != merchantdo.getSource()) {
                    if ("00".equals(merchantdo.getSource())) {
                        merchantdo.setSource("拉卡拉");
                    } else {
                        merchantdo.setSource("其他");
                    }
                }
                // 处理交易类型
                if (null != merchantdo.getTxnType()) {
                    if ("1".equals(merchantdo.getTxnType())) {
                        merchantdo.setTxnType("消费");
                    } else if ("2".equals(merchantdo.getTxnType())) {
                        merchantdo.setTxnType("撤销");
                    }
                }
                // 处理状态
                if ("0".equals(merchantdo.getStatus())) {
                    merchantdo.setStatus("非正常交易");
                } else if ("1".equals(merchantdo.getStatus())) {
                    merchantdo.setStatus("正常交易");
                }
                String li0 = merchantdo.getTimeStamp();
                String li1 = merchantdo.getOrderTime();
                Date li2 = merchantdo.getCreateTime();
                if (li0 != null) {
                    String year = li0.substring(0, 4);
                    String month = li0.substring(4, 6);
                    String day = li0.substring(6, 8);
                    String hour = li0.substring(8, 10);
                    String minutes = li0.substring(10, 12);
                    String seconds = li0.substring(12, 14);
                    String dateString0 = year + "-" + month + "-" + day + ' ' + hour + ':' + minutes + ':' + seconds;
                    merchantdo.setTimeStamp(dateString0);
                }
                if (li1 != null) {
                    String year = li1.substring(0, 4);
                    String month = li1.substring(4, 6);
                    String day = li1.substring(6, 8);
                    String hour = li1.substring(8, 10);
                    String minutes = li1.substring(10, 12);
                    String seconds = li1.substring(12, 14);
                    String dateString1 = year + "-" + month + "-" + day + ' ' + hour + ':' + minutes + ':' + seconds;
                    merchantdo.setOrderTime(dateString1);
                }
                if (li2 != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString2 = formatter.format(li2);
                    merchantdo.setCreateTimeStr(dateString2);
                }
            }
        }

        JSONObject jObject = new JSONObject();
        jObject.put("data", dataList);
        List<TradeDataLkl> list = (List<TradeDataLkl>) jObject.get("data");
        String itemMark = "orderNo,orderIdScan,merName,innerCode,snCode,referNo,txnType,merId,timeStamp,payType,amt,certifyId,orderTime,termId,batchNo,sysTraceNo,authCode,source,createTimeStr,status";
        String itemParap = "订单号,扫码交易的订单号,商户名,内部商户号,终端SN码,参考号,交易类型,结算商户号,支付时间,支付方式,交易金额(元),持卡人卡号,订单时间,终端号,批次号,凭证号,授权码,来源,创建时间,状态";

        String[] itemMarks = itemMark.split(",");// 键
        String[] itemParaps = itemParap.split(",");// 列头

        HSSFWorkbook workbook = ExcelUtils.getInputStream(itemParaps.length, itemMarks, itemParaps, list, "拉卡拉交易流水");

        response.setContentType("application/vnd.ms-excel;");
        String nowStr = DateUtils.getNowYMDStr();
        String fileName = "拉卡拉交易流水" + nowStr + ".xls";
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));// 设定输出文件头

        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);

        ouputStream.flush();
        ouputStream.close();
    }

}
