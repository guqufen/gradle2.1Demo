package net.fnsco.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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
        OssUtil.initOssClient();
        return 1;
    }
}