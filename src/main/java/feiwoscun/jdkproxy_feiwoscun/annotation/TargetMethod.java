package feiwoscun.jdkproxy_feiwoscun.annotation;

import lombok.Singular;

import java.lang.annotation.*;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetMethod {
    Signature[] signatures();

}
