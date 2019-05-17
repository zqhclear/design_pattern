package leetcode;

/**
 * @desc: 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 * @author: zhongqionghua
 * @create: 2019/5/8 15:29
 */
public class ThirtySix {
	public static void main(String[] args){

	}

	static int blockNum = 9;

	public static boolean isValidSudoku(char[][] board) {
		// 记录某行，某位数字是否已经被摆放
		boolean[][] row = new boolean[blockNum][blockNum];
		// 记录某列，某位数字是否已经被摆放
		boolean[][] col = new boolean[blockNum][blockNum];
		// 记录某 3x3 宫格内，某位数字是否已经被摆放
		boolean[][] block = new boolean[blockNum][blockNum];

		for (int i = 0; i < blockNum; i++) {
			for (int j = 0; j < blockNum; j++) {
				if (board[i][j] != '.') {
					int num = board[i][j] - '1';
					int blockIndex = i / 3 * 3 + j / 3;
					if (row[i][num] || col[j][num] || block[blockIndex][num]) {
						return false;
					} else {
						row[i][num] = true;
						col[j][num] = true;
						block[blockIndex][num] = true;
					}
				}
			}
		}
		return true;
	}
}
