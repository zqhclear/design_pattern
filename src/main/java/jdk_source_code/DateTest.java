package jdk_source_code;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description: jdk1.8操作时间api
 * @author: zhongqionghua
 * @Date: 2019/3/19 13:58
 */
public class DateTest {
	public static void main(String[] args) throws InterruptedException {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(localDateTime.getDayOfMonth()+":" + localDateTime.getMonth().getValue()+":"+localDateTime.getYear()+":"+localDateTime.getDayOfYear());

		Instant instant = Instant.now();
		Thread.sleep(1);
		System.out.println(instant.isBefore(Instant.now()));

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(localDateTime.format(dateTimeFormatter));
	}
}
