package netty.netty_in_action.udp;

import java.net.InetSocketAddress;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/6/21 17:25
 */
public final class LogEvent {

	public static final byte SEPARATOR = (byte)':';

	private final InetSocketAddress inetSocketAddress;

	private final String logFile;
	private final String msg;
	private final long received;

	public LogEvent(String logFile, String msg) {
		this(null, logFile, msg, -1);
	}

	public LogEvent(InetSocketAddress inetSocketAddress, String logFile, String msg, long received) {
		this.inetSocketAddress = inetSocketAddress;
		this.logFile = logFile;
		this.msg = msg;
		this.received = received;
	}

	public InetSocketAddress getInetSocketAddress() {
		return inetSocketAddress;
	}

	public String getLogFile() {
		return logFile;
	}

	public String getMsg() {
		return msg;
	}

	public long getReceived() {
		return received;
	}
}
