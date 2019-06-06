package netty;

import com.google.common.base.Charsets;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * @desc: netty的socket-server
 * @author: zhongqionghua
 * @create: 2019/6/6 11:37
 */
public class NettySocketServer {

	private static final AttributeKey<Object> SERVER_NAME_KEY = AttributeKey.newInstance("serverName");
	private static final String SERVER_NAME_VALUE = "nettyServer";
	public static final AttributeKey<Object> CLIENT_KEY = AttributeKey.newInstance("clientKey");
	public static final String CLIENT_VALUE = "clientValue";
	public static final int PORT = 8000;
	public static final String CHARSET = Charsets.UTF_8.toString();

	public static void main(String[] args) {
		/**
		 * 创建两个NioEventLoopGroup,这两个对象可以看做是传统IO编程模型的两大线程组,boosGroup表示监听端口,创建新连接的线程组,workerGroup表示处理每一条连接的数据读写的线程组
		 * 创建引导类 ServerBootstrap进行服务端的启动工作,通过.group(boosGroup, workerGroup)给引导类配置两大线程定型引导类的线程模型指定服务端的IO模型为NIO,通过.channel(NioServerSocketChannel.class)来指定IO模型
		 * 调用childHandler()方法给引导类创建ChannelInitializer定义后续每条连接的数据读写,业务处理逻辑,泛型参数NioSocketChannel是Netty对NIO类型的连接的抽象,而NioServerSocketChannel也是对NIO类型的连接的抽象
		 * serverBootstrap.bind()是异步的方法调用之后是立即返回的,返回值是ChannelFuture,给ChannelFuture添加监听器GenericFutureListener,在GenericFutureListener的operationComplete方法里面监听端口是否绑定成功
		 * childHandler()用于指定处理新连接数据的读写处理逻辑,handler()用于指定在服务端启动过程中的一些逻辑
		 * attr()方法给服务端的channel即NioServerSocketChannel指定一些自定义属性,通过channel.attr()取出该属性,给NioServerSocketChannel维护一个map
		 * childAttr()方法给每一条连接指定自定义属性,通过channel.attr()取出该属性
		 * childOption()方法给每条连接设置一些TCP底层相关的属性:
		 * ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制,true为开启
		 * ChannelOption.SO_REUSEADDR表示端口释放后立即就可以被再次使用,因为一般来说,一个端口释放后会等待两分钟之后才能再被使用
		 * ChannelOption.TCP_NODELAY表示是否开始Nagle算法,true表示关闭,false表示开启,通俗地说,如果要求高实时性,有数据发送时就马上发送,就关闭,如果需要减少发送次数减少网络交互就开启
		 * option()方法给服务端channel设置一些TCP底层相关的属性:
		 * ChannelOption.SO_BACKLOG表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁,服务器处理创建新连接较慢,适当调大该参数
		 */
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) {
						ch.pipeline().addLast(new ServerInHandler());
					}
				})
				.attr(SERVER_NAME_KEY, SERVER_NAME_VALUE)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childAttr(CLIENT_KEY, CLIENT_VALUE)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childOption(ChannelOption.SO_REUSEADDR, true)
				.childOption(ChannelOption.TCP_NODELAY, true)
		;
		bind(serverBootstrap, PORT);
	}

	/**
	 * 绑定端口号
	 *
	 * @param serverBootstrap
	 * @param port
	 */
	private static void bind(ServerBootstrap serverBootstrap, int port) {
		serverBootstrap
				.bind(port)
				.addListener(future -> {
					if (future.isSuccess()) {
						System.out.println("服务端：端口【" + port + "】绑定成功！");
					} else {
						System.out.println("服务端：端口【" + port + "】绑定失败，尝试绑定【" + (port + 1) + "】！");
						bind(serverBootstrap, port + 1);
					}
				});
	}

	/**
	 * 将content转成ByteBuf填充到ctx的缓存区中
	 *
	 * @param cxt
	 * @param content
	 * @return
	 */
	public static ByteBuf getByteBuf(ChannelHandlerContext cxt, String content) {
		// 获取 二进制抽象 ByteBuf
		ByteBuf byteBuf = cxt.alloc().buffer();
		// 准备数据
		byte[] bs = content.getBytes(Charset.forName("UTF-8"));
		// 把数据填充到buf中
		byteBuf.writeBytes(bs);
		return byteBuf;
	}
}

/**
 * netty都是通过handler来操作的
 */
class ServerInHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 服务端接收数据
	 *
	 * @param ctx
	 * @param msg
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf buf = (ByteBuf) msg;
		System.out.println(System.currentTimeMillis() + ": server receive:" + buf.toString(Charset.forName(NettySocketServer.CHARSET)));

		// 向客户端回复数据
		String content = "server reply message:" + System.currentTimeMillis();
		ByteBuf byteBuf = NettySocketServer.getByteBuf(ctx, content);
		ctx.channel().writeAndFlush(byteBuf);
	}
}