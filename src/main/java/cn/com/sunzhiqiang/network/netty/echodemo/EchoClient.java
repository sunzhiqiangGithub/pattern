/*
 *Baijiahulian.comInc.Copyright(c)2014-2019AllRightsReserved.
 */
package cn.com.sunzhiqiang.network.netty.echodemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;

import java.net.InetSocketAddress;

/**
 * @author sunzhiqiang
 * @version version
 * @title EchoClient
 * @desc echo client
 * @date: 2020年03月07日
 */
@AllArgsConstructor
public class EchoClient {

    private String ip;
    private int port;

    public static void main(String[] args) throws InterruptedException {

        final String ip = "127.0.0.1";
        final int port = 11111;

        new EchoClient(ip, port).start();
    }

    private void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        EchoClientHandler handler = new EchoClientHandler();
        try {
            ChannelFuture fu = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(ip, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    })
                    .connect().sync();
            ChannelFuture closeFuture = fu.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}

