package net.fnsco.web.controller.open;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.file.OrderFileService;
import net.fnsco.car.service.file.entity.OrderFileDO;
import net.fnsco.car.service.loan.OrderLoanService;
import net.fnsco.car.service.loan.entity.OrderLoanDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.web.controller.jo.LoanJO;
import net.fnsco.web.controller.vo.LoanVO;
import net.fnsco.web.controller.vo.QueryCityVO;

@RestController
@RequestMapping(value = "/h5/loan", method = RequestMethod.POST)
@Api(value = "/h5/loan", tags = { "业务申请-贷款申请" })
public class LoanApplyController extends BaseController {

	@Autowired
	private OrderLoanService orderLoanService;
	@Autowired
	private Environment env;
	@Autowired
	private OrderFileService orderFileService;

	@RequestMapping(value = "/add")
	@ApiOperation(value = "贷款申请-添加申请")
	public ResultDTO<LoanVO> addJO(@RequestBody LoanJO jo) {
		//校验验证码
		String deviceId = "fns";
		String verCode = jo.getVerCode();
		MessageUtils mUtils = new MessageUtils();
		ResultDTO<Object> rt = mUtils.validateCode(deviceId, verCode, jo.getMobile());
		if(!rt.isSuccess()){
			return ResultDTO.fail(rt.getMessage());
		}
		
		CustomerDO customer = new CustomerDO();
		customer.setName(jo.getName());
		customer.setMobile(jo.getMobile());

		OrderLoanDO orderLoan = new OrderLoanDO();
		orderLoan.setCityId(jo.getCityId());
		orderLoan.setAmount(jo.getAmount());
		orderLoan.setSuggestCode(jo.getSuggestCode());
		orderLoan.setCarTypeId(jo.getCarTypeId());//车品牌id
		orderLoan.setCarModel(jo.getMobile());//车型号
		
		String fileIds = jo.getFileIds();//上传的图片id
		ResultDTO<Object> result = orderLoanService.addJo(orderLoan, customer,fileIds);
		if (result.isSuccess()) {
			return ResultDTO.success("提交成功");
		} else {
			return ResultDTO.fail("提交失败");
		}
	}

	
	@ResponseBody
	@RequestMapping(value = "/fileInfo/upload", produces = "text/html;charset=UTF-8")
	public String upload() {
		return commImport(request, response, true);
	}

	
	private String commImport(HttpServletRequest req, HttpServletResponse response, boolean isApp) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件原名
			MultipartFile file = entity.getValue();

			OrderFileDO fileInfo = new OrderFileDO();
			String fileType = req.getParameter("fileTypeKey");
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
					// 上传阿里云OSS文件服务器
					OssLoaclUtil.uploadFile(fileURL, fileKey);
					String newUrl = OssLoaclUtil.getHeadBucketName() + "^" + fileKey;
					fileInfo.setFilePath(newUrl);
					fileInfo.setFileType(fileType);
					fileInfo.setFileName(fileName);
					fileInfo.setCreateTime(new Date());
					ResultDTO<Integer> result = orderFileService.doAddToDB(fileInfo);
					if (result.isSuccess()) {
						ResultDTO<TreeMap<String, String>> appResult = null;

						TreeMap<String, String> paras = new TreeMap<>();
						paras.put("id", String.valueOf(result.getData()));
						paras.put("url", newUrl);
						paras.put("fileType", fileType);

						appResult = ResultDTO.success(paras);
						String json = isApp ? JSONArray.toJSONString(appResult) : JSONArray.toJSONString(paras);
						response.getWriter().write(json);
					} else {
						logger.error(fileName + "上传失败");
						throw new RuntimeException();
					}

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
}
