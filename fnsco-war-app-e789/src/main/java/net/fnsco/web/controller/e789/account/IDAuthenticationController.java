package net.fnsco.web.controller.e789.account;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.JuheDemoUtil;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.dto.IdCardDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.userfile.AppUserFileService;
import net.fnsco.trading.service.userfile.entity.AppUserFileDO;
import net.fnsco.web.controller.e789.jo.IdentifyJO;
import net.fnsco.web.controller.e789.vo.IdAuthVO;

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
	 @Autowired
	 private AppUserFileService    appUserFileService;
	@RequestMapping(value = "/auth")
    @ApiOperation(value = "个人信息-身份证上传识别接口" ,notes="作者：何金庭")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType="String",paramType="body"),
		@ApiImplicitParam(name = "file", value = "图片文件流", required = true, dataType="MultipartFile",paramType="body"),
		@ApiImplicitParam(name = "side", value = "front:正面识别;back:反面识别;", required = true, dataType="String",paramType="body")
	})
    public ResultDTO<IdAuthVO> idAuth() {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String side = request.getParameter("side");
        if (userId == null) {
        	return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
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
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileURL));
                    stream.write(bytes);
                    stream.close();
                    //上传阿里云OSS文件服务器
                    OssLoaclUtil.uploadFile(fileURL, fileKey);
                    String newUrl = OssLoaclUtil.getHeadBucketName() + "^" + fileKey;
                    AppUserFileDO appUserFile = new AppUserFileDO();
                    appUserFile.setFilePath(newUrl);
                    appUserFile.setCreateTime(new Date());
                    appUserFile.setAppUserId(userId);
                    appUserFile.setFileName(userId+"的"+side+"身份证");
                    appUserFile.setFileType(side);
                    appUserFile = appUserFileService.doAdd(appUserFile);
                    IdAuthVO idAuth = new IdAuthVO();
                    idAuth.setFileId(appUserFile.getId());
                    return ResultDTO.success(idAuth);
                } catch (Exception e) {
                    logger.error(fileName + "上传失败！" + e);
                    throw new RuntimeException();
                }
            } else {
                logger.error(fileName + "上传失败");
                throw new RuntimeException();
            }
            /*
                IdCardDTO idCard =new IdCardDTO();
        		IdAuthVO idAuth = new IdAuthVO();
                if("front".equals(side)) {
            	idCard = JuheDemoUtil.valiIdImage(fileURL,side);
            	int errorCode = idCard.getErrorCode();
            	if(errorCode==228701) {
           		 	return ResultDTO.fail(E789ApiConstant.E_DATA_SOURCE_TIMEOUT);
           	 	}else if(errorCode==228702) {
           	 		return ResultDTO.fail(E789ApiConstant.E_PAR_ERROR);
           	 	}else if(errorCode==228703) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IMAGE_TYPE_ERROR);
           	 	}else if(errorCode==228704) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IMAGE_LENGTH_ERROR);
           	 	}else if(errorCode==228705) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IMAGE_SIZE_ERROR);
           	 	}else if(errorCode==228706) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IDENTIFY_FAILURE);
           	 	}else if(errorCode==228707) {
           	 		return ResultDTO.fail(E789ApiConstant.E_OTHER_ERROR);
           	 	}else if(errorCode==228708) {
           	 		return ResultDTO.fail(E789ApiConstant.E_DISSUPOPORT_GET);
           	 	}
            	idAuth.setAppUserId(userId);
            	idAuth.setRealName(idCard.getRealname());
            	idAuth.setIdCard(idCard.getIdcard());
            	idAuth.setSide("front");
            	idAuth.setFileURL(fileURL);
            }else if("back".equals(side)) {
            	idCard = JuheDemoUtil.valiIdImage(fileURL,side);
            	int errorCode = idCard.getErrorCode();
            	if(errorCode==228701) {
           		 	return ResultDTO.fail(E789ApiConstant.E_DATA_SOURCE_TIMEOUT);
           	 	}else if(errorCode==228702) {
           	 		return ResultDTO.fail(E789ApiConstant.E_PAR_ERROR);
           	 	}else if(errorCode==228703) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IMAGE_TYPE_ERROR);
           	 	}else if(errorCode==228704) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IMAGE_LENGTH_ERROR);
           	 	}else if(errorCode==228705) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IMAGE_SIZE_ERROR);
           	 	}else if(errorCode==228706) {
           	 		return ResultDTO.fail(E789ApiConstant.E_IDENTIFY_FAILURE);
           	 	}else if(errorCode==228707) {
           	 		return ResultDTO.fail(E789ApiConstant.E_OTHER_ERROR);
           	 	}else if(errorCode==228708) {
           	 		return ResultDTO.fail(E789ApiConstant.E_DISSUPOPORT_GET);
           	 	}
            	idAuth.setAppUserId(userId);
            	idAuth.setSide("back");
            	idAuth.setEndTime(idCard.getEnd());
            }*/
        }
        return null;
	}
        
	@RequestMapping(value = "/identify")
    @ApiOperation(value = "个人信息-身份证认证接口" ,notes="作者：何金庭")
    public ResultDTO identify(@RequestBody IdentifyJO identify) {
		AppUserFileDO appUserFile = appUserFileService.doQueryById(identify.getFileId());
		/*String endTime = identify.getEndTime();
		Date nowTime = new Date();
		Date date =null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		try {
			date = formatter.parse(endTime);
		} catch (ParseException e) {
			logger.info("身份证时间转换错误");
			e.printStackTrace();
		} 
		IdCardDTO idCard =new IdCardDTO();
        idCard = JuheDemoUtil.valiIdCard(identify.getIdCard(),identify.getRealName());
        int errorCode = idCard.getErrorCode();
        if (date.before(nowTime)) {
        	return ResultDTO.fail(E789ApiConstant.E_IDCARD_OUT_OF_TIME);
        }
        if(errorCode==210301) {
   		 	return ResultDTO.fail(E789ApiConstant.E_NOT_FOUND_PRE);
   	 	}else if(errorCode==210302) {
   	 		return ResultDTO.fail(E789ApiConstant.E_SERVER_EXC);
   	 	}else if(errorCode==210303) {
   	 		return ResultDTO.fail(E789ApiConstant.E_SERVER_MAINTENANCE);
   	 	}else if(errorCode==210304) {
   	 		return ResultDTO.fail(E789ApiConstant.E_PAR_ERROR_ID);
   	 	}else if(errorCode==210305) {
   	 		return ResultDTO.fail(E789ApiConstant.E_NETWORK_ERROR);
   	 	}else if(errorCode==210306) {
   	 		return ResultDTO.fail(E789ApiConstant.E_DATA_SOURCE_ERROR);
   	 	}
        AppUserDTO appUserDto = new AppUserDTO();
        appUserDto.setUserId(identify.getAppUserId());
        appUserDto.setRealName(identify.getRealName());
        appUserDto.setIdCardNumber(identify.getIdCard());
        appUserService.modifyInfo(appUserDto);*/
        return ResultDTO.success();
    }
}
