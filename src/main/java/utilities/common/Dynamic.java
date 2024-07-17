package utilities.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Dynamic
{
	
	public synchronized static String dynamicEmailID ()
	{
		String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
		String emaiID = "qa".concat(timeStamp.concat("@nStore.com"));
		return emaiID;
	}
	
	public synchronized static String currentDate()
	{
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		return currentDate;
	}
	
	public synchronized static String currentTime()
	{
		String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		return currentTime;
	}
	
	public synchronized static String futureDate()
	{
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_YEAR, 1);	    
	    Date tomorrow = calendar.getTime();
	    String futureDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
	    return futureDate;
	}
	
	public synchronized static String currentDateWithSlash()
	{
		String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		return currentDate;
	}
	
	public synchronized static String futureTime(int hour, int minute) {
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
		//create Calendar instance
	    Calendar now = Calendar.getInstance();
	    now.add(Calendar.HOUR,hour);
	    now.add(Calendar.MINUTE, minute);
	    sd.setTimeZone(TimeZone.getTimeZone("IST"));
	    String futureTime = sd.format(now.getTime());
		return futureTime;
	}
	
	public synchronized static String futureDateWithSlash()
	{
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_YEAR, 1);	    
	    Date tomorrow = calendar.getTime();
	    String futureDate = new SimpleDateFormat("dd/MMM/yyyy").format(tomorrow);
	    return futureDate;
	}
	
	public synchronized static String currentDateWithSlashFormat1()
	{
		String currentDate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
		return currentDate;
	}
	
	public synchronized static String getDynamicName() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10)
		{ // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString().toLowerCase();
        return saltStr;
	}

	public synchronized static Integer getRandomIntegerInRange(Integer StartingInteger, Integer EndingInteger)
	{
		Random random = new Random();
		return random.nextInt(EndingInteger - StartingInteger) + StartingInteger ;
	}
}
