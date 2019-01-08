package cn.com.sunzhiqiang.pattern.visitor;

import java.math.BigDecimal;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体访问者（具体抄表员）
 */
public class ConcreteMeterReader implements MeterReader {

    private static final int FIRST_LEVEL = 10;
    private static final int SECOND_LEVEL = 20;
    private static final int THIRD_LEVEL = 30;

    private BigDecimal firstLevelPrice = BigDecimal.ONE;
    private BigDecimal secondLevelPrice = new BigDecimal("1.5");
    private BigDecimal thirdLevelPrice = new BigDecimal("2");

    @Override
    public BigDecimal calcPrice(ConcreteElectricMeter electricMeter) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        BigDecimal meter = electricMeter.getMeter();

        if (meter.compareTo(new BigDecimal(FIRST_LEVEL)) <= 0) {
            return firstLevelPrice.multiply(meter);
        }

        if (meter.compareTo(new BigDecimal(SECOND_LEVEL)) <= 0) {
            return firstLevelPrice.multiply(new BigDecimal(FIRST_LEVEL))
                    .add(secondLevelPrice.multiply(meter.subtract(new BigDecimal(FIRST_LEVEL))));
        }

        totalPrice = firstLevelPrice.multiply(new BigDecimal(FIRST_LEVEL))
                .add(secondLevelPrice.multiply(new BigDecimal(SECOND_LEVEL).subtract(new BigDecimal(FIRST_LEVEL))))
                .add(thirdLevelPrice.multiply(meter.subtract(new BigDecimal(SECOND_LEVEL))));

        return totalPrice;
    }
}
