package feiwoscun.jdkproxy_feiwoscun.test;

import feiwoscun.jdkproxy_feiwoscun.annotation.FeiInterceptors;
import feiwoscun.jdkproxy_feiwoscun.annotation.Signature;
import feiwoscun.jdkproxy_feiwoscun.annotation.TargetMethod;
import feiwoscun.jdkproxy_feiwoscun.core.Invocation;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
@TargetMethod(signatures = {@Signature(clazz = Animal.class, args = {}, method = "hello")})
public class interceptor2 implements FeiInterceptors {
    @Override
    public Object doInterceptor(Invocation invocation) {
        System.out.println("我是一只可爱的小猫2");
        return invocation.next();
    }
}
