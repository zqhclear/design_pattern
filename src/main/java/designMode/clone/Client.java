package designMode.clone;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) throws Exception {
		Sleep sleep = new Sleep(10, "睡觉");
		List<String> strList = new ArrayList<String>();
		strList.add("one");
		strList.add("two");
		sleep.setStrList(strList);
		Sleep cloneSleep = sleep.clone();
		System.out.println(cloneSleep.getHour() + ":" + cloneSleep.getName());
		System.out.println("list:" + cloneSleep.getStrList());
		
		
	}
}
