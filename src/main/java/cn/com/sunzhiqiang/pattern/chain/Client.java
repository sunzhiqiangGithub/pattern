package cn.com.sunzhiqiang.pattern.chain;

/**
 * @author sunzhiqiang
 * @date 2018/10/10
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        BUGRepair bugRepair = new ModuleA_BUGRepair();
        bugRepair.setNext(new ModuleB_BUGRepair()).setNext(new ModuleC_BUGRepair());
        bugRepair.repairBUG();
    }
}
