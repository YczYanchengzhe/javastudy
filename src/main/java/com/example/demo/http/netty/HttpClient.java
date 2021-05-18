package com.example.demo.http.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

/**
 * @author chengzhe yan
 * @description
 * @date 2021/4/8 6:09 下午
 */
public class HttpClient {
	public static void start(String host, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		try {
			bootstrap.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel channel)
								throws Exception {
							channel.pipeline().addLast(new HttpClientCodec());
							channel.pipeline().addLast(new HttpObjectAggregator(65536));
							channel.pipeline().addLast(new HttpContentDecompressor());
							channel.pipeline().addLast(new HttpClientHandler());
						}
					});
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		//http://10.126.93.144:8080/test/testOceanus
		start("127.0.0.1",8085);
	}
}
