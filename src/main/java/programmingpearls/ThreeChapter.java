package programmingpearls;

/**
 * @description: 第三章
 * @author: zhongqionghua
 * @Date: 2019/2/13 16:39
 */
public class ThreeChapter {
	public static void main(String[] args) {
		first(102200);
	}

	/**
	 * 第一题:个人税率
	 * 规律:2200及以下不交税, 2700:交(2700-2200)*0.14,每增加500,税率增加0.1
	 *
	 * @param income
	 */
	static int defaultIncome = 2200;
	static int defaultRateRank = 500;
	static double defaultRate = 0.14;
	static double defaultRateIncrease = 0.01;

	public static void first(int income) {
		int multiplyRate = (income - defaultIncome) % defaultRateRank == 0
				? (income - defaultIncome) / defaultRateRank
				: (income - defaultIncome) / defaultRateRank + 1;
		System.out.println(multiplyRate);
		double tax = calculateTax(income, multiplyRate);
		System.out.println("tax:" + tax);
		System.out.println(count);
	}

	static int count = 0;
	private static int calculateTax(int income, int rateLevel) {
		if (income <= defaultIncome) {
			return 0;
		}
		count++;
		int preRateIncome = defaultIncome + defaultRateRank * (rateLevel - 1);
		double nowRate = (rateLevel - 1) * defaultRateIncrease + defaultRate > 0.7 ? 0.7 : (rateLevel - 1) * defaultRateIncrease + defaultRate;
		double rate = (income - preRateIncome) * nowRate;
		double rate2 = calculateTax(preRateIncome, rateLevel - 1);
		double sum = rate + rate2;
		System.out.println("preRateIncome:" + preRateIncome + "   rate:" + (int)rate + "    rate1:" + rate2 + "   sum:" + (int)sum);
		return (int)sum;
	}
}
