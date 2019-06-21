package netty.netty_in_action.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import sun.rmi.runtime.Log;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/21 17:54
 */
public class LogeventEncoder extends MessageToMessageEncoder<LogEvent> {
	private final InetSocketAddress inetSocketAddress;

	public LogeventEncoder(InetSocketAddress inetSocketAddress) {
		this.inetSocketAddress = inetSocketAddress;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, LogEvent logEvent, List<Object> out) throws Exception {
		byte[] file = logEvent.getLogFile().getBytes(CharsetUtil.UTF_8);
		byte[] msg = logEvent.getMsg().getBytes(CharsetUtil.UTF_8);
		ByteBuf byteBuf = ctx.alloc().buffer(file.length + msg.length);
		byteBuf.writeBytes(file);
		byteBuf.writeByte(LogEvent.SEPARATOR);
		byteBuf.writeBytes(msg);
		out.add(new DatagramPacket(byteBuf, inetSocketAddress));
	}
}
