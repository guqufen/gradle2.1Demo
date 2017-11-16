package net.fnsco.trading.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.core.utils.OssUtil;
import net.fnsco.trading.service.pay.channel.zxyh.util.ZxyhPayMD5Util;

@Configuration
public class InitPayConfig {
    @Autowired
    private Environment env;

    @Bean
    public int payConf() {
        ZxyhPayMD5Util.init(env.getProperty("zxyh.pay.md5"), env.getProperty("zxyh.pay.url"));
        return 1;
    }
}