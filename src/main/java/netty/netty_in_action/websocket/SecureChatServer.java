package netty.netty_in_action.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

/**
 * @desc: 通过ssl加密的websocket服务端
 * @author: zhongqionghua
 * @create: 2019/6/21 16:46
 */
public class SecureChatServer extends ChatServer {
	private final SslContext sslContext;

	public SecureChatServer(SslContext sslContext) {
		this.sslContext = sslContext;
	}

	@Override
	protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
		return new SecureChatServerInitializer(group, sslContext);
	}

	public static void main(String[] args) throws CertificateException, SSLException {
		if(null == args || args.length != 1){
			System.out.println("请输入正确的绑定端口号.eg:9999");
			System.exit(1);
		}

		int port = Integer.parseInt(args[0]);
		SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
		SslContext sslContext = SslContextBuilder.forServer(selfSignedCertificate.certificate()
				, selfSignedCertificate.privateKey()).build();
		final SecureChatServer endpoint = new SecureChatServer(sslContext);
		ChannelFuture bindFuture = endpoint.start(new InetSocketAddress(port));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> endpoint.destroy()));
		bindFuture.channel().closeFuture().syncUninterruptibly();
	}
}
