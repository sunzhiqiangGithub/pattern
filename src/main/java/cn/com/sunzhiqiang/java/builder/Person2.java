package cn.com.sunzhiqiang.java.builder;

/**
 * 功能描述: 使用builder模式构建javabean
 *
 * @author sunzhiqiang
 * @create 2018-08-12
 */
public class Person2 {

    private static final ThreadLocal<PersonBuilder> builder = new ThreadLocal<PersonBuilder>() {

        @Override
        protected PersonBuilder initialValue() {
            return new PersonBuilder();
        }
    };

    private String name;

    private int age;

    private String sex;

    private Person2(PersonBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
    }

    public static Person2.PersonBuilder getBuilder() {

        return builder.get();
    }

    private static class PersonBuilder {

        private String name;

        private int age;

        private String sex;

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Person2 build() {
            return new Person2(this);
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
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static void main(String[] args) {

        Person2 person2 = Person2.getBuilder()
                .name("李四")
                .age(10)
                .sex("不确定")
                .build();

        System.out.println(person2);
    }
}
