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
				sc.insertStatement(new GetCurrency(GetCurrency.usdLink).getCurrency(),new GetCurrency(GetCurrency.cnyLink).getCurrency(),
						new GetCurrency(GetCurrency.twdLink).getCurrency(),new GetCurrency(GetCurrency.jpyLink).getCurrency(),
						new GetCurrency(GetCurrency.krwLink).getCurrency(),new UTCTime().getDate(UTCTime.UTCTIME),
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
