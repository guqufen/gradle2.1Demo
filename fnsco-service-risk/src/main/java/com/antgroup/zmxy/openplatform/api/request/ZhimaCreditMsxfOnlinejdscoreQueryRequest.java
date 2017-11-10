package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditMsxfOnlinejdscoreQueryResponse;

/**
 * ALIPAY API: zhima.credit.msxf.onlinejdscore.query request
 * 
 * @author auto create
 * @since 1.0, 2017-08-22 17:29:40
 */
public class ZhimaCreditMsxfOnlinejdscoreQueryRequest implements ZhimaRequest<ZhimaCreditMsxfOnlinejdscoreQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 特殊订单金额差异占比
	 */
	private String allFddifDivideSixaQdHourbinAmtaorder;

	/** 
	* 短时间下单金额的差异系数
	 */
	private String allFddifMinusFiveaRangeHourbinAmtaorder;

	/** 
	* 特殊时间下单金额的波动指标
	 */
	private String allFddifMinusTwoaSdHourbinAmtaorder;

	/** 
	* 特定支付方式金额指标
	 */
	private String allFdescMeanPayonlinepaymentAmtorder;

	/** 
	* 用户购买时间波动系数1
	 */
	private String allGddescMinusLoantimenowMaxDaydiff;

	/** 
	* 用户购买时间波动系数2
	 */
	private String allGddescMinusLoantimenowMinHourdiff;

	/** 
	* 用户特殊支付金额占比
	 */
	private String allGddifDivCashondeliveryallSumPayAmtorder;

	/** 
	* 用户特殊支付金额指标
	 */
	private String allGddifDivOnlinepaymentallSumPayAmtorder;

	/** 
	* 用户特殊商品差异性指标
	 */
	private String allGddifDivSportsoutdoorallNCntprdcategory;

	/** 
	* 用户特殊订单占比
	 */
	private String allGddifDivideFailallNStsCntorder;

	/** 
	* 用户特殊时间下单量指标
	 */
	private String allGddifDivideFiveeightallNHourCntorder;

	/** 
	* 用户特殊商品差异性系数
	 */
	private String allGddifDividePhonedigitalallNCntprdcategory;

	/** 
	* 用户特殊订单量指标
	 */
	private String allGddifMinusCaMaxProductCntaorder;

	/** 
	* 用户特殊订单的差异性指标
	 */
	private String allGddifMinusCaSumAorderCntproduct;

	/** 
	* 用户特殊产品之间差异系数
	 */
	private String allGddifMinusCsMedianProductCntaorder;

	/** 
	* 用户特殊订单下单金额异常指标
	 */
	private String allGddifMinusCsSkewAorderAmtaorder;

	/** 
	* 用户购买商品的波动性指标
	 */
	private String allGddifMinusSaEntropyAorderCntproduct;

	/** 
	* 用户购买商品量
	 */
	private String allGddifMinusSaSumAorderCntproduct;

	/** 
	* 用户购买固定商品的稳定性
	 */
	private String allGddifMinusSaSumProductCntaorder;

	/** 
	* 用户购买固定商品的差异
	 */
	private String allGdescMeanProductCntaorder;

	/** 
	* 用户购买特殊订单的数量
	 */
	private String allGdescMeanSorderAmtaorder;

	/** 
	* 用户特殊订单金额指标
	 */
	private String allGdescMinCorderAmtaorder;

	/** 
	* 用户下单稳定性系数
	 */
	private String allGdescMinPhoneSumamt;

	/** 
	* 用户购买稳定性指标
	 */
	private String allGdescMinRecaddrcitySumamt;

	/** 
	* 用户下单稳定性
	 */
	private String allGdescMinRecaddrprovinceAvgamt;

	/** 
	* 用户购物行为稳定性指标
	 */
	private String allGdescNormentropyPhoneCntorder;

	/** 
	* 用户特殊购买行为稳定性指标
	 */
	private String allGdescNormentropyProductCntsorder;

	/** 
	* 用户特殊订单容量指标
	 */
	private String allGdescQlSorderAmtaorder;

	/** 
	* 用户下单跨度行为指标
	 */
	private String allTsdescAmtorderdiffAmtdiffMedian;

	/** 
	* 用户下单跨度行为稳定性
	 */
	private String allTsdescAmtorderdiffAmtdiffQu;

	/** 
	* 用户下单跨度行为波动性
	 */
	private String allTsdescAmtorderdiffAmtdiffSum;

	/** 
	* 用户下单跨度特殊差异性
	 */
	private String allTsdescAmtorderdiffTimediffCv;

	/** 
	* 用户下单跨度可靠性
	 */
	private String allTsdescAmtorderdiffTimediffQfour;

	/** 
	* 用户下单金额差异稳定度
	 */
	private String allTsdescAmtorderdiffTimediffQsix;

	/** 
	* 用户下单时间稳定度
	 */
	private String allTsdescAmtorderdiffTimediffQu;

	/** 
	* 用户下单行为差异度
	 */
	private String allTsdescAmtorderdiffVamtQnine;

	/** 
	* 用户可信度指标
	 */
	private String jdauthFddescExistChannelfinanceAuth;

	/** 
	* 用户授信稳定性指标
	 */
	private String jdauthFddescExistLoginnameEqualPhone;

	/** 
	* 用户信用欺诈指标
	 */
	private String jdauthFddescMinusNowauthtimeSeconds;

	/** 
	* 用户信用稳定性指标
	 */
	private String jdbankcardDescDivideNOwnernameReceiver;

	/** 
	* 用户可信度差异
	 */
	private String jdbankcardDescNBankphoneAuthphone;

	/** 
	* 用户可信度波动系数
	 */
	private String jdbankcardDescNOwnernameReceiver;

	/** 
	* 用户稳定性支付系数
	 */
	private String jdbankcardDiffDivideNndBindphone;

	/** 
	* 用户主流支付差异
	 */
	private String jdbankcardFdescNBanknameMajorfourbanks;

	/** 
	* 用户支付信用系数
	 */
	private String jdbankcardFdescNBanknameOthers;

	/** 
	* 用户支付差异系数
	 */
	private String jdbankcardFdiffDivideAbcallCntbankname;

	/** 
	* 用户信用卡稳定性
	 */
	private String jdbankcardFdiffDivideCreditallCntcardtype;

	/** 
	* 用户支付稳定性
	 */
	private String jdbankcardFdiffDividePostallCntbankname;

	/** 
	* 用户信用指标
	 */
	private String jdbtGddescExtractCreditscoreBt;

	/** 
	* 用户信用稳定系数
	 */
	private String jdbtGddiffMinusOverdraftquotaBtAmt;

	/** 
	* 用户活动金额指标
	 */
	private String jdoneoneoneonesumGdescAmt;

	/** 
	* 用户居住稳定性指标
	 */
	private String jdreceivaddrDescNAddress;

	/** 
	* 用户收货地址差异系数
	 */
	private String jdreceivaddrDescNNaemail;

	/** 
	* 用户收货地址稳定性指标
	 */
	private String jdreceivaddrDescRateNafixphone;

	/** 
	* 用户活动金额范围系数
	 */
	private String jdsixoneeightsumGdescAmt;

	/** 
	* 用户注册差异性指标
	 */
	private String jduserFddescExistWebloginnameLogname;

	/** 
	* 用户下单时间金额总共的时间精度
	 */
	private String jduserFddescNdCompareThreenames;

	/** 
	* 用户的绑定粘性指标
	 */
	private String jduserIsbindBothqqwechat;

	/** 
	* 1年内短时间金额稳定性指标
	 */
	private String oneyFddifDivideSevenaRangeHourbinAmtaorder;

	/** 
	* 1年内短时间金额占比
	 */
	private String oneyFddifMinusOneaRangeHourbinAmtaorder;

	/** 
	* 1年内短时间订单金额稳定性指标
	 */
	private String oneyFddifMinusSixaRangeHourbinAmtaorder;

	/** 
	* 1年内特殊订单金额平均水平
	 */
	private String oneyFdescMeanPaycashondeliveryAmtorder;

	/** 
	* 1年内特殊订单金额异常指标
	 */
	private String oneyFdescSumMeaninvoicecontentAmtorder;

	/** 
	* 1年内在线支付金额占比
	 */
	private String oneyGddifDivOnlinepaymentallSumPayAmtorder;

	/** 
	* 1年内特殊订单购买能力
	 */
	private String oneyGddifMinusCaMedianAorderAmtaorder;

	/** 
	* 1年内取消订单订单金额差异性指标
	 */
	private String oneyGddifMinusCaSdAmtbinAmtaorder;

	/** 
	* 1年内订单数量总和差异
	 */
	private String oneyGddifMinusCaSumAorderCntproduct;

	/** 
	* 1年内特殊订单金额波动性指标
	 */
	private String oneyGddifMinusSaEntropyAmtbinAmtaorder;

	/** 
	* 1年内地址差异指标
	 */
	private String oneyGdescCvRecaddrcityAvgamt;

	/** 
	* 1年内特殊订单金额分段差异性指标
	 */
	private String oneyGdescNormentropyAmtbinAmtsorder;

	/** 
	* 1年内订单金额特殊时间差异性系数
	 */
	private String oneyTsdescAmtorderdiffTimediffQsix;

	/** 
	* 1年内下单时间金额波动指标
	 */
	private String oneyTsdescAmtorderdiffVamtRange;

	/** 
	* 芝麻会员在商户端的身份标识。
	 */
	private String openId;

	/** 
	* 产品码
	 */
	private String productCode;

	/** 
	* 6月内特殊时间下波动性指标
	 */
	private String sixmFdescCvHourCntorder;

	/** 
	* 6月内在线支付总金额的占比
	 */
	private String sixmGddifDivOnlinepaymentallSumPayAmtorder;

	/** 
	* 6月内电子产品类目占比
	 */
	private String sixmGddifDivPhonedigitalallNCntprdcategory;

	/** 
	* 6月内特殊下单量的占比
	 */
	private String sixmGddifDivSixmallNHourtwefourteenCntorder;

	/** 
	* 6月内短时间下单占比
	 */
	private String sixmGddifDivideFiveeightallNHourCntorder;

	/** 
	* 6月内异常商品占比
	 */
	private String sixmGddifMinusCaSumAorderCntproduct;

	/** 
	* 6月内收货地址平均下单量的差异性指标
	 */
	private String sixmGdescMinRecaddrcityAvgamt;

	/** 
	* 6月内收货地址稳定性指标
	 */
	private String sixmGdescRangeRecaddrprovinceAvgamt;

	/** 
	* 用户活动期间的下单系数
	 */
	private String springfestivalGdescQuAamt;

	/** 
	* 3个月内特殊时段购买能力
	 */
	private String threemFddifMinusSevenaQdHourbinAmtaorder;

	/** 
	* 3月内特殊用途商品占比
	 */
	private String threemGddifDivTravelrechargeallNCntprdcateg;

	/** 
	* 3月内异常订单占比
	 */
	private String threemGddifDivideFailallNStsCntorder;

	/** 
	* 3月内金额总和异常占比
	 */
	private String threemGddifDivideNullallSumPayAmtorder;

	/** 
	* 3月内特殊订单金额指标
	 */
	private String threemGdescSumSorderAmtaorder;

	/** 
	* 商户传入的业务流水号。此字段由商户生成，需确保唯一性，用于定位每一次请求，后续按此流水进行对帐。生成规则: 固定30位数字串，前17位为精确到毫秒的时间yyyyMMddhhmmssSSS，后13位为自增数字。
	 */
	private String transactionId;

	public void setAllFddifDivideSixaQdHourbinAmtaorder(String allFddifDivideSixaQdHourbinAmtaorder) {
		this.allFddifDivideSixaQdHourbinAmtaorder = allFddifDivideSixaQdHourbinAmtaorder;
	}
	public String getAllFddifDivideSixaQdHourbinAmtaorder() {
		return this.allFddifDivideSixaQdHourbinAmtaorder;
	}

	public void setAllFddifMinusFiveaRangeHourbinAmtaorder(String allFddifMinusFiveaRangeHourbinAmtaorder) {
		this.allFddifMinusFiveaRangeHourbinAmtaorder = allFddifMinusFiveaRangeHourbinAmtaorder;
	}
	public String getAllFddifMinusFiveaRangeHourbinAmtaorder() {
		return this.allFddifMinusFiveaRangeHourbinAmtaorder;
	}

	public void setAllFddifMinusTwoaSdHourbinAmtaorder(String allFddifMinusTwoaSdHourbinAmtaorder) {
		this.allFddifMinusTwoaSdHourbinAmtaorder = allFddifMinusTwoaSdHourbinAmtaorder;
	}
	public String getAllFddifMinusTwoaSdHourbinAmtaorder() {
		return this.allFddifMinusTwoaSdHourbinAmtaorder;
	}

	public void setAllFdescMeanPayonlinepaymentAmtorder(String allFdescMeanPayonlinepaymentAmtorder) {
		this.allFdescMeanPayonlinepaymentAmtorder = allFdescMeanPayonlinepaymentAmtorder;
	}
	public String getAllFdescMeanPayonlinepaymentAmtorder() {
		return this.allFdescMeanPayonlinepaymentAmtorder;
	}

	public void setAllGddescMinusLoantimenowMaxDaydiff(String allGddescMinusLoantimenowMaxDaydiff) {
		this.allGddescMinusLoantimenowMaxDaydiff = allGddescMinusLoantimenowMaxDaydiff;
	}
	public String getAllGddescMinusLoantimenowMaxDaydiff() {
		return this.allGddescMinusLoantimenowMaxDaydiff;
	}

	public void setAllGddescMinusLoantimenowMinHourdiff(String allGddescMinusLoantimenowMinHourdiff) {
		this.allGddescMinusLoantimenowMinHourdiff = allGddescMinusLoantimenowMinHourdiff;
	}
	public String getAllGddescMinusLoantimenowMinHourdiff() {
		return this.allGddescMinusLoantimenowMinHourdiff;
	}

	public void setAllGddifDivCashondeliveryallSumPayAmtorder(String allGddifDivCashondeliveryallSumPayAmtorder) {
		this.allGddifDivCashondeliveryallSumPayAmtorder = allGddifDivCashondeliveryallSumPayAmtorder;
	}
	public String getAllGddifDivCashondeliveryallSumPayAmtorder() {
		return this.allGddifDivCashondeliveryallSumPayAmtorder;
	}

	public void setAllGddifDivOnlinepaymentallSumPayAmtorder(String allGddifDivOnlinepaymentallSumPayAmtorder) {
		this.allGddifDivOnlinepaymentallSumPayAmtorder = allGddifDivOnlinepaymentallSumPayAmtorder;
	}
	public String getAllGddifDivOnlinepaymentallSumPayAmtorder() {
		return this.allGddifDivOnlinepaymentallSumPayAmtorder;
	}

	public void setAllGddifDivSportsoutdoorallNCntprdcategory(String allGddifDivSportsoutdoorallNCntprdcategory) {
		this.allGddifDivSportsoutdoorallNCntprdcategory = allGddifDivSportsoutdoorallNCntprdcategory;
	}
	public String getAllGddifDivSportsoutdoorallNCntprdcategory() {
		return this.allGddifDivSportsoutdoorallNCntprdcategory;
	}

	public void setAllGddifDivideFailallNStsCntorder(String allGddifDivideFailallNStsCntorder) {
		this.allGddifDivideFailallNStsCntorder = allGddifDivideFailallNStsCntorder;
	}
	public String getAllGddifDivideFailallNStsCntorder() {
		return this.allGddifDivideFailallNStsCntorder;
	}

	public void setAllGddifDivideFiveeightallNHourCntorder(String allGddifDivideFiveeightallNHourCntorder) {
		this.allGddifDivideFiveeightallNHourCntorder = allGddifDivideFiveeightallNHourCntorder;
	}
	public String getAllGddifDivideFiveeightallNHourCntorder() {
		return this.allGddifDivideFiveeightallNHourCntorder;
	}

	public void setAllGddifDividePhonedigitalallNCntprdcategory(String allGddifDividePhonedigitalallNCntprdcategory) {
		this.allGddifDividePhonedigitalallNCntprdcategory = allGddifDividePhonedigitalallNCntprdcategory;
	}
	public String getAllGddifDividePhonedigitalallNCntprdcategory() {
		return this.allGddifDividePhonedigitalallNCntprdcategory;
	}

	public void setAllGddifMinusCaMaxProductCntaorder(String allGddifMinusCaMaxProductCntaorder) {
		this.allGddifMinusCaMaxProductCntaorder = allGddifMinusCaMaxProductCntaorder;
	}
	public String getAllGddifMinusCaMaxProductCntaorder() {
		return this.allGddifMinusCaMaxProductCntaorder;
	}

	public void setAllGddifMinusCaSumAorderCntproduct(String allGddifMinusCaSumAorderCntproduct) {
		this.allGddifMinusCaSumAorderCntproduct = allGddifMinusCaSumAorderCntproduct;
	}
	public String getAllGddifMinusCaSumAorderCntproduct() {
		return this.allGddifMinusCaSumAorderCntproduct;
	}

	public void setAllGddifMinusCsMedianProductCntaorder(String allGddifMinusCsMedianProductCntaorder) {
		this.allGddifMinusCsMedianProductCntaorder = allGddifMinusCsMedianProductCntaorder;
	}
	public String getAllGddifMinusCsMedianProductCntaorder() {
		return this.allGddifMinusCsMedianProductCntaorder;
	}

	public void setAllGddifMinusCsSkewAorderAmtaorder(String allGddifMinusCsSkewAorderAmtaorder) {
		this.allGddifMinusCsSkewAorderAmtaorder = allGddifMinusCsSkewAorderAmtaorder;
	}
	public String getAllGddifMinusCsSkewAorderAmtaorder() {
		return this.allGddifMinusCsSkewAorderAmtaorder;
	}

	public void setAllGddifMinusSaEntropyAorderCntproduct(String allGddifMinusSaEntropyAorderCntproduct) {
		this.allGddifMinusSaEntropyAorderCntproduct = allGddifMinusSaEntropyAorderCntproduct;
	}
	public String getAllGddifMinusSaEntropyAorderCntproduct() {
		return this.allGddifMinusSaEntropyAorderCntproduct;
	}

	public void setAllGddifMinusSaSumAorderCntproduct(String allGddifMinusSaSumAorderCntproduct) {
		this.allGddifMinusSaSumAorderCntproduct = allGddifMinusSaSumAorderCntproduct;
	}
	public String getAllGddifMinusSaSumAorderCntproduct() {
		return this.allGddifMinusSaSumAorderCntproduct;
	}

	public void setAllGddifMinusSaSumProductCntaorder(String allGddifMinusSaSumProductCntaorder) {
		this.allGddifMinusSaSumProductCntaorder = allGddifMinusSaSumProductCntaorder;
	}
	public String getAllGddifMinusSaSumProductCntaorder() {
		return this.allGddifMinusSaSumProductCntaorder;
	}

	public void setAllGdescMeanProductCntaorder(String allGdescMeanProductCntaorder) {
		this.allGdescMeanProductCntaorder = allGdescMeanProductCntaorder;
	}
	public String getAllGdescMeanProductCntaorder() {
		return this.allGdescMeanProductCntaorder;
	}

	public void setAllGdescMeanSorderAmtaorder(String allGdescMeanSorderAmtaorder) {
		this.allGdescMeanSorderAmtaorder = allGdescMeanSorderAmtaorder;
	}
	public String getAllGdescMeanSorderAmtaorder() {
		return this.allGdescMeanSorderAmtaorder;
	}

	public void setAllGdescMinCorderAmtaorder(String allGdescMinCorderAmtaorder) {
		this.allGdescMinCorderAmtaorder = allGdescMinCorderAmtaorder;
	}
	public String getAllGdescMinCorderAmtaorder() {
		return this.allGdescMinCorderAmtaorder;
	}

	public void setAllGdescMinPhoneSumamt(String allGdescMinPhoneSumamt) {
		this.allGdescMinPhoneSumamt = allGdescMinPhoneSumamt;
	}
	public String getAllGdescMinPhoneSumamt() {
		return this.allGdescMinPhoneSumamt;
	}

	public void setAllGdescMinRecaddrcitySumamt(String allGdescMinRecaddrcitySumamt) {
		this.allGdescMinRecaddrcitySumamt = allGdescMinRecaddrcitySumamt;
	}
	public String getAllGdescMinRecaddrcitySumamt() {
		return this.allGdescMinRecaddrcitySumamt;
	}

	public void setAllGdescMinRecaddrprovinceAvgamt(String allGdescMinRecaddrprovinceAvgamt) {
		this.allGdescMinRecaddrprovinceAvgamt = allGdescMinRecaddrprovinceAvgamt;
	}
	public String getAllGdescMinRecaddrprovinceAvgamt() {
		return this.allGdescMinRecaddrprovinceAvgamt;
	}

	public void setAllGdescNormentropyPhoneCntorder(String allGdescNormentropyPhoneCntorder) {
		this.allGdescNormentropyPhoneCntorder = allGdescNormentropyPhoneCntorder;
	}
	public String getAllGdescNormentropyPhoneCntorder() {
		return this.allGdescNormentropyPhoneCntorder;
	}

	public void setAllGdescNormentropyProductCntsorder(String allGdescNormentropyProductCntsorder) {
		this.allGdescNormentropyProductCntsorder = allGdescNormentropyProductCntsorder;
	}
	public String getAllGdescNormentropyProductCntsorder() {
		return this.allGdescNormentropyProductCntsorder;
	}

	public void setAllGdescQlSorderAmtaorder(String allGdescQlSorderAmtaorder) {
		this.allGdescQlSorderAmtaorder = allGdescQlSorderAmtaorder;
	}
	public String getAllGdescQlSorderAmtaorder() {
		return this.allGdescQlSorderAmtaorder;
	}

	public void setAllTsdescAmtorderdiffAmtdiffMedian(String allTsdescAmtorderdiffAmtdiffMedian) {
		this.allTsdescAmtorderdiffAmtdiffMedian = allTsdescAmtorderdiffAmtdiffMedian;
	}
	public String getAllTsdescAmtorderdiffAmtdiffMedian() {
		return this.allTsdescAmtorderdiffAmtdiffMedian;
	}

	public void setAllTsdescAmtorderdiffAmtdiffQu(String allTsdescAmtorderdiffAmtdiffQu) {
		this.allTsdescAmtorderdiffAmtdiffQu = allTsdescAmtorderdiffAmtdiffQu;
	}
	public String getAllTsdescAmtorderdiffAmtdiffQu() {
		return this.allTsdescAmtorderdiffAmtdiffQu;
	}

	public void setAllTsdescAmtorderdiffAmtdiffSum(String allTsdescAmtorderdiffAmtdiffSum) {
		this.allTsdescAmtorderdiffAmtdiffSum = allTsdescAmtorderdiffAmtdiffSum;
	}
	public String getAllTsdescAmtorderdiffAmtdiffSum() {
		return this.allTsdescAmtorderdiffAmtdiffSum;
	}

	public void setAllTsdescAmtorderdiffTimediffCv(String allTsdescAmtorderdiffTimediffCv) {
		this.allTsdescAmtorderdiffTimediffCv = allTsdescAmtorderdiffTimediffCv;
	}
	public String getAllTsdescAmtorderdiffTimediffCv() {
		return this.allTsdescAmtorderdiffTimediffCv;
	}

	public void setAllTsdescAmtorderdiffTimediffQfour(String allTsdescAmtorderdiffTimediffQfour) {
		this.allTsdescAmtorderdiffTimediffQfour = allTsdescAmtorderdiffTimediffQfour;
	}
	public String getAllTsdescAmtorderdiffTimediffQfour() {
		return this.allTsdescAmtorderdiffTimediffQfour;
	}

	public void setAllTsdescAmtorderdiffTimediffQsix(String allTsdescAmtorderdiffTimediffQsix) {
		this.allTsdescAmtorderdiffTimediffQsix = allTsdescAmtorderdiffTimediffQsix;
	}
	public String getAllTsdescAmtorderdiffTimediffQsix() {
		return this.allTsdescAmtorderdiffTimediffQsix;
	}

	public void setAllTsdescAmtorderdiffTimediffQu(String allTsdescAmtorderdiffTimediffQu) {
		this.allTsdescAmtorderdiffTimediffQu = allTsdescAmtorderdiffTimediffQu;
	}
	public String getAllTsdescAmtorderdiffTimediffQu() {
		return this.allTsdescAmtorderdiffTimediffQu;
	}

	public void setAllTsdescAmtorderdiffVamtQnine(String allTsdescAmtorderdiffVamtQnine) {
		this.allTsdescAmtorderdiffVamtQnine = allTsdescAmtorderdiffVamtQnine;
	}
	public String getAllTsdescAmtorderdiffVamtQnine() {
		return this.allTsdescAmtorderdiffVamtQnine;
	}

	public void setJdauthFddescExistChannelfinanceAuth(String jdauthFddescExistChannelfinanceAuth) {
		this.jdauthFddescExistChannelfinanceAuth = jdauthFddescExistChannelfinanceAuth;
	}
	public String getJdauthFddescExistChannelfinanceAuth() {
		return this.jdauthFddescExistChannelfinanceAuth;
	}

	public void setJdauthFddescExistLoginnameEqualPhone(String jdauthFddescExistLoginnameEqualPhone) {
		this.jdauthFddescExistLoginnameEqualPhone = jdauthFddescExistLoginnameEqualPhone;
	}
	public String getJdauthFddescExistLoginnameEqualPhone() {
		return this.jdauthFddescExistLoginnameEqualPhone;
	}

	public void setJdauthFddescMinusNowauthtimeSeconds(String jdauthFddescMinusNowauthtimeSeconds) {
		this.jdauthFddescMinusNowauthtimeSeconds = jdauthFddescMinusNowauthtimeSeconds;
	}
	public String getJdauthFddescMinusNowauthtimeSeconds() {
		return this.jdauthFddescMinusNowauthtimeSeconds;
	}

	public void setJdbankcardDescDivideNOwnernameReceiver(String jdbankcardDescDivideNOwnernameReceiver) {
		this.jdbankcardDescDivideNOwnernameReceiver = jdbankcardDescDivideNOwnernameReceiver;
	}
	public String getJdbankcardDescDivideNOwnernameReceiver() {
		return this.jdbankcardDescDivideNOwnernameReceiver;
	}

	public void setJdbankcardDescNBankphoneAuthphone(String jdbankcardDescNBankphoneAuthphone) {
		this.jdbankcardDescNBankphoneAuthphone = jdbankcardDescNBankphoneAuthphone;
	}
	public String getJdbankcardDescNBankphoneAuthphone() {
		return this.jdbankcardDescNBankphoneAuthphone;
	}

	public void setJdbankcardDescNOwnernameReceiver(String jdbankcardDescNOwnernameReceiver) {
		this.jdbankcardDescNOwnernameReceiver = jdbankcardDescNOwnernameReceiver;
	}
	public String getJdbankcardDescNOwnernameReceiver() {
		return this.jdbankcardDescNOwnernameReceiver;
	}

	public void setJdbankcardDiffDivideNndBindphone(String jdbankcardDiffDivideNndBindphone) {
		this.jdbankcardDiffDivideNndBindphone = jdbankcardDiffDivideNndBindphone;
	}
	public String getJdbankcardDiffDivideNndBindphone() {
		return this.jdbankcardDiffDivideNndBindphone;
	}

	public void setJdbankcardFdescNBanknameMajorfourbanks(String jdbankcardFdescNBanknameMajorfourbanks) {
		this.jdbankcardFdescNBanknameMajorfourbanks = jdbankcardFdescNBanknameMajorfourbanks;
	}
	public String getJdbankcardFdescNBanknameMajorfourbanks() {
		return this.jdbankcardFdescNBanknameMajorfourbanks;
	}

	public void setJdbankcardFdescNBanknameOthers(String jdbankcardFdescNBanknameOthers) {
		this.jdbankcardFdescNBanknameOthers = jdbankcardFdescNBanknameOthers;
	}
	public String getJdbankcardFdescNBanknameOthers() {
		return this.jdbankcardFdescNBanknameOthers;
	}

	public void setJdbankcardFdiffDivideAbcallCntbankname(String jdbankcardFdiffDivideAbcallCntbankname) {
		this.jdbankcardFdiffDivideAbcallCntbankname = jdbankcardFdiffDivideAbcallCntbankname;
	}
	public String getJdbankcardFdiffDivideAbcallCntbankname() {
		return this.jdbankcardFdiffDivideAbcallCntbankname;
	}

	public void setJdbankcardFdiffDivideCreditallCntcardtype(String jdbankcardFdiffDivideCreditallCntcardtype) {
		this.jdbankcardFdiffDivideCreditallCntcardtype = jdbankcardFdiffDivideCreditallCntcardtype;
	}
	public String getJdbankcardFdiffDivideCreditallCntcardtype() {
		return this.jdbankcardFdiffDivideCreditallCntcardtype;
	}

	public void setJdbankcardFdiffDividePostallCntbankname(String jdbankcardFdiffDividePostallCntbankname) {
		this.jdbankcardFdiffDividePostallCntbankname = jdbankcardFdiffDividePostallCntbankname;
	}
	public String getJdbankcardFdiffDividePostallCntbankname() {
		return this.jdbankcardFdiffDividePostallCntbankname;
	}

	public void setJdbtGddescExtractCreditscoreBt(String jdbtGddescExtractCreditscoreBt) {
		this.jdbtGddescExtractCreditscoreBt = jdbtGddescExtractCreditscoreBt;
	}
	public String getJdbtGddescExtractCreditscoreBt() {
		return this.jdbtGddescExtractCreditscoreBt;
	}

	public void setJdbtGddiffMinusOverdraftquotaBtAmt(String jdbtGddiffMinusOverdraftquotaBtAmt) {
		this.jdbtGddiffMinusOverdraftquotaBtAmt = jdbtGddiffMinusOverdraftquotaBtAmt;
	}
	public String getJdbtGddiffMinusOverdraftquotaBtAmt() {
		return this.jdbtGddiffMinusOverdraftquotaBtAmt;
	}

	public void setJdoneoneoneonesumGdescAmt(String jdoneoneoneonesumGdescAmt) {
		this.jdoneoneoneonesumGdescAmt = jdoneoneoneonesumGdescAmt;
	}
	public String getJdoneoneoneonesumGdescAmt() {
		return this.jdoneoneoneonesumGdescAmt;
	}

	public void setJdreceivaddrDescNAddress(String jdreceivaddrDescNAddress) {
		this.jdreceivaddrDescNAddress = jdreceivaddrDescNAddress;
	}
	public String getJdreceivaddrDescNAddress() {
		return this.jdreceivaddrDescNAddress;
	}

	public void setJdreceivaddrDescNNaemail(String jdreceivaddrDescNNaemail) {
		this.jdreceivaddrDescNNaemail = jdreceivaddrDescNNaemail;
	}
	public String getJdreceivaddrDescNNaemail() {
		return this.jdreceivaddrDescNNaemail;
	}

	public void setJdreceivaddrDescRateNafixphone(String jdreceivaddrDescRateNafixphone) {
		this.jdreceivaddrDescRateNafixphone = jdreceivaddrDescRateNafixphone;
	}
	public String getJdreceivaddrDescRateNafixphone() {
		return this.jdreceivaddrDescRateNafixphone;
	}

	public void setJdsixoneeightsumGdescAmt(String jdsixoneeightsumGdescAmt) {
		this.jdsixoneeightsumGdescAmt = jdsixoneeightsumGdescAmt;
	}
	public String getJdsixoneeightsumGdescAmt() {
		return this.jdsixoneeightsumGdescAmt;
	}

	public void setJduserFddescExistWebloginnameLogname(String jduserFddescExistWebloginnameLogname) {
		this.jduserFddescExistWebloginnameLogname = jduserFddescExistWebloginnameLogname;
	}
	public String getJduserFddescExistWebloginnameLogname() {
		return this.jduserFddescExistWebloginnameLogname;
	}

	public void setJduserFddescNdCompareThreenames(String jduserFddescNdCompareThreenames) {
		this.jduserFddescNdCompareThreenames = jduserFddescNdCompareThreenames;
	}
	public String getJduserFddescNdCompareThreenames() {
		return this.jduserFddescNdCompareThreenames;
	}

	public void setJduserIsbindBothqqwechat(String jduserIsbindBothqqwechat) {
		this.jduserIsbindBothqqwechat = jduserIsbindBothqqwechat;
	}
	public String getJduserIsbindBothqqwechat() {
		return this.jduserIsbindBothqqwechat;
	}

	public void setOneyFddifDivideSevenaRangeHourbinAmtaorder(String oneyFddifDivideSevenaRangeHourbinAmtaorder) {
		this.oneyFddifDivideSevenaRangeHourbinAmtaorder = oneyFddifDivideSevenaRangeHourbinAmtaorder;
	}
	public String getOneyFddifDivideSevenaRangeHourbinAmtaorder() {
		return this.oneyFddifDivideSevenaRangeHourbinAmtaorder;
	}

	public void setOneyFddifMinusOneaRangeHourbinAmtaorder(String oneyFddifMinusOneaRangeHourbinAmtaorder) {
		this.oneyFddifMinusOneaRangeHourbinAmtaorder = oneyFddifMinusOneaRangeHourbinAmtaorder;
	}
	public String getOneyFddifMinusOneaRangeHourbinAmtaorder() {
		return this.oneyFddifMinusOneaRangeHourbinAmtaorder;
	}

	public void setOneyFddifMinusSixaRangeHourbinAmtaorder(String oneyFddifMinusSixaRangeHourbinAmtaorder) {
		this.oneyFddifMinusSixaRangeHourbinAmtaorder = oneyFddifMinusSixaRangeHourbinAmtaorder;
	}
	public String getOneyFddifMinusSixaRangeHourbinAmtaorder() {
		return this.oneyFddifMinusSixaRangeHourbinAmtaorder;
	}

	public void setOneyFdescMeanPaycashondeliveryAmtorder(String oneyFdescMeanPaycashondeliveryAmtorder) {
		this.oneyFdescMeanPaycashondeliveryAmtorder = oneyFdescMeanPaycashondeliveryAmtorder;
	}
	public String getOneyFdescMeanPaycashondeliveryAmtorder() {
		return this.oneyFdescMeanPaycashondeliveryAmtorder;
	}

	public void setOneyFdescSumMeaninvoicecontentAmtorder(String oneyFdescSumMeaninvoicecontentAmtorder) {
		this.oneyFdescSumMeaninvoicecontentAmtorder = oneyFdescSumMeaninvoicecontentAmtorder;
	}
	public String getOneyFdescSumMeaninvoicecontentAmtorder() {
		return this.oneyFdescSumMeaninvoicecontentAmtorder;
	}

	public void setOneyGddifDivOnlinepaymentallSumPayAmtorder(String oneyGddifDivOnlinepaymentallSumPayAmtorder) {
		this.oneyGddifDivOnlinepaymentallSumPayAmtorder = oneyGddifDivOnlinepaymentallSumPayAmtorder;
	}
	public String getOneyGddifDivOnlinepaymentallSumPayAmtorder() {
		return this.oneyGddifDivOnlinepaymentallSumPayAmtorder;
	}

	public void setOneyGddifMinusCaMedianAorderAmtaorder(String oneyGddifMinusCaMedianAorderAmtaorder) {
		this.oneyGddifMinusCaMedianAorderAmtaorder = oneyGddifMinusCaMedianAorderAmtaorder;
	}
	public String getOneyGddifMinusCaMedianAorderAmtaorder() {
		return this.oneyGddifMinusCaMedianAorderAmtaorder;
	}

	public void setOneyGddifMinusCaSdAmtbinAmtaorder(String oneyGddifMinusCaSdAmtbinAmtaorder) {
		this.oneyGddifMinusCaSdAmtbinAmtaorder = oneyGddifMinusCaSdAmtbinAmtaorder;
	}
	public String getOneyGddifMinusCaSdAmtbinAmtaorder() {
		return this.oneyGddifMinusCaSdAmtbinAmtaorder;
	}

	public void setOneyGddifMinusCaSumAorderCntproduct(String oneyGddifMinusCaSumAorderCntproduct) {
		this.oneyGddifMinusCaSumAorderCntproduct = oneyGddifMinusCaSumAorderCntproduct;
	}
	public String getOneyGddifMinusCaSumAorderCntproduct() {
		return this.oneyGddifMinusCaSumAorderCntproduct;
	}

	public void setOneyGddifMinusSaEntropyAmtbinAmtaorder(String oneyGddifMinusSaEntropyAmtbinAmtaorder) {
		this.oneyGddifMinusSaEntropyAmtbinAmtaorder = oneyGddifMinusSaEntropyAmtbinAmtaorder;
	}
	public String getOneyGddifMinusSaEntropyAmtbinAmtaorder() {
		return this.oneyGddifMinusSaEntropyAmtbinAmtaorder;
	}

	public void setOneyGdescCvRecaddrcityAvgamt(String oneyGdescCvRecaddrcityAvgamt) {
		this.oneyGdescCvRecaddrcityAvgamt = oneyGdescCvRecaddrcityAvgamt;
	}
	public String getOneyGdescCvRecaddrcityAvgamt() {
		return this.oneyGdescCvRecaddrcityAvgamt;
	}

	public void setOneyGdescNormentropyAmtbinAmtsorder(String oneyGdescNormentropyAmtbinAmtsorder) {
		this.oneyGdescNormentropyAmtbinAmtsorder = oneyGdescNormentropyAmtbinAmtsorder;
	}
	public String getOneyGdescNormentropyAmtbinAmtsorder() {
		return this.oneyGdescNormentropyAmtbinAmtsorder;
	}

	public void setOneyTsdescAmtorderdiffTimediffQsix(String oneyTsdescAmtorderdiffTimediffQsix) {
		this.oneyTsdescAmtorderdiffTimediffQsix = oneyTsdescAmtorderdiffTimediffQsix;
	}
	public String getOneyTsdescAmtorderdiffTimediffQsix() {
		return this.oneyTsdescAmtorderdiffTimediffQsix;
	}

	public void setOneyTsdescAmtorderdiffVamtRange(String oneyTsdescAmtorderdiffVamtRange) {
		this.oneyTsdescAmtorderdiffVamtRange = oneyTsdescAmtorderdiffVamtRange;
	}
	public String getOneyTsdescAmtorderdiffVamtRange() {
		return this.oneyTsdescAmtorderdiffVamtRange;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId() {
		return this.openId;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
	}

	public void setSixmFdescCvHourCntorder(String sixmFdescCvHourCntorder) {
		this.sixmFdescCvHourCntorder = sixmFdescCvHourCntorder;
	}
	public String getSixmFdescCvHourCntorder() {
		return this.sixmFdescCvHourCntorder;
	}

	public void setSixmGddifDivOnlinepaymentallSumPayAmtorder(String sixmGddifDivOnlinepaymentallSumPayAmtorder) {
		this.sixmGddifDivOnlinepaymentallSumPayAmtorder = sixmGddifDivOnlinepaymentallSumPayAmtorder;
	}
	public String getSixmGddifDivOnlinepaymentallSumPayAmtorder() {
		return this.sixmGddifDivOnlinepaymentallSumPayAmtorder;
	}

	public void setSixmGddifDivPhonedigitalallNCntprdcategory(String sixmGddifDivPhonedigitalallNCntprdcategory) {
		this.sixmGddifDivPhonedigitalallNCntprdcategory = sixmGddifDivPhonedigitalallNCntprdcategory;
	}
	public String getSixmGddifDivPhonedigitalallNCntprdcategory() {
		return this.sixmGddifDivPhonedigitalallNCntprdcategory;
	}

	public void setSixmGddifDivSixmallNHourtwefourteenCntorder(String sixmGddifDivSixmallNHourtwefourteenCntorder) {
		this.sixmGddifDivSixmallNHourtwefourteenCntorder = sixmGddifDivSixmallNHourtwefourteenCntorder;
	}
	public String getSixmGddifDivSixmallNHourtwefourteenCntorder() {
		return this.sixmGddifDivSixmallNHourtwefourteenCntorder;
	}

	public void setSixmGddifDivideFiveeightallNHourCntorder(String sixmGddifDivideFiveeightallNHourCntorder) {
		this.sixmGddifDivideFiveeightallNHourCntorder = sixmGddifDivideFiveeightallNHourCntorder;
	}
	public String getSixmGddifDivideFiveeightallNHourCntorder() {
		return this.sixmGddifDivideFiveeightallNHourCntorder;
	}

	public void setSixmGddifMinusCaSumAorderCntproduct(String sixmGddifMinusCaSumAorderCntproduct) {
		this.sixmGddifMinusCaSumAorderCntproduct = sixmGddifMinusCaSumAorderCntproduct;
	}
	public String getSixmGddifMinusCaSumAorderCntproduct() {
		return this.sixmGddifMinusCaSumAorderCntproduct;
	}

	public void setSixmGdescMinRecaddrcityAvgamt(String sixmGdescMinRecaddrcityAvgamt) {
		this.sixmGdescMinRecaddrcityAvgamt = sixmGdescMinRecaddrcityAvgamt;
	}
	public String getSixmGdescMinRecaddrcityAvgamt() {
		return this.sixmGdescMinRecaddrcityAvgamt;
	}

	public void setSixmGdescRangeRecaddrprovinceAvgamt(String sixmGdescRangeRecaddrprovinceAvgamt) {
		this.sixmGdescRangeRecaddrprovinceAvgamt = sixmGdescRangeRecaddrprovinceAvgamt;
	}
	public String getSixmGdescRangeRecaddrprovinceAvgamt() {
		return this.sixmGdescRangeRecaddrprovinceAvgamt;
	}

	public void setSpringfestivalGdescQuAamt(String springfestivalGdescQuAamt) {
		this.springfestivalGdescQuAamt = springfestivalGdescQuAamt;
	}
	public String getSpringfestivalGdescQuAamt() {
		return this.springfestivalGdescQuAamt;
	}

	public void setThreemFddifMinusSevenaQdHourbinAmtaorder(String threemFddifMinusSevenaQdHourbinAmtaorder) {
		this.threemFddifMinusSevenaQdHourbinAmtaorder = threemFddifMinusSevenaQdHourbinAmtaorder;
	}
	public String getThreemFddifMinusSevenaQdHourbinAmtaorder() {
		return this.threemFddifMinusSevenaQdHourbinAmtaorder;
	}

	public void setThreemGddifDivTravelrechargeallNCntprdcateg(String threemGddifDivTravelrechargeallNCntprdcateg) {
		this.threemGddifDivTravelrechargeallNCntprdcateg = threemGddifDivTravelrechargeallNCntprdcateg;
	}
	public String getThreemGddifDivTravelrechargeallNCntprdcateg() {
		return this.threemGddifDivTravelrechargeallNCntprdcateg;
	}

	public void setThreemGddifDivideFailallNStsCntorder(String threemGddifDivideFailallNStsCntorder) {
		this.threemGddifDivideFailallNStsCntorder = threemGddifDivideFailallNStsCntorder;
	}
	public String getThreemGddifDivideFailallNStsCntorder() {
		return this.threemGddifDivideFailallNStsCntorder;
	}

	public void setThreemGddifDivideNullallSumPayAmtorder(String threemGddifDivideNullallSumPayAmtorder) {
		this.threemGddifDivideNullallSumPayAmtorder = threemGddifDivideNullallSumPayAmtorder;
	}
	public String getThreemGddifDivideNullallSumPayAmtorder() {
		return this.threemGddifDivideNullallSumPayAmtorder;
	}

	public void setThreemGdescSumSorderAmtaorder(String threemGdescSumSorderAmtaorder) {
		this.threemGdescSumSorderAmtaorder = threemGdescSumSorderAmtaorder;
	}
	public String getThreemGdescSumSorderAmtaorder() {
		return this.threemGdescSumSorderAmtaorder;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionId() {
		return this.transactionId;
	}
	private String channel;
	private String platform;	
	private String scene;
	private String extParams;

	public String getApiVersion() {
		return this.apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public void setChannel(String channel){
		this.channel=channel;
	}

    public String getChannel(){
    	return this.channel;
    }

	public void setPlatform(String platform){
		this.platform=platform;
	}

    public String getPlatform(){
    	return this.platform;
    }
    
    public void setScene(String scene){
		this.scene=scene;
	}

    public String getScene(){
    	return this.scene;
    }
    
    public void setExtParams(String extParams){
		this.extParams=extParams;
	}

    public String getExtParams(){
    	return this.extParams;
    }
    
	public String getApiMethodName() {
		return "zhima.credit.msxf.onlinejdscore.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("all_fddif_divide_sixa_qd_hourbin_amtaorder", this.allFddifDivideSixaQdHourbinAmtaorder);
		txtParams.put("all_fddif_minus_fivea_range_hourbin_amtaorder", this.allFddifMinusFiveaRangeHourbinAmtaorder);
		txtParams.put("all_fddif_minus_twoa_sd_hourbin_amtaorder", this.allFddifMinusTwoaSdHourbinAmtaorder);
		txtParams.put("all_fdesc_mean_payonlinepayment_amtorder", this.allFdescMeanPayonlinepaymentAmtorder);
		txtParams.put("all_gddesc_minus_loantimenow_max_daydiff", this.allGddescMinusLoantimenowMaxDaydiff);
		txtParams.put("all_gddesc_minus_loantimenow_min_hourdiff", this.allGddescMinusLoantimenowMinHourdiff);
		txtParams.put("all_gddif_div_cashondeliveryall_sum_pay_amtorder", this.allGddifDivCashondeliveryallSumPayAmtorder);
		txtParams.put("all_gddif_div_onlinepaymentall_sum_pay_amtorder", this.allGddifDivOnlinepaymentallSumPayAmtorder);
		txtParams.put("all_gddif_div_sportsoutdoorall_n_cntprdcategory", this.allGddifDivSportsoutdoorallNCntprdcategory);
		txtParams.put("all_gddif_divide_failall_n_sts_cntorder", this.allGddifDivideFailallNStsCntorder);
		txtParams.put("all_gddif_divide_fiveeightall_n_hour_cntorder", this.allGddifDivideFiveeightallNHourCntorder);
		txtParams.put("all_gddif_divide_phonedigitalall_n_cntprdcategory", this.allGddifDividePhonedigitalallNCntprdcategory);
		txtParams.put("all_gddif_minus_ca_max_product_cntaorder", this.allGddifMinusCaMaxProductCntaorder);
		txtParams.put("all_gddif_minus_ca_sum_aorder_cntproduct", this.allGddifMinusCaSumAorderCntproduct);
		txtParams.put("all_gddif_minus_cs_median_product_cntaorder", this.allGddifMinusCsMedianProductCntaorder);
		txtParams.put("all_gddif_minus_cs_skew_aorder_amtaorder", this.allGddifMinusCsSkewAorderAmtaorder);
		txtParams.put("all_gddif_minus_sa_entropy_aorder_cntproduct", this.allGddifMinusSaEntropyAorderCntproduct);
		txtParams.put("all_gddif_minus_sa_sum_aorder_cntproduct", this.allGddifMinusSaSumAorderCntproduct);
		txtParams.put("all_gddif_minus_sa_sum_product_cntaorder", this.allGddifMinusSaSumProductCntaorder);
		txtParams.put("all_gdesc_mean_product_cntaorder", this.allGdescMeanProductCntaorder);
		txtParams.put("all_gdesc_mean_sorder_amtaorder", this.allGdescMeanSorderAmtaorder);
		txtParams.put("all_gdesc_min_corder_amtaorder", this.allGdescMinCorderAmtaorder);
		txtParams.put("all_gdesc_min_phone_sumamt", this.allGdescMinPhoneSumamt);
		txtParams.put("all_gdesc_min_recaddrcity_sumamt", this.allGdescMinRecaddrcitySumamt);
		txtParams.put("all_gdesc_min_recaddrprovince_avgamt", this.allGdescMinRecaddrprovinceAvgamt);
		txtParams.put("all_gdesc_normentropy_phone_cntorder", this.allGdescNormentropyPhoneCntorder);
		txtParams.put("all_gdesc_normentropy_product_cntsorder", this.allGdescNormentropyProductCntsorder);
		txtParams.put("all_gdesc_ql_sorder_amtaorder", this.allGdescQlSorderAmtaorder);
		txtParams.put("all_tsdesc_amtorderdiff_amtdiff_median", this.allTsdescAmtorderdiffAmtdiffMedian);
		txtParams.put("all_tsdesc_amtorderdiff_amtdiff_qu", this.allTsdescAmtorderdiffAmtdiffQu);
		txtParams.put("all_tsdesc_amtorderdiff_amtdiff_sum", this.allTsdescAmtorderdiffAmtdiffSum);
		txtParams.put("all_tsdesc_amtorderdiff_timediff_cv", this.allTsdescAmtorderdiffTimediffCv);
		txtParams.put("all_tsdesc_amtorderdiff_timediff_qfour", this.allTsdescAmtorderdiffTimediffQfour);
		txtParams.put("all_tsdesc_amtorderdiff_timediff_qsix", this.allTsdescAmtorderdiffTimediffQsix);
		txtParams.put("all_tsdesc_amtorderdiff_timediff_qu", this.allTsdescAmtorderdiffTimediffQu);
		txtParams.put("all_tsdesc_amtorderdiff_vamt_qnine", this.allTsdescAmtorderdiffVamtQnine);
		txtParams.put("jdauth_fddesc_exist_channelfinance_auth", this.jdauthFddescExistChannelfinanceAuth);
		txtParams.put("jdauth_fddesc_exist_loginname_equal_phone", this.jdauthFddescExistLoginnameEqualPhone);
		txtParams.put("jdauth_fddesc_minus_nowauthtime_seconds", this.jdauthFddescMinusNowauthtimeSeconds);
		txtParams.put("jdbankcard_desc_divide_n_ownername_receiver", this.jdbankcardDescDivideNOwnernameReceiver);
		txtParams.put("jdbankcard_desc_n_bankphone_authphone", this.jdbankcardDescNBankphoneAuthphone);
		txtParams.put("jdbankcard_desc_n_ownername_receiver", this.jdbankcardDescNOwnernameReceiver);
		txtParams.put("jdbankcard_diff_divide_nnd_bindphone", this.jdbankcardDiffDivideNndBindphone);
		txtParams.put("jdbankcard_fdesc_n_bankname_majorfourbanks", this.jdbankcardFdescNBanknameMajorfourbanks);
		txtParams.put("jdbankcard_fdesc_n_bankname_others", this.jdbankcardFdescNBanknameOthers);
		txtParams.put("jdbankcard_fdiff_divide_abcall_cntbankname", this.jdbankcardFdiffDivideAbcallCntbankname);
		txtParams.put("jdbankcard_fdiff_divide_creditall_cntcardtype", this.jdbankcardFdiffDivideCreditallCntcardtype);
		txtParams.put("jdbankcard_fdiff_divide_postall_cntbankname", this.jdbankcardFdiffDividePostallCntbankname);
		txtParams.put("jdbt_gddesc_extract_creditscore_bt", this.jdbtGddescExtractCreditscoreBt);
		txtParams.put("jdbt_gddiff_minus_overdraftquota_bt_amt", this.jdbtGddiffMinusOverdraftquotaBtAmt);
		txtParams.put("jdoneoneoneonesum_gdesc_amt", this.jdoneoneoneonesumGdescAmt);
		txtParams.put("jdreceivaddr_desc_n_address", this.jdreceivaddrDescNAddress);
		txtParams.put("jdreceivaddr_desc_n_naemail", this.jdreceivaddrDescNNaemail);
		txtParams.put("jdreceivaddr_desc_rate_nafixphone", this.jdreceivaddrDescRateNafixphone);
		txtParams.put("jdsixoneeightsum_gdesc_amt", this.jdsixoneeightsumGdescAmt);
		txtParams.put("jduser_fddesc_exist_webloginname_logname", this.jduserFddescExistWebloginnameLogname);
		txtParams.put("jduser_fddesc_nd_compare_threenames", this.jduserFddescNdCompareThreenames);
		txtParams.put("jduser_isbind_bothqqwechat", this.jduserIsbindBothqqwechat);
		txtParams.put("oney_fddif_divide_sevena_range_hourbin_amtaorder", this.oneyFddifDivideSevenaRangeHourbinAmtaorder);
		txtParams.put("oney_fddif_minus_onea_range_hourbin_amtaorder", this.oneyFddifMinusOneaRangeHourbinAmtaorder);
		txtParams.put("oney_fddif_minus_sixa_range_hourbin_amtaorder", this.oneyFddifMinusSixaRangeHourbinAmtaorder);
		txtParams.put("oney_fdesc_mean_paycashondelivery_amtorder", this.oneyFdescMeanPaycashondeliveryAmtorder);
		txtParams.put("oney_fdesc_sum_meaninvoicecontent_amtorder", this.oneyFdescSumMeaninvoicecontentAmtorder);
		txtParams.put("oney_gddif_div_onlinepaymentall_sum_pay_amtorder", this.oneyGddifDivOnlinepaymentallSumPayAmtorder);
		txtParams.put("oney_gddif_minus_ca_median_aorder_amtaorder", this.oneyGddifMinusCaMedianAorderAmtaorder);
		txtParams.put("oney_gddif_minus_ca_sd_amtbin_amtaorder", this.oneyGddifMinusCaSdAmtbinAmtaorder);
		txtParams.put("oney_gddif_minus_ca_sum_aorder_cntproduct", this.oneyGddifMinusCaSumAorderCntproduct);
		txtParams.put("oney_gddif_minus_sa_entropy_amtbin_amtaorder", this.oneyGddifMinusSaEntropyAmtbinAmtaorder);
		txtParams.put("oney_gdesc_cv_recaddrcity_avgamt", this.oneyGdescCvRecaddrcityAvgamt);
		txtParams.put("oney_gdesc_normentropy_amtbin_amtsorder", this.oneyGdescNormentropyAmtbinAmtsorder);
		txtParams.put("oney_tsdesc_amtorderdiff_timediff_qsix", this.oneyTsdescAmtorderdiffTimediffQsix);
		txtParams.put("oney_tsdesc_amtorderdiff_vamt_range", this.oneyTsdescAmtorderdiffVamtRange);
		txtParams.put("open_id", this.openId);
		txtParams.put("product_code", this.productCode);
		txtParams.put("sixm_fdesc_cv_hour_cntorder", this.sixmFdescCvHourCntorder);
		txtParams.put("sixm_gddif_div_onlinepaymentall_sum_pay_amtorder", this.sixmGddifDivOnlinepaymentallSumPayAmtorder);
		txtParams.put("sixm_gddif_div_phonedigitalall_n_cntprdcategory", this.sixmGddifDivPhonedigitalallNCntprdcategory);
		txtParams.put("sixm_gddif_div_sixmall_n_hourtwefourteen_cntorder", this.sixmGddifDivSixmallNHourtwefourteenCntorder);
		txtParams.put("sixm_gddif_divide_fiveeightall_n_hour_cntorder", this.sixmGddifDivideFiveeightallNHourCntorder);
		txtParams.put("sixm_gddif_minus_ca_sum_aorder_cntproduct", this.sixmGddifMinusCaSumAorderCntproduct);
		txtParams.put("sixm_gdesc_min_recaddrcity_avgamt", this.sixmGdescMinRecaddrcityAvgamt);
		txtParams.put("sixm_gdesc_range_recaddrprovince_avgamt", this.sixmGdescRangeRecaddrprovinceAvgamt);
		txtParams.put("springfestival_gdesc_qu_aamt", this.springfestivalGdescQuAamt);
		txtParams.put("threem_fddif_minus_sevena_qd_hourbin_amtaorder", this.threemFddifMinusSevenaQdHourbinAmtaorder);
		txtParams.put("threem_gddif_div_travelrechargeall_n_cntprdcateg", this.threemGddifDivTravelrechargeallNCntprdcateg);
		txtParams.put("threem_gddif_divide_failall_n_sts_cntorder", this.threemGddifDivideFailallNStsCntorder);
		txtParams.put("threem_gddif_divide_nullall_sum_pay_amtorder", this.threemGddifDivideNullallSumPayAmtorder);
		txtParams.put("threem_gdesc_sum_sorder_amtaorder", this.threemGdescSumSorderAmtaorder);
		txtParams.put("transaction_id", this.transactionId);
		if(udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new ZhimaHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Class<ZhimaCreditMsxfOnlinejdscoreQueryResponse> getResponseClass() {
		return ZhimaCreditMsxfOnlinejdscoreQueryResponse.class;
	}
}
