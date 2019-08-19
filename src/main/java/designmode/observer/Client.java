package designmode.observer;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.lang.Collections;

import java.util.HashMap;
import java.util.Map;

/**
 * 观察者模式
 * 在对象之间定义了一对多的依赖，这样一来，当一个对象改变状态，依赖它的对象会收到通知并自动更新。
 * 其实就是发布订阅模式，发布者发布信息，订阅者获取信息，订阅了就能收到信息，没订阅就收不到信息。
 * <p>
 * 事例:发布-订阅者模式
 *
 * @author zhongqionghua
 * @desc
 * @date 2018年4月12日
 */
public class Client {
	public static void main(String[] args) {
		Subject subject = new MySubjectImpl();
		subject.addObserver(new ObserverOne());
		subject.addObserver(new ObserverTwo());
		subject.doByMySelf();

		Map<String, Integer> map = new HashMap<String, Integer>(5) {
			{
				put("month", null);
			}
		};
		Integer monthInt = map.get("month");
		System.out.println(monthInt);

		Integer income = null;
		Integer tmp = null;
		System.out.println((null == income ? 0 : income) + (null == tmp ? 0 : tmp));


		Integer str = null;
		System.out.println(str + "123");
		System.out.println(str);
		System.out.println("null".equals(str));


		Map<String, String> testMap = new HashMap<String, String>() {
			{
				put("month", "value1");
				put("day", "value2");
			}
		};
		Map<String, String> testMap2 = null;
		if (!Collections.isEmpty(testMap2 = testMap)) {
			System.out.println(JSONObject.toJSONString(testMap2));
		}


	}
}
