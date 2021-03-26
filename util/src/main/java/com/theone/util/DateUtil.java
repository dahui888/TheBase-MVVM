package com.theone.util;

import android.annotation.SuppressLint;
import android.os.ParcelFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    /**         格式
     *
     *  yyyy    四位年
     *  yy      两位年
     *  MM      月份  始终两位
     *  M       月份
     *  dd      日期  始终两位
     *  d       日期
     *  HH      24小时制  始终两位
     *  H       24小时制
     *  hh      12小时制  始终两位
     *  h       12小时制
     *  mm      分钟  始终两位
     *  ss      秒  始终两位
     *
     */

    /**
     * 年月日 时分秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 月日
     */
    public static final String MM_DD = "MM-dd";

    /**
     * 周
     */
    private static final String[] WEEKS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /**
     * 获取今天 年月日 格式的 String
     *
     * @return
     */
    public static String getTodayYMDString() {
        return dateToString(new Date(), YYYY_MM_DD);
    }

    /**
     * Date 转换成 年月日 格式的 String
     *
     * @param date
     * @return
     */
    public static String dateToYMDString(Date date) {
        return dateToString(date, YYYY_MM_DD);
    }

    /**
     * Date 转换成 String
     *
     * @param date   需要转换的日期
     * @param format 转换格式
     * @return
     */
    public static String dateToString(Date date, String format) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Date 转换成  Calendar
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * Calendar 转换成 Date
     * @return
     */
    public static Date calendarToDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * string格式的日期转换成long类型
     * @param dateStr 需要转换的日期
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static long stringToLong(String dateStr) {
        return stringToLong(dateStr,YYYY_MM_DD);
    }

    /**
     * string格式的日期转换成long类型
     * @param dateStr 需要转换的日期
     * @param format 格式
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static long stringToLong(String dateStr, String format) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * string格式的日期转换成Date类型
     * @param dateStr 需要转换的日期
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String dateStr) {
       return stringToDate(dateStr,YYYY_MM_DD);
    }

    /**
     * string格式的日期转换成Date类型
     * @param dateStr 需要转换的日期
     * @param format 格式
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ParcelFormatException(e.getMessage());
        }
    }

    /**
     * 获取 - 年
     * @param calendar
     * @return
     */
    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取 - 月
     * @param calendar
     * @return
     */
    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取 - 日
     * @param calendar
     * @return
     */
    public static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取 - 周
     * @param calendar
     * @return
     */
    public static String getWeek(Calendar calendar) {
        return WEEKS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

}
