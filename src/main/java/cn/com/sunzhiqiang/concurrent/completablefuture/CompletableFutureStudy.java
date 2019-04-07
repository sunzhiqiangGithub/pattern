package cn.com.sunzhiqiang.concurrent.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author sunzhiqiang
 * @description: CompletableFuture学习
 * @date 2019-04-0717:11
 */
public class CompletableFutureStudy {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建一个CompletableFuture对象
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> invokeRomoteService())
                .thenCombine(CompletableFuture.supplyAsync(CompletableFutureStudy::invokeRomoteService2),
                             (result1, result2) -> result1 * result2)
                .thenRunAsync(CompletableFutureStudy::onCompletionRun);

        future.get();
    }

    private static int invokeRomoteService() {

        System.out.println("远程调用1");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Random().nextInt(10);
    }

    private static int invokeRomoteService2() {

        System.out.println("远程调用2");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 10;
    }

    private static void onCompletion(int a) {

        System.out.println("回调方法");

        try {
            System.out.println(a);
        } catch (Exception e) {

        }
    }

    private static void onCompletionRun() {

        System.out.println("回调run");
    }
}
