package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @description: 客户端,socketChannel测试
 * @author: zhongqionghua
 * @Date: 2018/12/24 14:21
 */
public class SocketClientTest {

	public static void main(String[] args) {
		SocketChannel socketChannel = null;
		try {
			//使用open方法获取一个socketChannel对象
			socketChannel = SocketChannel.open();
			//将socketChannel对象绑定端口号和ip
			socketChannel.connect(new InetSocketAddress(8680));
			//创建缓存区 1m
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
			String message = "hello,this is message from socket client";
			byteBuffer.put(message.getBytes());
			byteBuffer.flip();
			//将buffer中的数据写入channel中
			socketChannel.write(byteBuffer);

			String receiveMessage = getMessageFormSocketChannel(socketChannel);
			System.out.println("从服务端接受的数据:" + receiveMessage);
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (null != socketChannel) {
				try {
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static String getMessageFormSocketChannel(SocketChannel socketChannel) throws IOException {
		//从服务端读取数据
		ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024*1024);
		socketChannel.read(readBuffer);
		StringBuilder stringBuilder = new StringBuilder();
		readBuffer.flip();
		while (readBuffer.hasRemaining()) {
			stringBuilder.append((char) readBuffer.get());
		}
		return stringBuilder.toString();
	}
}
