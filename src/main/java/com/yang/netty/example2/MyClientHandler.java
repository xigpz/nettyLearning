package com.yang.netty.example2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    /**
     * @param ctx 上下文请求
     * @param msg 服务起发送来的对象
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output:" + msg);
        ctx.writeAndFlush("from clent:" + LocalDateTime.now());

    }

    /**
     * 不主动发送消息，客户端和服务端讲一直等待对方发送消息，控制台不会进行输出消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //启动后主动向服务器发送消息
        ctx.writeAndFlush("来自客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
