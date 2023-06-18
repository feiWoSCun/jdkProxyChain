package feiwoscun.jdkproxy_feiwoscun.exception;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/6/18
 * @Email: 2825097536@qq.com
 */
public class FeiException extends RuntimeException {
    public FeiException() {
        super();
    }

    public FeiException(String message) {
        super(message);
    }

    public FeiException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeiException(Throwable cause) {
        super(cause);
    }
}
