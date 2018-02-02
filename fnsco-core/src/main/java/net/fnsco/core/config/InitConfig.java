package net.fnsco.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayConstants;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.OssUtil;

@Configuration
public class InitConfig {
    @Autowired
    private Environment env;

    @Bean
    public int ossConf() {
        OssUtil.setEndpoint(env.getProperty("aliyun.oss.endpoint"));
        OssUtil.setAccessKeyId(env.getProperty("aliyun.oss.accessKeyId"));
        OssUtil.setAccessKeySecret(env.getProperty("aliyun.oss.accessKeySecret"));
        OssUtil.setHeadBucketName(env.getProperty("aliyun.oss.headBucketName"));
        if (null != env.getProperty("aliyun.oss.endpoint")) {
            OssUtil.initOssClient();
        }

        OssLoaclUtil.setEndpoint(env.getProperty("aliyun.oss.local.endpoint"));
        OssLoaclUtil.setAccessKeyId(env.getProperty("aliyun.oss.local.accessKeyId"));
        OssLoaclUtil.setAccessKeySecret(env.getProperty("aliyun.oss.local.accessKeySecret"));
        OssLoaclUtil.setHeadBucketName(env.getProperty("aliyun.oss.local.headBucketName"));
        if (null != env.getProperty("aliyun.oss.local.endpoint")) {
            OssLoaclUtil.initOssClient();
        }
        return 1;
    }
    
    @Bean
    public int alipayConf() {
        AlipayClientUtil.setAppId(env.getProperty(AlipayConstants.APP_ID));
        AlipayClientUtil.setAlipayPublicKey(env.getProperty(AlipayConstants.ALIPAY_PUBLIC_KEY));
        AlipayClientUtil.setAppPrivateKey(env.getProperty(AlipayConstants.APP_PRIVATE_KEY));
        AlipayClientUtil.setAlipayAppPublicKey(env.getProperty(AlipayConstants.ALIPAY_APP_PUBLIC_KEY));
        
        AlipayClientUtil.initAlipayClient();
        return 1;
    }
}