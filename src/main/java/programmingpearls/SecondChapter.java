package programmingpearls;

import com.alibaba.fastjson.JSONObject;

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

//		//第一题:
//		int[] arrayOne = {0, 1, 4, 2, 3, 5, 6, 7, 8, 10, 9, 12, 13, 14, 15, 11};
//		//该处值定义为
//		System.out.println(lostNum(arrayOne, arrayOne.length, 8));


		//第二题
		char[] arrayTwo = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
		//rotate1(arrayTwo, 1);
		rotate2(arrayTwo, 3);
		//System.out.println(gcdNum(15, 10));
		System.out.println(JSONObject.toJSONString(arrayTwo));

	}

	/**
	 * 杂耍方式位移:
	 * 直观的想法：由于要对数组整体向左循环移动k位，
	 * 那么我们就可以对数组中的每一个元素向左移动k位(超过数组长度的可以取模回到数组中)，此时总是能获得结果的。
	 * <p>
	 * 步骤：(k表示循环移动的位数)
	 * 1)先将x[0]移到临时变量t中
	 * 2)将x[k]移动到x[0]中，x[2k]移动到x[k]中，依次类推
	 * 3)将x中的所有下标都对n取模，直到我们又回到从x[0]中提取元素。不过这时我们从t中提取元素，结束。
	 * 循环的终止条件：当我们要从循环的起始位置点中提取元素时，此次循环结束
	 * 由于k，2k...之间的偏移量是相同的，所以整个操作实际上就是讲序列向左移动k个位置
	 * 注意：从下标0开始，按照上述步骤移动位置时，一次循环并不一定能够把所有数移到目标位置。这还与n和k是否互质有关
	 * 如果，n与k互质，从0开始，每一个元素向前移动k个位置，一次循环就可以处理完所有元素，最后一个元素会从0位置取元素
	 * 如果，n与k不互质，仅仅从0开始，每次向前移动k个位置。终止时是不能把所有元素放到目的地的。
	 * 这是要需要进行gcd(n，k)次循环。即第一次是从0开始，每次向前移动k个位置，直至循环结束。第二次是从1开始，
	 * 每次向前移动k个位置，直至循环结束。第三次...直到第gcd(n，k)-1次。而且每次循环的最后一个元素都会回到该循环的起点
	 * 我们这里把包含gcd(n，k)的元素称为一段，可以看出程序需要进行gcd(n，k)循环才能够把所有数移到目标位置
	 *
	 * @param array
	 * @param rotateNum
	 */
	private static void rotate2(char[] array, int rotateNum) {
		//求array.length和位移数的最大公约数
		//gcdNum标志着一次for循环移动多少位
		int gcdNum = gcdNum(array.length, rotateNum);
		for (int i = 0; i < gcdNum; i++) {
			//first将会记录你最终的需要用tmp替换的位置
			int first = i;
			int next = (first + rotateNum) % array.length;
			//每段的起始位置，注意不能写成0
			char tmp = array[i];
			int forEarchNum = 0;
			while (next != i) {
				array[first] = array[next];
				first = next;
				next = (first + rotateNum) % array.length;
				forEarchNum++;
			}
			//临时变量中存储每一趟的循环的最后一个字符
			array[first] = tmp;
			System.out.println(forEarchNum);
		}
	}

	/**
	 * 求m,n得最大公约数
	 * 辗转相除法求最大公约数:两个整数的最大公约数等于其中较小的数和两数的差的最大公约数
	 *
	 * @param m
	 * @param n
	 * @return
	 */
	private static int gcdNum(int m, int n) {
		//确定m和n的大小
		if (m < n) {
			int temp = m;
			m = n;
			n = temp;
		}
		if(n == 0){
			return 1;
		}
		//如果能整除,则返回n
		if (m % n == 0) {
			return n;
		}
		//递归查找
		return gcdNum(n, m % n);
	}

	/**
	 * 循环位移字符串:
	 * 该方法和collections.rotate方法有很大不同,
	 * collections.rotate:对于abcde,按照2重新排序,结果为 deabc
	 * 该方法(循环位移):abcde->cdeab
	 *
	 * @param array
	 * @param rotateNum
	 */
	private static void rotate1(char[] array, int rotateNum) {
		swapArray(array, 0, rotateNum - 1);
		swapArray(array, rotateNum, array.length - 1);
		swapArray(array, 0, array.length - 1);
	}

	/**
	 * 从start位置开始,依次替换其对应的end位置,直到end位置
	 *
	 * @param array
	 * @param start
	 * @param end
	 */
	private static void swapArray(char[] array, int start, int end) {
		int mid = (start + end) / 2;
		for (int i = start, j = end; i <= mid; i++, j--) {
			char temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	/**
	 * 第一题,查找缺失的值:
	 * <p>
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
	private static Integer lostNum(int arr[], int len, int maxBits) {
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
					if (null == lostNum) {
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
