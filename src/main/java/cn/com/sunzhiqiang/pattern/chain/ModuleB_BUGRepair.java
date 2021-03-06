package cn.com.sunzhiqiang.pattern.chain;

/**
 * @author sunzhiqiang
 * @date 2018/10/10
 * @desc 责任链模式（模块B的BUG修复）
 */
public class ModuleB_BUGRepair extends BUGRepair {

    @Override
    public void repairBUG() {

        System.out.println("模块B的BUG修复了");
        if (getNext() != null) {
            getNext().repairBUG();
        }
    }
}
