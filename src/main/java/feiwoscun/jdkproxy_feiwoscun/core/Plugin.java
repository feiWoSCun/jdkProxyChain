package feiwoscun.jdkproxy_feiwoscun.core;

import feiwoscun.jdkproxy_feiwoscun.annotation.FeiInterceptors;
import feiwoscun.jdkproxy_feiwoscun.annotation.Signature;
import feiwoscun.jdkproxy_feiwoscun.annotation.TargetMethod;
import feiwoscun.jdkproxy_feiwoscun.exception.FeiException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @description: 把实现类和拦截器整合在一起，像链条一样
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
public class Plugin implements InvocationHandler {
    /**
     * 上一个代理类
     */
    private Object object;
    /**
     * 上一个拦截器
     */
    private FeiInterceptors interceptor;
    /**
     *
     */
    Map<Class, Set<Method>> declaredMethods;

    public Plugin(Object object, FeiInterceptors interceptor, Map<Class, Set<Method>> declaredMethods) {
        this.object = object;
        this.interceptor = interceptor;
        this.declaredMethods = declaredMethods;
    }

    /**
     * @param tar
     * @param interceptor
     * @return
     */

    public static Object wrap(Object tar, FeiInterceptors interceptor) {
        /**
         * 所有的拦截方法集合
         */
        Map<Class, Set<Method>> declaredMethods = getDeclaredMethods(interceptor);
        Class[] allInterfaces = getAllInterfaces(tar, declaredMethods);
        if (allInterfaces.length > 0) {
            return Proxy.newProxyInstance(
                    tar.getClass().getClassLoader(),
                    allInterfaces,
                    new Plugin(tar, interceptor, declaredMethods));
        }
        return tar;
    }

    /**
     * @param feiInterceptor
     * @return
     */
    private static Map<Class, Set<Method>> getDeclaredMethods(FeiInterceptors feiInterceptor) {
        TargetMethod annotation = feiInterceptor.getClass()
                .<TargetMethod>getAnnotation(TargetMethod.class);
        Map<Class, Set<Method>> signatureMap = new HashMap<>(2);
        try {
            Signature[] signatures = annotation.signatures();
            for (Signature signature : signatures) {
                Class clazz = signature.clazz();
                //很妙，拿到一个set<method>，如果没有就new一个
                Set<Method> methods = signatureMap.computeIfAbsent(clazz, t -> new HashSet<>());
                String method = signature.method();
                Class[] args = signature.args();

                Method tarMethod = clazz.getMethod(method, args);
                methods.add(tarMethod);
            }
        } catch (NoSuchMethodException e) {
            throw new FeiException("没有找到实际方法");
        }
        return signatureMap;
    }

    /**
     * 返回target和intercepts共同实现的接口数组
     *
     * @param obj
     * @param signatureMap
     * @return
     */
    private static Class[] getAllInterfaces(Object obj, Map<Class, Set<Method>> signatureMap) {
        Set<Class> sets = new HashSet<>();
        Class<?> aClass = obj.getClass();
        while (aClass != null && aClass != Object.class) {
            for (Class<?> anInterface : aClass.getInterfaces()) {
                if (signatureMap.containsKey(anInterface)) {
                    sets.add(anInterface);
                }
            }
            //迭代
            aClass = aClass.getSuperclass();
        }
        return sets.toArray(new Class[sets.size()]);
    }

    /**
     * @param o       o用不到
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Set<Method> methods = declaredMethods.get(method.getDeclaringClass());
        if (method != null && methods.contains(method)) {
            return interceptor.doInterceptor(new Invocation(object, method, objects));
        }
        return method.invoke(object, objects);

    }
}
