package feiwoscun.jdkproxy_feiwoscun.test;

import feiwoscun.jdkproxy_feiwoscun.annotation.FeiInterceptors;
import feiwoscun.jdkproxy_feiwoscun.annotation.Signature;
import feiwoscun.jdkproxy_feiwoscun.annotation.TargetMethod;
import feiwoscun.jdkproxy_feiwoscun.core.Invocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */

/**
 *
 */
@TargetMethod(signatures={@Signature(clazz = Animal.class, args = {}, method = "hello")})
public class interceptor1 implements FeiInterceptors {
    @Override
    public Object doInterceptor(Invocation invocation) {
        System.out.println("我是一只可爱的小猫1");
/*        return invocation.next();*/
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        Object obj = invocation.getObj();
         Object invoke=null;
        try {
            invoke= method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println("我是可爱的小猫1");
        return  invoke;
    }
}
