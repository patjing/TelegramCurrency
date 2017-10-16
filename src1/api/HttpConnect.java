package api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpConnect extends WebAgent
{
	private String url;
	private HttpURLConnection con = null;
	private String method;
	protected int responses;
	protected BufferedReader br;
	
	public HttpConnect(String link,String method)
	{
		this.url = link;
		this.method = method;
		HttpInit();
	}
	
	
	/**
	 * implement interface HttpConnection
	 * configure the URL, request method and web agent
	 * And openConnection() to connect
	 * 
	 * @author patjing
	 * @exception MalformedURLException
	 * @exception IOException
	 * @return	HttpConnect object
	 * @since	25-09-2017
	 * @version 0.0.2.0
	 */
	@Override
	protected HttpConnect HttpInit()
	{		
		URL obj;
				
		try
		{
			obj = new URL(url);
			this.con = (HttpURLConnection)obj.openConnection();
			this.con.setRequestMethod(this.method);
			this.con.setRequestProperty("User-Agent", WebAgent.USER_AGENT);
			responses = con.getResponseCode();	
			getContent();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return this;
	}
	
	/**
	 * get the HTTP response code
	 * 
	 * @author patjing
	 * @return	int the HTTP connection code
	 * @since	25-09-2017
	 * @version 0.0.3.0
	 */
	@Override
	public int getResponseCode()
	{
		return responses;
	}
	
	/**
	 * get the HTTP content, set all the content to br
	 * 
	 * @author patjing
	 * @return	HttpConnect
	 * @since	13-10-2017
	 * @version 0.0.1.0
	 */
	private HttpConnect getContent()
	{
		try
		{	
			if(responses>400)
			{
				throw new Exception();
			}
			br = new BufferedReader(new InputStreamReader(getConnection().getInputStream()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.getCause();
		}
		
		return this;
	}

	
	/**
	 * get url link
	 * 
	 * @author patjing
	 * @return	String
	 * @since	13-10-2017
	 * @version 0.0.1.0
	 */
	public String GetURL()
	{
		return url;
	}
	
	/**
	 * get bufferedreader content link
	 * 
	 * @author patjing
	 * @return	BufferedReader
	 * @since	13-10-2017
	 * @version 0.0.1.0
	 */
	public BufferedReader getBufferedReader()
	{
		return br;
	}
	
	
	/**
	 * get connection
	 * 
	 * @author patjing
	 * @return	HttpURLConnection
	 * @since	13-10-2017
	 * @version 0.0.1.0
	 */
	public HttpURLConnection getConnection()
	{
		return con;
	}
	
	
	public HttpConnect disconnect()
	{
		con.disconnect();
		return this;
	}
	
	@Override
	public String toString()
	{
		return "HttpConnect{"
				+"url="+url
				+", connection="+con.toString()
				+", method="+method
				+", BuffereReader="+br.toString()
				+", responsecode="+responses
				+"}";
	}


}
