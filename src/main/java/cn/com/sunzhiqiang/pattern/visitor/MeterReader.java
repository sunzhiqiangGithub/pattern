package cn.com.sunzhiqiang.pattern.visitor;

import java.math.BigDecimal;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 抽象访问者（抄电表的员工）
 */
public interface MeterReader {

    BigDecimal calcPrice(ConcreteElectricMeter electricMeter);
}
