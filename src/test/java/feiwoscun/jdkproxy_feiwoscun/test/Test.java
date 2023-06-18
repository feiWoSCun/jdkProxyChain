package feiwoscun.jdkproxy_feiwoscun.test;

import feiwoscun.jdkproxy_feiwoscun.core.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
@SpringBootTest
public class Test {
    @Autowired
    Configuration configuration;

    @org.junit.jupiter.api.Test
    public void test1() {
        System.out.println();

    }

    @org.junit.jupiter.api.Test
    public void test2() {
        configuration.addInterceptor(new interceptor1()).addInterceptor(new interceptor2());
        Object o = configuration.ProxyTargetObj(new Cat());
        Animal a = (Animal) o;
        ((Animal) o).hello();
    }
}
