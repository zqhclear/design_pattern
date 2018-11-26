//package thread.synchronizeddemo;
//
///**
// * @Description:
// * @Author: zhongqionghua
// * @CreateDate: 2018/10/29 15:13
// */
//public class SynchronizedDemo implements Runnable {
//	@Override
//	public void run() {
//		Target target = new Target();
//		synchronized (this){
//			target.sayBegin();
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			target.sayEnd();
//		}
//	}
//}
