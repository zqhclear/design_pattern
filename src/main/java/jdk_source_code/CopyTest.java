package jdk_source_code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 对象的深拷贝和浅拷贝
 * 1、容器类型（list、tuple、dict、set）的赋值、浅拷贝和深拷贝
 * <p>
 * 赋值（使用 =）
 * 赋值是将一个对象的地址赋值给一个变量，让变量指向该地址（ 旧瓶装旧酒 ）
 * 修改不可变对象（str、tuple）需要开辟新的空间
 * 修改可变对象（list等）不需要开辟新的空间
 * <p>
 * 浅拷贝（使用 copy.copy()）
 * 浅拷贝是在另一块地址中创建一个 新的变量或容器 ，但是容器内的元素的地址均是源对象的元素的地址的拷贝
 * 也就是说新的容器中的元素指向了旧的地址（ 新瓶装旧酒 ）
 * <p>
 * 深拷贝（使用 copy.deepcopy()）
 * 深拷贝是在另一块地址中创建一个 新的变量或容器，同时容器内的元素的 地址也是新开辟的 仅仅是值相同而已，是完全的副本
 * 也就是说新的容器中的元素指向了新的地址（ 新瓶装新酒 ）
 * 注意：对于容器中的 不可变元素类型，考虑到效率，依然使用原始的引用
 * <p>
 * 2、非容器类型
 * 对于非容器类型（如数字、字符串等原子类型的对象）没有被拷贝一说
 * @author: zhongqionghua
 * @create: 2019/6/5 15:52
 */
public class CopyTest implements Serializable {
	private static final long serialVersionUID = -8955888948995598555L;

	public static void main(String[] args) {
		Map<String, String> sourceMap = new HashMap<String, String>(10) {
			{
				put("key1", "value1");
				put("key2", "value2");
			}
		};
		Map<String, String> assignmentMap = sourceMap;
		System.out.println(assignmentMap == sourceMap);
		System.out.println(JSONObject.toJSONString(sourceMap));
		HashMap<String, String> cloneMap = (HashMap<String, String>) ((HashMap<String, String>) sourceMap).clone();
		System.out.println(JSONObject.toJSONString(cloneMap));
		System.out.println(cloneMap == sourceMap);

		cloneMap.put("key3", "value3");
		System.out.println(JSONObject.toJSONString(sourceMap));
		System.out.println(JSONObject.toJSONString(cloneMap));
		System.out.println(cloneMap == sourceMap);

		Map<String, CloneTest> sourceMap2 = new HashMap<String, CloneTest>(10) {
			{
				put("object1", new CloneTest("name1", "phone1"));
				put("object2", new CloneTest("name2", "phone2"));
			}
		};
		System.out.println(JSONObject.toJSONString(sourceMap2));
		HashMap<String, CloneTest> cloneMap2 = (HashMap<String, CloneTest>) ((HashMap<String, CloneTest>) sourceMap2).clone();
		System.out.println(JSONObject.toJSONString(cloneMap2));
		System.out.println(sourceMap2 == cloneMap2);

		CloneTest cloneTest2 = new CloneTest("name3", "phone3");
		cloneMap2.put("object3", cloneTest2);
		cloneMap2.put("object2", new CloneTest("name_modify","phone_modify"));
		System.out.println(JSONObject.toJSONString(sourceMap2));
		System.out.println(JSONObject.toJSONString(cloneMap2));
		System.out.println(cloneMap2 == sourceMap2);

	}
}

class CloneTest {
	private String name;
	private String phone;

	private static String static_property = "no_copy";


	public CloneTest(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
