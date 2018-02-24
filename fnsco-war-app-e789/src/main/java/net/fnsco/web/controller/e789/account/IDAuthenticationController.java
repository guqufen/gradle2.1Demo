package net.fnsco.web.controller.e789.account;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CreateFileUtils;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.core.utils.IdCardUtil;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.dto.IdCardDTO;
import net.fnsco.core.utils.dto.IdCardFaceDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.userfile.AppUserFileService;
import net.fnsco.trading.service.userfile.entity.AppUserFileDO;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.jo.IdentifyJO;

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
	@RequestMapping(value = "/idauth")
    @ApiOperation(value = "个人信息-身份证上传识别接口" ,notes="作者：何金庭")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType="String",paramType="body"),
		@ApiImplicitParam(name = "file", value = "图片文件流", required = true, dataType="MultipartFile",paramType="body"),
		@ApiImplicitParam(name = "side", value = "front:正面识别;back:反面识别;", required = true, dataType="String",paramType="body")
	})
    public ResultDTO idauth() {
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
            String url = env.getProperty("cardFileUpload.url");
            Map<String,String>  map =  CreateFileUtils.filePath(file,fileName,url);
            String fileURL = map.get("fileURL");
            String fileKey = map.get("fileKey");
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileURL));
                    stream.write(bytes);
                    stream.close();
                    //上传阿里云OSS文件服务器
                    OssLoaclUtil.uploadFile(fileURL, fileKey);
                    String newUrl = OssLoaclUtil.getHeadBucketName() + "^" + fileKey;
                    AppUserFileDO userFile = appUserFileService.doQueryByUserIdAndSide(userId,side);
            		if(userFile!=null) {
            			String path = userFile.getFilePath();
            			String deleteFileKey = path.substring(path.indexOf("^") + 1);
            			OssLoaclUtil.deleteFile(OssLoaclUtil.getHeadBucketName(), deleteFileKey);
            			File deleteFile = new File(fileURL);  
            			deleteFile.delete();
            			appUserFileService.deleteByIdAndSide(userId, side);
            		}
                    AppUserFileDO appUserFile = new AppUserFileDO();
                    appUserFile.setFilePath(newUrl);
                    appUserFile.setCreateTime(new Date());
                    appUserFile.setAppUserId(userId);
                    appUserFile.setFileName(userId+"的"+side+"身份证");
                    appUserFile.setFileType(side);
                    appUserFileService.doAdd(appUserFile);
                    return ResultDTO.success();
                } catch (Exception e) {
                    logger.error(fileName + "上传失败！" + e);
                }
            } else {
                logger.error(fileName + "上传失败");
            }
        }
        return ResultDTO.fail(E789ApiConstant.E_UPLOAD_IDCARD_FAIL);
	}
        
	@RequestMapping(value = "/identify")
    @ApiOperation(value = "个人信息-身份证认证接口" ,notes="作者：何金庭")
    public ResultDTO identify(@RequestBody CommonJO commonJO) {
		Integer userId = commonJO.getUserId();
		if (userId == null) {
        	return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
		List<AppUserFileDO> appUserFileList  = appUserFileService.doQueryByUserId(commonJO.getUserId());
		String imageFrontPath = null;
		String imagePathBack = null;
		for(AppUserFileDO appUserFile : appUserFileList) {
			String side =  appUserFile.getFileType();
			if(side.equals("back")) {
				imagePathBack = appUserFile.getFilePath();
			}else if(side.equals("front")) {
				imageFrontPath = appUserFile.getFilePath();
			}
		}
		if(Strings.isNullOrEmpty(imageFrontPath)) {
			return ResultDTO.fail(E789ApiConstant.E_FORNT_NOT_FOUND);
		}
		if(Strings.isNullOrEmpty(imagePathBack)) {
			return ResultDTO.fail(E789ApiConstant.E_BACK_NOT_FOUND);
		}
		//配置您申请的身份证识别KEY
		final String IMAGE_APPKEY = this.env.getProperty("image.appkey");
		//配置您申请的身份证识别api_secret
		final String IMAGE_APPSECRET = this.env.getProperty("image.appsecret");
		String pathFront = imageFrontPath.substring(imageFrontPath.indexOf("^") + 1);
		String line = System.getProperty("file.separator");// 文件分割符
		String fileURLFront = this.env.getProperty("cardFileUpload.url") + line + pathFront;
		IdCardFaceDTO idCardFaceFront = new IdCardFaceDTO();
		if(FileUtils.ifExist(fileURLFront)) {
			idCardFaceFront = IdCardUtil.valiIdImage(fileURLFront,IMAGE_APPKEY,IMAGE_APPSECRET);
		}else {
			OssLoaclUtil.getFileToLocal(OssLoaclUtil.getHeadBucketName(), pathFront, fileURLFront);
			idCardFaceFront = IdCardUtil.valiIdImage(fileURLFront,IMAGE_APPKEY,IMAGE_APPSECRET);
		}
		String errorCodeFront = idCardFaceFront.getErrorMessage();
		if(!Strings.isNullOrEmpty(errorCodeFront)) {
			logger.error(errorCodeFront + "正面照识别失败！");
			if(errorCodeFront.indexOf("INVALID_IMAGE_SIZE")!=-1) {
				return ResultDTO.fail(E789ApiConstant.INVALID_IMAGE_SIZE);
			}else if(errorCodeFront.indexOf("IMAGE_FILE_TOO_LARGE")!=-1) {
				return ResultDTO.fail(E789ApiConstant.IMAGE_FILE_TOO_LARGE);
			}else if("2100033".equals(errorCodeFront)) {
				return ResultDTO.fail(E789ApiConstant.E_ID_CARD_ERROR);
			}
			return ResultDTO.fail(E789ApiConstant.E_ID_CARD_F_ERROR);
		}
		String sideFront = idCardFaceFront.getSide();
		if(!"front".equals(sideFront)) {
			return ResultDTO.fail(E789ApiConstant.IS_NO_FRONT);
		}
		String realName = null;
		String cardId = null;
		realName = idCardFaceFront.getName();
    	cardId = idCardFaceFront.getId_card_number();
		String pathBack = imagePathBack.substring(imagePathBack.indexOf("^") + 1);
		String fileURLBack = this.env.getProperty("cardFileUpload.url") + line + pathBack;
		IdCardFaceDTO idCardFaceBack = new IdCardFaceDTO();
		if(FileUtils.ifExist(fileURLBack)) {
			idCardFaceBack = IdCardUtil.valiIdImage(fileURLBack,IMAGE_APPKEY,IMAGE_APPSECRET);
		}else {
			OssLoaclUtil.getFileToLocal(OssLoaclUtil.getHeadBucketName(), pathBack, fileURLBack);
			idCardFaceBack = IdCardUtil.valiIdImage(fileURLBack,IMAGE_APPKEY,IMAGE_APPSECRET);
		}
		String errorCodeBack = idCardFaceBack.getErrorMessage();
		if(!Strings.isNullOrEmpty(errorCodeBack)) {
			logger.error(errorCodeBack + "反面照识别失败！");
			if(errorCodeBack.indexOf("INVALID_IMAGE_SIZE")!=-1) {
				return ResultDTO.fail(E789ApiConstant.INVALID_IMAGE_SIZE);
			}else if(errorCodeBack.indexOf("IMAGE_FILE_TOO_LARGE")!=-1) {
				return ResultDTO.fail(E789ApiConstant.IMAGE_FILE_TOO_LARGE);
			}else if("2100033".equals(errorCodeBack)) {
				return ResultDTO.fail(E789ApiConstant.E_ID_CARD_ERROR);
			}
			return ResultDTO.fail(E789ApiConstant.E_ID_CARD_F_ERROR);
		}
		String sideBack= idCardFaceBack.getSide();
		if(!"back".equals(sideBack)) {
			return ResultDTO.fail(E789ApiConstant.IS_NO_BACK);
		}
		String endTime = null;
		String time = null;
		time = idCardFaceBack.getValidDate();
		endTime = time.substring(time.indexOf("-")+1, time.length());
		Date nowTime = new Date();
		Date date =null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		try {
			date = formatter.parse(endTime);
		} catch (ParseException e) {
			logger.info("身份证时间转换错误");
		} 
		IdCardDTO idCard =new IdCardDTO();
		//配置您申请的身份证实名认证KEY
		final String CARD_APPKEY =this.env.getProperty("card.appkey");
		idCard = IdCardUtil.valiIdCard(cardId,realName,CARD_APPKEY);
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
        if(idCard.getRes()==2) {
        	return ResultDTO.fail(E789ApiConstant.E_DATA_SOURCE_ERROR);
        }
        AppUserDTO appUserDto = new AppUserDTO();
        appUserDto.setUserId(userId);
        appUserDto.setRealName(realName);
        appUserDto.setIdCardNumber(cardId);
        appUserService.modifyInfo(appUserDto);
        IdentifyJO identify = new IdentifyJO();
        identify.setRealName(realName);
        return ResultDTO.successForMessage(identify);
    }
}
