package leetcode;

/**
 * @desc: 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * @author: zhongqionghua
 * @create: 2019/4/25 17:48
 */
public class Eleven {
	public static void main(String[] args) {
		int[] heights = new int[]{1, 8, 4, 6, 9, 7, 1, 6};
		System.out.println(maxArea(heights));
	}

	/**
	 * 面积根据小的那个数据来确定的
	 *
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int maxArea = 0;
		while (left < right) {
			int temp = Math.min(height[left], height[right]) * (right - left);
			maxArea = maxArea > temp ? maxArea : temp;
			if (height[left] > height[right]) {
				right--;
			} else {
				left++;
			}
		}
		return maxArea;
	}
}
