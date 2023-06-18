package feiwoscun.jdkproxy_feiwoscun.core;

import feiwoscun.jdkproxy_feiwoscun.annotation.FeiInterceptors;
import org.aopalliance.intercept.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */

public class Configuration {
  private   FeiInterceptorsChain interceptorsChain;

    public Configuration() {
    }

    public Configuration(FeiInterceptorsChain interceptorsChain) {
        this.interceptorsChain = interceptorsChain;
    }

    /**
     * 添加拦截器
     *
     * @param interceptor
     * @return
     */

    public Configuration addInterceptor(FeiInterceptors interceptor) {

        this.interceptorsChain.getInterceptors().add(interceptor);
        return this;
    }

    /**
     * 移除拦截器，要不要一个批量移除的方法？
     *
     * @param interceptor
     * @return
     */
    public Configuration removeInterceptor(FeiInterceptors interceptor) {
        this.interceptorsChain.getInterceptors().remove(interceptor);
        return this;
    }

    public FeiInterceptorsChain getInterceptorsChain() {
        return interceptorsChain;
    }

    public void setInterceptorsChain(FeiInterceptorsChain interceptorsChain) {
        this.interceptorsChain = interceptorsChain;
    }
    public  Object ProxyTargetObj(Object obj) {
        return getInterceptorsChain().PluginAll(obj);
    }

}
