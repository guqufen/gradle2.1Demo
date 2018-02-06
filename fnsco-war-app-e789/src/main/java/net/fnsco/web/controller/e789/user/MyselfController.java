package net.fnsco.web.controller.e789.user;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CreateFileUtils;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.AppUserInfoDTO;
import net.fnsco.order.api.dto.AppUserLoginInfoDTO;
import net.fnsco.trading.service.bank.AppUserBankService;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.jo.FindPasswordJO;
import net.fnsco.web.controller.e789.jo.ModifyInfoJO;
import net.fnsco.web.controller.e789.jo.ModifyPasswordJO;
import net.fnsco.web.controller.e789.jo.ModifyPayPasswordJO;
import net.fnsco.web.controller.e789.vo.GetPersonInfoVO;
import net.fnsco.web.controller.e789.vo.LoginVO;

/**
 * @author   hjt
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月4日 上午10:04:54
 *
 */
@RestController
@RequestMapping(value = "/app2c/user", method = RequestMethod.POST)
@Api(value = "/app2c/user", tags = { "我的-App用户管理接口" })
public class MyselfController extends BaseController {
    @Autowired
    private AppUserService        appUserService;
    @Autowired
    private Environment           env;
    @Autowired
    private AppUserBankService    appUserBankService;
    //修改密码     旧密码和新密码
    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    @ApiOperation(value = "设置-修改登录密码" ,notes="作者：何金庭")
    public ResultDTO<String> modifyPassword(@RequestBody ModifyPasswordJO modifyPasswordJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setUserId(modifyPasswordJO.getUserId());
    	appUserDTO.setPassword(modifyPasswordJO.getPassword());
    	appUserDTO.setOldPassword(modifyPasswordJO.getOldPassword());
        ResultDTO<String> result = new ResultDTO<>();
        result = appUserService.modifyPassword(appUserDTO);
        return result;
    }
    
  //修改支付密码     旧密码和新密码
    @RequestMapping(value = "/modifyPayPassword")
    @ResponseBody
    @ApiOperation(value = "设置-修改支付密码" ,notes="作者：何金庭")
    public ResultDTO<String> modifyPayPassword(@RequestBody ModifyPayPasswordJO modifyPayPasswordJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setUserId(modifyPayPasswordJO.getUserId());
    	appUserDTO.setPassword(modifyPayPasswordJO.getPayPassword());
    	appUserDTO.setOldPassword(modifyPayPasswordJO.getOldPayPassword());
        return appUserService.modifyPayPassword(appUserDTO);
    }
  
    @ResponseBody
    @RequestMapping(value = "/findPayPassword")
    @ApiOperation(value = "设置-找回支付密码" ,notes="作者：何金庭")
    public ResultDTO findPayPassword(@RequestBody FindPasswordJO findPasswordJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setCode(findPasswordJO.getCode());
    	appUserDTO.setDeviceId(findPasswordJO.getDeviceId());
    	appUserDTO.setMobile(findPasswordJO.getMobile());
    	appUserDTO.setPassword(findPasswordJO.getPassword());
    	return appUserService.e789FindPayPassword(appUserDTO);
    }
    /**
     * modifyInfo:(这里用一句话描述这个方法的作用)修改个人信息
     *
     * @param appUserDTO
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/modifyInfo")
    @ApiOperation(value = "个人信息-修改个人信息" ,notes="作者：何金庭")
    public ResultDTO modifyInfo(@RequestBody ModifyInfoJO modifyInfoJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    		appUserDTO.setUserId(modifyInfoJO.getUserId());
    		appUserDTO.setUserName(modifyInfoJO.getModifyContent());
    		if (null == appUserDTO.getUserId()) {
    			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
    		}
    		if (!Strings.isNullOrEmpty(appUserDTO.getUserName()) && appUserDTO.getUserName().length() > 19) {
    			return ResultDTO.fail(ApiConstant.E_STRING_TOO_LENGTH);
    		}
    		return appUserService.modifyInfo(appUserDTO);
    }

    /**
     * uploadImage:(这里用一句话描述这个方法的作用)文件上传
     *
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/uploadImage")
    @ApiOperation(value = "个人信息-上传头像文件" ,notes="作者：何金庭")
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType="String",paramType="body"),
    		@ApiImplicitParam(name = "file", value = "图片文件流", required = true, dataType="MultipartFile",paramType="body")
    })
    public ResultDTO<String> uploadImage() {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            String userId = request.getParameter("userId");
            if (Strings.isNullOrEmpty(userId)) {
                return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
            }
         // 上传文件原名
            MultipartFile file = entity.getValue();
            String fileName = file.getOriginalFilename();
            String url = env.getProperty("headFileUpload.url");
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
                    AppUserDTO appUserDto = new AppUserDTO();
                    appUserDto.setUserId(Integer.valueOf(userId));
                    appUserDto.setHeadImagePath(newUrl);
                    appUserService.modifyInfo(appUserDto);
                    String imageUrl = OssLoaclUtil.getForeverFileUrl(OssLoaclUtil.getHeadBucketName(), fileKey);
                    Map<String, String> datas = Maps.newHashMap();
                    datas.put("headImageUrl", imageUrl);
                    return ResultDTO.success(datas);
                } catch (Exception e) {
                    logger.error(fileName + "上传失败！" + e);
                }
            } else {
                logger.error(fileName + "上传失败");
            }
        }
        return null;
    }
    
    //获取个人信息
    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    @ApiOperation(value = "个人信息页-获取个人信息" ,notes="作者：何金庭")
    public ResultDTO<GetPersonInfoVO> getPersonInfo(@RequestBody CommonJO commonJO) {
    	Integer appUserId = commonJO.getUserId();
    	if (appUserId == null) {
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
    	AppUserInfoDTO appUserInfoDTO = appUserService.getMyselfInfo(appUserId);
    	if(appUserInfoDTO==null) {
    		return ResultDTO.fail(ApiConstant.E_ACCOUNTLOCKOUT_ERROR);
    	}
        GetPersonInfoVO getPersonInfoVO = new GetPersonInfoVO();
        getPersonInfoVO.setMobile(appUserInfoDTO.getMoblie());
        getPersonInfoVO.setHeadImagePath(appUserInfoDTO.getHeadImagePath());
        getPersonInfoVO.setUserName(appUserInfoDTO.getUserName());
        getPersonInfoVO.setRealName(appUserInfoDTO.getRealName());
        if(Strings.isNullOrEmpty(appUserInfoDTO.getRealName())) {
        	getPersonInfoVO.setIsBindingIdCard(false);
        }else {
        	getPersonInfoVO.setIsBindingIdCard(true);
        }
        AppUserBankDO appUserBank = appUserBankService.QueryByAppUserId(appUserId);
        if(appUserBank==null) {
        	getPersonInfoVO.setIsBindingBankCard(false);
        	getPersonInfoVO.setIsBindingStr("未绑定");
        }else {
        	getPersonInfoVO.setIsBindingBankCard(true);
        	getPersonInfoVO.setIsBindingStr("已绑定");
        }
        return ResultDTO.success(getPersonInfoVO);
    }


}
