package designmode.state;

/**
 * @Description: 状态接口：电视机
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/18 13:56
 */
public interface TVState {

	/**
	 * 节目+
	 */
	void prevChanel();

	/**
	 * 节目-
	 */
	void nextChanel();

	/**
	 * 音量+
	 */
	void turnUp();

	/**
	 * 音量-
	 */
	void turnDown();

}
