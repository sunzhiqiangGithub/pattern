package cn.com.sunzhiqiang.pattern.visitor;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 抽象元素（电表）
 */
public interface ElectricMeter {

    void accept(MeterReader meterReader);
}
