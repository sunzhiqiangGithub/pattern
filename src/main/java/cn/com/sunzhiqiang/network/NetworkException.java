package cn.com.sunzhiqiang.network;

/**
 * 功能描述: 网络异常类
 *
 * @author sunzhiqiang
 * @create 2019-02-23
 */
public class NetworkException extends RuntimeException {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
