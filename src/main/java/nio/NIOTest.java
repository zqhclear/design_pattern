package nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2018/12/21 14:56
 */
public class NIOTest {
	private static final String FILE_PATH = "C:\\Users\\zhongqionghua\\Desktop\\test.txt";
	private static final String FILE_PATH_TO = "C:\\Users\\zhongqionghua\\Desktop\\testTo.txt";

	public static void main(String[] args) throws IOException {
		//transferFrom();

		InputStream inputStream = new FileInputStream(FILE_PATH);
		((FileInputStream) inputStream).getChannel();

	}

	/**
	 *
	 */
	public static void selectorTest() throws IOException {
		Selector selector = Selector.open();
		RandomAccessFile fileFrom = new RandomAccessFile(FILE_PATH, "rw");
		RandomAccessFile fileTo = new RandomAccessFile(FILE_PATH_TO, "rw");



	}

	/**
	 * 通道之间传递数据
	 * 对应的为transferTo:功能一样的
	 */
	public static void transferFrom() throws IOException {
		RandomAccessFile randomAccessFileFrom = new RandomAccessFile(FILE_PATH, "rw");
		FileChannel fileChannelFrom = randomAccessFileFrom.getChannel();

		RandomAccessFile randomAccessFile1To = new RandomAccessFile(FILE_PATH_TO, "rw");
		FileChannel fileChannelTo = randomAccessFile1To.getChannel();

		fileChannelTo.transferFrom(fileChannelFrom, 0, fileChannelFrom.size());
	}



	/**
	 * 使用nio读取文件
	 *
	 * @throws IOException
	 */
	public static void test1() throws IOException {
		FileChannel fileChannel = null;
		try{
			RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, "rw");

			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 500);

			fileChannel = randomAccessFile.getChannel();

			int chanelRead = fileChannel.read(byteBuffer);
			while (chanelRead != -1) {

				//将buffer准备被读取
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					//读取一个position的内容
					System.out.print((char) byteBuffer.get());
				}

				// clear直接清空内存中所有的已读入的内容
				byteBuffer.compact();
				chanelRead = fileChannel.read(byteBuffer);
			}
		}catch (Exception e){

		}finally {
			if(null != fileChannel){
				fileChannel.close();
			}
		}
	}

}
