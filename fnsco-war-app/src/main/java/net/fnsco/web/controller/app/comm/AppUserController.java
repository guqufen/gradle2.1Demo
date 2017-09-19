package net.fnsco.web.controller.app.comm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.OssUtil;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.appuser.AppUserSettingService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppSettingDTO;
import net.fnsco.order.api.dto.AppUserDTO;

/**
 * @author   zhoujincheng
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年6月28日 下午3:44:54
 *
 */
@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)
public class AppUserController extends BaseController {
    @Autowired
    private AppUserService        appUserService;
    @Autowired
    private AppUserSettingService appUserSettingService;
    @Autowired
    private Environment           env;
    @Autowired
    private MerchantService       merchantService;

    @RequestMapping(value = "/register")
    @ApiOperation(value = "用户注册")
    @ResponseBody
    public ResultDTO register(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO result = appUserService.insertSelective(appUserDTO);
        return result;
    }

    //获取验证码
    @ResponseBody
    @RequestMapping(value = "/getValidateCode")
    @ApiOperation(value = "获取验证码")
    public ResultDTO getValidateCode(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO result = appUserService.getValidateCode(appUserDTO);
        return result;
    }

    //修改密码     旧密码和新密码
    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    @ApiOperation(value = "修改密码")
    public ResultDTO<String> modifyPassword(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO<String> result = new ResultDTO<>();
        result = appUserService.modifyPassword(appUserDTO);
        return result;
    }

    //根据手机号码找回密码
    @ResponseBody
    @RequestMapping(value = "/findPasswordByPhone")
    @ApiOperation(value = "找回密码")
    public ResultDTO<String> findPasswordByPhone(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO<String> result = appUserService.findPassword(appUserDTO);
        return result;
    }

    //登录
    @ResponseBody
    @RequestMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public ResultDTO<String> login(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO<String> result = appUserService.loginByMoblie(appUserDTO);
        return result;
    }

    //退出登录
    @ResponseBody
    @RequestMapping(value = "/loginOut")
    @ApiOperation(value = "退出登录")
    public ResultDTO<String> loginOut(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO<String> result = appUserService.loginOut(appUserDTO);
        return result;
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
    @ApiOperation(value = "修改个人信息")
    public ResultDTO modifyInfo(@RequestBody AppUserDTO appUserDTO) {
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
    @ApiOperation(value = "上传头像文件")
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
            String newFileName = System.currentTimeMillis() + "." + prefix;
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
                    String newUrl = OssUtil.getHeadBucketName() + "^" + fileKey;
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
                    throw new RuntimeException();
                }
            } else {
                logger.error(fileName + "上传失败");
                throw new RuntimeException();
            }
        }
        return null;
    }

    //获取个人信息
    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    @ApiOperation(value = "获取个人信息")
    public ResultDTO<String> getPersonInfo(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO<String> result = appUserService.getUserInfo(appUserDTO);
        return result;
    }

    /**
     * updateSettingStatus:(更新app用户消息通知状态)
     * @param appSettingDTO
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月12日 下午4:41:21
     * @return ResultDTO<String>    DOM对象
     */
    @RequestMapping(value = "/updateSettingStatus")
    @ApiOperation(value = "更新消息通知设置状态")
    public ResultDTO<String> updateSettingStatus(@RequestBody AppSettingDTO appSettingDTO) {
        if (null == appSettingDTO.getUserId()) {
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        if (Strings.isNullOrEmpty(appSettingDTO.getNoticeType()) || Strings.isNullOrEmpty(appSettingDTO.getOpenStatus())) {
            return ResultDTO.fail(ApiConstant.E_SETTING_STATUS_NULL);
        }
        int result = appUserSettingService.updateByPrimaryKeySelective(appSettingDTO);
        if (result > 0) {
            return ResultDTO.success();
        }
        return ResultDTO.fail();
    }

    @RequestMapping(value = "/isOpenFixedQr")
    @ApiOperation(value = "该用户绑定的所有商户是否开通台码功能")
    public ResultDTO isOpenFixedQr(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO<List<MerChantCoreDTO>> result = merchantService.getMerchantsCoreByUserId(appUserDTO.getUserId());
        List<MerChantCoreDTO> resultList = result.getData();
        if (!CollectionUtils.isEmpty(resultList)) {
            return ResultDTO.success("true");
        }
        return ResultDTO.success("false");
    }

}
