package cn.com.sunzhiqiang.java.zerocopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 功能描述: 零拷贝学习
 *
 * @author sunzhiqiang
 * @create 2018-08-19
 */
public class ZeroCopyStudy {

    public static void main(String[] args) throws IOException {

        FileInputStream in = new FileInputStream(ZeroCopyStudy.class.getResource("/source.txt").getFile());
        FileChannel inChannel = in.getChannel();

        FileOutputStream out = new FileOutputStream("copy.txt");
        FileChannel outChannel = out.getChannel();

        inChannel.transferTo(0, in.available(), outChannel);

        inChannel.close();
        in.close();
        outChannel.close();
        out.close();
    }
}
