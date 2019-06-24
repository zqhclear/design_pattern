package netty.netty_in_action.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.util.RandomAccess;

/**
 * @desc: udp广播类
 * @author: zhongqionghua
 * @create: 2019/6/24 10:59
 */
public class LogEventBroadCaster {

	private final EventLoopGroup eventLoopGroup;
	private final Bootstrap bootstrap;
	private final File file;

	public static void main(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("参数错误,请输入正确的两个参数值.eg:9999,c://file");
		}
		LogEventBroadCaster logEventBroadCaster = new LogEventBroadCaster(new InetSocketAddress(
				"127.0.0.1", Integer.parseInt(args[0])), new File(args[1]));
		try{
			logEventBroadCaster.run(Integer.parseInt(args[0]));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logEventBroadCaster.stop();
		}

	}

	public LogEventBroadCaster(InetSocketAddress inetSocketAddress, File file) {
		this.eventLoopGroup = new NioEventLoopGroup();
		this.file = file;
		this.bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup).channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler(new LogeventEncoder(inetSocketAddress));
	}

	public void run(int port) throws Exception {
		Channel channel = bootstrap.bind(0).sync().channel();
		//记录读取的文件指针
		long pointer = 0;
		//使用while循环+pointer+file.length来判断文件是否还有读取的元素
		while (true) {
			long len = file.length();
			if (len < pointer) {
				//file was reset
				pointer = len;
			} else if (len > pointer) {
				//content was added
				RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
				randomAccessFile.seek(pointer);
				String line;
				while (null != (line = randomAccessFile.readLine())) {
					line = new String(line.getBytes(CharsetUtil.ISO_8859_1), CharsetUtil.UTF_8);
					channel.writeAndFlush(new LogEvent(null, file.getAbsolutePath(), line, -1));
				}
				pointer = randomAccessFile.getFilePointer();
				randomAccessFile.close();
			}
			try {
				//每1秒循环查询文件是否更改了元素
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
		}
	}

	public void stop() {
		eventLoopGroup.shutdownGracefully();
	}
}
