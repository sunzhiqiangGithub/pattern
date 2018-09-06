package cn.com.sunzhiqiang.java.thread;

public class Test implements Runnable {

    volatile int b = 100;

    synchronized void m1() throws InterruptedException {
        System.out.println("s1 start");
        b = 1000;
        Thread.sleep(500);
        System.out.println("b=" + b);
        System.out.println("s1 end");
    }

    synchronized void m2() throws InterruptedException {
        System.out.println("s2 start");
        //Thread.sleep(250);
        b = 2000;
        System.out.println("s2 end");
    }

    public static void main(String[] args) throws InterruptedException {

        Test test = new Test();
        Thread t = new Thread(test);
        t.start();

        test.m2();
        //Thread.sleep(1000);
//        System.out.println("main thread b = " + test.b);
        synchronized (test){
            System.out.println(String.format("main thread b = %s", test.b));
        }
        System.out.println(test.b);
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
