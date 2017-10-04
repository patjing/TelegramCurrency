package httpContentHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import api.HttpConnect;


public class GetMessage extends HttpConnect{

	private static String url = "https://api.telegram.org/bot302785201:AAEyZ-tGoCByLVNTJWVVhIkXpYBbg5BVMh8/getUpdates";
	
	//set the global String url
	protected static void setURL(String link)
	{
		url = link;
	}
	
	//get the global String url
	public static String getURL()
	{
		return url;
	}
	
	/**
	 * Returns BufferedReader type.
	 * Reading all content information from the default website. 
	 * In my case, it often return 502.
	 * 
	 * 
	 * @author patjing
	 * @return	BufferedReader type, all content of website
	 * @exception IOException
	 * @since	25-09-2017
	 * @version 0.0.0.2
	 */

	public BufferedReader getContent()
	{

		
		
		HttpConnect connect = new HttpConnect(url);
		HttpURLConnection con = connect.HttpInit("GET");
		int responseCode = connect.HttpConnected(con);
		
		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			if(responseCode>400)
			{
				throw new Exception();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.getCause();
		}
		
		return in;
	}
}
