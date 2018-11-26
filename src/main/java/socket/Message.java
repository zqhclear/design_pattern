package socket;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 消息实体
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 712220979519674L;
	private  String  alert;

	private  List<String>  names;

	private  String  sendMsg;

	private String  from;

	private String  date;


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public Message() {
		super();
	}
}