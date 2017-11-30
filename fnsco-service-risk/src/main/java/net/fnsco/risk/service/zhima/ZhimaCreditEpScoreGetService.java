package net.fnsco.risk.service.zhima;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.domain.CompanyInfo;
import com.antgroup.zmxy.openplatform.api.domain.EpElement;
import com.antgroup.zmxy.openplatform.api.domain.EpInfo;
import com.antgroup.zmxy.openplatform.api.domain.LawsuitRecord;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpInfoGetRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpLawsuitRecordGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpInfoGetResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpLawsuitRecordGetResponse;

import net.fnsco.core.base.BaseService;
import net.fnsco.risk.service.zhimainfo.DataAccessThirdService;
import net.fnsco.risk.service.zhimainfo.DataCompanyAlterService;
import net.fnsco.risk.service.zhimainfo.DataCompanyBasicInfoService;
import net.fnsco.risk.service.zhimainfo.DataCompanyCaseInfoService;
import net.fnsco.risk.service.zhimainfo.DataCompanyEntinvService;
import net.fnsco.risk.service.zhimainfo.DataCompanyFrPositionService;
import net.fnsco.risk.service.zhimainfo.DataCompanyFrinvService;
import net.fnsco.risk.service.zhimainfo.DataCompanyPersonService;
import net.fnsco.risk.service.zhimainfo.DataCompanyShareHolderService;
import net.fnsco.risk.service.zhimainfo.DataLawsuitService;
import net.fnsco.risk.service.zhimainfo.entity.DataAccessThirdDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyAlterDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyBasicInfoDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyCaseInfoDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyEntinvDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrPositionDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrinvDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyPersonDO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyShareHolderDO;
import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDO;

/**
 * @desc 芝麻信用业务处理类
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年11月30日 上午11:38:53
 */
@Service
public class ZhimaCreditEpScoreGetService extends BaseService {

	@Autowired
	private DataAccessThirdService dataAccessThirdService;
	@Autowired
	private DataLawsuitService dataLawsuitService;
	@Autowired
	private DataCompanyBasicInfoService dataCompanyBasicInfoService;
	@Autowired
	private DataCompanyAlterService  dataCompanyAlterService;
	@Autowired
	private DataCompanyFrPositionService  dataCompanyFrPositionService;
	@Autowired
	private DataCompanyFrinvService  dataCompanyFrinvService;
	@Autowired
	private DataCompanyPersonService  dataCompanyPersonService;
	@Autowired
	private DataCompanyShareHolderService  dataCompanyShareHolderService;
	@Autowired
	private DataCompanyEntinvService  dataCompanyEntinvService;
	@Autowired
	private DataCompanyCaseInfoService  dataCompanyCaseInfoService;

	// 芝麻开放平台地址
	private String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
	// 商户应用 Id
	private String appId = "300001072";
	// 商户 RSA 私钥
	private String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMcz54w/98oJHglkeCSbNStpIq4NTCIgCo8ndYWwpHGTxh50mOnbPfCir1InQ1m15fN3z8PBIo3cxj/VEgOBjBxELV70sA1d7ir4dYJuBfxDc1XQgtzGghUgbvFFjKWL7VDeUwvAqd9oj/VMIanNlouL1NHEheYk1HuPb3s7jFQBAgMBAAECgYEAgN1XASewiIZ1Y+YWwreVIcwVnzsC8WCA9DK2mBG5j6/lDnanJUGrRCw59o9nxnUhmOr9AMnELLmRlmGkEZiQpeRXr3NCs9KME083WvLO/q5YqPbyZogiPyUDjKIMhCqR/ASOHT0LVjWWWagbwrkCmmcYizbSjjIkboMuVdhOwUECQQDipCPtc//pMTBeg8yWRjSLy5ZW6UuZoMw4BZYTfD+4400/tlEaiwxIQwqQf1hz2KHYrrcyuKA4jtHFW4WO7fvNAkEA4QHaX5klBaJNc4AUm2DltSW2Dr/2N5NABiC94EAJ5xjzCP6J79tSKqJ44kt4R+iku2plKJRpCZo4P1Y0rJENBQJBANr14ZADRfaxAx4ND5cPdKyqoDCPa+6cnzBwlTF2FMo3L+ah6XFPbSpTOt2nanlhjdud0Hg8Tu7VbGzToxEXcvECQQC5XBJusLWsB4GwhbH0MoXpjhCF5CPMsrSKl8x0Aa1mwMnt/eraOp5c2w2ktrF247NZZZPCM0i4jWCK5NRt2OyVAkB3CeNEv5aZeAjf3rPpZ20KjZzCdc/1+dNiNVCBMBf3dVZgEUG8W5mxk2t5KrchvZ5ew1AepI50rDwRzNl+1cCp";
	// 芝麻 RSA 公钥
	private String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNB5kuy+dg9lIWIgFIm2HXlovwBtFNHf9EUJ0w6lw8YnKZof/D9a8DUo7AxYyOr22DSMgl0mPSgDwC1WRnURKplRXVB2JmRvH1PHpwEu0law2wCxiPeLC2M1gxYGozioHKfgD8dV7Y+CTN2mHzKRIz+NGoHiaywwHmszmXlTGtGwIDAQAB";

