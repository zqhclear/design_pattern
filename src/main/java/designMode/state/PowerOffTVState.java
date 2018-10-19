package designMode.state;

/**
 * @Description: 电脑的关机状态
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/18 14:01
 */
public class PowerOffTVState implements TVState {
	public void prevChanel() {
		System.out.println("对不起，节目+失败，原因:{电脑未开机}");
	}

	public void nextChanel() {
		System.out.println("对不起，节目-失败，原因:{电脑未开机}");
	}

	public void turnUp() {
		System.out.println("对不起，音量+失败，原因:{电脑未开机}");
	}

	public void turnDown() {
		System.out.println("对不起，音量-失败，原因:{电脑未开机}");
	}
}
