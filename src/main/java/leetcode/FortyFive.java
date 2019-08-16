package leetcode;

import java.math.BigDecimal;

/**
 * @desc: 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * <p>
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 * @author: zhongqionghua
 * @create: 2019/7/11 14:55
 */
public class FortyFive {

	public static void main(String[] args) {
		Double s = 500000d;
		Double result = s / 3;
		System.out.println(result);
		BigDecimal paramBigDecimal = new BigDecimal(result);
		System.out.println(paramBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
}