	/**
	 * ZhimaCreditEpLawsuitRecordGet:(企业涉诉记录查询)
	 *
	 * @param @param
	 *            transactionId
	 * @param @param
	 *            companyName 设定文件
	 * @return void DOM对象
	 * @author tangliang
	 * @date 2017年11月30日 上午11:45:32
	 */
	public void zhimaCreditEpLawsuitRecordGet(String transactionId, String companyName, String innerCode) {
		ZhimaCreditEpLawsuitRecordGetRequest req = new ZhimaCreditEpLawsuitRecordGetRequest();
		req.setChannel("apppc");
		req.setPlatform("zmop");
		req.setTransactionId(transactionId);// 必要参数
		req.setProductCode("w1010100300000000001");// 必要参数
		req.setOrgNo("");// 69655606-5");// 必要参数
		req.setCompanyName(companyName);// 必要参数
		req.setLawsuitType("cpws");//
		DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
		try {
			ZhimaCreditEpLawsuitRecordGetResponse response = client.execute(req);
			if (response.isSuccess()) {
				LawsuitRecord lawsuitRecord = response.getLawsuitRecord();
				List<EpInfo> cpwsList = lawsuitRecord.getCpwsList();
				for (EpInfo ep : cpwsList) {
					List<EpElement> epeList = ep.getEpElementList();
					for (EpElement epe : epeList) {
						DataLawsuitDO dataLawsuit = new DataLawsuitDO();
						dataLawsuit.setCreateTime(new Date());
						dataLawsuit.setInnerCode(innerCode);
						dataLawsuit.setSource("0");
						dataLawsuit.setType("cpws");
						dataLawsuit.setKey(epe.getKey());
						dataLawsuit.setValue(epe.getValue());
						dataLawsuitService.doAdd(dataLawsuit, 0);
					}
				}
				saveAccessThirdToDB(response.getBizNo(),0);
				
			} else {
				logger.info(companyName + ":" + response.isSuccess() + ";" + response.getErrorCode() + ";"
						+ response.getErrorMessage());
			}
		} catch (ZhimaApiException e) {
			logger.error(companyName + " 信用企业涉诉记录查询异常", e);
		}
	}

