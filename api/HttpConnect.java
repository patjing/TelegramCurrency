package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URL;

public class HttpConnect extends WebAgent implements HttpConnection
{
	public HttpURLConnection HttpInit(String url)
	{		
		URL obj;
		HttpURLConnection con = null;
		
		try
		{
			obj = new URL(url);
			con = (HttpURLConnection)obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", WebAgent.USER_AGENT);
			
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return con;
	}
	
	
	public int HttpConnected(HttpURLConnection con)
	{
		int responseCode = 0;
		try 
		{
			responseCode = con.getResponseCode();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return responseCode;
	}
	
}
