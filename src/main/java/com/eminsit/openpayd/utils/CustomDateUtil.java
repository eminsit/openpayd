package com.eminsit.openpayd.utils;

import java.util.Calendar;
import java.util.Date;

public final class CustomDateUtil {
    public static Date getDateOfNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
