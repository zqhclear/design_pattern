package netty.netty_in_action.websocket;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @desc: 通讯加密
 * @author: zhongqionghua
 * @create: 2019/6/21 16:39
 */
public class SecureChatServerInitializer extends ChatServerInitializer {
	private final SslContext sslContext;

	public SecureChatServerInitializer(ChannelGroup group, SslContext sslContext) {
		super(group);
		this.sslContext = sslContext;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		super.initChannel(ch);
		SSLEngine engine = sslContext.newEngine(ch.alloc());
		engine.setUseClientMode(false);
		ch.pipeline().addFirst(new SslHandler(engine));
	}
}
