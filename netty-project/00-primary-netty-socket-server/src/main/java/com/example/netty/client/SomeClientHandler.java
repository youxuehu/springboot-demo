package com.example.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author youxuehu
 */
public class SomeClientHandler extends SimpleChannelInboundHandler<String> {


    /**
     * msg 消息类型与类中泛型类型一致
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress() + ", " + msg);
        channelHandlerContext.channel().writeAndFlush("from client : " + LocalDateTime.now());
        TimeUnit.MILLISECONDS.sleep(5000);
    }

    /**
     * 当channel被激活后会触发此方法执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("from client : begin talking");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
