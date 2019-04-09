package jdk_source_code;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

		LocalDateTime localDateTime2 = LocalDateTime.now();
		LocalTime localTime = localDateTime2.toLocalTime().withHour(0).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime localDateTime1 = LocalDateTime.of(localDateTime2.toLocalDate(), localTime);
		System.out.println(localDateTime1.toString());

		System.out.println(Integer.parseInt("5.12"));

	}

//	/**
//	 * 当前日期的00:00:00
//	 *
//	 * @param date
//	 * @return Date
//	 */
//	public static Date withTimeAtStartOfDay(Date date) {
//		DateTime dateTime = new DateTime(date);
//		return dateTime.withTimeAtStartOfDay().toDate();
//	}
//
//	/**
//	 * 当前日期的23:59:59
//	 *
//	 * @param date
//	 */
//	public static Date withTimeAtEndOfDay(Date date) {
//		DateTime dateTime = new DateTime(date);
//		return dateTime.withTimeAtStartOfDay().plusDays(1).minusSeconds(1).toDate();
//	}
}
