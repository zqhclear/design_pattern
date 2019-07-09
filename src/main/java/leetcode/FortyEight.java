package leetcode;

import com.alibaba.fastjson.JSONObject;

import java.rmi.MarshalException;
import java.util.Random;

/**
 * @desc: 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 示例 1:
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * @author: zhongqionghua
 * @create: 2019/7/8 15:13
 */
public class FortyEight {

	private static final int ROW = 4;
	private static final int COL = 4;

	public static void main(String[] args) {
		int[][] matrix = new int[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				matrix[i][j] = new Random().nextInt(30);
			}
		}
		for (int i = 0; i < ROW; i++) {
			System.out.println(JSONObject.toJSONString(matrix[i]));
		}
		rotate(matrix);
		System.out.println();
		for (int i = 0; i < ROW; i++) {
			System.out.println(JSONObject.toJSONString(matrix[i]));
		}
	}

	public static void rotateOptimize(int[][] matrix) {
		int temp;
		for (int i = 0; i < matrix.length / 2; i++) {// 控制圈数
			for (int j = 0; i + j < matrix.length - 1 - i; j++) {// 内层for完成一圈的旋转
				temp = matrix[i][i + j];
				matrix[i][i + j] = matrix[matrix.length - 1 - i - j][i];
				matrix[matrix.length - 1 - i - j][i] = matrix[matrix.length - 1 - i][matrix.length - 1 - i - j];
				matrix[matrix.length - 1 - i][matrix.length - 1 - i - j] = matrix[i + j][matrix.length - 1 - i];
				matrix[i + j][matrix.length - 1 - i] = temp;
			}
		}
	}

	/**
	 * 旋转矩阵
	 * 先翻转矩阵,之后再矩阵的行中数据倒置
	 *
	 * @param matrix
	 */
	public static void rotate(int[][] matrix) {
		int temp;
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = i; j < matrix.length; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		System.out.println();
		for (int i = 0; i < ROW; i++) {
			System.out.println(JSONObject.toJSONString(matrix[i]));
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length / 2; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1 - j];
				matrix[i][matrix.length - 1 - j] = temp;
			}
		}
	}
}
