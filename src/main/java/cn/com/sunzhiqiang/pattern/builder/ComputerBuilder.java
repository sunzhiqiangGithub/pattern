package cn.com.sunzhiqiang.pattern.builder;

/**
 * 功能描述: 抽象构建器
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public interface ComputerBuilder {

    ComputerBuilder buildCPU(String cpu);

    ComputerBuilder buildRAM(String ram);

    ComputerBuilder buildHardDisk(String hardDisk);

    ComputerBuilder buildMotherBoard(String motherBoard);

    Computer build();
}
