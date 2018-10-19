package designMode.state;

/**
 * @Description: 电视的操作类：电视的各种状态可以做的操作
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/18 14:02
 */
public class TVController implements TVState {
	private TVState tvState;

	public TVController() {
		this.tvState = new PowerOffTVState();
	}

	public void setOff() {
		this.tvState = new PowerOffTVState();
	}

	public void setOn() {
		this.tvState = new PowerOnTVState();
	}

	public void prevChanel() {
		tvState.prevChanel();
	}

	public void nextChanel() {
		tvState.nextChanel();
	}

	public void turnUp() {
		tvState.turnUp();
	}

	public void turnDown() {
		tvState.turnDown();
	}
}
