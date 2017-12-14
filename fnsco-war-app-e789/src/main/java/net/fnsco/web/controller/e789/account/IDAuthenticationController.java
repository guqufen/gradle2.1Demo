package net.fnsco.web.controller.e789.account;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.beust.jcommander.internal.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.JuheDemoUtil;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.dto.IdCardDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.web.controller.e789.jo.AccountBalanceJO;
import net.fnsco.web.controller.e789.vo.AccountBalanceVO;

/**
 * @desc 身份证认证相关功能控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午9:45:15
 */
@RestController
@RequestMapping(value = "/app2c/id", method = RequestMethod.POST)
@Api(value = "/app2c/id", tags = { "我的-身份证认证相关功能接口" })
public class IDAuthenticationController extends BaseController {
	 @Autowired
	 private AppUserService        appUserService;
	 @Autowired
	 private Environment           env;
	@RequestMapping(value = "/auth")
    @ApiOperation(value = "个人信息-身份证认证接口")
    public ResultDTO idAuth(@RequestBody AccountBalanceJO accountBalanceJO) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        IdCardDTO idCard =new IdCardDTO();
        Integer userId = accountBalanceJO.getUserId();
        if (userId == null) {
        	return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        	String side = entity.getKey();
            // 上传文件原名
            MultipartFile file = entity.getValue();
            String fileName = file.getOriginalFilename();
            String line = System.getProperty("file.separator");// 文件分割符
            // 保存文件的路径
            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
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
            String newFileName = userId +"-"+ System.currentTimeMillis() + "." + prefix;
            String fileKey = year + "/" + month + "/" + newFileName;
            String filepath = yearMonthPath + newFileName;

            String fileURL = this.env.getProperty("fileUpload.url") + line + filepath;
            if("front".equals(side)) {
            	idCard = JuheDemoUtil.idVerification(fileURL,side);
            	if(idCard.getErrorCode()==0) {
            		return ResultDTO.fail("证件正面扫描失败");
            	}else if(idCard.getRes()==2) {
            		return ResultDTO.fail("身份证有误，姓名与身份证号码不匹配");
            	}
            }else if("back".equals(side)) {
            	idCard = JuheDemoUtil.idVerification(fileURL,side);
            	if(idCard.getErrorCode()==0) {
            		return ResultDTO.fail("证件反面扫描失败");
            	}
            }
        }
        AppUserDTO appUserDto = new AppUserDTO();
        appUserDto.setUserId(userId);
        appUserDto.setRealName(idCard.getRealname());
        appUserDto.setIdCardNumber(idCard.getIdcard());
        appUserService.modifyInfo(appUserDto);
        return ResultDTO.success();
    }
}
