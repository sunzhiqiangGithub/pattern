package cn.com.sunzhiqiang.pattern.builder;

/**
 * 功能描述: 指挥者
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class Director {

    private ComputerBuilder builder;

    public Director(ComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer buildComputer() {

        return builder.buildCPU("Mac的CPU")
                      .buildRAM("Mac的内存")
                      .buildHardDisk("Mac的硬盘")
                      .buildMotherBoard("Mac的主板")
                      .build();
    }
}
