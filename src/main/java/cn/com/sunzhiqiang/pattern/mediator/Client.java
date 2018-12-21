package cn.com.sunzhiqiang.pattern.mediator;

import java.util.List;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2018-11-12
 */
public class Client {

    public static void main(String[] args) {

        ServiceMediator serviceMediator = new ServiceMediator();
        Service serviceA = new ConcreteServiceA(serviceMediator);
        Service serviceB = new ConcreteServiceB(serviceMediator);

        System.out.println("服务A提供的服务" + serviceA.service());
        List<Service> services = serviceA.getRemoteServices();
        System.out.println("服务A能获取的服务列表");
        for (Service service : services) {
            System.out.println(service.service());
        }
    }
}
