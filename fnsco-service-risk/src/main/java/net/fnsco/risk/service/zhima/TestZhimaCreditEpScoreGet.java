package net.fnsco.risk.service.zhima;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpScoreGetResponse;

public class TestZhimaCreditEpScoreGet {
    //芝麻开放平台地址
    private String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId          = "300000812";
    //商户 RSA 私钥
    private String privateKey     = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMcz54w/98oJHglkeCSbNStpIq4NTCIgCo8ndYWwpHGTxh50mOnbPfCir1InQ1m15fN3z8PBIo3cxj/VEgOBjBxELV70sA1d7ir4dYJuBfxDc1XQgtzGghUgbvFFjKWL7VDeUwvAqd9oj/VMIanNlouL1NHEheYk1HuPb3s7jFQBAgMBAAECgYEAgN1XASewiIZ1Y+YWwreVIcwVnzsC8WCA9DK2mBG5j6/lDnanJUGrRCw59o9nxnUhmOr9AMnELLmRlmGkEZiQpeRXr3NCs9KME083WvLO/q5YqPbyZogiPyUDjKIMhCqR/ASOHT0LVjWWWagbwrkCmmcYizbSjjIkboMuVdhOwUECQQDipCPtc//pMTBeg8yWRjSLy5ZW6UuZoMw4BZYTfD+4400/tlEaiwxIQwqQf1hz2KHYrrcyuKA4jtHFW4WO7fvNAkEA4QHaX5klBaJNc4AUm2DltSW2Dr/2N5NABiC94EAJ5xjzCP6J79tSKqJ44kt4R+iku2plKJRpCZo4P1Y0rJENBQJBANr14ZADRfaxAx4ND5cPdKyqoDCPa+6cnzBwlTF2FMo3L+ah6XFPbSpTOt2nanlhjdud0Hg8Tu7VbGzToxEXcvECQQC5XBJusLWsB4GwhbH0MoXpjhCF5CPMsrSKl8x0Aa1mwMnt/eraOp5c2w2ktrF247NZZZPCM0i4jWCK5NRt2OyVAkB3CeNEv5aZeAjf3rPpZ20KjZzCdc/1+dNiNVCBMBf3dVZgEUG8W5mxk2t5KrchvZ5ew1AepI50rDwRzNl+1cCp";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgxK9xYXf3tjwjC33JkLBjkc6TCzYPPzLuZR0DhbFxOKuFk38u+GNs33qvFrfEISHahHUquUYlNpcKXbLI4hhVIZ84E32KHyquQ8FY9micODA6LYv3maEwZ1GCqjMADxBawUKaHqsSDkZRxLGTnd26cbw/h2CZpaisOE6Vq2GD3wIDAQAB";

    public void  testZhimaCreditEpScoreGet() {
        ZhimaCreditEpScoreGetRequest req = new ZhimaCreditEpScoreGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setOpenId("268810000007909449496");// 必要参数 
        req.setTransactionId("201512100936588040000000465158");// 必要参数 
        req.setProductCode("w1010100003000001418");// 必要参数 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCreditEpScoreGetResponse response = client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestZhimaCreditEpScoreGet result = new  TestZhimaCreditEpScoreGet();
        result.testZhimaCreditEpScoreGet();
    }
}
