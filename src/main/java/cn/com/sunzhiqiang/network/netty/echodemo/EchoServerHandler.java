/*
 *Baijiahulian.comInc.Copyright(c)2014-2019AllRightsReserved.
 */
package cn.com.sunzhiqiang.network.netty.echodemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sunzhiqiang
 * @version version
 * @title EchoServerHandler
 * @desc echo demo服务端处理逻辑
 * @date: 2020年03月06日
 */
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        ctx.channel().write(buf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("EchoServerHandler catch exception:{}", cause);
        ctx.close();
    }
}

