package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @desc: netty的socket-client
 * @author: zhongqionghua
 * @create: 2019/6/6 11:41
 */
public class NettySocketClient {

	protected static ThreadPoolExecutor threadPoolExecutorClient = new ThreadPoolExecutor(2, 4,
			30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("netty_client_thread"));

	private static String ip = "127.0.0.1";

	public static void main(String[] args) {
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(workerGroup).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) {
						// 添加业务处理逻辑  可以添加自定义的业务处理逻辑也可以添加    Netty自带的简单通用的处理逻辑
						ch.pipeline().addFirst(new ClientInHandler());
					}
				});
		connect(bootstrap, ip, NettySocketServer.PORT, 5);
	}

	/**
	 * 连接服务端
	 *
	 * @param bootstrap
	 * @param IP
	 * @param port
	 * @param maxRetry   重试次数
	 * @param retryIndex
	 */
	private static void connect(Bootstrap bootstrap, String IP, int port, int maxRetry, int... retryIndex) {
		bootstrap.connect(IP, port).addListener(future -> {
			int[] finalRetryIndex;
			if (future.isSuccess()) {
				System.out.println("客户端连接【" + IP + ":" + port + "】成功");
			} else if (maxRetry == 0) {
				System.out.println("达到最大重试次数，放弃重试");
			} else {
				// 初始化 重试计数
				if (retryIndex.length == 0) {
					finalRetryIndex = new int[]{0};
				} else {
					finalRetryIndex = retryIndex;
				}
				// 计算时间间隔
				int delay = 1 << finalRetryIndex[0];
				// 执行重试
				System.out.println(new Date() + " 连接失败，剩余重试次数：" + maxRetry + "," + delay + "秒后执行重试");
				bootstrap.config().group().schedule(() -> {
					connect(bootstrap, IP, port, maxRetry - 1, finalRetryIndex[0] + 1);
				}, delay, TimeUnit.SECONDS);
			}
		});
	}
}

class ClientInHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		String message = ((ByteBuf) msg).toString(Charset.forName(NettySocketServer.CHARSET));
		if(new Random().nextInt(1) < 10){
			throw new RuntimeException();
		}
		System.out.println(System.currentTimeMillis() + ": client receive: ->" + message);
	}

	/**
	 * 如果想要不断发送消息,需要新启动线程
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettySocketClient.threadPoolExecutorClient.execute(new SendMessageThread(ctx));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("read方法读取完成了");
	}

	/**
	 * 出错走这个,但是还是会走channelReadComplete
	 *
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("read方法出错了ya:" + cause.getMessage());

	}
}

class SendMessageThread implements Runnable {

	private ChannelHandlerContext ctx;

	public SendMessageThread(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void run() {
		while (true) {
			String content = "hello netty_server, i am client" + System.currentTimeMillis();
			ByteBuf buffer = NettySocketServer.getByteBuf(ctx, content);
			ctx.channel().writeAndFlush(buffer);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

