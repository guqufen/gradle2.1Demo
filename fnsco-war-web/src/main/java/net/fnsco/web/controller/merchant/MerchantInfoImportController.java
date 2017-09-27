package net.fnsco.web.controller.merchant;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.base.Strings;

import net.fnsco.bigdata.service.modules.merchant.MerchantInfoImportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.ReadExcel;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.order.service.sys.dao.ImportErrorDAO;
import net.fnsco.order.service.sys.dao.helper.ImportErrorMsgHelper;
import net.fnsco.order.service.sys.entity.ImportErrorDO;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/web/merchantinfoImport")
public class MerchantInfoImportController extends BaseController {

    @Autowired
    private MerchantInfoImportService merchantInfoImportService;
    @Autowired
    private ImportErrorDAO            importErrorDAO;

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
    public ResultDTO<List<ImportErrorDO>> doImport() {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile file = fileMap.get("excel_file_merchant");
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
        Date startImportTime = new Date();
        if (customerList.size() != 0) {
            // excel导出的空数据是“null”，赋值一个空字符串
            int timeNum = 1;
            for (Object[] objs : customerList) {
                timeNum = timeNum + 1;
                for (int i = 0; i < objs.length; i++) {
                    if (objs[i] == null) {
                        objs[i] = "";
                    }
                }
                try {
                    //处理单个
                    ResultDTO<String> result1 = merchantInfoImportService.merchantBatchImportToDB(objs, userId, timeNum);
                    JSONObject jsonObject = JSONObject.fromObject(result1.getData());
                    String errorMsg = jsonObject.getString("result");
                    if (!Strings.isNullOrEmpty(errorMsg) && !"null".equalsIgnoreCase(errorMsg)) {
                        saveErrorMsgToDB(new Date(), null, null, userId, timeNum, name, errorMsg, objs.toString(), null);
                    }
                } catch (ParseException e) {
                    logger.error("接口导入数据程序异常", e);
                    return ResultDTO.fail();
                }
            }
        }
        Date endImportTime = new Date();
        List<ImportErrorDO> errorDOs = importErrorDAO.selectByCondition(ImportErrorMsgHelper.createImportErrorDO(null, startImportTime, endImportTime, 1, null, 0, null, null, null));
        return ResultDTO.success(errorDOs);
    }

    /**
     * saveErrorMsgToDB:(入库错误消息信息）    设定文件
     * @author    tangliang
     * @date      2017年9月25日 下午4:35:05
     * @return void    DOM对象
     */
    private void saveErrorMsgToDB(Date createTime, Date startCreateTime, Date endCreateTime, Integer createUserId, Integer rowNumber, String importFileName, String errorMsg, String data,
                                  Exception e) {

        ImportErrorDO errorDo = ImportErrorMsgHelper.createImportErrorDO(createTime, startCreateTime, endCreateTime, createUserId, rowNumber, 0, importFileName, errorMsg, data);
        importErrorDAO.insert(errorDo);
        logger.error("第" + rowNumber + errorMsg, e);
    }
}