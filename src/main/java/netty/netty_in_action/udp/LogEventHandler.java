package netty.netty_in_action.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.awt.*;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/24 11:47
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(msg.getReceived())
				.append(" [")
				.append(msg.getInetSocketAddress())
				.append("] [")
				.append(msg.getLogFile())
				.append("] : ")
				.append(msg.getMsg());
		System.out.println(stringBuilder.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
