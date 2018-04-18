package com.mortals.iot.server.initializer;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.codec.MessageDecoder;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.DeviceMessageHandler;
import com.mortals.iot.protocol.jt808.constant.SystemConstants;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Just a dummy protocol mainly to show the ServerBootstrap being initialized.
 * 
 * @author cjun
 * 
 */
@Component
@Qualifier("nettyServerInitializer")
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		// 心跳检测
		pipeline.addLast("idleStateHandler", new IdleStateHandler(SystemConstants.TCP_CLIENT_IDLE_MINUTES, 0, 0, TimeUnit.MINUTES));
		// 设置decoder用于记录日志
		// pipeline.addLast(new Decoder4LoggingOnly());
		// 1024表示单条消息的最大长度，解码器在查找分隔符的时候，达到该长度还没找到的话会抛异常
		// pipeline.addLast(
		// new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[] { 0x7e
		// }),
		// Unpooled.copiedBuffer(new byte[] { 0x7e, 0x7e })));
		pipeline.addLast(new MessageDecoder());
		pipeline.addLast(new DeviceMessageHandler());
		// pipeline.addLast(new MessageEncoder());
		// pipeline.addLast(tcpServerHandler);

	}

}
