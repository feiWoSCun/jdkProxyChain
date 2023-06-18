package feiwoscun.jdkproxy_feiwoscun.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 用于定位具体需要拦截的方法
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Signature {
        /**
     * 拦截的接口
     * @return
     */
    Class clazz();

    /**
     * 拦截的方法参数
     * @return
     */
    Class[] args();

    /**
     * 拦截的方法
     * @return
     */
    String method();

}
