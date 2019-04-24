package leetcode;

import com.alibaba.fastjson.JSONObject;

/**
 * @desc: 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * <p>
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 * @author: zhongqionghua
 * @create: 2019/4/24 14:44
 */
public class Four {

	public static void main(String[] args) {
		int[] nums1 = new int[]{2, 6, 9};
		int[] nums2 = new int[]{1, 23, 29};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

	/**
	 * gg还没搞明白
	 *
	 * @param arrayOne
	 * @param arrayTwo
	 * @return
	 */
	public static double findMedianSortedArrays(int[] arrayOne, int[] arrayTwo) {
		int m = arrayOne.length;
		int n = arrayTwo.length;
		if (m > n) {
			int[] temp = arrayOne;
			arrayOne = arrayTwo;
			arrayTwo = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && arrayTwo[j - 1] > arrayOne[i]) {
				iMin = i + 1;
			} else if (i > iMin && arrayOne[i - 1] > arrayTwo[j]) {
				iMax = i - 1;
			} else {
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = arrayTwo[j - 1];
				} else if (j == 0) {
					maxLeft = arrayOne[i - 1];
				} else {
					maxLeft = Math.max(arrayOne[i - 1], arrayTwo[j - 1]);
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}
				int minRight = 0;
				if (i == m) {
					minRight = arrayTwo[j];
				} else if (j == n) {
					minRight = arrayOne[i];
				} else {
					minRight = Math.min(arrayTwo[j], arrayOne[i]);
				}
				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}

	/**
	 * 找中位数: 无法满足log(m+n)的时间复杂度
	 * 失败 System.arraycopy时间复杂度就不少于logn了
	 *
	 * @param nums1 有序数组
	 * @param nums2 有序数组
	 * @return
	 */
	public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
		double[] numsCount = new double[nums1.length + nums2.length];
		System.arraycopy(nums1, 0, numsCount, 0, nums1.length);
		System.arraycopy(nums2, 0, numsCount, nums1.length, nums2.length);
		System.out.println(JSONObject.toJSONString(numsCount));
		//使用二分法排序
		quickSort(numsCount, 0, numsCount.length - 1);
		if (numsCount.length % 2 == 0) {
			return (numsCount[numsCount.length / 2] + numsCount[numsCount.length / 2 - 1]) / 2;
		} else {
			return numsCount[numsCount.length / 2 + 1];
		}
	}

	public static double[] quickSort(double[] intArray, int left, int right) {
		//参数不符合要求
		if (left >= right) {
			return intArray;
		}
		int i = left;
		int j = right;
		double initialNum = intArray[i];
		while (i < j) {
			/*
			此处为什么一定是需要从后往前找，而不能从前往后找的？
			原因：在找的过程中，其实是会改变指针（i,j）的值的，如果是先找左边的话，会出现找到对应的值，
			但是这个值是不正确的，不符合规范（即i>=j），指针i的值也发生了变，后续处理也是会计算进去，错误

			右边找到第一个小于初始值的第一个位置（从后往前）
			 */
			while (i < j && intArray[j] >= initialNum) {
				j--;
			}
			//左边找到大于初始的值的第一个位置
			while (i < j && intArray[i] <= initialNum) {
				i++;
			}
			if (i < j) {
				double temp = intArray[i];
				intArray[i] = intArray[j];
				intArray[j] = temp;
				//经过了该步骤，就已经达到了intArray[i]已经是最后的一个小于初始位置的值了
			}
		}
		//到这一步,i==j已成立
		intArray[left] = intArray[j];
		intArray[j] = initialNum;
		/*
		上两步可以使得：将初始位置放置在其中间位置，
		使得左边全是小于（大于）该初始值的值，右边全是大于（小于）初始值的值
		排序初始值左边
		 */
		System.out.println(JSONObject.toJSONString(intArray));
		quickSort(intArray, left, i - 1);
		//排序初始值右边
		quickSort(intArray, i + 1, right);
		return intArray;
	}

}
