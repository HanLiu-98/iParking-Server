package xyz.hanliu.iparking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HanLiu
 * @create 2020-03-10-16:21
 * @blogip 47.110.70.206
 */
public class Dateutils {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date strToDate(String str) throws ParseException {
        Date date = format.parse(str);
        return date;
    }

}
