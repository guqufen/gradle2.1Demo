package net.fnsco.risk.service.zhima;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpInfoGetRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpLawsuitDetailGetRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpLawsuitRecordGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpInfoGetResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpLawsuitDetailGetResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpLawsuitRecordGetResponse;

/**
 * 企业涉诉记录查询
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年11月10日 上午11:43:17
 *
 */
public class TestZhimaCreditEpScoreGet {
    //芝麻开放平台地址
    private String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId          = "300000812";
    //商户 RSA 私钥
    private String privateKey     = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMcz54w/98oJHglkeCSbNStpIq4NTCIgCo8ndYWwpHGTxh50mOnbPfCir1InQ1m15fN3z8PBIo3cxj/VEgOBjBxELV70sA1d7ir4dYJuBfxDc1XQgtzGghUgbvFFjKWL7VDeUwvAqd9oj/VMIanNlouL1NHEheYk1HuPb3s7jFQBAgMBAAECgYEAgN1XASewiIZ1Y+YWwreVIcwVnzsC8WCA9DK2mBG5j6/lDnanJUGrRCw59o9nxnUhmOr9AMnELLmRlmGkEZiQpeRXr3NCs9KME083WvLO/q5YqPbyZogiPyUDjKIMhCqR/ASOHT0LVjWWWagbwrkCmmcYizbSjjIkboMuVdhOwUECQQDipCPtc//pMTBeg8yWRjSLy5ZW6UuZoMw4BZYTfD+4400/tlEaiwxIQwqQf1hz2KHYrrcyuKA4jtHFW4WO7fvNAkEA4QHaX5klBaJNc4AUm2DltSW2Dr/2N5NABiC94EAJ5xjzCP6J79tSKqJ44kt4R+iku2plKJRpCZo4P1Y0rJENBQJBANr14ZADRfaxAx4ND5cPdKyqoDCPa+6cnzBwlTF2FMo3L+ah6XFPbSpTOt2nanlhjdud0Hg8Tu7VbGzToxEXcvECQQC5XBJusLWsB4GwhbH0MoXpjhCF5CPMsrSKl8x0Aa1mwMnt/eraOp5c2w2ktrF247NZZZPCM0i4jWCK5NRt2OyVAkB3CeNEv5aZeAjf3rPpZ20KjZzCdc/1+dNiNVCBMBf3dVZgEUG8W5mxk2t5KrchvZ5ew1AepI50rDwRzNl+1cCp";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgxK9xYXf3tjwjC33JkLBjkc6TCzYPPzLuZR0DhbFxOKuFk38u+GNs33qvFrfEISHahHUquUYlNpcKXbLI4hhVIZ84E32KHyquQ8FY9micODA6LYv3maEwZ1GCqjMADxBawUKaHqsSDkZRxLGTnd26cbw/h2CZpaisOE6Vq2GD3wIDAQAB";

    //企业涉诉记录查询
    public void testZhimaCreditEpLawsuitRecordGet() {
        ZhimaCreditEpLawsuitRecordGetRequest req = new ZhimaCreditEpLawsuitRecordGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");

        req.setTransactionId("201512100936588040000000465158");// 必要参数 
        req.setProductCode("w1010100300000000001");// 必要参数 
        req.setOrgNo("69655606-5");// 必要参数 
        req.setCompanyName("北京首钢建设集团");// 必要参数 
        req.setLawsuitType("cpws");// 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCreditEpLawsuitRecordGetResponse response = client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    //企业工商信息查询（按企业查询）
    public void testZhimaCreditEpInfoGet() {
        ZhimaCreditEpInfoGetRequest req = new ZhimaCreditEpInfoGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId("201512100936588040000000465158");// 必要参数 
        req.setProductCode("w1010100300000000004");// 必要参数 
        req.setDataType("2");// 必要参数    查询类型。1-社会信用代码；2-企业名称；3-企业注册号；4-组织机构代码。
        req.setDataValue("杭州法奈昇科技有限公司");// 必要参数 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCreditEpInfoGetResponse response = client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    // 企业涉诉详情查询
    public void testZhimaCreditEpLawsuitDetailGet() {
        ZhimaCreditEpLawsuitDetailGetRequest req = new ZhimaCreditEpLawsuitDetailGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId("201512100936588040000000465158");// 必要参数 
        req.setProductCode("w1010100300000000002");// 必要参数 
        req.setLawsuitType("cpws");// 必要参数 
        req.setLawsuitId("9134736");// 必要参数 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCreditEpLawsuitDetailGetResponse response = client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestZhimaCreditEpScoreGet result = new TestZhimaCreditEpScoreGet();
        //result.testZhimaCreditEpLawsuitRecordGet();

        result.testZhimaCreditEpInfoGet();
    }
}
