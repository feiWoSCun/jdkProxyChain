package feiwoscun.jdkproxy_feiwoscun.core;

import feiwoscun.jdkproxy_feiwoscun.annotation.FeiInterceptors;
import feiwoscun.jdkproxy_feiwoscun.exception.FeiException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
public class FeiInterceptorsChain {
    private List<FeiInterceptors> interceptors = new ArrayList<>();

    /**
     * @param tar 代理链
     * @return
     */
    public Object PluginAll(Object tar) {
        Object res = tar;
        for (FeiInterceptors interceptor : interceptors) {
            res = Plugin.wrap(res,interceptor);
        }
        return res;
    }

    public List<FeiInterceptors> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<FeiInterceptors> interceptors) {
       if( CollectionUtils.isEmpty(interceptors)){
           throw new FeiException("还没有注册拦截器");
       };
        this.interceptors = interceptors;
    }
}
