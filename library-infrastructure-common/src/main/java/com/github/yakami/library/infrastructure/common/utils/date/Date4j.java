package com.github.yakami.library.infrastructure.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @author kaka
 * @Date 2022/4/14
 */
public class Date4j {


    public final static String PLUS = "plus";
    public final static String MINUS = "minus";
    private final static String dateFormat = "yyyy-MM-dd";
    private final static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);

    public static String timestamp2Date(String seconds) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(Long.valueOf(seconds) / 1000, 0, ZoneOffset.ofHours(8));
        return dateTime.format(dateTimeFormatter);
    }

    public static String timestamp2Date(Long seconds) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(seconds / 1000, 0, ZoneOffset.ofHours(8));
        return dateTime.format(dateTimeFormatter);
    }

    public static String timestampDay(String seconds) {
        LocalDateTime date = LocalDateTime.ofEpochSecond(Long.valueOf(seconds) / 1000, 0, ZoneOffset.ofHours(8));
        return date.format(dateFormatter);
    }

    public static String timestampDay(Long milliSeconds) {
        LocalDateTime date = LocalDateTime.ofEpochSecond(Long.valueOf(milliSeconds) / 1000, 0, ZoneOffset.ofHours(8));
        return date.format(dateFormatter);
    }


    public static long timestampByDateTime(LocalDateTime dateTime) {
        return LocalDateTime.from(dateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    public static long timestampByDate(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String getCurrentDate() {
        LocalDate now = LocalDate.now();
        return now.format(dateFormatter);
    }

    public static String getCurrentDate(String format) {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern(format));
    }

    //当前时间加日期
    public static long getLaterDay(int num, String type) {
        LocalDateTime now = LocalDateTime.now();
        switch (type) {
            case "day":
                now = now.plusDays(num);
                break;
            case "week":
                now = now.plusWeeks(num);
                break;
            case "month":
                now = now.plusMonths(num);
                break;
            case "year":
                now = now.plusYears(num);
                break;
            default:
                break;
        }
        return timestampByDateTime(now);
    }

    //当前时间减日期
    public static long getAfterDay(int num, String type) {
        LocalDateTime now = LocalDateTime.now();
        switch (type) {
            case "day":
                now = now.minusDays(num);
                break;
            case "week":
                now = now.minusWeeks(num);
                break;
            case "month":
                now = now.minusMonths(num);
                break;
            case "year":
                now = now.minusYears(num);
                break;
            default:
                break;
        }
        return timestampByDateTime(now);
    }

    public static long getAfterDay(int num) {
        return getAfterDay(num, "day");
    }
//    public static Long getFormtMonth(String format){
//        LocalDateTime parse = LocalDateTime.parse(getCurrentDate(format), DateTimeFormatter.ofPattern(format));
//        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//    }


    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(dateTimeFormatter);
    }

    public static int getCurrentSecound() {
        LocalDateTime time = LocalDateTime.now();
        return time.getSecond();
    }


    public static Long getCurrentDay() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return timestampByDateTime(today);
    }

//    public static Pair<Long, Long> getCurrentWeek() {
//        LocalDate inputDate = LocalDate.now();
//        LocalDate firstDay = inputDate.minusDays(inputDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
//        timestampByDate(firstDay);
//        LocalDate lastDay = inputDate.plusDays(DayOfWeek.SUNDAY.getValue() - inputDate.getDayOfWeek().getValue());
//        LocalDateTime lastDay2 = lastDay.atStartOfDay().with(LocalTime.MAX);
//        return new Pair(timestampByDate(firstDay), timestampByDateTime(lastDay2));
//    }
//
//
//    public static Pair<Long, Long> getCurrentMonth() {
//        LocalDateTime inputDate = LocalDateTime.now();
//        LocalDateTime firstDay = inputDate.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
//        LocalDateTime lastDay = inputDate.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
//        return new Pair(timestampByDateTime(firstDay), timestampByDateTime(lastDay));
//    }


    public static LocalDate timestampToLocal(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    //时间戳相隔天数
    public static long equationDay(long startTime, long endTime) {
        long daysDiff = ChronoUnit.DAYS.between(
                Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime));
        return daysDiff;
    }

    //时间戳相隔小时
    public static long equationHour(long startTime, long endTime) {
        long daysDiff = ChronoUnit.HOURS.between(
                Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime));
        return daysDiff;
    }


    //时间戳相隔分钟
    public static long equationMinutes(long startTime, long endTime) {
        long daysDiff = ChronoUnit.MINUTES.between(
                Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime));
        return daysDiff;
    }

    //时间戳相隔秒
    public static long equationSeconds(long startTime, long endTime) {
        long daysDiff = ChronoUnit.SECONDS.between(
                Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime));
        return daysDiff;
    }

    //离明天还差多少秒
    public static long getIntervalTime() {
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        long lastTime = todayEnd.toInstant(ZoneOffset.of("+8")).toEpochMilli() + 1;
        long intervalTime = lastTime - System.currentTimeMillis();
        return intervalTime / 1000 + 1;
    }

    //离当月还差多少秒
    public static long getIntervalMonthTime() {
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime lastDay = todayEnd.with(TemporalAdjusters.lastDayOfMonth());
        long lastTime = lastDay.toInstant(ZoneOffset.of("+8")).toEpochMilli() + 1;
        long intervalTime = lastTime - System.currentTimeMillis();
        return intervalTime / 1000 + 1;
    }

    public static int getAge(Long birthDay) {
        Calendar cal = Calendar.getInstance();
        //当前时间
        int year = cal.get(1);
        int month = cal.get(2);
        int dayOfMonth = cal.get(5);
        //生日时间
        cal.setTime(new Date(birthDay));
        int age = year - cal.get(1);
        int monthBirth = cal.get(2);

        if (month == monthBirth) {
            int dayOfMonthBirth = cal.get(5);
            if (dayOfMonth < dayOfMonthBirth) {
                --age;
            }
        } else if (month < monthBirth) {
            --age;
        }

        return age;
    }

    /**
     * 时间戳操作工具
     *
     * @param timestamp
     * @param num
     * @param unit(ChronoUnit.DAYS/ERAS/HOURS/MINUTES/SECONDS)
     * @param type(plus/minus)
     * @return
     */
    public static Long timestampOperation(Long timestamp, int num, ChronoUnit unit, String type) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        if (PLUS.equals(type)) {
            ldt = ldt.plus(num, unit);
        } else if (MINUS.equals(type)) {
            ldt = ldt.minus(num, unit);
        }
        return ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static Long timeToLong(String time) {
        LocalDateTime parse = LocalDateTime.parse(time, dateTimeFormatter);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long dateStrToStampUTC(String dateStr, String format) {
        return dateStrToLocalDateTime(dateStr, format).toEpochSecond(ZoneOffset.UTC);
    }

    public static LocalDateTime dateStrToLocalDateTime(String dateStr, String format) {
        try {
            return dateToLocalDateTime(new SimpleDateFormat(format).parse(dateStr));
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间转换出错");
        }
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

}

