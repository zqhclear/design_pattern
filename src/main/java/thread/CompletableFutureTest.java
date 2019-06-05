package thread;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @desc: CompletableFuture测试
 * @author: zhongqionghua
 * @create: 2019/6/4 17:24
 */
public class CompletableFutureTest {
	public static void main(String[] args) throws Exception {
//		//无返回值
//		runAsync();
//		//有返回值
//		supplyAsync();
//
//		whenComplete();
//
//		/*
//		以下两个方法都是当前线程执行依赖于另外的线程
//		不同:handle:当第一个线程失败时,还是会继续执行第二个
//			thenapply:当第一个线程失败时,第二个不会执行
//		 */
//		thenApply();
//		handle();

		/*
		接收任务的处理结果，并消费处理，无返回结果。
		不同:thenRun不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
		 */
//		thenAccept();
//		thenRun();

//		thenCombine();
//		thenAcceptBoth();

//		applyToEither();

//		runAfterEither();

		runAfterBoth();
	}

	/**
	 * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
	 */
	private static void runAfterBoth() {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
			int t = 4;
			System.out.println("f1="+t);
//			try {
//				TimeUnit.SECONDS.sleep(t);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			return t;
		});

		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
			int t =6;
			System.out.println("f2="+t);
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return t;
		});
		f1.runAfterBoth(f2, () -> System.out.println("上面两个任务都执行完成了。"));
	}

	/**
	 * 两个线程,只要一个结束就直接执行后续
	 *
	 * @throws Exception
	 */
	private static void runAfterEither() {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
			int t = 4;
			System.out.println("f1=" + t);
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return t;
		});

		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
			int t = 6;
			System.out.println("f2=" + t);
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return t;
		});
		f1.runAfterEither(f2, () -> System.out.println("上面有一个已经完成了。"));
	}

	/**
	 * 两个线程,谁执行快用谁的
	 *
	 * @throws Exception
	 */
	private static void applyToEither() throws Exception {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
			int t = 7;
			System.out.println("f1=" + t);
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return t;
		});
		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
			int t = 5;
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("f2=" + t);
			return t;
		});

		CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
			System.out.println(t);
			return t * 2;
		});

		System.out.println(result.get());
	}

	/**
	 * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
	 *
	 * @throws Exception
	 */
	private static void thenAcceptBoth() {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
			int t = 6000;
			System.out.println(t);
//			try {
//				TimeUnit.MILLISECONDS.sleep(t);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			return t;
		});

		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
			int t = 5000;
			System.out.println(t);
			try {
				Thread.sleep(t + 5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return t;
		});
		f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1=" + t + ";f2=" + u + ";"));
	}

	/**
	 * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
	 *
	 * @throws Exception
	 */
	private static void thenCombine() throws Exception {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		});
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");
		CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
		System.out.println(result.get());
	}

	public static void thenRun() throws Exception {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(
				() -> new Random().nextInt(10)).thenRun(
				() -> System.out.println("thenRun ..."));
		future.get();
	}


	/**
	 * 接收任务的处理结果，并消费处理，无返回结果。
	 *
	 * @throws Exception
	 */
	public static void thenAccept() throws Exception {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(
				() -> new Random().nextInt(10)).thenAccept(
				integer -> System.out.println(integer));
		future.get();
	}

	/**
	 * 执行完第一个函数后执行第二个函数
	 * 注:当第一个函数异常,第二个函数还是会执行
	 *
	 * @throws Exception
	 */
	public static void handle() throws Exception {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			int i = 10 / 0;
			return new Random().nextInt(10);
		}).handle((param, throwable) -> {
			int result = -1;
			if (throwable == null) {
				result = param * 2;
			} else {
				System.out.println(throwable.getMessage());
			}
			return result;
		});
		System.out.println(future.get());
	}

	/**
	 * thenApply:其后的行数依赖于前一个函数执行
	 * 注;如果前一个函数异常,thenApply后的函数不会执行
	 *
	 * @throws Exception
	 */
	private static void thenApply() throws Exception {
		CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
			long result = new Random().nextInt(100);
			System.out.println("result1=" + result);
//			if (result > 0) {
//				throw new RuntimeException();
//			}
			return result;
		}).thenApply(t -> {
			long result = t * 5;
			System.out.println("result2=" + result);
			return result;
		});

		long result = future.get();
		System.out.println(result);
	}

	/**
	 * 当前执行的线程去执行
	 *
	 * @throws Exception
	 */
	public static void whenComplete() throws Exception {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
			if (new Random().nextInt() % 2 >= 0) {
				int i = 12 / 0;
			}
			System.out.println("run end ...");
		});
		future.whenComplete((t, action) -> System.out.println("执行完成！"));
		TimeUnit.SECONDS.sleep(2);
	}

	/**
	 * runAsync:无返回值
	 * 有两个重载方法,默认的线程池为fork-join(下同)
	 *
	 * @throws Exception
	 */
	public static void runAsync() throws Exception {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
			System.out.println("run end ...");
		});

		future.get();
	}

	/**
	 * supplyAsync:有返回值
	 *
	 * @throws Exception
	 */
	public static void supplyAsync() throws Exception {
		CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
			System.out.println("run end ...");
			return System.currentTimeMillis();
		});
		long time = future.get();
		System.out.println("time = " + time);
	}
}
