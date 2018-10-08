package com.roof;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static Date getThisYearStart(){
        Calendar start = new GregorianCalendar();
        start.set(Calendar.MONTH, 0);
        start.set(Calendar.DAY_OF_MONTH, 1);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        return start.getTime();
    }


    public static Date getThisYearEnd(){
        Calendar end = new GregorianCalendar();
        end.set(Calendar.MONTH, 11);
        end.set(Calendar.DAY_OF_MONTH, 31);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 59);
        return end.getTime();
    }

    public static Date getNowDayStart(){
        Calendar start = new GregorianCalendar();
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        return start.getTime();
    }


    public static Date getNowDayEnd(){
        Calendar end = new GregorianCalendar();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 59);
        return end.getTime();
    }
}
