package exclutil;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/7/1 14:07
 */
public class Client {

	/**
	 * 读取excel，支持xsl、xlsx、csv
	 * @throws Exception
	 */
	@Test
	public void readXLSX() throws Exception{
		String filepath = "/Users/puzhaolin/Downloads/test.xlsx";
		FileInputStream inputStream = new FileInputStream(filepath);
		AbstractExcelReader reader = ReaderFactory.create(inputStream, ReaderFactory.FileType.xlsx);
		// 解析起始行数，可略过表头，设置后，每个sheet都会略过表头， rowIndex start with startRow
		reader.setStartRows(2);

		int count = reader.parse((datas, rowIndex, sheetIndex) -> {
			// rowIndex and sheetindex start with 0
			System.out.println(String.format("当前sheetIndex=%d,当前行号index=%d,当前行数据：%s",
					sheetIndex, rowIndex, Arrays.toString(datas)));
			// 支持手动停止解析
			if(rowIndex == 2 && sheetIndex == 1){
				reader.stop();
			}
		});
		System.out.println(String.format("一共解析%d条数据", count));
		try{
			inputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 读取excel，支持xsl、xlsx、csv
	 * @throws Exception
	 */
	@Test
	public void readCSV() throws Exception{
		String filepath = "/Users/puzhaolin/Desktop/qunar/travelprov/target/classes/data/hot_poi_list.csv";
		FileInputStream inputStream = new FileInputStream(filepath);
		AbstractExcelReader reader = ReaderFactory.create(inputStream, ReaderFactory.FileType.csv, CSVFileReader.SEPARATOR_TAB);
		// 解析起始行数，可略过表头，设置后，每个sheet都会略过表头
		reader.setStartRows(3);

		int count = reader.parse((datas, rowIndex, sheetIndex) -> {
			// rowIndex start with 1
			// sheetIndex start with 0
			System.out.println(String.format("当前行号index=%d,当前行数据：%s",
					rowIndex, Arrays.toString(datas)));
			// 支持手动停止解析
//            if(rowIndex == 2){
//                reader.stop();
//            }
		});
		System.out.println(String.format("一共解析%d条数据", count));
		try{
			inputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * 读取excel，支持xsl、xlsx、csv
	 * @throws Exception
	 */
	@Test
	public void readXLS() throws Exception{
		String filepath = "/Users/puzhaolin/Downloads/test.xls";
		FileInputStream inputStream = new FileInputStream(filepath);
		AbstractExcelReader reader = ReaderFactory.create(inputStream, ReaderFactory.FileType.xls);
		// 解析起始行数，可略过表头，设置后，每个sheet都会略过表头
		reader.setStartRows(1);

		int count = reader.parse((datas, rowIndex, sheetIndex) -> {
			// rowIndex start with 1
			// sheetIndex start with 0
			System.out.println(String.format("当前sheetIndex=%d,当前行号index=%d,当前行数据：%s",
					sheetIndex, rowIndex, Arrays.toString(datas)));
			// 支持手动停止解析
//            if(rowIndex == 2){
//                reader.stop();
//            }
		});
		System.out.println(String.format("一共解析%d条数据", count));
		try{
			inputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Test
	public void writeArray() throws Exception{
		// 构造数据
//        String filepath = "F:\\test\\"+ UUID.randomUUID().toString()+".xls";
		String filepath = "F:\\test\\d1e02424-7469-4176-ac69-2d97fc4744b3.xls";
		long t1 = System.currentTimeMillis();
		List<String> header = new ArrayList<String>();
		header.add("名字");
		header.add("id");
		header.add("日期");
		List<Object[]> list = new ArrayList<Object[]>();
		for(int i = 0; i < 100; i++){
			list.add(new Object[]{1,"第一列",new Date()});
			list.add(new Object[]{1223,"第二列",new Date()});
		}

		AbstractExcelWriter<WriteFoo> writer = WriterFactory.create(filepath);
		// 大数据量情况，要分sheet写入
		writer.setSheetSize(5000);
		for(int i = 0; i<100; i++){
			writer.writeByArray(list, header);
		}
		writer.output(new FileOutputStream(new File(filepath)));
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)/3600);

	}



	@Test
	public void writeBean() throws Exception{
		String filepath = "/Users/puzhaolin/Downloads/"+ UUID.randomUUID().toString()+".xlsx";
		long t1 = System.currentTimeMillis();
		List<String> header = new ArrayList<String>();
		header.add("名字");
		header.add("id");
		header.add("日期");
		List<WriteFoo> list = new ArrayList<WriteFoo>();
		for(int i = 0; i < 3; i++){
			list.add(new WriteFoo(1,"第一列",new Date()));
			list.add(new WriteFoo(2,"第二列",new Date()));
		}



		AbstractExcelWriter<WriteFoo> writer = WriterFactory.create(filepath);
		writer.setSheetSize(6);
		for(int i = 0; i<3; i++){
			writer.writeByBean(list, header);
		}


		writer.output(new FileOutputStream(new File(filepath)));
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)/3600);

	}
}
