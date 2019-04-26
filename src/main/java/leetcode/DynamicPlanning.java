package leetcode;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @desc: 动态规划
 * @author: zhongqionghua
 * @create: 2019/4/26 10:55
 */
public class DynamicPlanning {

	public static void main(String[] args) {
//		System.out.println(fibonacciOfRecursion(8));
//		System.out.println(fibonacciOfDynamicPlanningTopToDown(8));
//		System.out.println(fibonacciOfDynamicPlanningDownToTop(8));
//		System.out.println(optimizeFibonacciOfDynamicPlanningDownToTop(8));
//
//		//动态规划-线性模型: 切钢材问题
//		int[] prices = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 20, 24, 30};
//		System.out.println(cutRolledSteelOfRecursion(prices, 4));
//		System.out.println(cutRolledSteelOfDynamicPlanningTopToDown(prices, 4));
//		System.out.println(cutRolledSteelOfDynamicPlanningDownToTop(prices, 4));

		//动态规划-线性模型: 小朋友过桥最短时间
		int[] peopleSpeeds = new int[]{2, 1, 10, 5};
		System.out.println(getMinTimeOfCrossBridge(peopleSpeeds));


	}

	/**
	 * 取过桥最短时间:
	 * <p>
	 * 我们先将所有人按花费时间递增进行排序，假设前i个人过河花费的最少时间为opt[i]，那么考虑前i-1个人过河的情况，
	 * 即河这边还有1个人，河那边有i-1个人，并且这时候手电筒肯定在对岸，所以opt[i] = opt[i-1] + a[1] + a[i] (让花费时间
	 * 最少的人把手电筒送过来，然后和第i个人一起过河)如果河这边还有两个人，一个是第i号，另外一个无所谓，河那边有i-2个人，
	 * 并且手电筒肯定在对岸，所以opt[i] = opt[i-2] + a[1] + a[i] + 2*a[2] (让花费时间最少的人把电筒送过来，然后第i个人和
	 * 另外一个人一起过河，由于花费时间最少的人在这边，所以下一次送手电筒过来的一定是花费次少的，送过来后花费最少的和花
	 * 费次少的一起过河，解决问题)
	 * 所以 opt[i] = min{opt[i-1] + a[1] + a[i] , opt[i-2] + a[1] + a[i] + 2*a[2] }
	 *
	 * @param peopleSpeeds
	 * @return
	 */
	private static int getMinTimeOfCrossBridge(int[] peopleSpeeds) {
		//将初始数据排序
		Arrays.sort(peopleSpeeds);
		//保存
		int[] cacheSpeeds = new int[peopleSpeeds.length + 1];
		for (int i = 0; i < peopleSpeeds.length; i++) {
			cacheSpeeds[i] = peopleSpeeds[i];
		}
		int minTime = 0;
		//  没有人的情况下
		if (peopleSpeeds.length == 0) {
			return 0;
		}
		//  有一个人时，返回这个人的时间
		if (peopleSpeeds.length == 1) {
			return peopleSpeeds[peopleSpeeds.length - 1];
		}
		//  有两个人时，返回用时较多的人的时间
		if (peopleSpeeds.length == 2) {
			return Math.max(peopleSpeeds[peopleSpeeds.length - 1], peopleSpeeds[peopleSpeeds.length - 2]);
		}
		/**
		 * 人数多于2时，采用动态规划的思想，将问题拆分
		 * 假设河的这一侧还剩一人，则派遣最快的人往返
		 * 假设河的这一侧还剩二人，先派遣花费时间最少的人过来，待这两人走后，花费时间次少的人过来携带最少的人一同回去
		 */
		for (int i = 2; i < peopleSpeeds.length; i++) {
			cacheSpeeds[i] = Math.min(cacheSpeeds[i - 1] + peopleSpeeds[0] + peopleSpeeds[i], cacheSpeeds[i - 2] + peopleSpeeds[0] + peopleSpeeds[i] + 2 * peopleSpeeds[1]);
			minTime = cacheSpeeds[i];
		}
		return minTime;
	}


