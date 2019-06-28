package jdk_source_code;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @description: jdk1.8操作时间api
 * @author: zhongqionghua
 * @Date: 2019/3/19 13:58
 */
public class LocalDateTimeTest {
	public static void main(String[] args) throws InterruptedException {
//		test();
		testLocalDate();
		testLocalTime();
		testLocalDateTime();

		testInstant();

		testDuration();

		testTemporalAdjuster();

		testDateTimeFormatter();

		testZoneId();
	}

	/**
	 * 时区处理
	 */
	private static void testZoneId(){
		ZoneId zoneId = ZoneId.of("Europe/Rome");
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
		System.out.println(zonedDateTime);

		LocalDate localDate = LocalDate.now();
		ZonedDateTime zonedDateTime1 = localDate.atStartOfDay(zoneId);
		System.out.println(zonedDateTime1);

		Instant instant = Instant.now();
		System.out.println(instant.atZone(zoneId));

	}

	/**
	 * 日期的转换
	 * String-date
	 */
	private static void testDateTimeFormatter(){
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));




	}

	/**
	 * 进行一些复杂的操作,比如:月的末尾,月首,下个工作日
	 * 注:其只会操作年,月日,对于时分秒无法进行操作
	 */
	private static void testTemporalAdjuster() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

		LocalDate localDate1 = localDate.with(nextOrSame(DayOfWeek.SUNDAY));
		System.out.println(localDate1);

		LocalDate localDate2 = localDate.with(lastDayOfMonth());
		System.out.println(localDate2);

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.with(firstDayOfNextMonth()));

	}

	private static void testPeriod() {
		//其使用方式和duration差不多
	}

	/**
	 * dureation:比较时间
	 * eg:between比较时间的差值
	 */
	private static void testDuration() {
		LocalDateTime localDateTime1 = LocalDateTime.now();
		LocalDateTime localDateTime2 = localDateTime1.plus(2, ChronoUnit.DAYS);
		System.out.println(Duration.between(localDateTime1, localDateTime2));

		LocalTime localTime1 = localDateTime1.toLocalTime();
		LocalTime localTime2 = localDateTime2.toLocalTime();
		System.out.println(Duration.between(localTime1, localTime2));

		Instant instant1 = Instant.now();
		Instant instant2 = instant1.plus(20, ChronoUnit.DAYS);
		System.out.println(Duration.between(instant2, instant1).toDays());

		System.out.println(Duration.ofDays(3).getSeconds());

	}

	private static void testInstant() {
		System.out.println(Instant.now());
		System.out.println(Instant.parse("2007-12-03T10:15:30.00Z"));

		/**
		 * 从1970****之后的时间
		 */
		System.out.println(Instant.ofEpochSecond(100));

		//instant是为了机器使用(时间戳),无法处理我们非常容容易理解的时间单位
		//以下方法会报错
//		System.out.println(Instant.now().get(ChronoField.DAY_OF_MONTH));
	}


	private static void testLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		System.out.println(localDateTime.toLocalDate());
		System.out.println(localDateTime.toLocalTime());
	}

	private static void testLocalTime() {
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);

		System.out.println(LocalTime.parse("12:35:45"));

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH/mm:ss");
		System.out.println(LocalTime.parse("12/35:45", dateTimeFormatter));
	}

	private static void testLocalDate() {
		LocalDate localDate = LocalDate.of(2014, 6, 23);
		System.out.println(localDate);
		System.out.println(localDate.getDayOfWeek());

		System.out.println(LocalDate.now());

		System.out.println(LocalDate.parse("2014-05-09"));

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(LocalDate.parse("2014/05/09", dateTimeFormatter));

		System.out.println(localDate.with(ChronoField.DAY_OF_MONTH, 20));

		System.out.println(localDate.plus(-24, ChronoUnit.DAYS));
		//相当于
		System.out.println(localDate.minusDays(24));
	}

	private static void test() throws InterruptedException {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(localDateTime.getDayOfMonth() + ":" + localDateTime.getMonth().getValue() + ":" + localDateTime.getYear() + ":" + localDateTime.getDayOfYear());

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

}
