package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatetimeUtils {

	private static void setDate(String sDt, DateFormat sdf, Calendar cal) {
		if (sDt.trim().length()!=0)
			try {
				cal.setTime(sdf.parse(sDt));
			} catch (ParseException e) {
				e.printStackTrace();
				System.err.println("Date format is wrong, please put the format as yyyy-MM" + sDt);
				System.exit(1);
			}
	}

    public static String getDateString(String sDt) { 
        Calendar cal = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDate(sDt, sdf, cal);
        cal.add(Calendar.DATE, 1);        
        return sdf.format(cal.getTime());
    }
    public static String getDateStringFromCalendar(Calendar cal) { 
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");       
        return sdf.format(cal.getTime());
    }  
    public static Calendar getCalendar(String sDt) {
        Calendar cal = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDate(sDt, sdf, cal);
        return cal;
    }  
   
    public static void main (String[] args){
    	System.out.println(DatetimeUtils.getDateString("2015-10-01"));
    }
}
