package cn.com.sunzhiqiang.pattern.visitor;

import java.math.BigDecimal;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        ElectricBox electricBox = new ElectricBox();
        ConcreteElectricMeter concreteElectricMeter1 = new ConcreteElectricMeter(new BigDecimal("12.5"));
        ConcreteElectricMeter concreteElectricMeter2 = new ConcreteElectricMeter(new BigDecimal("25"));
        electricBox.addElectricMeter(concreteElectricMeter1);
        electricBox.addElectricMeter(concreteElectricMeter2);

        MeterReader meterReader = new ConcreteMeterReader();

        for (ElectricMeter electricMeter : electricBox.getElectricMeters()) {
            electricMeter.accept(meterReader);
        }
    }
}
