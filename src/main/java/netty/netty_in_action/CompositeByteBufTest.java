package netty.netty_in_action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * @desc: CompositeByteBuf client
 * 使用bytebuffer或者bytebuf来接受http请求的head 和body
 * @author: zhongqionghua
 * @create: 2019/6/18 10:11
 */
public class CompositeByteBufTest {

	public static void main(String[] args) {
//		ByteBuffer headerBuffer = ByteBuffer.allocate(1024 * 1024);
//		ByteBuffer bodyBuffer = ByteBuffer.allocate(1024 * 1024);
//		receiveHttpRequest(headerBuffer, bodyBuffer);

		Integer a = null;
		a = null == a ? 0 : a;
		System.out.println(a+0);
	}

	/**
	 * 使用nio的bytebuffer蜡处理
	 */
	private static void receiveHttpRequest(ByteBuffer headerBuffer, ByteBuffer bodyBuffer) {
		// Use an array to hold the message parts
		ByteBuffer[] message = new ByteBuffer[]{headerBuffer, bodyBuffer};
		// Create a new ByteBuffer and use copy to merge the header and body
		ByteBuffer message2 =
				ByteBuffer.allocate(headerBuffer.remaining() + bodyBuffer.remaining());
		message2.put(headerBuffer);
		message2.put(bodyBuffer);
		message2.flip();
	}

	/**
	 * 使用compositeByteBuf来接收
	 *
	 * @param headerBuf
	 * @param bodyBuf
	 */
	private static void recieveHttpRequestByCompositeByteBuf(ByteBuf headerBuf, ByteBuf bodyBuf) {
		CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
		messageBuf.addComponents(headerBuf, bodyBuf);
		// remove the header
		messageBuf.removeComponent(0);
		for (ByteBuf buf : messageBuf) {
			System.out.println(buf.toString());
		}
	}

	private static void recieveHttpRequestByCompositeByteBuf2(ByteBuf headerBuf, ByteBuf bodyBuf){
		CompositeByteBuf compBuf = Unpooled.compositeBuffer();
		int length = compBuf.readableBytes();
		byte[] array = new byte[length];
		compBuf.getBytes(compBuf.readerIndex(), array);
//		handleArray(array, 0, array.length);
	}
}
