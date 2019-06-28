package java8_in_action;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @desc: 流测试
 * @author: zhongqionghua
 * @create: 2019/6/26 14:22
 */
public class StreamTest {

	static List<Dish> dishDemoList = new ArrayList<>(
			Arrays.asList(new Dish("鱼", 2500),
					new Dish("牛肉", 3000),
					new Dish("青菜", 500),
					new Dish("排骨", 2000)));

	public static void main(String[] args) {
//		streamFirst();
//
//		onlyOnceConsumer();
//
//		streamDelayering();
//
//		questionOne();
//
//		questionTwo();
//
//		questionThree();
//
//		findAnyTest();
//
//		reduceTest();
//
//		questionFour();
//
//		pythagoreanNumber();

//		fileStreamTest();

//		iterateTest();

//		questionFive();

//		streamGenerate();

//		groupByTest();

		collectorsTest();
	}

	/**
	 * collectors工具测试
	 */
	private static void collectorsTest() {
		Optional<Dish> optionalMaxDish = dishDemoList.stream().collect(maxBy(comparingInt(Dish::getCalories)));
		System.out.println(JSONObject.toJSONString(optionalMaxDish.get()));

		Optional<Dish> optionalMinDish = dishDemoList.stream().collect(minBy(comparing(Dish::getCalories)));
		System.out.println(JSONObject.toJSONString(optionalMinDish));

		int totalCalories = dishDemoList.stream().collect(summingInt(Dish::getCalories));
		System.out.println(totalCalories);

		double avgCalories = dishDemoList.stream().collect(averagingInt(Dish::getCalories));
		System.out.println("avg:" + avgCalories);

		IntSummaryStatistics intSummaryStatistics = dishDemoList.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println(JSONObject.toJSONString(intSummaryStatistics));

		System.out.println(dishDemoList.stream()
				.map(Dish::getName)
				.collect(joining()));

		System.out.println(dishDemoList.stream()
				.map(Dish::getName)
				.collect(joining(",")));

		System.out.println(dishDemoList.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j)));
		//相当于
		System.out.println(dishDemoList.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j));
		//简化
		System.out.println(dishDemoList.stream().map(Dish::getCalories).reduce(0, Integer::sum));
		//相当于
		System.out.println(dishDemoList.stream().mapToInt(Dish::getCalories).sum());

