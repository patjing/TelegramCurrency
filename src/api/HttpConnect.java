package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpConnect extends WebAgent implements HttpConnection
{
	private String url;
	
	public HttpConnect()
	{
		
	}
	
	
	public HttpConnect(String link)
	{
		this.url = link;
	}
	
	public String GetURL()
	{
		return url;
	}
	
	/**
	 * implement interface HttpConnection
	 * configure the URL, request method and web agent
	 * And openConnection() to connect
	 * 
	 * @author patjing
	 * @param String the request method
	 * @exception MalformedURLException
	 * @exception IOException
	 * @return	HttpURLConnection object
	 * @since	25-09-2017
	 * @version 0.0.1.0
	 */
	
	public HttpURLConnection HttpInit(String method)
	{		
		URL obj;
		HttpURLConnection con = null;
		
		try
		{
			obj = new URL(url);
			con = (HttpURLConnection)obj.openConnection();
			con.setRequestMethod(method);
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
	
	/**
	 * implement interface HttpConnection
	 * get the HTTP response code
	 * 
	 * 
	 * @author patjing
	 * @param HttpURLConnection object 
	 * @exception IOException
	 * @return	int the HTTP connection code
	 * @since	25-09-2017
	 * @version 0.0.1.0
	 */
	
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
