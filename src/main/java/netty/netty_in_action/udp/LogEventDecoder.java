package netty.netty_in_action.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/24 11:36
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
	@Override
	protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
		ByteBuf dataBuf = datagramPacket.content();
		int index = dataBuf.indexOf(0, dataBuf.readableBytes(), LogEvent.SEPARATOR);
		String fileName = dataBuf.slice(0, index).toString(CharsetUtil.UTF_8);
		String logMsg = dataBuf.slice(index + 1, dataBuf.readableBytes()).toString(CharsetUtil.UTF_8);

		LogEvent logEvent = new LogEvent(datagramPacket.sender(), fileName, logMsg, System.currentTimeMillis());
		out.add(logEvent);
	}
}