	/**
	 * 自底向上求钢材切割
	 *
	 * @param prices
	 * @param n
	 * @return
	 */
	private static int cutRolledSteelOfDynamicPlanningDownToTop(int[] prices, int n) {
		int[] cacheCut = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int maxPrice = -1;
			//该处是计算当钢材的长度为i时,其最大的价值,以便上层循环后期使用(其长度为i的钢材的最大价值保存在了cacheCut中)
			for (int j = 1; j <= i; j++) {
				maxPrice = Math.max(maxPrice, cacheCut[j - 1] + cacheCut[i - j]);
			}
			cacheCut[i] = maxPrice;
		}
		return cacheCut[n];
	}


	/**
	 * 备忘录模式切割钢材(动态规划自底向上)
	 *
	 * @param price 价格表
	 * @param n     钢材长度
	 * @return
	 */
	private static int cutRolledSteelOfDynamicPlanningTopToDown(int[] price, int n) {
		int[] cacheCut = new int[n + 1];
		return cutRolledSteelOfDynamicPlanningTopToDown(price, n, cacheCut);
	}

	/**
	 * @param price    价格表
	 * @param n        钢材长度
	 * @param cacheCut 下标
	 * @return
	 */
	private static int cutRolledSteelOfDynamicPlanningTopToDown(int[] price, int n, int[] cacheCut) {
		if (cacheCut[n] > 0) {
			return cacheCut[n];
		}
		int countPrice = -1;
		if (n == 0) {
			countPrice = 0;
		} else {
			for (int i = 1; i <= n; i++) {
				countPrice = Math.max(countPrice, cutRolledSteelOfDynamicPlanningTopToDown(price, n - i, cacheCut) + price[i - 1]);
			}
		}
		cacheCut[n] = countPrice;
		return countPrice;
	}

	/**
	 * 计算钢材的切割最优解,对于n长度的钢材,有price[n]
	 *
	 * @param price 钢材的价格表
	 * @param n     待切割钢材长度
	 * @return
	 */
	private static int cutRolledSteelOfRecursion(int[] price, int n) {
		if (n == 0) {
			return 0;
		}
		int q = 0;
		for (int i = 1; i <= n; i++) {
			q = Math.max(q, price[i - 1] + cutRolledSteelOfRecursion(price, n - i));
		}
		return q;
	}


	/**
	 * 缩减其空间复杂度,不需要初始化int[]对象
	 *
	 * @param n
	 * @return
	 */
	private static int optimizeFibonacciOfDynamicPlanningDownToTop(int n) {
		if (n <= 0) {
			return n;
		}
		int count = 0;
		int preOne = 1;
		int preTwo = 0;
		for (int i = 2; i <= n; i++) {
			count = preOne + preTwo;
			preTwo = preOne;
			preOne = count;
		}
		return count;
	}

	/**
	 * 动态规划,自底向上
	 *
	 * @param n
	 * @return
	 */
	public static int fibonacciOfDynamicPlanningDownToTop(int n) {
		if (n <= 0) {
			return n;
		}
		int[] cacheValue = new int[n + 1];
		cacheValue[0] = 0;
		cacheValue[1] = 1;
		for (int i = 2; i <= n; i++) {
			cacheValue[i] = cacheValue[i - 1] + cacheValue[i - 2];
		}
		return cacheValue[n];
	}


	public static int fibonacciOfDynamicPlanningTopToDown(int n) {
		int[] cacheValue = new int[n + 1];
		int value = fibonacciOfDynamicPlanningTopToDown(n, cacheValue);
		return value;
	}

	/**
	 * 动态规划,自顶向下(备忘录模式):其本质还是使用了递归,但是中间记录下了每次已经计算过了的数值,当重复执行n时,直接提取,不再计算
	 *
	 * @param n
	 * @param cacheValue
	 * @return
	 */
	public static int fibonacciOfDynamicPlanningTopToDown(int n, int[] cacheValue) {
		if (cacheValue[n] != 0) {
			return cacheValue[n];
		}
		if (n <= 2) {
			cacheValue[n] = 1;
		} else {
			cacheValue[n] = fibonacciOfDynamicPlanningTopToDown(n - 1, cacheValue) + fibonacciOfDynamicPlanningTopToDown(n - 2, cacheValue);
		}
		return cacheValue[n];
	}


	/**
	 * 使用递归实现:简单,但是会出现某个n重复计算多次的情况
	 *
	 * @param n
	 * @return
	 */
	public static int fibonacciOfRecursion(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fibonacciOfRecursion(n - 1) + fibonacciOfRecursion(n - 2);
	}
}
