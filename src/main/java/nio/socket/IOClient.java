package nio.socket;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/6 10:57
 */

import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 闪电侠
 */
public class IOClient {

	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 6,
			60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new DefaultThreadFactory("nio_socket_client_test"));

	public static void main(String[] args) {
		threadPoolExecutor.execute(new ClientSocket());
	}
}

class ClientSocket implements Runnable {
	@Override
	public void run() {
		try {
			Socket socket = new Socket("127.0.0.1", 8000);
			while (true) {
				try {
					socket.getOutputStream().write((new Date() + ": hello world").getBytes());
					socket.getOutputStream().flush();
					Thread.sleep(2000);
				} catch (Exception e) {
				}
			}
		} catch (IOException e) {
		}
	}
}