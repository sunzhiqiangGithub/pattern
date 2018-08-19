package cn.com.sunzhiqiang.pattern.prototype;

import java.io.IOException;
import java.io.Serializable;

/**
 * 功能描述: 抽象原型
 *
 * @author sunzhiqiang
 * @create 2018-08-19
 */
public interface Prototype extends Cloneable, Serializable {

    Object getObjectByClone() throws CloneNotSupportedException;

    Object getObjectBySerializable() throws IOException, ClassNotFoundException;
}
