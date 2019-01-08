package cn.com.sunzhiqiang.pattern.visitor;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 电箱
 */
public class ElectricBox {

    private Set<ElectricMeter> electricMeters = Sets.newHashSet();

    public void addElectricMeter(ElectricMeter electricMeter) {
        electricMeters.add(electricMeter);
    }

    public Set<ElectricMeter> getElectricMeters() {
        return electricMeters;
    }
}
