package cn.com.sunzhiqiang.pattern.mediator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述: 中介者模式-中介者
 *
 * @author sunzhiqiang
 * @create 2018-11-12
 */
public class ServiceMediator {

    private ConcurrentHashMap<String, Service> register = new ConcurrentHashMap<>();

    public void registered(Service service) {
        register.put(service.getName(), service);
    }

    public ConcurrentHashMap<String, Service> getRegister(){
        return register;
    }
}
