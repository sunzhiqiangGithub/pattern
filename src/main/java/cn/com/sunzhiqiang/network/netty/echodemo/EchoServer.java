/*
 *Baijiahulian.comInc.Copyright(c)2014-2019AllRightsReserved.
 */
package cn.com.sunzhiqiang.network.netty.echodemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

import java.net.InetSocketAddress;

/**
 * @author sunzhiqiang
 * @version version
 * @title EchoServer
 * @desc echo server
 * @date: 2020年03月06日
 */
@AllArgsConstructor
public class EchoServer {

    private int port;

    public static void main(String[] args) throws InterruptedException {
        final int port = 11111;
        new EchoServer(port).start();
    }

    private void start() throws InterruptedException {

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EchoServerHandler echoServerHandler = new EchoServerHandler();

        try {
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(new InetSocketAddress(port));
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(echoServerHandler);
                }
            });
            ChannelFuture channelFuture = bootstrap.bind().sync();
            ChannelFuture closeFuture = channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}

