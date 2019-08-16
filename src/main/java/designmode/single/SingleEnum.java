package designmode.single;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/15 16:28
 */
public enum SingleEnum {
	INSTANCE("单例模式-枚举方式");

	public SingleEnum getInstance(){
		return INSTANCE;
	}

	SingleEnum(String content) {
		this.content = content;
	}

	/**
	 * 内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}}
