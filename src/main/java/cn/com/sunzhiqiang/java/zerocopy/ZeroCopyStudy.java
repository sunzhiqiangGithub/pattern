package cn.com.sunzhiqiang.java.zerocopy;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 功能描述: 零拷贝学习
 *
 * @author sunzhiqiang
 * @create 2018-08-19
 */
public class ZeroCopyStudy {

    public static void main(String[] args) throws IOException {

        copyByBuffered();
        copyByTransferFrom();
        copyByTransferTo();
        copyByTransferToUseAccessFile();
        copyByFiles();
    }

    private static void copyByTransferTo() throws IOException {

        //FileChannel fromChannel = new FileInputStream(ZeroCopyStudy.class.getResource("/source.txt").getFile()).getChannel();
        FileChannel fromChannel = new FileInputStream("D:/video/a.avi").getChannel();
        FileChannel toChannel = new FileOutputStream("copy.txt").getChannel();

        long startTime = System.nanoTime();
        fromChannel.transferTo(0, fromChannel.size(), toChannel);
        System.out.println("transferTo:" + (System.nanoTime() - startTime)/1000000000.0);

        fromChannel.close();
        toChannel.close();

        Files.deleteIfExists(Paths.get("copy.txt"));
    }

    private static void copyByTransferFrom() throws IOException {

       // FileChannel fromChannel = new FileInputStream(ZeroCopyStudy.class.getResource("/source.txt").getFile()).getChannel();
        FileChannel fromChannel = new FileInputStream("D:/video/a.avi").getChannel();
        FileChannel toChannel = new FileOutputStream("copy.txt").getChannel();

        long startTime = System.nanoTime();
        toChannel.transferFrom(fromChannel,0,fromChannel.size());
        System.out.println("transferFrom:" + (System.nanoTime() - startTime)/1000000000.0);

        fromChannel.close();
        toChannel.close();

        Files.deleteIfExists(Paths.get("copy.txt"));
    }

    private static void copyByTransferToUseAccessFile() throws IOException {

        //FileChannel fromChannel = new RandomAccessFile(ZeroCopyStudy.class.getResource("/source.txt").getFile(),"rw").getChannel();
        FileChannel fromChannel = new RandomAccessFile("D:/video/a.avi","rw").getChannel();
        FileChannel toChannel = new RandomAccessFile("copy.txt","rw").getChannel();

        long startTime = System.nanoTime();
        fromChannel.transferTo(0, fromChannel.size(), toChannel);
        System.out.println("randomAccessFile:" + (System.nanoTime() - startTime)/1000000000.0);

        fromChannel.close();
        toChannel.close();

        Files.deleteIfExists(Paths.get("copy.txt"));
    }

    private static void copyByFiles() throws IOException {

        //Path fromPath = Paths.get(ZeroCopyStudy.class.getResource("/source.txt").getFile());
        Path fromPath = Paths.get("D:/video/a.avi");
        Path toPath = Paths.get("copy.txt");

        long startTime = System.nanoTime();
        Files.copy(fromPath,toPath);
        System.out.println("Files:" + (System.nanoTime() - startTime)/1000000000.0);

        Files.deleteIfExists(Paths.get("copy.txt"));
    }

    private static void copyByBuffered() throws IOException {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:/video/a.avi"));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("copy.txt"));

        long startTime = System.nanoTime();
        byte[] byteArray = new byte[4096];
        int index;
        while ((index = in.read(byteArray,0,byteArray.length)) != -1){
            out.write(byteArray,0,index);
        }
        System.out.println("buffer流:" + (System.nanoTime() - startTime)/1000000000.0);

        in.close();
        out.close();

        Files.deleteIfExists(Paths.get("copy.txt"));
    }

}
