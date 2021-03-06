package com.soundai.infrastructure.springcloud.oauth2.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 令牌配置
 */
@Configuration
public class AccessTokenConfig {

    @Value("${soundai.oauth2.token.store}")
    private String tokenStore;

    /**
     * JWT的秘钥
     * TODO 实际项目中需要统一配置到配置文件中，资源服务也需要用到
     */
    private final static String SIGN_KEY="myjszl";

    /**
     * 令牌的存储策略
     */
    @Bean
    TokenStore tokenStore() {
        if ("inMemory".equals(tokenStore)) {
            // todo 方便测试，使用内存存储策略，一旦服务重启令牌失效，后续可以使用数据库存储或者JWT
            return new InMemoryTokenStore();
        }
        if ("jwt".equals(tokenStore)) {
        // 使用JwtTokenStore生成JWT令牌
            return new JwtTokenStore(jwtAccessTokenConverter());
        }
        return null;
    }

    /**
     * JwtAccessTokenConverter
     * TokenEnhancer的子类，在JWT编码的令牌值和OAuth身份验证信息之间进行转换。
     * TODO：后期可以使用非对称加密
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 设置秘钥
        converter.setSigningKey(SIGN_KEY);
        return converter;
    }

}
