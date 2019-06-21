package netty;

import com.google.common.base.Charsets;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;

/**
 * @desc: netty的socket-server
 * 注:/*
 * TCP/IP协议中针对TCP默认开启了Nagle算法。Nagle算法通过减少需要传输的数据包，来优化网络。在内核实现中，
 * 数据包的发送和接受会先做缓存，分别对应于写缓存和读缓存。启动TCP_NODELAY，就意味着禁用了Nagle算法，允许小
 * 包的发送。对于延时敏感型，同时数据传输量比较小的应用，开启TCP_NODELAY选项无疑是一个正确的选择。比如，对
 * 于SSH会话，用户在远程敲击键盘发出指令的速度相对于网络带宽能力来说，绝对不是在一个量级上的，所以数据传输
 * 非常少；而又要求用户的输入能够及时获得返回，有较低的延时。如果开启了Nagle算法，就很可能出现频繁的延时，
 * 导致用户体验极差。当然，你也可以选择在应用层进行buffer，比如使用java中的buffered stream，尽可能地将大包
 * 写入到内核的写缓存进行发送；vectored I/O（writev接口）也是个不错的选择。对于关闭TCP_NODELAY，则是应用了
 * Nagle算法。数据只有在写缓存中累积到一定量之后，才会被发送出去，这样明显提高了网络利用率（实际传输数据pay
 * load与协议头的比例大大提高）。但是这由不可避免地增加了延时；与TCP delayed ack这个特性结合，这个问题会更
 * 加显著，延时基本在40ms左右。当然这个问题只有在连续进行两次写操作的时候，才会暴露出来，然后进行读操作，本
 * 身就不是一个好的网络编程模式；在应用层就应该进行优化。
 * 对于既要求低延时，又有大量小数据传输，还同时想提高网络利用率的应用，大概只能用UDP自己在应用层来实现
 * 可靠性保证了。好像企鹅家就是这么干的。TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，
 * 同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大
 * 的数据。（一个连接会设置MSS参数，因此，TCP/IP希望每次都能够以MSS尺寸的数据块来发送数据）。
 * Nagle算法就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
 * Nagle算法的基本定义是任意时刻，最多只能有一个未被确认的小段。 所谓“小段”，指的是小于MSS尺寸的数据
 * 块，所谓“未被确认”，是指一个数据块发送出去后，没有收到对方发送的ACK确认该数据已收到。
 * 举个例子，比如之前的blog中的实验，一开始client端调用socket的write操作将一个int型数据(称为A块)写入到
 * 网络中，由于此时连接是空闲的（也就是说还没有未被确认的小段），因此这个int型数据会被马上发送到server
 * 端，接着，client端又调用write操作写入‘/r/n’（简称B块），这个时候，A块的ACK没有返回，所以可以认为已
 * 经存在了一个未被确认的小段，所以B块没有立即被发送，一直等待A块的ACK收到（大概40ms之后），B块才被发送。
 * 整个过程如图所示：
 * 这里还隐藏了一个问题，就是A块数据的ACK为什么40ms之后才收到？这是因为TCP/IP中不仅仅有nagle算法，还有一个A
 * CK延迟机制 。当Server端收到数据之后，它并不会马上向client端发送ACK，而是会将ACK的发送延迟一段时间（假设
 * 为t），它希望在t时间内server端会向client端发送应答数据，这样ACK就能够和应答数据一起发送，就像是应答数据
 * 捎带着ACK过去。在我之前的时间中，t大概就是40ms。这就解释了为什么'/r/n'(B块)总是在A块之后40ms才发出。
 * 如果你觉着nagle算法太捣乱了，那么可以通过设置TCP_NODELAY将其禁用 。当然，更合理的方案还是应该使用一次大
 * 数据的写操作，而不是多次小数据的写操作。
 * @author:zhongqionghua
 * @create:2019/6/6 11:37
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
	 * 当客户端有信息发送过来时,执行该方法读取消息
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

	/**
	 * 当有客户端连接时,执行该方法
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

	/**
	 * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
	 * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
	 * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
	 * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
	 * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	/**
	 * 每当服务端收到新客户端连接时
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
	}

	/**
	 * 每当服务端收到客户端断开时
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
	}

	/**
	 * channelRead()方法执行完成之后执行该方法
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}
}