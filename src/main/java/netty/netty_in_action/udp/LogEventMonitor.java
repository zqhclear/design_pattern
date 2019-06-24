package netty.netty_in_action.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/24 13:39
 */
public class LogEventMonitor {
	private EventLoopGroup eventLoopGroup;
	private Bootstrap bootstrap;

	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("请输入正确的参数.");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		LogEventMonitor logEventMonitor = new LogEventMonitor(new InetSocketAddress(port));
		try{
			Channel channel = logEventMonitor.bind();
			System.out.println("LogEventMonitor is running");
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			logEventMonitor.stop();
		}


	}

	public LogEventMonitor(InetSocketAddress inetSocketAddress) {
		this.eventLoopGroup = new NioEventLoopGroup();
		this.bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup)
				.channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ChannelPipeline channelPipeline = ch.pipeline();
						channelPipeline.addLast(new LogEventDecoder(), new LogEventHandler());
					}
				})
				.localAddress(inetSocketAddress);
	}

	public Channel bind(){
		return bootstrap.bind().syncUninterruptibly().channel();
	}

	public void stop(){
		eventLoopGroup.shutdownGracefully();
	}
}
