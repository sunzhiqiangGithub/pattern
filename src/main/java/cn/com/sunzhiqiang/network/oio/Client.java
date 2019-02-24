package cn.com.sunzhiqiang.network.oio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 功能描述: 客户端
 *
 * @author sunzhiqiang
 * @create 2019-02-23
 */
public class Client {

    private String host;

    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));

        OutputStream out = socket.getOutputStream();
        out.write("ping".getBytes());

        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int size;
        while ((size = in.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, size));
        }
    }

    public static void main(String[] args) throws IOException {

        new Client("localhost", 20000).start();
    }
}
