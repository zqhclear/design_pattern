package demo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Description: 描述
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/18 15:25
 */
public class Test {
	public static void main(String[] args){
		JSONObject json = new JSONObject();
		json.put("borrowNo", 1231232L);
		json.put("currentPage", 1);
		System.out.println(json);

		int i = 5;
		modifyiInt(i);
		System.out.println(i);

		modifyJSON(json);
		System.out.println(json);


	}

	public static void modifyJSON(JSONObject jsonDemo){
		jsonDemo.put("demo", "demo");
		System.out.println(jsonDemo);
	}

	public static void modifyiInt(int i){
		i = i * 10;
		System.out.println(i);
	}




}
