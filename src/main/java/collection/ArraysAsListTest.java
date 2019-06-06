package collection;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc: arrays.asList()的弊端
 * @author: zhongqionghua
 * @create: 2019/6/6 9:43
 */
public class ArraysAsListTest {
	public static void main(String[] args) {
		//demo1:将基本类型数组当做参数时,会有问题,这里会当做int[]数组
		int[] arr = {1, 2, 3};
		List list = Arrays.asList(arr);
		System.out.println(list.size());
		System.out.println(JSONObject.toJSONString(list));
		//这里和上面的区别是这边会自动装箱,变成Integer[]数组
		List list1_2 = Arrays.asList(1, 2, 3);
		System.out.println(list1_2.size());
		System.out.println(JSONObject.toJSONString(list1_2));

		//demo2:非基本类型当做参数没事,但是需要注意是引用赋值而已,修改数据都会改变
		String[] strArr = {"欢迎", "关注", "java"};
		List list2 = Arrays.asList(strArr);
		System.out.println(list2.size());
		strArr[1] = "爱上";
		list2.set(2, "我");
		System.out.println(JSONObject.toJSONString(strArr));
		System.out.println(JSONObject.toJSONString(list2));

		//demo3:错误使用方式 asList返回的是java.util.arrays.ArrayList对象
		//该类是没有集成abstractList的,所以没有一些方法
		try {
			String[] strArr2 = {"欢迎", "关注", "java"};
			List list3 = Arrays.asList(strArr2);
			System.out.println(JSONObject.toJSONString(list3));
			list3.add("新增一个试试看");
			list3.remove("关注");
		}catch (UnsupportedOperationException e){
			e.getMessage();
		}

		//正确的使用方式
		//1.
		int[] intArr = {1,2,3};
		List list4 = Arrays.stream(intArr).boxed().collect(Collectors.toList());
		System.out.println(JSONObject.toJSONString(list4));
		//2.
		List list4_2 = new ArrayList(Arrays.asList(intArr));
		System.out.println(JSONObject.toJSONString(list4_2));

	}
}
