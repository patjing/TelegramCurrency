package currency;

import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class SendMessage extends WebAgent{

	/**
	 * It will return the HTTP code. Normally, it will less than 300.
	 * The UserId and the output message as the parameter. 
	 * Using POST method to post the message to the URL
	 * 
	 * @author patjing
	 * @param userID 	the user ID the program sent
	 * @param message	message content
	 * @return	HTTP response code
	 * @throws Exception
	 * @since	21-09-2017
	 */

	public int sendPost(String userID,String message) throws Exception 
	{
		String transferMessage = URLEncoder.encode(message,"UTF-8");
		String urlLink = "https://api.telegram.org/bot302785201:AAEyZ-tGoCByLVNTJWVVhIkXpYBbg5BVMh8/sendMessage?chat_id="+userID+"&text="+transferMessage;
		URL url = new URL(urlLink);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", WebAgent.USER_AGENT);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.flush();
		wr.close();

		// get the status code from the HTTP response message
		return con.getResponseCode();

	}
}
