package net.fnsco.web.controller.merchant;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.fnsco.bigdata.service.modules.merchant.MerchantInfoImportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.ReadExcel;
import net.fnsco.freamwork.business.WebUserDTO;

@Controller
@RequestMapping(value = "/web/merchantinfoImport")
public class MerchantInfoImportController extends BaseController {

    @Autowired
    private MerchantInfoImportService merchantInfoImportService;

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
        ResultDTO<String> result = null;
        try {
            result = merchantInfoImportService.merchantBatchImportToDB(customerList, userId);
        } catch (ParseException e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        if (!result.isSuccess()) {
            Map<String, String> map = new HashMap<>();
            if (("").equals(result.getMessage())) {
                map.put("data", "批量导入EXCEL失败！");
                return map;
            }
            map.put("data", result.getMessage());
            return map;
        }
        Map<String, String> map = new HashMap<>();
        map.put("data", "success");
        return map;
    }
}