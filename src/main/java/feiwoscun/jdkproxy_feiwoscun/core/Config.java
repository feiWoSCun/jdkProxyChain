package feiwoscun.jdkproxy_feiwoscun.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
@EnableConfigurationProperties()
public class Config {
    /**
     * 也可以使用单例模式，脱离Spring？
     *
     * @return Configuration
     */
    @Bean
    @ConditionalOnProperty(prefix = "feiwoscun", name = "ifInterceptors", havingValue = "true", matchIfMissing = true)
    public Configuration getConfiguration() {
        return new Configuration(new FeiInterceptorsChain());
    }
}
