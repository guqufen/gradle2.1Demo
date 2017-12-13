package net.fnsco.web.controller.open;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import ch.qos.logback.core.CoreConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.comm.CarConstant;
import net.fnsco.car.comm.CarServiceConstant;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.file.OrderFileService;
import net.fnsco.car.service.file.entity.OrderFileDO;
import net.fnsco.car.service.loan.OrderLoanService;
import net.fnsco.car.service.loan.entity.OrderLoanDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.MessageUtils;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.dto.MessageValidateDTO;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.web.controller.jo.LoanJO;
import net.fnsco.web.controller.jo.LoanJO2;
import net.fnsco.web.controller.vo.LoanVO;

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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "贷款申请-添加申请")
	public ResultDTO<LoanVO> addJO(@RequestBody LoanJO jo) {
		// 校验验证码
		String code = jo.getVerCode();
		String mobile = jo.getMobile();
		// 获取session中验证码信息
		MessageValidateDTO mDTO = (MessageValidateDTO) session.getAttribute(mobile);
		
		// 校验验证码是否正确
		MessageUtils utils = new MessageUtils();
		ResultDTO<Object> rt = utils.validateCode2(code, mobile, mDTO);
		if (!rt.isSuccess()) {
			return ResultDTO.fail(rt.getMessage());
		}

		CustomerDO customer = new CustomerDO();
		customer.setName(jo.getName());
		customer.setMobile(jo.getMobile());

		OrderLoanDO orderLoan = new OrderLoanDO();
		orderLoan.setCityId(jo.getCityId());
		orderLoan.setAmount(jo.getAmount());
		orderLoan.setSuggestCode(jo.getSuggestCode());

//		String fileIds = jo.getFileIds();// 上传的图片id
		ResultDTO<Object> result = orderLoanService.addJo(orderLoan, customer);
		LoanVO loanVO = new LoanVO();
		Integer orderId = (Integer)result.getData();
		loanVO.setOrderNo(orderId);
		if (result.isSuccess()) {
			return ResultDTO.success(loanVO);
		} else {
			return ResultDTO.fail("提交失败");
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "贷款申请-更新信息")
	public ResultDTO<Object> updateLoanJO(@RequestBody LoanJO2 jo){
		if(jo.getOrderId() == null){
			return ResultDTO.failForMessage(CarServiceConstant.anErrorMap.get("0001"));
		}
		OrderLoanDO orderLoan = new OrderLoanDO();
		orderLoan.setId(jo.getOrderId());
		orderLoan.setCarTypeId(jo.getCarTypeId());
		orderLoan.setCarSubTypeId(jo.getCarSubTypeId());
		orderLoan.setLastUpdateTime(new Date());
		orderLoanService.doUpdate(orderLoan, 0);
		return ResultDTO.success();
	}

	@ResponseBody
	@RequestMapping(value = "/fileInfo/upload", produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "上传图片")
	public ResultDTO<Object> upload( MultipartFile importFile) {
		return commImport(request, response, true);
	}

	private ResultDTO<Object> commImport(HttpServletRequest req, HttpServletResponse response, boolean isApp) {
		String orderId = request.getParameter("orderNo");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件原名
			MultipartFile file = entity.getValue();
			String file_type = entity.getKey();
			if(StringUtils.equals("xs", file_type)){
				file_type = "0";
			}else if(StringUtils.equals("dj", file_type)){
				file_type = "1";
			}else if(StringUtils.equals("cl", file_type)){
				file_type = "2";
			}
			OrderFileDO fileInfo = new OrderFileDO();
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
					fileInfo.setFileType(file_type);
					fileInfo.setFileName(fileName);
					fileInfo.setOrderNo(orderId);
					fileInfo.setCreateTime(new Date());
					ResultDTO<Integer> result = orderFileService.doAddToDB(fileInfo);
					if (result.isSuccess()) {
						return ResultDTO.success();
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
