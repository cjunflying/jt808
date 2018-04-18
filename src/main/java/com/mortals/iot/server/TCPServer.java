package com.mortals.iot.server;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

@Component
public class TCPServer {
	private Logger log = LoggerFactory.getLogger(TCPServer.class);

	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap b;
	
	@Autowired
	@Qualifier("tcpSocketAddress")
	private InetSocketAddress tcpPort;

	private ChannelFuture serverChannelFuture;

	@PostConstruct
	public void start() throws Exception {
		try {
			serverChannelFuture = b.bind(tcpPort).sync();
			log.info("protocol server is listing on:= {}",tcpPort.getPort());
		} catch (Exception e) {
			log.error("protocol server start failed.>>{} {}",e.getMessage(),tcpPort);
			System.exit(-1);
		}
	}

	@PreDestroy
	public void stop() throws Exception {
		log.info("stop server at " + tcpPort);
	    serverChannelFuture.channel().closeFuture().sync();
	}

	public ServerBootstrap getB() {
		return b;
	}

	public void setB(ServerBootstrap b) {
		this.b = b;
	}

	public InetSocketAddress getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(InetSocketAddress tcpPort) {
		this.tcpPort = tcpPort;
	}

}
