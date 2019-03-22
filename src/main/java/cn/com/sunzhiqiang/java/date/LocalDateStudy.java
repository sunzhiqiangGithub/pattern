package cn.com.sunzhiqiang.java.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sunzhiqiang
 * @description: LocalDate学习
 * @date 2019-03-2216:47
 */
public class LocalDateStudy {

    public static void main(String[] args) {

        // 创建当天的日期
        LocalDate today = LocalDate.now();
        System.out.println(today);

        // 获取年月日
        System.out.println(today.getYear());
        System.out.println(today.getMonthValue());
        System.out.println(today.getDayOfMonth());

        // 创建指定的日期
        LocalDate localDate = LocalDate.of(2021, 5, 1);
        System.out.println(localDate);

        // 日期是否相同的比较
        System.out.println(localDate.equals(today));
        System.out.println(localDate.equals(LocalDate.of(2021, 5, 1)));

        // 判断是否是同月同日
        MonthDay monthDay = MonthDay.of(7, 19);
        MonthDay monthDayOfToday = MonthDay.from(today);
        System.out.println(monthDay.equals(monthDayOfToday));

        // 获取一周后的日期
        System.out.println(today.plusWeeks(1));

        // 获取一年前一年后的日期
        System.out.println(today.plusYears(1));
        System.out.println(today.minusYears(1));

        // 2个日期前后比较
        System.out.println(today.plusDays(1).isAfter(today));
        System.out.println(today.isAfter(today));
        System.out.println(today.isBefore(today));

        // 时区转化
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("ACT"));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
        System.out.println(zonedDateTime);

        // 固定的年月
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth.lengthOfMonth());

        // 判断是否是闰年
        System.out.println(today.isLeapYear());

        // TODO 查看2个日期相差多少天
        Period period = Period.between(today, localDate);
        System.out.println(localDate);
        System.out.println(today);
        System.out.println(period.getDays());
        System.out.println(period.getMonths());

        // 返回当前时间戳
        Instant tempstamp = Instant.now();
        System.out.println(tempstamp);

        // 日期格式化DateTimeFormatter
        LocalDate localDate1 = LocalDate.parse("20161020", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.parse("20161020", dtf));
        System.out.println(today.format(dtf));
    }
}
