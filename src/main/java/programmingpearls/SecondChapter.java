package programmingpearls;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description: 第二章
 * @author: zhongqionghua
 * @Date: 2019/1/22 15:58
 */
public class SecondChapter {
	public static void main(String[] args) {
//		int sum = 1;
//		int flag = 2;
//		for (int i = 2; i < 31; i++) {
//			flag = flag * 2;
//			sum = sum + flag;
//		}
//		System.out.println(sum);

		//第一题:
		int[] array = {0, 1, 4, 2, 3, 5, 6, 7, 8, 10, 9, 12, 13, 14, 15, 11};
		//该处值定义为
		System.out.println(lostNum(array, array.length, 8));
	}

	/**
	 * 查找缺失的值:
	 *
	 * 从最高位开始,1/0,分别算起数量:
	 * 没有缺失，那么这两部分数的个数应该是相等的。
	 * 数据有缺失:那么两部分数可能相等，也可能不等
	 * 两部分相等的情况：两段都缺失，但缺失的个数相等
	 * 两部分不等的情况：一个缺一个不缺  或  都缺但缺的个数不同 
	 *
	 * @param arr     需要检测的数组
	 * @param len     需要检测的数据大小
	 * @param maxBits 该值和你需要检测的数组大小相匹配,
	 *                eg:当检测的数据量15内的话,则为4,因为4字节可以表示15内的数字
	 * @return
	 */
	public static Integer lostNum(int arr[], int len, int maxBits) {
		Integer lostNum = null;
		int MASK;
		int locZero;
		int locOne;
		//模拟文件,记录对应的位开头(1/0)的数
		int[] arrZero = new int[len];
		int[] arrOne = new int[len];
		//遍历,从最高位开始比较,一直到最低位
		for (int bit = maxBits - 1; bit >= 0; bit--) {
			locOne = 0;
			locZero = 0;
			//将mask设置为最高位:1000(代表8)
			MASK = 1 << bit;
			for (int i = 0; i < len; i++) {
				//最高位是否为1,并且使用模拟文件记录下其值
				if ((arr[i] & MASK) == MASK) {
					arrOne[locOne++] = arr[i];
				} else {
					arrZero[locZero++] = arr[i];
				}
			}
			// 如果两个文件(数组)存储的值数量不相等,则需要进行后续处理,如果想等,只能往后面移位,比较后续
			if (locOne != locZero) {
				//当首位 1数量>0数量
				if (locOne > locZero) {
					arr = arrZero;
					len = locZero;
				} else {
					if(null == lostNum){
						lostNum = new Integer(0);
					}
					lostNum += MASK;
					arr = arrOne;
					len = locOne;
				}
			}
		}
		return lostNum;
	}
}
