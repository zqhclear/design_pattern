package programmingpearls;

import arithmetic.SortWay;
import com.alibaba.fastjson.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @description: 第一章节
 * @author: zhongqionghua
 * @Date: 2019/1/21 14:14
 */
public class FirstChapter {

	/**
	 * 使用int表示(32位)
	 */
	static final int BIT_SPERWORD = 32;
	/**
	 * int 32位对应的位运算是5(<<5,>>5)
	 */
	static final int SHIFT = 5;
	/**
	 * 掩码=31,即75是放在第三个数据的二进制的11位置,
	 */
	static final int MASK = 0x1f;
	/**
	 * 需要存储多少数据
	 */
	static final int NUM = 10000000;
	/**
	 * 需要的数组的大小
	 */
	static final int[] a = new int[(NUM - 1) / BIT_SPERWORD + 1];


	public static void main(String[] args) throws IOException {
		//createPhoneFile();

		//firstQ();

		//secondQ();

		threeQ();
	}


	/**
	 * 第三题,使用位运算测试效率
	 */
	public static void threeQ() {
		Set paramSet = new HashSet();
		for (int i = 0; i < NUM; i++) {
			paramSet.add(new Random().nextInt(NUM - 1) + 1);
		}
		System.out.println("setSize:" + paramSet.size());
		long startTime = System.currentTimeMillis();
		paramSet.stream().forEach(param -> set((int)param));
		System.out.println(System.currentTimeMillis() - startTime);

	}

	/**
	 * 第二题:使用位逻辑(与,或,位移...)来实现位向量
	 */
	public static void secondQ() {
		//设置8对应的位向量值
		set(8);
		//重置8对应的位向量值
		reset(8);
	}


	/**
	 * 第一题:快速排序算法(c中sort库所支持的)
	 */
	public static void firstQ() {
		int[] arraySort = {5, 10, 45, 98, 15, 76, 84, 12, 45};
		System.out.println(JSONObject.toJSONString(arraySort));
		System.out.println(JSONObject.toJSONString(SortWay.quickSort(arraySort, 0, arraySort.length - 1)));
	}

	/**
	 * 设置位向量值:
	 * 就是将找到i对应的位置,将位置上的位从0变为1
	 * a[]下标是从0开始的
	 *
	 * @param i
	 */
	private static void set(int i) {
		a[i >> SHIFT] |= (1 << (i & MASK));
	}

	/**
	 * 重置对应的位 位置的值,从1变为0;逻辑和上面的方法一样
	 *
	 * @param i
	 */
	private static void reset(int i) {
		a[i >> SHIFT] &= ~(1 << (i & MASK));
	}

	/**
	 * 创建号码文件
	 */
	private static void createPhoneFile() throws IOException {
		int n = 1000000;
		String filePath = "C:\\Users\\zhongqionghua\\Desktop\\test.txt";
		OutputStream outputStream = new FileOutputStream(filePath);
		StringBuilder stringBuilder = new StringBuilder();
		Set<String> phoneSet = new HashSet<>();
		for (int i = 0; i < n; i++) {
			//随机生成小于n的电话号
			int phoneNum = new Random().nextInt(n);
			stringBuilder.append(phoneNum);
		}
		outputStream.write(stringBuilder.toString().getBytes("utf-8"));
		outputStream.close();
	}

}
