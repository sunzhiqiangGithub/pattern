package cn.com.sunzhiqiang.pattern.builder;

/**
 * 功能描述: 具体构建器
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class MacComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public MacComputerBuilder(Computer computer){
        this.computer = computer;
    }

    @Override
    public ComputerBuilder buildCPU(String cpu) {

        computer.cpu = cpu;
        return this;
    }

    @Override
    public ComputerBuilder buildRAM(String ram) {

        computer.ram = ram;
        return this;
    }

    @Override
    public ComputerBuilder buildHardDisk(String hardDisk) {

        computer.hardDisk = hardDisk;
        return this;
    }

    @Override
    public ComputerBuilder buildMotherBoard(String motherBoard) {

        computer.motherboard = motherBoard;
        return this;
    }

    @Override
    public Computer build() {

        return computer;
    }
}
