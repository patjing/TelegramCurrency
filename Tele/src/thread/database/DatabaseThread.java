package thread.database;

import api.datetime.UTCTime;
import databaseConnection.StatementCreate;
import httpContentHandler.GetCurrency;

public class DatabaseThread implements Runnable
{
	@Override
	public void run() 
	{
		StatementCreate sc = new StatementCreate();	
		sc.setAllColumns();

		try 
		{
			while(true)
			{
				sc.insertStatement(GetCurrency.getCurrency(GetCurrency.usdLink),GetCurrency.getCurrency(GetCurrency.rmbLink),
						GetCurrency.getCurrency(GetCurrency.twdLink),GetCurrency.getCurrency(GetCurrency.jpyLink),
						GetCurrency.getCurrency(GetCurrency.krwLink),new UTCTime().getDate(UTCTime.UTCTIME),
						new UTCTime().getTime(UTCTime.UTCTIME));
				Thread.sleep(1000*10);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


}
