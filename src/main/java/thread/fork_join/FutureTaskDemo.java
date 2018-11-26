package thread.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Description: fork/join框架处理并发任务(线程池)
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class FutureTaskDemo extends RecursiveTask {
	/**
	 * 阈值
	 */
	private static final int THRESHOLD = 0;
	private int start;
	private int end;

	public FutureTaskDemo(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected List compute() {
		List<Integer> intList = new ArrayList<>();
		//如果任务足够小就计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				System.out.println(Thread.currentThread());
				intList.add(i);
			}
		} else {
			//如果任务大于阀值，就分裂成两个子任务计算
			int middle = (start + end) / 2;
			FutureTaskDemo leftTask = new FutureTaskDemo(start, middle);
			FutureTaskDemo rightTask = new FutureTaskDemo(middle + 1, end);
			//执行子任务
			leftTask.fork();
			rightTask.fork();
			//等待子任务执行完，并得到其结果
			List leftResult = (List) leftTask.join();
			List rightResult = (List) rightTask.join();
			//合并子任务
			intList.addAll(leftResult);
			intList.addAll(rightResult);
		}
		return intList;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Long begin = System.currentTimeMillis();
		FutureTaskDemo task = new FutureTaskDemo(1, 100);
		//执行一个任务
		Future result = forkJoinPool.submit(task);
		try {
			List list = (List) result.get();
			System.out.println(System.currentTimeMillis() - begin);
			//System.out.println(list);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

//		int i = 0;
//		List intList2 = new ArrayList();
//		Long begin2 = System.currentTimeMillis();
//		while(i<= 10000){
//			intList2.add(i);
//		}
//		Long endTime = System.currentTimeMillis();
//		System.out.println(endTime - begin2);
	}

}