/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/3/18 10:12
 */
public class B {
	public static void main(String[] args){
		System.out.println("this is B.class method");
	}

	public static void sayOneParam(String param){
		System.out.println(param);
	}

	public static void sayTwoParams(String oneParam, String twoParam){
		System.out.println(oneParam + twoParam);
	}

	public static String sayTwoParamsReturn(String oneParam, String twoParam){
		return oneParam + twoParam;
	}

	public static Integer count(Integer...intParmas){
		if(null == intParmas || intParmas.length <= 0){
			return 0;
		}
		Integer sum = 0;
		for (int i = 0; i < intParmas.length; i++) {
			if (intParmas[i] != null) {
				sum += intParmas[i];
			}
		}
		return sum;
	}
}
