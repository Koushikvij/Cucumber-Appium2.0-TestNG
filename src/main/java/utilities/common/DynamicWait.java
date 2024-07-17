package utilities.common;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class DynamicWait {

	private final static long SMALL_PAUSE = 3000;
	private final static long MEDIUM_PAUSE = 5000;
	private final static long LONG_PAUSE = 10000;

	/**
	 * Small Wait waits for 3 sec
	 */
	public static synchronized long smallWait()
	{
		try
		{
			Thread.sleep(SMALL_PAUSE);
		}
		catch (InterruptedException e)
		{
    		Log.error(ExceptionUtils.getStackTrace(e));
    		e.printStackTrace();
		}
		return 3;
	}

	/**
	 * Medium Wait waits for 5 sec
	 */
	public static synchronized long mediumWait()
	{
		try
		{
			Thread.sleep(MEDIUM_PAUSE);
		}
		catch (InterruptedException e)
		{
    		Log.error(ExceptionUtils.getStackTrace(e));
    		e.printStackTrace();
		}
		return 5;
	}

	/**
	 * Long Wait waits for 10 sec
	 * @return long 10
	 */
	public static synchronized long longWait()
	{
		try
		{
			Thread.sleep(LONG_PAUSE);
		}
		catch (InterruptedException e)
		{
    		Log.error(ExceptionUtils.getStackTrace(e));
    		e.printStackTrace();
		}
		return 10;
	}

}