package com.test.date;

import com.github.crab2died.exceptions.TimeMatchFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>时间处理工具类</p>
 * author : Crab2Died
 * date : 2017/5/23  10:35
 */
public class DateUtils {

    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DAY_2 = "yyyy/MM/dd";
    public static final String TIME_FORMAT_SEC = "HH:mm:ss";
    public static final String DATE_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MSEC = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_MSEC_T = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_FORMAT_MSEC_T_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT_DAY_SIMPLE = "y/M/d";

    /**
     * 匹配yyyy-MM-dd
     */
    private static final String DATE_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
    /**
     * 匹配yyyy/MM/dd
     */
    private static final String DATE_REG_2 = "^[1-9]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])$";
    /**
     * 匹配y/M/d
     */
    private static final String DATE_REG_SIMPLE_2 = "^[1-9]\\d{3}/([1-9]|1[0-2])/([1-9]|[1-2][0-9]|3[0-1])$";
    /**
     * 匹配HH:mm:ss
     */
    private static final String TIME_SEC_REG = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
    /**
     * 匹配yyyy-MM-dd HH:mm:ss
     */
    private static final String DATE_TIME_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s" +
            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
    /**
     * 匹配yyyy-MM-dd HH:mm:ss.SSS
     */
    private static final String DATE_TIME_MSEC_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s" +
            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
    /**
     * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    private static final String DATE_TIME_MSEC_T_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T" +
            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
    /**
     * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    private static final String DATE_TIME_MSEC_T_Z_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T" +
            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}Z$";

    /**
     * <p>将{@link Date}类型转换为指定格式的字符串</p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:32:04
     *
     * @param date   {@link Date}类型的时间
     * @param format 指定格式化类型
     * @return 返回格式化后的时间字符串
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * <p>将{@link Date}类型转换为默认为[yyyy-MM-dd HH:mm:ss]类型的字符串</p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:30:01
     *
     * @param date {@link Date}类型的时间
     * @return 返回格式化后的时间字符串
     */
    public static String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SEC);
        return sdf.format(date);
    }

    /**
     * <p>根据给出的格式化类型将时间字符串转为{@link Date}类型</p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:27:22
     *
     * @param strDate 时间字符串
     * @param format  格式化类型
     * @return 返回{@link java.util.Date}类型
     */
    public static Date str2Date(String strDate, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * <p>字符串时间转为{@link Date}类型，
     * 未找到匹配类型则抛出{@link TimeMatchFormatException}异常</p>
     * <p>支持匹配类型列表：</p>
     * <p>yyyy-MM-dd</p>
     * <p>yyyy/MM/dd</p>
     * <p>HH:mm:ss</p>
     * <p>yyyy-MM-dd HH:mm:ss</p>
     * <p>yyyy-MM-dd'T'HH:mm:ss.SSS</p>
     * <p>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</p>
     * <p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:21:54
     *
     * @param strDate 时间字符串
     * @return Date  {@link Date}时间
     * @throws ParseException 异常
     */
    public static Date str2Date(String strDate) throws ParseException {

        strDate = strDate.trim();
        SimpleDateFormat sdf = null;
        if (RegularUtils.isMatched(strDate, DATE_REG)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_DAY);
        }
        if (RegularUtils.isMatched(strDate, DATE_REG_2)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_DAY_2);
        }
        if (RegularUtils.isMatched(strDate, DATE_REG_SIMPLE_2)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_DAY_SIMPLE);
        }
        if (RegularUtils.isMatched(strDate, TIME_SEC_REG)) {
            sdf = new SimpleDateFormat(TIME_FORMAT_SEC);
        }
        if (RegularUtils.isMatched(strDate, DATE_TIME_REG)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_SEC);
        }
        if (RegularUtils.isMatched(strDate, DATE_TIME_MSEC_REG)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC);
        }
        if (RegularUtils.isMatched(strDate, DATE_TIME_MSEC_T_REG)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC_T);
        }
        if (RegularUtils.isMatched(strDate, DATE_TIME_MSEC_T_Z_REG)) {
            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC_T_Z);
        }
        if (null != sdf) {
            return sdf.parse(strDate);
        }
        throw new TimeMatchFormatException(
                String.format("[%s] can not matching right time format", strDate));
    }

    /**
     * <p>字符串时间转为{@link Date}类型，未找到匹配类型则返NULL</p>
     * <p>支持匹配类型列表：</p>
     * <p>yyyy-MM-dd</p>
     * <p>yyyy/MM/dd</p>
     * <p>HH:mm:ss</p>
     * <p>yyyy-MM-dd HH:mm:ss</p>
     * <p>yyyy-MM-dTHH:mm:ss.SSS</p>
     * <p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:21:54
     *
     * @param strDate 时间字符串
     * @return Date  {@link Date}时间
     */
    public static Date str2DateUnmatch2Null(String strDate) {
        Date date = null;
        try {
            date = str2Date(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) throws ParseException {

        Date date = new Date();
        String str1 = DateUtils.date2Str(date,DATE_FORMAT_MSEC);
        System.out.println(str1);//2017-12-28 10:28:29.209

        String str2 =  DateUtils.date2Str(date);
        System.out.println(str2);//2017-12-28 10:28:29

        Date date1 = DateUtils.str2Date(str1);
        System.out.println(date1);//Thu Dec 28 10:29:34 CST 2017

        Date date11 = DateUtils.str2Date("10:29:34");
        System.out.println(date11);//Thu Jan 01 10:29:34 CST 1970

        Date date111 = DateUtils.str2Date("2017-12-28");
        System.out.println(date111);//Thu Dec 28 00:00:00 CST 2017

        Date date2 = DateUtils.str2Date("10:29:34",DateUtils.TIME_FORMAT_SEC);
        System.out.println(date2);//Thu Jan 01 10:29:34 CST 1970
    }
}
