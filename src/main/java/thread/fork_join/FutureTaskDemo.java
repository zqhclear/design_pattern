package thread.fork_join;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Description: fork/join框架处理并发任务( )
 * 在做任务切分时,尽量将任务划分成许多的小任务而不是少数的大任务
 * 原因:其除了切分大任务的效果外,forkjoinpool框架还能够进行任务窃取:即当一个线程执行完成他本身的任务外,
 * 如果发现别的线程还有任务没有执行完成,他会从别的线程的任务队列中,从尾部获取一个任务执行,直到全部执行完毕
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
	protected Integer compute() {
		//如果任务足够小就计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			int countTmp = 0;
			for (int i = start; i <= end; i++) {
				System.out.println(Thread.currentThread());
				countTmp += i;
			}
			return countTmp;
		} else {
			//如果任务大于阀值，就分裂成两个子任务计算
			int middle = (start + end) / 2;
			FutureTaskDemo leftTask = new FutureTaskDemo(start, middle);
			FutureTaskDemo rightTask = new FutureTaskDemo(middle + 1, end);
			//执行子任务
			leftTask.fork();
			rightTask.fork();
			//等待子任务执行完，并得到其结果
			int leftResult = (int) leftTask.join();
			int rightResult = (int) rightTask.join();

			/*
			注意：子任务调用fork()方法能够将任务放到forkJoinPool队列中.同时对lefttask,righttask调用该方法似乎是很自然的,
			但是要注意,这样的效率要比其中一个直接调用compute()低,
			因为这样做你可以为其中的一个子任务重用同一线程,从而避免在线程池中多分配一个任务造成的开销
			所以,以上代码最好做下面的优化
			 */
//			int middle = (start + end) / 2;
//			FutureTaskDemo leftTask = new FutureTaskDemo(start, middle);
//			FutureTaskDemo rightTask = new FutureTaskDemo(middle + 1, end);
//			//执行子任务
//			leftTask.fork();
//			//等待子任务执行完，并得到其结果
//			int rightResult = (int) rightTask.compute();
//			//join会阻塞当前线程,所以的都能到lefttask和righttask都执行fork或者compute()方法再执行join
//			int leftResult = (int) leftTask.join();
//			//合并子任务
//			return leftResult + rightResult;


			//合并子任务
			return leftResult + rightResult;
		}
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Long begin = System.currentTimeMillis();
		FutureTaskDemo task = new FutureTaskDemo(1, 100);
		//执行一个任务
		Future result = forkJoinPool.submit(task);
		try {
			int list = (int) result.get();
			System.out.println(JSONObject.toJSONString(list));
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