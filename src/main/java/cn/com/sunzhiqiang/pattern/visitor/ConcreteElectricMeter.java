package cn.com.sunzhiqiang.pattern.visitor;

import java.math.BigDecimal;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体元素（具体电表）
 */
public class ConcreteElectricMeter implements ElectricMeter {

    private BigDecimal meter;

    public ConcreteElectricMeter(BigDecimal meter) {
        this.meter = meter;
    }

    @Override
    public void accept(MeterReader meterReader) {

        meterReader.calcPrice(this);
    }

    public BigDecimal getMeter() {
        return meter;
    }
}
