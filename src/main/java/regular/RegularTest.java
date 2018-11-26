package regular;

/**
 * @Description: 正则表达式
 * 1.非打印字符: \f  \n \r
 * 2.特殊字符 * + . ?
 * 3.限定符  * + ? {n}, {n, } {n,m}
 * 4.定位符 ^ 字符串开始位置; $ 字符串尾; \b 匹配一个单词边界，即字与空格间的位置。 \B非单词边界匹配
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class RegularTest {

	public static void main(String[] args){
		String str = "\\.";
		System.out.println(str);
	}
}
