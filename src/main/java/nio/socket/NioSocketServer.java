package nio.socket;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/6 10:35
 */
public class NioSocketServer {

	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 6,
			60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new DefaultThreadFactory("nio_socket_test"));

	public static void main(String[] args) throws IOException {
		Selector serverSelector = Selector.open();
		Selector clientSelector = Selector.open();

		threadPoolExecutor.execute(new ServerSelector(serverSelector, clientSelector));
		threadPoolExecutor.execute(new ClientMessageRead(clientSelector));
	}
}


class ClientMessageRead implements Runnable {


	private Selector clientSelector;

	public ClientMessageRead(Selector clientSelector) {
		this.clientSelector = clientSelector;
	}
	@Override
	public void run() {
		try {
			while (true) {
				// (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
				if (clientSelector.select(1) > 0) {
					Set<SelectionKey> set = clientSelector.selectedKeys();
					Iterator<SelectionKey> keyIterator = set.iterator();
					while (keyIterator.hasNext()) {
						SelectionKey key = keyIterator.next();
						if (key.isReadable()) {
							try {
								SocketChannel clientChannel = (SocketChannel) key.channel();
								ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
								// (3) 读取数据以块为单位批量读取
								clientChannel.read(byteBuffer);
								byteBuffer.flip();
								System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
										.toString());
							} finally {
								keyIterator.remove();
								key.interestOps(SelectionKey.OP_READ);
							}
						}
					}
				}
			}
		} catch (IOException ignored) {
		}
	}
}

class ServerSelector implements Runnable {
	private Selector serverSelector;

	private Selector clientSelector;

	public ServerSelector(Selector serverSelector, Selector clientSelector) {
		this.serverSelector = serverSelector;
		this.clientSelector = clientSelector;
	}

	@Override
	public void run() {
		try {
			// 对应IO编程中服务端启动
			ServerSocketChannel listenerChannel = ServerSocketChannel.open();
			listenerChannel.socket().bind(new InetSocketAddress(8000));
			listenerChannel.configureBlocking(false);
			listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
			while (true) {
				// 监测是否有新的连接，这里的1指的是阻塞的时间为1ms
				if (serverSelector.select(1) > 0) {
					Set<SelectionKey> set = serverSelector.selectedKeys();
					Iterator<SelectionKey> keyIterator = set.iterator();
					while (keyIterator.hasNext()) {
						SelectionKey key = keyIterator.next();
						if (key.isAcceptable()) {
							try {
								// (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
								SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
								clientChannel.configureBlocking(false);
								clientChannel.register(clientSelector, SelectionKey.OP_READ);

								//向客户端发送消息
								String message = "hello, this is serverSocketChannel.";
								ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
								byteBuffer.put(message.getBytes());
								byteBuffer.flip();
								clientChannel.write(byteBuffer);
							} finally {
								keyIterator.remove();
							}
						}
					}
				}
			}
		} catch (IOException ignored) {
		}
	}
}