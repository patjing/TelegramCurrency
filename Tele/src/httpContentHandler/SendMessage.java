package httpContentHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import api.HttpConnect;

public class SendMessage extends HttpConnect{

	/**
	 * It will return the HTTP code. Normally, it will less than 300.
	 * The UserId and the output message as the parameter. 
	 * Using POST method to post the message to the URL
	 * 
	 * @author patjing
	 * @param userID 	the user ID the program sent
	 * @param message	message content
	 * @return	HTTP response code
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @since	25-09-2017
	 * @versions 0.0.0.2
	 */
	public int sendPost(String userID,String message)
	{
		String transferMessage = null;			
		try 
		{
			transferMessage = URLEncoder.encode(message,"UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{

			e.printStackTrace();
		}
		
		String urlLink = "https://api.telegram.org/bot302785201:AAEyZ-tGoCByLVNTJWVVhIkXpYBbg5BVMh8/sendMessage?chat_id="+userID+"&text="+transferMessage;
		
		HttpConnect connect = new HttpConnect(urlLink);
		HttpURLConnection con = connect.HttpInit("POST");
		int responseCode = connect.HttpConnected(con);

		// get the status code from the HTTP response message
		return responseCode;

	}
}
