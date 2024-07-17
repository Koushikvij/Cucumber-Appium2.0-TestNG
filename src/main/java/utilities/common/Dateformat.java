package utilities.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

public class Dateformat
{
    // Return String current date
    public synchronized static String get_SystemDate()
    {
        DateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
        Date dateobj = new Date();
        return df.format(dateobj);
    }


    //Return current year as Integer - System Date
    public synchronized static Integer get_currentyear()
    {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int currentyear = calendar.get(java.util.Calendar.YEAR);
        return currentyear;

    }

    //Return current month as Integer - System Date
    public synchronized static Integer get_currentmonth()
    {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int currentmonth = calendar.get(java.util.Calendar.MONTH);
        return currentmonth;
    }

    //Return Month from number to text with full words
    public synchronized static String getMonthByCode(String month)
    {
        if(month.equals("01"))
        {
            return "January";
        }
        if(month.equals("02"))
        {
            return "February";
        }
        if(month.equals("03"))
        {
            return "March";
        }
        if(month.equals("04"))
        {
            return "April";
        }
        if(month.equals("05"))
        {
            return "May";
        }
        if(month.equals("06"))
        {
            return "June";
        }
        if(month.equals("07"))
        {
            return "July";
        }
        if(month.equals("08"))
        {
            return "August";
        }
        if(month.equals("09"))
        {
            return "September";
        }
        if(month.equals("10"))
        {
            return "October";
        }
        if(month.equals("11"))
        {
            return "November";
        }
        if(month.equals("12"))
        {
            return "December";
        }
        else
        {
            return null;
        }
    }

    //Return Month from number to partial text with full words
    public synchronized static String getMonthreviewpage(String month)
    {
        if(month.equals("01"))
        {
            return "Jan";
        }
        if(month.equals("02"))
        {
            return "Feb";
        }
        if(month.equals("03"))
        {
            return "Mar";
        }
        if(month.equals("04"))
        {
            return "Apr";
        }
        if(month.equals("05"))
        {
            return "May";
        }
        if(month.equals("06"))
        {
            return "Jun";
        }
        if(month.equals("07"))
        {
            return "Jul";
        }
        if(month.equals("08"))
        {
            return "Aug";
        }
        if(month.equals("09"))
        {
            return "Sep";
        }
        if(month.equals("10"))
        {
            return "Oct";
        }
        if(month.equals("11"))
        {
            return "Nov";
        }
        if(month.equals("12"))
        {
            return "Dec";
        }
        else
        {
            return null;
        }
    }

    // Return Month from text to number
    public static synchronized Integer getdatepickermonth(String month)
    {
        if(month.equals("January"))
        {
            return 1;
        }
        if(month.equals("February"))
        {
            return 2;
        }
        if(month.equals("March"))
        {
            return 3;
        }
        if(month.equals("April"))
        {
            return 4;
        }
        if(month.equals("May"))
        {
            return 5;
        }
        if(month.equals("June"))
        {
            return 6;
        }
        if(month.equals("July"))
        {
            return 7;
        }
        if(month.equals("August"))
        {
            return 8;
        }
        if(month.equals("September"))
        {
            return 9;
        }
        if(month.equals("October"))
        {
            return 10;
        }
        if(month.equals("November"))
        {
            return 11;
        }
        if(month.equals("December"))
        {
            return 12;
        }
        else
        {
            return null;
        }
    }

    //To format the date
    public static synchronized String excelDateformat(String formatdate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
        String format = sdf.format(new Date(formatdate));
        Log.info(format);
        return format;
    }

    public static synchronized String pdfDateformat(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        String format = sdf.format(new Date(date));
        System.out.println(format);
        return format;
    }

    public static synchronized String getDateInGivenFormat(String date, String pattern)
    {
        SimpleDateFormat  fromUser = new SimpleDateFormat(pattern);
        SimpleDateFormat  myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            String newDateString = fromUser.format(myFormat.parse(date));
            Log.info(newDateString);
            return newDateString;
        }
        catch(ParseException e)
        {
            Log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public static synchronized String getDateInGivenFormat(String date, String dataFormat, String pattern)
    {
        SimpleDateFormat  fromUser = new SimpleDateFormat(pattern);
        SimpleDateFormat  myFormat = new SimpleDateFormat(dataFormat);
        try
        {
            String newDateString = fromUser.format(myFormat.parse(date));
            Log.info(newDateString);
            return newDateString;
        }
        catch(ParseException e)
        {
            Log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    //Return Month from number to text with full words
    public static synchronized String getMonthByShorForm(String month)
    {
        if(month.equals("Jan"))
        {
            return "January";
        }
        if(month.equals("Feb"))
        {
            return "February";
        }
        if(month.equals("Mar"))
        {
            return "March";
        }
        if(month.equals("Apr"))
        {
            return "April";
        }
        if(month.equals("May"))
        {
            return "May";
        }
        if(month.equals("Jun"))
        {
            return "June";
        }
        if(month.equals("Jul"))
        {
            return "July";
        }
        if(month.equals("Aug"))
        {
            return "August";
        }
        if(month.equals("Sep"))
        {
            return "September";
        }
        if(month.equals("Oct"))
        {
            return "October";
        }
        if(month.equals("Nov"))
        {
            return "November";
        }
        if(month.equals("Dec"))
        {
            return "December";
        }
        else
        {
            return null;
        }
    }

    public static synchronized String convert12hrsTo24hrs(String time)
    {
        DateFormat inFormat = new SimpleDateFormat("hh:mm:ss aa");
        DateFormat outFormat = new SimpleDateFormat("HH:mm");
        String convertedTime = "";
        Date date = null;
        try
        {
            date = inFormat.parse(time);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        if(date != null)
        {
            convertedTime = outFormat.format(date);
        }
        return convertedTime;
    }

    public static synchronized String getHour(String time)
    {
        String timeParts[] = time.split(":");
        String hour = timeParts[0];
        return hour;
    }

    public static synchronized String getMiniute(String time)
    {
        String timeParts[] = time.split(":");
        String minute = timeParts[1];
        return minute;
    }

    public static synchronized String getToday()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        String strDate = formatter.format(date);
        System.out.println(strDate);
        return strDate;
    }

    public static synchronized Date convertToEST(Date date) throws ParseException
    {
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
        formatter.setTimeZone(TimeZone.getTimeZone("EST"));
        return formatter.parse((formatter.format(date)));
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
    public static Integer getYearDiff(String dateofbirth)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date secondDate = sdf.parse(dateofbirth);

        String  cd= java.time.LocalDate.now().toString();
        String  a []=cd.split("-");
        Date x= sdf.parse(a[1]+"/"+a[2]+"/"+a[0]);
        return getDiffYears(secondDate,x);
    }
}