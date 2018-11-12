package cn.com.sunzhiqiang.pattern.mediator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: 中介者模式-具体服务
 *
 * @author sunzhiqiang
 * @create 2018-11-12
 */
public class ConcreteServiceA implements Service {

    private final ServiceMediator serviceMediator;

    private String name;

    public ConcreteServiceA(ServiceMediator serviceMediator) {
        this.serviceMediator = serviceMediator;
        name = ConcreteServiceA.class.getName();

        serviceMediator.registered(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String service() {
        return name + "提供的服务";
    }

    @Override
    public List<Service> getRemoteServices() {
        List<Service> services = serviceMediator.getRegister().entrySet().stream()
                .map(e -> e.getValue()).collect(Collectors.toList());
        return services;
    }
}
