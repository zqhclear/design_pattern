package java8_in_action;

import java.util.Optional;

/**
 * @desc: optional测试
 * @author: zhongqionghua
 * @create: 2019/6/28 15:35
 */
public class OptionalTest {

	public static void main(String[] args){
//		Optional optional = Optional.of(null);
//		Optional optional2 = Optional.ofNullable(null);
//		testMap();
		testFilter();
	}

	private static void testFilter(){
		Optional<People> peopleOptional = Optional.ofNullable(null);
		peopleOptional.filter(item -> "".equals(item.getName()))
				.ifPresent(item -> System.out.println(item.getName()));
	}

	private static void testMap(){
		Optional<People> peopleOptional = Optional.ofNullable(null);
		Optional<String> peopleName = peopleOptional.map(People::getName);
//		System.out.println(peopleName.get());
		System.out.println(peopleName.orElse("你要的值为null,我是默认值"));
		System.out.println(peopleName.orElseGet(() -> {
			System.out.println("你所要的值为null");
			return "defaule value";
		}));

		peopleName.ifPresent(item -> System.out.println("this is item:" + item));

		System.out.println(peopleName.orElseThrow(() -> new IllegalArgumentException("this is new exception")));
	}
}


class People{
	private String Name;
	private String color;
	private Integer weight;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
