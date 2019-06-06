package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @description: serverSocketChannel 服务端
 * @author: zhongqionghua
 * @Date: 2018/12/24 14:21
 */
public class SocketServerTest {

	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel = null;
		SocketChannel socketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			//通过serverSocketChannel绑定服务端端口号,下面两个方法有什么区别
			//serverSocketChannel.bind(new InetSocketAddress(8580));
			serverSocketChannel.socket().bind(new InetSocketAddress(8680));
			socketChannel = serverSocketChannel.accept();

			//给客户端发送的消息
			String message = "hello, this is serverSocketChannel.";
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
			byteBuffer.put(message.getBytes());
			byteBuffer.flip();
			socketChannel.write(byteBuffer);

			String receiveMessage = SocketClientTest.getMessageFormSocketChannel(socketChannel);
			System.out.println("从客户端接收数据:" + receiveMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != serverSocketChannel) {
				try {
					socketChannel.close();
					serverSocketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
