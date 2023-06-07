package com.ivoyant.GlobalScheduler.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
@Component
public class DateModifier {


    public Date formatDateOnZone(Date date,String zoneId)
    {
        Date parsedDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        sdf.setTimeZone(timeZone);

        // Format the date as a string
        String formattedDateString = sdf.format(date);
        System.out.println("Formatted date string: " + formattedDateString);

        try {
            // Parse the string into a Date object
             parsedDate = sdf.parse(formattedDateString);
            System.out.println("Parsed date: " + parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
