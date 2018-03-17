package com.klepra.dailylogger.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author klemen
 */
public class DateUtils {

    public static Date getDateZeroTime(Date date) throws ParseException {
        //we want today's date with time part set to 00:00:00
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date todayWithZeroTime = formatter.parse(formatter.format(date));
        return todayWithZeroTime;
    }
    
        public static Date getTommorowsDateMidnight() throws ParseException {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        cal.add(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


}
