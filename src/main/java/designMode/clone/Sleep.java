package designMode.clone;

import java.util.List;

public class Sleep implements Cloneable {
	private int hour;
	private String name;
	private List<String> strList;
	
	
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public Sleep clone() throws CloneNotSupportedException {
        Sleep obj=(Sleep)super.clone();//直接调用Object对象的clone方法
        obj.strList = this.strList;
        return obj;
    }
	
	public Sleep(Integer hour, String name){
		this.hour = hour;
		this.name = name;
	}
}
