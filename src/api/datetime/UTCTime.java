package api.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class UTCTime {
		
	public final static int UTCTIME = 1;
	public final static int SYSTEMTIME = 2;
	
	
	
	private static ZonedDateTime utc;
	private DateTimeFormatter formatter;
	private static ZoneId zone;
	
	
	public UTCTime()
	{
		zone = ZoneId.systemDefault();
		utc = ZonedDateTime.now(ZoneOffset.UTC);
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd EEE hh:mm:ss a z");
	}
	
	
	/**
	 * get the utc time
	 * 
	 * @author patjing
	 * @return	String	utc time
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	public String utcTime()
	{
		return utc.format(formatter);	
	}
	
	
	/**
	 * get the system time
	 * 
	 * @author patjing
	 * @return	String	system time
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	public String systemTime()
	{
		ZonedDateTime temp = utc.withZoneSameInstant(zone);
		return temp.format(formatter);
	}
	
	/**
	 * get the system time zone
	 * 
	 * @author patjing
	 * @return	String	system time zone
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	public String getSystemZone()
	{
		return zone.toString();
	}
	
	
	/**
	 * get the Date time
	 * 
	 * @author patjing
	 * @return	String	date
	 * @since	03/10/2017 
	 * @version 0.0.0.3
	 */
	public String getDate(int timeLocation)
	{
		if(timeLocation == 1)
		{
			return utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		}
		else if(timeLocation == 2)
		{
			return utc.withZoneSameInstant(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		}
		return "";
	}
	
	
	/**
	 * get the Time time
	 * 
	 * @author patjing
	 * @return	String	date
	 * @since	03/10/2017 
	 * @version 0.0.0.3
	 */
	public String getTime(int timeLocation)
	{
		if(timeLocation == 1)
		{
			return utc.format(DateTimeFormatter.ofPattern("HH:mm:ss")); 
		}
		else if(timeLocation == 2)
		{
			return utc.withZoneSameInstant(zone).format(DateTimeFormatter.ofPattern("HH:mm:ss")); 
		}
		return "";
	}

}
