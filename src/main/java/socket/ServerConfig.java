package socket;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

/**
 * @Description:
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class ServerConfig implements ServerApplicationConfig {

	/**
	 * 扫描src下所有类@ServerEndPoint注解的类。
	 * @param scan
	 * @return
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scan) {
		System.out.println("扫描到"+scan.size()+"个服务端程序");
		return scan;
	}

	/**
	 * 获取所有以接口方式配置的webSocket类。
	 * @param point
	 * @return
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(
			Set<Class<? extends Endpoint>> point) {
		System.out.println("实现EndPoint接口的类数量："+point.size());
		return null;
	}

}