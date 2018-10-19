package designMode.state;

/**
 * @Description: 电脑的开机状态
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/18 13:59
 */
public class PowerOnTVState implements TVState {

	public void prevChanel() {
		System.out.println("好的，节目+成功");
	}

	public void nextChanel() {
		System.out.println("好的，节目-成功");
	}

	public void turnUp() {
		System.out.println("好的，音量+成功");
	}

	public void turnDown() {
		System.out.println("好的，音量+成功");
	}
}
