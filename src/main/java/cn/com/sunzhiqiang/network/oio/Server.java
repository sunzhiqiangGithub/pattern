package cn.com.sunzhiqiang.network.oio;

import cn.com.sunzhiqiang.network.NetworkException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述: 服务端
 *
 * @author sunzhiqiang
 * @create 2019-02-23
 */
public class Server {

    /**
     * 端口号
     */
    private int port;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    public Server(int port) {
        if (port < 0 || port > 65535) {
            throw new NetworkException("无效的端口号");
        }

        this.port = port;
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("连接客户端：" + socket);

            threadPool.submit(() -> {
                OutputStream out = socket.getOutputStream();
                out.write("pong".getBytes());

                InputStream in = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int size;
                while ((size = in.read(bytes)) != -1) {
                    System.out.println(new String(bytes, 0, size));
                    out.write(size);
                }

                socket.close();

                return null;
            });
        }
    }

    public static void main(String[] args) throws IOException {

        new Server(20000).start();
    }
}
