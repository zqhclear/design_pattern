package nio.selector;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @description: selector测试类
 * @author: zhongqionghua
 * @Date: 2018/12/24 16:44
 */
public class SelectorTest {

	public static void main(String[] args){
		Selector selector = null;
		try {
			selector = Selector.open();
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.register(selector, SelectionKey.OP_READ);



		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != selector){
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
}
