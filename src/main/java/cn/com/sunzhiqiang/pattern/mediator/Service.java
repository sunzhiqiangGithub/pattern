package cn.com.sunzhiqiang.pattern.mediator;

import java.util.List;

/**
 * 功能描述: 中介者模式-抽象的服务
 *
 * @author sunzhiqiang
 * @create 2018-11-12
 */
public interface Service {

    String getName();

    String service();

    List<Service> getRemoteServices();
}