	/**
	 * zhimaCreditEpInfoGet:(企业工商信息查询（按企业查询）)
	 *
	 * @param @param
	 *            transactionId
	 * @param @param
	 *            companyName 设定文件
	 * @return void DOM对象
	 * @author tangliang
	 * @date 2017年11月30日 上午11:51:28
	 */
	public void zhimaCreditEpInfoGet(String transactionId, String companyName,String innerCode) {
		ZhimaCreditEpInfoGetRequest req = new ZhimaCreditEpInfoGetRequest();
		req.setChannel("apppc");
		req.setPlatform("zmop");
		req.setTransactionId(transactionId);// 必要参数
		req.setProductCode("w1010100300000000004");// 必要参数
		req.setDataType("2");// 必要参数 查询类型。1-社会信用代码；2-企业名称；3-企业注册号；4-组织机构代码。
		req.setDataValue(companyName);// 必要参数
		DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
		try {
			ZhimaCreditEpInfoGetResponse response = client.execute(req);

			if (response.isSuccess()) {
				logger.info(companyName + " 工商信息查询结果:" + JSON.toJSONString(response.getCompanyInfo()));
				Date nowTime = new Date();
				CompanyInfo companyInfo = response.getCompanyInfo();
				
				/**
				 * 企业历史变更信息
				 */
				List<EpInfo> epInfoList = companyInfo.getAlterList();
				for (EpInfo epInfo : epInfoList) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyAlterDO dataCompanyAlter = new DataCompanyAlterDO();
						dataCompanyAlter.setCreateTime(nowTime);
						dataCompanyAlter.setInnerCode(innerCode);
						dataCompanyAlter.setSource("0");
						dataCompanyAlter.setType("cpws");
						dataCompanyAlter.setKey(element.getKey());
						dataCompanyAlter.setValue(element.getValue());
						dataCompanyAlterService.doAdd(dataCompanyAlter, 0);
					}
				}
				/**
				 * 企业基本信息
				 */
				EpInfo baseEpInfo = companyInfo.getBasicInfo();
				List<EpElement> baseEpList = baseEpInfo.getEpElementList();
				for (EpElement element : baseEpList) {
					DataCompanyBasicInfoDO dataCompanyBasicInfo = new DataCompanyBasicInfoDO();
					dataCompanyBasicInfo.setCreateTime(nowTime);
					dataCompanyBasicInfo.setInnerCode(innerCode);
					dataCompanyBasicInfo.setSource("0");
					dataCompanyBasicInfo.setType("cpws");
					dataCompanyBasicInfo.setKey(element.getKey());
					dataCompanyBasicInfo.setValue(element.getValue());
					dataCompanyBasicInfoService.doAdd(dataCompanyBasicInfo, 0);
				}
				
				/**
				 * 企业法定代表人其他公司任职信息
				 */
				List<EpInfo> frPositionEpInfo = companyInfo.getFrPositionList();
				for (EpInfo epInfo : frPositionEpInfo) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyFrPositionDO dataCompanyFrPosition = new DataCompanyFrPositionDO();
						dataCompanyFrPosition.setCreateTime(nowTime);
						dataCompanyFrPosition.setInnerCode(innerCode);
						dataCompanyFrPosition.setSource("0");
						dataCompanyFrPosition.setType("cpws");
						dataCompanyFrPosition.setKey(element.getKey());
						dataCompanyFrPosition.setValue(element.getValue());
						dataCompanyFrPositionService.doAdd(dataCompanyFrPosition, 0);
					}
				}
				
				/**
				 * 企业法定代表人对外投资信息
				 */
				List<EpInfo> frinvEpInfo = companyInfo.getFrinvList();
				for (EpInfo epInfo : frinvEpInfo) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyFrinvDO dataCompanyFrinvDO = new DataCompanyFrinvDO();
						dataCompanyFrinvDO.setCreateTime(nowTime);
						dataCompanyFrinvDO.setInnerCode(innerCode);
						dataCompanyFrinvDO.setSource("0");
						dataCompanyFrinvDO.setType("cpws");
						dataCompanyFrinvDO.setKey(element.getKey());
						dataCompanyFrinvDO.setValue(element.getValue());
						dataCompanyFrinvService.doAdd(dataCompanyFrinvDO, 0);
					}
				}
				
				/**
				 * 企业主要管理人员信息
				 */
				List<EpInfo> personEpInfo = companyInfo.getPersonList();
				for (EpInfo epInfo : personEpInfo) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyPersonDO dataCompanyPerson = new DataCompanyPersonDO();
						dataCompanyPerson.setCreateTime(nowTime);
						dataCompanyPerson.setInnerCode(innerCode);
						dataCompanyPerson.setSource("0");
						dataCompanyPerson.setType("cpws");
						dataCompanyPerson.setKey(element.getKey());
						dataCompanyPerson.setValue(element.getValue());
						dataCompanyPersonService.doAdd(dataCompanyPerson, 0);
					}
				}
				/**
				 * 企业股东及出资信息
				 */
				List<EpInfo> shareHolderEpInfo = companyInfo.getShareHolderList();
				for (EpInfo epInfo : shareHolderEpInfo) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyShareHolderDO dataCompanyShareHolder = new DataCompanyShareHolderDO();
						dataCompanyShareHolder.setCreateTime(nowTime);
						dataCompanyShareHolder.setInnerCode(innerCode);
						dataCompanyShareHolder.setSource("0");
						dataCompanyShareHolder.setType("cpws");
						dataCompanyShareHolder.setKey(element.getKey());
						dataCompanyShareHolder.setValue(element.getValue());
						dataCompanyShareHolderService.doAdd(dataCompanyShareHolder, 0);
					}
				}
				
				/**
				 * 企业对外投资信息
				 */
				List<EpInfo> entinvEpInfo = companyInfo.getEntinvList();
				for (EpInfo epInfo : entinvEpInfo) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyEntinvDO dataCompanyEntinv = new DataCompanyEntinvDO();
						dataCompanyEntinv.setCreateTime(nowTime);
						dataCompanyEntinv.setInnerCode(innerCode);
						dataCompanyEntinv.setSource("0");
						dataCompanyEntinv.setType("cpws");
						dataCompanyEntinv.setKey(element.getKey());
						dataCompanyEntinv.setValue(element.getValue());
						dataCompanyEntinvService.doAdd(dataCompanyEntinv, 0);
					}
				}
				
				/**
				 * 行政处罚历史信息
				 */
				List<EpInfo> caseInfoEpInfo = companyInfo.getCaseInfoList();
				for (EpInfo epInfo : caseInfoEpInfo) {
					List<EpElement> epList = epInfo.getEpElementList();
					for (EpElement element : epList) {
						DataCompanyCaseInfoDO dataCompanyCaseInfo = new DataCompanyCaseInfoDO();
						dataCompanyCaseInfo.setCreateTime(nowTime);
						dataCompanyCaseInfo.setInnerCode(innerCode);
						dataCompanyCaseInfo.setSource("0");
						dataCompanyCaseInfo.setType("cpws");
						dataCompanyCaseInfo.setKey(element.getKey());
						dataCompanyCaseInfo.setValue(element.getValue());
						dataCompanyCaseInfoService.doAdd(dataCompanyCaseInfo, 0);
					}
				}
				
				saveAccessThirdToDB(response.getBizNo(),0);
				
			} else {
				logger.info(companyName + " 工商信息查询结果:" + response.isSuccess() + ";" + response.getErrorCode() + ";"
						+ response.getErrorMessage());
			}
			
		} catch (ZhimaApiException e) {
			logger.error(companyName + " 企业工商信息查询（按企业查询）异常", e);
		}
	}
	
	/**
	 * saveAccessThirdToDB:(入库DataAccessThirdDO实体)
	 *
	 * @param  @param bizNo
	 * @param  @param type    设定文件
	 * @return void    DOM对象
	 * @author tangliang
	 * @date   2017年11月30日 下午3:19:10
	 */
	private void saveAccessThirdToDB(String bizNo,Integer type) {
		DataAccessThirdDO dataAccessThirdDO = new DataAccessThirdDO();
		dataAccessThirdDO.setCreateTime(new Date());
		dataAccessThirdDO.setChannel(0);
		dataAccessThirdDO.setBizNo(bizNo);
		 dataAccessThirdDO.setKey(null);
		dataAccessThirdDO.setType(type);
		dataAccessThirdService.doAdd(dataAccessThirdDO, 0);
	}
}
