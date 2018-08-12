package cn.com.sunzhiqiang.java.builder;

/**
 * 功能描述: 使用builder模式构建javabean
 *
 * @author sunzhiqiang
 * @create 2018-08-12
 */
public class Person {

    private String name;

    private int age;

    private String sex;

    private Person() {

    }

    public static PersonBuilder getBuilder() {
        return new Person.PersonBuilder();
    }

    private static class PersonBuilder {

        private Person person = new Person();

        public PersonBuilder name(String name) {
            person.name = name;
            return this;
        }

        public PersonBuilder age(int age) {
            person.age = age;
            return this;
        }

        public PersonBuilder sex(String sex) {
            person.sex = sex;
            return this;
        }

        public Person build() {
            return person;
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static void main(String[] args) {

        // 通过builder创建对象
        Person person = Person.getBuilder().name("张三").age(11).build();
        System.out.println(person);
    }
}
