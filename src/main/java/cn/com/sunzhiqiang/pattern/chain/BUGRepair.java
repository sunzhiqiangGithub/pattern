package cn.com.sunzhiqiang.pattern.chain;

/**
 * @author sunzhiqiang
 * @date 2018/10/10
 * @desc 责任链模式（BUG修改接口）
 */
public abstract class BUGRepair {

    private BUGRepair next;

    public abstract void repairBUG();

    public BUGRepair setNext(BUGRepair next) {
        this.next = next;
        return next;
    }

    public BUGRepair getNext() {
        return next;
    }
}
