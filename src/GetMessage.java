package currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetMessage extends WebAgent{

	
	/**
	 * Returns BufferedReader type.
	 * Reading all content information from the default website. 
	 * In my case, it often return 502.
	 * 
	 * 
	 * @author patjing
	 * @return	BufferedReader type, all content of website
	 * @throws Exception
	 * @since	21-09-2017
	 */

	public BufferedReader getContent() throws Exception 
	{

		String url = "https://api.telegram.org/bot302785201:AAEyZ-tGoCByLVNTJWVVhIkXpYBbg5BVMh8/getUpdates";
		
		int responseCode = 0;

		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", WebAgent.USER_AGENT);
		try
		{		
			responseCode = con.getResponseCode();
		}
		catch(Exception ex)
		{
			while(responseCode>500)
			{
				responseCode = con.getResponseCode();
			}
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		return in;
	}
}
