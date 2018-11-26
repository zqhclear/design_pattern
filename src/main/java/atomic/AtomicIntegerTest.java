package atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @Description: AtomicIntegerTest
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class AtomicIntegerTest {

	public static void main(String[] args) {
		AtomicInteger aiTest = new AtomicInteger(20);
		System.out.println(aiTest.getAndAdd(2));
		System.out.println(aiTest);
		//addAndGet和getAndAdd区别在于先加然后返回还是先返回然后再加  类似于++b和b++
		System.out.println(aiTest.addAndGet(2));
		System.out.println(aiTest);

		//该方法需要传递一个function函数,其标准为IntUnaryOperator:即一个入参和一个出参都为int类型
		System.out.println(aiTest.getAndUpdate(intUnaryOperator));
		System.out.println(aiTest);
		System.out.println(aiTest.updateAndGet(intUnaryOperator));
		System.out.println(aiTest);

		// 该方法是可以对于更新后的值和原先的值进行运算
		System.out.println(aiTest.getAndAccumulate(5, intBinaryOperator));
		System.out.println(aiTest);


		int a = 0;
		if(true || (a=4) > 4){
			System.out.println(a);
		}

	}

	static IntUnaryOperator intUnaryOperator = x -> x + 6;

	static IntBinaryOperator intBinaryOperator = (x, y) -> x + y + 6;

}
