package cn.com.sunzhiqiang.pattern.proxy;

/**
 * 功能描述: 代理模式
 *
 * @author sunzhiqiang
 * @create 2018-08-09
 */
public class Secretary implements Phone {

    private Phone boss;

    public Secretary(Phone boss){
        this.boss = boss;
    }

    public void handlePhone(){
        System.out.println("秘书先处理一部分电话");
        boss.handlePhone();//老板在秘书处理之后再回复电话
    }
}
