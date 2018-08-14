package cn.com.sunzhiqiang.java.objects;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 功能描述: Objects工具类学习
 *
 * @author sunzhiqiang
 * @create 2018-08-14
 */
public class MyObjects {

    //private static Objects objects;

    @Test
    public void testEquals() {

        String str1 = "aaa";
        String str2 = new String("aaa");
        System.out.println(str1.equals(str2)); // true
        System.out.println(Objects.equals(str1, str2)); // true

        Integer integer1 = 1;
        Integer integer2 = 1;
        System.out.println(integer1.equals(integer2)); // true
        System.out.println(Objects.equals(integer1, integer2)); // true

        Integer integer3 = 128;
        Integer integer4 = 128;
        System.out.println(integer3.equals(integer4)); // true
        System.out.println(Objects.equals(integer3, integer4)); // true

        MyObjects myObjects1 = new MyObjects();
        MyObjects myObjects2 = new MyObjects();
        System.out.println(myObjects1.equals(myObjects2)); // false
        System.out.println(Objects.equals(myObjects1, myObjects2)); // false
    }

    @Test
    public void testDeepEquals() {

        // 比较2个对象的值是不是相等
        String str1 = "aaa";
        String str2 = new String("aaa");
        System.out.println(Objects.deepEquals(str1, str2)); // true

        // 比较2个对象数组所存储的值是不是相等
        String[] strArray1 = new String[]{"aaa", "bbb", "ccc"};
        String[] strArray2 = new String[]{new String("aaa"), new String("bbb"), new String("ccc")};
        System.out.println(Objects.deepEquals(strArray1, strArray2)); // true
    }

    @Test
    public void testHashCode() {

        String str1 = null;
        String str2 = "aaa";
        System.out.println(Objects.hashCode(str1)); // 0
        System.out.println(str2.hashCode()); // 96321
        System.out.println(Objects.hashCode(str2)); // 96321
    }

    @Test
    public void testHash() {

        String[] strArray = new String[]{"aaa", "bbb", "ccc"};
        System.out.println(Arrays.hashCode(strArray)); // 95709313
        System.out.println(Objects.hash(strArray)); // 95709313
    }

    @Test
    public void testToString() {

        Integer integer = 200;
        System.out.println(integer.toString()); // 200
        System.out.println(Objects.toString(integer)); // 200

        MyObjects myObjects = new MyObjects();
        System.out.println(myObjects.toString()); // cn.com.sunzhiqiang.java.objects.MyObjects@1794d431
        System.out.println(Objects.toString(myObjects)); // cn.com.sunzhiqiang.java.objects.MyObjects@1794d431
    }

    @Test
    public void testToStringHasDefaultValue() {

        String str1 = null;
        String str2 = "bbb";
        System.out.println(Objects.toString(str1, "aaa")); // aaa
        System.out.println(Objects.toString(str2, "aaa")); // bbb
    }

    @Test
    public void testCompare() {

        Integer a = 6;
        Integer b = 8;
        System.out.println(Objects.compare(a, b, (o1,o2) -> {

                if (o1 > o2)
                    return 1;
                if (o1 < o2)
                    return -1;
                return 0;

        })); // -1
    }

    @Test
    public void testRequireNonNull() {

        String str = null;
        String str2 = "abc";
        System.out.println(Objects.requireNonNull(str)); // java.lang.NullPointerException
        System.out.println(Objects.requireNonNull(str2)); // abc
    }

    @Test
    public void testRequireNotNullHasDefaultMessage() {

        String str = null;
        String str2 = "abc";
        System.out.println(Objects.requireNonNull(str, "参数不能为空")); // java.lang.NullPointerException: 参数不能为空
        System.out.println(Objects.requireNonNull(str2, "参数不能为空")); // abc
    }

    @Test
    public void testIsNull() {

        String str = null;
        String str2 = "abc";
        System.out.println(Objects.isNull(str)); // true
        System.out.println(Objects.isNull(str2)); // false
    }

    @Test
    public void testNonNull() {

        String str = null;
        String str2 = "abc";
        System.out.println(Objects.nonNull(str)); // false
        System.out.println(Objects.nonNull(str2)); // true
    }

    @Test
    public void testRequireNonNullHasFunctionInterface(){

        String str = null;
        String str2 = "abc";
        System.out.println(Objects.requireNonNull(str,() -> {
            return "参数不能为空";
        })); //return java.lang.NullPointerException: 参数不能为空
        System.out.println(Objects.requireNonNull(str2,() -> {
            return "参数不能为空";
        })); // abc
    }
}
