package netty.netty_in_action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.local.LocalChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @desc: channelFuture组件测试
 * @author: zhongqionghua
 * @create: 2019/6/14 15:13
 */
public class ChannelFutureTest {

	public static void main(String[] args) {
		Channel channel = new LocalChannel();
		// Does not block
		ChannelFuture future = channel.connect(
				new InetSocketAddress("192.168.0.1", 25));
		future.addListener((ChannelFutureListener) future1 -> {
			if (future1.isSuccess()) {
				ByteBuf buffer = Unpooled.copiedBuffer(
						"Hello", Charset.defaultCharset());
				ChannelFuture wf = future1.channel()
						.writeAndFlush(buffer);
			} else {
				Throwable cause = future1.cause();
				cause.printStackTrace();
			}
		});
	}
}