//		Map<Integer, List<Dish>> map = dishDemoList.stream()
//				.collect(groupingBy(dish -> {
//
//				}));
	}


	private static void groupByTest() {
		List<Dish> dishList = new ArrayList<>(
				Arrays.asList(new Dish("鱼", 2500),
						new Dish("牛肉", 3000),
						new Dish("青菜", 500),
						new Dish("排骨", 2000)));
		Map<Integer, List<Dish>> result = dishList.stream().collect(groupingBy(Dish::getCalories));
		System.out.println(JSONObject.toJSONString(result));
	}

	/**
	 * Stream.generate方法测试
	 */
	private static void streamGenerate() {
		Stream.generate(() -> new Random().nextInt(100))
				.filter(item -> item > 20)
				.limit(20)
				.forEach(System.out::println);
	}

	/**
	 * 斐波那契数组
	 */
	private static void questionFive() {
		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
				.limit(20)
				.forEach(item -> System.out.println(JSONObject.toJSONString(item)));
	}

	/**
	 * 无限流,生成无限个元素
	 * 注:要记得使用limit限定元素的个数,否则会无限循环下去
	 */
	private static void iterateTest() {
		Stream.iterate(1, n -> n * 2)
				.limit(10)
				.forEach(System.out::println);
	}

	/**
	 * 文件流
	 */
	private static void fileStreamTest() {
		String path = "C:\\Users\\zhongqionghua\\Desktop\\小贷字样替换-mongo索引创建.txt";
		try {
			Stream<String> fileStream = Files.lines(Paths.get(path), CharsetUtil.UTF_8);
			long wordDistinctCount = fileStream
					.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
			System.out.println(wordDistinctCount);

			Files.lines(Paths.get(path), CharsetUtil.UTF_8)
//					.flatMap(line -> Arrays.stream(line.split(" ")))
					.forEach(item -> System.out.println(item));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建勾股数a*a+b*b=c*c
	 */
	private static void pythagoreanNumber() {
		IntStream.rangeClosed(1, 100)
				//下面的flatMap会返回int[]对象,所以需要装箱操作,转成stream<Integer>
				.boxed()
				//该处从a往后生成数字,防止重复数组
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
				.limit(5)
				.forEach(item -> System.out.println(JSONObject.toJSONString(item)));

		//优化
		IntStream.rangeClosed(1, 100)
				//下面的flatMap会返回int[]对象,所以需要装箱操作,转成stream<Integer>
				.boxed()
				//该处从a往后生成数字,防止重复数组
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
				.filter(sqrt -> sqrt[2] % 1 == 0)
				.limit(5)
				.forEach(item -> System.out.println(JSONObject.toJSONString(item)));

	}


	private static void questionFour() {
		List<Dish> dishList = new ArrayList<>(
				Arrays.asList(new Dish("鱼", 2500),
						new Dish("牛肉", 3000),
						new Dish("青菜", 500),
						new Dish("排骨", 2000)));
		int count = dishList.stream()
				.map(item -> 1)
				.reduce(0, Integer::sum);
		//等价于
//		long count = dishList.stream()
//				.count();
		System.out.println(count);
	}

	private static void reduceTest() {
		List<String> str = new ArrayList<>(Arrays.asList("asdfad", "asdfasdf", "a", "b", "c"));
		int wordLengthSum = str.stream()
				.map(String::length)
				//identity参数表示初始值,其操作:第一次a=identity,b=x[0],其结果值为下次计算的a
//				.reduce(0, (a, b) -> a + b);
				//等价于
				.reduce(0, Integer::sum);

		Optional<Integer> wordLengthSum2 = str.stream()
				.map(String::length)
				//identity参数表示初始值,其操作:第一次a=identity,b=x[0],其结果值为下次计算的a
//				.reduce(0, (a, b) -> a + b);
				//等价于
				.reduce(Integer::sum);

		int maxNumber = str.stream()
				.map(String::length)
				//identity参数表示初始值,其操作:第一次a=identity,b=x[0],其结果值为下次计算的a
//				.reduce(0, (a, b) -> a + b);
				//等价于
				.reduce(0, Integer::max);

		Optional<Integer> maxNumber2 = str.stream()
				.map(String::length)
				//identity参数表示初始值,其操作:第一次a=identity,b=x[0],其结果值为下次计算的a
//				.reduce(0, (a, b) -> a + b);
				//等价于
				.reduce(Integer::max);
		System.out.println(wordLengthSum + "::" + wordLengthSum2.get());
		System.out.println(maxNumber + ":::" + maxNumber2.get());

	}

	/**
	 * findAny和findFirst的区别
	 * 在stream并行的时候有差别 :
	 * findFirst在并行的时候限制很多,但是能保证返回的是第一个(例如list这种有顺序的集合)
	 * findAny在并行的时候限制很少,但是不保证获取第一个
	 */
	private static void findAnyTest() {
		List<String> str = new ArrayList<>(Arrays.asList("asdfad", "asdfasdf", "a", "b", "c"));
		Optional<String> result = str.stream().filter(item -> item.length() > 5)
				.findAny();
		System.out.println(JSONObject.toJSONString(result));
	}

	private static void questionThree() {
		List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Integer> numbers2 = new ArrayList<>(Arrays.asList(3, 4));
//		List<int[]> addNumbers = numbers1.stream()
//				.flatMap(num1 -> numbers2.stream().map(num2 -> new int[]{num1, num2}))
//				.filter(item -> (item[0] + item[1])%3==0)
//				.collect(toList());
		List<int[]> addNumbers = numbers1.stream()
				.flatMap(num1 -> numbers2.stream()
						.filter(num2 -> (num1 + num2) % 3 == 0)
						.map(num2 -> new int[]{num1, num2}))
				.collect(toList());
		System.out.println(JSONObject.toJSONString(addNumbers));
	}

	private static void questionOne() {
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		List<Integer> squareNumbers = numbers.stream()
				.map(item -> item * item)
				.collect(toList());
		System.out.println(JSONObject.toJSONString(squareNumbers));
	}

	private static void questionTwo() {
		List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Integer> numbers2 = new ArrayList<>(Arrays.asList(3, 4));
		List<int[]> addNumbers = numbers1.stream()
				.flatMap(num1 -> numbers2.stream().map(num2 -> new int[]{num1, num2}))
				.collect(toList());
		System.out.println(JSONObject.toJSONString(addNumbers));
	}

	/**
	 * 将给定的List<String>中的元素拆开为单个字符,去重输出
	 */
	private static void streamDelayering() {
		//数组流
		List<String> wordList = new ArrayList<>(Arrays.asList("hello", "world"));
		List<String[]> strings = wordList.stream()
				.map(str -> str.split(""))
				.distinct()
				.collect(toList());
		System.out.println(JSONObject.toJSONString(strings));

		//字符流
		List strings2 = wordList.stream()
				.map(str -> str.split(""))
				//将生成的单个流合并起来
				.flatMap(Arrays::stream)
				.distinct()
				.collect(toList());
		System.out.println(JSONObject.toJSONString(strings2));
	}

	private static void onlyOnceConsumer() {
		List<String> codeList = new ArrayList<>(Arrays.asList("java", "c", "c++", "c#", "python", "go"));
		Stream stream = codeList.stream();
		stream.forEach(System.out::println);
		//流只能被消费一次,当再次被消费时,报错
		// 如果需要再次被消费,则在从codeList(源)中获取,但是如果是io流的话 就没有办法了
//		stream.forEach(item -> {
//			String s = JSONObject.toJSONString(item);
//			System.out.println(s);
//		});
	}

	private static void streamFirst() {
		List<Dish> dishList = new ArrayList<>(
				Arrays.asList(new Dish("鱼", 2500),
						new Dish("牛肉", 3000),
						new Dish("青菜", 500),
						new Dish("排骨", 2000)));
		List<String> dishNameList = dishList.stream()
				//热量<4000
				.filter(dish -> dish.getCalories() < 4000)
				//按照热量升序
				.sorted(comparing(Dish::getCalories))
				//截断流,使元素不超过四个
				.limit(4)
				//获取菜的名字
				.map(Dish::getName)
				//变为list(ArrayList)
				.collect(toList());
		System.out.println(dishNameList);
		System.out.println(JSONObject.toJSONString(dishList));
	}
}

class Dish {
	private String name;
	private Integer calories;
	private String type;
	private boolean vegetarian;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Dish(String name, Integer calories) {
		this.name = name;
		this.calories = calories;
	}

	public Dish(String name, Integer calories, String type, boolean vegetarian) {
		this.name = name;
		this.calories = calories;
		this.type = type;
		this.vegetarian = vegetarian;
	}

	@Override
	public String toString() {
		return "Dish{" +
				"name='" + name + '\'' +
				'}';
	}
}
