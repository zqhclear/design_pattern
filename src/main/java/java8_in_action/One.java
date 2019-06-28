package java8_in_action;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * @desc: 第一章
 * @author: zhongqionghua
 * @create: 2019/6/25 13:48
 */
public class One {
	static Map<String, BiFunction<Integer, String, Apple>> map = new HashMap<>();
	static{
		map.put("apple1", Apple::new);
		map.put("apple2", Apple::new);
	}


	public static void main(String[] args){
//		File[] file1 = new File(".").listFiles(new FileFilter() {
//			@Override
//			public boolean accept(File pathname) {
//				return pathname.isHidden();
//			}
//		});
//		System.out.println(file1.length);
//
//		File[] file2 = new File(".").listFiles(pathname -> pathname.isHidden());
//		System.out.println(file2.length);
//
//		File[] file3 = new File(".").listFiles(File::isHidden);
//		System.out.println(file3.length);

		List<Apple> inventorys = new ArrayList<>();
		//顺序处理
		List<Apple> heavyApples = inventorys.stream().filter(a -> a.getWeight() > 150)
				.collect(Collectors.toList());
		//并行处理
		List<Apple> heavyAppls2 = inventorys.parallelStream().filter(a -> a.getWeight() > 150)
				.collect(Collectors.toList());

		//简单的书写方式
		inventorys.sort(comparing((apple -> apple.getWeight())));
		//相当于下
		inventorys.sort(comparing(Apple::getWeight));

//		Trouble.doSomeThing();

		int paramRunnable = 10;
		Runnable runnable = () -> System.out.println(paramRunnable);
		//如果在这里更改值,则上面的lembda会报错,lembda表达式中必须使用的局部变量值必须要是final的 或者是本来就是final的
//		paramRunnable = 10;


		List<String> strList = new ArrayList<>(Arrays.asList("a","b","c","d"));
		strList.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
		//等价于
		strList.sort(String::compareToIgnoreCase);

		System.out.println(JSONObject.toJSONString(map.get("apple1".toLowerCase()).apply(0, "red")));
	}


}



class Trouble{
	private final int value = 4;

	public void doInt(){
		int value = 6;
		Runnable runnable = new Runnable() {
			public final int value = 5;
			@Override
			public void run() {
				int value = 10;
				System.out.println(this.value);
			}
		};
		runnable.run();
	}

	public static void doSomeThing(){
		Trouble trouble = new Trouble();
		trouble.doInt();
	}
}

class Apple{
	private Integer weight;
	private String color;

	public Apple() {
	}

	public Apple(Integer integer) {
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Apple(Integer weight, String color) {
		this.weight = weight;
		this.color = color;
	}
}

