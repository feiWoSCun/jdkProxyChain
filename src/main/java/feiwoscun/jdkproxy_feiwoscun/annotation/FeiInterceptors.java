package feiwoscun.jdkproxy_feiwoscun.annotation;

import feiwoscun.jdkproxy_feiwoscun.core.Invocation;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
public interface FeiInterceptors {
    /**
     * 具体的拦截行为
     * @param invocation
     * @return
     */
    Object  doInterceptor(Invocation invocation);

    /**
     * 下一步
     * @return
     */
   default Object next(){
       return  null;
   };


}
